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
    public static String questionPointStr = "showAllDAO:1-showAllDAO:2";
    private TemplateQuestion templateQuestion = new TemplateQuestion();
    public static WebDriver driver;
    //public static InternetExplorerOptions options;
    public static ChromeOptions options;
    

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
public void showAllDAO(){if( !isLogin ){assertTrue( false );}else{assertEquals( Integer.valueOf("3") , TemplateQuestion.showAll(); );}}@Test 
@Order(1) 
public void showAllDAO(){if( !isLogin ){ assertTrue( false  ); }else{ assertEquals( Integer.valueOf("3") , TemplateQuestion.showAll() ); }}
//end
}
