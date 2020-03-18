package com.fpt.automatedtesting.common;

import com.fpt.automatedtesting.practicalexams.dtos.PracticalExamTemplateDto;
import com.fpt.automatedtesting.practicalexams.dtos.UploadFileDto;
import com.fpt.automatedtesting.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class UploadFile {

    public static void uploadFile(UploadFileDto dto) {
        try {
            MultipartFile file = dto.getFile();
            if (file != null) {
                String folPath = ResourceUtils.getFile("classpath:static").getAbsolutePath();
                Path copyLocation = Paths.get(folPath + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
                Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new CustomException(HttpStatus.CONFLICT, ex.getMessage());
        }
    }

    public static String uploadFileToReturnString(MultipartFile excelFile) {
        try {
            MultipartFile file = excelFile;
            if (file != null) {
                String folPath = ResourceUtils.getFile("classpath:static").getAbsolutePath();
                Path copyLocation = Paths.get(folPath + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
                if (Files.exists(copyLocation)) {
                    Files.delete(copyLocation);
                }
                Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                Thread.sleep(1000);
                return copyLocation.toString();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void unzip(String filePath, String destination) {

        try {
            ZipFile zipFile = new ZipFile(filePath);
            zipFile.extractAll(destination);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    public static void uploadTemplate(PracticalExamTemplateDto dto) {
        try {
            String folPath = ResourceUtils.getFile("classpath:static").getAbsolutePath();
            String csFolderScript = folPath + "/Template_CSharp";
            String javaFolderScript = folPath + "/Template_Java";
            String webFolderScript = folPath + "/Template_JavaWeb";
            String cFolderScript = folPath + "/Template_C";
            MultipartFile scriptfile = dto.getScriptFile();
            MultipartFile serverfile = dto.getServerFile();
            String path = "";
            if (scriptfile != null && serverfile != null) {
                Path copyLocation = Paths.get(folPath + File.separator + StringUtils.cleanPath(scriptfile.getOriginalFilename()));
                Files.copy(scriptfile.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                String pathScript = folPath + File.separator + StringUtils.cleanPath(scriptfile.getOriginalFilename());
                if (dto.getScriptSubject().equals("CSharp")) {
                    unzip(pathScript, csFolderScript);
                } else if (dto.getScriptSubject().equals("Java")) {
                    unzip(pathScript, javaFolderScript);
                } else if (dto.getScriptSubject().equals("Web")) {
                    unzip(pathScript, webFolderScript);
                } else if (dto.getScriptSubject().equals("C")) {
                    unzip(pathScript, cFolderScript);
                }

                Path copyLocationServer = Paths.get(folPath + File.separator + StringUtils.cleanPath(serverfile.getOriginalFilename()));
                Files.copy(serverfile.getInputStream(), copyLocationServer, StandardCopyOption.REPLACE_EXISTING);
                String pathServer = folPath + File.separator + StringUtils.cleanPath(serverfile.getOriginalFilename());
                if (dto.getServerSubject().equals("CSharp")) {
                    unzip(pathServer, PathConstants.PATH_SERVER_C_SHARP);
                } else if (dto.getServerSubject().equals("Java")) {
                    unzip(pathServer, PathConstants.PATH_SERVER_JAVA);
                } else if (dto.getServerSubject().equals("Web")) {
                    unzip(pathServer, PathConstants.PATH_SERVER_JAVA_WEB);
                } else if (dto.getServerSubject().equals("C")) {
                    unzip(pathServer, PathConstants.PATH_SERVER_C);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new CustomException(HttpStatus.CONFLICT, ex.getMessage());
        }
    }
}

