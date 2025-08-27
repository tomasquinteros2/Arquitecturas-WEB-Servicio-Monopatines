package micro.example.microservicio_admin.dto;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ReporteKilometrajeDTO implements Serializable {

    private Long idMonopatin;
    private Double kilometraje;
    private Long tiempoDePausa;

    public ReporteKilometrajeDTO() {
    }

    public ReporteKilometrajeDTO(Long idMonopatin, Double kilometraje, Long tiempoDePausa) {
        this.idMonopatin = idMonopatin;
        this.kilometraje = kilometraje;
        this.tiempoDePausa = tiempoDePausa;
    }

    public ReporteKilometrajeDTO(Long idMonopatin, Double kilometraje) {
        this.idMonopatin = idMonopatin;
        this.kilometraje = kilometraje;
        this.tiempoDePausa = null;
    }

    // Agrega los getters generados por Lombok
    public Long getIdMonopatin() {
        return idMonopatin;
    }

    public Double getKilometraje() {
        return kilometraje;
    }

    public Long getTiempoDePausa() {
        return tiempoDePausa;
    }
}