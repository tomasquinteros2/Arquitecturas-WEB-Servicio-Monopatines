package micro.example.microservicio_admin.service;

import lombok.RequiredArgsConstructor;
import micro.example.microservicio_admin.dto.*;
import micro.example.microservicio_admin.entity.Administrador;
import micro.example.microservicio_admin.entity.clases.Monopatin;
import micro.example.microservicio_admin.entity.clases.Parada;
import micro.example.microservicio_admin.entity.clases.Precio;
import micro.example.microservicio_admin.feignClients.*;
import micro.example.microservicio_admin.repository.AdministracionRepo;
import micro.example.microservicio_admin.entity.clases.Mantenimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicioAdministracion {

    @Autowired
    private AdministracionRepo ar;

    @Autowired
    MonopatinFeignClient monopatinFeignClient;

    @Autowired
    MantenimientoFeignClient mantenimientoFeignClient;

    @Autowired
    ParadaFeignClient paradaFeignClient;

    @Autowired
    ViajeFeignClient viajeFeignClient;

    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    public ServicioAdministracion(AdministracionRepo ar,
                                  MonopatinFeignClient monopatinFeignClient, MantenimientoFeignClient mantenimientoFeignClient,
                                  ParadaFeignClient paradaFeignClient, ViajeFeignClient viajeFeignClient,
                                  UserFeignClient userFeignClient) {
        this.ar = ar;
        this.monopatinFeignClient = monopatinFeignClient;
        this.mantenimientoFeignClient = mantenimientoFeignClient;
        this.paradaFeignClient = paradaFeignClient;
        this.viajeFeignClient = viajeFeignClient;
        this.userFeignClient = userFeignClient;
    }

    @Transactional
    public ResponseEntity<?> getReporteKilometraje(Long limite, boolean incluirPausas) {
        try {
            ResponseEntity<List<ReporteKilometrajeDTO>> response = viajeFeignClient.getReporteKilometraje(limite, incluirPausas);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage() + " fallo");
        }
    }

    @Transactional
    public ResponseEntity<?> ajustarPreciosPorFecha(
          double valor, String fechaAHabilitar) {
        return viajeFeignClient.ajustarPreciosPorFecha(valor, fechaAHabilitar);
    }

    @Transactional
    public ResponseEntity<List<MonopatinViajeDTO>> findMonopatinesConMasDeXViajesPorAnio(int cantidad, int anio) {
            ResponseEntity<List<MonopatinViajeDTO>> response = viajeFeignClient.findMonopatinesConMasDeXViajesPorAnio(cantidad, anio);
            return response;
    }

    @Transactional
    public ResponseEntity<?> anularCuenta(Long id){
        try{
            return ResponseEntity.ok(userFeignClient.anularCuenta(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage()+"fallo");
        }
    }

    @Transactional
   public ResponseEntity<?> agregarPrecio(Precio precio) {
       return ResponseEntity.ok(viajeFeignClient.agregarPrecio(precio));

   }

    @Transactional
    public ResponseEntity<?> createParada(Parada p) {
    try{
        Parada paradaNueva = new Parada(p);

        ResponseEntity<Parada> response = paradaFeignClient.createParada(paradaNueva);
        return ResponseEntity.ok(response.getBody());
    }
    catch (Exception e){
        return ResponseEntity.badRequest().body(e.getMessage()+"fallo");
        }
    }

    @Transactional
    public ResponseEntity<String> deleteParada(Long id) {
        try {
            paradaFeignClient.deleteParada(id);
            return ResponseEntity.ok("Parada eliminada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error del servidor al intentar eliminar la parada.");
        }
    }

    @Transactional
    public ResponseEntity addMonopatin(MonopatinDTO monopatin){
        ResponseEntity<MonopatinDTO> response = monopatinFeignClient.saveMonopatin(monopatin);
        return ResponseEntity.ok(response.getBody());
    }

    @Transactional
    public ResponseEntity<String> deleteMonopatin(Long id) {
        try {
            monopatinFeignClient.deleteMonopatin(id);
            return ResponseEntity.ok("Monopatín eliminado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error del servidor al intentar eliminar el monopatín.");
        }
    }

    @Transactional
    public ResponseEntity settearMonopatinAMantenimiento(Long id) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        Monopatin monopatin = monopatinFeignClient.getMonopatinById(id);
        if(monopatin != null) {
            if (!monopatin.isEnMantenimiento()) {
                this.agregarMantenimiento(monopatin);
                monopatin.setEnMantenimiento(true);
                ResponseEntity<Monopatin> response2 = monopatinFeignClient.updateMonopatin(id,monopatin);
                return response2;
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El monopatin ya está en mantenimiento");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el monopatin a dar de baja");
    }

    @Transactional
    public ResponseEntity<?> agregarMantenimiento(Monopatin monopatin) {

        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setId_monopatin(monopatin.getId());
        mantenimiento.setInicio(LocalDate.now());
        mantenimiento.setFin(null);
        mantenimiento.setKm_recorridos(monopatin.getKilometraje());

        ResponseEntity<Mantenimiento> response = mantenimientoFeignClient.agregarMantenimiento(mantenimiento);
        return response;
    }
    @Transactional
    public List<AdministradorDTO> findAll() throws Exception {
        return ar.findAll().stream().map(AdministradorDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public AdministradorDTO findById(Long id) throws Exception {
        return ar.findById(id).map(AdministradorDTO::new).orElse(null);
    }

    @Transactional
    public AdministradorDTO save(Administrador entity) throws Exception {
        ar.save(entity);
        return this.findById(entity.getId().longValue());
    }

    @Transactional
    public Administrador update(Long id, Administrador updatedAdministrador) throws Exception {
        Optional<Administrador> optionalAdministrador = ar.findById(id);

        if (optionalAdministrador.isPresent()) {
            Administrador existingAdministrador = optionalAdministrador.get();

            existingAdministrador.setNombre(updatedAdministrador.getNombre());

            ar.save(existingAdministrador);

            return existingAdministrador;
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Transactional
    public ResponseEntity<String> delete(Long id) throws Exception {
        if (ar.existsById(id)) {
            ar.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La entidad con ID " + id + " no existe");
        }
    }

    @Transactional
    public ResponseEntity<?> getTotalFacturadoEntreMeses(int anio, int mesInicio, int mesFin) {
        try {
            ResponseEntity<Integer> response = viajeFeignClient.getTotalFacturadoEntreMeses(anio, mesInicio,mesFin);
            FacturadoDTO informe = new FacturadoDTO();
            informe.setTotalFacturado(response.getBody());
            if(response.getBody()==null){
                return ResponseEntity.badRequest().body("No se recaudo en el periodo indicado");
            }
            else{
                return ResponseEntity.ok(informe);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage() + " fallo");
        }
    }
    @Transactional
    public ResponseEntity<?> getComparacionEstados(){
        try{
            ResponseEntity<EstadoMonopatinDTO> response = monopatinFeignClient.comparacionEstados();
            EstadoMonopatinDTO informe = response.getBody();
            return ResponseEntity.ok(informe);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}


