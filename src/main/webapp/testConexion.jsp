<%@page import="com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.config.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prueba de Conexion</title>
</head>
<body>
    <h1>Prueba de conexion JDBC</h1>

    <p>
        <%= Conexion.probarConexion() %>
    </p>

    <a href="index.html">Volver al inicio</a>
</body>
</html>