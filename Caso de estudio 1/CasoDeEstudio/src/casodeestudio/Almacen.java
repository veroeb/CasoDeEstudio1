package casodeestudio;

public class Almacen implements IAlmacen {

    private final String nombre;
    private final String direccion;
    private final Integer telefono;
    private final String barrio;
    private final String ciudad;

    private final TArbolBB<Producto> productos;

    public Almacen(String nombre, Integer telefono, String direccion, String barrio, String ciudad) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.barrio = barrio;
        this.ciudad = ciudad;
        this.productos = new TArbolBB<>();
    }

    @Override
    public void insertarProducto(Producto unProducto) {
        TElementoAB<Producto> prod = new TElementoAB(unProducto.getEtiqueta(),unProducto);
        productos.insertar(prod);
    }

  

    @Override
    public String imprimirProductos() {
        return productos.inOrden();
    }

    @Override
    public Boolean agregarStock(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> prod = productos.buscar(clave);
        int cantidadActual = prod.getDatos().getStock();
        prod.getDatos().setStock(cantidad + cantidadActual);
        return true;
    }

    @Override
    public Integer restarStock(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> prod = productos.buscar(clave);
        int cantidadActual = prod.getDatos().getStock();
        if (cantidadActual - cantidad >= 0)
            prod.getDatos().setStock(cantidadActual - cantidad);
        else
            return -1;// No se puede restart stock
        return cantidadActual - cantidad;
    }

    @Override
    public Producto buscarPorCodigo(Comparable clave) {
        TElementoAB<Producto> prod = productos.buscar(clave);
        return prod.getDatos();
    }

    @Override
    public boolean eliminarProducto(Comparable clave) {
        productos.eliminar(clave);
        return true;
    }
   

}
