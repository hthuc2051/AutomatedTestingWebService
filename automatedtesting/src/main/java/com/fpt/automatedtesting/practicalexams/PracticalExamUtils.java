package com.fpt.automatedtesting.practicalexams;

import com.fpt.automatedtesting.common.CustomConstant;
import com.fpt.automatedtesting.githubResult.dtos.GitHubFileDuplicateDTO;
import com.fpt.automatedtesting.githubResult.dtos.GitHubResponseDTO;
import com.google.gson.Gson;
import org.kohsuke.github.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

import static com.fpt.automatedtesting.common.PathConstants.*;
import static com.fpt.automatedtesting.common.CustomConstant.*;


public class PracticalExamUtils {
    static HttpURLConnection myURLConnection = null;
    static Map<String, GitHubFileDuplicateDTO> functionSimilarityToFile = new HashMap<>();
    static int timeCallAPIInAFunction = 0;
   static  int readingIndex = 0;
   static  int readLengthBase = 110;

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
            return replaceAllSpecialChar(encoded);
        } catch (Exception ex) {
            System.out.println("readFile error : " + ex.getMessage());
        }
        return null;
    }

    public static String replaceAllSpecialChar(byte[] encoded) {
        String replaceString = new String(encoded, Charset.forName("UTF-8"))
                .replace("{", "")
                .replace("}", "")
                .replace(";", "")
                .replace("/", "")
                .replace("*", "")
                .replace("@", "")
                .replace("(", "")
                .replace(")", "")
                .replace('"', ' ');
        return replaceString;
    }

    public static String removeNullOrBlankElements(String lineOfCode) {
        String inputString = lineOfCode.replaceAll("\r\n", "\t");
//        String[] lines = inputString.trim().split("\t", -1);
        lineOfCode = lineOfCode.replaceAll("\\s+", " ");
        return lineOfCode;
//        String[] removedNull = Arrays.stream(lines)
//                .filter(value ->
//                        value != null && value.length() > 0 && !value.trim().equals("")
//                )
//                .toArray(size -> new String[size]);
//        if (language.equals(LANGUAGE_JAVA)) {
//            try {
//                List<String> removeList = new ArrayList<>(Arrays.asList(removedNull));
//                for (String line : removedNull) {
//                    if (line.length() >= 6) {
//                        if (line.substring(0, 6).contains(IMPORT_JAVA)) {
//                            if (!line.substring(7, line.length()).contains("com")) {
//                                removeList.remove(line);
//                            }
//                        }
//                    }
//                }
//                removedNull = removeList.toArray(new String[0]);
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//            }
//        } else if (language.equals(LANGUAGE_CS)) {
//            try {
//                List<String> removeList = new ArrayList<>(Arrays.asList(removedNull));
//                for (String line : removedNull) {
//                    if (line.length() >= 5) {
//                        if (line.substring(0, 5).contains(IMPORT_CS)) {
//                            if (!line.substring(6, line.length()).contains("com")) {
//                                removeList.remove(line);
//                            }
//                        }
//                    }
//                }
//                removedNull = removeList.toArray(new String[0]);
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//            }
//        } else if (language.equals(LANGUAGE_C)) {
//            try {
//                List<String> removeList = new ArrayList<>(Arrays.asList(removedNull));
//                for (String line : removedNull) {
//                    if (line.length() >= 8) {
//                        if (line.substring(0, 8).contains(IMPORT_C)) {
//                            if (!line.substring(9, line.length()).contains("com")) {
//                                removeList.remove(line);
//                            }
//                        }
//                    }
//                }
//                removedNull = removeList.toArray(new String[0]);
//
//            } catch (Exception ex) {
//                System.out.println("removeNullOrBlankElements error : " + ex.getMessage());
//            }
        //       }
//        String convertedToString = Arrays.toString(removedNull);
//        convertedToString = convertedToString.replaceAll("\\s+", " ");
//        return convertedToString;
    }

    static Gson gson = new Gson();

    //    public static void searchRepo(GitHub gitHub, String lineOfCode, String language) {
