<%-- 
    Document   : admin
    Created on : 05 30, 13, 10:47:32 PM
    Author     : Dell
--%>
<%@page import="pup.thesis.server.AdminManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="pup.thesis.server.DietfixServer"%>
<% 
    AdminManager am = AdminManager.validate(request);
    if (am == null) {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    if (request.getParameter("logout") != null) {
        request.getSession().invalidate();
        request.getRequestDispatcher("login.jsp").forward(request, response);
    } else if (request.getParameter("setserver") != null) {
        String setserver = request.getParameter("setserver").trim();
        if (setserver.equals("Start")) {
            DietfixServer.start();
        } else if (setserver.equals("Stop")) {
            DietfixServer.stop();
        }
    }
 

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
        <form method="POST" >
            <p><input type="submit" name="setserver" value="<% out.println((DietfixServer.isRunning()) ? "Stop" : "Start");%>" /></p>
            <p><input type="submit" name="logout" value="Logout" /></p>
        </form>



        <h1>Hello World!</h1>
    </body>
</html>
