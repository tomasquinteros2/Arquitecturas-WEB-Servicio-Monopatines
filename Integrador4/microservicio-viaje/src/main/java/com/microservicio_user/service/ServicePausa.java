package com.microservicio_user.service;


import com.microservicio_user.entity.Pausa;
import com.microservicio_user.repository.RepositoryPausa;
import com.microservicio_user.dto.PausaDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicePausa {
    private RepositoryPausa pr;

    @Autowired
    public ServicePausa(RepositoryPausa pr){
        this.pr=pr;
    }

    @Transactional
    public PausaDTO findById(Long id) {
        return pr.findById(id).map(PausaDTO::new).orElse(null);
    }

    @Transactional
    public List<PausaDTO> findAll() throws Exception {
        return pr.findAll().stream().map(PausaDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public PausaDTO save(Pausa entity) throws Exception {
        pr.save(entity);
        return this.findById(entity.getId());
    }

    @Transactional
    public PausaDTO update(Long id, Pausa updatedPausa) throws Exception {
        return this.save(updatedPausa);
    }
    @org.springframework.transaction.annotation.Transactional
    public ResponseEntity<String> delete(Long id) throws Exception {
        if (pr.existsById(id)) {
            pr.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La entidad con ID " + id + " no existe");
        }
    }
}
