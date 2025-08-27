package integrador3.DTO;

import integrador3.entities.Estudiante;
import lombok.Getter;

@Getter
public class EstudianteDTO {
    private String nombre;
    private String apellido;
    private int LU;

    public EstudianteDTO(Estudiante estudiante){
        this.nombre = estudiante.getNombre();
        this.apellido = estudiante.getApellido();
        this.LU = estudiante.getNroLU();
    }
}
