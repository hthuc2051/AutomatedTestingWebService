package server;

import com.practicalexam.student.TemplateQuestion;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = {SpringbootWithWebxmlApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@ExtendWith(TestResultLoggerExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestwebApplicationTests {
    public static String questionPointStr = "showAllUI:1";
    private TemplateQuestion templateQuestion = new TemplateQuestion();
    private static boolean isLogin;

    //start
@Test 
@Order(1) 
public void testcase(){if( driver != null ){if( !isLogin ){assertTrue( false );}else{driver.get( "http://localhost:8080/login.html" );driver.findElement(By.name( "txtUsername")).clear();driver.findElement(By.name( "txtUsername" )).sendKeys( "LoginSuccess" );driver.findElement(By.name( "txtPassword")).clear();driver.findElement(By.name( "txtPassword" )).sendKeys( "1" );driver.findElement(By.name( "btnAction" )).click();try{String html = driver.findElement(By.tagName("body")).getText();assertEquals( true , html.toLowerCase().contains("search page") && html.toLowerCase().contains("am01") );}catch( Exception e ){assertTrue( false );}}}else{assertTrue( false );}}
//end
}
