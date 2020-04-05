package com.fpt.automatedtesting.scripts;

import com.fpt.automatedtesting.scripts.dtos.ScriptRequestDto;
import com.fpt.automatedtesting.scripts.dtos.ScriptResponseDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ScriptService {
    List<ScriptResponseDto> getAll();
    List<ScriptResponseDto> getScriptTestBySubjectId(Integer subjectId);
    String generateScriptTest(ScriptRequestDto dto);
    void downloadFile(HttpServletResponse response);
    String deleteScript(Integer scriptId);
    String updateScriptTest(ScriptRequestDto dto);
    ScriptResponseDto getScriptTestByScriptId(Integer scriptId);
}
