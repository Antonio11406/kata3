# Kata 3 - Transformación de datos a JSON

## Objetivo de la entrega
El objetivo de esta práctica es construir una aplicación en Java que lea datos tabulares de un origen externo (en este caso, un conjunto de videojuegos en formato CSV), los transforme en objetos puros de dominio y genere una salida estructurada en formato JSON. 

El diseño se centra en la separación estricta de responsabilidades: el componente de lectura y parsing es independiente del de serialización, y la entidad de dominio no conoce el origen ni el destino de los datos.

## Dependencias y JDK
*   **Lenguaje:** Java (JDK 25)
*   **Gestor de dependencias:** Maven
*   **Biblioteca JSON:** Gson (versión 2.10.1)

Se ha elegido Gson para evitar la concatenación manual y frágil de cadenas de texto, garantizando que el archivo final sea siempre un JSON válido y bien formateado.

##  Estructura del proyecto y clases principales
El proyecto sigue una arquitectura desacoplada y se divide en las siguientes clases:

*   `Main`: Orquesta la ejecución conectando el lector, la lista de objetos en memoria y el serializador.
*   `domain.Game`: Entidad de dominio pura que representa un registro válido. Utiliza los enums `Platform` y `Genre`.
*   `io.UrlGameReader` / `io.FileGameReader`: Componentes encargados de abrir el flujo de datos (archivo local o conexión a internet) y transformar (*parsear*) las líneas de texto en objetos `Game`. Implementan la validación explícita para descartar registros mal formados.
*   `io.JsonGameSerializer`: Recibe los objetos `Game` ya validados y genera el archivo `games_output.json`, incluyendo un bloque adicional de metadatos o resumen calculado.

##  Repeticiones y variaciones introducidas
Siguiendo la dinámica de repetición deliberada, esta kata se ha realizado en iteraciones con las siguientes variaciones para reducir la fricción técnica:
1.  **Iteración 1:** Lectura básica de un archivo `games.csv` local usando `FileGameReader` y generación del JSON con los registros válidos.
2.  **Iteración 2:** Inclusión de un contador de registros descartados (por campos vacíos o tipos de datos erróneos) en el bloque de metadatos del JSON resultante.
3.  **Iteración 3:** Cambio en el origen de los datos. Se sustituyó el `FileGameReader` por un `UrlGameReader` para leer el CSV directamente desde un enlace *Raw* de GitHub. Esto permitió comprobar que la lógica de transformación y serialización se mantenía intacta a pesar de cambiar la fuente de entrada.

##  Evidencias de verificación y pruebas
Se ha verificado la robustez del parser utilizando un *dataset* que incluye casos de error forzados:
*   **Entrada normal:** Juegos con todos los campos correctos (se procesan exitosamente).
*   **Campos vacíos / incompletos:** Registros donde falta el año o no tienen las columnas mínimas (ej. `5,Juego Incompleto,PS4,,ACTION`).
*   **Tipos de datos incorrectos:** Registros con años en texto (ej. `dos mil`) o plataformas no existentes en el enumerador.
**Resultado:** El programa captura las excepciones (`NumberFormatException`, `IllegalArgumentException`), descarta estos registros de forma segura sumándolos al contador de "descartados" y continúa con la ejecución sin interrumpirse.

##  Vídeo explicativo
A continuación, se adjunta el enlace al vídeo (duración menor a 7 minutos) donde se explica el diseño, la separación de responsabilidades y la ejecución ante registros mal formados:

*   **Enlace al vídeo:** https://drive.google.com/file/d/1KOwa_KR-EcLzBSWIyyKfshZBRCLmavxt/view?usp=sharing
