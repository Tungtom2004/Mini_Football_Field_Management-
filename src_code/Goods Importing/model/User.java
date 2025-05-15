/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.Serializable;
/**
 *
 * @author Admin
 */
public class User implements Serializable{
    private String usersID;
    private String username;
    private String password;
    
    
    public User(){
        super();
    }

    public User(String usersID, String username, String password) {
        this.usersID = usersID;
        this.username = username;
        this.password = password;
    }
    
    
    public void setUsersID(String usersID) {
        this.usersID = usersID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsersID() {
        return usersID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    
    
    
    
}
