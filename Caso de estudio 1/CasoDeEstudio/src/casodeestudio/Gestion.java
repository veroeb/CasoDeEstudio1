package casodeestudio;

public class Gestion {

    public static void altaAlmacen(Sucursal sucursal, String linea) {
        if (linea.contains("\"")) {
            String s = linea.substring(linea.indexOf("\"") + 1);
            s = s.substring(0, s.indexOf("\""));
            String name = s.replace(",", ".");
            linea = linea.replace("\"" + s + "\"", name);
        }
        String[] data = linea.split(",");
        while (!Character.isLetterOrDigit(data[1].charAt(0))) {
            data[1] = data[1].substring(1);
        }
        Producto prod = new Producto(data[0].trim(), data[1].trim());
        sucursal.insertarProducto(prod);
//        System.out.println("El valor del stock del producto " + prod.getEtiqueta() + " se ha incrementado $"
//                + Integer.parseInt(data[2]) * Integer.parseInt(data[3]));
//        return Integer.parseInt(data[2]) * Integer.parseInt(data[3]);
    }

    public static void ventaAlmacen(Sucursal sucursal, String producto) {
        String[] data = producto.split(",");
        if (data.length == 2) {
            sucursal.restarStock(data[0].trim(), Integer.parseInt(data[1]));
            Producto prod = sucursal.buscarPorCodigo(data[0].trim());
            if (prod != null) {
                System.out.println("El valor del stock " + prod.getEtiqueta() + " se ha reducido $"
                        + Integer.parseInt(data[1]) * prod.getPrecio());
            } else {
                System.out.println("El producto no esta en el almacen");
            }
        } else {
            System.out.println("Por favor pase argumentos validos.");
        }
    }

    public static void eliminarProd(Sucursal sucursal, String codigo) {
        Producto prod = sucursal.buscarPorCodigo(codigo.trim());
        if (prod != null) {
            double valor = prod.getStock() * prod.getPrecio();
            sucursal.eliminarProducto(codigo.trim());
            System.out.println(
                    "El stock del producto " + prod.getEtiqueta() + " fue eliminado y tenía un valor de: $" + valor);
        } else {
            System.out.println("El producto no esta en el almacen");
        }
    }

//    public static void contarProductos(Sucursal sucursal) {
//        sucursal.imprimirProductos();
//        Lista<Producto> lista = almacen.getListaProductos();
//        Nodo<Producto> actual = lista.getPrimero();
//        int contador = 0;
//        long valorTotal = 0;
//        while (actual != null) {
//            IProducto producto = actual.getDato();
//            if (producto.getStock() > 0) {
//                contador++;
//                valorTotal += (producto.getPrecio() * producto.getStock());
//                String[] res = { producto.getNombre(), String.valueOf(producto.getPrecio()) };
//                ManejadorArchivosGenerico.escribirArchivo("productos.txt", res);
//                System.out.println("El valor total monetario del producto " + producto.getEtiqueta() + " es: $"
//                        + producto.getPrecio() * producto.getStock());
//            }
//            actual = actual.getSiguiente();
//        }
//        System.out.println("El valor total monetario del almacen es de: $" + valorTotal);
//        System.out.println("La cantidad total de productos en el almacen es de: " + contador);
//
//    }

    public static void existenciaDeProducto(Sucursal sucursal, String codigo) {
        Producto prod = sucursal.buscarPorCodigo(codigo);
        if (prod != null) {
            if (prod.getStock() > 0) {
                System.out.println("Hay " + prod.getStock() + " disponibles");
            } else {
                System.out.println("El producto no esta disponible por falta de stock");
            }
        } else {
            System.out.println("El producto no se encuentra en el almacen: " + codigo);
        }
    }
}