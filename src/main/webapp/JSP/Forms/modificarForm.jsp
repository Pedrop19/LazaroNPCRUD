<%-- 
    Document   : modificarForm
    Created on : 17 nov. 2023, 13:14:52
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
        <title>Modificar Registro</title>
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="shortcut icon" href="IMG/bbdd.png" type="image/x-icon">
    </head>
    <body>
        <h3>Modificar Registro</h3>
        <div class="form-container">
            <form action="ModificarController" method="post">
                <div class="input-labels">
                    <label for="anilla">Anilla</label>
                    <input type="text" name="anilla" id="anilla" value="${sessionScope.ave.getAnilla()}" disabled><br>
                    <label for="especie">Especie</label>
                    <input type="text" name="especie" id="especie" value="${sessionScope.ave.getEspecie()}"><br>
                    <label for="fecha">Fecha</label>
                    <input type="date" name="fecha" id="fecha" value='<c:out value="${sessionScope.ave.getFecha()}" default="2023-11-17"/>''><br>
                    <label for="lugar">Lugar</label>
                    <input type="text" name="lugar" id="lugar" value="${sessionScope.ave.getLugar()}"><br>
                    <p class="error"><c:out value="${requestScope.error}"/></p>
                </div>
                    <button type="submit" name="button" value="aceptar">Aceptar</button>
                    <button type="submit" name="button" value="cancelar">Cancelar</button>
            </form>
        </div>
    </body>
</html>
