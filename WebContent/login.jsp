<%-- 
    Document   : login
    Created on : 05 30, 13, 10:40:42 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head> 
    <body>
        <form method="POST" action="admin">
            <fieldset>
                <label for="un">Username: <input type="text" id="un" name="username" size="15" /></label><br />
                <label for="pw">Password: <input type="text" id="pw" name="password" size="15" /></label><br />
                <p><input type="submit" value="Login" /></p>
            </fieldset>    
        </form>
    </body>
</html>
