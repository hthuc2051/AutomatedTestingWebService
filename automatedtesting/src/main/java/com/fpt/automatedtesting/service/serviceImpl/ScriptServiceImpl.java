package com.fpt.automatedtesting.service.serviceImpl;

import com.fpt.automatedtesting.common.CustomConstant;
import com.fpt.automatedtesting.common.CustomMessages;
import com.fpt.automatedtesting.constants.PathConstants;
import com.fpt.automatedtesting.dto.request.CodeDto;
import com.fpt.automatedtesting.dto.request.ScriptRequestDto;
import com.fpt.automatedtesting.dto.response.ScriptResponseDto;
import com.fpt.automatedtesting.entity.Script;
import com.fpt.automatedtesting.entity.User;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.mapper.MapperManager;
import com.fpt.automatedtesting.repository.ScriptRepository;
import com.fpt.automatedtesting.repository.UserRepository;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class ScriptServiceImpl implements ScriptService {

    private static final String PREFIX_START = "//start";
    private static final String PREFIX_END = "//end";

    private final ScriptRepository scriptRepository;
    private final UserRepository userRepository;

    @Autowired
    public ScriptServiceImpl(ScriptRepository scriptRepository, UserRepository userRepository) {
        this.scriptRepository = scriptRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ScriptResponseDto> getAll() {
        List<ScriptResponseDto> result = MapperManager.mapAll(scriptRepository.findAll(), ScriptResponseDto.class);
        return result;
    }

    @Override
    public Boolean generateScriptTest(ScriptRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found user with id" + dto.getUserId()));
        List<User> users = new ArrayList<>();
        try {
            if (dto == null) throw new CustomException(HttpStatus.NOT_FOUND, CustomMessages.MSG_SCRIPT_NULL);
            String templatePath = "";
            String scriptStorePath = "";
            String fileExtension = "";

            // Select path to create and save script test by type
            switch (dto.getType()) {
                case CustomConstant.TEMPLATE_TYPE_JAVA:
                    templatePath = PathConstants.PATH_TEMPLATE_JAVA;
                    scriptStorePath = PathConstants.PATH_SCRIPT_JAVA;
                    fileExtension = ".java";
                    break;
                case CustomConstant.TEMPLATE_TYPE_JAVA_WEB:
                    templatePath = PathConstants.PATH_TEMPLATE_JAVA_WEB;
                    scriptStorePath = PathConstants.PATH_SCRIPT_JAVA_WEB;
                    fileExtension = ".java";
                    break;
                case CustomConstant.TEMPLATE_TYPE_CSHARP:
                    templatePath = PathConstants.PATH_TEMPLATE_C_SHARP;
                    scriptStorePath = PathConstants.PATH_SCRIPT_C_SHARP;
                    fileExtension = "";

                    break;
                case CustomConstant.TEMPLATE_TYPE_C:
                    templatePath = PathConstants.PATH_TEMPLATE_C;
                    scriptStorePath = PathConstants.PATH_SCRIPT_C;
                    fileExtension = ".c";
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
            // TODO: Add prefix cur datetime later
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(scriptStorePath + dto.getName() + fileExtension,
                            false));
            writer.write(fullScript);
            writer.close();
            inputStream.close();

            // Save to database
            Script script = new Script();
            script.setTimeCreated(CustomUtils.getCurDateTime("Date"));
            script.setScriptPath("");
            users.add(user);
            script.setUsers(users);
            if (scriptRepository.save(script) != null) {
                return true;
            }
//            // Copy script file to Server
//            Path sourceDirectory = Paths.get(PathConstants.PATH_SCRIPT_JAVA + script.getName());
//            Path targetDirectory = Paths.get(PathConstants.PATH_SERVER_JAVA + "De2.java");
//
//            //copy source to target using Files Class
//            Files.copy(sourceDirectory, targetDirectory);
//            //
//            String zipPath = ResourceUtils.getFile("classpath:static").getAbsolutePath();
//            ZipFile.zipping(zipPath);
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
