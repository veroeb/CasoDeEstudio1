/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casodeestudio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author vero
 */
public class CasoDeEstudio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        int contador = 0;
//        
//        TArbolBB<Producto> arbol = new TArbolBB<>();
//        TElementoAB<Producto> elem;
//
//        //Se cambia el metodo de leerArchivo a un ArrayList para que no de StackOverflow
//        ArrayList<String> lineas = ManejadorArchivosGenerico.leerArchivo("src/casodeestudio/productos.txt");
////
////        Sucursal sucursal = new Sucursal("GEANT", 213244, "kjhkh 1234", 12321, "Malvin", "Montevideo");
////        Producto producto;
////
//        //Se crean sets para que no se repitan los valores y para que no de StackOverflow
//        Set s = new HashSet(lineas.size());
//        s.addAll(lineas);
//        List<String> shuffledList = new ArrayList(s.size());
//        shuffledList.addAll(s);
//
//        for (String data1 : shuffledList) {
//            String[] datos = data1.split("\"");
////            datos[0] = datos[0].replace(",", "");
////            datos[1] = datos[1].replace("\"", "");
////            System.out.println(++contador + " " + datos[1]);
////            datos[2] = datos[2].replace(",", "");
//            producto = new Producto(datos[0], datos[1]);            
//            producto.setPrecio(Float.parseFloat(datos[2]));
//            elem = new TElementoAB<>(datos[0], producto);           //no se si dejarlo asi o agregarle el precio como dato
////            elem = new TElementoAB<>(datos[0], datos[1] + ", " + datos[2]);
//            arbol.insertar(elem);
////           producto.setStock(Integer.parseInt(datos[3]));
////            System.out.println(String.format(++contador + " El codigo es: %s, nombre: %s y precio: $%s",
////                    producto.getEtiqueta().toString(), producto.getNombre(), producto.getPrecio().toString()));
//            sucursal.insertarProducto(producto);
//        }
//        
//        System.out.println(arbol.inOrden());
//        System.out.print("\nLa altura del arbol es : ");
//        System.out.println(arbol.altura());
        
//        System.out.print("\nExiste 7621915358? : ");
//        System.out.println(arbol.buscar(7621915358l));
//
//        System.out.print("\nLas hojas son: ");
//        System.out.println(arbol.listarHojas().imprimir(", "));
//        
//        System.out.print("\nLa raiz: ");
//        System.out.println(arbol.getRaiz().getEtiqueta());


//        ArrayList<String> lineasSucursal = ManejadorArchivosGenerico.leerArchivo("src/casodeestudio/sucursales.txt");
//        Empresa empresa = new Empresa("Geant");
//        TArbolBB<Sucursal> listaSucursales = new TArbolBB<>();
//        Sucursal sucursalaAgregar;
//        
//        for(String datosSucursal : lineasSucursal){
//            String[] str = datosSucursal.split(",");
//            sucursalaAgregar = new Sucursal(str[0], Integer.parseInt(str[1]), str[2], Integer.parseInt(str[3]), str[4], str[5]);
////            TElementoAB<Sucursal> unaSucursal = new TElementoAB<>(str[0], sucursalaAgregar);
////            listaSucursales.insertar(unaSucursal);
//            empresa.insertarSucursal(sucursalaAgregar);
//        }
        
        Empresa empresa = new Empresa("Geant");
        empresa.insertarSucursalesArchivo("src/casodeestudio/sucursales.txt");
        empresa.insertarProductosArchivo("src/casodeestudio/productos.txt");
//        empresa.getSucursales();
//        empresa.getProductos();
        empresa.agregarStockArchivo("src/casodeestudio/stock.txt");
//        empresa.restarStockSucursal("Local 122", "1403796890", 81);
//        empresa.eliminarProductoDeTodasLasSucursales("1453060375");
//        empresa.buscarProductoEnSucursales("1453060782");
//        empresa.listarProductosPorNombre("Local 122");
        empresa.listarPorDepartamento();

    }

}
