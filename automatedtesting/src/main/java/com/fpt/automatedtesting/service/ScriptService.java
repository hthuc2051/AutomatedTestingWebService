package com.fpt.automatedtesting.service;

import com.fpt.automatedtesting.dto.request.ScriptRequestDto;
import com.fpt.automatedtesting.dto.response.ScriptResponseDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ScriptService {
    List<ScriptResponseDto> getAll();
    Boolean generateScriptTest(ScriptRequestDto dto);
    void downloadFile(HttpServletResponse response);
    Boolean deleteScript(Integer scriptId);
    Boolean updateScriptTest(ScriptRequestDto dto);
}
