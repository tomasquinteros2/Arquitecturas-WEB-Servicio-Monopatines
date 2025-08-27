package micro.example.microservicio_admin.feignClients;

import micro.example.microservicio_admin.entity.clases.Parada;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="microservicioparada") // Cambiar solo el nombre aquí
public interface ParadaFeignClient {

        @GetMapping("paradas/{id}")
        Parada getParadaById(@PathVariable Long id);

        @PostMapping("paradas/editar/{id}")
        ResponseEntity<Parada> updateParada(@PathVariable Long id, Parada parada);

        @PostMapping("paradas")
        ResponseEntity<Parada> createParada(@RequestBody Parada parada);

        @DeleteMapping("paradas/{id}")
        ResponseEntity<Void> deleteParada(@PathVariable Long id);

}
