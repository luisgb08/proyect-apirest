# PROYECTO API-REST
Repositorio Personal Proyecto Semillero automation LB

En este repositorio se aloja el Proyecto Final del Semillerio de Automatización de Lulo Bank de Julio de 2023

# Api a probar
https://api.thecatapi.com/v1

## Módulos a probar:
- Images
- Favourites


## Parametría
Toda la parametría del proyecto se encuentra alojada en el Archivo con la siguiente ruta:

'_/Users/lfigueras/Documents/proyect-apirest/src/test/resources/param.properties_'

Se debe tener en cuenta datos importantes tales como la Api-key del usuario registrado en la Api


## Archivos de insumo
Para la ejecución de algunos de los escenarios se tiene un archivo de insumo de id's de imágenes con la siguiente ruta:
'/Users/lfigueras/Documents/proyect-apirest/src/test/resources/randomImgInputFile.properties'

Estos id's se pueden reemplazar por cualquiera de los id's que la Api tenga disponibles

Se tiene el parámetro CANTIDFILE que hace referencia a la cantidad de registros que tiene el archivo de insumo de id de imágenes: randomImgInputFile.properties
Es importante tener en cuenta que la cantidad de test para el Escenario 5 de tipo Outline, debe ser menor o igual a la cantidad de resgitros del archivo insumo de id's de imágenes (Parámetro CANTIDFILE)

## Archivos de salida de datos
Con la ejecución del Escenario 1 se crea un archivo que almacena todos los id's consultados en dicho escenario.
Si el archivo no existe, se crea uno nuevo, y si ya existe con o sin datos, se creará un nuevo archivo sobreescribiendo el archivo que exista previamente.
El archivo es: '/Users/lfigueras/Documents/proyect-apirest/src/test/resources/randomImgOutputFile.properties'

## Archivos de imágenes
Su ruta se encuentra especificada también en el Archivo de parámetros '/Users/lfigueras/Documents/proyect-apirest/src/test/resources/param.properties'
Esta ruta podría cambiar pero también se debe tener en cuenta el nombre de los archivos de las imágenes

### Por ejemplo:
- La imagen cat1_ok.png hace referencia a una imagen que tiene un gato que podrá ser cargada con el método POST en el Escenario 6
- La imagen nocatimg.jpg hace referencia a una imagen que no tiene un gato y que podría intentar cargarse con el método POST en el Escenario 16




## EJECUCIÓN
Para la ejecución del proyecto bastan con ejecutar la clase '**ExecuteService**' desde el paquete runners en:
/Users/lfigueras/Documents/proyect-apirest/src/test/java/com/runners/ExecuteService.java

## Reporte de ejecución
Para la consulta del resporte de ejecución con Serenity BDD puede consultar la ruta:
[ReporteApiRestTheCatApi](file:///Users/lfigueras/Documents/proyect-apirest/target/site/serenity/index.html)




