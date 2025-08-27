package com.microservicio_mantenimiento;

import com.microservicio_mantenimiento.util.CargaDeDatos;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MicroservicioMantenimiento {
	@Autowired
	CargaDeDatos cargaDeDatos;

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioMantenimiento.class, args);
	}

	@PostConstruct
	public void init() throws IOException {
		if(cargaDeDatos.isTablaVacia()){
			cargaDeDatos.cargarDatosDesdeCSV();
		}
	}
}
