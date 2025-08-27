package integrador3.repository;

import integrador3.entities.Estudiante_Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Estudiante_CarreraRepository extends JpaRepository<Estudiante_Carrera, Integer> {
    @Query("SELECT ec.anio_inicio, COUNT(ec) FROM Estudiante_Carrera ec WHERE ec.carrera.id_carrera = :idCarrera GROUP BY ec.anio_inicio ORDER BY ec.anio_inicio")
    List<Object[]> getByAnioInicio(int idCarrera);

    @Query("SELECT ec.anio_fin, COUNT(ec) FROM Estudiante_Carrera ec WHERE ec.carrera.id_carrera = :idCarrera AND ec.anio_fin <> 0 GROUP BY ec.anio_fin ORDER BY ec.anio_fin")
    List<Object[]> getByAnioFin(int idCarrera);
}
