package com.microservicio_user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private LocalDate fechaAlta;

    @Column
    private double x;

    @Column
    private double y;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "rel_user_account",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn (name = "account_id")
    )
    private Set<Cuenta> cuentas;
    public User(){
        this.fechaAlta = LocalDate.now();
    }
    public User(Long id,String nombre, String telefono, String email, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.apellido = apellido;
        this.fechaAlta = LocalDate.now();
    }
    public User(String nombre, String telefono, String email, String apellido) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.apellido = apellido;
        this.fechaAlta = LocalDate.now();
    }

    public User(User user) {}
}