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
        
        Empresa empresa = new Empresa("Geant");
        empresa.insertarSucursalesArchivo("src/casodeestudio/sucursales.txt");
        empresa.insertarProductosArchivo("src/casodeestudio/productos.txt");
        
        //*********************************************************************
        
        /*
        Metodos para probar de la parte 1 (sobre la empresa)
        */
//        empresa.agregarStockEmpresa("src/casodeestudio/stockEmpresa.txt");
//        empresa.restarStockEmpresa("1403796890", 80);
//        empresa.eliminarProducto("23432");
//        empresa.eliminarProducto("1403796890");
//        empresa.buscarProductoEmpresa("1453060782");
//        empresa.listarPorNombre();


        /*
        Metodos para probar de la parte 2 (sobre las sucursales)
        */
        empresa.getSucursales();
        empresa.agregarStockArchivo("src/casodeestudio/stock.txt");
        empresa.restarStockSucursal("Local 122", "1403796890", 81);
        empresa.restarStockSucursal("Local 122", "1837994021", 10);
        empresa.eliminarProductoDeTodasLasSucursales("1453060375");
        empresa.buscarProductoEnSucursales("1453060782");
        empresa.listarProductosPorNombre("Local 122");
        empresa.listarPorDepartamento();

    }

}
