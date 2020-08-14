<%-- 
    Document   : createRoom
    Created on : Mar 11, 2020, 8:52:54 PM
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
                <h2 style=" font-size: 35px; margin: 40px;">Create New Room</h2>
                <form action="MainController" method="POST">
                    Room No: <input type="text" name="txtRoom" style="margin: 20px 20px 20px 40px;" value="${param.txtRoom}"/>
                    <font color="red">
                    ${requestScope.INVALID.roomError}
                    </font>
                    <br/>
                    Floor: <input type="text" name="txtFloor" style="margin: 20px 20px 20px 65px;" value="${param.txtFloor}"/>
                    <font color="red">
                    ${requestScope.INVALID.floorError}
                    </font>
                    <br/>
                    <input type="submit" value="Create Room" name="action" style="margin:20px 20px 30px 170px"/>
                </form>
            </section>
            <%@include file="aside.jsp" %>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
