<%-- 
    Document   : updateUser
    Created on : Mar 10, 2020, 7:06:32 PM
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
                <h1 style=" font-size: 35px; margin: 40px;">Update Page</h1>
                <form action="MainController" method="POST">
                    Username: <input type="text" name="txtUsername" readonly="true" value="${requestScope.DTO.username}" style="margin: 20px 20px 20px 30px; "/>
                    <br/>
                    Fullname: <input type="text" name="txtFullname" value="${requestScope.DTO.fullname}" style="margin: 20px 20px 20px 38px; "/>
                    <font color="red">
                    ${requestScope.INVALID.fullnameError}
                    </font>
                    <br/>
                    Role: <input type="text" name="txtRole" value="${requestScope.DTO.role}" style="margin: 20px 20px 20px 67px; "/>
                    <font color="red">
                    ${requestScope.INVALID.roleError}
                    </font>
                    <br/>
                    Working Room: <input type="text" name="txtWorkingRoom" value="${requestScope.DTO.workingroom}" style="margin: 20px 20px 20px 3px; "/>
                    <font color="red">
                    ${requestScope.INVALID.workingRoomError}
                    </font>
                    <br/>
                    <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                    <font color="red">${requestScope.ERROR}</font></br>
                    <input type="submit" name="action" value="Update User" style="margin:20px 20px 20px 150px"/>
                </form>
            </section>
            <%@include file="aside.jsp" %>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
