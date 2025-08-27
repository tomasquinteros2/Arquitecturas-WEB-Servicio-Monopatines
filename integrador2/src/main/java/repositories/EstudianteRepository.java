package repositories;

import entities.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EstudianteRepository implements Repository<Estudiante>{
    private EntityManager em;
    public EstudianteRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public void create(Estudiante object) {
        em.persist(object);
    }

    @Override
    public void update(Estudiante object) {
        //TODO
    }

    @Override
    public void delete(int id) {
        //TODO
    }

    @Override
    public Estudiante findById(int id) {
        return em.find(Estudiante.class, id);
    }
    public Estudiante findByLU(int LU){
        String jpql = "SELECT e FROM Estudiante e WHERE nroLU = :LU";

        return  em.createQuery(jpql, Estudiante.class).setParameter("LU", LU).getSingleResult();
    }

    public List<Estudiante> findAll(){
        String jpql = "SELECT e FROM Estudiante e ORDER BY e.apellido";
        return  em.createQuery(jpql, Estudiante.class).getResultList();
    }

    public List<Estudiante> findByGenero(String genero){
        String jpql = "SELECT e FROM Estudiante e WHERE e.genero = :genero";
        Query q = em.createQuery(jpql, Estudiante.class);
        q.setParameter("genero", genero);
        return q.getResultList();
    }

    public List<Estudiante> findByCarreraAndCiudad(int id_carrera,String ciudad) {
        String jpql = "SELECT e FROM Estudiante e JOIN Estudiante_Carrera ec ON ec.estudiante.dni = e.dni WHERE ec.carrera.id_carrera = :carrera AND e.ciudadResidencia = :ciudad";
        Query query = em.createQuery(jpql);
        query.setParameter("carrera", id_carrera);
        query.setParameter("ciudad", ciudad);
        return query.getResultList();
    }

//    public List<Estudiante> findEstudiantesReporte(int id_carrera, boolean egresado) {
//        String jpql = null;
//        if(egresado){
//            jpql = "SELECT e FROM Estudiante e JOIN Estudiante_Carrera ec ON ec.estudiante.nroLU = e.nroLU WHERE ec.carrera.id_carrera = :carrera AND ec.fecha_fin IS NOT NULL ORDER BY ec.fecha_fin";
//        } else {
//            jpql = "SELECT e FROM Estudiante e JOIN Estudiante_Carrera ec ON ec.estudiante.nroLU = e.nroLU WHERE ec.carrera.id_carrera = :carrera AND ec.fecha_fin IS NULL ORDER BY ec.fecha_inicio";
//        }
//        Query query = em.createQuery(jpql);
//        query.setParameter("carrera", id_carrera);
//        return query.getResultList();
//    }
}
