package com.microservicio_user.feignClient;

import com.microservicio_user.services.dto.ParadaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservicioparada")
public interface ParadaFeignClient {
    @GetMapping("/paradas/monopatinesCercanos/{x}/{y}")
    ResponseEntity<List<ParadaDTO>> getMonopatinesCercanos(@PathVariable double x, @PathVariable double y);

}