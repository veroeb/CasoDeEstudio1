/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casodeestudio;

import java.util.ArrayList;

/**
 *
 * @author vero
 */
public class Empresa implements IEmpresa{
   
    private String nombreEmpresa;
    private TArbolBB<Sucursal> listaSucursales;
    
    public Empresa(String nombreEmpresa, TArbolBB<Sucursal> listaSucursales) {
        this.nombreEmpresa = nombreEmpresa;
        this.listaSucursales = new TArbolBB<>();
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
    public Sucursal buscarSucursal(Comparable nombreSucursal) {
        TElementoAB<Sucursal> unaSucursal = listaSucursales.buscar(nombreSucursal);
        
        if(unaSucursal != null){
            System.out.println(String.format("La sucursal %s ya se encuentra en el directorio", nombreSucursal));
            return unaSucursal.getDatos();
        }
        else{
            System.out.println(String.format("La sucursal %s no se encuentra en el directorio", nombreSucursal));
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
            Sucursal sucursal = new Sucursal(result[0], Integer.parseInt(result[1]), result[2], Integer.parseInt(result[3]), result[4], result[5]);
            insertarSucursal(sucursal);
        }
        listaSucursales.inOrden();
    }

    @Override
    public TArbolBB<Sucursal> getSucursales() {
        System.out.println("Las sucursales existentes son: " + listaSucursales.inOrden());
        return listaSucursales;
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
    
}
