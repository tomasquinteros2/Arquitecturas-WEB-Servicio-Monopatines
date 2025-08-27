package com.microservicio_user.entity.clases;

import com.microservicio_user.dto.MonopatinDTO;
import com.microservicio_user.entity.Viaje;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monopatin {

    private Long id;
    private String numeroSerie;
    private double kilometraje;

    private boolean enMantenimiento;

    private List<Viaje> viajes;

    public Monopatin(Monopatin monopatin) {
        this.id = monopatin.id;
        this.numeroSerie = monopatin.numeroSerie;
        this.kilometraje = monopatin.kilometraje;
        this.enMantenimiento = monopatin.enMantenimiento;
        this.viajes = monopatin.viajes;
    }

    public Monopatin(MonopatinDTO monopatin) {
        this.id = monopatin.getId();
        this.numeroSerie = monopatin.getNumeroSerie();
        this.kilometraje = monopatin.getKilometraje();
        this.enMantenimiento = monopatin.isEnMantenimiento();
    }


}
