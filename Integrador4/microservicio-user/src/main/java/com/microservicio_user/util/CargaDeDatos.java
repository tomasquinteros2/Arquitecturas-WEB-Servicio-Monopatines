package com.microservicio_user.util;

import com.microservicio_user.entity.Cuenta;
import com.microservicio_user.entity.User;
import com.microservicio_user.repository.CuentaRepository;
import com.microservicio_user.repository.UserRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Component
public class CargaDeDatos {

    UserRepository ur;
    CuentaRepository cr;


    @Autowired
    public CargaDeDatos(UserRepository ur, CuentaRepository cr) {
        this.ur = ur;
        this.cr = cr;
    }

    public boolean isTablaVacia() {
        // Verifica si la tabla 'viaje' está vacía
        return ur.count() == 0 && cr.count() == 0;
    }

    public void cargarDatosDesdeCSV() throws IOException {
        // Utiliza InputStream para cargar el archivo desde el classpath
        InputStream inputStream = getClass().getResourceAsStream("/usuarios.csv");
        InputStream inputStream1 = getClass().getResourceAsStream("/cuentas.csv");

        if (inputStream == null) {
            throw new IOException("Archivo usuarios.csv no encontrado en resources.");
        }
        if (inputStream1 == null) {
            throw new IOException("Archivo cuentas.csv no encontrado en resources.");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream1));
             CSVParser datosCuentas = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (CSVRecord cuenta : datosCuentas){
                Cuenta c = new Cuenta();

                c.setId(Long.valueOf(cuenta.get("idCuenta")));
                c.setAnulada(Boolean.parseBoolean(cuenta.get("anulada")));
                c.setDate(LocalDate.parse(cuenta.get("date"),formatter));

                Long usuarioId = Long.valueOf(cuenta.get("usuarioId"));
                Set<User> usuariosSet = new HashSet<>();
                usuariosSet.add(this.ur.findById(usuarioId).map(User::new).orElse(null));
                c.setUsers(usuariosSet);

                cr.save(c);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // Lee el archivo CSV
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             CSVParser datosUsuarios = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())){
            for (CSVRecord usuario : datosUsuarios) {
                User u = new User();

                u.setId(Long.parseLong(usuario.get("idUsuario")));
                u.setTelefono(usuario.get("telefono"));
                u.setApellido(usuario.get("apellido"));
                u.setNombre(usuario.get("nombre"));
                u.setEmail(usuario.get("email"));
                u.setPassword(usuario.get("password"));
                u.setX(Double.parseDouble(usuario.get("x")));
                u.setY(Double.parseDouble(usuario.get("y")));
                ur.save(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
