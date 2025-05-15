/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList; 
import model.Good;

public class GoodDAO extends DAO{
    public GoodDAO(){
        super();
    }
    
    public Good[] SearchGood(String key){
        ArrayList<Good> result = new ArrayList<>();
        String sql = "SELECT * FROM tblGoods WHERE name LIKE ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql); 
            ps.setString(1,'%' + key + '%');
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Good g = new Good(rs.getString("goodsID"),rs.getString("name"), rs.getString("providerID")); 
                result.add(g); 
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toArray(new Good[0]); 
    }
    
    public boolean AddGood(Good good){
        String sql = "INSERT INTO tblGoods(goodsId,name,providerID) VALUES(?,?,?)"; 
        try{
            PreparedStatement ps = con.prepareStatement(sql); 
            ps.setString(1,good.getGoodsID()); 
            ps.setString(2,good.getName()); 
            ps.setString(3,good.getProviderID()); 
            
            int rowInserted = ps.executeUpdate(); 
            return rowInserted > 0; 
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    
}
