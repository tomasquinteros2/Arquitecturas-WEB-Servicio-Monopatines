package micro.example.microservicio_admin.feignClients;

import micro.example.microservicio_admin.entity.clases.Mantenimiento;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="microservicio-mantenimiento")
public interface MantenimientoFeignClient {
    @PostMapping("mantenimiento/agregar")
    ResponseEntity<Mantenimiento> agregarMantenimiento(@RequestBody Mantenimiento mantenimiento);
}
