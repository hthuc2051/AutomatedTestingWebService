package com.fpt.automatedtesting.scripts;

import com.fpt.automatedtesting.scripts.dtos.ScriptRequestDto;
import com.fpt.automatedtesting.scripts.dtos.ScriptResponseDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ScriptService {
    List<ScriptResponseDto> getAll();
    Boolean generateScriptTest(ScriptRequestDto dto);
    void downloadFile(HttpServletResponse response);
    Boolean deleteScript(Integer scriptId);
    Boolean updateScriptTest(ScriptRequestDto dto);
}
