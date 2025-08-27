package Integrador.utils;

import Integrador.dao.FacturaDAO;
import Integrador.dao.Factura_ProductoDAO;
import Integrador.dao.ProductoDAO;
import Integrador.entities.Cliente;
import Integrador.entities.Factura;
import Integrador.entities.Factura_Producto;
import Integrador.entities.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import Integrador.dao.ClienteDAO;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelperMySQL {
    private Connection conn = null;

    public HelperMySQL() {//Constructor
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/arquiDB";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        //Funcion que cierra la conexion
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void dropTables() throws SQLException {
        String dropFactura_Producto = "DROP TABLE IF EXISTS Factura_Producto";
        this.conn.prepareStatement(dropFactura_Producto).execute();
        this.conn.commit();
        String dropFactura = "DROP TABLE IF EXISTS Factura";
        this.conn.prepareStatement(dropFactura).execute();
        this.conn.commit();
        //Funcion que elimina las tablas si existen
        String dropCliente = "DROP TABLE IF EXISTS Cliente";
        this.conn.prepareStatement(dropCliente).execute();
        this.conn.commit();

        String dropProducto = "DROP TABLE IF EXISTS Producto";
        this.conn.prepareStatement(dropProducto).execute();
        this.conn.commit();
    }

    public void createTables() throws SQLException {
        //Funcion que crea las tablas si no existen
        String tablaCliente = "CREATE TABLE IF NOT EXISTS Cliente (" +
                "idCliente INT NOT NULL, " +
                "nombre VARCHAR(500), " +
                "email VARCHAR(150), " +
                "CONSTRAINT Cliente_pk PRIMARY KEY (idCliente))";
        this.conn.prepareStatement(tablaCliente).execute();
        this.conn.commit();
        
        String tablaFactura = "CREATE TABLE IF NOT EXISTS Factura(" +
                "idFactura INT NOT NULL , " +
                "idCliente INT NOT NULL , " +
                "CONSTRAINT Factura_pk PRIMARY KEY (idFactura), " +
                "CONSTRAINT FK_Cliente_fk FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente)) ";
        this.conn.prepareStatement(tablaFactura).execute();
        this.conn.commit();

        String tablaProducto = "CREATE TABLE IF NOT EXISTS Producto(" +
                "idProducto INT NOT NULL , " +
                "nombre VARCHAR(45) , " +
                "valor FLOAT , " +
                "CONSTRAINT Producto_pk PRIMARY KEY (idProducto))";
        this.conn.prepareStatement(tablaProducto).execute();
        this.conn.commit();

        String tablaFactura_Producto = "CREATE TABLE IF NOT EXISTS Factura_Producto (" +
                "idFactura INT NOT NULL , " +
                "idProducto INT NOT NULL , " +
                "cantidad INT, " +
                "CONSTRAINT FK_Factura_fk FOREIGN KEY (idFactura) REFERENCES Factura (idFactura)," +
                "CONSTRAINT FK_Producto_fk FOREIGN KEY (idProducto) REFERENCES Producto (idProducto))";
        this.conn.prepareStatement(tablaFactura_Producto).execute();
        this.conn.commit();
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src\\main\\resources\\" + archivo;
        // String path = "src/main/resources/" + archivo; //para linux
        Reader in = new FileReader(path);
        String[] header = {};  // Puedes configurar tu encabezado personalizado aquí si es necesario
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void populateDB(ClienteDAO clientedao, ProductoDAO productodao, Factura_ProductoDAO facturaProductodao, FacturaDAO facturadao) throws Exception {
        //Metodo que recorre un archivo CSV y inserta los datos en las tablas indicadas
        try {
            System.out.println("Populating DB...");
            for(CSVRecord row : getData("clientes.csv")) {
                //idCliente,nombre,email
                if(row.size() >= 3) { // Verificar que hay al menos 4 campos en el CSVRecord
                    String idString = row.get(0);//obtiene el id de direccion
                    String nombreString = row.get(1);//obtiene el nombre
                    String emailString = row.get(2);//obtiene el email
                    if(!idString.isEmpty() && !nombreString.isEmpty() && !emailString.isEmpty() ) {//si no estan vacios
                        try {
                            int id = Integer.parseInt(idString);//lo parsea a int
                            Cliente cliente = new Cliente(id, nombreString, emailString);
                            clientedao.insertCliente(cliente);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Clientes insertados");

            for(CSVRecord row : getData("productos.csv")) {
                //idFactura,idProducto,cantidad
                if(row.size() >= 3) { // Verificar que hay al menos 4 campos en el CSVRecord
                    String idString = row.get(0);//obtiene el id
                    String nombreString = row.get(1);//obtiene el nombre
                    String valorString = row.get(2);//obtiene el valor
                    if(!idString.isEmpty() && !nombreString.isEmpty() && !valorString.isEmpty() ) {//si no estan vacios
                        try {
                            int id = Integer.parseInt(idString);//lo parsea a int
                            int valor = Integer.parseInt(valorString);//lo parsea a int
                            Producto producto = new Producto(id, nombreString, valor);
                            productodao.insert(producto);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Productos insertados");

            for(CSVRecord row : getData("facturas.csv")) {
                //idFactura, idCliente
                if(row.size() >= 2) { // Verificar que hay al menos 3 campos en el CSVRecord
                    String idFactura = row.get(0);//obtiene el idFactura
                    String idCliente = row.get(1);//obtiene el idCliente

                    if (!idFactura.isEmpty() && !idCliente.isEmpty()) { //si no estan vacios
                        try {
                            int idFac = Integer.parseInt(idFactura);//lo parsea a int
                            int idCl = Integer.parseInt(idCliente);//lo parsea a int

                            Factura factura = new Factura(idFac, idCl);
                            facturadao.insertFactura(factura);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de factura: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Facturas insertados");

            for(CSVRecord row : getData("facturas-productos.csv")) {
                //idFactura,idProducto,cantidad
                if(row.size() >= 3) { // Verificar que hay al menos 4 campos en el CSVRecord
                    String idFacturaString = row.get(0);//obtiene el id
                    String idProductoString = row.get(1);//obtiene el id
                    String cantidadString = row.get(2);//obtiene el valor
                    if(!idFacturaString.isEmpty() && !idProductoString.isEmpty() && !cantidadString.isEmpty() ) {//si no estan vacios
                        try {
                            int idFactura = Integer.parseInt(idFacturaString);//lo parsea a int
                            int idProducto = Integer.parseInt(idProductoString);//lo parsea a int
                            int valor = Integer.parseInt(cantidadString);//lo parsea a int
                            Factura_Producto facturaProducto = new Factura_Producto(idFactura, idProducto, valor);
                            facturaProductodao.insert(facturaProducto);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Facturas-Productos insertados");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void closePsAndCommit(Connection conn, PreparedStatement ps) {
        //Funcion que cierra el PreparedStatement y commitea
        if (conn != null){
            try {
                ps.close();
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


