package micro.example.microservicio_admin.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="microservicio-user")
public interface UserFeignClient {

    @PutMapping("/cuentas/anular/{id}")
    ResponseEntity<?> anularCuenta(@PathVariable Long id);

}