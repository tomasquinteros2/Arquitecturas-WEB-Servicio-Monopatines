package micro.example.microservicio_admin.dto;

import lombok.Getter;
import micro.example.microservicio_admin.entity.clases.Monopatin;

import java.io.Serializable;

@Getter
public class MonopatinDTO implements Serializable {
    private Long id;
    private String numeroSerie;
    private double kilometraje;
    private boolean enMantenimiento;

    public MonopatinDTO() {
    }

    public MonopatinDTO(Long id, String numeroSerie, double kilometraje, boolean enMantenimiento) {
        this.id = id;
        this.numeroSerie = numeroSerie;
        this.kilometraje = kilometraje;
        this.enMantenimiento = enMantenimiento;
    }

    public MonopatinDTO(Monopatin m){
        this.id = m.getId();
        this.numeroSerie = m.getNumeroSerie();
        this.kilometraje = m.getKilometraje();
        this.enMantenimiento = m.isEnMantenimiento();
    }


    public Long getId() {
        return id;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public boolean isEnMantenimiento() {
        return enMantenimiento;
    }


}
