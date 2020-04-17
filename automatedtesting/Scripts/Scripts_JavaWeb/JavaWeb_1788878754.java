package com.thucnh.azuredevops;

import com.thucnh.azuredevops.template.TemplateQuestion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ScripTestJava {
    testcase:12
    @Autowired
    private TemplateQuestion templateQuestion;

    //start
@Test 
public void testcase(){driver.findElement(By.id($paramName)).clear(); driver.findElement(By.id($paramName)).sendKeys($paramValue);}
//end
}
