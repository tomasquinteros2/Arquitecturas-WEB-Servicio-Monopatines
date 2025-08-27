package com.microservicio_mantenimiento.controller;

import com.microservicio_mantenimiento.entity.Mantenimiento;
import com.microservicio_mantenimiento.service.MantenimientoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoController {

    private MantenimientoServicio ms;

    @Autowired
    public MantenimientoController(MantenimientoServicio ms) {
        this.ms = ms;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarMantenimiento(@RequestBody Mantenimiento m) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ms.save(m));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarMantenimiento(@PathVariable Long id, @RequestBody Mantenimiento m) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ms.update(id, m));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, o no se encontró el ID. Revise los campos e intente nuevamente.\"}");
        }
    }

    @GetMapping("/getMonopatin/{idMonopatin}")
    public ResponseEntity<?> getMantenimientoPorIdMonopatin(@PathVariable Long idMonopatin) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ms.findByIdMonopatin(idMonopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ms.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(ms.findById(id));
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(ms.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }

}
