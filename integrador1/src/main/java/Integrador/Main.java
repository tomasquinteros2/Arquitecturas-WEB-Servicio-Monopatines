package Integrador;

import Integrador.dao.ClienteDAO;
import Integrador.dao.FacturaDAO;
import Integrador.dao.Factura_ProductoDAO;
import Integrador.dao.ProductoDAO;
import Integrador.dto.ProductoDTO;
import Integrador.factory.AbstractFactory;
import Integrador.utils.HelperMySQL;

public class Main {

    public static void main(String[] args) throws Exception {

        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        ClienteDAO cliente = chosenFactory.getClienteDAO();
        Factura_ProductoDAO factura_producto = chosenFactory.getFactura_ProductoDAO();
        FacturaDAO factura = chosenFactory.getFacturaDAO();
        ProductoDAO producto = chosenFactory.getProductoDAO();

        HelperMySQL dbMySQL = new HelperMySQL();

        dbMySQL.dropTables();
        dbMySQL.createTables();
        dbMySQL.populateDB(cliente,producto,factura_producto,factura);
        dbMySQL.closeConnection();

        System.out.println();
        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");
        System.out.println();

        System.out.println(producto.getProductoMayorRecaudacion());

        System.out.println();
        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");
        System.out.println();

        System.out.println(cliente.findByMasFacturado());
    }
}
