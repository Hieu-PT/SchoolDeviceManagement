<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<body>
    <div class="main">
        <section class="col-main" style="min-height: 625px">
            <h1 style="font-size: 25px">Notification center</h1>
            <div class="float-left">
                <ul>
                    <c:if test="${requestScope.INFO != null}">
                        <c:if test="${not empty requestScope.INFO}" var="checkList">
                            <c:forEach items="${requestScope.INFO}" var="dto">
                                <li>${dto.content}</li>
                            </c:forEach>
                        </c:if>
                        <c:if test="${!checkList}">
                            No record found
                        </c:if>
                    </c:if>

                </ul>
            </div>

        </section>
