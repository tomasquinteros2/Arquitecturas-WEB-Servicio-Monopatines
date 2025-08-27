package com.microservicio_user.service;


import com.microservicio_user.entity.Precio;
import com.microservicio_user.repository.RepositoryPrecio;
import com.microservicio_user.dto.PrecioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioPrecio {
    private RepositoryPrecio pr;

    @Autowired
    public ServicioPrecio(RepositoryPrecio pr) {
        this.pr = pr;
    }

    @Transactional
    public List<PrecioDTO> ajustarPreciosPorFecha(double valor, LocalDate fechaAHabilitar) {
        List<Precio> precios = pr.findByFechaFacturacionAfter(fechaAHabilitar);

        List<PrecioDTO> result = new ArrayList<>();
        if (precios.isEmpty()) {
            return result;
        }

        for (Precio pre : precios) {
            pre.setValorXkilometro(valor);
            PrecioDTO response = new PrecioDTO(pre);
            result.add(response);
        }

        pr.saveAll(precios);
        return result;
    }

    @Transactional
    public List<PrecioDTO> findAll() throws Exception {
        return pr.findAll().stream().map(PrecioDTO::new).collect(Collectors.toList());
    }
    @Transactional
    public PrecioDTO findById(Long id) throws Exception {
        return pr.findById(id).map(PrecioDTO::new).orElse(null);
    }
    @Transactional
    public PrecioDTO save(Precio p) throws Exception {
        pr.save(p);
        return this.findById(p.getId());
    }

    @Transactional
    public Precio update(Long id, Precio updatedPrecio) throws ChangeSetPersister.NotFoundException {
        Precio existingPrecio = pr.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        // Actualizar los campos necesarios
        existingPrecio.setValor(updatedPrecio.getValor());
        existingPrecio.setFechaFacturacion(updatedPrecio.getFechaFacturacion());
        existingPrecio.setValorPorPausaExtendida(updatedPrecio.getValorPorPausaExtendida());

        // Guardar la entidad actualizada
        return pr.save(existingPrecio);
    }
    @Transactional
    public ResponseEntity<String> delete(Long id) throws Exception {
        if (pr.existsById(id)) {
            pr.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La entidad con ID " + id + " no existe");
        }
    }
}