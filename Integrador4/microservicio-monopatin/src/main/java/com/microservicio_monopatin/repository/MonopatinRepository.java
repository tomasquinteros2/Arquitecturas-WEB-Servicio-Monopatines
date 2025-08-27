package com.microservicio_monopatin.repository;

import com.microservicio_monopatin.dto.EstadoMonopatinDTO;
import com.microservicio_monopatin.model.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {
    @Query("SELECT new com.microservicio_monopatin.dto.EstadoMonopatinDTO(" +
            "SUM(CASE WHEN m.enMantenimiento = false THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN m.enMantenimiento = true THEN 1 ELSE 0 END)) " +
            "FROM Monopatin m")
    EstadoMonopatinDTO getComparacionEstados();
}
