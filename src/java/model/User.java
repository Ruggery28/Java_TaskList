/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Ruggery
 */
public class User {

    /* 
    When a class is Private, it will only share inside the class, so you can only
    call the variable if you are in the same class, it's safer and better. However,
    it's still possible to store it from another class in another file, but we need
    the parameters Get and Set.
     */
    private String email, password;
    private int id;
    private ArrayList<Task> Tasks; 

    /*Method Get, the user will enter a value in the main[TaskList]
    and it will be stored here in this.email [references private String email] 
    and it will receive the value in the String email I'm getting from the main[TaskList]
    A getter method retrieves the value of a private field from a class.
    The getter method allows controlled access to the value of a private field.*/
 /*
    Here in this method Set, what email user entered in the main[TaskList] after 
    is stored in the private email here in this class, I need to see it in the 
    main[TaksList]. so, I can create a setEmail method that returns the email value
    stored in the getEmail method.
    A setter method updates or modifies the value of a private field in a class.
    The setter method allows controlled modification of the value of a private 
    field and can include validation logic.
     */
    
    /*All users need their onw Task, that's why I created Arry to store all task
    from each user*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Task> getTasks() {
        return Tasks;
    }

    public void setTasks(ArrayList<Task> Tasks) {
        this.Tasks = Tasks;
    }


    
}
