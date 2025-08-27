package micro.example.microservicio_admin;

import jakarta.annotation.PostConstruct;
import micro.example.microservicio_admin.util.CargaDeDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicioAdminApplication {

    @Autowired
    CargaDeDatos cargaDeDatos;

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioAdminApplication.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        if(cargaDeDatos.isTablaVacia()){
            cargaDeDatos.cargarDatosDesdeCSV();
        }
    }
}
