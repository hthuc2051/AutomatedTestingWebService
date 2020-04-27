package com.fpt.automatedtesting.common;

import java.io.File;

public class PathConstants {

    public static final String PROJECT_DIR = System.getProperty("user.dir");
    public static final String STATIC_DIR = "static/";

    public static final String PATH_DB_TOOLS = PROJECT_DIR + File.separator + "DBTools";
    public static final String PATH_DB_TEMPLATE = PROJECT_DIR + File.separator + "DBTemplates";
    public static final String PATH_TEMPLATE_PROJECT = PROJECT_DIR + File.separator + "TemplateProject";

    public static final String PATH_EXCEPTIONS = PROJECT_DIR + File.separator + "Exceptions";
    public static final String PATH_SUBMISSIONS = PROJECT_DIR + File.separator + "Submissions";
    public static final String PATH_SERVER_REPOSITORY = PROJECT_DIR + File.separator + "ServerRepository";

    // Path server
    private static final String SERVER_PREFIX = PROJECT_DIR + File.separator + "Servers" + File.separator;

    // Server template
    public static final String PATH_SERVER_JAVA_WEB = SERVER_PREFIX + "Server_JavaWeb" + File.separator + "Server";
    public static final String PATH_SERVER_JAVA = SERVER_PREFIX + "Server_Java" + File.separator + "Server";
    public static final String PATH_SERVER_C = SERVER_PREFIX + "Server_C" + File.separator + "Server";
    public static final String PATH_SERVER_CSHARP = SERVER_PREFIX + "Server_CSharp" + File.separator + "Server";

    // Server check online
    public static final String PATH_SERVER_ONLINE_JAVA_WEB = SERVER_PREFIX + "Server_JavaWeb" + File.separator + "Server_Online";
    public static final String PATH_SERVER_ONLINE_JAVA = SERVER_PREFIX + "Server_Java" + File.separator + "Server_Online";
    public static final String PATH_SERVER_ONLINE_C = SERVER_PREFIX + "Server_C" + File.separator + "Server_Online";
    public static final String PATH_SERVER_ONLINE_CSHARP = SERVER_PREFIX + "Server_CSharp" + File.separator + "Server_Online";

    // Prefix path;

    private static final String SRC_MAIN_JAVA_COM_PRACTICAL_EXAM_STUDENT = "Server" + File.separator
            + "src" + File.separator
            + "main" + File.separator
            + "java" + File.separator
            + "com" + File.separator
            + "practicalexam" + File.separator
            + "student" + File.separator;

    // Java
    public static final String PATH_SERVER_ONLINE_JAVA_STUDENT = PATH_SERVER_ONLINE_JAVA + File.separator
            + "Server" + File.separator
            + "src" + File.separator
            + "main" + File.separator
            + "java" + File.separator
            + "com" + File.separator
            + "practicalexam" + File.separator
            + "student";

    public static final String PATH_SERVER_ONLINE_JAVA_TEST = PATH_SERVER_ONLINE_JAVA + File.separator
            + "src" + File.separator
            + "test" + File.separator
            + "java" + File.separator
            + "com" + File.separator
            + "practicalexam";


    // Java web
    public static final String PATH_SERVER_ONLINE_JAVA_WEB_STUDENT = PATH_SERVER_ONLINE_JAVA_WEB + File.separator
            + "Server" + File.separator
            + "src" + File.separator
            + "main" + File.separator
            + "java" + File.separator
            + "com" + File.separator
            + "practicalexam" + File.separator
            + "student";

    public static final String PATH_SERVER_ONLINE_JAVA_WEB_TEST = PATH_SERVER_ONLINE_JAVA_WEB + File.separator
            + "Server" + File.separator
            + "src" + File.separator
            + "test" + File.separator
            + "java" + File.separator
            + "server" + File.separator;

    public static final String PATH_SERVER_ONLINE_JAVA_WEB_CONNECTION = PATH_SERVER_ONLINE_JAVA_WEB + File.separator
            + SRC_MAIN_JAVA_COM_PRACTICAL_EXAM_STUDENT
            + "connection";


    public static final String PATH_SERVER_ONLINE_CSHARP_STUDENT = "";

    public static final String PATH_SERVER_ONLINE_C_STUDENT = "";

