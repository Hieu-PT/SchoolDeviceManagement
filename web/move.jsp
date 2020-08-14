<%-- 
    Document   : move
    Created on : Mar 12, 2020, 10:26:03 PM
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
                <h1 style=" font-size: 35px; margin: 40px;">Move Device</h1>
                <form action="MainController" method="POST">
                    Device ID: <input type="text" name="txtDeviceID" readonly="true" value="${requestScope.DTO.deviceID}" style="margin: 20px 20px 20px 40px; "/>
                    <br/>
                    Device Name: <input type="text" name="txtDeviceName" readonly="true" value="${requestScope.DTO.deviceName}" style="margin: 20px 20px 20px 17px; "/>
                    <br/>
                    Room: <input type="text" name="txtRoom" readonly="true" value="${requestScope.DTO.room}" style="margin: 20px 20px 20px 63px; "/>
                    <br/>
                    Image: <img src="${requestScope.DTO.deviceImage}" style="width: 100px; height: 75px; margin: 20px 20px 20px 60px">
                    <br/>
                    User Move: <input type="text" name="txtUserMove" readonly="true" value="${sessionScope.FULLNAME}" style="margin: 20px 20px 20px 32px;"/>
                    <br/>
                    Move To: <input type="text" name="txtMoveTo" style="margin: 20px 20px 20px 45px"/>
                    <font color="red">
                    ${requestScope.INVALID.moveToError}
                    </font>
                    <br/>
                    Reason: <input type="text" name="txtReason" style="margin: 20px 20px 20px 48px"/>
                    <font color="red">
                    ${requestScope.INVALID.reasonError}
                    </font>
                    <br/>
                    <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                    <input type="submit" name="action" value="Move Device" style="margin:20px 20px 20px 150px"/>
                </form>
            </section>
            <%@include file="aside.jsp" %>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
