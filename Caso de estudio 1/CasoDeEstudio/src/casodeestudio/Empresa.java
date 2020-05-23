package casodeestudio;

import java.util.Random;

/**
 *
 * @author vero
 */
public class Empresa implements IEmpresa{
   
    private String nombreEmpresa;
    private final TArbolBB<Sucursal> arbolSucursales;
    private final TArbolBB<Sucursal> arbolSucursalesPorDepartamento;
    private final TArbolBB<Producto> arbolProductosBase;
    private final TArbolBB<Producto> arbolProductosEmpresa;
    private final TArbolBB<Producto> arbolProductosEmpresaPorNombre;
    
    public Empresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
        this.arbolSucursales = new TArbolBB<>();
        this.arbolProductosBase = new TArbolBB<>();
        this.arbolProductosEmpresa = new TArbolBB<>();
        this.arbolProductosEmpresaPorNombre = new TArbolBB<>();
        this.arbolSucursalesPorDepartamento = new TArbolBB<>();
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
    public TArbolBB<Sucursal> getSucursales() {
        System.out.println("Las sucursales existentes son: " + arbolSucursales.inOrden());
        return arbolSucursales;
    }
    
  
    public TArbolBB<Producto> getProductos() {
        System.out.println("Los productos existentes son: " + arbolProductosBase.inOrden());
        return arbolProductosBase;
    }  
    
    
    private Producto buscarProducto(Comparable idProducto) {
        TElementoAB<Producto> unProducto = arbolProductosBase.buscar(idProducto);
        
        if(unProducto != null){
            return unProducto.getDatos();
        }
        else{
            return null;
        }
    }
    
    @Override
    public void insertarProducto(Producto producto) {
        TElementoAB<Producto> productoId = new TElementoAB<>(producto.getEtiqueta(), producto);
        Producto p = buscarProducto(producto.getEtiqueta());
        
        if(p == null){
            arbolProductosBase.insertar(productoId);     
        }
    }
     
    @Override
    public void insertarProductosArchivo(String nombreArchivo) {
        System.out.println("Espere mientras se carga el archivo de productos...");
    
        String[] lineas = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        Random random = new Random();

        for(int i = 0; i < lineas.length; i++){
            int randomIndice = random.nextInt(lineas.length);
            String resultado = lineas[randomIndice];
            lineas[randomIndice] = lineas[i];
            lineas[i] = resultado;
        }

        for(String linea : lineas){
            String[] datos = linea.split("\"");
            datos[0] = datos[0].replace(",", "");
            datos[1] = datos[1].replace("\"", "");
            datos[2] = datos[2].replace(",", "");
            Producto producto = new Producto(datos[0], datos[1]);            
            producto.setPrecio(Float.parseFloat(datos[2]));       
            insertarProducto(producto);
        }
    }   
    
    @Override
    public void agregarStockEmpresa(String nombreArchivo){
        System.out.println("Espere mientras se carga el archivo de stock a la empresa...");
    
        String[] lineas = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        Random random = new Random();

        for(int i = 0; i < lineas.length; i++){
            int randomIndice = random.nextInt(lineas.length);
            String resultado = lineas[randomIndice];
            lineas[randomIndice] = lineas[i];
            lineas[i] = resultado;
        }

        for(String linea : lineas){
            String[] result = linea.split(",");
            Comparable idProducto = result[0];
            int stock = Integer.parseInt(result[1]);

            Producto producto = buscarProducto(idProducto);

            if(producto != null){
                Producto prodAInsertar = new Producto(idProducto, producto.getNombre());
                TElementoAB<Producto> elemProducto = new TElementoAB<>(prodAInsertar.getEtiqueta(), prodAInsertar);
                arbolProductosEmpresa.insertar(elemProducto);
                TElementoAB<Producto> productoPorNombre = new TElementoAB<>(prodAInsertar.getNombre(), prodAInsertar);
                arbolProductosEmpresaPorNombre.insertar(productoPorNombre);
                prodAInsertar.agregarStock(stock); 
            }       
        }      
    }
    
    @Override
    public Boolean restarStockEmpresa(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> prod = arbolProductosEmpresa.buscar(clave);
        if(prod != null){
            int stockFinal = prod.getDatos().restarStock(cantidad);
            if(stockFinal != -1)
                return true;
            else
                return false;
        }
        else{
            System.out.println("Ese producto no se encuentra en la empresa.");
            return false;
        }
    }     
     
