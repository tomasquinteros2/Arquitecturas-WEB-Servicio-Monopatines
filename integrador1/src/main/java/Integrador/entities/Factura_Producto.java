package Integrador.entities;

public class Factura_Producto {
    private Integer idFactura;
    private Integer idProducto;
    private Integer cantidad;
    public Factura_Producto() {}
    public Factura_Producto(Integer idFactura, Integer idProducto, Integer cantidad) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Integer getIdFactura() {
        return idFactura;
    }
    public Integer getIdProducto() {
        return idProducto;
    }
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Factura_Producto{" +
                "idFactura=" + idFactura +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                '}';
    }
}
