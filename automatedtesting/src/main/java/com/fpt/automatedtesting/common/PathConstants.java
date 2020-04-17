package com.fpt.automatedtesting.common;

import java.io.File;

public class PathConstants {

    public static final String PROJECT_DIR = System.getProperty("user.dir");
    public static final String STATIC_DIR = "static/";
    // Log path
    public static final String PATH_EXCEPTIONS = PROJECT_DIR + File.separator + "Exceptions";

    public static final String PATH_SUBMISSIONS = PROJECT_DIR + File.separator + "Submissions";
    public static final String PATH_SERVER_REPOSITORY = PROJECT_DIR + File.separator + "ServerRepository";

    // Path server
    public static final String PATH_SERVER_JAVA_WEB = PROJECT_DIR + File.separator + "Server_JavaWeb" + File.separator + "Server";
    public static final String PATH_SERVER_JAVA = PROJECT_DIR + File.separator + "Server_Java" + File.separator + "Server";
    public static final String PATH_SERVER_C = PROJECT_DIR + File.separator + "Server_C" + File.separator + "Server";
    public static final String PATH_SERVER_C_SHARP = PROJECT_DIR + File.separator + "Server_CSharp" + File.separator + "Server";

    // Path scripts
    public static final String PATH_SCRIPT_JAVA_WEB = PROJECT_DIR + File.separator + "Scripts_JavaWeb" + File.separator;
    public static final String PATH_SCRIPT_JAVA = PROJECT_DIR + File.separator + "Scripts_Java" + File.separator;
    public static final String PATH_SCRIPT_C = PROJECT_DIR + File.separator + "Scripts_C" + File.separator;
    public static final String PATH_SCRIPT_C_SHARP = PROJECT_DIR + File.separator + "Scripts_CSharp" + File.separator;

    // Path docs
    public static final String PATH_DOCS_JAVA_WEB = PROJECT_DIR + File.separator + "Docs_JavaWeb" + File.separator;
    public static final String PATH_DOCS_JAVA = PROJECT_DIR + File.separator + "Docs_Java" + File.separator;
    public static final String PATH_DOCS_C = PROJECT_DIR + File.separator + "Docs_C" + File.separator;
    public static final String PATH_DOCS_C_SHARP = PROJECT_DIR + File.separator + "Docs_CSharp" + File.separator;

    // Path TEMPLATE QUESTION
    public static final String PATH_TEMPLATE_QUESTION_JAVA_WEB = PROJECT_DIR + File.separator + "Docs_JavaWeb" + File.separator;
    public static final String PATH_TEMPLATE_QUESTION_JAVA = PROJECT_DIR + File.separator + "Docs_Java" + File.separator;
    public static final String PATH_TEMPLATE_QUESTION_C = PROJECT_DIR + File.separator + "Docs_C" + File.separator;
    public static final String PATH_TEMPLATE_QUESTION_C_SHARP = PROJECT_DIR + File.separator + "Docs_CSharp" + File.separator;

    // Path TEMPLATE QUESTION
    public static final String PATH_DATABASE_SCRIPT_JAVA_WEB = PROJECT_DIR + File.separator + "Docs_JavaWeb" + File.separator;
    public static final String PATH_DATABASE_SCRIPT_JAVA = PROJECT_DIR + File.separator + "Docs_Java" + File.separator;
    public static final String PATH_DATABASE_SCRIPT_C = PROJECT_DIR + File.separator + "Docs_C" + File.separator;
    public static final String PATH_DATABASE_SCRIPT_C_SHARP = PROJECT_DIR + File.separator + "Docs_CSharp" + File.separator;

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

    public static final String PATH_TEMPLATE_C = STATIC_DIR + File.separator + "Template_C";
    public static final String PATH_TEMPLATE_C_SHARP = STATIC_DIR
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


}
