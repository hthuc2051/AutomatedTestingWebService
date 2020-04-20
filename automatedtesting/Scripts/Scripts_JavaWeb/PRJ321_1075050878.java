package com.thucnh.azuredevops;

import com.thucnh.azuredevops.template.TemplateQuestion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ScripTestJava {
    public static String questionPointStr = "testcase:1";
    @Autowired
    private TemplateQuestion templateQuestion;

    //start
@Test 
@Order(1) 
public void testcase(){driver.findElement(By.id( "a" )).sendKeys( "a" );if( a ){ driver.get( "http" ); }else{  }}
//end
}
