package micro.example.microservicio_admin.entity.clases;

import lombok.Data;
import micro.example.microservicio_admin.dto.CuentaDTO;


import java.time.LocalDate;
@Data
public class Cuenta {

    private Long id;
    private LocalDate date;
    private boolean anulada;

    public Cuenta(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
        this.anulada = false;
    }

    public Cuenta(Long id, LocalDate date, boolean anulada) {
        this.id = id;
        this.date = date;
        this.anulada = anulada;
    }

    public Cuenta(){
        this.anulada = false;
    }

    public Cuenta(CuentaDTO cuenta) {
        this.id = cuenta.getId();
        this.date = cuenta.getDate();
        this.anulada = cuenta.isAnulada();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

}
