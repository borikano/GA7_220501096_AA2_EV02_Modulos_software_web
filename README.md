<!-- LICENCIA_USO_ACADEMICO_INICIO -->

## Licencia y uso académico

Este repositorio se publica con fines académicos, de revisión técnica y de portafolio.

La licencia y condiciones de uso están documentadas en:

- LICENSE.md

Los datos usados en pruebas son ficticios y deben mantenerse anonimizados.

<!-- LICENCIA_USO_ACADEMICO_FIN -->
<!-- GUIA_TECNICA_PROYECTO_INICIO -->

## Guía técnica del proyecto

Para facilitar la lectura, ejecución, prueba y auditoría técnica, este repositorio incluye una guía pública de navegación:

- 00_GUIA_TECNICA_PROYECTO/README.md

La evidencia funcional principal se encuentra en:

- docs/EVIDENCIA_HTTP_JSP.md
- docs/PRUEBAS_FUNCIONALES.md
- evidencias/EV02-JSP-FUNCIONAL/README.md

<!-- GUIA_TECNICA_PROYECTO_FIN -->
# GA7-220501096-AA2-EV02 - Módulos de software codificados y probados

## Descripción del proyecto

Este proyecto corresponde a la evidencia **GA7-220501096-AA2-EV02**, enfocada en la codificación y prueba de un módulo web utilizando **JSP, Servlets, JDBC y MySQL**.

El sistema permite iniciar sesión, consultar usuarios, registrar nuevos usuarios, editar información básica y desactivar usuarios de forma lógica. El desarrollo se realizó aplicando separación de responsabilidades, manejo de sesiones, conexión segura a base de datos mediante JDBC y protección de claves con BCrypt.

---

## Autor

**Luis H. Echeverry O**  
**Ficha:** ADSO 3118315  
**Evidencia:** GA7-220501096-AA2-EV02  

---

## Tecnologías utilizadas

- Java
- Jakarta EE
- JSP
- Servlets
- JDBC
- MySQL
- Apache Tomcat 10.1.54
- Maven
- NetBeans IDE
- Git

---

## Servidor y base de datos

### Servidor web

```text
Apache Tomcat 10.1.54
Puerto HTTP: 8081
```

### Base de datos

```text
Motor: MySQL
Puerto: 3307
Base de datos: bbdd_ga7_ev02_web
Tabla principal: usuario
```

---

## Estructura general del proyecto

```text
src/main/java
└── com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web
    ├── config
    │   └── Conexion.java
    ├── dao
    │   └── UsuarioDAO.java
    ├── data
    │   └── DatosIniciales.java
    ├── modelo
    │   └── Usuario.java
    ├── servlet
    │   ├── LoginServlet.java
    │   ├── LogoutServlet.java
    │   └── UsuarioServlet.java
    └── util
        └── SeguridadClave.java
```

```text
src/main/webapp
├── assets
│   └── css
│       └── styles.css
├── views
│   ├── dashboard.jsp
│   ├── error.jsp
│   ├── formularioUsuario.jsp
│   ├── login.jsp
│   └── usuarios.jsp
├── index.jsp
├── testConexion.jsp
└── WEB-INF
    └── web.xml
```

---

## Funcionalidades implementadas

### Inicio de sesión

El usuario ingresa con correo electrónico y clave. El sistema valida las credenciales contra la base de datos utilizando `UsuarioDAO` y BCrypt.

### Cierre de sesión

El sistema invalida la sesión activa y redirige al formulario de login.

### Listado de usuarios

El sistema consulta la tabla `usuario` y muestra los usuarios registrados.

### Registro de usuarios

Permite registrar nuevos usuarios. La clave ingresada no se guarda en texto plano; se transforma en un hash BCrypt antes de almacenarse.

### Edición de usuarios

Permite actualizar nombre, correo y estado del usuario.

### Desactivación de usuarios

El sistema no elimina físicamente los usuarios. En su lugar, cambia el campo `estado` a inactivo.

---

## Seguridad y buenas prácticas aplicadas

El proyecto aplica las siguientes buenas prácticas:

- Separación entre vistas, servlets, DAO, modelo, utilidades y configuración.
- Uso de `PreparedStatement` para prevenir inyección SQL.
- Uso de BCrypt para proteger contraseñas.
- Manejo de sesiones para proteger vistas internas.
- Validación de campos obligatorios.
- Validación de correos duplicados.
- Manejo de errores técnicos controlados.
- Eliminación lógica de usuarios mediante el campo `estado`.
- Estilos separados en archivo CSS.
- Versionamiento con Git mediante commits por etapa.

---

## Configuración de la conexión

La clase encargada de la conexión es:

```text
Conexion.java
```

Configuración usada:

```java
private static final String URL = "jdbc:mysql://localhost:3307/bbdd_ga7_ev02_web";
private static final String USER = "root";
private static final String PASSWORD = "";
private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
```

---

## Preparación de la base de datos

El script SQL se encuentra en:

```text
database/bbdd_ga7_ev02_web.sql
```

Ese archivo crea:

```text
Base de datos: bbdd_ga7_ev02_web
Tabla: usuario
Campos: id, nombre, correo, clave, estado, fecha_creacion
```

Después de crear la base de datos, los usuarios iniciales se pueden cargar ejecutando la clase:

