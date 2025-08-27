package com.microservicio_user.repository;

import com.microservicio_user.entity.Precio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RepositoryPrecio extends JpaRepository<Precio, Long> {
    @Query("SELECT p FROM Precio p WHERE p.fechaFacturacion > :fechaAHabilitar")
    List<Precio> findByFechaFacturacionAfter(@Param("fechaAHabilitar") LocalDate fechaAHabilitar);

}

