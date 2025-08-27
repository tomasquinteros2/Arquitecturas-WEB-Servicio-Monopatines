package com.microservicio_user.dto;


import com.microservicio_user.entity.Precio;
import com.microservicio_user.entity.Viaje;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class PrecioDTO  implements Serializable {
    private Long id;

    private double valor;
    private LocalDate fechaInicio;
    private double valorXkilometro;
    private Double valorPorPausaExtendida;
    private Viaje viaje;


    public PrecioDTO(Long id, double valor, LocalDate fechaInicio, Double valorPorPausaExtendida) {
        this.id = id;
        this.valor = valor;
        this.fechaInicio = fechaInicio;
        this.valorPorPausaExtendida=valorPorPausaExtendida;
    }

    public PrecioDTO(Precio p){
        this.id = p.getId();
        this.valor = p.getValor();
        this.valorPorPausaExtendida= p.getValorPorPausaExtendida();
        this.valorXkilometro = p.getValorXkilometro();
        this.fechaInicio = p.getFechaFacturacion();
        this.viaje = p.getViaje();
    }

    public Long getId() {
        return id;
    }

    public Double getValorPorPausaExtendida() {
        return valorPorPausaExtendida;
    }

    public void setValorPorPausaExtendida(Double valorPorPausaExtendida) {
        this.valorPorPausaExtendida = valorPorPausaExtendida;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
}
