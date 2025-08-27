package integrador3.controllers;

import integrador3.entities.Carrera;
import integrador3.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping("/list")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getOne(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Carrera entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping("/{id}")
    Carrera replaceCarrera(@PathVariable int id, @RequestBody Carrera carrera) throws Exception {
        return carreraService.save(carrera);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(carreraService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }

   @GetMapping("/inscriptos")
   public ResponseEntity<?> findCarreraConInscriptos() {
       try{
           return ResponseEntity.status(HttpStatus.OK).body(carreraService.findCarreraConInscriptos());
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
       }
   }

    @GetMapping("/reporte")
    public ResponseEntity<?> getReporte() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.getReporte());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
