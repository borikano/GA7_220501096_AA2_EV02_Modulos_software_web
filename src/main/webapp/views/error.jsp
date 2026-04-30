<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String mensajeError = (String) request.getAttribute("mensajeError");

    if (mensajeError == null) {
        mensajeError = "Ha ocurrido un error inesperado.";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error del sistema</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
</head>
<body>

    <div class="header">
        <h1>Error del sistema</h1>
        <p>Se presento un inconveniente durante la ejecucion.</p>
    </div>

    <main class="container">
        <section class="card">
            <div class="alert alert-error">
                <%= mensajeError %>
            </div>

            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/views/login.jsp">
                Volver al inicio de sesion
            </a>
        </section>
    </main>
    <div class="signature">
        Desarrollado por Luis H. Echeverry O - ADSO 3118315
    </div>
                
</body>
</html>