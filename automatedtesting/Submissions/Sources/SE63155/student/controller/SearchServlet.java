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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author T.Z.B
 */
public class SearchServlet extends HttpServlet {

    private final String INVALID = "invalid.jsp";
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
        String url = INVALID;
        try {
            String from = request.getParameter("txtFrom");
            String to = request.getParameter("txtTo");
            String doctorId = request.getParameter("txtId");
            if (from == null || to == null || doctorId == null || from.length() <= 0 || to.length() <= 0 || doctorId.length() <= 0) {
                url = SEARCH_PAGE;
            } else {
                Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(from);
                Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(to);
                DoctorDAO dao = new DoctorDAO();
                int result = dao.searchDoctorSchedule(fromDate, toDate, doctorId);
                if (result >= 0) {
                    List<DoctorDTO> listSearch = dao.getListDoctorsSearch();
                    request.setAttribute("LIST_DOCTORS", listSearch);
                    url = SEARCH_PAGE;
                }
            }
        } catch (ParseException ex) {
            log("SearchServlet _ ParseException _ " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("SearchServlet _ ClassNotFoundException _ " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchServlet _ NamingException _ " + ex.getMessage());
        } catch (SQLException ex) {
            log("SearchServlet _ SQLException _ " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
