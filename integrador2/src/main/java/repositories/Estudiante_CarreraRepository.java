package repositories;

import entities.Estudiante_Carrera;
import javax.persistence.EntityManager;

public class Estudiante_CarreraRepository implements Repository<Estudiante_Carrera>{
    private EntityManager em;
    public Estudiante_CarreraRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public void create(Estudiante_Carrera object) {
        em.persist(object);
    }

    @Override
    public void update(Estudiante_Carrera object) {
        //TODO
    }

    @Override
    public void delete(int id) {
        //TODO
    }

    @Override
    public Estudiante_Carrera findById(int id) {
        return em.find(Estudiante_Carrera.class, id);
    }

}
