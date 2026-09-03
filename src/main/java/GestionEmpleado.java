
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Comparator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Niko
 */
public class GestionEmpleado {
    
    private static GestionEmpleado gestion;
    private ArrayList<Empleado> ListaE = new ArrayList<>();
   private static final String SQLISTA = "{call dbo.sp_ListarEmpleados}";
   private static final String SQLINSERT = "{call dbo.sp_InsertarEmpleado(?, ?, ?)}";
    
    private GestionEmpleado(){
    
        try {
           this.ListaE = ObtenerDatosBD();
        } catch (SQLException ex) {
        System.out.println("ERROR SQL: " + ex.getMessage());

        } catch (ClassNotFoundException ex) {
         System.out.println("ERROR de clase: " + ex.getMessage());
    
        }

    }
    
    public static GestionEmpleado getInstance()
    {
        if(gestion == null)
        {
          gestion = new GestionEmpleado();
      
        }
            
        
        return gestion;
    }
    
    public void Mostrar()
    {
        for (int i = 0; i < ListaE.size() ; i++) {
            Empleado e = ListaE.get(i);       
        }        
    }
    
    // Ordena alfabeticamente los empleados
    public void Ordenar()
    {
        ListaE.sort(Comparator.comparing(
                Empleado::getNombre,
                String::compareToIgnoreCase));
    
    }
    
    // Metodo de insertar lista, se retorna un 0 si pudo insertar o -1 so ya existe
    public int InsertarLista(Empleado e) throws SQLException, ClassNotFoundException
    {
        Conectar conexion = new Conectar();
        
        try(Connection con = conexion.getConectar();
             CallableStatement cs = con.prepareCall(SQLINSERT))
        {
            // Insercion en la BD
            cs.setString(1, e.getNombre());
            cs.setDouble(2, e.getSalario());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
   
            if(cs.getInt(3) == 0)
             {
                 // Actualizar
                this.ListaE = ObtenerDatosBD();
        
             }
            return cs.getInt(3);
        }
        
        

    }

    public ArrayList<Empleado> getListaE() {
        return ListaE;
    }
    
    public ArrayList<Empleado> ObtenerDatosBD() throws SQLException, ClassNotFoundException 
     {
         ArrayList<Empleado> lista = new ArrayList<>();
         Conectar conexion = new Conectar();
         try(Connection con = conexion.getConectar();
             CallableStatement cs = con.prepareCall(SQLISTA);
             ResultSet rs = cs.executeQuery()
                 )
         {
             while(rs.next())
             {
                 Empleado e = new Empleado();
                 e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("Nombre"));
                e.setSalario(rs.getDouble("Salario"));
                lista.add(e);
             }
           
         }
         
      return lista;
     }
    
    
    
}
