package com.thucnh.azuredevops;

import com.thucnh.azuredevops.template.TemplateQuestion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ScripTestJava {
    public static String questionPointStr = "testcase:10";
    @Autowired
    private TemplateQuestion templateQuestion;

    //start
@Test 
@Order(12) 
public void testcase(){driver.findElement(By.id( "txtUsername" )).sendKeys( "t01" );driver.findElement(By.id( "txtPassword" )).sendKeys( "t02" );driver.findElement(By.id( "txtConfirm" )).sendKeys( "t03" );}
//end
}
