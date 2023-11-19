/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.albarregas.beans.Ave;
import es.albarregas.conections.Conexion;
import es.albarregas.models.Utils;

import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author pedro
 */
@WebServlet(name = "CrearController", urlPatterns = { "/CrearController" })
public class CrearController extends HttpServlet {

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
        Ave ave = new Ave();
        Map<String, String[]> parametros = request.getParameterMap();
        Iterator<String> it = parametros.keySet().iterator();
        String url = "";
        String sql = "";
        Connection conexion = Conexion.getConnection();
        String boton = request.getParameter("button");
        Boolean errBoolean = false;

        PreparedStatement preparada = null;

        switch (boton) {
            // Caso crear
            case "crear":
                while (it.hasNext()) {
                    String clave = it.next();
                    String valor = parametros.get(clave)[0];
                    if (clave.equals("button")) {
                        continue;
                    // Si el campo esta vacio
                    } else if (valor.equals("")) {
                        Utils.getError(request, response, "No se puede dejar campos vacios", "JSP/Forms/crearForm.jsp");
                        errBoolean = true;
                        break;
                    // Si la fecha no es correcta
                    } else if (clave.equals("fecha")) {
                        LocalDate ahora = LocalDate.now();
                        LocalDate fecha = LocalDate.parse(valor);
                        if (fecha.isAfter(ahora)) {
                            Utils.getError(request, response, "La fecha no puede ser posterior a la actual",
                                    "JSP/Forms/crearForm.jsp");
                            errBoolean = true;
                            break;
                        } else {
                            continue;
                        }
                    }

                }
                // Si no hay errores creamos el ave y la insertamos en la base de datos
                if (!errBoolean) {
                    try {
                        BeanUtils.populate(ave, parametros);
                        sql = "INSERT INTO aves (anilla, especie, fecha, lugar) VALUES (?, ?, ?, ?)";
                        preparada = conexion.prepareStatement(sql);
                        preparada.setString(1, ave.getAnilla());
                        preparada.setString(2, ave.getEspecie());
                        preparada.setDate(3, ave.getFecha());
                        preparada.setString(4, ave.getLugar());
                        preparada.executeUpdate();
                        request.setAttribute("ave", ave);
                        url = "JSP/VistasFinales/vistaFinalCrear.jsp";
                        break;
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        if (e.getErrorCode() == 1062) {
                            Utils.getError(request, response, "Ya existe un ave con esa anilla",
                                    "JSP/Forms/crearForm.jsp");
                        } else {
                            e.printStackTrace();
                        }
                    } finally {
                        Conexion.closeConexion();
                        try {
                            preparada.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case "cancelar":
                url = "index.html";
                break;
            default:
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
