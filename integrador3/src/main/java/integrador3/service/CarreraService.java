package integrador3.service;

import integrador3.DTO.CarreraDTO;
import integrador3.DTO.CarreraInfoDTO;
import integrador3.DTO.ReporteDTO;
import integrador3.entities.Carrera;
import integrador3.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("CarreraService")
public class CarreraService implements Servicio<Carrera>{

    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private Estudiante_CarreraService estudiante_CarreraService;

    @Override
    public List<Carrera> findAll() throws Exception {
        return carreraRepository.findAll();
    }

    public Carrera findById(int id) throws Exception {
        try{
            Optional<Carrera> carrera = carreraRepository.findById(id);
            return carrera.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Carrera save(Carrera entity) throws Exception {
        try {
            return carreraRepository.save(entity);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Carrera update(int id, Carrera entity) throws Exception {
        try{
            Optional<Carrera> entityOpcional = carreraRepository.findById(id);
            Carrera carrera = entityOpcional.get();
            carrera = carreraRepository.save(entity);
            return carrera;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        try {
            if (carreraRepository.existsById(id)){
                carreraRepository.deleteById(id);
                return true;
            }
            else {
                throw new Exception();
            }
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<CarreraDTO> findCarreraConInscriptos() throws Exception {
        try{
            List<Object[]> query = carreraRepository.findCarreraConInscriptos();
            List<CarreraDTO> carreras = new ArrayList<>();
            for (Object[] elemento : query){
                Carrera c = carreraRepository.findById((Integer) elemento[0]).get();
                Integer cantidad = Math.toIntExact((Long) elemento[1]);
                CarreraDTO carrera = new CarreraDTO(c, cantidad);
                carreras.add(carrera);
            }
            return carreras;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    public List<ReporteDTO> getReporte() throws Exception {
        try {
            List<Carrera> carreras = carreraRepository.getByName();
            List<ReporteDTO> reportes = new ArrayList<>();
            for (Carrera c : carreras){
                ReporteDTO reporte = new ReporteDTO(c.getNombre());

                List<Object[]> inscriptos = estudiante_CarreraService.getByAnioInicio(c.getId_carrera());
                List<Object[]> egresados = estudiante_CarreraService.getByAnioFin(c.getId_carrera());

                for (Object[] item : inscriptos){
                    int anio = (Integer) item[0];
                    int cantidad = ((Number) item[1]).intValue();
                    reporte.getInfoPorAnio().put(anio, new CarreraInfoDTO(cantidad));
                }

                for (Object[] item : egresados){
                    int anio = (Integer) item[0];
                    int cantidad = ((Number) item[1]).intValue();
                    CarreraInfoDTO carrera = reporte.getInfoPorAnio().get(anio);
                    if (carrera == null){
                        carrera = new CarreraInfoDTO(0);
                    }
                    carrera.setEgresados(cantidad);
                    reporte.getInfoPorAnio().put(anio, carrera);
                }
                reportes.add(reporte);
            }
            return reportes;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
