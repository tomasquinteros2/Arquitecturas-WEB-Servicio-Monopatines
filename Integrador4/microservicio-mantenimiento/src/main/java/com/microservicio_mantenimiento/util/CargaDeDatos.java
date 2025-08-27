package com.microservicio_mantenimiento.util;

import com.microservicio_mantenimiento.entity.Mantenimiento;
import com.microservicio_mantenimiento.repository.MantenimientoRepositorio;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CargaDeDatos {

    private final MantenimientoRepositorio mr;

    @Autowired
    public CargaDeDatos(MantenimientoRepositorio mr) {
        this.mr = mr;
    }

    public boolean isTablaVacia() {
        return mr.count() == 0;
    }

    public void cargarDatosDesdeCSV() throws IOException {

        InputStream inputStream = getClass().getResourceAsStream("/Mantenimiento.csv");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (inputStream == null) {
            throw new IOException("Archivo Mantenimiento.csv no encontrado en resources.");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             CSVParser datosMantenimiento = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord mantenimiento : datosMantenimiento) {
                Mantenimiento m = new Mantenimiento();

                m.setId(Long.valueOf(mantenimiento.get("idMantenimiento")));
                m.setId_monopatin(Long.valueOf(mantenimiento.get("idMonopatin")));
                m.setInicio(LocalDate.parse(mantenimiento.get("inicio"), formatter));
                m.setFin(LocalDate.parse(mantenimiento.get("fin"), formatter));
                m.setKm_recorridos((Double.parseDouble(mantenimiento.get("kilometros"))));

                mr.save(m);
            }
        }
    }
}
