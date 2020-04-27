package com.practicalexam;

import com.practicalexam.student.TemplateQuestion;
import com.practicalexam.utils.CheckingUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(TestResultLoggerExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JavaApplicationTests {

    public static String questionPointStr = "questionPointStrValue";
    //GLOBAL_VARIABLE
    private TemplateQuestion templateQuestion = new TemplateQuestion();


    //start

    //end

}
