/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.albarregas.beans.Ave;
import es.albarregas.conections.Conexion;
import es.albarregas.models.Utils;

/**
 *
 * @author Pedro Lazaro
 */
@WebServlet(name = "FrontController", urlPatterns = { "/FrontController" })
public class FrontController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.html").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String boton = request.getParameter("button");
        String url = "";
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;
        String sql = "SELECT * FROM aves";

        Ave ave;
        LinkedList<Ave> aves = new LinkedList<Ave>();
           
            switch (boton) {
                case "crear":
                    url = "JSP/Forms/crearForm.jsp";
                    break;
                // Caso ver
                case "ver":
                    try {
                        conexion = Conexion.getConnection();
                        sentencia = conexion.createStatement();
                        resultado = sentencia.executeQuery(sql);
                        // Si no hay aves en la base de datos
                        if (!resultado.next()) {
                            Utils.getError(request, response, "No hay aves en la base de datos", "JSP/Errores/error.jsp");
                        } else {
                            // Recorremos el resultado y lo guardamos en un objeto ave
                            do {
                                ave = new Ave();
                                ave.setAnilla(resultado.getString("anilla"));
                                ave.setEspecie(resultado.getString(2));
                                ave.setFecha(resultado.getDate("fecha"));
                                ave.setLugar(resultado.getString("lugar"));
                                aves.add(ave);
                            } while (resultado.next());
                        
                            request.getSession().setAttribute("aves", aves);
                            url = "JSP/VistasFinales/vistaFinalVer.jsp";
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        Conexion.closeConexion();
                    }
                    break;
                case "modificar":
               
                // Caso borrar
                case "borrar":
                    try {
                        conexion = Conexion.getConnection();
                        sentencia = conexion.createStatement();
                        resultado = sentencia.executeQuery(sql);
                        // Si no hay aves en la base de datos
                        if (!resultado.next()) {
                            Utils.getError(request, response, "No hay aves en la base de datos", "JSP/Errores/error.jsp");
                        } else {
                            // Recorremos el resultado y lo guardamos en un objeto ave
                            do {
                                ave = new Ave();
                                ave.setAnilla(resultado.getString("anilla"));
                                ave.setEspecie(resultado.getString(2));
                                ave.setFecha(resultado.getDate("fecha"));
                                ave.setLugar(resultado.getString("lugar"));
                                aves.add(ave);
                            } while (resultado.next());
                        
                            request.getSession().setAttribute("aves", aves);
                            if (boton.equals("modificar")) {
                                url = "JSP/Vistas/modificar.jsp";
                            } else {
                                url = "JSP/Vistas/borrar.jsp";
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        Conexion.closeConexion();
                    }
                    break;
            }
        request.getRequestDispatcher(url).forward(request, response);
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
