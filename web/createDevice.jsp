<%-- 
    Document   : createDevice
    Created on : Mar 10, 2020, 8:00:49 PM
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
        <%@include file="menuAdmin.jsp" %>
        <div class="main">
            <section class="col-main" style="min-height: 625px">
                <h2 style=" font-size: 35px; margin: 40px;">Create New Device</h2>
                <form action="MainController" method="POST">
                    Device ID: <input type="text" name="txtDeviceID" style="margin: 20px 20px 20px 70px;" value="${param.txtDeviceID}"/>
                    <font color="red">
                    ${requestScope.INVALID.deviceIDError}
                    </font>
                    <br/>
                    Device Name: <input type="text" name="txtDeviceName" style="margin: 20px 20px 20px 46px;" value="${param.txtDeviceName}"/>
                    <font color="red">
                    ${requestScope.INVALID.deviceNameError}
                    </font>
                    <br/>
                    Device Description: <input type="text" name="txtDeviceDescription" style="margin: 20px 20px 20px 13px;" value="${param.txtDeviceDescription}"/>
                    <font color="red">
                    ${requestScope.INVALID.deviceDescriptionError}
                    </font>
                    <br/>
                    Device Type: <input type="text" name="txtDeviceType" style="margin: 20px 20px 20px 52px;" value="${param.txtDeviceType}"/>
                    <font color="red">
                    ${requestScope.INVALID.deviceTypeError}
                    </font>
                    <br/>
                    Room: <input type="text" name="txtRoom" style="margin: 20px 20px 20px 92px;" value="${param.txtRoom}"/>
                    <font color="red">
                    ${requestScope.INVALID.roomError}
                    </font>
                    <br/>
                    Device Image Link: <input type="text" name="txtDeviceImage" style="margin: 20px 20px 20px 13px;" value="${param.txtDeviceImage}"/>
                    <font color="red">
                    ${requestScope.INVALID.deviceImageError}
                    </font>
                    <br/>
                    Date of purchase: <input type="text" name="txtDateOfPurchase" style="margin: 20px 20px 20px 21px;" value="${param.txtDateOfPurchase}"/>
                    <font color="red">
                    ${requestScope.INVALID.dateOfPurchaseError}
                    </font>
                    <br/>
                    Warranty Period: <input type="text" name="txtWarrantyPeriod" style="margin: 20px 20px 20px 26px;" value="${param.txtWarrantyPeriod}"/>
                    <font color="red">
                    ${requestScope.INVALID.warrantyPeriodError}
                    </font>
                    <br/>
                    Device Status: <input type="text" name="txtDeviceStatus" style="margin: 20px 20px 20px 40px;" value="${param.txtDeviceStatus}"/>
                    <font color="red">
                    ${requestScope.INVALID.deviceStatusError}
                    </font>
                    <br/>
                    <input type="submit" value="Create Device" name="action" style="margin:20px 20px 30px 170px"/>
                </form>
            </section>
            <%@include file="aside.jsp" %>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
