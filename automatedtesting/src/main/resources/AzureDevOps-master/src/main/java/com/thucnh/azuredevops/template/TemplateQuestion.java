package com.thucnh.azuredevops.template;

import com.thucnh.azuredevops.student.StudentWork;
import org.springframework.stereotype.Service;

@Service
public class TemplateQuestion {

    StudentWork studentWork = new StudentWork();

    public Integer question1(Integer a, Integer b) {

        return studentWork.computeQuestion1(a, b);
    }

    public Integer question2(Integer a, Integer b) {
        return studentWork.computeQuestion2(a, b);
    }

    public String question3(Integer a, Integer b) {
        return studentWork.computeQuestion3(a, b);
    }

    public String question4(Integer a, Integer b) {
        return studentWork.computeQuestion4(a, b);
    }
}
