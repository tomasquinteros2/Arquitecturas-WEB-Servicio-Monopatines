package Integrador.entities;

public class Factura {
    private Integer idFactura;
    private Integer idCliente;
    public Factura() {}
    public Factura(Integer idFactura, Integer idCliente) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
    }

    public Integer getIdFactura() {
        return idFactura;
    }
    public Integer getIdCliente() {
        return idCliente;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", idCliente=" + idCliente +
                '}';
    }
}
