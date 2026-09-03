/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;
/**
 *
 * @author Niko
 */
public class Conectar {
    private static final String USER = "adminTareaBD";
    private static final String CLAVE = "Admin1234";
    private static final String HOST = "gestion-de-salarios.database.windows.net";
    private static final String BASE = "bese_salios";
    private static final String URL = "jdbc:sqlserver://" + HOST + ":1433;database=" + BASE
        + ";encrypt=true;trustServerCertificate=false"
        + ";hostNameInCertificate=*.database.windows.net"
        + ";loginTimeout=30";
    
    private Connection con;
    
    public Connection getConectar() throws ClassNotFoundException, SQLException
    {
        con =  null;

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = (Connection) DriverManager.getConnection(URL, USER, CLAVE);
        
        return con;
    }
    
}
