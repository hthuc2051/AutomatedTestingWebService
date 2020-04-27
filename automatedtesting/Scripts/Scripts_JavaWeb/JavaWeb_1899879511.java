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
    public static String questionPointStr = "checkWelcome:1";
    private TemplateQuestion templateQuestion = new TemplateQuestion();
    public static WebDriver driver;
    //public static InternetExplorerOptions options;
    public static ChromeOptions options;
    static boolean isLogin  = false;


    public TestwebApplicationTests() {
        //  System.setProperty("webdriver.ie.driver", "src/main/resources/static/IEDriverServer.exe");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/static/chromedriver2.exe");
        if (options == null) {
            options = new ChromeOptions();
            if (driver == null) {
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            }
        }
    }
    //start
@Test 
@Order(1) 
public void checkWelcome(){if( driver != null ){if( !isLogin ){assertTrue( false );}else{driver.get( "http://localhost:8080/login.html" );driver.findElement(By.name( "txtUsername")).clear();driver.findElement(By.name( "txtUsername" )).sendKeys( "LoginSuccess" );driver.findElement(By.name( "txtPassword")).clear();driver.findElement(By.name( "txtPassword" )).sendKeys( "1" );driver.findElement(By.name( "btnAction" )).click();try{String html = driver.findElement(By.tagName("body")).getText();assertEquals( true , html.toLowerCase().contains("loginsuccess") && html.toLowerCase().contains("1") );}catch( Exception e ){assertTrue( false );}}}else{assertTrue( false );}}
//end
}
