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
    private final TArbolBB<Producto> productosPorNombre;

    public Sucursal(Comparable id, int telefono, String direccion, int codigoPostal, String ciudad, String departamento) {
        this.id = id;
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.productos = new TArbolBB<>();
        this.productosPorNombre = new TArbolBB<>();
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
    
    public void insertarProductoPorNombre(Producto unProducto) {
        TElementoAB<Producto> productoNombre = new TElementoAB<>(unProducto.getNombre(), unProducto);
        productosPorNombre.insertar(productoNombre);
    }
    
    @Override
    public Boolean agregarStock(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> prod = productos.buscar(clave);
        if(prod != null){
            prod.getDatos().agregarStock(cantidad);
            return true;            
        }
        else{
            System.out.println("Ese producto no se encuentra en la empresa.");
            return false;
        }
    }
    
    //no se usa por ahora
    public Boolean agregarStockPorNombre(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> prodNombre = productosPorNombre.buscar(clave);
        if(prodNombre != null){
            prodNombre.getDatos().agregarStock(cantidad);
            return true;            
        }
        else{
            System.out.println("Ese producto no se encuentra en la empresa.");
            return false;
        }
    }
    
    @Override
    public Boolean restarStock(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> prod = productos.buscar(clave);
        if(prod != null){
            int stockFinal = prod.getDatos().restarStock(cantidad);
            if(stockFinal == -1){
                return false;                
            }
            else if(stockFinal == 0){
                System.out.println(String.format("\nEl nuevo stock del producto %s en la sucursal %s es de %d.\n", clave, id, stockFinal));
                return true;
            }
            else{
                System.out.println(String.format("\nEl nuevo stock del producto %s en la sucursal %s es de %d.\n", clave, id, stockFinal));
                return true;                
            }
        }
        else{
            System.out.println("Ese producto no se encuentra en la sucursal.");
            return false;
        }
    }
    
    @Override
    public boolean eliminarProducto(Comparable clave) {
        productos.eliminar(clave);
        System.out.println(String.format("El producto %s ha sido eliminado de la sucursal %s", clave, id));
        return true;
    }

    @Override
    public Producto buscarPorCodigo(Comparable clave) {
        TElementoAB<Producto> prod = productos.buscar(clave);
        
        if(prod != null){
            System.out.println(String.format("El producto %s se encuentra en la sucursal %s con stock %d.", 
                    clave, id, prod.getDatos().getStock()));
            return prod.getDatos();            
        }
        else{
            System.out.println(String.format("El producto %s no se encuentra en la sucursal %s.", clave, id));
            return null;
        }
    }
    
    public Producto buscarPorCodigoStock(Comparable clave, Integer stock) {
        TElementoAB<Producto> prod = productos.buscar(clave);
        
        if(prod != null && prod.getDatos().getStock() > stock){
            System.out.println(String.format("El producto %s se encuentra en la sucursal %s con stock %d.", 
                    clave, id, prod.getDatos().getStock()));
            return prod.getDatos();            
        }
        else{
            System.out.println(String.format("El producto %s no tiene stock suficiente en la sucursal %s.", clave, id));
            return null;
        }
    }
    
    public void listarPorNombre(Boolean departamento){
        if(!productosPorNombre.esVacio()){
            if(!departamento){
                System.out.println(String.format("\nLos productos existentes en la sucursal \"%s\" son:", id));                
            }
            else{
                System.out.println(String.format("\nProductos existentes de %s, %s, %d, %s son:", 
                        this.departamento, this.ciudad, this.codigoPostal, this.id));                
            }
            listarPorNombreImplementacion(productosPorNombre.getRaiz());            
        }
        else
            System.out.println("La lista de sucursales esta vacia");
    }
    
    private void listarPorNombreImplementacion(TElementoAB<Producto> producto){
        if(producto.getHijoIzq() != null){
            listarPorNombreImplementacion(producto.getHijoIzq());
        }
        
        System.out.println(String.format("- %s; stock %d", producto.getEtiqueta(), producto.getDatos().getStock()));
        
        if(producto.getHijoDer() != null)
            listarPorNombreImplementacion(producto.getHijoDer());    
    }    
    
    @Override
    public String imprimirProductos() {
        return productos.inOrden();
    }

}
