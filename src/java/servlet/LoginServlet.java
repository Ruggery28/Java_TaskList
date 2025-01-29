/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Ruggery
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //When user click on Sign in, it will direct him to the login page with request and response
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //create the variables email and password to store what the user will enter
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Create object u to send email to the class USERDAO and method SearchUserEmail to look for it.
        User u = UserDAO.searchUserEmail(email);

        boolean loginCheck = false;
        //if email exist and password match I will change loginCheck to true
        if (u != null && u.getPassword().equals(password)) {
            loginCheck = true;
        }

        //if loggin is correct, I'll send userLogged U and go to homepage
        if (loginCheck) {

            //Create a session and request it to save who logged = "u"
            HttpSession session = request.getSession();
            session.setAttribute("UserLogged", u);

            //Request and respose the home page if the log in is sucessfull
            RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
            rd.forward(request, response);

        } else {

            //If the log in is not correct, will set this error messsage to a variable inside login.jsp
            request.setAttribute("Error", "Email/Password Incorrect");

            //and it will keep the user in the login.jsp page
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
