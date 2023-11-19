<%-- 
    Document   : BorrarConfirmacion
    Created on : 16 nov. 2023, 23:23:54
    Author     : pedro
--%>

<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<!DOCTYPE html>
<html lang="es">
<html>
    <head>
        <jsp:directive.include file="/INC/metas.inc"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Borrado</title>
        <link rel="stylesheet" href="CSS/style.css">
    </head>
    <body>
        <div class="error-container">
            <div class="confirmacion">
                <img src="IMG/borrar.gif" alt="error" width="200px">
                <h1>Confirmaci&oacute;n de Borrado</h1>
                <p>Se han borrado correntamente ${contador} registros</p>
            </div>
            <form action="VueltaAEmpezar" method="post">
                    <button type="submit" value="volver">Inicio</button>
            </form>
        </div>
    </body>
</html>
