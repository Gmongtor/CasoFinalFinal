# CasoFinalFinal
## Link: https://github.com/Gmongtor/CasoFinalFinal.git
### Pregunta 1:
1. b
2. b
3. b
4. a
5. b
### Pregunta 2:
#### 1. 
Para el diseño de un programa destinado a dispositivos portátiles en telemedicina con una memoria de solo 4 KB, evaluamos tres alternativas considerando su complejidad computacional y uso de memoria:

La primera alternativa ofrece una complejidad computacional lineal 
𝑂
(
𝑛
)
O(n) y un uso de memoria constante 
𝑆
(
1
)
S(1). Esta opción es muy eficiente en términos de memoria ya que siempre utiliza la misma cantidad mínima, pero el tiempo de procesamiento aumenta directamente con el tamaño de los datos.

La segunda alternativa tiene una complejidad computacional constante 
𝑂
(
1
)
O(1), lo cual es ideal para rapidez, pero su uso de memoria es proporcional al cuadrado del tamaño de los datos 
𝑆
(
𝑁
2
)
S(N 
2
 ). Esto resulta impracticable dado el límite de memoria disponible y el tamaño de entrada de los datos.

La tercera alternativa presenta una complejidad computacional logarítmica 
𝑂
(
log
⁡
2
(
𝑁
)
)
O(log 
2
​
 (N)) y un uso de memoria que también crece logarítmicamente 
𝑆
(
𝑂
(
log
⁡
2
(
𝑁
)
)
)
S(O(log 
2
​
 (N))). Esta propuesta equilibra bien el tiempo de procesamiento y el uso de memoria, adaptándose eficientemente a las limitaciones del dispositivo.

Conclusión:
Dado el estricto límite de memoria y la necesidad de procesar eficientemente los datos, la tercera alternativa es la más recomendable. Su enfoque logarítmico en el uso del tiempo y la memoria la convierte en una solución viable y eficaz para manejar grandes volúmenes de datos en dispositivos con recursos limitados, cumpliendo con los requerimientos de un sistema de telemedicina eficiente.
#### 2. 
Las tablas hash y los árboles son estructuras de datos que sirven para almacenar y organizar información, pero se utilizan de maneras distintas dependiendo de las necesidades específicas de cada aplicación.

Tablas Hash: Son ideales para situaciones donde la rapidez en la inserción y búsqueda de datos es crítica. Utilizan una función hash para determinar dónde almacenar cada elemento, permitiendo operaciones de inserción y búsqueda muy rápidas, típicamente en tiempo constante 
𝑂
(
1
)
O(1). No mantienen ningún orden natural entre los elementos, lo que puede ser un inconveniente si el orden de los datos es importante. Son especialmente útiles en aplicaciones como cachés o bases de datos donde se requiere un acceso rápido y directo a los elementos a través de claves únicas.

Árboles: A diferencia de las tablas hash, los árboles (especialmente los árboles de búsqueda binaria) mantienen los datos ordenados, facilitando operaciones como la búsqueda de rangos y el mantenimiento del orden natural durante las inserciones y eliminaciones. Estas operaciones suelen tener una complejidad de 
𝑂
(
log
⁡
𝑛
)
O(logn) en árboles balanceados. Esto los hace adecuados para aplicaciones donde el orden de los datos es crucial, como en sistemas que manejan grandes volúmenes de datos que necesitan ser constantemente ordenados o evaluados.

Cuándo usar cada uno:

Tablas Hash: Cuando el rendimiento de acceso directo es más importante que el orden de los datos.
Árboles: Cuando se necesita mantener un orden entre los datos y realizar búsquedas complejas, como búsquedas de rango.
En resumen, la elección entre una tabla hash y un árbol se basa en si el orden de los datos y las operaciones relacionadas son importantes o si se prioriza la velocidad de acceso a través de claves únicas.
#### 3. 
Pilas y colas son estructuras de datos que manejan elementos de formas opuestas. Las pilas utilizan un enfoque "último en entrar, primero en salir" (LIFO), ideal para aplicaciones como el deshacer acciones en programas o manejar las llamadas de funciones. En contraste, las colas operan bajo un principio "primero en entrar, primero en salir" (FIFO), perfecto para tareas que requieren procesamiento en el orden de llegada, como en la gestión de impresiones o el manejo de eventos en interfaces de usuario.

