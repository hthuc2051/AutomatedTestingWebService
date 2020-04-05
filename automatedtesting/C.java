/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thucnh.student.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thucnh.dtos.BookDTO;
import thucnh.dtos.BookErrObj;
import thucnh.models.BookDAO;

/**
 *
 * @author USER
 */
public class InsertController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String INVALID = "insert.jsp";
    private static final String SUCCESS = "LoadDataController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String bookID = request.getParameter("txtBookID");
            String bookTitle = request.getParameter("txtBookTitle");
            String description = request.getParameter("txtDescription");
            String author = request.getParameter("txtAuthor");
            String publisher = request.getParameter("txtPublisher");
            String price = request.getParameter("txtPrice");
            BookErrObj errObj = new BookErrObj();
            boolean check = true;
            if (bookID.length() < 1 || bookID.length() > 5) {
                check = false;
                errObj.setBookIDErr("BookID must has [1-5] characters");
            }
            if (bookTitle.length() < 1 || bookID.length() > 50) {
                check = false;
                errObj.setTitleErr("Book title must has [1-50] characters");
            }
            if (description.length() < 1 || description.length() > 250) {
                check = false;
                errObj.setDescriptionErr("Description must has [1-250] characters");
            }
            if (author.length() < 1 || author.length() > 50) {
                check = false;
                errObj.setAuthorErr("Author must has [1-50] characters");
            }
            if (publisher.length() < 1 || publisher.length() > 50) {
                check = false;
                errObj.setPublisherErr("Publisher must has [1-50] characters");
            }
            if (!price.matches("\\d+(\\.\\d+)?")) {
                check = false;
                errObj.setPriceErr("Price must be a number >= 0");
            }
            if (check) {
                BookDTO dto = new BookDTO(bookID, bookTitle, description, author, publisher, price);
                BookDAO dao = new BookDAO();
                if (dao.insert(dto)) {
                    request.setAttribute("STATUS", "Insert successfully");
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Insert failed");
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errObj);
            }
        } catch (Exception e) {
            if (e.getMessage().contains("duplicate")) {
                BookErrObj errorObj = new BookErrObj();
                errorObj.setBookIDErr("BookID is existed");
                request.setAttribute("INVALID", errorObj);
                url = INVALID;
            }
            log("Error at Insercontroller " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
