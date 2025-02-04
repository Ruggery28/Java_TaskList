/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.UserDAO;
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
@WebServlet(name = "AddTaskServlet", urlPatterns = {"/AddTaskServlet"})
public class AddTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request the session that is coming from and then get the user
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("UserLogged");
        
        //create the variable title to store what the user will enter
        String title = request.getParameter("title");


        Task t = new Task();
        t.setTitle(title);
        t.setFinished(false);
        t.setidUser(u.getId());

        boolean inserted = taskDAO.insertTask(t);
        //if inserted went right, I'll reload the page and show the name of the title
        if (inserted) {

            request.setAttribute("message", "task was added.");
            //Request and respose the home page if the log in is sucessfull
            RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
            rd.forward(request, response);

        } else {

            //If the log in is not correct, will set this error messsage to a variable inside login.jsp
            request.setAttribute("message", "Error while adding the task. Try Again.");

            //and it will keep the user in the login.jsp page
            RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
