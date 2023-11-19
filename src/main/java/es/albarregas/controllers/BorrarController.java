/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import es.albarregas.beans.Ave;
import es.albarregas.conections.Conexion;
import es.albarregas.models.Utils;

/**
 *
 * @author pedro
 */
@WebServlet(name = "BorrarController", urlPatterns = { "/BorrarController" })
public class BorrarController extends HttpServlet {

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
        String error = "";
        String[] valores = request.getParameterValues("anilla");
        String sql = "";
        Connection conexion = Conexion.getConnection();
        LinkedList<Ave> listaAves = new LinkedList<>();
        ResultSet resultado = null;


        switch (boton) {
            // Caso de borrar
            case "borrar":

                // Comprobamos que se ha seleccionado alguna anilla
                if (valores != null) {
                try {
                    // Recorremos el array de anillas y vamos añadiendo las aves a la lista
                    for (String anilla : valores) {
                        sql = "SELECT * FROM aves WHERE anilla = '" + anilla + "'";
                            resultado = conexion.createStatement().executeQuery(sql);
                            while (resultado.next()) {
                                Ave ave = new Ave();
                                ave.setAnilla(resultado.getString("anilla"));
                                ave.setEspecie(resultado.getString("especie"));
                                ave.setLugar(resultado.getString("lugar"));
                                ave.setFecha(resultado.getDate("fecha"));
                                listaAves.add(ave);
                                request.getSession().setAttribute("aves", listaAves);
                            }
                        }
                    } catch (Exception e) {
                        error = e.getMessage();
                    } finally {
                        Conexion.closeConexion();
                        try {
                            resultado.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    request.getSession().setAttribute("valores", valores);
                    url = "JSP/Avisos/BorrarAviso.jsp";
                } else {
                    Utils.getError(request, response, "No se ha seleccionado ninguna anilla", "JSP/Vistas/borrar.jsp");
                }
                break;
            case "cancelar":
                url = "index.html";
                break;
            // Caso de aceptar
            case "aceptar":
                try {
                    // Recorremos el array de anillas y vamos borrando las aves
                    int contador = 0;
                    valores = (String[]) request.getSession().getAttribute("valores");
                    for (String anilla : valores) {
                        sql = "DELETE FROM aves WHERE anilla = '" + anilla + "'";
                        conexion.createStatement().executeUpdate(sql);
                        contador++;
                    }
                    request.setAttribute("contador", contador);
                    url = "JSP/Avisos/BorrarConfirmacion.jsp";
                } catch (Exception e) {
                    error = e.getMessage();
                } finally {
                    Conexion.closeConexion();
                }
            break;
        }
        request.setAttribute("error", error);
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
