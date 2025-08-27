package micro.example.microservicio_admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacturadoDTO {
    private Integer totalFacturado;

    public FacturadoDTO() {
    }
    public FacturadoDTO(Integer totalFacturado) {
        this.totalFacturado = totalFacturado;
    }
    public void setTotalFacturado(Integer totalFacturado) {
        this.totalFacturado = totalFacturado;
    }
}
