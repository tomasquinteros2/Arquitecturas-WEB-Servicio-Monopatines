package com.microservicio_user.services;

import com.microservicio_user.entity.Cuenta;
import com.microservicio_user.repository.CuentaRepository;
import com.microservicio_user.services.dto.CuentaDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaService {
    @Autowired
    private CuentaRepository cr;

    @Autowired
    public CuentaService(CuentaRepository cr){
        this.cr = cr;
    }
    @Transactional
    public CuentaDTO findById(Long id) {
        CuentaDTO cuenta = cr.findById(id).map(CuentaDTO::new).orElse(null);
        System.out.println(cuenta);
        return cr.findById(id).map(CuentaDTO::new).orElse(null);
    }

    @Transactional
    public List<CuentaDTO> findAll() throws Exception {
        return cr.findAll().stream().map(CuentaDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public CuentaDTO save(Cuenta entity) throws Exception {
        cr.save(entity);
        return this.findById(entity.getId().longValue());
    }

    @Transactional
    public CuentaDTO update(Long id, Cuenta entity) throws Exception {
        return this.save(entity);
    }

    @Transactional
    public ResponseEntity<?> delete(Long id) throws Exception {
        if (cr.existsById(id)) {
            cr.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La entidad con ID " + id + " no existe");
        }
    }

    @Transactional
    public CuentaDTO anularCuenta(Long id) throws Exception {
        CuentaDTO response = this.findById(id);
        Cuenta cuenta = new Cuenta(response.getId(), response.getDate(), true);
        return  this.save(cuenta);
    }

    @Transactional
    public CuentaDTO habilitarCuenta(Long id) throws Exception {
        CuentaDTO response = this.findById(id);
        Cuenta cuenta = new Cuenta(response.getId(), response.getDate());
        return this.save(cuenta);
    }
}
