# API REST de Gestión de Usuarios

Proyecto académico desarrollado como parte de una actividad del **SENA**, en el programa **Tecnólogo en Análisis y Desarrollo de Software (ADSO)**.

## Tecnologías

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- JUnit y Mockito
- Postman

## Funcionalidades

- Registro de usuarios mediante una API REST.
- Validación de datos de entrada.
- Verificación de correos duplicados.
- Hash de contraseñas con BCrypt.
- Manejo de errores HTTP.
- Pruebas unitarias del servicio.

## Instalación

Clonar el repositorio:

```bash
git clone https://github.com/sronaal/api-rest-usuario-spring-boot.git
cd api-rest-usuario-spring-boot
```

Configurar las variables de entorno para la conexión a MySQL: `DB_URL`, `DB_USERNAME` y `DB_PASSWORD`.

Ejecutar las pruebas:

```bash
./mvnw test
```

Iniciar la aplicación:

```bash
./mvnw spring-boot:run
```

## Endpoint disponible

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/usuarios` | Registrar un usuario |

## Autoría y propósito

Proyecto de formación SENA ADSO para practicar desarrollo backend, bases de datos, pruebas de software y construcción de API REST.

Repositorio: [GitHub](https://github.com/sronaal/api-rest-usuario-spring-boot)
