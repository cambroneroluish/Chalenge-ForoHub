# ForoHub API REST

![Status](https://img.shields.io/badge/Estado-Completado-success?style=for-the-badge)
![License](https://img.shields.io/badge/Licencia-MIT-blue?style=for-the-badge)

## Descripción del Proyecto

ForoHub es una API RESTful desarrollada en Java con Spring Boot, diseñada para replicar el funcionamiento backend de un foro de discusiones. Este proyecto permite a los usuarios autenticados gestionar tópicos (preguntas, dudas o discusiones) mediante operaciones CRUD completas, garantizando la integridad y seguridad de los datos.

Este sistema fue construido como parte del desafío backend, aplicando las mejores prácticas de la industria, incluyendo migraciones de base de datos, validaciones de entrada y un sistema de autenticación Stateless utilizando JSON Web Tokens (JWT).

---

## Tecnologías Utilizadas

El proyecto fue desarrollado utilizando el siguiente stack tecnológico:

* ![Java](https://img.shields.io/badge/java-17-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) **Java 17:** Lenguaje principal de desarrollo.
* ![Spring Boot](https://img.shields.io/badge/spring_boot-3.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white) **Spring Boot 3:** Framework para la creación de la API REST.
* ![Spring Security](https://img.shields.io/badge/spring_security-Protecci%C3%B3n-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white) **Spring Security:** Módulo para el control de acceso y autenticación.
* ![JWT](https://img.shields.io/badge/JWT-Auth0-black?style=for-the-badge&logo=JSON%20web%20tokens) **Auth0 JWT:** Librería para la generación y validación de tokens.
* ![PostgreSQL](https://img.shields.io/badge/postgresql-BD-316192?style=for-the-badge&logo=postgresql&logoColor=white) **PostgreSQL:** Motor de base de datos relacional.
* ![Flyway](https://img.shields.io/badge/flyway-Migraciones-CC0200?style=for-the-badge&logo=flyway&logoColor=white) **Flyway:** Herramienta para el control de versiones de la base de datos.
* ![Hibernate](https://img.shields.io/badge/Hibernate-ORM-59666C?style=for-the-badge&logo=Hibernate&logoColor=white) **Hibernate / Spring Data JPA:** Persistencia y mapeo objeto-relacional.

---

## Arquitectura y Funcionalidades Principales

La aplicación sigue un diseño de arquitectura por capas (Controladores, Servicios, Repositorios, Dominios), asegurando la separación de responsabilidades.

### 1. Autenticación y Autorización
* Implementación de encriptación de contraseñas mediante **BCrypt**.
* Generación de tokens JWT con un tiempo de expiración definido (2 horas).
* Filtro de seguridad personalizado (`SecurityFilter`) para interceptar y validar el token en cada petición HTTP, protegiendo las rutas sensibles.

### 2. Gestión de Tópicos (CRUD)
* **Creación (POST):** Registro de nuevos tópicos con validación de datos a nivel de DTO (`@Valid`, `@NotBlank`, etc.).
* **Lectura (GET):** Obtención de la lista de tópicos registrados.
* **Actualización (PUT):** Modificación de tópicos existentes verificando su ID.
* **Eliminación (DELETE):** Borrado lógico o físico de los tópicos.

---

## Configuración y Ejecución Local

### Requisitos Previos
* Java Development Kit (JDK) 17 o superior.
* Maven instalado.
* PostgreSQL instalado y en ejecución.

### Pasos de Instalación

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/TuUsuarioGithub/forohub.git](https://github.com/TuUsuarioGithub/forohub.git)
    cd forohub
    ```

2.  **Configurar la Base de Datos:**
    Crea una base de datos en PostgreSQL llamada `forohub_db`.
    Luego, actualiza las credenciales en el archivo `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/forohub_db
    spring.datasource.username=TU_USUARIO_POSTGRES
    spring.datasource.password=TU_CONTRASEÑA_POSTGRES
    
    # Define tu propia clave secreta para la firma de tokens JWT
    api.security.secret=tu_clave_secreta_aqui
    ```

3.  **Ejecutar la aplicación:**
    Al ejecutar el proyecto, Flyway se encargará automáticamente de crear las tablas necesarias (`topicos` y `usuarios`) mediante los scripts de migración.
    ```bash
    mvn spring-boot:run
    ```

---

## Endpoints de la API

A continuación, un resumen de las rutas principales (requieren entorno local `http://localhost:8080`).

| Método | Endpoint | Descripción | Requiere Auth (JWT) |
|---|---|---|---|
| `POST` | `/login` | Autentica al usuario y devuelve un token JWT | No |
| `POST` | `/topicos` | Crea un nuevo tópico | Sí |
| `GET` | `/topicos` | Lista los tópicos disponibles | Sí |
| `PUT` | `/topicos/{id}` | Actualiza un tópico por su ID | Sí |
| `DELETE` | `/topicos/{id}`| Elimina un tópico por su ID | Sí |

> **Nota para evaluación:** Para probar las rutas protegidas, primero debe enviar un request a `/login` con un usuario válido registrado en la base de datos, copiar el token generado y enviarlo en el encabezado de las siguientes peticiones bajo el formato: `Authorization: Bearer <token>`.

---

## Autor

Desarrollado por **Luis Humberto Cambronero Coto**.
Conecta conmigo en [LinkedIn](https://www.linkedin.com/in/luis-humberto-cambronero-coto/) o revisa mis otros proyectos en [GitHub](https://github.com/cambroneroluish).