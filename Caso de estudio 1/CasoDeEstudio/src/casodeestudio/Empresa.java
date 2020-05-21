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
    private TArbolBB<Sucursal> arbolSucursales;
    private TArbolBB<Sucursal> arbolSucursalesPorDepartamento;
    private TArbolBB<Producto> arbolProductosBase;
    private TArbolBB<Producto> arbolProductosEmpresa;
    private TArbolBB<Producto> arbolProductosEmpresaPorNombre;
    
    public Empresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
        this.arbolSucursales = new TArbolBB<>();
        this.arbolProductosBase = new TArbolBB<>();
        this.arbolProductosEmpresa = new TArbolBB<>();
        this.arbolProductosEmpresaPorNombre = new TArbolBB<>();
        this.arbolSucursalesPorDepartamento = new TArbolBB<>();
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
    public TArbolBB<Sucursal> getSucursales() {
        System.out.println("Las sucursales existentes son: " + arbolSucursales.inOrden());
        return arbolSucursales;
    }
    
  
    public TArbolBB<Producto> getProductos() {
        System.out.println("Los productos existentes son: " + arbolProductosBase.inOrden());
        return arbolProductosBase;
    }
    
     @Override
    public boolean directorioVacio() {
        if(arbolSucursales.esVacio()){
            System.out.println("El directorio esta vacio");
            return true;
        }            
        else{
            System.out.println("El directorio no esta vacio");
            return false;
        }  
    }

    @Override
    public Sucursal buscarSucursal(Comparable idSucursal) {
        TElementoAB<Sucursal> unaSucursal = arbolSucursales.buscar(idSucursal);
        
        if(unaSucursal != null){
//            System.out.println(String.format("La sucursal %s ya se encuentra en el directorio", idSucursal));
            return unaSucursal.getDatos();
        }
        else{
//            System.out.println(String.format("La sucursal %s no se encuentra en el directorio", idSucursal));
            return null;
        }
    }
    
    public Sucursal buscarSucursalPorDepartamento(Comparable stringCompuesto) {
        TElementoAB<Sucursal> unaSucursal = arbolSucursalesPorDepartamento.buscar(stringCompuesto);
        
        if(unaSucursal != null){
//            System.out.println(String.format("La sucursal %s ya se encuentra en el directorio", idSucursal));
            return unaSucursal.getDatos();
        }
        else{
//            System.out.println(String.format("La sucursal %s no se encuentra en el directorio", idSucursal));
            return null;
        }
    }
    
    private Producto buscarProducto(Comparable idProducto) {
        TElementoAB<Producto> unProducto = arbolProductosBase.buscar(idProducto);
        
        if(unProducto != null){
            return unProducto.getDatos();
        }
        else{
            return null;
        }
    }

    @Override
    public void insertarSucursal(Sucursal sucursal) {
        TElementoAB<Sucursal> unaSucursal = new TElementoAB<>(sucursal.getId(), sucursal);
        Sucursal s = buscarSucursal(sucursal.getId());
        
        if(s == null){
            arbolSucursales.insertar(unaSucursal);      //Inserta unicamente si no hay sucursales repetidas
        }
    }
    
    public void insertarSucursalPorDepartamento(Sucursal sucursal) {
        String stringCompuesto;
        stringCompuesto = sucursal.getDepartamento() + ", " + sucursal.getCiudad() + ", " 
                + sucursal.getCodigoPostal() + ", " + sucursal.getId();
        TElementoAB<Sucursal> unaSucursal = new TElementoAB<>(stringCompuesto, sucursal);
        Sucursal s = buscarSucursalPorDepartamento(stringCompuesto);        
        
        if(s == null){
            arbolSucursalesPorDepartamento.insertar(unaSucursal);      //Inserta unicamente si no hay sucursales repetidas
        }
    }

    @Override
    public void insertarSucursalesArchivo(String nombreArchivo) {
        System.out.println("Espere mientras se carga el archivo de sucursales...");
        ArrayList<String> sucursales = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        
//        String stringCompuesto;
        
        for(String linea : sucursales){
            String[] result = linea.split(",");
            int tel = Integer.parseInt(result[1]);
            int cp = Integer.parseInt(result[3]);
//            stringCompuesto = result[5] + ", " + result[4] + ", " + result[3];
            Sucursal sucursal = new Sucursal(result[0], tel, result[2], cp, result[4], result[5]);
            insertarSucursal(sucursal);
            insertarSucursalPorDepartamento(sucursal);
        }
        arbolSucursales.inOrden();
    }

    
    
    public void insertarProducto(Producto producto) {
        TElementoAB<Producto> productoId = new TElementoAB<>(producto.getEtiqueta(), producto);
//        TElementoAB<Producto> productoNombre = new TElementoAB<>(producto.getNombre(), producto);
        Producto p = buscarProducto(producto.getEtiqueta());
        
        if(p == null){
            arbolProductosBase.insertar(productoId);     
//            arbolProductosEmpresa.insertar(productoId);     //Si quiero agregar los productos ordenados por nombre a la empresa misma
        }
    }
    
    
    public void insertarProductosArchivo(String nombreArchivo) {
        System.out.println("Espere mientras se carga el archivo de productos...");
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
            elem = new TElementoAB<>(datos[0], producto);          
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
//        arbolProductosBase.inOrden();
    }

   
    
    public void agregarStockEmpresa(String nombreArchivo){
        System.out.println("Espere mientras se carga el archivo de stock a la empresa...");
        ArrayList<String> stockArchivo = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        
        Producto producto;        
        
        //Se crean sets para que no se repitan los valores y para que no de StackOverflow
        Set s = new HashSet(stockArchivo.size());
        s.addAll(stockArchivo);
        List<String> shuffledList = new ArrayList(s.size());
        shuffledList.addAll(s);
        
        for(String linea : shuffledList){
            String[] result = linea.split(",");
            Comparable idProducto = result[0];
            int stock = Integer.parseInt(result[1]);
            
            producto = buscarProducto(idProducto);
            
            if(producto != null){
                
//                producto.agregarStock(stock);
                Producto prodAInsertar = new Producto(idProducto, producto.getNombre());
                TElementoAB<Producto> elemProducto = new TElementoAB<>(prodAInsertar.getEtiqueta(), prodAInsertar);
                arbolProductosEmpresa.insertar(elemProducto);
                TElementoAB<Producto> productoPorNombre = new TElementoAB<>(prodAInsertar.getNombre(), prodAInsertar);
                arbolProductosEmpresaPorNombre.insertar(productoPorNombre);
                prodAInsertar.agregarStock(stock);                
//                System.out.println(String.format("- El producto %s tiene %d de stock.", prodAInsertar.getEtiqueta(), prodAInsertar.getStock()));
            }           
        }          
    }
    
    
     public Boolean restarStockEmpresa(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> prod = arbolProductosEmpresa.buscar(clave);
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
     
     
    public boolean eliminarProducto(Comparable clave) {
        arbolProductosEmpresa.eliminar(clave);
        System.out.println(String.format("El producto %s ha sido eliminado de la empresa %s.", clave, nombreEmpresa));
        return true;
    }
    
    
    public void buscarProductoEmpresa(Comparable clave){
        TElementoAB<Producto> producto = arbolProductosEmpresa.buscar(clave);
        
        if(producto != null){
            System.out.println(String.format("El producto %s se encuentra en la empresa con un stock de %d.", 
                    clave, producto.getDatos().getStock()));
        }
        else{
            System.out.println("El producto no se encuentra en la empresa.");
        }
    }
    
    
    public void listarPorNombre(){
        if(!arbolProductosEmpresaPorNombre.esVacio()){
            System.out.println(String.format("\nLos productos existentes en la empresa \"%s\" son:", nombreEmpresa));  
            listarPorNombreImplementacion(arbolProductosEmpresaPorNombre.getRaiz());               
        }
        else
            System.out.println("La empresa esta vacia");
    }
    
    private void listarPorNombreImplementacion(TElementoAB<Producto> producto){
        if(producto.getHijoIzq() != null){
            listarPorNombreImplementacion(producto.getHijoIzq());
        }
        
        System.out.println(String.format("- %s; stock %d", producto.getEtiqueta(), producto.getDatos().getStock()));
        
        if(producto.getHijoDer() != null)
            listarPorNombreImplementacion(producto.getHijoDer());    
    }
    
    
    
    public void agregarStockArchivo(String nombreArchivo){
        System.out.println("Espere mientras se carga el archivo de stock...");
        ArrayList<String> stockArchivo = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        
        Producto producto;
        
        
        //Se crean sets para que no se repitan los valores y para que no de StackOverflow
        Set s = new HashSet(stockArchivo.size());
        s.addAll(stockArchivo);
        List<String> shuffledList = new ArrayList(s.size());
        shuffledList.addAll(s);
        
        for(String linea : shuffledList){
            String[] result = linea.split(",");
            Comparable idSucursal = result[0];
            Comparable idProducto = result[1];
            int stock = Integer.parseInt(result[2]);
            Sucursal sucursal = buscarSucursal(idSucursal);
            
            if(sucursal != null){
                producto = buscarProducto(idProducto);
                Producto prodAInsertar = new Producto(idProducto, producto.getNombre());
//                Producto productoPorNombre = new Producto(producto.getNombre(), producto.getStock().toString());
                sucursal.insertarProducto(prodAInsertar);
                sucursal.insertarProductoPorNombre(prodAInsertar);
                sucursal.agregarStock(idProducto, stock);
//                sucursal.agregarStockPorNombre(prodAInsertar.getNombre(), stock);
            }
        }        
    }
    
    public void restarStockSucursal(Comparable idSucursal, Comparable idProducto, Integer cantidad){
        Sucursal sucursal = buscarSucursal(idSucursal);
            
        if(sucursal != null){
            Boolean hayStock = sucursal.restarStock(idProducto, cantidad);
            
            if(!hayStock){
                buscarProductoEnSucursalesStock(idProducto, cantidad);
            }
        }
    }
    
    public void buscarProductoEnSucursalesStock(Comparable claveProducto, Integer stock){
        if(!arbolSucursales.esVacio())
            buscarProductoEnSucursalesStockImplementacion(arbolSucursales.getRaiz(), claveProducto, stock);
        else
            System.out.println("La lista de sucursales esta vacia");
    }
    
    private void buscarProductoEnSucursalesStockImplementacion(TElementoAB<Sucursal> elemSucursal, Comparable claveProducto, Integer stock){
        if(elemSucursal.getHijoIzq() != null){
            buscarProductoEnSucursalesStockImplementacion(elemSucursal.getHijoIzq(), claveProducto, stock);
        }
        
        elemSucursal.getDatos().buscarPorCodigoStock(claveProducto, stock);        
        
        if(elemSucursal.getHijoDer() != null)
            buscarProductoEnSucursalesStockImplementacion(elemSucursal.getHijoDer(), claveProducto, stock);        
    }
    
//    public void buscarProductoEnEmpresa(Comparable claveProducto){
//        if(!arbolSucursales.esVacio())
//            buscarProductoEnEmpresaImplementacion(arbolProductosEmpresa.getRaiz(), claveProducto);
//        else
//            System.out.println("La lista de productos esta vacia");
//    }
//    
//    private void buscarProductoEnEmpresaImplementacion(TElementoAB<Producto> producto, Comparable claveProducto){
//        if(producto.getHijoIzq() != null){
//            buscarProductoEnEmpresaImplementacion(producto.getHijoIzq(), claveProducto);
//        }
//        
//        producto.getDatos().(claveProducto);        
//        
//        if(producto.getHijoDer() != null)
//            buscarProductoEnEmpresaImplementacion(producto.getHijoDer(), claveProducto);        
//    }
    
//    public void listarProductosSucursal(Comparable idSucursal){
//        Sucursal sucursal = buscarSucursal(idSucursal);
//        System.out.println("Los productos existentes en la sucursal " + idSucursal + " son:");
//        sucursal.imprimirProductos();
//    }
    
//    public void listarProductosSucursal(Comparable idSucursal){
//        Sucursal sucursal = buscarSucursal(idSucursal);
//        System.out.println("Los productos existentes en la sucursal " + idSucursal + " son:");
//        sucursal.buscarPorNombre();
////        sucursal.imprimirProductos();
//    }
    
    public void buscarProductoEnSucursales(Comparable claveProducto){
        if(!arbolSucursales.esVacio())
            buscarProductoEnSucursalesImplementacion(arbolSucursales.getRaiz(), claveProducto);
        else
            System.out.println("La lista de sucursales esta vacia");
    }
    
    private void buscarProductoEnSucursalesImplementacion(TElementoAB<Sucursal> elemSucursal, Comparable claveProducto){
        if(elemSucursal.getHijoIzq() != null){
            buscarProductoEnSucursalesImplementacion(elemSucursal.getHijoIzq(), claveProducto);
        }
        
        elemSucursal.getDatos().buscarPorCodigo(claveProducto);        
        
        if(elemSucursal.getHijoDer() != null)
            buscarProductoEnSucursalesImplementacion(elemSucursal.getHijoDer(), claveProducto);        
    }
    
    
    public void eliminarProductoDeTodasLasSucursales(Comparable claveProducto){
        if(!arbolSucursales.esVacio())
            eliminarProductoDeTodasLasSucursalesImplementacion(arbolSucursales.getRaiz(), claveProducto);
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
    
    public void listarProductosPorNombre(Comparable idSucursal){
        Sucursal sucursal = buscarSucursal(idSucursal);
        sucursal.listarPorNombre(false);
    }
    
    public void listarPorDepartamento(){
        if(!arbolSucursalesPorDepartamento.esVacio())
            listarPorDepartamentoImplementacion(arbolSucursalesPorDepartamento.getRaiz());
        else
            System.out.println("La lista de sucursales esta vacia");
    }
    
    private void listarPorDepartamentoImplementacion(TElementoAB<Sucursal> elemSucursal){
        if(elemSucursal.getHijoIzq() != null){
            listarPorDepartamentoImplementacion(elemSucursal.getHijoIzq());
        }
        
        elemSucursal.getDatos().listarPorNombre(true);
        
        if(elemSucursal.getHijoDer() != null)
            listarPorDepartamentoImplementacion(elemSucursal.getHijoDer());       
        
    }
    
//    public void listarPorDepartamentoCiudad(){
//        if(!arbolSucursalesPorDepartamento.esVacio())
//            listarPorDepartamentoCiudadImplementacion(arbolSucursalesPorDepartamento.getRaiz());
//        else
//            System.out.println("La lista de sucursales esta vacia");
//    }
//    
//    private Boolean listarPorDepartamentoCiudadImplementacion(TElementoAB<Sucursal> elemSucursal){
//        Comparable ciudadSucursal = elemSucursal.getDatos().getCiudad();
//        Comparable ciudadSucursalIzq = elemSucursal.getHijoIzq().getDatos().getCiudad();
//        Comparable ciudadSucursalDer = elemSucursal.getHijoDer().getDatos().getCiudad();
//        
//        if(elemSucursal.getHijoIzq() != null){
//            listarPorDepartamentoCiudadImplementacion(elemSucursal.getHijoIzq());
//        }
//        
////        elemSucursal.getDatos().eliminarProducto(claveProducto);
//        
//        if(elemSucursal.getHijoDer() != null)
//            listarPorDepartamentoCiudadImplementacion(elemSucursal.getHijoDer());     
//        
//        
//        if (ciudadSucursal.compareTo(ciudadSucursalIzq) == 0) {
//            return false;
//        } 
//        else if (ciudadSucursal.compareTo(ciudadSucursalIzq) < 0) {
//            if (elemSucursal.getHijoIzq() != null) {
//                return elemSucursal.getHijoIzq().insertar(elemSucursal);
//            } 
//            else {
//                this.elemSucursal.getHijoIzq() = elemSucursal;
//                return true;
//            }
//        } 
//        else {
//            if (elemSucursal.getHijoDer() != null) {
//                return elemSucursal.getHijoDer().insertar(elemSucursal);
//            } 
//            else {
//                this.elemSucursal.getHijoDer() = elemSucursal;
//                return true;
//            }
//        }
//        
//        if (arbolSucursalesPorDepartamento.esVacio()) {
//            return null;
//        } else if (unaEtiqueta.compareTo(etiqueta) == 0) {
////            System.out.println("El contador imprime: " + contador);
//            return this;
//        } else if (unaEtiqueta.compareTo(etiqueta) < 0 && hijoIzq != null) {
//            return hijoIzq.buscar(unaEtiqueta);
//
//        } else if (unaEtiqueta.compareTo(etiqueta) > 0 && hijoDer != null) {
//            return hijoDer.buscar(unaEtiqueta);
//        } else {
//            return null;
//        }
//        
//    }
    
}
