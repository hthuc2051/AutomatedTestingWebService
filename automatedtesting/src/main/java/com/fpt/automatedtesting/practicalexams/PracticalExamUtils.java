package com.fpt.automatedtesting.practicalexams;

import org.kohsuke.github.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.fpt.automatedtesting.common.PathConstants.*;
import static com.fpt.automatedtesting.common.CustomConstant.*;

public class PracticalExamUtils {


    public static GitHub getConnection(String username, String token) throws IOException {
        try {
            GitHub github = GitHub.connect(username, token);
            return github;
        } catch (Exception ex) {
            System.out.println("getConnection error : " + ex.getMessage());
        }
        return null;
    }

    public static String readFile(String filePath) throws IOException {
        try {
            File file = new File(filePath);
            byte[] encoded = Files.readAllBytes(file.toPath());
            return new String(encoded, Charset.forName("UTF-8"))
                    .replace("{", "")
                    .replace("}", "")
                    .replace(";", "");
        } catch (Exception ex) {
            System.out.println("readFile error : " + ex.getMessage());
        }
        return null;
    }

    public static String removeNullOrBlankElements(String lineOfCode, String language) {
        String inputString = lineOfCode.replace("\r\n", "\t");
        String[] lines = inputString.trim().split("\t", -1);

        String[] removedNull = Arrays.stream(lines)
                .filter(value ->
                        value != null && value.length() > 0 && !value.trim().equals("")
                )
                .toArray(size -> new String[size]);
        if (language.equals(LANGUAGE_JAVA)) {
            try {
                List<String> removeList = new ArrayList<>(Arrays.asList(removedNull));
                for (String line : removedNull) {
                    if (line.length() >= 6) {
                        if (line.substring(0, 6).contains(IMPORT_JAVA)) {
                            if (!line.substring(7, line.length()).contains("com")) {
                                removeList.remove(line);
                            }
                        }
                    }
                }
                removedNull = removeList.toArray(new String[0]);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else if (language.equals(LANGUAGE_CS)) {
            try {
                List<String> removeList = new ArrayList<>(Arrays.asList(removedNull));
                for (String line : removedNull) {
                    if (line.length() >= 5) {
                        if (line.substring(0, 5).contains(IMPORT_CS)) {
                            if (!line.substring(6, line.length()).contains("com")) {
                                removeList.remove(line);
                            }
                        }
                    }
                }
                removedNull = removeList.toArray(new String[0]);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else if (language.equals(LANGUAGE_C)) {
            try {
                List<String> removeList = new ArrayList<>(Arrays.asList(removedNull));
                for (String line : removedNull) {
                    if (line.length() >= 8) {
                        if (line.substring(0, 8).contains(IMPORT_C)) {
                            if (!line.substring(9, line.length()).contains("com")) {
                                removeList.remove(line);
                            }
                        }
                    }
                }
                removedNull = removeList.toArray(new String[0]);

            } catch (Exception ex) {
                System.out.println("removeNullOrBlankElements error : " + ex.getMessage());
            }
        }
        String convertedToString = Arrays.toString(removedNull);
        convertedToString = convertedToString.replaceAll("\\s+", " ");
        return convertedToString;
    }

    public static void searchRepo(GitHub gitHub, String lineOfCode, String language) {
        try {
            List<String> pageUrl = new ArrayList<>();
            GHContentSearchBuilder search = gitHub.searchContent();
            GHContentSearchBuilder searchBuilder = search.q(lineOfCode).in("file").language(language);
            PagedSearchIterable<GHContent> res = searchBuilder.list();
            for (GHContent ghContent : res) {
                System.out.println(ghContent.getOwner());
                GHRepository ghRepository = ghContent.getOwner();
                pageUrl.add(ghRepository.getHtmlUrl().toString() + "\r\n");
            }
            writeToFile("", lineOfCode, pageUrl);
            //write to file;
        } catch (Exception ex) {
            System.out.println("searchRepo error : " + ex.getMessage());
        }
    }

    public static void writeToFile(String fileReportPath, String queryCode, List<String> resultPage) {
        try {
            String[] resultConverted = resultPage.toArray(new String[0]);
            FileWriter writer = new FileWriter(GITHUB_LOG_PATH, true);
            writer.write("System has searched the line of code : " + queryCode);
            writer.write("\r\n");   // write new line
            writer.write("The result is here :");
            writer.write("\r\n");
            writer.write("The total detected : " + resultConverted.length);
            writer.write("\r\n");
            writer.write(Arrays.toString(resultConverted));
            writer.write("\r\n");
            writer.close();
        } catch (Exception ex) {
            System.out.println("writeToFile error :" + ex.getMessage());
        }
    }

    public static void writeReport(GitHub github, String lineOfCode, String language) {
        try {
            int i = 0;
            int readLength = 120;
            int nextRead = 121;
            lineOfCode = lineOfCode.replace("[", "").replace("]", "").trim();
            do {
                if (i < lineOfCode.length() && lineOfCode.length() > readLength) {
                    if (i + readLength > lineOfCode.length()) {
                        String subLineOfCode = lineOfCode.substring(i, lineOfCode.length()).replace(",", "").trim();
                        searchRepo(github, subLineOfCode, language);
                        break;
                    } else {
                        String subLineOfCode = lineOfCode.substring(i, i + readLength).replace(",", "").trim();
                        searchRepo(github, subLineOfCode, language);
                    }
                } else if (lineOfCode.length() <= readLength) {
                    String subLineOfCode = lineOfCode.substring(i, lineOfCode.length()).replace(",", "").trim();
                    searchRepo(github, subLineOfCode, language);
                    break;
                } else {
                    String subLineOfCode = lineOfCode.substring(i, lineOfCode.length() - 1).replace(",", "").trim();
                    searchRepo(github, subLineOfCode, language);
                    break;
                }
                i = i + nextRead;
            } while (i < lineOfCode.length());
        } catch (Exception ex) {
            System.out.println("writeReport error : " + ex.getMessage());
        }

    }

    public static void checkDuplicatedCodeGithub(String filePath) {
        try {
            GitHub github = getConnection("lamthanhphat98", "a6d23ad8a3c5a82870c06142bd8ef0ccba2c62cc");
            //System.out.println(github.getMyself().getEmail());
            //Map<String,GHBranch> hashMap = new HashMap<>();
            //read file
            //String filePath ="C:\\Users\\ADMIN\\Downloads\\student\\ShoeDAO.java";
            String language = "";
            if (filePath.contains(".java")) {
                language = LANGUAGE_JAVA;
            } else if (filePath.contains(".cs")) {
                language = LANGUAGE_CS;
            } else if (filePath.contains(".c")) {
                language = LANGUAGE_C;
            }
            String inputString = readFile(filePath);
            String convertedString = removeNullOrBlankElements(inputString, language).trim();
            writeReport(github, convertedString, language);
        } catch (Exception ex) {
            System.out.println("logGithub error : " + ex.getMessage());
        }
    }
}
