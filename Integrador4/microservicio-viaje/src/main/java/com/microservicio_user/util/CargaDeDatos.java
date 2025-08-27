package com.microservicio_user.util;

import com.microservicio_user.entity.Pausa;
import com.microservicio_user.entity.Precio;
import com.microservicio_user.entity.Viaje;
import com.microservicio_user.repository.RepositoryPausa;
import com.microservicio_user.repository.RepositoryPrecio;
import com.microservicio_user.repository.RepositoryViaje;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class CargaDeDatos {

    private final RepositoryViaje viaje;
    private final RepositoryPrecio precio;

    private final RepositoryPausa pausa;



    @Autowired
    public CargaDeDatos(RepositoryPausa pausa, RepositoryPrecio precio, RepositoryViaje viaje) {
        this.pausa = pausa;
        this.precio = precio;
        this.viaje = viaje;
    }

    public boolean isTablaVacia() {
        // Verifica si la tabla 'viaje' está vacía
        return viaje.count() == 0 && pausa.count() == 0 && precio.count() == 0;
    }

    public void cargarDatosDesdeCSV() throws IOException{
        InputStream inputViaje = getClass().getResourceAsStream("/csv/Viaje.csv");
        InputStream inputPausa = getClass().getResourceAsStream("/csv/Pausa.csv");
        InputStream inputPrecio = getClass().getResourceAsStream("/csv/Precio.csv");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter formatterPrecio = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        if (inputViaje == null) {
            throw new IOException("Archivo Viaje.csv no encontrado en resources.");
        }
        if (inputPausa == null) {
            throw new IOException("Archivo Pausa.csv no encontrado en resources.");
        }
        if (inputPrecio == null) {
            throw new IOException("Archivo Precio.csv no encontrado en resources.");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputPrecio));
             CSVParser datosPrecio = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())){
            for (CSVRecord precio : datosPrecio){
                Precio p = new Precio();

                p.setId((Long.valueOf(precio.get("idPrecio"))));
                p.setValor(Double.parseDouble(precio.get("valor")));
                p.setFechaFacturacion(LocalDate.parse(precio.get("fechaFacturacion"),formatterPrecio));
                p.setValorPorPausaExtendida(Double.valueOf(precio.get("valorPorPausa")));
                p.setValorXkilometro(1000);
                this.precio.save(p);
            }
        }
        catch (IOException e){
            throw new IOException(e+ "no se pudo abrir la lectura de Precio.csv.");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputViaje));
             CSVParser datosViaje = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())){
            for (CSVRecord viaje : datosViaje){
                Viaje v = new Viaje();

                v.setId(Long.valueOf(viaje.get("idViaje")));
                v.setHoraInicio(LocalTime.parse(viaje.get("fechaInicio"),formatter));
                v.setHoraFin(LocalTime.parse(viaje.get("fechaFin"),formatter));
                v.setKmRecorridos(Double.parseDouble(viaje.get("kmRecorridos")));
                v.setFecha(LocalDate.parse(viaje.get("fecha"),formatterPrecio));
                Long monopatinId = Long.valueOf(viaje.get("monopatinId"));
                v.setIdMonopatin(monopatinId);

                Long precioId = Long.valueOf(viaje.get("precioId"));
                v.setPrecio(this.precio.findById(precioId).map(Precio:: new).orElse(null));

                this.viaje.save(v);
            }
        }
        catch (IOException e){
            throw new IOException(e+ "no se pudo abrir la lectura de Viaje.csv.");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputPausa));
             CSVParser datosPausa = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())){
            for (CSVRecord pausa : datosPausa){
                Pausa p = new Pausa();

                p.setId(Long.valueOf(pausa.get("idPausa")));
                p.setPausaTotal(Long.valueOf(pausa.get("pausaTotal")));
                p.setFechaInicio(LocalDate.parse(pausa.get("fechaInicio"),formatter));
                p.setFechaFin(LocalDate.parse(pausa.get("fechaFin"),formatter));

                Long viajeId = Long.valueOf(pausa.get("viajeId"));
                p.setViaje(this.viaje.findById(viajeId).map(Viaje :: new).orElse(null));

                this.pausa.save(p);
            }
        }
        catch (IOException e){
            throw new IOException(e+ "no se pudo abrir la lectura de Pausa.csv.");
        }



    }
}
