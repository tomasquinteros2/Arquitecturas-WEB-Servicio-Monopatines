package com.microservicio_user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Cuenta {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    @Column
    private LocalDate date;
    @Setter
    @Getter
    @Column
    private boolean anulada;

    @JsonIgnore
    @ManyToMany( fetch = FetchType.LAZY, mappedBy = "cuentas" )
    private Set<User> users;

    public Cuenta(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
        this.anulada = false;
    }
    public Cuenta(Long id, LocalDate date, boolean anulada) {
        this.id = id;
        this.date = date;
        this.anulada = anulada;
    }

    public Cuenta() {this.anulada = false;}

    public void setUsers(Set<User> usuarios) {
        this.users = usuarios;
    }
}
