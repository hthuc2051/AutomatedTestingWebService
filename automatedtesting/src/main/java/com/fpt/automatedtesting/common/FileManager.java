package com.fpt.automatedtesting.common;

import com.fpt.automatedtesting.practicalexams.dtos.PracticalExamTemplateDto;
import com.fpt.automatedtesting.practicalexams.dtos.StudentSubmissionDto;
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
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class FileManager {

    public static void uploadFile(StudentSubmissionDto dto) {
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

    //    Zip folder
    public static void zipFolder(String folder, String outPath) throws IOException {
        FileOutputStream fos = new FileOutputStream(outPath + ".zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(folder);
        zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
        fos.close();
    }

    public static void downloadZip(File fileParam, OutputStream output) {
        try {
            File file = fileParam;
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[4096];
                int b = -1;
                while ((b = fis.read(buffer)) != -1) {
                    output.write(buffer, 0, b);
                }
                fis.close();
                output.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFolder(String folder) {
        File file = new File(folder);
        if (file.isDirectory()) {
            file.delete();
        }
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    public static void getAllFiles(String directoryName, List<File> files, String extension) {
        // Get all files from a directory.
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        if (fList != null)
            for (File file : fList) {
                if (file.isFile()) {
                    if (file.getName().endsWith(extension)) {
                        files.add(file);
                    }
                } else if (file.isDirectory()) {
                    getAllFiles(file.getAbsolutePath(), files, extension);
                }
            }
    }
}

