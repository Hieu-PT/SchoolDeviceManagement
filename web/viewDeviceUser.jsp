<%-- 
    Document   : viewDeviceUser
    Created on : Mar 11, 2020, 10:45:33 PM
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
                <h2 style="font-size: 25px">Search</h2>
                <form action="MainController" method="POST">
                    Device Name:<input type="text" name="txtSearch" style="margin-left: 35px"/>
                    <input type="submit" value="Search Device" name="action" />
                </form>
                <c:if test="${requestScope.INFO != null}">
                    <c:if test="${not empty requestScope.INFO}" var="checkList">
                        <table border="1" style="margin: 30px;">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Device ID</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Type</th>
                                    <th>Room</th>
                                    <th>Image</th>
                                    <th>Date Of Purchase</th>
                                    <th>Warranty Period</th>
                                    <th>Status</th>
                                    <th>Fixed Times</th>
                                    <th>Feedback</th>
                                    <th>Position History</th>
                                    <th>Fixed History</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.INFO}" var="dto" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.deviceID}</td>
                                        <td>${dto.deviceName}</td>
                                        <td>${dto.deviceDescription}</td>
                                        <td>${dto.deviceType}</td>
                                        <td>${dto.room}</td>
                                        <td><img src="${dto.deviceImage}" style="width: 100px; height: 75px"</td>
                                        <td>${dto.dateOfPurchase}</td>
                                        <td>${dto.warrantyPeriod}</td>
                                        <td>${dto.deviceStatus}</td>
                                        <td>${dto.numberOfCorrections}</td>
                                        <td>
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                                <input type="hidden" name="id" value="${dto.deviceID}"/>
                                                <input type="submit" value="Feedback" name="action" />
                                            </form>
                                        </td>
                                        <td>
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="id" value="${dto.deviceID}"/>
                                                <input type="submit" value="Position History" name="action" />
                                            </form>
                                        </td>
                                        <td>
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="id" value="${dto.deviceID}"/>
                                                <input type="submit" value="Fixed History" name="action" />
                                            </form>
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
