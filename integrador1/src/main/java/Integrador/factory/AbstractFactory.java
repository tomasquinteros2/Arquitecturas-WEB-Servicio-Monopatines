package Integrador.factory;

import Integrador.dao.ClienteDAO;
import Integrador.dao.FacturaDAO;
import Integrador.dao.Factura_ProductoDAO;
import Integrador.dao.ProductoDAO;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;
    public abstract ClienteDAO getClienteDAO();
    public abstract FacturaDAO getFacturaDAO();
    public abstract Factura_ProductoDAO getFactura_ProductoDAO();
    public abstract ProductoDAO getProductoDAO();
    public static AbstractFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : {
                return MySQLDAOFactory.getInstance();
            }
            case DERBY_JDBC: return null;
            default: return null;
        }
    }

}
