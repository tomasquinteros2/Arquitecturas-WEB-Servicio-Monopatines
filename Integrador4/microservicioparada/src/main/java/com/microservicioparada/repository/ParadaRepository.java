package com.microservicioparada.repository;

import com.microservicioparada.model.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {
    @Query("SELECT p FROM Parada p WHERE SQRT((p.x - :usuarioX) * (p.x - :usuarioX) + (p.y - :usuarioY) * (p.y - :usuarioY)) <= :distanciaCercana ORDER BY SQRT((p.x - :usuarioX) * (p.x - :usuarioX) + (p.y - :usuarioY) * (p.y - :usuarioY)) limit 5")
    List<Parada> getMonopatinesCercanos(double usuarioX, double usuarioY, double distanciaCercana);
}
