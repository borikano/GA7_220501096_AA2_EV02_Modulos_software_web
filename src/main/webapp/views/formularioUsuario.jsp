<%-- 
    Proyecto: GA7-220501096-AA2-EV02
    Autor: Luis H. Echeverry O
    ADSO: 3118315
    Modulo: Login web con JSP, Servlets, JDBC y MySQL
--%>

<%@page import="com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Object usuarioSesion = session.getAttribute("usuarioSesion");

    if (usuarioSesion == null) {
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        return;
    }

    String modoFormulario = (String) request.getAttribute("modoFormulario");
    String mensajeError = (String) request.getAttribute("mensajeError");
    Usuario usuarioEditar = (Usuario) request.getAttribute("usuarioEditar");

    boolean esEdicion = "editar".equals(modoFormulario);

    String titulo = esEdicion ? "Editar usuario" : "Registrar usuario";
    String accion = esEdicion ? "actualizar" : "guardar";

    String nombre = esEdicion && usuarioEditar != null ? usuarioEditar.getNombre() : "";
    String correo = esEdicion && usuarioEditar != null ? usuarioEditar.getCorreo() : "";
    boolean estado = !esEdicion || (usuarioEditar != null && usuarioEditar.isEstado());
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= titulo %></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
</head>
<body>

    <div class="header">
        <h1><%= titulo %></h1>
        <p>Formulario de administracion de usuarios.</p>
    </div>

    <nav class="nav">
        <a href="${pageContext.request.contextPath}/views/dashboard.jsp">Inicio</a>
        <a href="${pageContext.request.contextPath}/usuarios">Usuarios</a>
        <a href="${pageContext.request.contextPath}/logout">Cerrar sesion</a>
    </nav>

    <main class="container">
        <section class="card">
            <% if (mensajeError != null) { %>
                <div class="alert alert-error">
                    <%= mensajeError %>
                </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/usuarios" method="post">
                <input type="hidden" name="accion" value="<%= accion %>">

                <% if (esEdicion && usuarioEditar != null) { %>
                    <input type="hidden" name="id" value="<%= usuarioEditar.getId() %>">
                <% } %>

                <div class="form-group">
                    <label for="nombre">Nombre completo</label>
                    <input type="text" id="nombre" name="nombre" value="<%= nombre %>" required>
                </div>

                <div class="form-group">
                    <label for="correo">Correo electronico</label>
                    <input type="email" id="correo" name="correo" value="<%= correo %>" required>
                </div>

                <% if (!esEdicion) { %>
                    <div class="form-group">
                        <label for="clave">Clave</label>
                        <input type="password" id="clave" name="clave" required>
                    </div>
                <% } %>

                <% if (esEdicion) { %>
                    <div class="form-group">
                        <label for="estado">Estado</label>
                        <select id="estado" name="estado">
                            <option value="1" <%= estado ? "selected" : "" %>>Activo</option>
                            <option value="0" <%= !estado ? "selected" : "" %>>Inactivo</option>
                        </select>
                    </div>
                <% } %>

                <button type="submit"><%= esEdicion ? "Actualizar" : "Registrar" %></button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/usuarios">Cancelar</a>
            </form>
        </section>
    </main>

</body>
</html>