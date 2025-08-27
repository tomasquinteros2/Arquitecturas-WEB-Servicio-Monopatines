package com.microservicioparada.controller;

import com.microservicioparada.dto.ParadaDTO;
import com.microservicioparada.model.Parada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.RequestMapping;
import com.microservicioparada.service.ParadaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paradas")
public class ParadaController {

    private ParadaService paradaService;

    @Autowired
    public ParadaController(ParadaService paradaService) {
        this.paradaService = paradaService;
    }

    @GetMapping("")
    public ResponseEntity<List<Parada>> getAllParadas() throws Exception {
        try{
            List<Parada> paradas = paradaService.findAll();
            return new ResponseEntity<>(paradas, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/monopatinesCercanos/{x}/{y}")
    public ResponseEntity<?> getMonopatinesCercanos(@PathVariable double x, @PathVariable double y){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(paradaService.getMonopatinesCercanos(x,y));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parada> getParadaById(@PathVariable Long id) throws Exception {
        Optional<Parada> parada = Optional.ofNullable(paradaService.findById(id));
        return parada.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<?> createParada(@RequestBody Parada entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(paradaService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parada> updateParada(@PathVariable Long id, @RequestBody Parada paradaDetails) throws ChangeSetPersister.NotFoundException {
        Optional<Parada> updatedParada = Optional.ofNullable(paradaService.update(id, paradaDetails));
        return updatedParada.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParada(@PathVariable Long id) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(paradaService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }
}
