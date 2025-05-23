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
import dao.ProviderDAO;
import model.Provider;

public class ProviderDAOTest {
    ProviderDAO dao = new ProviderDAO();
    
    @Test
    public void testSearchProviderException1(){
        String key = "xxxxx";
        Provider list[] = dao.SearchProvider(key);
        Assert.assertNotNull(list);
        Assert.assertEquals(0,list.length);
    }
    
    @Test
    public void testSearchProviderException2(){
        String key = "!@#??";
        Provider list[] = dao.SearchProvider(key);
        Assert.assertNotNull(list);
        Assert.assertEquals(0,list.length);
    }
    
    @Test
    public void testSearchProviderStandard1(){
        String key = "Pepsico";
        Provider list[] = dao.SearchProvider(key);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.length > 0);
        for(Provider p:list){
            Assert.assertTrue(p.getProviderID().toLowerCase().contains(key.toLowerCase()) || 
                    p.getName().toLowerCase().contains(key.toLowerCase())); 
        }
    }
    
    @Test
    public void testSearchProviderStandard2(){
        String key = "A";
        Provider list[] = dao.SearchProvider(key);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.length > 0);
        for(Provider p:list){
            Assert.assertTrue(p.getProviderID().toLowerCase().contains(key.toLowerCase()) ||
                    p.getName().toLowerCase().contains(key.toLowerCase()));
        }
    }
    
    @Test
    public void testAddProvider(){
        Provider p = new Provider("P007","Long Hai","Nghe An","longhai@gmail.com","0987233234","Fresh food");
        Boolean result = dao.AddProvider(p);
        Assert.assertTrue(result);
        
        Provider list[] = dao.SearchProvider("Long Hai");
        Assert.assertTrue(list.length > 0);
        Assert.assertEquals("Long Hai",list[0].getName()); 
        
    }
    
    
}
