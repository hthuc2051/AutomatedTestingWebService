package com.fpt.automatedtesting.controller;

import com.fpt.automatedtesting.dto.request.CodeDto;
import com.fpt.automatedtesting.dto.request.TestScriptParamDto;
import com.fpt.automatedtesting.utils.ZipFile;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ScriptController {
    @GetMapping("/writescript")
    public String getString(){
        return "ok";
    }
    @PostMapping("/read")
    public String readSomething(@RequestBody TestScriptParamDto script) throws IOException {
        Resource resource = new ClassPathResource("AzureDevOps-master\\src\\test\\java\\com\\thucnh\\azuredevops\\AzuredevopsApplicationTests.java");
        InputStream inputStream = resource.getInputStream();
        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            String data = new String(bdata, StandardCharsets.UTF_8);
            String middlePart = "";
            for ( CodeDto code : script.getQuestion()) {
                    middlePart += code.getCode();
            }
            int startIndex = data.indexOf("//start");
            String startPart = data.substring(0,startIndex) + "//start";
            int remainderIndex = data.indexOf("//end");
            String remainderPart =  data.substring(remainderIndex,data.length());
            String fullScript = startPart + middlePart + remainderPart;
            BufferedWriter writer = new BufferedWriter(new FileWriter("G:\\ProjectSpringBoot\\automatedtesting\\src\\main\\resources\\AzureDevOps-master\\src\\test\\java\\com\\thucnh\\azuredevops\\AzuredevopsApplicationTests.java",false));
            writer.write(fullScript);
            writer.close();
            inputStream.close();
            return "written successfully";
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "ok";
    }
    @GetMapping("/testzip")
    public String getTestZip() throws IOException {
        ZipFile.zipping(null);
        return "ok";
    }
    @GetMapping("/download")
    public String downloadFile(HttpServletResponse response) throws IOException {
        String filePath = "ziptest.zip";
        File file = new File(filePath);
        String mimeType = "application/octet-stream";
        response.setContentType(mimeType);
        response.addHeader("Content-Disposition", "attachment; filename=" + filePath);
        response.setContentLength((int) file.length());
        OutputStream os = response.getOutputStream();
        ZipFile.downloadZip(file,os);
        return "ok";
    }
}
