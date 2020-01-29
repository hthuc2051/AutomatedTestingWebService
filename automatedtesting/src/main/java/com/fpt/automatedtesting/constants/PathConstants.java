package com.fpt.automatedtesting.constants;

import java.io.File;

public class PathConstants {

    public static final String PROJECT_DIR = System.getProperty("user.dir");
    public static final String STATIC_DIR = "static/";

    // Path server
    public static final String PATH_SERVER_JAVA_WEB = PROJECT_DIR + File.separator + "Server_JavaWeb";
    public static final String PATH_SERVER_JAVA = PROJECT_DIR + File.separator + "Server_Java";
    public static final String PATH_SERVER_C = PROJECT_DIR + File.separator + "Server_C";
    public static final String PATH_SERVER_C_SHARP = PROJECT_DIR + File.separator + "Server_CSharp";

    // Path scripts
    public static final String PATH_SCRIPT_JAVA_WEB = PROJECT_DIR + File.separator + "Scripts_JavaWeb" + File.separator;
    public static final String PATH_SCRIPT_JAVA = PROJECT_DIR + File.separator + "Scripts_Java" + File.separator;
    public static final String PATH_SCRIPT_C = PROJECT_DIR + File.separator + "Scripts_C" + File.separator;
    public static final String PATH_SCRIPT_C_SHARP = PROJECT_DIR + File.separator + "Scripts_CSharp" + File.separator;

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
    public static final String PATH_TEMPLATE_C_SHARP = STATIC_DIR + File.separator + "Template_CSharp";

}
