package Integrador.dao;

import Integrador.entities.Cliente;
import Integrador.entities.Factura_Producto;
import Integrador.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Factura_ProductoDAO {
    private Connection conn;
    public Factura_ProductoDAO(Connection conn) {
        this.conn = conn;
    }
    public void insert(Factura_Producto facturaProducto) throws Exception {
        String query = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, facturaProducto.getIdFactura()); // idF
            ps.setInt(2, facturaProducto.getIdProducto()); // idP
            ps.setInt(3, facturaProducto.getCantidad()); // cantidad
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
    public boolean delete(Integer idFactura, Integer idProducto) {
        if(find(idFactura, idProducto) == null)
            return false;
        String query = "DELETE FROM Factura_Producto WHERE idFactura = ? and idProducto = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);
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
    public Factura_Producto find(Integer idFactura, Integer idProducto) {
        String query = "SELECT idFactura, idProducto, cantidad " +
                        "FROM Factura_Producto " +
                        "WHERE idFactura = ? and idProducto = ?";
        Factura_Producto factura_producto = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);
            rs = ps.executeQuery();
            if (rs.next()) {
                Integer cantidad = rs.getInt("cantidad");
                factura_producto = new Factura_Producto(idFactura, idProducto, cantidad);
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
        return factura_producto;
    }
    public boolean update(Factura_Producto dao) {
        if(find(dao.getIdFactura(), dao.getIdProducto()) == null)
            return false;
        String query = "UPDATE Factura_Producto " +
                "SET cantidad = ? " +
                "WHERE idFactura = ? and idProducto = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, dao.getCantidad());
            ps.setInt(2, dao.getIdFactura());
            ps.setInt(3, dao.getIdProducto());
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
}
