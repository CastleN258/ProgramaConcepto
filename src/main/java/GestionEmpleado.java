
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
    private ArrayList<Empleado> ListaE = new ArrayList<Empleado>();
    
    private GestionEmpleado(){}
    
    public static GestionEmpleado getInstance()
    {
        if(gestion == null)
        {
          gestion = new GestionEmpleado();
          // TODO: BORRAR CUANDO YA ESTE LOS DE BD
          gestion.InsertarLista(new Empleado(24, "Nicolas", 25.7));
          gestion.InsertarLista(new Empleado(64, "Sebastian", 53.7));
          gestion.InsertarLista(new Empleado(32, "Franco", 324.7));
          
        }
            
        
        return gestion;
    }
    
    public void Mostrar()
    {
        for (int i = 0; i < ListaE.size() ; i++) {
            Empleado e = ListaE.get(i);       
        }        
    }
    
    // Ordena alfabeticamente los 
    public void Ordenar()
    {
        ListaE.sort(Comparator.comparing(
                Empleado::getNombre,
                String::compareToIgnoreCase));
    
    }
    
    // Inserta en el arreglo lógico, faltaria en la base de datos
    // TODO: VER COMO SE VALIDA LA INSERCION
    public boolean InsertarLista(Empleado e)
    {
        ListaE.add(e);
        return true;
    }

    public ArrayList<Empleado> getListaE() {
        return ListaE;
    }
    
    
    
}
