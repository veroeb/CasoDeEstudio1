/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casodeestudio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author vero
 */
public class Sucursal implements ISucursal{
    private Comparable id;
    private int telefono;
    private String direccion;
    private int codigoPostal;
    private String ciudad;
    private String departamento;

    private final TArbolBB<Producto> productos;
    private final TArbolBB<Producto> productosPorNombre;

    public Sucursal(Comparable id, int telefono, String direccion, int codigoPostal, String ciudad, String departamento) {
        this.id = id;
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.productos = new TArbolBB<>();
        this.productosPorNombre = new TArbolBB<>();
    }
    
    @Override
    public Comparable getId() {
        return id;
    }

    @Override
    public void setId(Comparable id) {
        this.id = id;
    }

    @Override
    public int getTelefono() {
        return telefono;
    }

    @Override
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    @Override
    public String getDireccion() {
        return direccion;
    }

    @Override
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    @Override
    public int getCodigoPostal() {
        return codigoPostal;
    }

    @Override
    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String getCiudad() {
        return ciudad;
    }

    @Override
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    @Override
    public String getDepartamento() {
        return departamento;
    }

    @Override
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    //****************************************************************************
    //Metodos de Sucursal
    
    @Override
    public void insertarProducto(Producto unProducto) {
        TElementoAB<Producto> prod = new TElementoAB(unProducto.getEtiqueta(), unProducto);
        productos.insertar(prod);
        
    }
    
    public void insertarProductoPorNombre(Producto unProducto) {
        TElementoAB<Producto> productoNombre = new TElementoAB<>(unProducto.getNombre(), unProducto);
        productosPorNombre.insertar(productoNombre);
    }
    
    @Override
    public Boolean agregarStock(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> prod = productos.buscar(clave);
        if(prod != null){
            int stock = prod.getDatos().agregarStock(cantidad);
//            prod.getDatos().setStock(cantidad);
//        System.out.println(String.format("Sucursal %s. El producto %s tiene %d stock", id, clave, stock));

            return true;            
        }
        else{
            System.out.println("Ese producto no se encuentra en la empresa.");
            return false;
        }
    }
    
    //no se usa por ahora
    public Boolean agregarStockPorNombre(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> prodNombre = productosPorNombre.buscar(clave);
        if(prodNombre != null){
            int stockNombre = prodNombre.getDatos().agregarStock(cantidad);
//            prod.getDatos().setStock(cantidad);
//        System.out.println(String.format("Sucursal %s. El producto %s tiene %d stock", id, clave, stock));

            return true;            
        }
        else{
            System.out.println("Ese producto no se encuentra en la empresa.");
            return false;
        }
    }

//    @Override
//    public Integer restarStock(Comparable clave, Integer cantidad) {
//        TElementoAB<Producto> prod = productos.buscar(clave);
//        System.out.println(String.format("Sucursal %s, producto %s, stock %d", id, clave, prod.getDatos().getStock()));
//        int stockFinal = prod.getDatos().restarStock(cantidad);
//        System.out.println(String.format("Sucursal %s, producto %s, el nuevo stock es %d", id, clave, stockFinal));
////        int cantidadActual = prod.getDatos().getStock();
////        if (cantidadActual - cantidad >= 0)
////            prod.getDatos().setStock(cantidadActual - cantidad);
////        else
////            return -1;// No se puede restart stock
////        return cantidadActual - cantidad;
//        return stockFinal;
//    }
    
    @Override
    public Boolean restarStock(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> prod = productos.buscar(clave);
        if(prod != null){
            int stockFinal = prod.getDatos().restarStock(cantidad);
            if(stockFinal != -1)
                return true;
            else
                return false;
        }
        else{
            System.out.println("Ese producto no se encuentra en la sucursal.");
            return false;
        }
    }

    @Override
    public Producto buscarPorCodigo(Comparable clave) {
        TElementoAB<Producto> prod = productos.buscar(clave);
        
        if(prod != null){
            System.out.println(String.format("El producto %s se encuentra en la sucursal %s con stock %d.", 
                    clave, id, prod.getDatos().getStock()));
            return prod.getDatos();            
        }
        else{
            System.out.println(String.format("El producto %s no se encuentra en la sucursal %s.", clave, id));
            return null;
        }
    }
    
