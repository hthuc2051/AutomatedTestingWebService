/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.config;

import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Dell
 */

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sctx = sce.getServletContext();
ResourceBundle bundle = ResourceBundle.getBundle("config");
        sctx.setAttribute("CONFIG", bundle);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
