package integrador3.DTO;


import integrador3.entities.Carrera;
import lombok.Getter;

@Getter
public class CarreraDTO {
    private String nombre;
    private int cantidad;

    public CarreraDTO(Carrera carrera, int cantidad)  {
        this.cantidad = cantidad;
        this.nombre = carrera.getNombre();
    }
}
