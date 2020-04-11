package com.thucnh.azuredevops;

import com.thucnh.azuredevops.template.TemplateQuestion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ScripTestJava {
    checkLoginS:3
    @Autowired
    private TemplateQuestion templateQuestion;

    //start
@Test 
public void checkLoginS(){if( !isLogin ){ assertTrue( false ); }else{ if( driver != null ){ driver.get( "http://localhost:8080/login.html" );driver.findElement(By.name( "txtUsername" )).clear();driver.findElement(By.name( "txtUsername" )).sendKeys( "t01" );driver.findElement(By.name( "txtPassword" )).clear();driver.findElement(By.name( "txtPassword" )).sendKeys( "t01" );try{ String html = driver.findElement(By.tagName("body")).getText();assertEquals( true, html.toLowerCase().contains("search page")); }catch( Exception e ){ assertTrue( false ); } } }}
//end
}
