package model;
import java.io.Serializable;

public class Customer implements Serializable{
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String note;

    public Customer() {
        super();
    }

    public Customer(String name, String idCard, String address, String phone, String email, String note) {
        super();
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return phone;
    }

    public void setTel(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}

