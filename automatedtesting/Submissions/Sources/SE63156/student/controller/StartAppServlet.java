/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.controller;

import com.practicalexam.student.tblDoctorDAO.DoctorDAO;
import com.practicalexam.student.tblDoctorDTO.DoctorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Le Ngoc Tan
 */
public class StartAppServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String SEARCH_PAGE = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                Cookie lastcookies = cookies[cookies.length - 1];
                String username = lastcookies.getName();
                String password = lastcookies.getValue();
                DoctorDAO dao = new DoctorDAO();
                boolean result = dao.checkLogin(username, password);
                String fullname = dao.getFullnameFromDoctorId(username);
                if (result) {
                    HttpSession session = request.getSession();
                    session.setAttribute("FULLNAME", fullname);
                    dao.getAllDoctorsWithSchedules();
                    List<DoctorDTO> listDoctors = dao.getListDoctors();
                    session.setAttribute("LIST_DOCTORS", listDoctors);
                    url = SEARCH_PAGE;
                }
            }
        } catch (ClassNotFoundException ex) {
            log("StartAppServlet _ ClassNotFound _ " + ex.getMessage());
        } catch (NamingException ex) {
            log("StartAppSerlvet _ Naming _ " + ex.getMessage());
        } catch (SQLException ex) {
            log("StartAppServlet _ SQL _ " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
