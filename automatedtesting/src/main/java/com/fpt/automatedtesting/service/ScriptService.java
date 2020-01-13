package com.fpt.automatedtesting.service;

import com.fpt.automatedtesting.dto.request.ScriptRequestDto;
import com.fpt.automatedtesting.dto.request.TestScriptParamDto;
import com.fpt.automatedtesting.dto.response.ScriptResponseDto;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.List;

public interface ScriptService {
    List<ScriptResponseDto> getAll();
    Boolean generateScriptTest(TestScriptParamDto dto);
    void downloadFile(HttpServletResponse response);
}
