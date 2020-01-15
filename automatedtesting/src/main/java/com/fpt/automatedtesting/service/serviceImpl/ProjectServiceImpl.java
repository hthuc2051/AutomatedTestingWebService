package com.fpt.automatedtesting.service.serviceImpl;

import com.fpt.automatedtesting.entity.Script;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.repository.ScriptRepository;
import com.fpt.automatedtesting.service.ProjectService;
import com.fpt.automatedtesting.utils.ZipFile;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class ProjectServiceImpl implements ProjectService {
    private static final String JAVA_CONSOLE_FOLDER_NAME = "JavaConsole";
    private static final String JAVA_CONSOLE_TEST_FOLDER_PATH = "\\src\\test\\java\\com\\example\\JavaConsole";

    @Autowired
    private final ScriptRepository scriptRepository;

    public ProjectServiceImpl(ScriptRepository scriptRepository) {
        this.scriptRepository = scriptRepository;
    }

    @Override
    public void downloadJavaConsoleApp(HttpServletResponse response, Integer scriptId) {
        String folPath = null;
        try {
            folPath = ResourceUtils.getFile("classpath:static").getAbsolutePath();
            // add test case to project
            Script script = scriptRepository.getOne(scriptId);
            String scriptFileName = script.getScriptPath().substring(script.getScriptPath().lastIndexOf("\\") + 1);
            copy(new File(script.getScriptPath()), new File(folPath + File.separator + JAVA_CONSOLE_FOLDER_NAME + JAVA_CONSOLE_TEST_FOLDER_PATH + File.separator + scriptFileName));
            // find and zip project
            String filePath = folPath + File.separator + JAVA_CONSOLE_FOLDER_NAME;
            //zip project
            ZipFile.zipping(filePath);
            //download zip file
            filePath = filePath + ".zip";
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

    private static void copy(File src, File dest) throws IOException {
        File folder = dest.getParentFile();
        dest.deleteOnExit();
        folder.mkdirs();
        dest.createNewFile();
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dest);

            // buffer size 1K
            byte[] buf = new byte[1024];

            int bytesRead;
            while ((bytesRead = is.read(buf)) > 0) {
                os.write(buf, 0, bytesRead);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}
