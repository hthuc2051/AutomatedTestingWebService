package com.practicalexam;

import com.practicalexam.student.TemplateQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(TestResultLoggerExtension.class)

@SpringBootTest
@ExtendWith(TestResultLoggerExtension.class)
class JavaApplicationTests {

    public static String questionPointStr = "question1:5-question2:5";

    @Autowired
    private TemplateQuestion templateQuestion = new TemplateQuestion();


    //start
@Test 
public void testcase(){driver.findElement(By.id("name")).clear(); driver.findElement(By.id("name")).sendKeys("value");assertEquals("result42",templateQuestion.question1("value"));}@Test 
public void testcase(){driver.findElement(By.id("name")).clear(); driver.findElement(By.id("name")).sendKeys("value");assertEquals("result4214",templateQuestion.question2("value","1"));}
//end

}
