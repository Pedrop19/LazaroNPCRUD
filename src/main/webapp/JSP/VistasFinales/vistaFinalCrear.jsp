<%-- 
    Document   : vistaFinalCrear
    Created on : 17 nov. 2023, 11:18:24
    Author     : pedro
--%>


<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<!DOCTYPE html>
<html lang="es">
<html>
    <head>
        <jsp:directive.include file="/INC/metas.inc"/>
        <link rel="shortcut icon" href="IMG/bbdd.png" type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmacion Crear</title>
        <link rel="stylesheet" href="CSS/style.css">
    </head>
    <body>
     <div class="final-container">
        <div class="text-container">
            <h2>Registro completado</h2>
            <p>El registro se ha completado correctamente para el ave: ${ave.especie}</p>
            <img src="IMG/success.gif" alt="success" width="200px">
            <h4>Informaci&oacute;n del ave</h4>
            <p>Anilla: ${ave.anilla}</p>
            <p>Especie: ${ave.especie}</p>
            <p>Lugar: ${ave.lugar}</p>
            <p>Fecha: ${ave.fecha}</p>
        </div>
        <form action="VueltaAEmpezar" method="post">
            <button type="submit">Volver</button>
        </form>
    </div>
    </body>
</html>
