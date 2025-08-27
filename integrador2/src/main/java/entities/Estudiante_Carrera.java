package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Estudiante_Carrera implements Serializable {
    @EmbeddedId
    private Estudiante_Carrera_pk pk;

    @ManyToOne
    @MapsId("id_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @MapsId("id_carrera")
    private Carrera carrera;
    @Column
    private int anio_inicio;
    @Column
    private int anio_fin;
    @Column
    private int antiguedad;

    public Estudiante_Carrera() {}

    public Estudiante_Carrera(Estudiante e, Carrera c, int anio_inicio, int anio_fin, int antiguedad) {
        this.estudiante = e;
        this.carrera=c;
        this.anio_inicio = anio_inicio;
        this.anio_fin = anio_fin;
        this.antiguedad = antiguedad;
        this.pk = new Estudiante_Carrera_pk(c.getId_carrera(),e.getDni());
    }
}
