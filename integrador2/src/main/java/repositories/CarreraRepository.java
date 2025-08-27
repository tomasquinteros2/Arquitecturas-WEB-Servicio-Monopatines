package repositories;

import DTO.CarreraDTO;
import DTO.CarreraInfoDTO;
import DTO.ReporteDTO;
import entities.Carrera;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CarreraRepository implements Repository<Carrera>{
    private EntityManager em;
    public CarreraRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Carrera object) {
        em.persist(object);
    }

    @Override
    public void update(Carrera object) {
        //TODO
    }

    @Override
    public void delete(int id) {
        //TODO
    }

    @Override
    public Carrera findById(int id) {
        return em.find(Carrera.class, id);
    }

    public List<CarreraDTO> findCarreraConInscriptos() {
        String jpql = "SELECT c, c.estudiantes.size FROM Carrera c WHERE c.estudiantes.size > 0 ORDER BY c.estudiantes.size DESC ";
        Query query = em.createQuery(jpql);
        List<Object[]> resultados = query.getResultList();
        List<CarreraDTO> carreras = new ArrayList<>();
        for(Object[] r : resultados){
            CarreraDTO carrera = new CarreraDTO((Carrera) r[0], (Integer)r[1]);
            carreras.add(carrera);
        }
        return carreras;
    }

    public List<ReporteDTO> generarReporte() {
        List<Carrera> carreras = em.createQuery("SELECT c FROM Carrera c ORDER BY c.nombre", Carrera.class).getResultList();
        List<ReporteDTO> reportes = new ArrayList<>();

        for(Carrera carrera : carreras){
            ReporteDTO reporte = new ReporteDTO(carrera.getNombre());

            String jpql = "SELECT ec.anio_inicio, COUNT(ec) " +
                    "FROM Estudiante_Carrera ec " +
                    "WHERE ec.carrera.id_carrera = :idCarrera " +
                    "GROUP BY ec.anio_inicio " +
                    "ORDER BY ec.anio_inicio";

            String jpql2 = "SELECT ec.anio_fin, COUNT(ec) " +
                    "FROM Estudiante_Carrera ec " +
                    "WHERE ec.carrera.id_carrera = :idCarrera AND ec.anio_fin <> 0" +
                    "GROUP BY ec.anio_fin " +
                    "ORDER BY ec.anio_fin";

            List<Object[]> inscriptosList = em.createQuery(jpql).setParameter("idCarrera", carrera.getId_carrera()).getResultList();
            List<Object[]> esgresadosList = em.createQuery(jpql2).setParameter("idCarrera", carrera.getId_carrera()).getResultList();
            for (Object[] resultado : inscriptosList){
                int anio = (Integer) resultado[0];
                int inscriptos = ((Number) resultado[1]).intValue();
                reporte.getInfoPorAnio().put(anio, new CarreraInfoDTO(inscriptos));
            }
            for (Object[] resultado : esgresadosList){
                int anio = (Integer) resultado[0];
                int egresados = ((Number) resultado[1]).intValue();
                CarreraInfoDTO c = reporte.getInfoPorAnio().get(anio);
                if (c == null){
                    c = new CarreraInfoDTO(0);
                }
                c.setEgresados(egresados);
                reporte.getInfoPorAnio().put(anio, c);
            }
            reportes.add(reporte);
        }
        return reportes;
    }
}
