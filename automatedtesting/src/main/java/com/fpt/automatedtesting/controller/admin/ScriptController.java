package com.fpt.automatedtesting.controller.admin;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ScriptController {
    @GetMapping("/writescript")
    public String getString(){
        return "ok";
    }
    @GetMapping("/read")
    public String readSomething() throws IOException {
        Resource resource = new ClassPathResource("AzureDevOps-master\\src\\test\\java\\com\\thucnh\\azuredevops\\AzuredevopsApplicationTests.java");
        InputStream inputStream = resource.getInputStream();
        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            String data = new String(bdata, StandardCharsets.UTF_8);
            int startIndex = data.indexOf("//start");
            String startPart = data.substring(0,startIndex) + "//start";
            int remainderIndex = data.indexOf("//end");
            String remainderPart =  data.substring(remainderIndex,data.length());
            String middle = "\n Phan nay se de code automated testing \n";
            String fullScript = startPart + middle + remainderPart;
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
}
