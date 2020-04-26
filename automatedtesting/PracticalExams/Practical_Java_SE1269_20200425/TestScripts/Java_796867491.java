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

    public static String questionPointStr = "checkCreate:1.5-checkUpdate:1.5-checkSearch:1.5-checkDelete:1.5";

    private TemplateQuestion templateQuestion = new TemplateQuestion();


    //start
@Test 
@Order(1) 
public void checkCreate(){String[] data = new String[]{"codeAdd","modelAdd","10","1000.0"};String start = "==========Start Insert==========";String end = "==========End Insert==========";System.out.println("==========Start Insert==========");templateQuestion.insert();System.out.println("==========End Insert==========");assertEquals(true, CheckingUtils.checkConsoleLogContains(data, end, start));}@Test 
@Order(2) 
public void checkUpdate(){String[] data = new String[]{"codeAdd","modelUpdate","11","1001"};String start = "==========Start Update==========";String end = "==========End Update==========";System.out.println("==========Start Update==========");templateQuestion.update();System.out.println("==========End Update==========");assertEquals(true, CheckingUtils.checkConsoleLogContains(data, end, start));}@Test 
@Order(3) 
public void checkSearch(){String[] data = new String[]{"codeAdd","modelAdd","10","1000"};String start = "==========Start Search==========";String end = "==========End Search==========";System.out.println("==========Start Search==========");templateQuestion.search();System.out.println("==========End Search==========");assertEquals(true, CheckingUtils.checkConsoleLogContains(data, end, start));}@Test 
@Order(4) 
public void checkDelete(){String[] data = new String[]{"codeAdd","modelAdd","10","1000"};String start = "==========Start Remove==========";String end = "==========End Remove==========";System.out.println("==========Start Remove==========");templateQuestion.remove();System.out.println("==========End Remove==========");assertEquals(false, CheckingUtils.checkConsoleLogContains(data, end, start));}
//end

}
