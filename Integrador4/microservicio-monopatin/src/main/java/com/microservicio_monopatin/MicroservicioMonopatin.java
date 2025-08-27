package com.microservicio_monopatin;

import com.microservicio_monopatin.util.CargaDeDatos;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MicroservicioMonopatin {
	@Autowired
	CargaDeDatos cargaDeDatos;

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioMonopatin.class, args);
	}

	@PostConstruct
	public void init() throws IOException {
		if(cargaDeDatos.isTablaVacia()){
			cargaDeDatos.cargarDatosDesdeCSV();
		}
	}
}