    //    Link repo
    public static final String LINK_GITHUB_REPO_JAVA_WEB = "https://github.com/headlecturer2020/PE2A_Practical_JavaWeb";
    public static final String LINK_GITHUB_REPO_JAVA = "https://github.com/headlecturer2020/PE2A_Practical_Java";
    public static final String LINK_GITHUB_REPO_C = "https://github.com/headlecturer2020/PE2A_Practical_C";
    public static final String LINK_GITHUB_REPO_CSHARP = "https://github.com/headlecturer2020/PE2A_Practical_CSharp";

    // Path scripts
    private static final String SCRIPTS_PREFIX = PROJECT_DIR + File.separator + "Scripts" + File.separator;
    public static final String PATH_SCRIPT_JAVA_WEB = SCRIPTS_PREFIX + "Scripts_JavaWeb" + File.separator;
    public static final String PATH_SCRIPT_JAVA = SCRIPTS_PREFIX + "Scripts_Java" + File.separator;
    public static final String PATH_SCRIPT_C = SCRIPTS_PREFIX + "Scripts_C" + File.separator;
    public static final String PATH_SCRIPT_CSHARP = SCRIPTS_PREFIX + "Scripts_CSharp" + File.separator;

    // Path scripts
    public static final String PATH_SCRIPT_JAVA_WEB_ONLINE = SCRIPTS_PREFIX + "Scripts_JavaWeb_Online" + File.separator;
    public static final String PATH_SCRIPT_JAVA_ONLINE = SCRIPTS_PREFIX + "Scripts_Java_Online" + File.separator;
    public static final String PATH_SCRIPT_C_ONLINE = SCRIPTS_PREFIX + "Scripts_C_Online" + File.separator;
    public static final String PATH_SCRIPT_CSHARP_ONLINE = SCRIPTS_PREFIX + "Scripts_CSharp_Online" + File.separator;

    // Path docs
    private static final String DOCS_PREFIX = PROJECT_DIR + File.separator + "Documents" + File.separator;

    public static final String PATH_DOCS_JAVA_WEB = DOCS_PREFIX + "Docs_JavaWeb" + File.separator;
    public static final String PATH_DOCS_JAVA = DOCS_PREFIX + "Docs_Java" + File.separator;
    public static final String PATH_DOCS_C = DOCS_PREFIX + "Docs_C" + File.separator;
    public static final String PATH_DOCS_CSHARP = DOCS_PREFIX + "Docs_CSharp" + File.separator;

    // Path TEMPLATE QUESTION
    private static final String PATH_TEMPLATE_QUESTION_PREFIX = PROJECT_DIR + File.separator + "TemplateQuestion" + File.separator;
    public static final String PATH_TEMPLATE_QUESTION_JAVA_WEB = PATH_TEMPLATE_QUESTION_PREFIX + "JavaWeb" + File.separator;
    public static final String PATH_TEMPLATE_QUESTION_JAVA = PATH_TEMPLATE_QUESTION_PREFIX + "Java" + File.separator;
    public static final String PATH_TEMPLATE_QUESTION_C = PATH_TEMPLATE_QUESTION_PREFIX + "C" + File.separator;
    public static final String PATH_TEMPLATE_QUESTION_C_SHARP = PATH_TEMPLATE_QUESTION_PREFIX + "CSharp" + File.separator;

    // Path TEMPLATE QUESTION TEMPLATE
    private static final String PATH_TEMPLATE_QUESTION_TEMPLATE_PREFIX = PROJECT_DIR + File.separator + "TemplateQuestionTemplate" + File.separator;
    public static final String PATH_TEMPLATE_QUESTION_TEMPLATE_JAVA_WEB = PATH_TEMPLATE_QUESTION_TEMPLATE_PREFIX + "JavaWeb" + File.separator;
    public static final String PATH_TEMPLATE_QUESTION_TEMPLATE_JAVA = PATH_TEMPLATE_QUESTION_TEMPLATE_PREFIX + "Java" + File.separator;
    public static final String PATH_TEMPLATE_QUESTION_TEMPLATE_C = PATH_TEMPLATE_QUESTION_TEMPLATE_PREFIX + "C" + File.separator;
    public static final String PATH_TEMPLATE_QUESTION_TEMPLATE_C_SHARP = PATH_TEMPLATE_QUESTION_TEMPLATE_PREFIX + "CSharp" + File.separator;

