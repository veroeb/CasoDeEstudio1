package casodeestudio;

public class Producto implements IProducto {
    
    private final Comparable etiqueta;
    private String nombre;
    private Float precio;
    private Integer stock;

    public Producto(Comparable etiqueta, String nombre) {
        this.etiqueta = etiqueta;
        this.nombre = nombre;
        this.precio = 0.0f;
        this.stock = 0;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public Float getPrecio() {
        return precio;
    }

    @Override
    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    @Override
    public Integer getStock() {
        return stock;
    }

    public void agergarStock(Integer stock) {
        this.stock += stock;
    }
    
// restarStock devolverá -1 si se pretende extraer más de lo que hay... 
    // y el campo stock quedará inalterado
    public Integer restarStock(Integer stock) {
        if (stock > this.stock) {
            return -1;
        } else {
            setStock(this.stock - stock);
            return this.stock;
        }
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public Comparable getEtiqueta() {
        return this.etiqueta;
    }

}
