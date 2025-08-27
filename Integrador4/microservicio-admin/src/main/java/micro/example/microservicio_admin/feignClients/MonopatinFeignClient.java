package micro.example.microservicio_admin.feignClients;

import micro.example.microservicio_admin.dto.EstadoMonopatinDTO;
import micro.example.microservicio_admin.entity.clases.Monopatin;
import micro.example.microservicio_admin.dto.MonopatinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="microservicio-monopatin")
public interface MonopatinFeignClient {

        @GetMapping("monopatines/{id}")
        Monopatin getMonopatinById(@PathVariable Long id);

        @PostMapping("monopatines/editar/{id}")
        ResponseEntity<Monopatin> updateMonopatin(@PathVariable Long id, Monopatin monopatin);

        @PostMapping("monopatines")
        ResponseEntity<MonopatinDTO> saveMonopatin(@RequestBody MonopatinDTO monopatin);

        @DeleteMapping("monopatines/{id}")
        ResponseEntity<Void> deleteMonopatin(@PathVariable Long id);

        @GetMapping("/monopatines/comparacionEstados")
        ResponseEntity<EstadoMonopatinDTO> comparacionEstados();
}