    // Path Database
    private static final String PATH_DATABASE_PREFIX = PROJECT_DIR + File.separator + "Database" + File.separator;
    public static final String PATH_DATABASE_SCRIPT_JAVA_WEB = PATH_DATABASE_PREFIX + "JavaWeb" + File.separator;
    public static final String PATH_DATABASE_SCRIPT_JAVA = PATH_DATABASE_PREFIX + "Java" + File.separator;
    public static final String PATH_DATABASE_SCRIPT_C = PATH_DATABASE_PREFIX + "C" + File.separator;
    public static final String PATH_DATABASE_SCRIPT_C_SHARP = PATH_DATABASE_PREFIX + "CSharp" + File.separator;

    // Path TestData
    private static final String PATH_TESTDATA_PREFIX = PROJECT_DIR + File.separator + "TestData" + File.separator;
    public static final String PATH_TESTDATA_JAVA_WEB = PATH_TESTDATA_PREFIX + "JavaWeb" + File.separator;
    public static final String PATH_TESTDATA_JAVA = PATH_TESTDATA_PREFIX + "Java" + File.separator;
    public static final String PATH_TESTDATA_C = PATH_TESTDATA_PREFIX + "C" + File.separator;
    public static final String PATH_TESTDATA_C_SHARP = PATH_TESTDATA_PREFIX + "CSharp" + File.separator;

    // Path template
    public static final String PATH_TEMPLATE_JAVA_WEB = STATIC_DIR
            + File.separator
            + "Template_JavaWeb"
            + File.separator
            + "JavaApplicationTests.java";

    public static final String PATH_TEMPLATE_JAVA = STATIC_DIR
            + File.separator
            + "Template_Java"
            + File.separator
            + "JavaApplicationTests.java";

    public static final String PATH_TEMPLATE_C = STATIC_DIR
            + File.separator
            + "Template_C" +
            File.separator
            + "ScriptTestC.c";
    public static final String PATH_TEMPLATE_CSHARP = STATIC_DIR
            + File.separator
            + "Template_CSharp"
            + File.separator
            + "ScriptTestCSharp.cs";

    public static final String PATH_TEMPLATE_CSV_STUDENT_RESULT = STATIC_DIR + File.separator + "Student_Results.csv";
    public static final String PATH_TEMPLATE_CSV_STUDENT_LIST = STATIC_DIR + File.separator + "Student_List.csv";


    // Path created practical
    public static final String PATH_PRACTICAL_EXAMS = PROJECT_DIR + File.separator + "PracticalExams";


    // Lexer and parser file

    // Java
    public static final String PATH_GRAMMAR_JAVA_LEXER = PROJECT_DIR
            + File.separator
            + "Grammar"
            + File.separator
            + "JavaLexer.g4";
    public static final String PATH_GRAMMAR_JAVA_PARSER = PROJECT_DIR
            + File.separator
            + "Grammar"
            + File.separator
            + "JavaParser.g4";

    // CSharp
    public static final String PATH_GRAMMAR_CSHARP_LEXER = PROJECT_DIR
            + File.separator
            + "Grammar"
            + File.separator
            + "CSharpLexer.g4";
    public static final String PATH_GRAMMAR_CSHARP_PARSER = PROJECT_DIR
            + File.separator
            + "Grammar"
            + File.separator
            + "CSharpParser.g4";

    // C
    public static final String PATH_GRAMMAR_C_PARSER = PROJECT_DIR
            + File.separator
            + "Grammar"
            + File.separator
            + "C.g4";


    // Log path
    public static final String GITHUB_LOG_PATH = STATIC_DIR + "Github_Log.txt";


    // Path Template project

    public static final String PATH_STUDENT_JAVA_WEB = PATH_TEMPLATE_PROJECT
            + File.separator + "JavaWebTemplate"
            + File.separator + "src"
            + File.separator + "java"
            + File.separator + "com"
            + File.separator + "practicalexam"
            + File.separator + "student";

    public static final String PATH_STUDENT_JAVA = PATH_TEMPLATE_PROJECT
            + File.separator + "JavaTemplate"
            + File.separator + "src"
            + File.separator + "com"
            + File.separator + "practicalexam"
            + File.separator + "student";


    public static final String PATH_STUDENT_C = PATH_TEMPLATE_PROJECT;
    public static final String PATH_STUDENT_CSHARP = PATH_TEMPLATE_PROJECT
            + File.separator;


}
