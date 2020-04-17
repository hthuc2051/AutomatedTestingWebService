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
public void testcase(){driver.findElement(By.id("name")).clear(); driver.findElement(By.id("name")).sendKeys("value");assertEquals("5",templateQuestion.question1("5"));}@Test 
public void testcase(){driver.findElement(By.id("name")).clear(); driver.findElement(By.id("name")).sendKeys("value");assertEquals("result",templateQuestion.question2("value1"));}
//end

}
