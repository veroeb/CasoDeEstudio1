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
public class SucursalTest {
    Producto p;
    Sucursal s;
    TElementoAB<Producto> elemProducto;
    TArbolBB<Producto> arbolProductos;
   
    public SucursalTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        p = new Producto("12345", "producto1");
        p.setStock(100);
        s = new Sucursal("abcd", 0, "kdhaskdh", 100, "Montevideo", "Montevideo");
        elemProducto = new TElementoAB<>(p.getEtiqueta(), p);
        arbolProductos = new TArbolBB<>();
        s.insertarProducto(p);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of insertarProducto method, of class Sucursal.
     */
    @Test
    public void testInsertarProducto() {
        setUp();
        System.out.println("insertarProducto");
        s.insertarProducto(p);
        int result = s.tamañoProductos();
        int expResult = 1;
        assertEquals(result, expResult);
    }

    /**
     * Test of insertarProductoPorNombre method, of class Sucursal.
     */
    @Test
    public void testInsertarProductoPorNombre() {
        System.out.println("insertarProductoPorNombre");
        setUp();
        s.insertarProductoPorNombre(p);
        int result = s.tamañoProductosPorNombre();
        int expResult = 1;
        assertEquals(result, expResult);
    }

    /**
     * Test of agregarStock method, of class Sucursal.
     */
    @Test
    public void testAgregarStock() {
        setUp();
        System.out.println("agregarStock");
        Comparable clave = p.getEtiqueta();
        Integer cantidad = 100;
        Boolean expResult = true;
        Boolean result = s.agregarStock(clave, cantidad);
        assertTrue(expResult == result);
    }

    /**
     * Test of restarStock method, of class Sucursal.
     */
    @Test
    public void testRestarStock() {
        setUp();
        System.out.println("restarStock");
        Comparable clave = p.getEtiqueta();
        Integer cantidad = 100;
        Boolean expResult = true;
        Boolean result = s.restarStock(clave, cantidad);
        assertTrue(expResult == result);
    }

    /**
     * Test of eliminarProducto method, of class Sucursal.
     */
    @Test
    public void testEliminarProducto() {
        setUp();
        System.out.println("eliminarProducto");
        Comparable clave = p.getEtiqueta();
        Boolean expResult = true;
        Boolean result = s.eliminarProducto(clave);
        assertTrue(expResult == result);
    }

    /**
     * Test of buscarPorCodigo method, of class Sucursal.
     */
    @Test
    public void testBuscarPorCodigo() {
        setUp();
        System.out.println("buscarPorCodigo");
        Comparable clave = p.getEtiqueta();
        Producto expResult = p;
        Producto result = s.buscarPorCodigo(clave);
        assertSame(expResult, result);
    }

    /**
     * Test of buscarPorCodigoStock method, of class Sucursal.
     */
    @Test
    public void testBuscarPorCodigoStock() {
        setUp();
        System.out.println("buscarPorCodigoStock");
        Comparable clave = p.getEtiqueta();
        Producto expResult = p;
        Producto result = s.buscarPorCodigoStock(clave, 80);
        assertSame(expResult, result);
    }
    
}
