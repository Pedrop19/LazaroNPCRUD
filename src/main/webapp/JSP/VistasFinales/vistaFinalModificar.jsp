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
        <title>Confirmaci&oacute;n Modificaci&oacute;n</title>
        <link rel="stylesheet" href="CSS/style.css">
    </head>
    <body>
        <div class="final-container">
            <div class="text-container">
                <h2>Registro actualizado</h2>
                <p>El registro se ha actulizado correctamente para el ave: ${sessionScope.ave.especie}</p>
                <img src="IMG/success.gif" alt="success" width="200px">
                <h4>Informaci&oacute;n del ave</h4>
                <p>Anilla: ${sessionScope.ave.anilla}</p>
                <p>Especie: ${sessionScope.ave.especie}</p>
                <p>Lugar: ${sessionScope.ave.lugar}</p>
                <p>Fecha: ${sessionScope.ave.fecha}</p>
            </div>
            <form action="VueltaAEmpezar" method="post">
                <button type="submit">Volver</button>
            </form>
        </div>
    </body>
</html>
