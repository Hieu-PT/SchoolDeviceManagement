<%-- 
    Document   : updateDevice
    Created on : Mar 11, 2020, 7:12:38 PM
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
                <h2 style=" font-size: 35px; margin: 40px;">Update Page</h2>
                <form action="MainController" method="POST">
                    Device ID: <input type="text" name="txtDeviceID" value="${requestScope.DTO.deviceID}" readonly="true" style="margin: 20px 20px 20px 70px;"/>
                    <br/>
                    Device Name: <input type="text" name="txtDeviceName" value="${requestScope.DTO.deviceName}" style="margin: 20px 20px 20px 46px;"/>
                    <font color="red">
                    ${requestScope.INVALID.deviceNameError}
                    </font>
                    <br/>
                    Device Description: <input type="text" name="txtDeviceDescription" style="margin: 20px 20px 20px 13px;" value="${requestScope.DTO.deviceDescription}" />
                    <font color="red">
                    ${requestScope.INVALID.deviceDescriptionError}
                    </font>
                    <br/>
                    Device Type: <input type="text" name="txtDeviceType" style="margin: 20px 20px 20px 52px;" value="${requestScope.DTO.deviceType}" />
                    <font color="red">
                    ${requestScope.INVALID.deviceTypeError}
                    </font>
                    <br/>
                    Room: <input type="text" name="txtRoom" style="margin: 20px 20px 20px 92px;" readonly="true" value="${requestScope.DTO.room}" />
                    <br/>
                    Device Image Link: <input type="text" name="txtDeviceImage" style="margin: 20px 20px 20px 13px;" value="${requestScope.DTO.deviceImage}" />
                    <font color="red">
                    ${requestScope.INVALID.deviceImageError}
                    </font>
                    <br/>
                    Date of purchase: <input type="text" name="txtDateOfPurchase" style="margin: 20px 20px 20px 21px;" value="${requestScope.DTO.dateOfPurchase}" />
                    <font color="red">
                    ${requestScope.INVALID.dateOfPurchaseError}
                    </font>
                    <br/>
                    Warranty Period: <input type="text" name="txtWarrantyPeriod" style="margin: 20px 20px 20px 26px;" value="${requestScope.DTO.warrantyPeriod}" />
                    <font color="red">
                    ${requestScope.INVALID.warrantyPeriodError}
                    </font>
                    <br/>
                    Fixed Times: <input type="text" name="txtNumberOfCorrections" style="margin: 20px 20px 20px 51px;" readonly="true" value="${requestScope.DTO.numberOfCorrections}" />
                    <br/>
                    Device Status: <input type="text" name="txtDeviceStatus" style="margin: 20px 20px 20px 40px;" value="${requestScope.DTO.deviceStatus}" />
                    <font color="red">
                    ${requestScope.INVALID.deviceStatusError}
                    </font>
                    <br/>
                    <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                    <input type="submit" value="Update Device" name="action" style="margin:20px 20px 30px 170px"/>
                </form>
            </section>
            <%@include file="aside.jsp" %>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
