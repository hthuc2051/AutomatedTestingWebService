<%-- 
    Document   : search
    Created on : Mar 24, 2020, 8:22:50 AM
    Author     : Dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.FULLNAME}
        </font>
        <br/>
        <h1>Search Page</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>Number</th>
                    <th>Description</th>
                    <th>Classification</th>
                    <th>Defense</th>
                    <th>Time of create</th>
                    <th>Status</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dbo" items="${requestScope.LISTWEAPON}" varStatus="counter">
                    <tr>
                        <th>
                            ${counter.count}
                        </th>
                        <th>
                            ${dbo.description}
                        </th>
                        <th>
                            ${dbo.classification}
                        </th>
                        <th>
                            ${dbo.defense}
                        </th>
                        <th>
                            ${dbo.timeOfCreate}
                        </th>
                        <th>
                            <input type="checkbox" name="chkStatus" value="ON" 
                                   <c:if test="${dbo.status}">checked="checked"</c:if>
                                   />
                        </th>
                        <th>
                            <c:url var="deleteLink" value="delete">
                                <c:param name="txtSearch" value="${param.txtSearch}"></c:param>
                                <c:param name="idDelete" value="${dbo.amourId}"></c:param>
                                <c:param name="btAction" value="Delete"></c:param>
                            </c:url>
                            <a href="${deleteLink}">Delete</a>
                        </th>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


    </body>
</html>
