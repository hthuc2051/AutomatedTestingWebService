package com.fpt.automatedtesting.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UploadFile {
  public static void uploadFile(String folder, MultipartFile file)
  {
     try{
         Path copyLocation = Paths.get(folder + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
         Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
     }catch(Exception ex)
     {
         System.out.println(ex.getMessage());
     }
  }
}
