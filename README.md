# Sistema de Gestión para Clínica Privada

![Java](https://img.shields.io/badge/Java-11-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-13-4B8BBE?style=flat-square)
![SQLite](https://img.shields.io/badge/SQLite-003B57?style=flat-square&logo=sqlite&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)

Aplicación de escritorio para gestionar la atención en consultorios externos de una clínica privada: registro de pacientes, emisión de órdenes de consulta con número de turno correlativo y consulta de turnos del día.

## Estado

| Módulo | Estado |
|---|---|
| **Recepción**: consulta y alta de pacientes, órdenes de consulta, turnos | Implementado |
| **Médico**: órdenes asignadas, diagnóstico y prácticas realizadas | Diseñado y prototipado |
| **Administración**: listado de caja por turno y resumen de consultas | Diseñado y prototipado |

## Funcionalidades de Recepción

- **Consulta de paciente por DNI.** Si existe, se habilita la emisión de la orden; si no, el alta.
- **Alta de paciente** con datos personales, domicilio y número de historia clínica. Los menores de 18 años requieren un jefe de grupo familiar ya registrado.
- **Orden de consulta.** Se elige el servicio; la pantalla carga los tipos de consulta y los médicos de ese servicio y muestra el costo total antes de guardar.
- **Turno correlativo por día**, asignado al guardar la orden.
- **Pantalla de turnos** con paciente, DNI y servicio de cada turno emitido.

## Arquitectura

Aplicación en capas con JavaFX (FXML + CSS) y acceso a datos por JDBC.

| Capa | Paquete | Responsabilidad |
|---|---|---|
| Vista | `resources/*.fxml`, `styles.css` | Pantallas y estilos |
| Controladores | `controlador` | Eventos de la interfaz y validaciones |
| Modelo | `modelo` | Entidades del dominio |
| DTO | `dto` | Transporte de datos entre capas |
| Acceso a datos | `dao` | Consultas SQL con `PreparedStatement` |

Patrones: **DAO** (interfaz genérica `Dao<T>`), **DTO**, **Singleton** para la conexión (`ConexionSql`) y **Estrategia** para el cálculo de costos, que está definido en el diseño y figura entre los próximos pasos.

## Cómo ejecutarlo

Requisitos: **JDK 11 o superior** y **Maven**.

```bash
cd clinicaPrivadaDOO
mvn javafx:run
```

La aplicación abre `dbdoo.db` desde el directorio en el que se ejecuta, por eso hay que lanzarla desde `clinicaPrivadaDOO`.

### Datos de ejemplo

La base incluida contiene datos **ficticios** para probar:

| Qué | Datos |
|---|---|
| Pacientes | `28111222` Ana Torres, `30222333` Luis Medina, `25444555` Rosa Díaz, `40555666` Tomás Vega |
| Menor de edad | `52333444` Camila Medina (jefe de grupo familiar: `30222333`) |
| Servicios | Cardiología (Dra. Laura Gómez), Neurología (Dr. Martín Acosta) |

Para probar el alta, ingresá un DNI que no exista. Si el paciente es menor de 18 años, usá `30222333` como jefe de grupo familiar.

## Documentación

- [Especificación funcional y de diseño](docs/Especificacion-funcional-y-diseno.pdf): contexto, casos de uso, reglas de negocio, modelo de dominio, arquitectura, patrón de diseño, modelo de datos y prototipos.
- [Diagramas UML](docs/Diagramas-UML.pdf): clases, casos de uso, colaboración, secuencia y estados.

## Estructura del repositorio

```
sistema-gestion-clinica/
├── clinicaPrivadaDOO/          Proyecto Maven (JavaFX)
│   ├── src/main/java/...       controlador, dao, dto, modelo
│   ├── src/main/resources/...  vistas FXML y estilos
│   ├── dbdoo.db                Base SQLite con datos ficticios
│   └── pom.xml
└── docs/
    ├── Especificacion-funcional-y-diseno.pdf
    └── Diagramas-UML.pdf
```

## Próximos pasos

- Pruebas de unidad (JUnit y TestFX ya están configurados en el `pom.xml`).
- Módulos de Médico y Administración.
- Patrón Estrategia para el cálculo de costos y descuentos.
- Máquina de estados de la orden de consulta.

## Autoras

Jezabel Rosso y Juana Ruiz Luque.
