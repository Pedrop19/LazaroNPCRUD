<%-- 
    Document   : error
    Created on : 17 nov. 2023, 11:04:46
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
        <title>Error</title>
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="shortcut icon" href="IMG/bbdd.png" type="image/x-icon">
    </head>
    <body>
        <div class="error-container">
            <div class="error">
                <h1>ERROR</h1>
                <img src="IMG/error.gif" alt="error" width="200px">
                <p>${error}</p>
            </div>
            <form action="VueltaAEmpezar" method="post">
                <button type="submit" value="volver">Inicio</button>
            </form>
        </div>
    </body>
</html>
