<%-- 
    Document   : login
    Created on : 05 30, 13, 10:40:42 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Prototype Login</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <div id="container"> 
            <div id="loginForm">
                <h1>Dietfix - Login</h1>
                <form id="loginContent">
                    <table>
                        <tr>
                            <td class="label">Username:</td>
                            <td><input type="text" name="usrname" class="field"></td>
                        </tr>
                        <tr>
                            <td class="label">Password: </td>
                            <td><input type="password" name="pw" class="field"></td>
                        </tr>
                    </table>
                    <div class="buttonFields">
                        <input type="submit" value="Login">
                        <input type="button" value="Register"> 
                    </div>
                    <div class="error">
                        <p>Invalid Username or Password</p>
                    </div>
                </form>
            </div>
        </div>

        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <script type="text/javascript" src="js/login.js"></script>
    </body>
</html>