/*package com.inventory.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static Connection connection = null;

    // Información de la conexión a la base de datos
    private static final String URL = "jdbc:mysql://InventarioExamen.mssql.somee.com:3306/InventarioExamen";
    private static final String USER = "rekedomina_SQLLogin_1";
    private static final String PASSWORD = "omp5dhnret";

    // Método para obtener la conexión
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al conectar a la base de datos");
            }
        }
        return connection;
    }

    // Método para cerrar la conexión
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar la conexión a la base de datos");
            }
        }
    }
}*/

package com.inventory.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB  {
    private static final String SERVIDOR = "InventarioExamen.mssql.somee.com";
    private static final String USUARIO = "rekedomina_SQLLogin_1";
    private static final String PW = "omp5dhnret";
    private static final String NOMBREBD = "InventarioExamen";
    //private static final String PUERTO = "1433";
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    public Connection obtenerConexion(){
        try{
            String conexionUrl = "jdbc:sqlserver://" + SERVIDOR +//": " + PUERTO +
                    "; Databasename= " + NOMBREBD +"; user= " + USUARIO + 
                    "; password = " + PW + ";";
            Class.forName(DRIVER);      
            return (DriverManager.getConnection(conexionUrl));
        }catch(ClassNotFoundException | SQLException ex){
           Logger.getLogger(ConexionDB .class.getName()).log(Level.SEVERE, null, ex);
        }      
        return null;
    }
    
    
    public void close(Connection conn){
        try{
           conn.close();
        }catch(SQLException ex){
            Logger.getLogger(ConexionDB .class.getName()).log(Level.SEVERE, null, ex);
        }
    }