```text
DatosIniciales.java
```

Esta clase registra usuarios usando el DAO del proyecto, por lo que las claves se guardan protegidas con BCrypt.

---

## Usuario de prueba

Después de ejecutar `DatosIniciales.java`, se puede probar el acceso con:

```text
Correo: usuario.web.01@example.com
Clave: Sofia123*
```

---

## Prueba de conexión JDBC

El proyecto incluye una página de diagnóstico:

```text
/testConexion.jsp
```

Resultado esperado:

```text
Conexion exitosa a la base de datos bbdd_ga7_ev02_web.
```

Esta página permite identificar si hay fallas de conexión, puerto, base de datos, usuario o driver JDBC.

---

## Rutas principales

### Login

```text
/views/login.jsp
```

### Panel principal

```text
/views/dashboard.jsp
```

### Gestión de usuarios

```text
/usuarios
```

### Prueba de conexión

```text
/testConexion.jsp
```

---

## Flujo de uso

1. Iniciar Apache Tomcat 10.1.54.
2. Iniciar MySQL desde XAMPP o el entorno local configurado.
3. Crear la base de datos con el script SQL.
4. Ejecutar `DatosIniciales.java` para cargar usuarios seguros.
5. Compilar el proyecto con Maven.
6. Desplegar el archivo WAR en Tomcat.
7. Abrir el login en el navegador.
8. Iniciar sesión con un usuario de prueba.
9. Gestionar usuarios desde el módulo web.

---

## Pruebas funcionales

Las pruebas realizadas se documentan en:

```text
docs/PRUEBAS_FUNCIONALES.md
```

Ese documento evidencia:

- Conexión JDBC.
- Login correcto.
- Login incorrecto.
- Cierre de sesión.
- Protección de vistas.
- Listado de usuarios.
- Registro de usuarios.
- Edición de usuarios.
- Desactivación de usuarios.
- Uso de BCrypt para claves.
- Manejo de errores controlados.

---

## Commits principales

Durante el desarrollo se generaron commits por etapa, entre ellos:

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

## Ejecución local

URL base esperada después del despliegue:

```text
http://localhost:8081/GA7_220501096_AA2_EV02_Modulos_software_web-1.0-SNAPSHOT/
```

La página inicial redirige automáticamente al login.

---

## Notas importantes

- No se deben insertar usuarios manualmente con claves en texto plano.
- Para crear usuarios de prueba seguros se debe usar `DatosIniciales.java` o el formulario web del sistema.
- El campo `clave` está preparado como `VARCHAR(255)` para almacenar hashes BCrypt.
- La desactivación de usuarios es lógica, no física.
- El archivo `target/` no debe subirse al repositorio.

---

## Conclusión

El proyecto cumple con los requerimientos de la evidencia, ya que implementa un módulo web con JSP, Servlets, métodos GET y POST, conexión JDBC, gestión de usuarios, validación de sesión, seguridad básica en contraseñas y versionamiento del proceso mediante Git.

---

## Formularios JSP, metodos HTTP y flujo del modulo web

### Formularios JSP utilizados

El proyecto utiliza formularios desarrollados en JSP para capturar y procesar informacion del usuario.

Archivos principales:

- src/main/webapp/views/login.jsp
- src/main/webapp/views/formularioUsuario.jsp
- src/main/webapp/views/usuarios.jsp

### Uso de metodos HTTP

En esta evidencia se implementan los metodos GET y POST para gestionar el flujo del modulo web.

#### Metodo GET

Se utiliza para:
- mostrar formularios
- listar usuarios
- cargar datos para edicion
- redirigir a vistas JSP

Aplicado principalmente en:
- UsuarioServlet.java
- navegacion hacia usuarios.jsp
- navegacion hacia formularioUsuario.jsp

#### Metodo POST

Se utiliza para:
- procesar el formulario de login
- registrar usuarios
- actualizar datos enviados desde formularios JSP

Aplicado principalmente en:
- LoginServlet.java
- UsuarioServlet.java

### Relacion entre JSP y Servlets

#### Login
- Vista: views/login.jsp
- Servlet: LoginServlet.java
- Metodo principal: POST
- Funcion: recibir credenciales, validar acceso y crear sesion

#### Gestion de usuarios
- Vista principal: views/usuarios.jsp
- Vista de formulario: views/formularioUsuario.jsp
- Servlet: UsuarioServlet.java
- Metodos principales:
  - GET para listar, consultar o preparar edicion
  - POST para guardar o actualizar informacion

#### Cierre de sesion
- Servlet: LogoutServlet.java
- Funcion: invalidar sesion activa y redirigir al login

### Flujo general del sistema

login.jsp
  -> POST
LoginServlet
  -> validacion
dashboard.jsp

usuarios.jsp
  -> GET
UsuarioServlet
  -> consulta con DAO
UsuarioDAO
  -> respuesta
usuarios.jsp

formularioUsuario.jsp
  -> POST
UsuarioServlet
  -> persistencia con DAO
UsuarioDAO
  -> redireccion
usuarios.jsp

### Evidencia del objetivo de la actividad

Este modulo web demuestra:
- uso de formularios JSP
- uso de Servlets
- aplicacion de metodos GET y POST
- integracion con JDBC y MySQL
- separacion entre vista, logica de control y acceso a datos



