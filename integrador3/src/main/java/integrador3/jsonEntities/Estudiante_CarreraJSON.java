package integrador3.jsonEntities;

import lombok.Data;

@Data
public class Estudiante_CarreraJSON {
    private int id;
    private int id_estudiante;
    private int id_carrera;
    private int anio_inicio;
    private int anio_fin;
    private int antiguedad;

    public Estudiante_CarreraJSON() {
    }
}
