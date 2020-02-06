package com.fpt.automatedtesting.service;

import com.fpt.automatedtesting.dto.request.PracticalExamRequest;

import javax.servlet.http.HttpServletResponse;

public interface PracticalExamService {

    void create(PracticalExamRequest dto);
    void downloadPracticalTemplate(HttpServletResponse response);
}
