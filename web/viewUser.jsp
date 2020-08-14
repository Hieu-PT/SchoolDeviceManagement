<%-- 
    Document   : viewUser
    Created on : Mar 10, 2020, 11:03:30 AM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%@include file="menuAdmin.jsp" %>
        <div class="main">
            <section class="col-main" style="min-height: 625px">
                <h2 style="font-size: 25px">Search</h2>
                <form action="MainController" method="POST">
                    Fullname:<input type="text" name="txtSearch" style="margin-left: 35px"/>
                    <input type="submit" value="Search User" name="action" />
                </form>
                <c:if test="${requestScope.INFO != null}">
                    <c:if test="${not empty requestScope.INFO}" var="checkList">
                        <table border="1" style="margin: 30px;">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Username</th>
                                    <th>Fullname</th>
                                    <th>Role</th>
                                    <th>Working Room</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.INFO}" var="dto" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${dto.username}</td>
                                    <td>${dto.fullname}</td>
                                    <td>${dto.role}</td>
                                    <td>${dto.workingroom}</td>
                                    <td>
                                        <form action="MainController" method="POST">
                                            <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                            <input type="hidden" name="id" value="${dto.username}"/>
                                            <input type="submit" value="Edit User" name="action" />
                                        </form>
                                    </td>
                                    <td>
                                <c:url var="deleteLink" value="MainController">
                                    <c:param name="action" value="DeleteUser"/>
                                    <c:param name="id" value="${dto.username}"/>
                                    <c:param name="txtSearch" value="${param.txtSearch}"/>
                                </c:url>
                                <a href="${deleteLink}">Delete</a>
                                </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </c:if>
                    <c:if test="${!checkList}">
                        No record found
                    </c:if>
                </c:if>
            </section>
            <%@include file="aside.jsp" %>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
