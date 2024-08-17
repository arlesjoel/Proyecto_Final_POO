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
    private static Connection connection = null;
    
    public static Connection getConnection(){
        try{
            String conexionUrl = "jdbc:sqlserver://" + SERVIDOR +//": " + PUERTO +
                    "; Databasename= " + NOMBREBD +"; user= " + USUARIO + 
                    "; password = " + PW + ";";
            Class.forName(DRIVER);      
            connection = (DriverManager.getConnection(conexionUrl));
            return connection;
        }catch(ClassNotFoundException | SQLException ex){
           Logger.getLogger(ConexionDB .class.getName()).log(Level.SEVERE, null, ex);
        }      
        return null;
    }
    
    
    public static void close(){
        try{
           connection.close();
        }catch(SQLException ex){
            Logger.getLogger(ConexionDB .class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}