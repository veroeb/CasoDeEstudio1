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
public class Sucursal implements ISucursal{
    private Comparable id;
    private int telefono;
    private String direccion;
    private int codigoPostal;
    private String ciudad;
    private String departamento;

    private final TArbolBB<Producto> productos;

    public Sucursal(Comparable id, int telefono, String direccion, int codigoPostal, String ciudad, String departamento) {
        this.id = id;
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.productos = new TArbolBB<>();
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
  

    @Override
    public String imprimirProductos() {
//        String str;
//        for(Producto p : productos){
////            str = p.getEtiqueta() + p.getStock().toString();
//            str = String.format("Producto %s, stock %i", p.getEtiqueta(), p.getStock());
//        }
//        return str;
        
        return productos.inOrden();
    }

    @Override
    public Boolean agregarStock(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> prod = productos.buscar(clave);
        
        int stock = prod.getDatos().agregarStock(cantidad);
//        System.out.println(String.format("%d El producto %s tiene %s stock", contador, clave, stock));
        
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
        System.out.println(String.format("El producto %s ha sido eliminado de la sucursal %s", clave, id));
        return true;
    }
}
