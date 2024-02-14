Mejoras:
Aquí hay algunas mejoras que podrías considerar:

1. Refactorización de código duplicado: Había un código duplicado en el método `computerTurn()`. Se creo un método privado que recorre la cuadrícula y realice una acción específica en la primera celda vacía que encuentre.

2. Mejora de la legibilidad: Los números mágicos (como 1 y 2 para representar a los jugadores) se reemplazaron por constantes con nombres descriptivos para mejorar la legibilidad.

3. Optimización de la comprobación de victoria: La comprobación de victoria se realizaba recorriendo todas las filas, columnas y diagonales. Se optimizo esto para que solo se comprueben las filas, columnas y diagonales que contienen la última celda marcada.
