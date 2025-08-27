package micro.example.microservicio_admin.dto;


import lombok.Getter;
import micro.example.microservicio_admin.entity.Administrador;

import java.io.Serializable;

@Getter
public class AdministradorDTO implements Serializable {

        private Long id;

        private String nombre;


        public AdministradorDTO(Long id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public AdministradorDTO(Administrador a) {
            this.id = a.getId();
            this.nombre = a.getNombre();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }
}
