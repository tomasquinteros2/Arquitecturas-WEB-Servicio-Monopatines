package com.microservicio_monopatin.controller;

import com.microservicio_monopatin.model.Monopatin;
import com.microservicio_monopatin.service.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/monopatines")
public class MonopatinController {

    private MonopatinService monopatinService;

    @Autowired
    public MonopatinController(MonopatinService monopatinService) {
        this.monopatinService = monopatinService;
    }



        @GetMapping("")
        public ResponseEntity<List<Monopatin>> getAllMonopatines() throws Exception {
            try{
                List<Monopatin> monopatin = monopatinService.findAll();
                return new ResponseEntity<>(monopatin, HttpStatus.OK);
            }
            catch (Exception e){
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<Monopatin> getMonopatinById(@PathVariable Long id) throws Exception {
            return ResponseEntity.ok(monopatinService.findById(id));
        }

        @PostMapping("")
        public ResponseEntity<?> createMonopatin(@RequestBody Monopatin entity){
            try{
                return ResponseEntity.status(HttpStatus.OK).body(monopatinService.save(entity));
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
            }
        }

        @PutMapping("/editar/{id}")
        public ResponseEntity<Monopatin> updateMonopatin(@PathVariable Long id, @RequestBody Monopatin paradaDetails) throws ChangeSetPersister.NotFoundException {
            Optional<Monopatin> updatedMonopatin = Optional.ofNullable(monopatinService.update(id, paradaDetails));
            return updatedMonopatin.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteMonopatin(@PathVariable Long id) throws Exception {
            try{
                return ResponseEntity.status(HttpStatus.OK).body(monopatinService.delete(id));
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
            }
        }
        @GetMapping("/comparacionEstados")
        public ResponseEntity<?> getComparacionEstados(){
            try {
                return ResponseEntity.status(HttpStatus.OK).body(monopatinService.getComparacionEstados());
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                        ".\"}");
            }
        }


    }