    @Override
    public boolean eliminarProducto(Comparable clave) {
        TElementoAB<Producto> prod = arbolProductosEmpresa.buscar(clave);
        if(prod != null){
            arbolProductosEmpresa.eliminar(clave);
            System.out.println(String.format("El producto %s ha sido eliminado de la empresa %s.", clave, nombreEmpresa));
            return true;
        }
        else{
            System.out.println("El producto que desea eliminar, no se encuentra en la empresa.");
            return false;
        }
    }    
    
    @Override
    public void buscarProductoEmpresa(Comparable clave){
        TElementoAB<Producto> producto = arbolProductosEmpresa.buscar(clave);
        
        if(producto != null){
            System.out.println(String.format("El producto %s se encuentra en la empresa con un stock de %d.", 
                    clave, producto.getDatos().getStock()));
        }
        else{
            System.out.println("El producto no se encuentra en la empresa.");
        }
    }    
    
    @Override
    public void listarPorNombre(){
        if(!arbolProductosEmpresaPorNombre.esVacio()){
            System.out.println(String.format("\nLos productos existentes en la empresa \"%s\" son:", nombreEmpresa));  
            listarPorNombreImplementacion(arbolProductosEmpresaPorNombre.getRaiz());               
        }
        else
            System.out.println("La empresa esta vacia");
    }
    
    private void listarPorNombreImplementacion(TElementoAB<Producto> producto){
        if(producto.getHijoIzq() != null){
            listarPorNombreImplementacion(producto.getHijoIzq());
        }
        
        System.out.println(String.format("- %s; stock %d", producto.getEtiqueta(), producto.getDatos().getStock()));
        
        if(producto.getHijoDer() != null)
            listarPorNombreImplementacion(producto.getHijoDer());    
    }
    
    
    //**************************************************************************************************************
    
    
    /**
     * Metodos de sucursales
     */
    

    @Override
    public Sucursal buscarSucursal(Comparable idSucursal) {
        TElementoAB<Sucursal> unaSucursal = arbolSucursales.buscar(idSucursal);
        
        if(unaSucursal != null){
            return unaSucursal.getDatos();
        }
        else{
            return null;
        }
    }
    
    @Override
    public Sucursal buscarSucursalPorDepartamento(Comparable stringCompuesto) {
        TElementoAB<Sucursal> unaSucursal = arbolSucursalesPorDepartamento.buscar(stringCompuesto);
        
        if(unaSucursal != null){
            return unaSucursal.getDatos();
        }
        else{
            return null;
        }
    }    

    @Override
    public void insertarSucursal(Sucursal sucursal) {
        TElementoAB<Sucursal> unaSucursal = new TElementoAB<>(sucursal.getId(), sucursal);
        Sucursal s = buscarSucursal(sucursal.getId());
        
        if(s == null){
            arbolSucursales.insertar(unaSucursal);      //Inserta unicamente si no hay sucursales repetidas
        }
    }
    
    @Override
    public void insertarSucursalPorDepartamento(Sucursal sucursal) {
        String stringCompuesto;
        stringCompuesto = sucursal.getDepartamento() + ", " + sucursal.getCiudad() + ", " 
                + sucursal.getCodigoPostal() + ", " + sucursal.getId();
        TElementoAB<Sucursal> unaSucursal = new TElementoAB<>(stringCompuesto, sucursal);
        Sucursal s = buscarSucursalPorDepartamento(stringCompuesto);        
        
        if(s == null){
            arbolSucursalesPorDepartamento.insertar(unaSucursal);      //Inserta unicamente si no hay sucursales repetidas
        }
    }

    @Override
    public void insertarSucursalesArchivo(String nombreArchivo) {
        System.out.println("Espere mientras se carga el archivo de sucursales...");
        String[] sucursales = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        
        for(String linea : sucursales){
            String[] result = linea.split(",");
            int tel = Integer.parseInt(result[1]);
            int cp = Integer.parseInt(result[3]);
            Sucursal sucursal = new Sucursal(result[0], tel, result[2], cp, result[4], result[5]);
            insertarSucursal(sucursal);
            insertarSucursalPorDepartamento(sucursal);
        }
    }
    
    @Override
    public void agregarStockArchivo(String nombreArchivo){
        System.out.println("Espere mientras se carga el archivo de stock...");
    
        String[] lineas = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        Random random = new Random();

        for(int i = 0; i < lineas.length; i++){
            int randomIndice = random.nextInt(lineas.length);
            String resultado = lineas[randomIndice];
            lineas[randomIndice] = lineas[i];
            lineas[i] = resultado;
        }

        for(String linea : lineas){
            String[] result = linea.split(",");
            Comparable idSucursal = result[0];
            Comparable idProducto = result[1];
            int stock = Integer.parseInt(result[2]);
            Sucursal sucursal = buscarSucursal(idSucursal);

            if(sucursal != null){
                Producto producto = buscarProducto(idProducto);
                Producto prodAInsertar = new Producto(idProducto, producto.getNombre());
                sucursal.insertarProducto(prodAInsertar);
                sucursal.insertarProductoPorNombre(prodAInsertar);
                sucursal.agregarStock(idProducto, stock);
            }   
        }
    }
    
