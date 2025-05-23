/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
import java.io.Serializable;

public class Provider implements Serializable{
    private String providerID;
    private String providerName;
    private String address;
    private String email;
    private String phone;
    private String description;

    public Provider(String providerID, String providerName, String address, String email, String phone, String description) {
        this.providerID = providerID;
        this.providerName = providerName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.description = description;
    }

    public Provider() {
        super(); 
    }

    public String getProviderID() {
        return this.providerID;
    }

    public String getName() {
        return this.providerName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getDescription() {
        return this.description;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    public void setName(String providerName) {
        this.providerName = providerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}

