/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;
/**
 *
 * @author Admin
 */
public class UserDAO extends DAO{

    public UserDAO() {
        super();
    }
    
    public boolean checkLogin(User user){
        String sql = "SELECT usersID, username FROM tblUsers WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql); 
            ps.setString(1, user.getUsername()); 
            ps.setString(2, user.getPassword()); 
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                user.setUsersID(rs.getString("usersID")); 
                user.setUsername(rs.getString("username")); 
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false; 
    }

    
}