    @Override
    public void restarStockSucursal(Comparable idSucursal, Comparable idProducto, Integer cantidad){
        Sucursal sucursal = buscarSucursal(idSucursal);
            
        if(sucursal != null){
            Boolean hayStock = sucursal.restarStock(idProducto, cantidad);
            
            if(!hayStock){
                buscarProductoEnSucursalesStock(idProducto, cantidad);
            }
        }
    }
    
    @Override
    public void buscarProductoEnSucursalesStock(Comparable claveProducto, Integer stock){
        if(!arbolSucursales.esVacio())
            buscarProductoEnSucursalesStockImplementacion(arbolSucursales.getRaiz(), claveProducto, stock);
        else
            System.out.println("La lista de sucursales esta vacia");
    }
    
    private void buscarProductoEnSucursalesStockImplementacion(TElementoAB<Sucursal> elemSucursal, Comparable claveProducto, Integer stock){
        if(elemSucursal.getHijoIzq() != null){
            buscarProductoEnSucursalesStockImplementacion(elemSucursal.getHijoIzq(), claveProducto, stock);
        }
        
        elemSucursal.getDatos().buscarPorCodigoStock(claveProducto, stock);        
        
        if(elemSucursal.getHijoDer() != null)
            buscarProductoEnSucursalesStockImplementacion(elemSucursal.getHijoDer(), claveProducto, stock);        
    }
    
    @Override    
    public void buscarProductoEnSucursales(Comparable claveProducto){
        if(!arbolSucursales.esVacio())
            buscarProductoEnSucursalesImplementacion(arbolSucursales.getRaiz(), claveProducto);
        else
            System.out.println("La lista de sucursales esta vacia");
    }
    
    private void buscarProductoEnSucursalesImplementacion(TElementoAB<Sucursal> elemSucursal, Comparable claveProducto){
        if(elemSucursal.getHijoIzq() != null){
            buscarProductoEnSucursalesImplementacion(elemSucursal.getHijoIzq(), claveProducto);
        }
        
        elemSucursal.getDatos().buscarPorCodigo(claveProducto);        
        
        if(elemSucursal.getHijoDer() != null)
            buscarProductoEnSucursalesImplementacion(elemSucursal.getHijoDer(), claveProducto);        
    }    
    
    @Override
    public void eliminarProductoDeTodasLasSucursales(Comparable claveProducto){
        if(!arbolSucursales.esVacio())
            eliminarProductoDeTodasLasSucursalesImplementacion(arbolSucursales.getRaiz(), claveProducto);
        else
            System.out.println("La lista de sucursales esta vacia");
    }
    
    private void eliminarProductoDeTodasLasSucursalesImplementacion(TElementoAB<Sucursal> elemSucursal, Comparable claveProducto){
        if(elemSucursal.getHijoIzq() != null){
            eliminarProductoDeTodasLasSucursalesImplementacion(elemSucursal.getHijoIzq(), claveProducto);
        }
        
        elemSucursal.getDatos().eliminarProducto(claveProducto);
        
        if(elemSucursal.getHijoDer() != null)
            eliminarProductoDeTodasLasSucursalesImplementacion(elemSucursal.getHijoDer(), claveProducto);       
        
    }
    
    @Override
    public void listarProductosPorNombre(Comparable idSucursal){
        Sucursal sucursal = buscarSucursal(idSucursal);
        sucursal.listarPorNombre(false);
    }
    
    @Override
    public void listarPorDepartamento(){
        if(!arbolSucursalesPorDepartamento.esVacio())
            listarPorDepartamentoImplementacion(arbolSucursalesPorDepartamento.getRaiz());
        else
            System.out.println("La lista de sucursales esta vacia");
    }
    
    private void listarPorDepartamentoImplementacion(TElementoAB<Sucursal> elemSucursal){
        if(elemSucursal.getHijoIzq() != null){
            listarPorDepartamentoImplementacion(elemSucursal.getHijoIzq());
        }
        
        elemSucursal.getDatos().listarPorNombre(true);
        
        if(elemSucursal.getHijoDer() != null)
            listarPorDepartamentoImplementacion(elemSucursal.getHijoDer());             
    }    
}
