package integrador3.service;

import integrador3.entities.Carrera;

import java.util.List;

public interface Servicio<E>{

    public List<E> findAll()throws Exception;

    public E findById(int id)throws Exception;

    public E save(E entity)throws  Exception;

    public E update(int id, E entity)throws Exception;

    public boolean delete(int id)throws Exception;

    E update(int id, Carrera entity) throws Exception;
}
