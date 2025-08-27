package com.microservicio_user.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Data
public class Precio {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column
    private double valor;

    @Setter
    @Getter
    @Column
    private double valorXkilometro;

    @Column
    private LocalDate fechaFacturacion;

    @Setter
    @Getter
    @Column
    private Double valorPorPausaExtendida;

    @JsonIgnore
    @OneToOne(mappedBy = "precio")
    private Viaje viaje;

    public Precio(Long id, double valor, LocalDate fechaFacturacion, LocalDate fechaInicioAHabilitar, Viaje viaje) {
        this.id = id;
        this.valor = valor;
        this.fechaFacturacion = fechaFacturacion;
        this.valorPorPausaExtendida = 0.0;
        this.viaje = viaje;
    }

    public Precio() {

    }

    public Precio(Precio precio) {
        this.id = precio.id;
        this.valor = precio.valor;
        this.fechaFacturacion = precio.fechaFacturacion;
        this.valorPorPausaExtendida = precio.valorPorPausaExtendida;
        this.viaje = precio.viaje;
    }

    public Precio(Long id, double valor, LocalDate fechaFacturacion, LocalDate fechaInicioAHabilitar, Double valorPorPausaExtendida, Viaje viaje) {
        this.id = id;
        this.valor = valor;
        this.fechaFacturacion = fechaFacturacion;
        this.valorPorPausaExtendida = valorPorPausaExtendida;
        this.viaje = viaje;
    }
    public void setValorXkilometro(double valorxkilometro) {
        this.valorXkilometro = valorxkilometro;
    }
    public double getValor(){
        return this.valor;
    }
}
