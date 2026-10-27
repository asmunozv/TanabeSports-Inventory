# TanabeSports Inventory

Sistema de inventario **monolítico** con Java 21, Spring Boot, PostgreSQL, MongoDB y patrón Observer.

## Inicio rápido

Requisitos: Docker Engine/Desktop con Docker Compose.

```bash
cp .env.example .env
# Cambia POSTGRES_PASSWORD en .env antes de levantar el sistema
docker compose up --build -d
```

En Windows PowerShell, copia el archivo con `Copy-Item .env.example .env`.

API: `http://localhost:8080/api`.

## Pruebas con curl

Crear producto:

```bash
curl -X POST http://localhost:8080/api/products -H 'Content-Type: application/json' -d '{"sku":"SKI-001","name":"Botas de esquí","price":129.99,"stock":10,"minimumStock":3}'
```

Listar productos:

```bash
curl http://localhost:8080/api/products
```

Registrar salida de 8 unidades (suponiendo que el producto creado tiene ID 1):

```bash
curl -X POST http://localhost:8080/api/products/1/stock -H 'Content-Type: application/json' -d '{"quantity":-8}'
```

Ver alertas en MongoDB:

```bash
curl http://localhost:8080/api/alerts
```

Ver movimientos:

```bash
curl http://localhost:8080/api/products/1/movements
```

Detener:

```bash
docker compose down
```

## Diseño

- PostgreSQL: productos y movimientos de inventario.
- MongoDB: alertas de bajo stock.
- Observer: `LowStockObserver` escucha `StockChangedEvent`, publicado **después** del commit de PostgreSQL.
- Arquitectura: una sola aplicación Spring Boot, no microservicios.

## Límites de esta versión inicial

**Prototipo de desarrollo, no apto para producción.** La API todavía no tiene autenticación ni autorización, por lo que no debe exponerse públicamente. `ddl-auto: update` es solo para desarrollo. Los eventos se publican en memoria: si MongoDB falla o la aplicación se interrumpe, se pueden perder alertas. Para producción se recomienda patrón transactional outbox, reintentos, control de duplicados, migraciones Flyway y pruebas de integración. El control `@Version` previene actualizaciones concurrentes silenciosas, pero los conflictos requieren reintentos del cliente. No se genera alerta al crear un producto con stock bajo; se genera cuando cambia el stock.

## Subir a GitHub

En el repositorio de GitHub, selecciona **Add file → Upload files** y arrastra el contenido de la carpeta (no el ZIP). Para conservar la estructura, también puedes subirlo mediante Git local. No subas `.env`.
