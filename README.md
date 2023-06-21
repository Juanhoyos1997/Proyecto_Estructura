# README - ArchivoCodificador

Este es un programa en Java que permite codificar y decodificar archivos utilizando compresión Huffman. El programa toma un archivo de entrada, lo codifica utilizando el algoritmo de compresión Huffman y guarda el archivo codificado. Luego, puede decodificar el archivo codificado y guardar el archivo decodificado.

## Requisitos previos
- Java 8 o superior.

## Uso

1. Descarga el archivo `ArchivoCodificador.java` y guárdalo en tu directorio de trabajo.
2. Asegúrate de tener los archivos de entrada `entrada.txt` que deseas codificar y `archivo_codificado.txt` donde se guardará el archivo codificado.
3. Ejecuta el siguiente comando en tu terminal o línea de comandos:

```bash
javac ArchivoCodificador.java
java ArchivoCodificador
```

4. El programa codificará el archivo `entrada.txt` utilizando el algoritmo de compresión Huffman y guardará el resultado en `archivo_codificado.txt`. Además, imprimirá la tasa de compresión obtenida.
5. Luego, el programa decodificará el archivo codificado `archivo_codificado.txt` y guardará el archivo decodificado en `archivo_decodificado.txt`.

## Personalización

Si deseas cambiar los nombres de los archivos de entrada, salida o su ubicación, puedes modificar las siguientes líneas de código en el método `main()`:

```java
String rutaEntrada = "entrada.txt";
String rutaCodificada = "archivo_codificado.txt";
String rutaDecodificada = "archivo_decodificado.txt";
```

Simplemente actualiza los nombres o rutas de los archivos según tus necesidades.

## Créditos

Este programa fue escrito por [Tu nombre] como una implementación del algoritmo de compresión Huffman.

## Licencia

Este proyecto está bajo la [Licencia MIT](https://opensource.org/licenses/MIT). Puedes modificarlo y distribuirlo según tus necesidades.