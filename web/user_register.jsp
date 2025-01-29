<%-- 
    Document   : user_register
    Created on : 28 Jan 2025, 22:48:00
    Author     : Ruggery
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USER REGISTER | LIST TASK</title>
    </head>
    <body>
        <h1>CREATE ACCOUNT</h1>
        <form method="POST" action="UserRegisterServelet">
            <p>
                <label>Email Address: </label>
                <input type="email" name="email">
            </p>
            <p>
                <label>Password: </label>
                <input type="password" name="password">
            </p>
                <input type="submit" value="Create Account">
        </form>        
    </body>
</html>

