package service;

import DTO.CarreraDTO;
import DTO.EstudianteDTO;
import DTO.ReporteDTO;
import entities.Carrera;
import entities.Estudiante;
import entities.Estudiante_Carrera;
import helper.CSVReader;
import repositories.CarreraRepository;
import repositories.EstudianteRepository;
import repositories.Estudiante_CarreraRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Servicios {
    private EntityManager em;
    private CarreraRepository cr;
    private EstudianteRepository er;
    private Estudiante_CarreraRepository ecr;

    public Servicios(EntityManager em){
        this.em = em;
        this.cr = new CarreraRepository(em);
        this.er = new EstudianteRepository(em);
        this.ecr = new Estudiante_CarreraRepository(em);
    }

    private List<EstudianteDTO> convertirEstudiantes(List<Estudiante> estudiantes){
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>();
        for (Estudiante estudiante : estudiantes){
            EstudianteDTO estudianteDTO = new EstudianteDTO(estudiante);
            estudiantesDTO.add(estudianteDTO);
        }
        return estudiantesDTO;
    }

    public void inicializarDB() throws Exception {
        CSVReader reader = new CSVReader(em, cr, ecr, er);
        reader.populateDB();
    }
    //a
    public void agregarEstudiante(Estudiante estudiante){
        em.getTransaction().begin();
        er.create(estudiante);
        em.getTransaction().commit();
    }
    //b
    public void matricularEstudiante(int dni, int carrera_id){
        Carrera carrera = cr.findById(carrera_id);
        Estudiante estudiante = er.findById(dni);
        Estudiante_Carrera estudianteCarrera = new Estudiante_Carrera(estudiante, carrera, 2024, 0, 0);
        em.getTransaction().begin();
        ecr.create(estudianteCarrera);
        em.getTransaction().commit();
    }
    //c
    public List<EstudianteDTO> obtenerEstudiantes(){
        return convertirEstudiantes(er.findAll());
    }
    //d
    public EstudianteDTO obtenerEstudiantePorLU(int LU){
        return new EstudianteDTO(er.findByLU(LU));
    }
    //e
    public List<EstudianteDTO> obtenerEstudiantesPorGenero(String genero){
        return convertirEstudiantes(er.findByGenero(genero));
    }
    //f
    public List<CarreraDTO> obteberCarrerasConInscriptos(){
        return cr.findCarreraConInscriptos();
    }
    //g
    public List<EstudianteDTO> obtenerEstudiantesPorCarreraCiudad(int id_carrera, String ciudad){
        return convertirEstudiantes(er.findByCarreraAndCiudad(id_carrera, ciudad));
    }
    //3
    public List<ReporteDTO> generarReporte(){
        return cr.generarReporte();
    }

}
