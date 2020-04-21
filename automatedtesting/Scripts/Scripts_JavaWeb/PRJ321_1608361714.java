package com.thucnh.azuredevops;

import com.thucnh.azuredevops.template.TemplateQuestion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ScripTestJava {
    public static String questionPointStr = "script_findAreaOfRectangle:1-script_checkBubbleSort:2";
    @Autowired
    private TemplateQuestion templateQuestion;

    //start
@Test 
@Order(1) 
public void script_findAreaOfRectangle(){driver.findElement(By.id($paramName)).clear(); driver.findElement(By.id($paramName)).sendKeys($paramValue);}@Test 
@Order(2) 
public void script_checkBubbleSort(){driver.findElement(By.id($paramName)).clear(); driver.findElement(By.id($paramName)).sendKeys($paramValue);}
//end
}