Ambas estructuras ofrecen operaciones de inserción y eliminación con una complejidad de 
𝑂
(
1
)
O(1), permitiendo acciones rápidas y eficientes. La elección entre una y otra depende de si necesitas procesar los últimos elementos primero con pilas o mantener un orden de llegada con colas.
#### 4. 
GPT
El método de ordenación por inserción es un algoritmo simple que construye la lista final ordenada un elemento a la vez. Funciona de manera similar a cómo una persona ordena las cartas en sus manos. Comienza con un elemento como parte de la lista ordenada, y luego va tomando uno por uno los elementos restantes, insertándolos en su posición correcta dentro de la lista ya ordenada hasta que todos los elementos han sido incorporados.

Para el conjunto de datos dado: 50, 20, 84, 13, 22, 16, 89, 85, las iteraciones del método de ordenación por inserción se desarrollarían de la siguiente manera:

Inicio con el primer elemento (50) considerado como la lista ordenada.
Insertar 20: Comparar con 50 y colocarlo antes, porque 20 es menor. Nuevo orden: 20, 50.
Insertar 84: Comparar con 50, no necesita moverse porque 84 es mayor. Nuevo orden: 20, 50, 84.
Insertar 13: Comparar primero con 84, luego con 50, y finalmente con 20. Insertar antes de 20. Nuevo orden: 13, 20, 50, 84.
Insertar 22: Comparar con 84, luego con 50, e insertar entre 20 y 50. Nuevo orden: 13, 20, 22, 50, 84.
Insertar 16: Comparar sucesivamente y colocarlo entre 13 y 20. Nuevo orden: 13, 16, 20, 22, 50, 84.
Insertar 89: Comparar con 84, y colocarlo después porque 89 es mayor. Nuevo orden: 13, 16, 20, 22, 50, 84, 89.
Insertar 85: Comparar con 89 y colocarlo antes, entre 84 y 89. Nuevo orden: 13, 16, 20, 22, 50, 84, 85, 89.
Cada elemento se compara con los elementos de la lista ordenada desde el más grande hacia el más pequeño hasta encontrar su posición correcta, lo que puede ser eficiente para listas pequeñas o listas que ya están parcialmente ordenadas, pero el método puede volverse ineficiente para listas grandes debido a que cada inserción puede requerir desplazar muchos elementos.
### 3.
#### a) ¿Qué imprime el código? En caso de que no compile indique el motivo y arregle el programa como considere conveniente. Explique su solución de manera concisa.
El código proporcionado intenta realizar un cálculo recursivo pero no compilará correctamente debido a varios errores:

Error de Sintaxis en la Línea 4:
System.out.println(a) está mal escrito como System.out.println(a).
Problema de Lógica en la Recursividad:
La función recursive es llamada con un valor inicial de b = -2, y en el código no hay un caso base que maneje b negativos. Esto resultará en un bucle infinito de llamadas recursivas, ya que b nunca alcanzará 0, y continuará decreciendo indefinidamente.
Código Corregido:
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            int a = recursive(1, -2);
            System.out.println(a);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int recursive(int a, int b) {
        if (b < 0) {
            throw new IllegalArgumentException("El segundo argumento no debe ser negativo.");
        } else if (b == 0) {
            return 1;
        } else if (a == 0) {
            return 0;
        } else {
            return a * recursive(a, b-1);
        }
    }
}
Explicación de la Solución:

He agregado un caso base para manejar valores negativos de b, lanzando una excepción para indicar que el valor no es válido.
He corregido el error tipográfico en la llamada al método println.

#### b) Explica brevemente qué cálculo está haciendo y qué tipo de recursividad está empleando.
