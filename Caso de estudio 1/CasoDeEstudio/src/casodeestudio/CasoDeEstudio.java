/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casodeestudio;

/**
 *
 * @author vero
 */
public class CasoDeEstudio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] lineas = ManejadorArchivosGenerico.leerArchivo("src/casodeestudio/productos.txt");

        Almacen almacen = new Almacen("GEANT", 213244, "kjhkh 1234", "Malvin", "Montevideo");
        Producto producto;
//        Comparable codigo;
//        Double precio;

//        for (String linea : lineas) {
//            codigo = linea.split(",", 2)[0];
//            precio = Double.parseDouble(linea.substring(1 + linea.lastIndexOf(",")));
//            //           String[] datos = data1.split(",");
////            producto = new Producto(datos[0],datos[1]);
////            producto.setPrecio(Double.parseDouble(datos[2]));
//            //           producto.setStock(Integer.parseInt(datos[3]));
////            almacen.insertarProducto(producto);
//        }
        for (String data1 : lineas) {
            String[] datos = data1.split("\"");
            datos[0] = datos[0].replace(",", "");
            datos[1] = datos[1].replace("\"", "");
            System.out.println(datos[1]);
            datos[2] = datos[2].replace(",", "");
            producto = new Producto(datos[0], datos[1]);
            producto.setPrecio(Float.parseFloat(datos[2]));
//           producto.setStock(Integer.parseInt(datos[3]));
//           System.out.println(String.format("El producto es codigo: %s nombre %s y precio %s", 
//            producto.getEtiqueta().toString(), producto.getNombre(), producto.getPrecio().toString()));
            almacen.insertarProducto(producto);
        }

    }

}