    public Producto buscarPorCodigoStock(Comparable clave, Integer stock) {
        TElementoAB<Producto> prod = productos.buscar(clave);
        
        if(prod != null && prod.getDatos().getStock() > stock){
            System.out.println(String.format("El producto %s se encuentra en la sucursal %s con stock %d.", 
                    clave, id, prod.getDatos().getStock()));
            return prod.getDatos();            
        }
        else{
            System.out.println(String.format("El producto %s no tiene stock suficiente en la sucursal %s.", clave, id));
            return null;
        }
    }
    
//    public void buscarPorNombre(){
//        if(!productos.esVacio()){
//            buscarPorNombreImplementacion();
////            listarPorNombre();
//        }        
//        else
//            System.out.println("La lista de sucursales esta vacia");
//    }
    
//    private void buscarPorNombreImplementacion(TElementoAB<Producto> producto){
//        if(producto.getHijoIzq() != null){
//            buscarPorNombreImplementacion(producto.getHijoIzq());
//        }
//        
//        System.out.println(String.format("%s con stock %d.", producto.getDatos().getNombre(), producto.getDatos().getStock()));
//        
//        if(producto.getHijoDer() != null)
//            buscarPorNombreImplementacion(producto.getHijoDer());    
//        
//        
//    }
    
//    private void buscarPorNombreImplementacion() {
//        TElementoAB<Producto> producto = productos.getRaiz();
//        productosPorNombre = new TArbolBB<>();
//        
//        if(producto.getHijoIzq()!= null)
//            buscarPorNombreImplementacion();   
//        
////        Set s = new HashSet(productos.tamaño());
////        s.addAll(productos);
////        List<String> shuffledList = new ArrayList(s.size());
////        shuffledList.addAll(s);
//        
//        TElementoAB<Producto> productoNuevo = new TElementoAB<>(producto.getDatos().getNombre(), producto.getDatos());
//        productosPorNombre.insertar(productoNuevo);
//        
//        if(producto.getHijoDer() != null)
//            buscarPorNombreImplementacion();       
//        
//        
////        if (nombreProducto.compareTo(nombre) == 0) {            
////            System.out.println(String.format("- %s con stock %d.", nombre, stock));
////        } 
////        else if (nombreProducto.compareTo(nombre) < 0 && producto.getHijoIzq() != null) {
////            buscarPorNombreImplementacion(producto.getHijoIzq(), nombreProducto);
////
////        } 
////        else if (nombreProducto.compareTo(nombre) > 0 && producto.getHijoDer() != null) {
////            buscarPorNombreImplementacion(producto.getHijoDer(), nombreProducto);
////        } 
////        else {
////            System.out.println("No existe el producto");
////        }
////        return productosPorNombre;
//    }
    
    public void listarPorNombre(){
        if(!productosPorNombre.esVacio()){
            System.out.println(String.format("\nLos productos existentes en la sucursal \"%s\" son:", id));
            listarPorNombreImplementacion(productosPorNombre.getRaiz());            
        }
        else
            System.out.println("La lista de sucursales esta vacia");
    }
    
    private void listarPorNombreImplementacion(TElementoAB<Producto> producto){
        if(producto.getHijoIzq() != null){
            listarPorNombreImplementacion(producto.getHijoIzq());
        }
        
        System.out.println(String.format("- %s; stock %d", producto.getEtiqueta(), producto.getDatos().getStock()));
        
        if(producto.getHijoDer() != null)
            listarPorNombreImplementacion(producto.getHijoDer());    
    }
    
    
//    public Producto buscarPorNombre() {
////        TElementoAB<Producto> prod = productos.buscar(clave);
//        productos.getRaiz().getDatos().getNombre();
//        
//        if(prod != null){
//            System.out.println(String.format("El producto %s se encuentra en la sucursal %s con stock %d.", 
//                    prod.getDatos().getNombre(), id, prod.getDatos().getStock()));
//            return prod.getDatos();            
//        }
//        else{
//            System.out.println(String.format("El producto %s no se encuentra en la sucursal %s.", clave, id));
//            return null;
//        }
//    }

    @Override
    public boolean eliminarProducto(Comparable clave) {
        productos.eliminar(clave);
        System.out.println(String.format("El producto %s ha sido eliminado de la sucursal %s", clave, id));
        return true;
    }
    
    @Override
    public String imprimirProductos() {
        return productos.inOrden();
    }


}
