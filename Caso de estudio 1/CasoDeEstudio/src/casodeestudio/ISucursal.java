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
public interface ISucursal {            
    public Comparable getId();
    public void setId(Comparable id);
    public int getTelefono();
    public void setTelefono(Integer telefono);
    public String getDireccion();
    public void setDireccion(String direccion);
    public int getCodigoPostal();
    public void setCodigoPostal(int codigoPostal);
    public String getCiudad();
    public void setCiudad(String ciudad);
    public String getDepartamento();
    public void setDepartamento(String departamento);
    
    /**
     * Incorporar un nuevo producto al supermercado.
     *
     * @param unProducto
     */
    public void insertarProducto(Producto unProducto);

    /**
     * Eliminar productos que ya no se venden (por no ser comercializados m�s).
     *
     * @param clave
     * @return
     */
    public boolean eliminarProducto(Comparable clave);

    /**
     * Imprime la lista de productos
     *
     * @return
     */
    public String imprimirProductos();

   
    public Boolean agregarStock(Comparable clave, Integer cantidad);

    /**
     * Simular la venta de un producto (reducir el stock de un producto
     * existente
     *
     * @param clave
     * @param cantidad
     * @return
     */
    public Boolean restarStock(Comparable clave, Integer cantidad);

    /**
     * Dado un código de producto, indicar las existencias del mismo en el
     * almac�n.
     *
     * @param clave
     * @return
     */
    public Producto buscarPorCodigo(Comparable clave);
}
