package integrador3.repository;


import integrador3.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    @Query("SELECT e FROM Estudiante e ORDER BY e.apellido")
    List<Estudiante> findAll();

    @Query("SELECT e FROM Estudiante e WHERE e.nroLU = :lu")
    Optional<Estudiante> findByLU(int lu);

    @Query("SELECT e FROM Estudiante e WHERE e.genero = :genero")
    List<Estudiante> findByGenero(String genero);

    @Query("SELECT e FROM Estudiante e JOIN Estudiante_Carrera ec ON ec.estudiante.dni = e.dni WHERE ec.carrera.id_carrera = :carrera AND e.ciudadResidencia = :ciudad")
    List<Estudiante> findByCarreraCiudad(int carrera, String ciudad);
}
