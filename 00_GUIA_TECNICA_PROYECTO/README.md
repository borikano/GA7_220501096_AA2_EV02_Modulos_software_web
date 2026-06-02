# Guía técnica del proyecto EV02 Web

Repositorio: `GA7_220501096_AA2_EV02_Modulos_software_web`

Proyecto académico en Java Web para la evidencia EV02 de módulos de software web.

Esta guía funciona como panel de revisión para ubicar rápidamente el código fuente, la documentación, la base de datos, las pruebas funcionales y las evidencias del proyecto.

---

## Lectura rápida para revisión

| Elemento | Ubicación | Estado |
|---|---|---|
| Código fuente Java Web | [`src`](../src) | Disponible |
| Documentación técnica | [`docs`](../docs) | Disponible |
| Base de datos | [`database`](../database) | Disponible |
| Configuración Maven | [`pom.xml`](../pom.xml) | Disponible |
| Evidencia funcional | [`evidencias/EV02-JSP-FUNCIONAL`](../evidencias/EV02-JSP-FUNCIONAL) | Disponible |
| README principal | [`README.md`](../README.md) | Disponible |
| Licencia y uso académico | [`LICENSE.md`](../LICENSE.md) | Disponible |

---

## Qué debe revisar el evaluador

| Criterio | Ruta sugerida | Estado |
|---|---|---|
| Formularios JSP | [`src`](../src) | Completado |
| Servlets | [`src`](../src) | Completado |
| Métodos GET y POST | [`docs/EVIDENCIA_HTTP_JSP.md`](../docs/EVIDENCIA_HTTP_JSP.md) | Documentado |
| Conexión JDBC | [`src`](../src) | Completado |
| Base de datos | [`database`](../database) | Documentado |
| Pruebas funcionales | [`docs/PRUEBAS_FUNCIONALES.md`](../docs/PRUEBAS_FUNCIONALES.md) | Completado |
| Evidencia visible | [`evidencias/EV02-JSP-FUNCIONAL`](../evidencias/EV02-JSP-FUNCIONAL) | Completado |
| Datos de prueba anonimizados | [`docs/PRUEBAS_FUNCIONALES.md`](../docs/PRUEBAS_FUNCIONALES.md) | Completado |

---

## Alcance técnico

| Componente | Descripción | Estado |
|---|---|---|
| Lenguaje | Java | Implementado |
| Vista | JSP | Implementado |
| Controlador | Servlets | Implementado |
| Persistencia | JDBC con MySQL | Implementado |
| Seguridad básica | Sesión y protección de contraseñas con BCrypt | Implementado |
| Construcción | Maven | Disponible |
| Servidor local | Apache Tomcat o entorno compatible | Documentado |

---

## Ejecución local

| Paso | Acción |
|---|---|
| 1 | Revisar la configuración de base de datos en el proyecto |
| 2 | Crear o importar la base de datos desde [`database`](../database) |
| 3 | Compilar el proyecto con Maven según `pom.xml` |
| 4 | Desplegar el módulo web en el servidor local |
| 5 | Ejecutar las pruebas funcionales documentadas |

Comando Maven de referencia:

`mvn clean package`

Nota: la configuración local usa MySQL en entorno académico. Los valores como `localhost` y `root` corresponden al ambiente local de desarrollo.

---

## Evidencias y pruebas

| Evidencia | Ubicación | Estado |
|---|---|---|
| Evidencia HTTP/JSP | [`docs/EVIDENCIA_HTTP_JSP.md`](../docs/EVIDENCIA_HTTP_JSP.md) | Disponible |
| Pruebas funcionales | [`docs/PRUEBAS_FUNCIONALES.md`](../docs/PRUEBAS_FUNCIONALES.md) | Disponible |
| Punto de entrada de evidencia | [`evidencias/EV02-JSP-FUNCIONAL/README.md`](../evidencias/EV02-JSP-FUNCIONAL/README.md) | Disponible |

---

## Pruebas esperadas

| Prueba | Resultado esperado | Evidencia |
|---|---|---|
| Carga de aplicación | La aplicación web responde en entorno local | [`docs/PRUEBAS_FUNCIONALES.md`](../docs/PRUEBAS_FUNCIONALES.md) |
| Formulario JSP | El formulario permite capturar información | [`docs/EVIDENCIA_HTTP_JSP.md`](../docs/EVIDENCIA_HTTP_JSP.md) |
| Método GET | La navegación o consulta responde correctamente | [`docs/EVIDENCIA_HTTP_JSP.md`](../docs/EVIDENCIA_HTTP_JSP.md) |
| Método POST | El envío de datos se procesa correctamente | [`docs/EVIDENCIA_HTTP_JSP.md`](../docs/EVIDENCIA_HTTP_JSP.md) |
| Persistencia | Los datos se registran o consultan desde base de datos | [`docs/PRUEBAS_FUNCIONALES.md`](../docs/PRUEBAS_FUNCIONALES.md) |
| Validación de sesión | El acceso se controla mediante sesión | [`docs/PRUEBAS_FUNCIONALES.md`](../docs/PRUEBAS_FUNCIONALES.md) |

---

## Seguridad y datos de prueba

| Criterio | Estado |
|---|---|
| Datos reales no requeridos | Cumplido |
| Correos de prueba bajo `example.com` | Cumplido |
| Contraseñas de prueba sin uso productivo | Cumplido |
| BCrypt documentado para protección de contraseñas | Cumplido |
| Configuración local documentada | Cumplido |
| Uso académico indicado | Cumplido |

---

## Documentos de soporte

| Documento | Propósito |
|---|---|
| [`README.md`](../README.md) | Presentación principal del repositorio |
| [`docs/EVIDENCIA_HTTP_JSP.md`](../docs/EVIDENCIA_HTTP_JSP.md) | Evidencia sobre JSP, HTTP, GET y POST |
| [`docs/PRUEBAS_FUNCIONALES.md`](../docs/PRUEBAS_FUNCIONALES.md) | Pruebas funcionales del módulo web |
| [`evidencias/EV02-JSP-FUNCIONAL/README.md`](../evidencias/EV02-JSP-FUNCIONAL/README.md) | Punto de entrada a la evidencia funcional |
| [`LICENSE.md`](../LICENSE.md) | Licencia y uso académico |

---

## Estado final de revisión

| Criterio | Estado |
|---|---|
| Código fuente visible | Completado |
| Documentación funcional visible | Completado |
| Base de datos visible | Completado |
| Evidencia disponible | Completado |
| Guía técnica visual | Completado |
| Licencia documentada | Completado |
| Repositorio listo para revisión | Completado |
