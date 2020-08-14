<%-- 
    Document   : feedback
    Created on : Mar 12, 2020, 11:26:29 AM
    Author     : ADMIN
--%>

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
                <h1 style=" font-size: 35px; margin: 40px;">Update Page</h1>
                <form action="MainController" method="POST">
                    Device ID: <input type="text" name="txtDeviceID" readonly="true" value="${requestScope.DTO.deviceID}" style="margin: 20px 20px 20px 30px; "/>
                    <br/>
                    Sender: <input type="text" name="txtSender" readonly="true" value="${sessionScope.USER}" style="margin: 20px 20px 20px 45px; "/>
                    <br/>
                    Content: <input type="text" name="txtRequiredContent" style="margin: 20px 20px 20px 40px; width: 350px;"/>
                    <font color="red">
                    ${requestScope.INVALID.requiredContentError}
                    </font>
                    <br/>
                    <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                    <input type="submit" name="action" value="Send Feedback" style="margin:20px 20px 20px 150px"/>
                </form>
            </section>
            <%@include file="aside.jsp" %>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