//
//        if (gson == null) gson = new Gson();
//        try {
//            List<String> pageUrl = new ArrayList<>();
//            GHContentSearchBuilder search = gitHub.searchContent();
//            String q = URLEncoder.encode(lineOfCode, java.nio.charset.StandardCharsets.UTF_8.toString()).replace("+", "%20");
////            GHContentSearchBuilder searchBuilder = search.q(q).in("file").language(language);
////            PagedSearchIterable<GHContent> res = searchBuilder.list();
////            for (GHContent ghContent : res) {
////                GHRepository ghRepository = ghContent.getOwner();
////                pageUrl.add(ghRepository.getHtmlUrl().toString() + "\r\n");
////            }
//            String url = "https://api.github.com/search/code?q=" + q + "+in%3Afile+language%3AJAVA";
//            System.out.println("====================Start===================");
//            System.out.println("line: " + lineOfCode);
//            Thread.sleep(6000);
//            String response = search(url);
//            System.out.println(response);
//            GitHubResponseDTO listResponse = gson.fromJson(response, GitHubResponseDTO.class);
//            addResultToListFunctionSimilarity(listResponse.getItems());
//            System.out.println("====================END===================");
//            //    writeToFile("", lineOfCode, pageUrl);
//            //write to file;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
    static int count = 0;
    public static void searchRepo(String lineOfCode, String language) {

        if (gson == null) gson = new Gson();
        boolean isSentSuccess = false;
        do{
            try {
                String q = URLEncoder.encode(lineOfCode, java.nio.charset.StandardCharsets.UTF_8.toString()).replace("+", "%20");
                String url = "https://api.github.com/search/code?q=" + q + "+in%3Afile+language%3A"+language;
                System.out.println("====================Start===================");
                System.out.println("line:" + lineOfCode);
                Thread.sleep(6000);
                String response = search(url);
                System.out.println(response);
                GitHubResponseDTO listResponse = gson.fromJson(response, GitHubResponseDTO.class);
                if(listResponse.getTotal_count() != 0) System.out.println("TIME GET DATA: "+ ++count);;
                addResultToListFunctionSimilarity(listResponse.getItems(),lineOfCode.length());
                System.out.println("====================END===================");
                isSentSuccess = true;
                //    writeToFile("", lineOfCode, pageUrl);
                //write to file;
            } catch (Exception ex) {
                // IF exception is "length is not longer than 128" THEN continue ELSE break
                ex.printStackTrace();
                if(!ex.getMessage().contains("response code: 422")) break;
                readLengthBase -= 10;
                int newIndex = findCodeStatementIndex(lineOfCode,0,readLengthBase);
                readingIndex  = readingIndex  - lineOfCode.length() + newIndex;
                lineOfCode = lineOfCode.substring(0,newIndex);
            }
        }while (!isSentSuccess);

    }

    private static void addResultToListFunctionSimilarity(List<GitHubFileDuplicateDTO> listFile,int length) {
        for (GitHubFileDuplicateDTO dto : listFile) {
            if (functionSimilarityToFile.containsKey(dto.getHtml_url())) {
                int lastSimilarity =  functionSimilarityToFile.get(dto.getHtml_url()).getScore();
                functionSimilarityToFile.get(dto.getHtml_url()).setScore(lastSimilarity + length);
            } else {
                dto.setScore(length);
                functionSimilarityToFile.put(dto.getHtml_url(), dto);
            }
        }
    }

    public static String search(String url) throws IOException {
        URL obj = new URL(url);
        myURLConnection = (HttpURLConnection) obj.openConnection();
        String basicAuth = "token " + AUTH_TOKEN;
        myURLConnection.setRequestProperty("Authorization", basicAuth);
        myURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        myURLConnection.setRequestProperty("Content-Language", "en-US");
        myURLConnection.setUseCaches(false);
        myURLConnection.setDoInput(true);
        myURLConnection.setDoOutput(true);
        myURLConnection.setRequestMethod("GET");
        myURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        myURLConnection.disconnect();
        in.close();
        return response.toString();
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

    //    public static void writeReport(GitHub github, String lineOfCode, String language) {
//
//        try {
//            int i = 0;
//            int readLength = 110;
//            int nextRead = 201;
////            lineOfCode = lineOfCode.replace("[", "").replace("]", "").trim();
//            do {
//                timeCallAPIInAFunction++;
//                if (i < lineOfCode.length() && lineOfCode.length() > readLength) {
//                    if (i + readLength > lineOfCode.length()) {
//                        //  String subLineOfCode = lineOfCode.substring(i, lineOfCode.length()).replace(",", "").trim();
//                        String subLineOfCode = lineOfCode.substring(i, lineOfCode.length());
//                        searchRepo(github, subLineOfCode, language);
//                        break;
//                    } else {
//                        //   String subLineOfCode = lineOfCode.substring(i, i + readLength).replace(",", "").trim();
//                        String subLineOfCode = lineOfCode.substring(i, i + readLength);
//                        searchRepo(github, subLineOfCode, language);
//                    }
//                } else if (lineOfCode.length() <= readLength) {
//                    //    String subLineOfCode = lineOfCode.substring(i, lineOfCode.length()).replace(",", "").trim();
//                    String subLineOfCode = lineOfCode.substring(i, lineOfCode.length());
//                    searchRepo(github, subLineOfCode, language);
//                    break;
//                } else {
//                    //    String subLineOfCode = lineOfCode.substring(i, lineOfCode.length() - 1).replace(",", "").trim();
//                    String subLineOfCode = lineOfCode.substring(i, lineOfCode.length() - 1);
//                    searchRepo(github, subLineOfCode, language);
//                    break;
//                }
//
//                i = i + readLength;
//            } while (i < lineOfCode.length());
//        } catch (Exception ex) {
//            System.out.println("writeReport error : " + ex.getMessage());
//        }
//
//    }
    public static void writeReport(String lineOfCode, String language) {
        try {
            readingIndex = 0;
            readLengthBase = 110;
            String sendString = "";
            do {
                timeCallAPIInAFunction++;
                int length = lineOfCode.length();
                if (length <= readLengthBase) {
                    searchRepo(lineOfCode,language);
                    readingIndex = readLengthBase;
                }else{
                   int index =  findCodeStatementIndex(lineOfCode,readingIndex,readLengthBase);
                   sendString = lineOfCode.substring(readingIndex,index);
                   readingIndex = index;
                   searchRepo(sendString,language);
                }
            } while (readingIndex < lineOfCode.length());
        } catch (Exception ex) {
            System.out.println("writeReport error : " + ex.getMessage());
        }

    }


    private static int findCodeStatementIndex(String lineOfCode, int currentIndex, int baseLength){
        int lastIndex = currentIndex + baseLength;
        if(lastIndex >= lineOfCode.length()) return lineOfCode.length();
        if(' ' == lineOfCode.charAt(lastIndex)){
            System.out.println("lastIndex "+lineOfCode.charAt(lastIndex));
            return lastIndex + 1;
        }else{
            char nextCharacter = lineOfCode.charAt(lastIndex + 1);
            if(' ' == nextCharacter){
                return lastIndex + 1;
            }else{
                String checkingString = lineOfCode.substring(currentIndex,lastIndex);
                int breakIndex  = 0;
                for(int i = checkingString.length()- 1;i >= 0; i--){
                    if(checkingString.charAt(i) == ' '){
                        breakIndex = i + 1;
                        break;
                    }
                }
                return currentIndex + breakIndex;
            }
        }
    }


    //    public static Map<String, GitHubFileDuplicateDTO> checkDuplicatedCodeGithub(String code, String extension) {
//        timeCallAPIInAFunction = 0;
//        functionSimilarityToFile.clear();
//        try {
//            System.out.println("Check online - " + code);
//            GitHub github = getConnection("hthuc2051", "d85504b05b9f3fb33bb108138e9c11e503dc9deb");
//            String language = "";
//            if (".java".equals(extension)) {
//                language = LANGUAGE_JAVA;
//            } else if (".cs".equals(extension)) {
//                language = LANGUAGE_CS;
//            } else if (".c".equals(extension)) {
//                language = LANGUAGE_C;
//            }
//            String convertedString = removeNullOrBlankElements(code, language);
//            writeReport(github, convertedString, language);
//            for (Map.Entry<String, GitHubFileDuplicateDTO> item : functionSimilarityToFile.entrySet()) {
//                item.getValue().setScore(item.getValue().calculateAverageScore());
//                System.out.println(item.getValue());
//            }
//        } catch (Exception ex) {
//            System.out.println("Log Github error : " + ex.getMessage());
//        }
//        return functionSimilarityToFile;
//    }
    public static Map<String, GitHubFileDuplicateDTO> checkDuplicatedCodeGithub(String code, String extension) {
        timeCallAPIInAFunction = 0;
        functionSimilarityToFile.clear();
        try {
            System.out.println("Check online - " + code);
            String language = "";
            if (".java".equals(extension)) {
                language = LANGUAGE_JAVA;
            } else if (".cs".equals(extension)) {
                language = LANGUAGE_CS;
            } else if (".c".equals(extension)) {
                language = LANGUAGE_C;
            }
            writeReport(code, language);
        } catch (Exception ex) {
            System.out.println("Log Github error : " + ex.getMessage());
        }
        return functionSimilarityToFile;
    }

    public static void pushCodeToGithubStorage(String filePath) {
        try {
            String url = "";
            URL obj = new URL(url);
            HttpURLConnection myURLConnection = (HttpURLConnection) obj.openConnection();
            String basicAuth = "token b72ee1f1156a3a546de9004dcb60821c74cd5738";
            myURLConnection.setRequestProperty("Authorization", basicAuth);
            myURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            myURLConnection.setRequestProperty("Content-Language", "en-US");
            myURLConnection.setUseCaches(false);
            myURLConnection.setDoInput(true);
            myURLConnection.setDoOutput(true);
            myURLConnection.setRequestMethod("GET");
            myURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            myURLConnection.disconnect();
            in.close();
        } catch (Exception ex) {
            System.out.println("Log Github error : " + ex.getMessage());
        }
    }
}
