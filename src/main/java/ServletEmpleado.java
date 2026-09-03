/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Niko
 */
@WebServlet(urlPatterns = {"/ServletEmpleado"})
public class ServletEmpleado extends HttpServlet {
    static final GestionEmpleado lista = GestionEmpleado.getInstance();
 

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletEmpleado</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletEmpleado at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Ordenamos la lista
        lista.Ordenar();
        
        // Gson transforma objectos de java en json
        Gson gson = new Gson();
        String json = gson.toJson(lista.getListaE());
        
        // Escribirmos en la respuesta el json
        response.getWriter().write(json);    
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Gson gson = new Gson();
        // Generamos el empleado nuevo por insertar
        Empleado nuevoE = gson.fromJson(request.getReader(), Empleado.class);
        int resultado = 0;
        try {
            resultado = lista.InsertarLista(nuevoE);
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());

        } catch (ClassNotFoundException ex) {
              System.out.println("Error Clase: " + ex.getMessage());
  
        }
        boolean exito = resultado == 0;
        String msg = "";
        
        switch(resultado)
        {
            case 0:
                msg = "Inserción exitosa";
                break;
                
            case -1:
                msg = "Nombre de Empleado ya existe";
                break;
                
        }
        response.setContentType("application/json");
        response.getWriter().write("{\"exito\": " 
                + exito + ", \"mensaje\": \"" 
                + msg + "\"}");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
