package integrador3.service;

import integrador3.entities.Carrera;
import integrador3.entities.Estudiante_Carrera;
import integrador3.repository.Estudiante_CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("Estudiante_CarreraService")
public class Estudiante_CarreraService implements Servicio<Estudiante_Carrera>{
    @Autowired
    private Estudiante_CarreraRepository estudianteCarreraRepository;

    @Override
    public List<Estudiante_Carrera> findAll() throws Exception {
        return estudianteCarreraRepository.findAll();
    }

    @Override
    public Estudiante_Carrera findById(int id) throws Exception {
        //TODO
        return null;
    }

    @Override
    public Estudiante_Carrera update(int id, Estudiante_Carrera entity) throws Exception {
        //TODO
        return null;
    }

    @Override
    public boolean delete(int id) throws Exception {
        //TODO
        return false;
    }

    @Override
    public Estudiante_Carrera update(int id, Carrera entity) throws Exception {
        //TODO
        return null;
    }

    @Override
    public Estudiante_Carrera save(Estudiante_Carrera entity) throws Exception {
        try {
            return estudianteCarreraRepository.save(entity);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<Object[]> getByAnioInicio(int idCarrera) throws Exception {
        try {
            return estudianteCarreraRepository.getByAnioInicio(idCarrera);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Object[]> getByAnioFin(int idCarrera) throws Exception {
        try {
            return estudianteCarreraRepository.getByAnioFin(idCarrera);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
