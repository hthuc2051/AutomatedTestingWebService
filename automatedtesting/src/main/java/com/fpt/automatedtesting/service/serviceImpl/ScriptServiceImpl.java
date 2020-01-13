package com.fpt.automatedtesting.service.serviceImpl;

import com.fpt.automatedtesting.common.CustomMessages;
import com.fpt.automatedtesting.dto.request.CodeDto;
import com.fpt.automatedtesting.dto.request.TestScriptParamDto;
import com.fpt.automatedtesting.dto.response.ScriptResponseDto;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.mapper.MapperManager;
import com.fpt.automatedtesting.repository.ScriptRepository;
import com.fpt.automatedtesting.service.ScriptService;
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
import java.util.List;

@Service
public class ScriptServiceImpl implements ScriptService {

    private static final String PREFIX_START = "//start";
    private static final String PREFIX_END = "//end";
    private static final String TEMPLATE_SCRIPT_JAVA = "static/ScripTestJava.java";

    @Autowired
    private final ScriptRepository scriptRepository;

    public ScriptServiceImpl(ScriptRepository scriptRepository) {
        this.scriptRepository = scriptRepository;
    }

    @Override
    public List<ScriptResponseDto> getAll() {
        List<ScriptResponseDto> result = MapperManager.mapAll(scriptRepository.findAll(), ScriptResponseDto.class);
        return result;
    }

    @Override
    public Boolean generateScriptTest(TestScriptParamDto script) {
        try {
            if (script == null) throw new CustomException(HttpStatus.NOT_FOUND, CustomMessages.MSG_SCRIPT_NULL);
            Resource resource = new ClassPathResource(TEMPLATE_SCRIPT_JAVA);
            if (resource == null) throw new CustomException(HttpStatus.NOT_FOUND, "File not found");
            InputStream inputStream = resource.getInputStream();
            byte[] bData = FileCopyUtils.copyToByteArray(inputStream);
            String data = new String(bData, StandardCharsets.UTF_8);

            String middlePart = "";
            for (CodeDto code : script.getQuestions()) {
                middlePart += code.getCode().replace("'", "\"");
            }

            int startIndex = data.indexOf(PREFIX_START);
            String startPart = data.substring(0, startIndex) + PREFIX_START;
            int endIndex = data.indexOf(PREFIX_END);
            System.out.println(resource.getURI().getPath());
            System.out.println(resource.getURL().getPath());
            String endPart = data.substring(endIndex, data.length());
            String fullScript = startPart + "\n" + middlePart + "\n" + endPart;
            String filePath = ResourceUtils.getFile("classpath:static/ScripTestJava.java").getAbsolutePath();
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
            writer.write(fullScript);
            writer.close();
            inputStream.close();
            String zipPath = ResourceUtils.getFile("classpath:static").getAbsolutePath();
            ZipFile.zipping(zipPath);
        } catch (IOException e) {
            throw new CustomException(HttpStatus.CONFLICT, e.getMessage());
        }
        return true;
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
