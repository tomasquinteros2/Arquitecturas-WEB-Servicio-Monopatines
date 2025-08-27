package integrador3.utils;

import integrador3.entities.Carrera;
import integrador3.entities.Estudiante;
import integrador3.entities.Estudiante_Carrera;
import integrador3.repository.CarreraRepository;
import integrador3.repository.EstudianteRepository;
import integrador3.repository.Estudiante_CarreraRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
public final class CSVReader {

    @Autowired
    private EstudianteRepository er;
    @Autowired
    private CarreraRepository cr;
    @Autowired
    private Estudiante_CarreraRepository ecr;

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "integrador2\\src\\main\\resources\\" + archivo;
        // String path = "integrador2/src/main/resources/" + archivo; //para linux
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void populateDB() throws Exception {
        try {
            insertEstudiantes();
            insertCarreras();
            insertMatriculas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertEstudiantes() throws IOException {
        for(CSVRecord row : getData("estudiantes.csv")) {
            //DNI,nombre,apellido,edad,genero,ciudad,LU
            if(row.size() >= 7) {
                String dniString = row.get(0);
                String nombre = row.get(1);
                String apellido = row.get(2);
                String edadString = row.get(3);
                String genero = row.get(4);
                String ciudad = row.get(5);
                String nroLUString = row.get(6);
                if(!dniString.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !edadString.isEmpty() && !genero.isEmpty() && !nroLUString.isEmpty() && !ciudad.isEmpty()) {
                    try {
                        int nroLU = Integer.parseInt(nroLUString);
                        int edad = Integer.parseInt(edadString);
                        int dni = Integer.parseInt(dniString);
                        Estudiante estudiante = new Estudiante(dni, nombre, apellido, edad, genero, nroLU, ciudad);

                        er.save(estudiante);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                    }
                }
            }
        }
    }
    private void insertCarreras() throws Exception {
        for(CSVRecord row : getData("carreras.csv")) {
            //id_carrera,nombre, duracion
            if(row.size() >= 3) {
                String id_carreraString = row.get(0);
                String nombre = row.get(1);
                String duracionString = row.get(2);
                if(!id_carreraString.isEmpty() && !nombre.isEmpty() && !duracionString.isEmpty()) {
                    try {
                        int id_carrera = Integer.parseInt(id_carreraString);
                        int duracion = Integer.parseInt(duracionString);
                        Carrera carrera = new Carrera(id_carrera, nombre, duracion);
                        cr.save(carrera);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                    }
                }
            }
        }
    }
    private void insertMatriculas() throws IOException {
        for(CSVRecord row : getData("estudianteCarrera.csv")) {
            //id,id_estudiante,id_carrera,inscripcion,graduacion,antiguedad
            if(row.size() >= 6) {
                String idString = row.get(0);
                String dniString = row.get(1);
                String id_carreraString = row.get(2);
                String anio_inicioString = row.get(3);
                String anio_finString = row.get(4);
                String antiguedadString = row.get(5);
                if(!dniString.isEmpty() && !id_carreraString.isEmpty() && !anio_inicioString.isEmpty() && !anio_finString.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idString);
                        int dni = Integer.parseInt(dniString);
                        int id_carrera = Integer.parseInt(id_carreraString);
                        int anio_inicio = Integer.parseInt(anio_inicioString);
                        int anio_fin = Integer.parseInt(anio_finString);
                        int antiguedad = Integer.parseInt(antiguedadString);

                        Estudiante estudiante = er.findById(dni).get();
                        Carrera carrera = cr.findById(id_carrera).get();
                        Estudiante_Carrera estudianteCarrera = new Estudiante_Carrera(id, estudiante, carrera, anio_inicio, anio_fin, antiguedad);
                        ecr.save(estudianteCarrera);

                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                    }
                }
            }
        }
    }
}
