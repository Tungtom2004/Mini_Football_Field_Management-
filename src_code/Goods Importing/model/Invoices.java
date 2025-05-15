/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.Serializable;

public class Invoices implements Serializable{
    private String invoicesID; 
    private String usersID;
    private InvoicesGood invoicesGood; 

    public Invoices(String invoicesID, String usersID) {
        this.invoicesID = invoicesID;
        this.usersID = usersID;
        this.invoicesGood = invoicesGood; 
    }

    public Invoices() {
        super(); 
    }

    public String getInvoicesID() {
        return invoicesID;
    }

    public String getUsersID() {
        return usersID;
    }

    public void setInvoicesID(String invoicesID) {
        this.invoicesID = invoicesID;
    }

    public void setUsersID(String usersID) {
        this.usersID = usersID;
    }

    public void setInvoicesGood(InvoicesGood invoicesGood) {
        this.invoicesGood = invoicesGood;
    }

    public InvoicesGood getInvoicesGood() {
        return invoicesGood;
    }
    
    
    
}
