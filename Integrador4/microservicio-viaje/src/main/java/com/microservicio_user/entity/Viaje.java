package com.microservicio_user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter @Setter
public class Viaje {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate fecha;
    @Setter
    @Getter
    @Column
    private LocalTime horaInicio;
    @Setter
    @Getter
    @Column
    private LocalTime horaFin;
    @Setter
    @Column
    private double kmRecorridos;
    @Column
    private boolean pausa;

    @Setter
    @Getter
    @JsonIgnore
    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL)
    private List<Pausa> pausas;

    @Setter
    @Getter
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "precio_id")
    private Precio precio;

    @JsonIgnore
    private Long idMonopatin;
    private int idUsuario;

    public Viaje(Viaje viaje) {
        this.id = viaje.id;
        this.idMonopatin = viaje.idMonopatin;
        this.horaInicio = viaje.horaInicio;
        this.horaFin = viaje.horaFin;
        this.kmRecorridos = viaje.kmRecorridos;
        this.pausas = viaje.pausas;
        this.precio = viaje.precio;
    }

    public Viaje() {

    }

    public Viaje(Long id, Long idmonopatin, LocalTime fechaInicio, LocalTime fechaFin, double kilometrosRecorridos, List<Pausa> pausas, Precio precio) {
        this.id = id;
        this.idMonopatin = idmonopatin;
        this.horaInicio = fechaInicio;
        this.horaFin = fechaFin;
        this.kmRecorridos = kilometrosRecorridos;
        this.pausas = pausas;
        this.precio = precio;
    }

    public Long getMonopatin() {
        return this.idMonopatin;
    }

    public void setMonopatin(Long monopatin) {
        this.idMonopatin = monopatin;
    }

}
