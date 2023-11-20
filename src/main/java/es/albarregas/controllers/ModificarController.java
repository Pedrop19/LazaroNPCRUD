/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import es.albarregas.beans.Ave;
import es.albarregas.conections.Conexion;
import es.albarregas.models.Utils;

/**
 *
 * @author pedro
 */
@WebServlet(name = "ModificarController", urlPatterns = { "/ModificarController" })
public class ModificarController extends HttpServlet {

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
        String sql = "";
        Connection conexion = null;
        ResultSet resultado = null;
        PreparedStatement preparada = null;
        conexion = Conexion.getConnection();
        Ave ave = new Ave();
        


        try {
            switch (boton) {
                // Caso de modificar
                case "modificar":

                    String anilla = request.getParameter("anilla");
                    request.getSession().setAttribute("anilla", anilla);
                    // Comprobamos que se ha seleccionado un ave
                    if (anilla == null || anilla.equals("")) {
                        Utils.getError(request, response, "Debe seleccionar un ave para modificar",
                                "JSP/Vistas/modificar.jsp");
                    } else {
                        try {
                            sql = "SELECT * FROM aves WHERE anilla = '" + anilla + "'";
                            resultado = conexion.createStatement().executeQuery(sql);
                            while (resultado.next()) {
                                ave.setAnilla(resultado.getString("anilla"));
                                ave.setEspecie(resultado.getString("especie"));
                                ave.setLugar(resultado.getString("lugar"));
                                ave.setFecha(resultado.getDate("fecha"));
                                request.getSession().setAttribute("ave", ave);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            Conexion.closeConexion();
                            resultado.close();
                        }
                    }
                    url = "JSP/Forms/modificarForm.jsp";
                    break;
                // Caso de aceptar
                case "aceptar":
                    Ave aveSesion = (Ave) request.getSession().getAttribute("ave");
                    BeanUtils.populate(ave, request.getParameterMap());
                    Map<String, String[]> parametros = request.getParameterMap();
                    Iterator<String> it = parametros.keySet().iterator();
                    Boolean errBoolean = false;

                    // Comprobamos que no se dejan campos vacios
                    while (it.hasNext()) {
                        String clave = it.next();
                        String valor = parametros.get(clave)[0];
                        if (clave.equals("button")) {
                            continue;
                        } else if (valor.equals("")) {
                            Utils.getError(request, response, "No se puede dejar campos vacios",
                                    "JSP/Forms/modificarForm.jsp");
                            errBoolean = true;
                            break;
                        } else if (clave.equals("fecha")) {
                            LocalDate ahora = LocalDate.now();
                            LocalDate fecha = LocalDate.parse(valor);
                            if (fecha.isAfter(ahora)) {
                                Utils.getError(request, response, "La fecha no puede ser posterior a la actual",
                                        "JSP/Forms/modificarForm.jsp");
                                errBoolean = true;
                                break;
                            } else {
                                continue;
                            }
                        }
                    }

                    // Comprobamos que se ha modificado algun campo
                    if (ave.equals(aveSesion)) {
                        Utils.getError(request, response, "No se ha modificado ningun campo",
                                "JSP/Forms/modificarForm.jsp");
                        errBoolean = true;
                        break;
                    }

                    // Si no hay errores, modificamos el ave
                    if (!errBoolean) {
                        try {
                            sql = "UPDATE aves SET especie = ?, lugar = ?, fecha = ? WHERE anilla = '"
                                    + request.getSession().getAttribute("anilla") + "'";
                            preparada = conexion.prepareStatement(sql);
                            preparada.setString(1, request.getParameter("especie"));
                            preparada.setString(2, request.getParameter("lugar"));
                            preparada.setDate(3, java.sql.Date.valueOf(request.getParameter("fecha")));
                            preparada.executeUpdate();
                            ave.setAnilla(request.getSession().getAttribute("anilla").toString());
                            ave.setEspecie(request.getParameter("especie"));
                            ave.setLugar(request.getParameter("lugar"));
                            ave.setFecha(java.sql.Date.valueOf(request.getParameter("fecha")));
                            request.getSession().setAttribute("ave", ave);
                            url = "JSP/VistasFinales/vistaFinalModificar.jsp";
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            Conexion.closeConexion();
                            preparada.close();
                        }

                    }
                    break;
                case "cancelar":
                    url = "index.html";
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            Conexion.closeConexion();
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
