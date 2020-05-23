/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casodeestudio;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author vero
 */
public class EmpresaTest {
    Empresa e;
    Producto p;
    Sucursal s;
    TElementoAB<Producto> elemProducto;
    public TArbolBB<Sucursal> arbolSucursales;
    public TArbolBB<Sucursal> arbolSucursalesPorDepartamento;
    public TArbolBB<Producto> arbolProductosBase;
    public TArbolBB<Producto> arbolProductosEmpresa;
    public TArbolBB<Producto> arbolProductosEmpresaPorNombre;
    
    public EmpresaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        e = new Empresa("GEANT");
        p = new Producto("1403796890", "WWE Kids Todler Velvet Slippers featuring John Cena size 7/8 Infants COLOR Black -");
//        p.setPrecio(3.99f);
        p.setStock(80);
        s = new Sucursal("Local 122", 29827010, "18 de Julio 580", 97000, "Durazno", "Departamento de Durazno");
        elemProducto = new TElementoAB<>(p.getEtiqueta(), p);
        this.arbolSucursales = new TArbolBB<>();
        this.arbolProductosBase = new TArbolBB<>();
        this.arbolProductosEmpresa = new TArbolBB<>();
        this.arbolProductosEmpresaPorNombre = new TArbolBB<>();
        this.arbolSucursalesPorDepartamento = new TArbolBB<>();
        s.insertarProducto(p);
        arbolProductosBase.insertar(elemProducto);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insertarProducto method, of class Empresa.
     */
    @Test
    public void testInsertarProducto() {
        setUp();
        System.out.println("insertarProducto");
        e.insertarProducto(p);
        int result = e.tamañoProductos();
        int expResult = 1;
        assertEquals(result, expResult);
    }

    /**
     * Test of insertarProductosArchivo method, of class Empresa.
     */
    @Test
    public void testInsertarProductosArchivo() {
        setUp();
        System.out.println("insertarProductosArchivo");
        e.insertarProductosArchivo("test/casodeestudio/productosTest.txt");
        int result = e.tamañoProductos();
        int expResult = 1;
        assertEquals(result, expResult);
    }

    /**
     * Test of agregarStockEmpresa method, of class Empresa.
     */
    @Test
    public void testAgregarStockEmpresa() {
        setUp();
        System.out.println("agregarStockEmpresa");
        testInsertarProductosArchivo();
        e.agregarStockEmpresa("test/casodeestudio/stockTest.txt");
        int result = e.tamañoProductos();
        int expResult = 1;
        assertEquals(result, expResult);
    }

    /**
     * Test of restarStockEmpresa method, of class Empresa.
     */
    @Test
    public void testRestarStockEmpresa() {
        setUp();
        System.out.println("restarStockEmpresa");
        testAgregarStockEmpresa();
        Comparable clave = "1403796890";
        Integer cantidad = 60;
        Boolean expResult = true;
        Boolean result = e.restarStockEmpresa(clave, cantidad);
        assertTrue(expResult == result);
    }

    /**
     * Test of eliminarProducto method, of class Empresa.
     */
    @Test
    public void testEliminarProducto() {
        setUp();
        System.out.println("eliminarProducto");
        testAgregarStockEmpresa();
        Comparable clave = "1403796890";
        boolean expResult = true;
        boolean result = e.eliminarProducto(clave);
        assertTrue(expResult == result);
    }

    /**
     * Test of buscarProductoEmpresa method, of class Empresa.
     */
    @Test
    public void testBuscarProductoEmpresa() {
        setUp();
        System.out.println("buscarProductoEmpresa");
        testAgregarStockEmpresa();
        Comparable clave = "1403796890";
        Producto result = e.buscarProductoEmpresa(clave);
        assertNotNull(result);
    }
   
    /**
     * Test of buscarSucursal method, of class Empresa.
     */
    @Test
    public void testBuscarSucursal() {
        setUp();
        System.out.println("buscarSucursal");
        testInsertarSucursalesArchivo();
        Comparable idSucursal = "Local 122";
        Sucursal result = e.buscarSucursal(idSucursal);
        assertNotNull(result);
    }

    /**
     * Test of buscarSucursalPorDepartamento method, of class Empresa.
     */
    @Test
    public void testBuscarSucursalPorDepartamento() {
        System.out.println("buscarSucursalPorDepartamento");
        setUp();
        testInsertarSucursalesArchivo();
        Comparable idSucursal = "Departamento de Durazno, Durazno, 97000, Local 122";
        Sucursal result = e.buscarSucursalPorDepartamento(idSucursal);
        assertNotNull(result);
    }

    /**
     * Test of insertarSucursal method, of class Empresa.
     */
    @Test
    public void testInsertarSucursal() {
        setUp();
        System.out.println("insertarSucursal");
        e.insertarSucursal(s);
        int result = e.tamañoSucursales();
        int expResult = 1;
        assertEquals(result, expResult);
    }

    /**
     * Test of insertarSucursalPorDepartamento method, of class Empresa.
     */
    @Test
    public void testInsertarSucursalPorDepartamento() {
        System.out.println("insertarSucursalPorDepartamento");
        setUp();
        e.insertarSucursalPorDepartamento(s);
        int result = e.tamañoSucursalesDepartamento();
        int expResult = 1;
        assertEquals(result, expResult);
    }

    /**
     * Test of insertarSucursalesArchivo method, of class Empresa.
     */
    @Test
    public void testInsertarSucursalesArchivo() {
        setUp();
        System.out.println("insertarSucursalesArchivo");
        e.insertarSucursalesArchivo("test/casodeestudio/sucursalesTest.txt");
        int result = e.tamañoSucursales();
        int expResult = 1;
        assertEquals(result, expResult);
        
        int result1 = e.tamañoSucursalesDepartamento();
        int expResult1 = 1;
        assertEquals(result1, expResult1);
    }

    /**
     * Test of agregarStockArchivo method, of class Empresa.
     */
    @Test
    public void testAgregarStockArchivo() {
        setUp();
        System.out.println("agregarStockArchivo");
        testInsertarSucursalesArchivo();
        e.insertarProducto(p);
        e.agregarStockArchivo("test/casodeestudio/stockSucursalTest.txt");
        int result = e.tamañoSucursales();
        int expResult = 1;
        assertEquals(result, expResult);
    }

    /**
     * Test of restarStockSucursal method, of class Empresa.
     */
    @Test
    public void testRestarStockSucursal() {
        setUp();
        System.out.println("restarStockSucursal");
        testAgregarStockArchivo();
        Comparable idSucursal = "Local 122";
        Comparable idProducto = "1403796890";
        Integer cantidad = 30;
        Boolean result = e.restarStockSucursal(idSucursal, idProducto, cantidad);
        assertTrue(result);
    }
    
    /**
     * Test of buscarProductoEnSucursales method, of class Empresa.
     */
    @Test
    public void testBuscarProductoEnSucursales() {
        setUp();
        System.out.println("buscarProductoEnSucursales");        
        testAgregarStockArchivo();
        Comparable clave = "1403796890";
        boolean expResult = true;
        boolean result = e.buscarProductoEnSucursales(clave);
        assertTrue(expResult == result);
    }

    /**
     * Test of eliminarProductoDeTodasLasSucursales method, of class Empresa.
     */
    @Test
    public void testEliminarProductoDeTodasLasSucursales() {
        setUp();
        System.out.println("eliminarProductoDeTodasLasSucursales");    
        testAgregarStockArchivo();
        Comparable clave = "1403796890";
        boolean expResult = true;
        boolean result = e.eliminarProductoDeTodasLasSucursales(clave);
        assertTrue(expResult == result);
    }
    
}
