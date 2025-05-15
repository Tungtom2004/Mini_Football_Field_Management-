/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.Serializable; 
public class Good implements Serializable{
    private String goodsID;
    private String name;
    private String providerID; 

    public Good(String goodsID, String name, String providerID) {
        this.goodsID = goodsID;
        this.name = name;
        this.providerID = providerID;
    }

    public Good() {
        super();
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public String getName() {
        return name;
    }

    public String getProviderID() {
        return providerID;
    }
    
    
}
