package com.microservicio_user.services.dto;

import com.microservicio_user.entity.Cuenta;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CuentaDTO {

    private Long id;
    private LocalDate date;
    private boolean anulada;

    public CuentaDTO(){

    }

    public CuentaDTO(Cuenta c) {
        this.id = c.getId();
        this.date = c.getDate();
        this.anulada = c.isAnulada();
    }
}
