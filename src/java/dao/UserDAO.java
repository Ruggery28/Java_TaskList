/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;
import model.User;

/**
 *
 * @author Ruggery
 */
public class UserDAO {

    public static Scanner scanner = new Scanner(System.in);
    //Constant
    private static final String INSERT = "INSERT INTO list_task.user (email, password) VALUES (?, ?)";
    private static final String SELECT = "SELECT * FROM list_task.user";
    private static final String SELECT_EMAIL = "SELECT * FROM list_task.user WHERE email = ?";
    private static final String UPDATE = "UPDATE list_task.user SET email = ?, password = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM list_task.user WHERE id = ?";

    private static final String URL = "jdbc:mysql://localhost:3306/list_task";
    private static final String USER = "root";
    private static final String PASSWORD = "Pass1234!";

    //STUDYING ABOUT CRUD - CREATE, READ, UPDATE AND DELETE
    //READ
    public static ArrayList<User> searchUser() {

        ArrayList<User> users = new ArrayList();
        //TRY THE CONNECTION AND IF THERE IS SOMETHING WRONG CLOSE AND CATCH A ERROR MESSAGE
        try {

            //CREATING THE DRIVERS TO CONNECT TO MYSQL
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            //CREATING A CONNECTION WITH DRIVER FROM JAVA
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/list_task", "root", "Pass1234!");

            //CRETING A STATEMENT TO SEND TO DRIVE AND EXECUTE ON MYSQL
            PreparedStatement stmt = c.prepareStatement(SELECT);

            //STORING THE RESULT INSIDE THE RESULTSET
            ResultSet rs = stmt.executeQuery();

            //WHILE THERE IS A LINE TO READ WE STORE EVERY COLUMN INSIDE THE VALUES AND PRINT THEM AFTER
            while (rs.next()) {

                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");

                //Instacia um novo object
                User u = new User();
                u.setId(id);
                u.setEmail(email);
                u.setPassword(password);

                users.add(u);

            }

            //WE NEED TO CLOSE THE STATEMENT AND CONNECTION, BECAUSE IT USE MEMORY
            stmt.close();
            c.close();

            //IF THERE IS NO RIGHT CONNECTION, IT CLOSE AND GIVE THE ERROR MESSAGE
        } catch (SQLException e) {
            System.out.println("Error when registering driver or connecting: " + e.getMessage());
            e.printStackTrace();
        }

        return users;
    }

    public static User searchUserEmail(String email) {

        User u = null;

        try {
            //CREATING THE DRIVERS TO CONNECT TO MYSQL
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            //CREATING A CONNECTION WITH DRIVER FROM JAVA
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);

            //CRETING A STATEMENT TO SEND TO DRIVE AND EXECUTE ON MYSQL
            PreparedStatement stmt = c.prepareStatement(SELECT_EMAIL);

            //SENDING EMAIL AS A PARAMETER TO SEARCH IT INTO DATABASE
            stmt.setString(1, email);

            //STORING THE RESULT INSIDE THE RESULTSET
            ResultSet rs = stmt.executeQuery();

            //IF THERE IS A LINE TO READ IT MEANS EMAIL MATCH WITH A EMAIL INSIDE THE DATABASE
            if (rs.next()) {
                //I'm getting id and password that match with email
                int id = rs.getInt("id");
                String password = rs.getString("password");

                //created a new object and set the datas to it
                u = new User();
                u.setId(id);
                u.setEmail(email);
                u.setPassword(password);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;

    }

    //CREATE
    public static boolean insertUser(User u) {

        boolean sucess = false;

        try {

            //CREATING THE DRIVERS TO CONNECT TO MYSQL
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            //CREATING A CONNECTION WITH DRIVER FROM JAVA
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);

            //CRETING A STATEMENT TO SEND TO DRIVE AND EXECUTE ON MYSQL, ? will be the value we will switch
            PreparedStatement stmt = c.prepareStatement(INSERT);

            //SETTING THE VALUES INSIDE THE '?' WITH STMT
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getPassword());

            //STORE INSIDE THE IN ROWSAFFECTED THE UPDATE THAT WAS MADE INSIDE THE STMT
            int rowsAffected = stmt.executeUpdate();

            //CHECK IF THE UPDATE WAS SUCESSFULLY BY CHEKING HOW MANY ROWS WAS AFFECTED INSIDE MYSQL
            if (rowsAffected > 0) {
                sucess = true;
            }

        } catch (SQLException e) {
            System.out.println("Error when registering driver or connecting: " + e.getMessage());
            e.printStackTrace();
        }

        return sucess;
    }

    //UPDATE
    public static boolean updateUser(User u) {

        boolean sucess = false;

        try {

            //CREATING THE DRIVERS TO CONNECT TO MYSQL
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            //CREATING A CONNECTION WITH DRIVER FROM JAVA
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);

            //CRETING A STATEMENT TO SEND TO DRIVE AND EXECUTE ON MYSQL, ? will be the value we will switch
            PreparedStatement stmt = c.prepareStatement(UPDATE);

            //SETTING THE VALUES INSIDE THE '?' WITH STMT
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getPassword());
            stmt.setInt(3, u.getId());

            //STORE INSIDE THE IN ROWSAFFECTED THE UPDATE THAT WAS MADE INSIDE THE STMT
            int rowsAffected = stmt.executeUpdate();

            //CHECK IF THE UPDATE WAS SUCESSFULLY BY CHEKING HOW MANY ROWS WAS AFFECTED INSIDE MYSQL
            if (rowsAffected > 0) {
                sucess = true;
            }

        } catch (SQLException e) {
            System.out.println("Error when registering driver or connecting: " + e.getMessage());
            e.printStackTrace();
        }

        return sucess;

    }

    //DELETE
    public static boolean deleteUser(User u) {

        boolean sucess = false;

        try {

            //CREATING THE DRIVERS TO CONNECT TO MYSQL
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            //CREATING A CONNECTION WITH DRIVER FROM JAVA
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);

            //CRETING A STATEMENT TO SEND TO DRIVE AND EXECUTE ON MYSQL, ? will be the value we will switch
            PreparedStatement stmt = c.prepareStatement(DELETE);

            //SETTING THE VALUES INSIDE THE '?' WITH STMT
            stmt.setInt(1, u.getId());

            //STORE INSIDE THE IN ROWSAFFECTED THE UPDATE THAT WAS MADE INSIDE THE STMT
            int rowsAffected = stmt.executeUpdate();

            //CHECK IF THE UPDATE WAS SUCESSFULLY BY CHEKING HOW MANY ROWS WAS AFFECTED INSIDE MYSQL
            if (rowsAffected > 0) {
                sucess = true;
            }

        } catch (SQLException e) {
            System.out.println("Error when registering driver or connecting: " + e.getMessage());
            e.printStackTrace();
        }

        return sucess;
    }

}
