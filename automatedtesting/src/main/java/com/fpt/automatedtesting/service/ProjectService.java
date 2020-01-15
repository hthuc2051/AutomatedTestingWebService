package com.fpt.automatedtesting.service;

import javax.servlet.http.HttpServletResponse;

public interface ProjectService {
    void downloadJavaConsoleApp(HttpServletResponse response, Integer scriptId);
}
