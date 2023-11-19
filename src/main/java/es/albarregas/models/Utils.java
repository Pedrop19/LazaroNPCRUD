/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.albarregas.models;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
public class Utils {
    
    public Utils() {
    }

    // Método para mostrar errores
    public static void getError(HttpServletRequest request, HttpServletResponse response, String error, String url) throws ServletException, IOException {
        request.setAttribute("error", error);
        request.getRequestDispatcher(url).forward(request, response);
    }
}
