package com.microservicioparada.dto;

import com.microservicioparada.model.Parada;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParadaDTO {
    private Long id;
    private double x;
    private double y;
    private List<Long> monopatinIds;

    public ParadaDTO(Parada parada) {
        this.id = parada.getId();
        this.x = parada.getX();
        this.y = parada.getY();
        this.monopatinIds = parada.getMonopatinIds();
    }
}