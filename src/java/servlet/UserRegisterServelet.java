package servlet;

import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Ruggery
 */
@WebServlet(urlPatterns = {"/UserRegisterServelet"})
public class UserRegisterServelet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //When user click on Sign out, it will direct him to the register page with request and response
        RequestDispatcher rd = request.getRequestDispatcher("user_register.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Create variables to store email and password
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Create object "U" to store it and set it to class User
        User u = new User();
        u.setEmail(email);
        u.setPassword(password);

        //execute the method insertUser and sending the object "U". It will give a response true or false.
        boolean registered = UserDAO.insertUser(u);

        //If the response is true, the user will be directed to a page called register_sucess
        if (registered) {

            RequestDispatcher rd = request.getRequestDispatcher("register_sucess.jsp");
            rd.forward(request, response);
            
        //otherwise will be directed to a page called register error
        } else {

            RequestDispatcher rd = request.getRequestDispatcher("register_error.jsp");
            rd.forward(request, response);

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
