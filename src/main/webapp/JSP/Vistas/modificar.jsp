<%-- 
    Document   : Modificar
    Created on : 16 nov. 2023, 23:19:08
    Author     : pedro
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<!DOCTYPE html>
<html lang="es">
<html>
    <head>
        <jsp:directive.include file="/INC/metas.inc"/>
        <link rel="shortcut icon" href="IMG/bbdd.png" type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/style.css">
        <title>Modificar</title>
    </head>
    <body>
        <div class="table-container">
            <h1>Elige un registro para modificar: </h1>
            <form action="ModificarController" method="post">
                <table cellspacing="0">
                    <tr>
                        <th>Anilla</th>
                        <th>Especie</th>
                    <tr>
                    <c:forEach var="ave" items="${sessionScope.aves}">
                        <tr>
                            <td><input type="radio" name="anilla" value="${ave.anilla}"> ${ave.anilla}</td>
                            <td>${ave.especie}</td>
                        </tr>
                    </c:forEach>
                </table>
                <p class="error">
                    <c:out value="${requestScope.error}"/>
                </p>
                <br>
                    <button type="submit" name="button" value="modificar">Modificar</button>
                    <button type="submit" name="button" value="cancelar">Cancelar</button>
                </form>
            </div>
    </body>
</html>
