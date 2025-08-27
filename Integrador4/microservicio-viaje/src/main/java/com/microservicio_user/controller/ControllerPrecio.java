package com.microservicio_user.controller;


import com.microservicio_user.dto.PrecioDTO;
import com.microservicio_user.entity.Precio;
import com.microservicio_user.service.ServicioPrecio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/precios")
public class ControllerPrecio {
    
    private ServicioPrecio sp;

    @Autowired
    public ControllerPrecio(ServicioPrecio sp) {
        this.sp = sp;
    }

    @PutMapping("/editar/habilitar/{fechaAHabilitar}/{valor}")
    public ResponseEntity<?> ajustarPreciosPorFecha(@PathVariable double valor,
                                                    @PathVariable LocalDate fechaAHabilitar) {
        List<PrecioDTO> preciosActualizados = sp.ajustarPreciosPorFecha(valor, fechaAHabilitar);
        if (preciosActualizados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron precios para actualizar");
        }
        return ResponseEntity.status(HttpStatus.OK).body(preciosActualizados);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregarPrecio(@RequestBody Precio p){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sp.save(p));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar el precio, revise los campos e intente nuevamente.\"}");
        }
    }
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sp.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sp.findById(id));
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Precio entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sp.update(id,entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, o no se encontró el ID. Revise los campos e intente nuevamente.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(sp.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar. Asegurese que el objeto a eliminar no tenga una relacion.\"}");
        }
    }

}
