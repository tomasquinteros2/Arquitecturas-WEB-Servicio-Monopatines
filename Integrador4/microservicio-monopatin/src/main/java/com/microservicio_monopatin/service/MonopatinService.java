package com.microservicio_monopatin.service;

import com.microservicio_monopatin.dto.EstadoMonopatinDTO;
import com.microservicio_monopatin.model.Monopatin;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.microservicio_monopatin.repository.MonopatinRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/monopatines")
public class MonopatinService {
    @Autowired
    private MonopatinRepository monopatinRepository;

    @Autowired
    public MonopatinService(MonopatinRepository monopatinRepo) {
        this.monopatinRepository = monopatinRepo;
    }

    @Transactional
    public List<Monopatin> findAll() throws Exception {
        return monopatinRepository.findAll();
    }
    @Transactional
    public Monopatin findById(Long id) throws Exception {
        return monopatinRepository.findById(id).orElse(null);
    }

    @Transactional
    public Monopatin save(Monopatin entity) throws Exception {
        monopatinRepository.save(entity);
        return this.findById(entity.getId());
    }

    @Transactional
    public Monopatin update(Long id, Monopatin updatedUsuario) throws ChangeSetPersister.NotFoundException {
        Optional<Monopatin> paradaOptional = monopatinRepository.findById(id);

        if (paradaOptional.isPresent()) {
            Monopatin mono = paradaOptional.get();
            mono.setKilometraje(mono.getKilometraje());
            mono.setNumeroSerie(mono.getNumeroSerie());
            mono.setEnMantenimiento(mono.isEnMantenimiento());
//            mono.setEstado(mono.getEs());
            return monopatinRepository.save(mono);
        } else {
            return null;
        }
    }

    @Transactional
    public ResponseEntity<?> delete(Long id) throws Exception {
        if (monopatinRepository.existsById(id)) {
            monopatinRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La parada con ID " + id + " no existe");
        }
    }
    @Transactional
    public EstadoMonopatinDTO getComparacionEstados() {
        return monopatinRepository.getComparacionEstados();
    }
}
