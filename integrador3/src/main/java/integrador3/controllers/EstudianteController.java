package integrador3.controllers;

import integrador3.entities.Estudiante;
import integrador3.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("estudiantes")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Estudiante entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo crear el estudiante, revise los campos e intente nuevamente.\"}");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron recuperar los datos.\"}");
        }

    }

    @GetMapping("/lu/{lu}")
    public ResponseEntity<?> findByLu(@PathVariable("lu") int lu){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findByLU(lu));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron recuperar los datos.\"}");
        }
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<?> findByGenero(@PathVariable("genero") String genero){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findByGenero(genero));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron recuperar los datos.\"}");
        }
    }

    @GetMapping("carrerayciudad/{carrera}/{ciudad}")
    public ResponseEntity<?> findByCarreraCiudad(@PathVariable("carrera") int carrera, @PathVariable("ciudad") String ciudad){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findByCarreraCiudad(carrera, ciudad));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron recuperar los datos.\"}");
        }
    }
}
