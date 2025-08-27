package com.microservicio_mantenimiento.service.dto;



import com.microservicio_mantenimiento.entity.Mantenimiento;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class MantenimientoDTO implements Serializable {
    private Long id;
    private Long id_monopatin;
    private LocalDate inicio;
    private LocalDate fin;
    private double km_recorridos;

    public MantenimientoDTO(Long id, Long id_monopatin, LocalDate inicio, LocalDate fin, double km_recorridos) {
        this.id = id;
        this.id_monopatin = id_monopatin;
        this.inicio = inicio;
        this.fin = fin;
        this.km_recorridos = km_recorridos;
    }

    public MantenimientoDTO(Mantenimiento m) {
        this.id = m.getId();
        this.id_monopatin = m.getId_monopatin();
        this.inicio = m.getInicio();
        this.fin = m.getFin();
        this.km_recorridos = m.getKm_recorridos();
    }


    public Long getId() {
        return id;
    }


    public Long getId_monopatin() {
        return id_monopatin;
    }


    public LocalDate getInicio() {
        return inicio;
    }


    public LocalDate getFin() {
        return fin;
    }



    public double getKm_recorridos() {
        return km_recorridos;
    }


}

