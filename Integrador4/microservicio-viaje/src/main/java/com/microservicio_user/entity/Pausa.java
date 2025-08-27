package com.microservicio_user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Setter
@Entity
public class Pausa {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column
    private LocalDate fechaInicio;

    //this.setCantidadPausa();
    @Getter
    @Column
    private LocalDate fechaFin;

    @Getter
    @Column Long pausaTotal;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idviaje")
    private Viaje viaje;

    public Pausa() {}

    public Pausa(Pausa pausa) {
        this.id = pausa.id;
        this.fechaInicio =pausa.fechaInicio;
        this.fechaFin = pausa.fechaFin;
        this.pausaTotal = pausa.pausaTotal;
        this.viaje = pausa.viaje;
    }

    public Pausa(Long id, LocalDate fechaInicio, LocalDate fechaFin, Long pausaTotal, Viaje viaje) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.pausaTotal = pausaTotal;
        this.viaje = viaje;
    }

    public Pausa(Long id, LocalDate fechaInicio, LocalDate fechaFin, Viaje viaje) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.pausaTotal = 0L;
        this.viaje = viaje;
    }

    public Viaje getRegistroUsoMonopatin() {
        return viaje;
    }

    @JsonIgnore
    public Long getCantidadPausa(){
        return getFechaInicio().until(getFechaFin(), ChronoUnit.MINUTES);
    }


}