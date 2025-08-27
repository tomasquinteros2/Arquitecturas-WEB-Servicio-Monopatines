package micro.example.microservicio_admin.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class EstadoMonopatinDTO implements Serializable {
    private Long enOperacion;
    private Long enMantenimiento;


    public EstadoMonopatinDTO() {
    }

    public EstadoMonopatinDTO(Long enOperacion, Long enMantenimiento) {
        this.enOperacion = enOperacion;
        this.enMantenimiento = enMantenimiento;
    }
    // getters y setters

    public Long getEnOperacion() {
        return enOperacion;
    }

    public void setEnOperacion(Long enOperacion) {
        this.enOperacion = enOperacion;
    }

    public Long getEnMantenimiento() {
        return enMantenimiento;
    }

    public void setEnMantenimiento(Long enMantenimiento) {
        this.enMantenimiento = enMantenimiento;
    }
}