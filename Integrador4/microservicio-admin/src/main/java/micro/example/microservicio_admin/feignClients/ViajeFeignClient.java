package micro.example.microservicio_admin.feignClients;

import micro.example.microservicio_admin.dto.MonopatinViajeDTO;
import micro.example.microservicio_admin.dto.ReporteKilometrajeDTO;
import micro.example.microservicio_admin.dto.ViajePrecioDTO;
import micro.example.microservicio_admin.entity.clases.Precio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name="microservicio-viaje")
public interface ViajeFeignClient {

    @PostMapping("/precios/agregar")
    ResponseEntity<?> agregarPrecio(@RequestBody Precio p);

    @GetMapping("/viajes/{cantidad}/{anio}")
    ResponseEntity<List<MonopatinViajeDTO>> findMonopatinesConMasDeXViajesPorAnio(@PathVariable int cantidad, @PathVariable int anio);

    @GetMapping("/viajes/getReporteKilometraje/{limite}/{incluirPausas}")
    ResponseEntity<List<ReporteKilometrajeDTO>> getReporteKilometraje(@PathVariable Long limite, @PathVariable boolean incluirPausas);

    @GetMapping("/viajes/facturado/{anio}/{mesInicio}/{mesFin}")
    ResponseEntity<Integer> getTotalFacturadoEntreMeses(@PathVariable int anio, @PathVariable int mesInicio, @PathVariable int mesFin);

    @PutMapping("/precios/editar/habilitar/{fechaAHabilitar}/{valor}")
    ResponseEntity<?> ajustarPreciosPorFecha(@PathVariable double valor,
                                                    @PathVariable String fechaAHabilitar);
}
