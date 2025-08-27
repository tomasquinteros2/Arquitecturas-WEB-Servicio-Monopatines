package micro.example.microservicio_admin.controller;

import lombok.RequiredArgsConstructor;
import micro.example.microservicio_admin.dto.MonopatinViajeDTO;
import micro.example.microservicio_admin.entity.Administrador;
import micro.example.microservicio_admin.entity.clases.Parada;
import micro.example.microservicio_admin.entity.clases.Precio;
import micro.example.microservicio_admin.service.ServicioAdministracion;
import micro.example.microservicio_admin.dto.MonopatinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/administrar")
@RequiredArgsConstructor
public class
AdministracionController {

    @Autowired
    private ServicioAdministracion sa;

    @Autowired
    public AdministracionController(ServicioAdministracion sa) {
        this.sa = sa;
    }

    /**
     * Como administrador quiero hacer un ajuste de precios, y que a aprtir de cierta fecha el sistema habilite
     * nuevos precios
     */
    @PutMapping("/precios/editar/habilitar/{fechaAHabilitar}/{valor}")
    public ResponseEntity<?> ajustarPreciosPorFecha(@PathVariable double valor,
                                                    @PathVariable String fechaAHabilitar) {
        return ResponseEntity.status(HttpStatus.OK).body(sa.ajustarPreciosPorFecha(valor, fechaAHabilitar));
    }


    /**
     * Como administrador quiero consultar los monopatines con mas de X viajes en un cierto anio.
     */
    @GetMapping("/{cantidad}/{anio}")
    public ResponseEntity<List<MonopatinViajeDTO>> findMonopatinesConMasDeXViajesPorAnio(
            @PathVariable int cantidad,
            @PathVariable int anio) {
        return ResponseEntity.status(HttpStatus.OK).body(sa.findMonopatinesConMasDeXViajesPorAnio(cantidad, anio).getBody());
    }

    /**
     * Como administrador quiero poder anular cuentas para ihabilitar el uso momentaneo de la misma.
     */
    @PutMapping("/cuentas/anular/{id}")
    public ResponseEntity<?> anularCuenta(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(sa.anularCuenta(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error. No se pudo anular la cuenta, revise los campos e intente nuevamente.\"}");
        }
    }

    @PostMapping("/precios/agregar")
    public ResponseEntity<?> agregarPrecio(@RequestBody  Precio p){
        return ResponseEntity.status(HttpStatus.OK).body(sa.agregarPrecio(p));
    }

    @DeleteMapping("/paradas/{id}")
    public ResponseEntity<?> eliminarParada(@PathVariable Long id) {
        try {
            return sa.deleteParada(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error. No se pudo eliminar la parada, revise los campos e intente nuevamente.\"}");
        }
    }

   @PostMapping("/paradas")
    public ResponseEntity<?> registrarParada(@RequestBody Parada entity) {
        try {
            return sa.createParada(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error. No se pudo agregar la parada.\"}");
        }
    }

    @PostMapping("/monopatines")
    public ResponseEntity<?> agregarMonopatin(@RequestBody MonopatinDTO entity) {
        try {
            return sa.addMonopatin(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error. No se pudo agregar el monopatin, revise los campos e intente nuevamente.\"}");
        }
    }

    @DeleteMapping("/monopatines/{id}")
    public ResponseEntity<?> eliminarMonopatin(@PathVariable Long id) {
        try {
            return sa.deleteMonopatin(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error. No se pudo eliminar el monopatin, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping("/monopatines/setearAMantenimiento/{idMonopatin}")
    public ResponseEntity<ResponseEntity> setearAMantenimiento (@PathVariable Long idMonopatin){
        return ResponseEntity.status(HttpStatus.OK).body(sa.settearMonopatinAMantenimiento(idMonopatin));
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sa.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sa.findById(id));
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Administrador entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sa.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Administrador entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sa.update(id,entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, o no se encontró el ID. Revise los campos e intente nuevamente.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sa.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }

    @GetMapping("/monopatines/reporte/kilometraje/{limite}/{incluirPausas}")
    public ResponseEntity<?> getReporteKilometraje(@PathVariable Long limite, @PathVariable boolean incluirPausas) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(sa.getReporteKilometraje(limite, incluirPausas));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo conseguir lo buscado intente nuevamente.\"}");

        }
    }

        @GetMapping("/viajes/totalFacturado/{anio}/{mesInicio}/{mesFin}")
    public ResponseEntity<?> getTotalFacturadoEntreMeses(@PathVariable int anio, @PathVariable int mesInicio, @PathVariable int mesFin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sa.getTotalFacturadoEntreMeses(anio, mesInicio, mesFin));
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo conseguir lo buscado intente nuevamente.\"}");

        }
    }
    @GetMapping("/monopatines/getComparacionEstados")
    public ResponseEntity<?> getComparacionEstados(){
        return ResponseEntity.status(HttpStatus.OK).body(sa.getComparacionEstados());
    }
}
