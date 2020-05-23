/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casodeestudio;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author vero
 */
public class ProductoTest {
    Producto p;
    
    public ProductoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        p = new Producto("123323", "nsdhskjh");
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of agregarStock method, of class Producto.
     */
    @Test
    public void testAgregarStock() {
        setUp();
        System.out.println("agregarStock");
        int stock = 200;
        int expResult = 200;
        int result = p.agregarStock(stock);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of restarStock method, of class Producto.
     */
    @Test
    public void testRestarStock() {
        setUp();
        System.out.println("restarStock");
        int stock = 200;
        p.agregarStock(stock);
        int result = p.restarStock(stock);
        int expResult = 0;
        assertEquals(expResult, result);
    }
    
}
