package com.fpt.automatedtesting.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ScriptServiceImpl implements ScriptService {

    private static final String PREFIX_START = "//start";
    private static final String PREFIX_END = "//end";
    private static final String EXTENSION_JAVA = ".java";
    private static final String EXTENSION_C = ".c";
    private static final String EXTENSION_CSharp = ".cs";
    private static final String QUESTION_POINT_STR_VALUE ="questionPointStrValue";

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
            String docsFolPath = "";
            // Select path to create and save script test by type
            switch (subject.getCode()) {
                case CustomConstant.TEMPLATE_TYPE_JAVA:
                    templatePath = PathConstants.PATH_TEMPLATE_JAVA;
                    scriptStorePath = PathConstants.PATH_SCRIPT_JAVA;
                    docsFolPath = PathConstants.PATH_DOCS_JAVA;
                    fileExtension = EXTENSION_JAVA;
                    break;
                case CustomConstant.TEMPLATE_TYPE_JAVA_WEB:
                    templatePath = PathConstants.PATH_TEMPLATE_JAVA_WEB;
                    scriptStorePath = PathConstants.PATH_SCRIPT_JAVA_WEB;
                    docsFolPath = PathConstants.PATH_DOCS_JAVA_WEB;
                    fileExtension = EXTENSION_JAVA;
                    break;
                case CustomConstant.TEMPLATE_TYPE_CSHARP:
                    templatePath = PathConstants.PATH_TEMPLATE_C_SHARP;
                    scriptStorePath = PathConstants.PATH_SCRIPT_C_SHARP;
                    docsFolPath = PathConstants.PATH_DOCS_C_SHARP;
                    fileExtension = EXTENSION_CSharp;

                    break;
                case CustomConstant.TEMPLATE_TYPE_C:
                    templatePath = PathConstants.PATH_TEMPLATE_C;
                    scriptStorePath = PathConstants.PATH_SCRIPT_C;
                    docsFolPath = PathConstants.PATH_DOCS_C;
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

            ObjectMapper mapper = new ObjectMapper();
            // [{"testcase":"testcase1", "code":"ABC"}, {"testcase":"testcase2", "code":"AB2C"}]
            String middlePart = "";
            List<CodeDto> codeDtoList = Arrays.asList(mapper.readValue(dto.getQuestions(), CodeDto[].class));
            if (codeDtoList != null && codeDtoList.size() > 0) {
                for (CodeDto code : codeDtoList) {
                    middlePart += code.getCode().replace(QUESTION_POINT_STR_VALUE, "\"");
                }
            }
            int startIndex = data.indexOf(PREFIX_START);
            String startPart = data.substring(0, startIndex) + PREFIX_START;
            int endIndex = data.indexOf(PREFIX_END);

            String endPart = data.substring(endIndex, data.length());
            String tempScript = startPart + "\n" + middlePart + "\n" + endPart;
            String fullScript = tempScript.replace(QUESTION_POINT_STR_VALUE, dto.getQuestionPointStr());
            // Write new file to Scripts_[Language] folder
            Date date = new Date();
            Integer hashCode = CustomUtils.getCurDateTime(date, "Date").hashCode();
            String code = subject.getCode() + "_" + Math.abs(hashCode);
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(scriptStorePath + code + fileExtension,
                            false));
            writer.write(fullScript);
            writer.close();
            inputStream.close();

            // Copy docs file to Docs_[Language] folder
            MultipartFile docsFile = dto.getDocsFile();
            if (docsFile != null) {
                Path copyLocation = Paths.get(docsFolPath + File.separator + code + ".docx");
                Files.copy(docsFile.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            }
            // Save to database
            Script script = new Script();
            script.setName(dto.getName());
            script.setHeadLecturer(headLecturer);
            script.setSubject(subject);
            script.setCode(code);

            script.setTimeCreated(CustomUtils.getCurDateTime(date, "Date"));

            // TODO:SetPath later
            script.setScriptPath("Path temp");
//
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
