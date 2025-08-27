package micro.example.microservicio_admin.dto;

import lombok.Getter;
import micro.example.microservicio_admin.entity.clases.Precio;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class ViajePrecioDTO implements Serializable {
    Precio precio;
    boolean estaHabilitado; //if fechaAHabilitar == localDate.now() TRUE

    public ViajePrecioDTO(Precio precio, boolean estaHabilitado) {
        this.precio = precio;
        this.estaHabilitado = estaHabilitado;
    }
}
