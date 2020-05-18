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
        int contador = 0;

        //Se cambia el metodo de leerArchivo a un ArrayList para que no de StackOverflow
        ArrayList<String> lineas = ManejadorArchivosGenerico.leerArchivo("src/casodeestudio/productos.txt");

        Almacen almacen = new Almacen("GEANT", 213244, "kjhkh 1234", "Malvin", "Montevideo");
        Producto producto;

        //Se crean sets para que no se repitan los valores y para que no de StackOverflow
        Set s = new HashSet(lineas.size());
        s.addAll(lineas);
        List<String> shuffledList = new ArrayList(s.size());
        shuffledList.addAll(s);

        for (String data1 : shuffledList) {
            String[] datos = data1.split("\"");
            datos[0] = datos[0].replace(",", "");
            datos[1] = datos[1].replace("\"", "");
            System.out.println(++contador + " " + datos[1]);
            datos[2] = datos[2].replace(",", "");
            producto = new Producto(datos[0], datos[1]);
            producto.setPrecio(Float.parseFloat(datos[2]));
//           producto.setStock(Integer.parseInt(datos[3]));
//            System.out.println(String.format(++contador + " El codigo es: %s, nombre: %s y precio: $%s",
//                    producto.getEtiqueta().toString(), producto.getNombre(), producto.getPrecio().toString()));
            almacen.insertarProducto(producto);
        }

    }

}
