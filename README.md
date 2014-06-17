Grupo 13 de los viernes-noche (VIERNES 13)
==========================================

Tests:
------
[![Build Status](https://drone.io/github.com/dds-utn/2014-vn-group-13/status.png)](https://drone.io/github.com/dds-utn/2014-vn-group-13/latest)

Comandos útiles:
----------------

Crear un proyecto maven (estar parado en el workspace del eclipse).  
`$ mvn archetype:create -DgroupId=ar.edu.utn.frba.dds.demo -DartifactId=demo`

Construir archivos para eclipse.   
`$ mvn eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true`

Clonar este repo de git (estar parado en el lugar donde se desea descargar)   
`$ git clone https://github.com/dds-utn/2014-vn-group-13.git`

Clonar este repo de git pero de un brach determinado(estar parado en el lugar donde se desea descargar)   
`$ git clone https://github.com/dds-utn/2014-vn-group-13.git -b nombre_branch`   
Ejemplo para descargar branch fedeG:   
`$ git clone https://github.com/dds-utn/2014-vn-group-13.git -b fedeG`   

Establecer el usuario git en tu repo local (muy util)   
`$ git config user.name "Billy Everyteen"`   
[Tenes que estar parado en la raiz de tu repo local]

Para el uso mas amigable y copado de agregar y commitear pueden usar gitg (con graficos en linux)   
`$ gitg --all`   
(Es genial)[Tenes que estar parado en la raiz de tu repo local]

Agregar un cambio local   
`$ git add carpeta/archivo.java`   
[Tenes que estar parado en la raiz de tu repo local]

Commitear un cambio local (luego de hacer git add)   
`$ git commit -m "Descripción del commit.... lalala"`   
[Tenes que estar parado en la raiz de tu repo local]

Pushear cambios (subirlos a internet)   
`$ git push`   
[Tenes que estar parado en la raiz de tu repo local]

Pushear cambios a un branch(subirlos a internet)(modo seguro)   
`$ git push -u origin nombre_branch`   
[Tenes que estar parado en la raiz de tu repo local]
Aunque si esta bien configurado pueden usar directamente:   
`$ git push`   

Ver el brach actual   
`$ git branch`   
[Tenes que estar parado en la raiz de tu repo local]

Cambiar de brach   
`$ git checkout nombre_branch`   
[Tenes que estar parado en la raiz de tu repo local]
Cambiar al brach fedeG por ejemplo   
`$ git checkout fedeG`   
[Tenes que estar parado en la raiz de tu repo local]

EXTRA: Si alguno quiere que git no este pidiendo todo el tiempo usuario y contraseña me dice y el viernes le configuro el ssh para no tener que escribirlo mas

*.md por Ezequiel Ayzenberg y Federico Gonzalez*
