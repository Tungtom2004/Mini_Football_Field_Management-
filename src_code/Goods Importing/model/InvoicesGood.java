/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.Serializable;
import java.util.List;
public class InvoicesGood implements Serializable{
    private List<GoodsItem> listGoods;
    private String goodsID;
    private String invoicesID;

    public InvoicesGood(int unitprice, int quantity, String goodsID, String invoicesID) {
        this.listGoods = listGoods;
        this.goodsID = goodsID;
        this.invoicesID = invoicesID;
    }

    public InvoicesGood() {
        super(); 
    }


    public String getGoodsID() {
        return goodsID;
    }

    public String getInvoicesID() {
        return invoicesID;
    }


    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public void setInvoicesID(String invoicesID) {
        this.invoicesID = invoicesID;
    }

    public List<GoodsItem> getListGoods() {
        return listGoods;
    }

    public void setListGoods(List<GoodsItem> listGoods) {
        this.listGoods = listGoods;
    }
    
    public static class GoodsItem implements Serializable {
        private Good goods;
        private int quantity;
        private int unitPrice;

        public GoodsItem() {
            super(); 
        }

        public GoodsItem(Good goods, int quantity, int unitPrice) {
            this.goods = goods;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public Good getGoods() {
            return goods;
        }

        public void setGoods(Good goods) {
            this.goods = goods;
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
    
}
