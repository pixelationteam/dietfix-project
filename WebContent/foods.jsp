<%-- 
    Document   : food
    Created on : 06 6, 13, 4:57:26 PM
    Author     : Dell
--%>

<%@page import="pup.thesis.server.DietfixServer"%>
<%@page import="pup.thesis.logging.App"%>
<%@page import="pup.thesis.server.FoodsPage"%>
<%@page import="pup.thesis.server.AdminManager"%>
<%
	if(!DietfixServer.isRunning()){
		DietfixServer.start();
	}
    AdminManager am = AdminManager.validate(request);
    if (am == null) {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    
    FoodsPage fp = new  FoodsPage(request,response);
    if(fp.hasErrors()){
        for(String error:fp.getErrors()){
            App.log("Foods.jsp",error);
        }
        return;
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
        <h1>New Food:</h1><br/>
        <label for='fd_name'>Food Name:</label>
        <input type='text' id='fd_name'/><br/>
        <label for='sel_cat'>Select Category:</label>
        <select id='sel_cat'>
        <%
        
			out.println(fp.test());
        %>
        </select>
        
    </body>
</html>
