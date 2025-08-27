package Integrador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Integrador.dto.ClienteDTO;
import Integrador.entities.Cliente;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }
    public void insertCliente(Cliente cliente) {
        String query = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, cliente.getIdCliente()); // idPersona
            ps.setString(2, cliente.getNombre()); // nombre
            ps.setString(3, cliente.getEmail()); // edad
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean delete(Integer id) {
        if(find(id) == null)
            return false;
        String query = "DELETE FROM Cliente WHERE idCliente = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    public boolean update(Cliente dao) {
        if(find(dao.getIdCliente()) == null)
            return false;
        String query = "UPDATE Cliente " +
                "SET nombre = ?, email = ? " +
                "WHERE idCliente = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, dao.getNombre());
            ps.setString(2, dao.getEmail());
            ps.setInt(3, dao.getIdCliente());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    public Cliente find(Integer pk) {
        String query = "SELECT c.nombre, c.email " +
                        "FROM Cliente c " +
                        "WHERE c.idCliente = ?";
        Cliente clienteById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk); // Establecer el parámetro en la consulta SQL
            rs = ps.executeQuery();
            if (rs.next()) { // Verificar si hay resultados
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");

                // Crear una nueva instancia de Persona con los datos recuperados de la consulta
                clienteById = new Cliente(pk, nombre, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clienteById;
    }
    public List<ClienteDTO> findByMasFacturado() {
        String sql = "SELECT c.idCliente, c.nombre, c.email, " +
                    "COALESCE(SUM(p.valor * fp.cantidad), 0) AS totalFacturado " +
                    "FROM Cliente c " +
                    "LEFT JOIN Factura f ON c.idCliente = f.idCliente " +
                    "LEFT JOIN Factura_Producto fp ON f.idFactura = fp.idFactura " +
                    "LEFT JOIN Producto p ON fp.idProducto = p.idProducto " +
                    "GROUP BY c.idCliente, c.nombre, c.email " +//Agrupa los resultados por cliente para sumar el monto total facturado a cada uno.
                    "ORDER BY totalFacturado DESC;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ClienteDTO> list = new ArrayList<>();
        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int facturado = rs.getInt("totalFacturado");
                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                ClienteDTO cliente = new ClienteDTO(facturado,idCliente,nombre);
                list.add(cliente);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
