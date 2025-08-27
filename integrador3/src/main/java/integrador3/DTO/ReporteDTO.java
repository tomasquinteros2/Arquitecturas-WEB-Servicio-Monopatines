package integrador3.DTO;

import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
public class ReporteDTO {
    String nombreCarrera;
    Map<Integer,CarreraInfoDTO>infoPorAnio;

    public ReporteDTO(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
        this.infoPorAnio = new TreeMap<>(); // Para mantener los años en orden
    }
}
