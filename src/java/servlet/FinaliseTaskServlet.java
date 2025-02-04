/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.taskDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Task;
import model.User;

/**
 *
 * @author Ruggery
 */
@WebServlet(name = "FinaliseTaskServlet", urlPatterns = {"/FinaliseTaskServlet"})
public class FinaliseTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request the session that is coming from and then get the user
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("UserLogged");

        //I'll get the id in the home page after the "?" because it means I'll setting a parameter there and getting it here
        String idTask = request.getParameter("id");
        int id = Integer.parseInt(idTask);

        Task t = new Task();
        t.setId(id);
        t.setidUser(u.getId());

        boolean finished = taskDAO.updateTask(t);
        //if inserted went right, I'll reload the page and show the name of the title
        if (finished) {

            request.setAttribute("message", "Task finalised sucessfully.");
            //Request and respose the home page if the log in is sucessfull
            RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
            rd.forward(request, response);

        } else {

            //If the log in is not correct, will set this error messsage to a variable inside login.jsp
            request.setAttribute("message", "Error while finalising the task. Try Again.");

            //and it will keep the user in the login.jsp page
            RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request the session that is coming from and then get the user
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("UserLogged");

        //Create an Array of string and request the parameter on home.jsp and get the value inside the input="name"
        String[] idTasks = request.getParameterValues("tasks");

        boolean sucess = true;

        for (String idTask : idTasks) {

            int id = Integer.parseInt(idTask);

            Task t = new Task();
            t.setId(id);
            t.setidUser(u.getId());

            boolean finished = taskDAO.updateTask(t);

            //if finalised is false it means there is an error and inside the if will return true "!" and will change sucess to false
            if (!finished) {
                sucess = false;
            }
        }

        //if sucess is true it means it work well and will show this message, otherwise it means there is an error.
        if (sucess) {
            request.setAttribute("message", "Task was finalised sucessfully!");
        } else {
            request.setAttribute("message", "Error while finalising the task. Try again!");
        }

        //It will keep the user in the login.jsp page
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
