<%-- 
    Document   : home
    Created on : 29 Jan 2025, 22:47:36
    Author     : Ruggery
--%>

<%@page import="dao.taskDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Task"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | TASK LIST</title>
    </head>
    <body>
        <a>
            <%
                User u = (User) session.getAttribute("UserLogged");
                if (u != null) {
                    out.print(u.getEmail());
                }
            %>
        </a>
        <a href="LogoutServlet">Logout</a>
    <hr>
        <form method="POST" action="AddTaskServlet">
            <p>
            <label>Add Task Title: </label>
            <input type="text" name="title">
            <input type="submit" value="Add Task" >
            </p>
            <p>
                <%
                    String message = (String) request.getAttribute("message");
                    
                    if(message != null){
                    out.print(message);
                    }
                %>
            </p>
        </form>
    <hr>    
        <h1>Task List</h1>
        <form method="POST" action="FinaliseTaskServlet">
        <%
        
        ArrayList<Task> tasks = taskDAO.searchUserTask(u);
        
        if (tasks.isEmpty()){
        out.print("There is not tasks.");
            } 

            for(Task t : tasks){
            out.print("<p>" + "<input type=\"checkbox\" name=\"tasks\" value=\"" + t.getId()  + "\">" 
            + t.getTitle() + " - " + t.isFinished()  
            + " <a href=\"FinaliseTaskServlet?id="+ t.getId() + "\">finalise</a>" 
            + " <a href=\"DeleteTaskServlet?id="+ t.getId() + "\">Delete</a>" +"</p>"
            );
            }
        %>
        <p>
            <input type="submit"  value="Finalise">
        </p>
    </form>
    </body>
</html>
