package micro.example.microservicio_admin.entity.clases;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Viaje {
    private Long id;

    private Monopatin monopatin;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double kilometrosRecorridos;

    private List<Pausa> pausas;

    private Precio precio;

    public Viaje(Viaje viaje) {
        this.id = viaje.id;
        this.monopatin = viaje.monopatin;
        this.fechaInicio = viaje.fechaInicio;
        this.fechaFin = viaje.fechaFin;
        this.kilometrosRecorridos = viaje.kilometrosRecorridos;
        this.pausas = viaje.pausas;
        this.precio = viaje.precio;
    }

    public Viaje() {

    }

    public Viaje(Long id, Monopatin monopatin, LocalDate fechaInicio, LocalDate fechaFin, double kilometrosRecorridos, List<Pausa> pausas, Precio precio) {
        this.id = id;
        this.monopatin = monopatin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.kilometrosRecorridos = kilometrosRecorridos;
        this.pausas = pausas;
        this.precio = precio;
    }

    public Monopatin getMonopatin() {
        return monopatin;
    }

    public void setMonopatin(Monopatin monopatin) {
        this.monopatin = monopatin;
    }

    public List<Pausa> getPausas() {
        return pausas;
    }

    public void setPausas(List<Pausa> pausas) {
        this.pausas = pausas;
    }

    public Precio getPrecio() {
        return precio;
    }

    public void setPrecio(Precio precio) {
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Monopatin getMonopatinId() {
        return monopatin;
    }

    public void setMonopatinId(Monopatin m) {
        this.monopatin = m;
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
    }

    public double getKilometrosRecorridos() {
        return kilometrosRecorridos;
    }

    public void setKilometrosRecorridos(double kilometrosRecorridos) {
        this.kilometrosRecorridos = kilometrosRecorridos;
    }
}
