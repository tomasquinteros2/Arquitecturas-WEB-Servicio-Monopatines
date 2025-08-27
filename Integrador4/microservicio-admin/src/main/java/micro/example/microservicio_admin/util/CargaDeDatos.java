package micro.example.microservicio_admin.util;

import micro.example.microservicio_admin.entity.Administrador;
import micro.example.microservicio_admin.repository.AdministracionRepo;
import micro.example.microservicio_admin.service.ServicioAdministracion;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.format.DateTimeFormatter;

import static java.lang.Integer.parseInt;

@Component
public class CargaDeDatos {

    private final AdministracionRepo ar;
    private ServicioAdministracion sa;

    @Autowired
    public CargaDeDatos(AdministracionRepo ar) {
        this.ar = ar;
    }

    public boolean isTablaVacia() {
        return ar.count() == 0;
    }

    public void cargarDatosDesdeCSV() throws IOException{

        InputStream inputStream = getClass().getResourceAsStream("/Administracion.csv");

        if (inputStream == null) {
            throw new IOException("Archivo usuarios.csv no encontrado en resources.");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             CSVParser datosAdministracion = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())){
            for (CSVRecord administrador: datosAdministracion) {
                Administrador a = new Administrador();

                a.setId(Long.valueOf(administrador.get("id")));
                a.setNombre(administrador.get("nombre"));
                ar.save(a);

            }
        }
    }
}
