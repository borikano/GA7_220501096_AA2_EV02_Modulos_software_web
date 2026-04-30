<%@page import="java.util.List"%>
<%@page import="com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Object usuarioSesion = session.getAttribute("usuarioSesion");

    if (usuarioSesion == null) {
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        return;
    }

    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    String mensajeError = (String) request.getAttribute("mensajeError");
    String mensajeExito = (String) request.getAttribute("mensajeExito");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion de usuarios</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
</head>
<body>

    <div class="header">
        <h1>Gestion de usuarios</h1>
        <p>Consulta de usuarios registrados en la base de datos.</p>
    </div>

    <nav class="nav">
        <a href="${pageContext.request.contextPath}/views/dashboard.jsp">Inicio</a>
        <a href="${pageContext.request.contextPath}/usuarios">Usuarios</a>
        <a href="${pageContext.request.contextPath}/logout">Cerrar sesion</a>
    </nav>

    <main class="container">
        <section class="card">
            <h2>Usuarios registrados</h2>

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

            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Correo</th>
                        <th>Estado</th>
                        <th>Fecha de creacion</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (usuarios != null && !usuarios.isEmpty()) { %>
                        <% for (Usuario usuario : usuarios) { %>
                            <tr>
                                <td><%= usuario.getId() %></td>
                                <td><%= usuario.getNombre() %></td>
                                <td><%= usuario.getCorreo() %></td>
                                <td><%= usuario.isEstado() ? "Activo" : "Inactivo" %></td>
                                <td><%= usuario.getFechaCreacion() %></td>
                            </tr>
                        <% } %>
                    <% } else { %>
                        <tr>
                            <td colspan="5">No hay usuarios para mostrar.</td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </section>
    </main>
    <div class="signature">
        Desarrollado por Luis H. Echeverry O - ADSO 3118315
    </div>
                
</body>
</html>