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
public interface IEmpresa {
    public String getNombre();
    public void setNombre(String nombre);
    public Sucursal buscarSucursal(Comparable nombreSucursal);
    public void insertarSucursal(Sucursal sucursal);
    public void insertarSucursalesArchivo(String nombreArchivo);
    public TArbolBB<Sucursal> getSucursales();
    public boolean directorioVacio();
}
