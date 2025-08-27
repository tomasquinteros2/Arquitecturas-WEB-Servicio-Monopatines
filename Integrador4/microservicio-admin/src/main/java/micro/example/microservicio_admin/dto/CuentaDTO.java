package micro.example.microservicio_admin.dto;


import lombok.Getter;
import micro.example.microservicio_admin.entity.clases.Cuenta;

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

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isAnulada() {
        return anulada;
    }


}
