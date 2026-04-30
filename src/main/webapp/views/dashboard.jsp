<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Object usuarioSesion = session.getAttribute("usuarioSesion");

    if (usuarioSesion == null) {
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel principal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
</head>
<body>

    <div class="header">
        <h1>Panel principal</h1>
        <p>Bienvenido al modulo web de usuarios.</p>
    </div>

    <nav class="nav">
        <a href="${pageContext.request.contextPath}/views/dashboard.jsp">Inicio</a>
        <a href="${pageContext.request.contextPath}/usuarios">Usuarios</a>
        <a href="${pageContext.request.contextPath}/logout">Cerrar sesion</a>
    </nav>

    <main class="container">
        <section class="card">
            <h2>Sesion iniciada correctamente</h2>
            <p>Desde este panel se podra acceder a las funcionalidades del modulo.</p>
        </section>
    </main>

    <div class="signature">
        Desarrollado por Luis H. Echeverry O - ADSO 3118315
    </div>
    
</body>
</html>