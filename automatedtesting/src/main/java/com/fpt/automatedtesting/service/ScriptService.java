package com.fpt.automatedtesting.service;

import com.fpt.automatedtesting.dto.request.TestScriptParamDto;

import javax.servlet.http.HttpServletResponse;

public interface ScriptService {
    Boolean generateScriptTest(TestScriptParamDto dto);
    Boolean downloadFile(HttpServletResponse response);
}
