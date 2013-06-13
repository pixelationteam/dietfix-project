<%-- 
    Document   : knownwords
    Created on : 05 29, 13, 6:30:04 PM
    Author     : Dell
--%>

<%@page import="pup.thesis.helper.MysqlHelper"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/jquery.min.js" type="text/javascript" ></script>
        <script src="js/knownwords.js" type="text/javascript" ></script>
    </head>
    <body>
      
        <h1>Known Words</h1>
        <label for="tagtype" >Type</label>
    <select id="tagtype" >
    </select>
</body>
</html>
