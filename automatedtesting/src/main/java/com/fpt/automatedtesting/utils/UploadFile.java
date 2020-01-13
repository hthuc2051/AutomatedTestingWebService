package com.fpt.automatedtesting.utils;

import com.fpt.automatedtesting.dto.request.UploadFileDto;
import com.fpt.automatedtesting.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UploadFile {

  public static void uploadFile( UploadFileDto dto) {
      try {
          MultipartFile file = dto.getFile();
          if(file != null) {
              String folPath = ResourceUtils.getFile("classpath:static").getAbsolutePath();
              Path copyLocation = Paths.get(folPath + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
              Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
          }
      } catch (Exception ex) {
          System.out.println(ex.getMessage());
          throw new CustomException(HttpStatus.CONFLICT,ex.getMessage());
      }
  }
}
