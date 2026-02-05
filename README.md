# Microservicio: ms-venta-bs (Business Service)

## Descripción General
**ms-venta-bs** es el microservicio de lógica de negocio principal del sistema de ventas. Actúa como orquestador entre el frontend, el servicio ambassador (que se comunica con Webpay), y la base de datos. Es responsable de coordinar el flujo completo de una transacción.

## Puerto de Ejecución
- **Puerto:** `8081`

## Responsabilidades Principales

### 1. Orquestación de Transacciones
- Coordina el flujo completo de creación y confirmación de transacciones
- Gestiona la comunicación entre el frontend (BFF), Webpay (vía Ambassador) y la base de datos
- Implementa la lógica de negocio central del proceso de pago

### 2. Manejo de Estado
- Mantiene la coherencia entre el estado en Webpay y la base de datos
- Actualiza el estado de las transacciones según respuestas de Webpay

## Comunicación con Otros Microservicios

### Recibe Llamadas Desde:
1. **ms-venta-bff (BFF - Puerto 8080)**
   - `POST /bs/add/venta` - Para crear nueva transacción
   - `PUT /bs/status/venta/{token_ws}` - Para confirmar transacción

### Realiza Llamadas Hacia:
1. **ms-venta-amb (Ambassador - Puerto 8082)**
   - Creación de transacciones en Webpay
   - Confirmación de transacciones

2. **ms-venta-db (Database - Puerto 8083)**
   - Persistencia inicial de transacciones
   - Consulta de transacciones existentes

### Flujo de Comunicación Completo:
```
Frontend → ms-venta-bff → ms-venta-bs → ms-venta-amb → Webpay
    ↑           ↑              ↓              ↑
    └───────────┴────────── ms-venta-db ←─────┘
```

## Endpoints Disponibles

### Endpoints Principales:
- **POST `/bs/add/venta`** - Crea una nueva transacción de venta
  - Recibe: `VentaRequest` con datos de la compra
  - Devuelve: `VentaResponse` con token y URL de Webpay

- **PUT `/bs/status/venta/{token_ws}`** - Confirma el estado de una transacción
  - Recibe: `token_ws` de Webpay
  - Devuelve: `StatusResponse` con estado final de la transacción

## Modelos de Datos

### Recibe:
- **`VentaRequest`**: Datos básicos de la transacción
  - `buy_order`, `session_id`, `amount`, `return_url`

### Devuelve:
- **`VentaResponse`**: Respuesta de creación de transacción
  - `token`, `url` (para redirección a Webpay)
- **`StatusResponse`**: Estado completo de transacción confirmada
  - Incluye todos los campos de respuesta de Webpay

## Flujo de Transacción Típico

### 1. Creación de Transacción:
```
1. Frontend → BFF (8080) → Business Service (8081)
2. Business Service → Ambassador (8082) → Webpay
3. Webpay → Ambassador → Business Service
4. Business Service → Database (8083) → Persistencia
5. Business Service → BFF → Frontend
```

### 2. Confirmación de Transacción:
```
1. Frontend (con token_ws) → BFF → Business Service
2. Business Service → Ambassador → Webpay (confirmación)
3. Webpay → Ambassador → Business Service
4. Business Service → Database → Actualización estado
5. Business Service → BFF → Frontend (resultado)
```

## Lógica de Negocio Implementada

### En `VentaService.addVenta()`:
1. Llama a Ambassador para crear transacción en Webpay
2. **Problema identificado:** Llama dos veces al mismo endpoint (duplicación)
3. Construye objeto `Venta` para persistencia
4. Guarda en base de datos
5. Retorna respuesta al BFF

### En `VentaService.statusVenta()`:
1. Llama a Ambassador para confirmar transacción en Webpay
2. Recibe estado completo de la transacción
3. Retorna respuesta al BFF

## Dependencias Clave

### Dependencias Internas:
- Spring Cloud OpenFeign para comunicación entre servicios
- Spring Web para endpoints REST
- Lombok para reducción de código boilerplate

### Microservicios Dependientes:
- **ms-venta-amb** (Ambassador): Para comunicación con Webpay
- **ms-venta-db** (Database): Para persistencia de transacciones

## Integración en el Ecosistema
Este microservicio es el **corazón del sistema de pagos**:
- Conecta todas las capas del sistema
- Mantiene la coherencia de estado
- Expone la API principal de transacciones al BFF

Su correcto funcionamiento es esencial para la operación del sistema completo, ya que cualquier fallo aquí interrumpe todo el flujo de pagos.
