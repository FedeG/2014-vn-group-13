Grupo 13 de los viernes-noche ENTREGA 1
==========================================

Diagrama de clases:
----------------

![Diagrama](https://github.com/dds-utn/2014-vn-group-13/blob/forma1/workspace/tpa/DiagramaDeClases.jpg?raw=true "Diagrama")

Casos de uso:
----------------

Caso de Uso : Inscribirse a un partido 

Prueba 1 : Inscribirme Condicionalmente
PRECONDICIONES: un partido y un jugador que se quiere anotar. 
El jugador tiene que tener asociada esa condición.
PASOS: unPartido inscribimeCondicionalmente(unJugador)
POSTCONDICIONES: El jugador se agregó a la lista de condicionales para ese partido

Prueba 2 : Inscribirme De Forma Estándar
PRECONDICIONES: un partido y un jugador que se quiere anotar. 
PASOS: unPartido inscribimeEstandar(unJugador)
POSTCONDICIONES: El jugador se agregó a la lista de estándar para ese partido

Prueba 3 : Inscribirme De Forma Solidaria
PRECONDICIONES: un partido y un jugador que se quiere anotar. 
PASOS: unPartido inscribimeSolidario(unJugador)
POSTCONDICIONES: El jugador se agregó a la lista de solidarios para ese partido

Caso de Uso : Organizar nuevo partido

Prueba 1 : crear un partido de cupo 10 jugadores para el jueves 8 de Mayo a las 21:00 hs en "Panamá 933"
PRECONDICIONES : un Administrador
PASOS: unAdministrador crearPartido(jueves 8 de Mayo a las 21:00 hs, Panamá 933, 10)
POSTCONDCIONES: se creó un partido para el jueves 8 de Mayo en Panamá 933 con cupo de 10 jugadores


Comandos útiles:
----------------

Construir archivos para eclipse.   
`$ mvn eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true`

*.md Federico Gonzalez*
