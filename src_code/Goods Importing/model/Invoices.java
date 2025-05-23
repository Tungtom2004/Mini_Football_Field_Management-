package model;
import java.io.Serializable;
import java.util.List;

public class Invoices implements Serializable {
    private String invoicesID;
    private String usersID;
    private List<InvoicesGood> listGood;
    private String providerID;

    public Invoices() {
        super();
    }

    public Invoices(String invoicesID, String usersID, List<InvoicesGood> listGood, String providerID) {
        this.invoicesID = invoicesID;
        this.usersID = usersID;
        this.listGood = listGood;
        this.providerID = providerID;
    }

    public String getProviderID() {
        return this.providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }
    
    public String getInvoicesID() {
        return this.invoicesID;
    }

    public void setInvoicesID(String invoicesID) {
        this.invoicesID = invoicesID;
    }

    public String getUsersID() {
        return this.usersID;
    }

    public void setUsersID(String usersID) {
        this.usersID = usersID;
    }

    public List<InvoicesGood> getListGood() {
        return listGood;
    }

    public void setListGood(List<InvoicesGood> listGood) {
        this.listGood = listGood;
    }
}
