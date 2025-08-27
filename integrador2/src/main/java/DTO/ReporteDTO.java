package DTO;

import java.util.Map;
import java.util.TreeMap;

public class ReporteDTO {
    String nombreCarrera;
    Map<Integer,CarreraInfoDTO>infoPorAnio;
    public ReporteDTO(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
        this.infoPorAnio = new TreeMap<>(); // Para mantener los años en orden
    }

    @Override
    public String toString() {
        return "\nCarrera = " + nombreCarrera + ", " +
                "Informacion de alumnos por año = " + infoPorAnio;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public Map<Integer, CarreraInfoDTO> getInfoPorAnio() {
        return infoPorAnio;
    }

    public void setInfoPorAnio(Map<Integer, CarreraInfoDTO> infoPorAnio) {
        this.infoPorAnio = infoPorAnio;
    }
}
