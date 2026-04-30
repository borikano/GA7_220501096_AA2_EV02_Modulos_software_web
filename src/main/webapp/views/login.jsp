<%-- 
    Proyecto: GA7-220501096-AA2-EV02
    Autor: Luis H. Echeverry O
    ADSO: 3118315
    Modulo: Login web con JSP, Servlets, JDBC y MySQL
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String mensajeError = (String) request.getAttribute("mensajeError");
    String mensajeExito = (String) request.getAttribute("mensajeExito");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inicio de sesion</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
</head>
<body>

    <div class="header">
        <h1>Modulo Web de Usuarios</h1>
        <p>GA7-220501096-AA2-EV02 - JSP, Servlets y JDBC</p>
    </div>

    <main class="container">
        <section class="card login-card">
            <h2>Inicio de sesion</h2>
            <p>Ingrese sus credenciales para acceder al sistema.</p>

            <% if (mensajeError != null) { %>
                <div class="alert alert-error">
                    <%= mensajeError %>
                </div>
            <% } %>

            <% if (mensajeExito != null) { %>
                <div class="alert alert-success">
                    <%= mensajeExito %>
                </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <label for="correo">Correo electronico</label>
                    <input type="email" id="correo" name="correo" required>
                </div>

                <div class="form-group">
                    <label for="clave">Clave</label>
                    <input type="password" id="clave" name="clave" required>
                </div>

                <button type="submit">Ingresar</button>
            </form>
        </section>
    </main>

    <footer class="footer">
        Proyecto academico desarrollado con Jakarta EE, Servlets, JSP, JDBC y MySQL.
    </footer>
    <div class="signature">
        Desarrollado por Luis H. Echeverry O - ADSO 3118315
    </div>
                
</body>
</html>