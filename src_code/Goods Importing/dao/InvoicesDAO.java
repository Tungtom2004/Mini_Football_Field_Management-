package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.Invoices;
import model.InvoicesGood;
import model.User;

public class InvoicesDAO extends DAO {

    public InvoicesDAO() {
        super();
    }

    public String generateInvoiceID() {
        String prefix = "IV";
        String sql = "SELECT COUNT(*) FROM tblInvoices";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1) + 1;
                return prefix + String.format("%03d", count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prefix + "001";
    }

    public boolean addInvoice(Invoices invoice) {
        String sqlInvoice = "INSERT INTO tblInvoices (invoicesID,usersID) VALUES (?, ?)";
        String sqlItem = "INSERT INTO tblInvoicesGood (goodsID, invoicesID, unitPrice, quantity) VALUES (?, ?, ?, ?)";

        try {
            con.setAutoCommit(false);

            
            PreparedStatement psInvoice = con.prepareStatement(sqlInvoice);
            psInvoice.setString(1,invoice.getInvoicesID());
            psInvoice.setString(2, invoice.getUsersID());
            psInvoice.executeUpdate();

            
            List<InvoicesGood> goodsList = invoice.getListGood();
            for (InvoicesGood item : goodsList) {
                PreparedStatement psItem = con.prepareStatement(sqlItem);
                psItem.setString(1, item.getGoodsID());
                psItem.setString(2, invoice.getInvoicesID());
                psItem.setInt(3, item.getUnitPrice());
                psItem.setInt(4, item.getQuantity());
                psItem.executeUpdate();
            }

            con.commit();
            return true;

        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean confirmInvoice(String invoiceId) {
        String sql = "SELECT COUNT(*) FROM tblInvoicesGood WHERE invoicesID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, invoiceId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updatePaymentStatus(String invoiceID){
        String sql = "UPDATE tblInvoices SET paymentStatus = 'Paid' WHERE invoicesID = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,invoiceID);
            int rows = ps.executeUpdate();
            return rows > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    
    
} 
