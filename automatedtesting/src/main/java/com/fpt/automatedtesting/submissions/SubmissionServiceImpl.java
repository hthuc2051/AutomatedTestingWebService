package com.fpt.automatedtesting.submissions;

import com.fpt.automatedtesting.common.FileManager;
import com.fpt.automatedtesting.submissions.dtos.request.SubmissionFilesRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.fpt.automatedtesting.common.CustomConstant.*;
import static com.fpt.automatedtesting.common.PathConstants.*;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Override
    public List<List<String>> getSubmissionFiles(SubmissionFilesRequest request) {

        // Check lecturer token later
        List<List<String>> result = null;
        List<File> files = new ArrayList<>();
        String token = request.getFilesToken();
        String[] arr = token.split("~");
        String firstFile = "";
        String secondFile = "";

        String extension = "";
        if (arr != null && arr.length >= 2) {
            int indexFirstFile = arr[0].lastIndexOf("_");
            int indexSecondFile = arr[1].lastIndexOf("_");
            if (indexFirstFile > 0 && indexSecondFile > 0) {
                firstFile = arr[0].substring(0, indexFirstFile);
                secondFile = arr[1].substring(0, indexSecondFile);
                if (firstFile.contains(EXTENSION_JAVA) && secondFile.contains(EXTENSION_JAVA)) {
                    extension = EXTENSION_JAVA;
                } else if (firstFile.contains(EXTENSION_CSHARP) && secondFile.contains(EXTENSION_CSHARP)) {
                    extension = EXTENSION_CSHARP;
                } else if (firstFile.contains(EXTENSION_C) && secondFile.contains(EXTENSION_C)) {
                    extension = EXTENSION_C;
                }
                FileManager.getAllFiles(PATH_SERVER_REPOSITORY, files, extension);
                if (files.size() > 0) {
                    result = new ArrayList<>();
                    if (!firstFile.equals("") && !secondFile.equals("")) {
                        for (File file : files) {
                            String fileName = file.getName();
                            if (fileName.contains(firstFile) || fileName.contains(secondFile)) {
                                try {
                                    List<String> fileString = Files.readAllLines(Paths.get(file.getAbsolutePath()));
                                    result.add(fileString);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
