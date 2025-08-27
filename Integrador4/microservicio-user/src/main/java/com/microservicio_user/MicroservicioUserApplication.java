package com.microservicio_user;

import com.microservicio_user.util.CargaDeDatos;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;

@EnableFeignClients
@SpringBootApplication
public class MicroservicioUserApplication {

	@Autowired
	CargaDeDatos cargaDeDatos;

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioUserApplication.class, args);
	}

	@PostConstruct
	public void init() throws IOException {
		if(cargaDeDatos.isTablaVacia()){
			cargaDeDatos.cargarDatosDesdeCSV();
		}
	}

}
