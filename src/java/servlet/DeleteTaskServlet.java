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
@WebServlet(name = "DeleteTaskServlet", urlPatterns = {"/DeleteTaskServlet"})
public class DeleteTaskServlet extends HttpServlet {

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

        boolean deleted = taskDAO.deleteTask(t);

        if (deleted) {
            //if inserted went right, I'll reload the page and show the name of the title
            request.setAttribute("message", "Task deleted sucessfully.");

        } else {
            //If the log in is not correct, will set this error messsage to a variable inside login.jsp
            request.setAttribute("message", "Error while deleting the task. Try Again.");
        }

        //Request and respose the home page if the log in is sucessfull
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
