package micro.example.microservicio_admin.entity.clases;

import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
public class Pausa {

    private Long id;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private Long pausaTotal;

    private Viaje viaje;

    public Pausa() {}

    public Pausa(Pausa pausa) {
        this.id = pausa.id;
        this.fechaInicio =pausa.fechaInicio;
        this.fechaFin = pausa.fechaFin;
        this.pausaTotal = pausa.pausaTotal;
        this.viaje = pausa.viaje;
    }

    public Pausa(Long id, LocalDate fechaInicio, LocalDate fechaFin, Long pausaTotal, Viaje viaje) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.pausaTotal = pausaTotal;
        this.viaje = viaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pausa(Long id, LocalDate fechaInicio, LocalDate fechaFin, Viaje viaje) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.pausaTotal = 0L;
        this.viaje = viaje;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        //this.setCantidadPausa();
    }

    public Viaje getRegistroUsoMonopatin() {
        return viaje;
    }

    public Long getPausaTotal() {
        return pausaTotal;
    }

    public void setPausaTotal(Long pausaTotal) {
        this.pausaTotal = pausaTotal;
    }

    public Long getCantidadPausa(){
        return getFechaInicio().until(getFechaFin(), ChronoUnit.MINUTES);
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    /*public void setCantidadPausa(){
        this.pausaTotal = getFechaInicio().until(getFechaFin(), ChronoUnit.MINUTES);
    }*/
}
