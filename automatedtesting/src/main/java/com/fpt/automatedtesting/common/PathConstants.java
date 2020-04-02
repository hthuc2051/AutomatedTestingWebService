package com.fpt.automatedtesting.common;

import java.io.File;

public class PathConstants {

    public static final String PROJECT_DIR = System.getProperty("user.dir");
    public static final String STATIC_DIR = "static/";


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

    // Language in search api
    public static final String LANGUAGE_JAVA="JAVA";
    public static final String LANGUAGE_CS="CS";
    public static final String LANGUAGE_C="C";

    // Import name
    public static final String IMPORT_JAVA="import";
    public static final String IMPORT_CS="using";
    public static final String IMPORT_C="#include";

    // Log path
    public static final String GITHUB_LOG_PATH = STATIC_DIR + "Github_Log.txt";


}
