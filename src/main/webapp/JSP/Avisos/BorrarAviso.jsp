<%-- 
    Document   : BorrarAviso
    Created on : 16 nov. 2023, 23:24:00
    Author     : pedro
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<!DOCTYPE html>
<html lang="es">
<html>
    <head>
        <jsp:directive.include file="/INC/metas.inc"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar Borrado</title>
        <link rel="stylesheet" href="CSS/style.css">
    </head>
    <body>
     <div class="table-container">
        <h1>Borrado Efectuado</h1>
        <h3>¿Est&aacute; seguro de que desea borrar el/los siguiente/s registro/s?</h3>
        <table>
            <tr>
                <th>Anilla</th>
                <th>Especie</th>
                <th>Fecha</th>
                <th>Lugar</th>
            </tr>
            <c:forEach var="ave" items="${aves}">
                <tr>
                    <td>${ave.anilla}</td>
                    <td>${ave.especie}</td>
                    <td>${ave.fecha}</td>
                    <td>${ave.lugar}</td>
                </tr>
            </c:forEach>
        </table>
            <form action="BorrarController" method="post">
                <button type="submit" name="button" value="aceptar">Aceptar</button>
                <button type="submit" name="button" value="cancelar">Cancelar</button>
            </form>
    </div>
    </body>
</html>
