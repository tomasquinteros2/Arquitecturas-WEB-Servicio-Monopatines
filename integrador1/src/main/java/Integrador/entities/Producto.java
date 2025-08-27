package Integrador.entities;

public class Producto {
    private Integer idProducto;
    private String nombre;
    private int valor;
    public Producto() {}
    public Producto(Integer idProducto, String nombre, int valor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.valor = valor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                '}';
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
