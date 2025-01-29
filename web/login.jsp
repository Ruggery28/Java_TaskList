<%-- 
    Document   : login
    Created on : 29 Jan 2025, 22:15:45
    Author     : Ruggery
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String error = (String) request.getAttribute("Error");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login | TASK LIST</title>
    </head>
    <body>
        <h1>Sign In</h1>
        <form method="POST" action="LoginServlet">
            <p>
                <label>Email: </label>
                <input type="email" name="email">
            </p>
            <p>
                <label>Password: </label>
                <input type="password" name="password">
            </p>
            <input type="submit" value="Sign In">
            <p>
                <%
                if (error != null){
                    out.print(error);
                    }
                %>
            </p>
        </form>
    </body>
</html>
