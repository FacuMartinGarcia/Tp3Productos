# 📦 Trabajo Práctico – Gestor de Productos (MVVM + Java)

Aplicación Android desarrollada en **Java** que implementa un sistema de gestión de productos en memoria. El proyecto utiliza una arquitectura moderna basada en componentes de navegación y reactividad.

---

## 🎯 Objetivo

Desarrollar una herramienta para el alta y visualización de productos, aplicando el patrón de arquitectura **MVVM**, el uso de **LiveData** para la actualización de la UI y **Fragments** para una navegación fluida.

---

## 📌 Descripción

La aplicación utiliza un menú lateral navegable (Navigation Drawer) que permite alternar entre las diferentes secciones. La persistencia de datos durante la sesión se garantiza mediante un **Singleton**, asegurando que todos los componentes de la interfaz consulten la misma fuente de datos.

---

## ✨ Funcionalidades

- **Navegación mediante Fragments:** Implementación de un menú navegable que gestiona el ciclo de vida de las vistas de forma eficiente.
- **Carga de Productos:** Formulario para registrar código, descripción y precio.
    - *Validaciones:* Se impide el ingreso de campos vacíos y se valida que **no se repitan ni el código ni la descripción** entre productos.
- **Listado Dinámico:** Visualización en un `RecyclerView` con todos los productos ordenados alfabéticamente por descripción.
- **Patrón Singleton:** Uso de una instancia única para el repositorio de datos, permitiendo que la lista sea compartida entre Fragments.
- **Diálogo de Salida:** Opción para cerrar la aplicación mediante un cuadro de diálogo de confirmación.

---

## 📂 Estructura del Proyecto

- **`modelo/`**: 
    - `Producto`: Clase POJO que define los atributos del objeto.
    - `ProductoRepository`: Implementación **Singleton** que gestiona el `ArrayList<Producto>` en memoria.
- **`ui/`**:
    - **`cargar/`**: Contiene el `CargarFragment` y su respectivo `CargarViewModel`.
    - **`listar/`**: Contiene el `ListarFragment` y su `ListarViewModel`.
    - `ProductoAdapter`: Adaptador personalizado para el RecyclerView de productos.
- **`MainActivity`**: Activity principal que gestiona el Navigation Drawer y el NavHostFragment.

**Recursos de Layout (`res/layout/`):**
- `fragment_cargar.xml` y `fragment_listar.xml`: Diseños específicos de cada pantalla.
- `item_producto.xml`: Diseño de la tarjeta (card) para cada elemento de la lista.
- `activity_main.xml` / `app_bar_main.xml`: Estructura del menú y barra de herramientas.

---

## 🚀 Requisitos de Entrega

- **Video de Funcionamiento:** [Link al video de YouTube/Drive] (Máx. 3 minutos).
- **Código Fuente:** Repositorio alojado en GitHub.

---

## 🛠️ Cómo ejecutar el proyecto

1. Clonar el repositorio.
2. Abrir en **Android Studio**.
3. Sincronizar **Gradle**.
4. Ejecutar en emulador o dispositivo real (Recomendado: API 30 o superior).

---

## 👥 Integrantes del grupo

- Manuel Facundo Martín García – DNI: 28399283
- Victor Angel Aguilera – DNI: 36220045
- Rafael Nicolas Cuello – DNI: 39396258
- Martin Nahuel Becerra – DNI: 47266622
