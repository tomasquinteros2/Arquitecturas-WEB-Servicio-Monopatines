package com.microservicioparada.util;

import com.microservicioparada.model.Parada;
import com.microservicioparada.repository.ParadaRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CargaDeDatos {

    private ParadaRepository pr;

    @Autowired
    public CargaDeDatos(ParadaRepository pr) {
        this.pr = pr;
    }

    public boolean isTablaVacia() {
        return pr.count() == 0;
    }

    public void cargarDatosDesdeCSV() throws IOException{
        InputStream inputViaje = getClass().getResourceAsStream("/Parada.csv");


        if (inputViaje == null) {
            throw new IOException("Archivo Parada.csv no encontrado en resources.");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputViaje));
             CSVParser datosParadas = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())){
            for (CSVRecord parada : datosParadas){
                Parada p = new Parada();

                p.setId(Long.parseLong(parada.get("idParada")));
                p.setX(Double.parseDouble(parada.get("x")));
                p.setY(Double.parseDouble(parada.get("y")));

                String monopatinesStr = parada.get("monopatinIds");
                if (monopatinesStr != null && !monopatinesStr.trim().isEmpty()) {
                    List<Long> monopatinesIds = Arrays.stream(monopatinesStr.split(","))
                            .map(String::trim)
                            .map(Long::parseLong)
                            .collect(Collectors.toList());
                    p.setMonopatinIds(monopatinesIds);
                }

                pr.save(p);
            }
        }
    }
}