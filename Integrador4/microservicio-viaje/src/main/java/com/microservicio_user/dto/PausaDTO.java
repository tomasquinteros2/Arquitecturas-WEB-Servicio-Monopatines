package com.microservicio_user.dto;


import com.microservicio_user.entity.Pausa;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class PausaDTO implements Serializable {
    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;


    public PausaDTO(Long id, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public PausaDTO(Pausa p) {
        this.id = p.getId();
        this.fechaInicio = p.getFechaInicio();
        this.fechaFin = p.getFechaFin();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }


}
