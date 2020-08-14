<%-- 
    Document   : index
    Created on : Mar 8, 2020, 10:07:34 PM
    Author     : ADMIN
--%>

<%@page import="hieupt.dtos.RegistrationErrorObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="font-size: 35px; margin: 40px;">Login Page</h1>
        <form action="MainController" method="POST">
            Username:<input type="text" name="txtUsername" style="margin: 20px;"/>
            <font color="red">
            ${requestScope.INVALID.usernameError}
            </font>
            </br>
            Password:<input type="password" name="txtPassword" style="margin: 22px;"/>
            <font color="red">
            ${requestScope.INVALID.passwordError}
            </font>
            </br>
            <font color="red">${requestScope.ERROR}</font></br>
            <a href="register.jsp" style="margin-top: 80px;">Register account</a>
            <input type="submit" value="Login" name="action" style="margin: 20px 0 0 100px;"/></br>
        </form>
        
    </body>
</html>
