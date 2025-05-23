package model;

import java.io.Serializable;

public class InvoicesGood implements Serializable {
    private String goodsID;
    private int quantity;
    private int unitPrice;

    public InvoicesGood() {}

    public InvoicesGood(String goodsID,int quantity, int unitPrice) {
        this.goodsID = goodsID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

  
    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
}
