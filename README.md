# ForoHub - API REST

API REST desarrollada en Java con Spring Boot para la gestión de un foro. Este proyecto forma parte del challenge de Alura.

##  Tecnologías Utilizadas
* Java 17
* Spring Boot 3
* PostgreSQL
* Flyway (Migraciones de base de datos)
* Spring Data JPA
* Lombok
* Insomnia (Para pruebas de API)

## Funcionalidades Implementadas (CRUD)
Hasta el momento, la API cuenta con los siguientes endpoints operativos para la entidad `Topico`:

* **Crear un tópico (POST `/topicos`):** Recibe título, mensaje, autor y curso. Implementa validaciones para no aceptar campos vacíos y base de datos protegida contra títulos y mensajes duplicados.
* **Listar tópicos (GET `/topicos`):** Devuelve todos los tópicos con paginación (10 por página) y ordenamiento por fecha de creación ascendente.
* **Detalle de tópico (GET `/topicos/{id}`):** Búsqueda específica que devuelve los datos de un tópico o un error `404 Not Found` si no existe.
* **Actualizar tópico (PUT `/topicos/{id}`):** Modificación de textos usando la anotación `@Transactional`.
* **Eliminar tópico (DELETE `/topicos/{id}`):** Borrado físico de la base de datos devolviendo el código estándar `204 No Content`.