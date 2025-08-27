package micro.example.microservicio_admin.entity.clases;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Mantenimiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_monopatin;
    private LocalDate inicio;
    private LocalDate fin;
    private double km_recorridos;

    public Mantenimiento(Long id, Long id_monopatin, LocalDate inicio, LocalDate fin, int km_recorridos) {
        this.id = id;
        this.id_monopatin = id_monopatin;
        this.inicio = inicio;
        this.fin = fin;
        this.km_recorridos = km_recorridos;
    }

    public Mantenimiento() {

    }

    public Long getId() {
        return id;
    }

    public Long getId_monopatin() {
        return id_monopatin;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public double getKm_recorridos() {
        return km_recorridos;
    }


}

