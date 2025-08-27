package micro.example.microservicio_admin.entity.clases;


import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class Parada implements Serializable {

    private long id;
    private Long x;
    private Long y;
    private List<Long> monopatinIds;

    public Parada(Long id, Long x, Long y, List<Long> monopatinIds) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.monopatinIds = monopatinIds;
    }
    public Parada() {}

    public Parada(Parada p){
        this.id = p.id;
        this.x = p.x;
        this.y = p.y;
        this.monopatinIds = p.monopatinIds;
    }
}

