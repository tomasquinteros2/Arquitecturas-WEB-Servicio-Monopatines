package micro.example.microservicio_admin.dto;

import lombok.Getter;
import micro.example.microservicio_admin.entity.clases.Parada;

import java.io.Serializable;
import java.util.List;

@Getter
public class ParadaDTO implements Serializable {
    private Long id;
    private Long x;
    private Long y;
    private List<Long> monopatinIds;

    public ParadaDTO(Long id, Long x, Long y, List<Long> monopatinIds) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.monopatinIds = monopatinIds;
    }

    public ParadaDTO(Parada parada) {
        this.id = parada.getId();
        this.x = parada.getX();
        this.y = parada.getY();
        this.monopatinIds = getMonopatinIds();
    }
}
