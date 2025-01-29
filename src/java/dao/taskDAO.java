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
import model.Task;
import model.User;

/**
 *
 * @author Ruggery
 */
public class taskDAO {

    public static Scanner scanner = new Scanner(System.in);
    //Constant
    private static final String INSERT = "INSERT INTO list_task.task (title, finalised, id_user) VALUES (?, ?, ?)";
    private static final String SELECT = "SELECT * FROM list_task.task WHERE id_user = ?";
    private static final String UPDATE = "UPDATE list_task.task SET finalised = ? WHERE id = ? and id_user = ?";
    private static final String DELETE = "DELETE FROM list_task.task WHERE id = ? and id_user = ?";

    private static final String URL = "jdbc:mysql://localhost:3306/list_task";
    private static final String USER = "root";
    private static final String PASSWORD = "Pass1234!";

    //STUDYING ABOUT CRUD - CREATE, READ, UPDATE AND DELETE
    //READ
    public static ArrayList<Task> searchUserTask(User u) {

        ArrayList<Task> tasks = new ArrayList();
        //TRY THE CONNECTION AND IF THERE IS SOMETHING WRONG CLOSE AND CATCH A ERROR MESSAGE
        try {

            //CREATING THE DRIVERS TO CONNECT TO MYSQL
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            //CREATING A CONNECTION WITH DRIVER FROM JAVA
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);

            //CRETING A STATEMENT TO SEND TO DRIVE AND EXECUTE ON MYSQL
            PreparedStatement stmt = c.prepareStatement(SELECT);

            //SETTING '? = ID'
            stmt.setInt(1, u.getId());

            //STORING THE RESULT INSIDE THE RESULTSET
            ResultSet rs = stmt.executeQuery();

            //WHILE THERE IS A LINE TO READ WE STORE EVERY COLUMN INSIDE THE VALUES AND PRINT THEM AFTER
            while (rs.next()) {

                int id = rs.getInt("id");
                String title = rs.getString("title");
                boolean finished = rs.getBoolean("finalised");

                //Instacia um novo object
                Task t = new Task();
                t.setId(id);
                t.setTitle(title);
                t.setFinished(finished);
                t.setidUser(u.getId());

                tasks.add(t);
            }

            //WE NEED TO CLOSE THE STATEMENT AND CONNECTION, BECAUSE IT USE MEMORY
            stmt.close();
            c.close();

            //IF THERE IS NO RIGHT CONNECTION, IT CLOSE AND GIVE THE ERROR MESSAGE
        } catch (SQLException e) {
            System.out.println("Error when registering driver or connecting: " + e.getMessage());
            e.printStackTrace();
        }

        return tasks;
    }

    //CREATE
    public static boolean insertTask(Task t) {

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
            stmt.setString(1, t.getTitle());
            stmt.setBoolean(2, t.isFinished());
            stmt.setInt(3, t.getidUser());

            //STORE INSIDE THE IN ROWSAFFECTED THE UPDATE THAT WAS MADE INSIDE THE STMT
            int rowsAffected = stmt.executeUpdate();

            //CHECK IF THE UPDATE WAS SUCESSFULLY BY CHEKING HOW MANY ROWS WAS AFFECTED INSIDE MYSQL
            sucess = rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error when registering driver or connecting: " + e.getMessage());
            e.printStackTrace();
        }

        return sucess;
    }

    //UPDATE
    public static boolean updateTask(Task t) {

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
            stmt.setBoolean(1, true);
            stmt.setInt(2, t.getId());
            stmt.setInt(3, t.getidUser());

            //STORE INSIDE THE IN ROWSAFFECTED THE UPDATE THAT WAS MADE INSIDE THE STMT
            int rowsAffected = stmt.executeUpdate();

            //CHECK IF THE UPDATE WAS SUCESSFULLY BY CHEKING HOW MANY ROWS WAS AFFECTED INSIDE MYSQL
            sucess = rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error when registering driver or connecting: " + e.getMessage());
            e.printStackTrace();
        }

        return sucess;

    }

    //DELETE
    public static boolean deleteTask(Task t) {

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
            stmt.setInt(1, t.getId());
            stmt.setInt(2, t.getidUser());

            //STORE INSIDE THE IN ROWSAFFECTED THE UPDATE THAT WAS MADE INSIDE THE STMT
            int rowsAffected = stmt.executeUpdate();

            //CHECK IF THE UPDATE WAS SUCESSFULLY BY CHEKING HOW MANY ROWS WAS AFFECTED INSIDE MYSQL
            sucess = rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error when registering driver or connecting: " + e.getMessage());
            e.printStackTrace();
        }

        return sucess;
    }

}
