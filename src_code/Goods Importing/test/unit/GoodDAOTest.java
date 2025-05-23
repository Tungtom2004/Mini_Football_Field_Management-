/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.unit;

import java.sql.Connection;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import dao.DAO;
import dao.GoodDAO;
import model.Good;
import model.Provider;

public class GoodDAOTest {
    GoodDAO dao = new GoodDAO();
    
    @Test
    public void testSearchGoodException1(){
        String key = "xxxxx";
        Good[] list = dao.SearchGood(key);
        Assert.assertNotNull(list);
        Assert.assertEquals(0,list.length);
    }
    
    @Test
    public void testSearchGoodException2(){
        String key = "?!#";
        Good[] list = dao.SearchGood(key);
        Assert.assertNotNull(list);
        Assert.assertEquals(0,list.length);
    }
    
    @Test
    public void testSearchGoodStandard1(){
        String key = "Banh Gao";
        Good list[] = dao.SearchGood(key);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.length > 0);
        for(Good g:list){
            Assert.assertTrue(g.getGoodsID().toLowerCase().contains(key.toLowerCase())||
                    g.getName().toLowerCase().contains(key.toLowerCase())); 
        }
    }
    
    @Test
    public void testSearchGoodStandard2(){
        String key = "A";
        Good list[] = dao.SearchGood(key);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.length > 0);
        for(Good g:list){
            Assert.assertTrue(g.getGoodsID().toLowerCase().contains(key.toLowerCase())||
                    g.getName().toLowerCase().contains(key.toLowerCase()));
        }
    }
    
    
    @Test
    public void testAddGood(){
        Good g = new Good("G009","Thach rau cau","P007");
        boolean added = dao.AddGood(g);
        Assert.assertTrue(added);
        
        Good list[] = dao.SearchGood("Thach rau cau");
        Assert.assertTrue(list.length > 0);
        Assert.assertEquals("Thach rau cau",list[0].getName());
    }
    
    
}
