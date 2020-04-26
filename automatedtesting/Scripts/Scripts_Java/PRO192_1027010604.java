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

    public static String questionPointStr = "1";

    @Autowired
    private TemplateQuestion templateQuestion = new TemplateQuestion();


    //start
@Test 
@Order(1) 
public void testcase(){driver.findElement(By.id($paramName)).clear(); driver.findElement(By.id($paramName)).sendKeys($paramValue);}
//end

}
