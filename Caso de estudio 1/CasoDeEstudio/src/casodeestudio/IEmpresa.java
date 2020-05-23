package casodeestudio;

/**
 *
 * @author vero
 */
public interface IEmpresa {
    public String getNombre();
    public void setNombre(String nombre);        
    
    //Metodos de la empresa
    public void insertarProducto(Producto producto);
    public void insertarProductosArchivo(String nombreArchivo);
    public void agregarStockEmpresa(String nombreArchivo);
    public Boolean restarStockEmpresa(Comparable clave, Integer cantidad);
    public boolean eliminarProducto(Comparable clave);
    public void buscarProductoEmpresa(Comparable clave);
    public void listarPorNombre();
    
    //Metodos de sucursales
    public TArbolBB<Sucursal> getSucursales();
    public Sucursal buscarSucursal(Comparable nombreSucursal);
    public Sucursal buscarSucursalPorDepartamento(Comparable stringCompuesto);
    public void insertarSucursal(Sucursal sucursal);
    public void insertarSucursalPorDepartamento(Sucursal sucursal);
    public void insertarSucursalesArchivo(String nombreArchivo);
    public void agregarStockArchivo(String nombreArchivo);
    public void restarStockSucursal(Comparable idSucursal, Comparable idProducto, Integer cantidad);
    public void buscarProductoEnSucursalesStock(Comparable claveProducto, Integer stock);
    public void buscarProductoEnSucursales(Comparable claveProducto);
    public void eliminarProductoDeTodasLasSucursales(Comparable claveProducto);
    public void listarProductosPorNombre(Comparable idSucursal);
    public void listarPorDepartamento();
}
