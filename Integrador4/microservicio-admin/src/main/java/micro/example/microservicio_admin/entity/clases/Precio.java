package micro.example.microservicio_admin.entity.clases;


import lombok.Data;

import java.time.LocalDate;


@Data
public class Precio {

    private Long id;
    private double valor;
    private LocalDate fechaFacturacion;
    private Double valorPorPausaExtendida;
    private Viaje viaje;

    public Precio(Long id, double valor, LocalDate fechaFacturacion, Viaje viaje) {
        this.id = id;
        this.valor = valor;
        this.fechaFacturacion = fechaFacturacion;
        this.valorPorPausaExtendida = 0.0;
        this.viaje = viaje;
    }

    public Precio() {

    }

    public Precio(Precio precio) {
        this.id = precio.id;
        this.valor = precio.valor;
        this.fechaFacturacion = precio.fechaFacturacion;
        this.valorPorPausaExtendida = precio.valorPorPausaExtendida;
        this.viaje = precio.viaje;
    }

    public Precio(Long id, double valor, LocalDate fechaFacturacion, Double valorPorPausaExtendida, Viaje viaje) {
        this.id = id;
        this.valor = valor;
        this.fechaFacturacion = fechaFacturacion;
        this.valorPorPausaExtendida = valorPorPausaExtendida;
        this.viaje = viaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorPorPausaExtendida() {
        return valorPorPausaExtendida;
    }

    public void setValorPorPausaExtendida(Double valorPorPausaExtendida) {
        this.valorPorPausaExtendida = valorPorPausaExtendida;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getFechaInicio() {
        return fechaFacturacion;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaFacturacion = fechaInicio;
    }
}
