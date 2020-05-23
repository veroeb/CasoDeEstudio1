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
    public Comparable getEtiqueta() {
        return this.etiqueta;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public void setStock(Integer stock) {
        this.stock = stock;
    }    

    @Override
    public Integer agregarStock(Integer stock) {
        this.stock += stock;
        return this.stock;
    }
    
    @Override
    public Integer restarStock(Integer stock) {
        if (stock > this.stock) {
            System.out.println(String.format("\nNo se puede sacar mas stock del que tiene al producto %s.\n", etiqueta));
            return -1;
        } 
        else if(stock == this.stock){
            setStock(this.stock - stock);
            System.out.println(String.format("\nEl producto %s se quedó sin stock.\n", etiqueta));
            return 0;          //Al ser el mismo monto, devuelve 0
        }
        else {
            setStock(this.stock - stock);
//            System.out.println(String.format("El nuevo stock del producto %s es de %d.", this.etiqueta, this.stock));
            return this.stock;
        }
    }    

}
