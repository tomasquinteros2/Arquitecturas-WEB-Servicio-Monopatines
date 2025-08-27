package integrador3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Carrera {
    @Id
    private int id_carrera;
    @Column
    private String nombre;
    @Column
    private int duracion;
    @OneToMany(mappedBy = "carrera")
    @JsonIgnore
    private List<Estudiante_Carrera> estudiantes;

    public Carrera() {}

    public Carrera(int i,String nombre, int duracion) {
        this.id_carrera = i;
        this.nombre = nombre;
        this.duracion = duracion;
        this.estudiantes = new ArrayList<Estudiante_Carrera>();
    }
    public Carrera(Carrera carrera){
        this.id_carrera = carrera.id_carrera;
        this.nombre = carrera.nombre;
        this.duracion = carrera.duracion;
        this.estudiantes = carrera.estudiantes;
    }
}
