package repositories;

import javax.persistence.EntityManager;

public interface Repository <T>{
    void create(T object);
    void update(T object);
    void delete(int id);
    T findById(int id);
}