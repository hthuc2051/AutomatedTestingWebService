package com.fpt.automatedtesting.service.serviceImpl;

import com.fpt.automatedtesting.common.CustomConstant;
import com.fpt.automatedtesting.common.CustomMessages;
import com.fpt.automatedtesting.constants.PathConstants;
import com.fpt.automatedtesting.dto.request.CodeDto;
import com.fpt.automatedtesting.dto.request.ScriptRequestDto;
import com.fpt.automatedtesting.dto.response.ScriptResponseDto;
import com.fpt.automatedtesting.entity.HeadLecturer;
import com.fpt.automatedtesting.entity.Script;
import com.fpt.automatedtesting.entity.Subject;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.mapper.MapperManager;
import com.fpt.automatedtesting.repository.HeadLecturerRepository;
import com.fpt.automatedtesting.repository.ScriptRepository;
import com.fpt.automatedtesting.repository.SubjectRepository;
import com.fpt.automatedtesting.service.ScriptService;
import com.fpt.automatedtesting.utils.CustomUtils;
import com.fpt.automatedtesting.utils.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
public class ScriptServiceImpl implements ScriptService {

    private static final String PREFIX_START = "//start";
    private static final String PREFIX_END = "//end";
    private static final String EXTENSION_JAVA = ".java";
    private static final String EXTENSION_C = ".c";
    private static final String EXTENSION_CSharp = ".cs";
    private static final String PREFIX_SCRIPT = "_Script_";

    private final ScriptRepository scriptRepository;
    private final HeadLecturerRepository headLecturerRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public ScriptServiceImpl(ScriptRepository scriptRepository, HeadLecturerRepository headLecturerRepository, SubjectRepository subjectRepository) {
        this.scriptRepository = scriptRepository;
        this.headLecturerRepository = headLecturerRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<ScriptResponseDto> getAll() {
        List<ScriptResponseDto> result = MapperManager.mapAll(scriptRepository.findAll(), ScriptResponseDto.class);
        return result;
    }

    @Override
    public Boolean generateScriptTest(ScriptRequestDto dto) {
        HeadLecturer headLecturer = headLecturerRepository.findById(dto.getHeadLecturerId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found user with id" + dto.getHeadLecturerId()));
        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found subject with id" + dto.getSubjectId()));

        try {
            if (dto == null) throw new CustomException(HttpStatus.NOT_FOUND, CustomMessages.MSG_SCRIPT_NULL);
            String templatePath = "";
            String scriptStorePath = "";
            String fileExtension = "";

            // Select path to create and save script test by type
            switch (subject.getCode()) {
                case CustomConstant.TEMPLATE_TYPE_JAVA:
                    templatePath = PathConstants.PATH_TEMPLATE_JAVA;
                    scriptStorePath = PathConstants.PATH_SCRIPT_JAVA;
                    fileExtension = EXTENSION_JAVA;
                    break;
                case CustomConstant.TEMPLATE_TYPE_JAVA_WEB:
                    templatePath = PathConstants.PATH_TEMPLATE_JAVA_WEB;
                    scriptStorePath = PathConstants.PATH_SCRIPT_JAVA_WEB;
                    fileExtension = EXTENSION_JAVA;
                    break;
                case CustomConstant.TEMPLATE_TYPE_CSHARP:
                    templatePath = PathConstants.PATH_TEMPLATE_C_SHARP;
                    scriptStorePath = PathConstants.PATH_SCRIPT_C_SHARP;
                    fileExtension = EXTENSION_CSharp;

                    break;
                case CustomConstant.TEMPLATE_TYPE_C:
                    templatePath = PathConstants.PATH_TEMPLATE_C;
                    scriptStorePath = PathConstants.PATH_SCRIPT_C;
                    fileExtension = EXTENSION_C;
                    break;
                default:
                    throw new CustomException(HttpStatus.CONFLICT, "TypeConflictNotSupported");
            }

            // Get template path
            Resource resource = new ClassPathResource(templatePath);
            if (resource == null) throw new CustomException(HttpStatus.NOT_FOUND, "TemplateNotFound");
            InputStream inputStream = resource.getInputStream();
            byte[] bData = FileCopyUtils.copyToByteArray(inputStream);
            String data = new String(bData, StandardCharsets.UTF_8);

            String middlePart = "";
            for (CodeDto code : dto.getQuestions()) {
                middlePart += code.getCode().replace("'", "\"");
            }

            int startIndex = data.indexOf(PREFIX_START);
            String startPart = data.substring(0, startIndex) + PREFIX_START;
            int endIndex = data.indexOf(PREFIX_END);

            String endPart = data.substring(endIndex, data.length());
            String fullScript = startPart + "\n" + middlePart + "\n" + endPart;

            // Write new file to Scripts_Languages folder
            Date date = new Date();
            String scriptCode = subject.getCode() + "_" + dto.getName() + "_" + CustomUtils.getCurDateTime(date, "Date");
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(scriptStorePath + scriptCode + fileExtension,
                            false));
            writer.write(fullScript);
            writer.close();
            inputStream.close();

            // Save to database
            Script script = new Script();
            script.setHeadLecturer(headLecturer);
            script.setSubject(subject);
            script.setCode(scriptCode);

            script.setTimeCreated(CustomUtils.getCurDateTime(date, "Date"));

            // TODO:SetPath later
            script.setScriptPath("Path temp");

            if (scriptRepository.save(script) != null) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException(HttpStatus.CONFLICT, e.getMessage());
        }
        return false;
    }

    @Override
    public void downloadFile(HttpServletResponse response) {
        String folPath = null;
        try {
            folPath = ResourceUtils.getFile("classpath:static").getAbsolutePath();
            String filePath = folPath + File.separator + "SE63155.zip";
            File file = new File(filePath);
            String mimeType = "application/octet-stream";
            response.setContentType(mimeType);
            response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());
            response.setContentLength((int) file.length());
            OutputStream os = null;
            os = response.getOutputStream();

            ZipFile.downloadZip(file, os);
        } catch (FileNotFoundException e) {
            throw new CustomException(HttpStatus.CONFLICT, e.getMessage());
        } catch (IOException e) {
            throw new CustomException(HttpStatus.CONFLICT, e.getMessage());
        }
    }


}
