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
public class Empresa implements IEmpresa{
   
    private String nombreEmpresa;
    private TArbolBB<Sucursal> listaSucursales;
    private TArbolBB<Producto> listaProductos;
    
    public Empresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
        this.listaSucursales = new TArbolBB<>();
        this.listaProductos = new TArbolBB<>();
    }

    @Override
    public String getNombre() {
        return nombreEmpresa;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombreEmpresa = nombre;
    }

    @Override
    public Sucursal buscarSucursal(Comparable idSucursal) {
        TElementoAB<Sucursal> unaSucursal = listaSucursales.buscar(idSucursal);
        
        if(unaSucursal != null){
//            System.out.println(String.format("La sucursal %s ya se encuentra en el directorio", idSucursal));
            return unaSucursal.getDatos();
        }
        else{
//            System.out.println(String.format("La sucursal %s no se encuentra en el directorio", idSucursal));
            return null;
        }
    }
    
    public Producto buscarProducto(Comparable idProducto) {
        TElementoAB<Producto> unProducto = listaProductos.buscar(idProducto);
        
        if(unProducto != null){
//            System.out.println(String.format("El producto %s ya se encuentra en el directorio", idProducto));
            return unProducto.getDatos();
        }
        else{
//            System.out.println(String.format("El producto %s no se encuentra en el directorio", idProducto));
            return null;
        }
    }

    @Override
    public void insertarSucursal(Sucursal sucursal) {
        TElementoAB<Sucursal> unaSucursal = new TElementoAB<>(sucursal.getId(), sucursal);
        Sucursal s = buscarSucursal(sucursal.getId());
        
        if(s == null){
            listaSucursales.insertar(unaSucursal);      //Inserta unicamente si no hay sucursales repetidas
        }
    }

    @Override
    public void insertarSucursalesArchivo(String nombreArchivo) {
        ArrayList<String> sucursales = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        
        for(String linea : sucursales){
            String[] result = linea.split(",");
            int tel = Integer.parseInt(result[1]);
            int cp = Integer.parseInt(result[3]);
            Sucursal sucursal = new Sucursal(result[0], tel, result[2], cp, result[4], result[5]);
            insertarSucursal(sucursal);
        }
        listaSucursales.inOrden();
    }

    @Override
    public TArbolBB<Sucursal> getSucursales() {
        System.out.println("Las sucursales existentes son: " + listaSucursales.inOrden());
        return listaSucursales;
    }
    
  
    public TArbolBB<Producto> getProductos() {
        System.out.println("Los productos existentes son: " + listaProductos.inOrden());
        return listaProductos;
    }
    
    public void insertarProducto(Producto producto) {
        TElementoAB<Producto> unProducto = new TElementoAB<>(producto.getEtiqueta(), producto);
        Producto p = buscarProducto(producto.getEtiqueta());
        
        if(p == null){
            listaProductos.insertar(unProducto);      //Inserta unicamente si no hay sucursales repetidas
        }
    }
    
    
    public void insertarProductosArchivo(String nombreArchivo) {
        ArrayList<String> productos = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        Producto producto;
        TElementoAB<Producto> elem;
        
        //Se crean sets para que no se repitan los valores y para que no de StackOverflow
        Set s = new HashSet(productos.size());
        s.addAll(productos);
        List<String> shuffledList = new ArrayList(s.size());
        shuffledList.addAll(s);

        for (String data1 : shuffledList) {
            String[] datos = data1.split("\"");
            datos[0] = datos[0].replace(",", "");
            datos[1] = datos[1].replace("\"", "");
//            System.out.println(++contador + " " + datos[1]);
            datos[2] = datos[2].replace(",", "");
            producto = new Producto(datos[0], datos[1]);            
            producto.setPrecio(Float.parseFloat(datos[2]));
            elem = new TElementoAB<>(datos[0], producto);           //no se si dejarlo asi o agregarle el precio como dato
//            elem = new TElementoAB<>(datos[0], datos[1] + ", " + datos[2]);
            insertarProducto(producto);
//           producto.setStock(Integer.parseInt(datos[3]));
//            System.out.println(String.format(++contador + " El codigo es: %s, nombre: %s y precio: $%s",
//                    producto.getEtiqueta().toString(), producto.getNombre(), producto.getPrecio().toString()));
//            sucursal.insertarProducto(producto);
        }
        
//        for(String linea : productos){
//            String[] result = linea.split(",");
//            
//            float precio = Float.parseFloat(result[2]);
//            Producto producto = new Producto(result[0], result[1]);
//            producto.setPrecio(precio);
//            insertarProducto(producto);
//        }
//        listaProductos.inOrden();
    }

    @Override
    public boolean directorioVacio() {
        if(listaSucursales.esVacio()){
            System.out.println("El directorio esta vacio");
            return true;
        }            
        else{
            System.out.println("El directorio no esta vacio");
            return false;
        }  
    }
    
    public void agregarStockArchivo(String nombreArchivo){
        ArrayList<String> stockArchivo = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        for(String linea : stockArchivo){
            String[] result = linea.split(",");
            Comparable idSucursal = result[0];
            Comparable idProducto = result[1];
            int stock = Integer.parseInt(result[2]);
            Sucursal sucursal = buscarSucursal(idSucursal);
            
            if(sucursal != null){
                Producto prod = buscarProducto(idProducto);
                sucursal.insertarProducto(prod);
                sucursal.agregarStock(idProducto, stock);
            }
        }        
    }
    
    public void restarStockSucursal(Comparable idSucursal, Comparable idProducto, Integer cantidad){
        Sucursal sucursal = buscarSucursal(idSucursal);
            
        if(sucursal != null){
            sucursal.restarStock(idProducto, cantidad);
        }
    }
    
    public void listarProductosSucursal(Comparable idSucursal){
        Sucursal sucursal = buscarSucursal(idSucursal);
        System.out.println("Los productos existentes en la sucursal " + idSucursal + " son:");
        sucursal.imprimirProductos();
    }
    
    public void eliminarProductoDeTodasLasSucursales(Comparable claveProducto){
        if(!listaSucursales.esVacio())
            eliminarProductoDeTodasLasSucursalesImplementacion(listaSucursales.getRaiz(), claveProducto);
        else
            System.out.println("La lista de sucursales esta vacia");
    }
    
    private void eliminarProductoDeTodasLasSucursalesImplementacion(TElementoAB<Sucursal> elemSucursal, Comparable claveProducto){
        if(elemSucursal.getHijoIzq() != null){
            eliminarProductoDeTodasLasSucursalesImplementacion(elemSucursal.getHijoIzq(), claveProducto);
        }
        elemSucursal.getDatos().eliminarProducto(claveProducto);
        if(elemSucursal.getHijoDer() != null)
            eliminarProductoDeTodasLasSucursalesImplementacion(elemSucursal.getHijoDer(), claveProducto);
        
        
    }
    
}
