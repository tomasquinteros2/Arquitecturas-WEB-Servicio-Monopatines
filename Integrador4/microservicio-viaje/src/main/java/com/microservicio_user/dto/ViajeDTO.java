package com.microservicio_user.dto;


import com.microservicio_user.entity.Viaje;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
public class ViajeDTO implements Serializable {
    private Long id;
    private LocalTime fechaInicio;
    private LocalTime fechaFin;
    private double kilometrosRecorridos;

    public ViajeDTO(Long id, LocalTime fechaInicio, LocalTime fechaFin, double kilometrosRecorridos) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.kilometrosRecorridos = kilometrosRecorridos;
    }

    public ViajeDTO(Viaje r) {
        this.id = r.getId();
        this.fechaInicio = r.getHoraInicio();
        this.fechaFin = r.getHoraFin();
        this.kilometrosRecorridos = r.getKmRecorridos();
    }


}
