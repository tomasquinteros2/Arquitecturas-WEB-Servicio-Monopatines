package com.microservicio_user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MonopatinViajeDTO {
    //List<Long> id_monopatin;
    Long id_monopatin;
    Long cantidad;

    public MonopatinViajeDTO(Long id_monopatin, Long cantidad) {
        this.id_monopatin = id_monopatin;
        this.cantidad = cantidad;
    }

}
