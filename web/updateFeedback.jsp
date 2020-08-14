<%-- 
    Document   : updateFeedback
    Created on : Mar 14, 2020, 4:32:34 PM
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
        <%@include file="menuStaff.jsp" %>
        <div class="main">
            <section class="col-main" style="min-height: 625px">
                <h1 style=" font-size: 35px; margin: 40px;">Update Page</h1>
                <form action="MainController" method="POST">
                    Device ID: <input type="text" name="txtDeviceID" readonly="true" value="${requestScope.DTO.deviceID}" style="margin: 20px 20px 20px 30px; "/>
                    <br/>
                    Sender: <input type="text" name="txtSender" readonly="true" value="${requestScope.DTO.sender}" style="margin: 20px 20px 20px 45px; "/>
                    <br/>
                    Request: <input type="text" name="txtRequest" readonly="true" value="${requestScope.DTO.requiredContent}" style="margin: 20px 20px 20px 37px; "/>
                    <br/>
                    Time: <input type="text" name="txtTime" readonly="true" value="${requestScope.DTO.time}" style="margin: 20px 20px 20px 58px; "/>
                    <br/>
                    Status: <input type="text" name="txtStatus" readonly="true" value="${requestScope.DTO.result}" style="margin: 20px 20px 20px 48px; "/>
                    <br/>
                    Response: <input type="text" name="txtResponse" style="margin: 20px 20px 20px 22px; width: 350px"/>
                    <br/>
                    <input type="hidden" name="id" value="${requestScope.DTO.feedbackID}"/>
                    <input type="hidden" name="txtRepairFrom" value="${requestScope.DTO.repairFrom}"/>
                    <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                    <input type="submit" name="action" value="Send" style="margin:20px 20px 20px 150px"/>
                </form>
            </section>
            <%@include file="aside.jsp" %>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
