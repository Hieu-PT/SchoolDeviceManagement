<%-- 
    Document   : changePasswordStaff
    Created on : Mar 15, 2020, 5:15:12 PM
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
                <h2 style=" font-size: 35px; margin: 40px;">Create New Account</h2>
                <form action="MainController" method="POST">
                    Old Password: <input type="password" name="txtOldPassword" style="margin: 20px 20px 20px 60px;"/>
                    <font color="red">
                    ${requestScope.INVALID.oldPassError}
                    </font>
                    <br/>
                    New Password: <input type="password" name="txtNewPassword" style="margin: 20px 20px 20px 52px;"/>
                    <font color="red">
                    ${requestScope.INVALID.newPassError}
                    </font>
                    <br/>
                    Confirm Password: <input type="password" name="txtConfirm" style="margin: 20px 20px 20px 31px;"/>
                    <font color="red">
                    ${requestScope.INVALID.confirmError}
                    </font>
                    <br/>
                    <input type="submit" value="Change" name="action" style="margin-left: 100px"/>
                </form>
            </section>
            <%@include file="aside.jsp" %>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
