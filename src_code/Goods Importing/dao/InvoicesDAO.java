/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Invoices;
import model.InvoicesGood;
import model.InvoicesGood.GoodsItem; 

public class InvoicesDAO extends DAO{

    public InvoicesDAO() {
        super();
    }
    
    public boolean AddToInvoice(Invoices invoice){
        boolean result = false;
        try{
            // Add new to tblInvoices
            String sql = "INSERT INTO tblInvoices (invoicesID, userid) VALUES (?, ?)"; 
            PreparedStatement psInvoice = con.prepareStatement(sql); 
            psInvoice.setString(1,invoice.getInvoicesID());
            psInvoice.setString(2,invoice.getUsersID()); 
            int affectedRows = psInvoice.executeUpdate(); 
            // Add new row to InvoicesGood
            if(affectedRows > 0 && invoice.getInvoicesID() != null){
                InvoicesGood invoicesGood = invoice.getInvoicesGood();
                String sqlDetail = "INSERT INTO tblInvoicesGood (unit price, quantity, tblGoodsGoodsid, tblInvoicesinvoicesID) VALUES (?, ?, ?, ?)";
                PreparedStatement psDetail = con.prepareStatement(sqlDetail); 
                for(GoodsItem item:invoicesGood.getListGoods()){
                    psDetail.setInt(1,item.getUnitPrice());
                    psDetail.setInt(2,item.getQuantity());
                    psDetail.setString(3,item.getGoods().getGoodsID()); 
                    psDetail.setString(4,invoice.getInvoicesID()); 
                    psDetail.executeUpdate(); 
                } 
                result = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
}
