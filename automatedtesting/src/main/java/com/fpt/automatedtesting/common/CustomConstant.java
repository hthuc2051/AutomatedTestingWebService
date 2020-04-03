package com.fpt.automatedtesting.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomConstant {

    // Script type
    public static final String TEMPLATE_TYPE_JAVA = "Java";
    public static final String TEMPLATE_TYPE_JAVA_WEB = "JavaWeb";
    public static final String TEMPLATE_TYPE_C = "C";
    public static final String TEMPLATE_TYPE_CSHARP = "CSharp";
    public static final String PRACTICAL_INFO_FILE_NAME = "practical-info.json";

    public static final String COLUMN_NO = "No";
    public static final String COLUMN_STUDENT_CODE = "Student Code";
    public static final String COLUMN_STUDENT_NAME = "Student Name";
    public static final String COLUMN_SCRIPT_CODE = "Script Code";
    public static final String COLUMN_SUBMITTED_TIME = "Submitted Time";
    public static final String COLUMN_EVALUATED_TIME = "Evaluated Time";
    public static final String COLUMN_CODING_CONVENTION = "Coding Convention";
    public static final String COLUMN_RESULT = "Result";
    public static final String COLUMN_TOTAL_POINT = "Total point";
    public static final String COLUMN_ERROR = "Error";

    public static final String STATE_NOT_EVALUATE = "NOT_EVALUATE";
    public static final String STATE_EVALUATE = "EVALUATE";
    public static final String STATE_ERROR = "ERROR";

    public static final String HEAD_LECTURER = "HEAD LECTURER";
    public static final String LECTURER = "LECTURER";

    public static final int JAVA = 0;
    public static final int JAVA_WEB = 1;
    public static final int C = 2;
    public static final int CSHARP = 3;

    // Language in search api
    public static final String LANGUAGE_JAVA = "JAVA";
    public static final String LANGUAGE_CS = "CS";
    public static final String LANGUAGE_C = "C";

    // Import name
    public static final String IMPORT_JAVA = "import";
    public static final String IMPORT_CS = "using";
    public static final String IMPORT_C = "#include";

    public static final String DELETE_SCRIPT_SUCCESS = "Delete Successfully";
    public static final String DELETE_SCRIPT_FAIL = "Delete Failed";


}
