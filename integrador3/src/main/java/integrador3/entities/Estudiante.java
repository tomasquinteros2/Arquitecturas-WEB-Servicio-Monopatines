package integrador3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Estudiante {
    @Id
    private int dni;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int edad;
    @Column
    private String genero;
    @Column
    private String ciudadResidencia;
    @Column
    private int nroLU;
    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private List<Estudiante_Carrera> carreras;

    public Estudiante() {

    }
    public Estudiante(int dni, String nombre, String apellido, int edad, String genero, int nroLU, String ciudadResidencia) {
        this.nroLU = nroLU;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.dni = dni;
        this.ciudadResidencia= ciudadResidencia;
    }


}
