<%-- 
    Document   : viewFeedback
    Created on : Mar 14, 2020, 2:41:28 PM
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
        <%@include file="menuStaff.jsp" %>
        <div class="main">
            <section class="col-main" style="min-height: 625px">
                <h2 style="font-size: 35px">Feedback Waiting For Response</h2>
                <c:if test="${requestScope.INFO != null}">
                    <c:if test="${not empty requestScope.INFO}" var="checkList">
                        <table border="1" style="margin: 30px;">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Device ID</th>
                                    <th>Sender</th>
                                    <th>Request</th>
                                    <th>Time</th>
                                    <th>Repairer</th>
                                    <th>Response</th>
                                    <th>Result</th>
                                    <th>Accept</th>
                                    <th>Finish</th>
                                    <th>Deny</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.INFO}" var="dto" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${dto.deviceID}</td>
                                    <td>${dto.sender}</td>
                                    <td>${dto.requiredContent}</td>
                                    <td>${dto.time}</td>
                                    <td>${dto.repairer}</td>
                                    <td>${dto.repairContent}</td>
                                    <td>${dto.result}</td>
                                    <td>
                                        <form action="MainController" method="POST">
                                            <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                            <input type="hidden" name="id" value="${dto.feedbackID}"/>
                                            <input type="hidden" name="sender" value="${dto.sender}"/>
                                            <input type="submit" value="Accept" name="action" />
                                        </form>
                                    </td>
                                    <td>
                                        <form action="MainController" method="POST">
                                            <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                            <input type="hidden" name="id" value="${dto.feedbackID}"/>
                                            <input type="hidden" name="sender" value="${dto.sender}"/>
                                            <input type="submit" value="Finish" name="action"/>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="MainController" method="POST">
                                            <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                            <input type="hidden" name="id" value="${dto.feedbackID}"/>
                                            <input type="hidden" name="sender" value="${dto.sender}"/>
                                            <input type="submit" value="Reject" name="action"/>
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
