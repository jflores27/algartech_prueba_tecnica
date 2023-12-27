
# README - Algartech Prueba Técnica

## Introducción

Este README proporciona instrucciones detalladas sobre cómo levantar y ejecutar una aplicación Java en un entorno de contenedores Docker, utilizando Docker Compose. La aplicación Java se conecta a una base de datos PostgreSQL, también contenerizada, para su funcionamiento.

## Requisitos Previos

Antes de comenzar, asegúrese de tener instalado lo siguiente:

- Docker: [Instrucciones de instalación](https://docs.docker.com/get-docker/)
- Docker Compose: [Instrucciones de instalación](https://docs.docker.com/compose/install/)

## Estructura del Proyecto

La estructura del proyecto debería verse similar a esto:

```
your-project/
│
├── src/                      # Directorio de código fuente de la aplicación Java
├── Dockerfile                # Dockerfile para construir la imagen de la aplicación Java
├── docker-compose.yml        # Archivo de configuración de Docker Compose
└── README.md                 # Este archivo
```

## Configuración de Docker Compose

El archivo `docker-compose.yml` define dos servicios:

1. **java_app:** La aplicación Java, construida e implementada en un contenedor Docker.
2. **java_db:** Un servidor de base de datos PostgreSQL.

## Instrucciones para Levantar los Servicios

Siga los siguientes pasos para iniciar los servicios:

### Paso 1: Clonar el Repositorio

Clone el repositorio de la aplicación en su máquina local usando Git (asegúrese de tener permisos de acceso al repositorio):

```
git clone [URL del Repositorio]
cd your-project
```

### Paso 2: Construir y Ejecutar con Docker Compose

Desde la raíz del proyecto (donde se encuentra `docker-compose.yml`), ejecute el siguiente comando para construir y levantar los contenedores:

```
docker-compose up --build
```

Este comando realiza lo siguiente:

- Construye una imagen Docker para la aplicación Java si no existe.
- Descarga la imagen de PostgreSQL si no está presente en su máquina.
- Inicia los contenedores definidos en `docker-compose.yml`.

### Paso 3: Verificación

Una vez que los contenedores estén en ejecución, la aplicación Java debería ser accesible en `http://localhost:8080`. Puede verificar esto accediendo a la URL desde su navegador o utilizando una herramienta como curl:

```
curl http://localhost:8080
```

El servidor de base de datos PostgreSQL estará escuchando en el puerto 5432 de su máquina local.

### Paso 4: Detener los Servicios

Para detener los servicios y remover los contenedores, use el siguiente comando:

```
docker-compose down
```
