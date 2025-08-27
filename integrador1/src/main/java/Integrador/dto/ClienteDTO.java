package Integrador.dto;

public class ClienteDTO {
    private int facturado;
    private int id;
    private String nombre;
    public ClienteDTO(int facturado, int id, String nombre) {
        this.facturado = facturado;
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "\nCliente:" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", facturado=" + facturado;
    }
}
