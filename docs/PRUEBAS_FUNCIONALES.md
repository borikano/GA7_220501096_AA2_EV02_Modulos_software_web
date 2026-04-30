# Pruebas funcionales - GA7-220501096-AA2-EV02

## Proyecto

**Nombre:** GA7_220501096_AA2_EV02_Modulos_software_web  
**Autor:** Luis H. Echeverry O  
**Ficha:** ADSO 3118315  
**Tecnologías:** Jakarta EE, JSP, Servlets, JDBC, MySQL, Maven y Apache Tomcat  
**Servidor de aplicaciones:** Apache Tomcat 10.1.54  
**Puerto del servidor:** 8081  
**Base de datos:** bbdd_ga7_ev02_web  
**Puerto MySQL:** 3307  

## Objetivo de las pruebas

Validar el funcionamiento del módulo web desarrollado con JSP, Servlets y JDBC, verificando autenticación, manejo de sesiones, conexión a base de datos, administración de usuarios y aplicación de buenas prácticas de seguridad.

---

## 1. Prueba de conexión JDBC

### URL probada

```text
/testConexion.jsp
```

### Resultado esperado

El sistema debe mostrar un mensaje de conexión exitosa a la base de datos.

### Resultado obtenido

```text
Conexion exitosa a la base de datos bbdd_ga7_ev02_web.
```

### Estado

```text
OK
```

---

## 2. Inicio de sesión correcto

### Datos usados

```text
Correo: sofia.herrera@email.com
Clave: Sofia123*
```

### Resultado esperado

El sistema debe validar las credenciales, crear la sesión del usuario y redirigir al panel principal.

### Resultado obtenido

El sistema permitió el acceso y mostró el panel principal con el mensaje de sesión iniciada correctamente.

```text
Sesion iniciada correctamente.
```

### Estado

```text
OK
```

---

## 3. Inicio de sesión incorrecto

### Datos usados

```text
Correo: sofia.herrera@email.com
Clave: claveIncorrecta
```

### Resultado esperado

El sistema debe rechazar el acceso y mostrar un mensaje de error controlado.

### Resultado obtenido

El sistema mostró el mensaje:

```text
Correo o clave incorrectos, o usuario inactivo.
```

### Estado

```text
OK
```

---

## 4. Cierre de sesión

### Acción realizada

Desde el panel principal se seleccionó la opción:

```text
Cerrar sesion
```

### Resultado esperado

El sistema debe invalidar la sesión activa y redirigir al formulario de inicio de sesión.

### Resultado obtenido

La sesión fue cerrada correctamente y el sistema volvió a la pantalla de login.

### Estado

```text
OK
```

---

## 5. Protección de vistas sin sesión

### Acción realizada

Después de cerrar sesión, se intentó acceder directamente a una vista protegida.

### URL probada

```text
/views/dashboard.jsp
```

### Resultado esperado

El sistema debe validar que no existe sesión activa y redirigir al login.

### Resultado obtenido

El sistema redirigió al formulario de inicio de sesión.

### Estado

```text
OK
```

---

## 6. Listado de usuarios

### Acción realizada

Desde el panel principal se seleccionó la opción:

```text
Usuarios
```

### Resultado esperado

El sistema debe consultar la tabla `usuario` en MySQL y mostrar los registros existentes.

### Resultado obtenido

El sistema mostró correctamente la lista de usuarios registrados en la base de datos.

### Estado

```text
OK
```

---

## 7. Registro de usuario

### Datos usados

```text
Nombre: Mariana Salazar
Correo: mariana.salazar@email.com
Clave: Mariana123*
```

### Resultado esperado

El sistema debe registrar un nuevo usuario, proteger la clave con BCrypt y mostrarlo en la tabla de usuarios.

### Resultado obtenido

El usuario fue registrado correctamente y apareció en el listado con estado activo.

### Estado

```text
OK
```

---

## 8. Edición de usuario

### Acción realizada

Se seleccionó la opción editar sobre el usuario registrado.

### Datos modificados

```text
Nombre: Mariana Salazar Actualizada
Correo: mariana.salazar.actualizada@email.com
Estado: Activo
```

### Resultado esperado

El sistema debe actualizar la información del usuario seleccionado.

### Resultado obtenido

La información del usuario se actualizó correctamente.

### Estado

```text
OK
```

---

## 9. Desactivación de usuario

### Acción realizada

Se seleccionó la opción desactivar sobre el usuario registrado.

### Resultado esperado

El sistema debe cambiar el estado del usuario a inactivo sin eliminarlo físicamente de la base de datos.

### Resultado obtenido

El usuario quedó marcado como inactivo.

### Estado

```text
OK
```

---

## 10. Validación de almacenamiento seguro de claves

### Acción realizada

Se revisó la tabla `usuario` en phpMyAdmin después de cargar usuarios mediante la clase de datos iniciales.

### Resultado esperado

La columna `clave` no debe guardar contraseñas en texto plano. Debe almacenar hashes generados con BCrypt.

### Resultado obtenido

La columna `clave` mostró valores con formato BCrypt, por ejemplo:

```text
$2a$10$...
```

### Estado

```text
OK
```

---

## 11. Validación de usuario duplicado

### Acción realizada

Se intentó registrar o editar un usuario usando un correo ya existente.

### Resultado esperado

El sistema debe impedir el registro o actualización cuando el correo ya pertenece a otro usuario.

### Resultado obtenido

El sistema valida la existencia del correo mediante consultas seguras en el DAO.

### Estado

```text
OK
```

---

## 12. Manejo de errores controlados

### Acción realizada

Se implementaron mensajes controlados para errores de conexión, driver y base de datos.

### Resultado esperado

El sistema debe mostrar mensajes claros cuando ocurra una falla de conexión o un error técnico.

### Resultado obtenido

El sistema diferencia errores como:

```text
Error de base de datos.
Error tecnico: no se encontro el driver de conexion.
Correo o clave incorrectos, o usuario inactivo.
```

### Estado

```text
OK
```

---

## Buenas prácticas aplicadas

Durante el desarrollo del módulo web se aplicaron las siguientes buenas prácticas:

- Separación de responsabilidades por paquetes.
- Uso de JSP para vistas.
- Uso de Servlets como controladores.
- Uso de DAO para operaciones con base de datos.
- Uso de clase de configuración para la conexión JDBC.
- Uso de `PreparedStatement` para prevenir inyección SQL.
- Uso de BCrypt para proteger claves.
- Manejo de sesiones para proteger vistas internas.
- Validación de campos obligatorios.
- Validación de correos duplicados.
- Eliminación lógica de usuarios mediante el campo `estado`.
- Separación de estilos en archivo CSS.
- Manejo controlado de errores técnicos y de usuario.
- Versionamiento mediante Git con commits por etapa.

---

## Commits relevantes del proceso

```text
chore: create Jakarta web project structure
feat: add database dependencies and connection diagnostics
feat: add Usuario model and password security utility
feat: add Usuario DAO with secure queries
test: add secure initial user data loader
feat: add JSP views and CSS styles
feat: add login and logout servlets
feat: add user listing servlet
feat: add user management forms
test: document functional web tests
```

---

## Conclusión

Las pruebas funcionales realizadas permiten evidenciar que el módulo web cumple con los requerimientos solicitados para la evidencia.

El proyecto implementa formularios HTML/JSP, Servlets, métodos GET y POST, conexión JDBC con MySQL, manejo de sesiones, seguridad básica en credenciales mediante BCrypt, administración de usuarios y versionamiento con Git.
