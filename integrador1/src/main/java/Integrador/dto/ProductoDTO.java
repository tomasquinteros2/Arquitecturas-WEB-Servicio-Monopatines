package Integrador.dto;

import Integrador.entities.Producto;

public class ProductoDTO {
    private Integer id;
    private String nombre;
    private int valor;
    private int recaudado;

    public ProductoDTO(Producto producto, int recaudado){
        this.id = producto.getIdProducto();
        this.nombre = producto.getNombre();
        this.valor = producto.getValor();
        this.recaudado = recaudado;
    }

    @Override
    public String toString() {
        return "Producto:" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", recaudado=" + recaudado;
    }
}
