<%-- 
    Document   : positionHistoryUser
    Created on : Mar 11, 2020, 11:00:25 PM
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
        <%@include file="menuUser.jsp" %>
        <div class="main">
            <section class="col-main" style="min-height: 625px">
                <h2 style="font-size: 35px">Position History</h2>
                <c:if test="${requestScope.INFO != null}">
                    <c:if test="${not empty requestScope.INFO}" var="checkList">
                        <table border="1" style="margin: 30px;">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>DeviceID</th>
                                    <th>Room</th>
                                    <th>From</th>
                                    <th>To</th>
                                    <th>User Move</th>
                                    <th>Reason Move</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.INFO}" var="dto" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${dto.deviceID}</td>
                                    <td>${dto.roomNo}</td>
                                    <td>${dto.from}</td>
                                    <td>${dto.to}</td>
                                    <td>${dto.userMove}</td>
                                    <td>${dto.reasonMove}</td>
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
