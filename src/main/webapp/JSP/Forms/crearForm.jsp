<%-- 
    Document   : crearForm
    Created on : 16 nov. 2023, 23:18:50
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
        <title>Crear un nuevo Registro</title>
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="shortcut icon" href="IMG/bbdd.png" type="image/x-icon">
    </head>
    <body>
        <div class="form-container">
         <h3>Crear un nuevo Registro</h3>
            <form action="CrearController" method="post">
                <div class="input-labels">
                    <label for="anilla">Anilla</label>
                    <input type="text" name="anilla" id="anilla" maxlength="3" value="${param.anilla}"><br>
                    <label for="especie">Especie</label>
                    <input type="text" name="especie" id="especie" value="${param.especie}"><br>
                    <label for="fecha">Fecha</label>
                    <input type="date" name="fecha" id="fecha" value='<c:out value="${param.fecha}" default="2023-11-17"/>''><br>
                    <label for="lugar">Lugar</label>
                    <input type="text" name="lugar" id="lugar" value="${param.lugar}"><br>
                    <p class="error"><c:out value="${requestScope.error}"/></p>
                </div>
                <button type="submit" name="button" value="crear">Crear</button>
                <button type="submit" name="button" value="cancelar">Cancelar</button>
            </form>
        </div>
    </body>
</html>
