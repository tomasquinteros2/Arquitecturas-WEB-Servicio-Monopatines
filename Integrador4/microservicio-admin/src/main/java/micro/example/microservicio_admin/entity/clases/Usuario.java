package micro.example.microservicio_admin.entity.clases;

import lombok.Data;

import java.util.List;

@Data
public class Usuario {
    
    private long id;

    private String cel;
    private String Email;
    private String nombre;
    private String apellido;

    private double x;

    private double y;

    private List<Cuenta> cuenta;


    public Usuario(Usuario usuario) {
        this.id = usuario.id;
        this.cel = usuario.cel;
        Email = usuario.Email;
        this.nombre = usuario.nombre;
        this.apellido = usuario.apellido;
        this.x = usuario.x;
        this.y = usuario.y;
        this.cuenta = usuario.cuenta;
    }

    public Usuario(long id, String cel, String email, String nombre, String apellido, double x, double y, List<Cuenta> cuenta) {
        this.id = id;
        this.cel = cel;
        Email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.x = x;
        this.y = y;
        this.cuenta = cuenta;
    }

    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }


    public Usuario(){

    }

    public long getId() {
        return id;
    }


    public String getCel() {
        return cel;
    }


    public String getEmail() {
        return Email;
    }


    public String getNombre() {
        return nombre;
    }


    public String getApellido() {
        return apellido;
    }


    public List<Cuenta> getCuenta() {
        return cuenta;
    }

}
