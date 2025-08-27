package com.microservicio_monopatin.util;

import com.microservicio_monopatin.model.Monopatin;
import com.microservicio_monopatin.repository.MonopatinRepository;
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

    private final MonopatinRepository monopatin;

    @Autowired
    public CargaDeDatos(MonopatinRepository monopatin) {
        this.monopatin = monopatin;
    }
    public boolean isTablaVacia() {
        return monopatin.count() == 0;
    }
    public void cargarDatosDesdeCSV() throws IOException{

        InputStream inputStream = getClass().getResourceAsStream("/Monopatin.csv");


        if (inputStream == null) {
            throw new IOException("Archivo Monopatin.csv no encontrado en resources.");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             CSVParser datosMonopatin = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            for (CSVRecord monopatin : datosMonopatin){
                Monopatin m = new Monopatin();

                m.setKilometraje(Double.parseDouble(monopatin.get("kilometraje")));
                m.setEnMantenimiento(Boolean.parseBoolean(monopatin.get("enMantenimiento")));
                m.setNumeroSerie(monopatin.get("numeroSerie"));

                this.monopatin.save(m);
            }
        }
    }
}
