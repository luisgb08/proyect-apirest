@services
  Feature: Make request to service TheCatAPI

#Scenario 1:
#Se consultan 10 imágenes random según API
@Get10Images
Scenario: Make request to method Get for consult 10 random images
  Given I make the connection to the api
  When Execute the method GET with the resource api "search?limit=10"
  Then See that the is returned 200

#Scenario 2:
#Se consulta una imagen dado su Id, el Id es consultado aleatoriamente de una lista generada con el response del escernario 1
@GetImageIdScenario1
Scenario: Make request to method Get for consult image by id
  Given I make the connection to the api
  When Execute the method GET with the random Id from Scenario 1
  Then See that the is returned 200

#Scenario 3:
#Se quiere ejecutar petición Get por cada Id de la respuesta del primer escenario. Consultar uno a uno los 10 id's
@GetAllImagesId
Scenario Outline: Make request to method Get for consult image by all ids from Scenario 1
  Given I make the connection to the api
  When Execute the method GET with the Id <id> from scenario 1
  Then See that the is returned 200
  Examples:
    |id  |
    |0   |
    |1   |
    |2   |
    |3   |
    |4   |
    |5   |
    |6   |
    |7   |
    |8   |
    |9   |


#Scenario 4:
@GetImageFile
Scenario: Make request to method Get for consult image by id from file
  Given I make the connection to the api
  When Execute the method GET with the random Id from file
  Then See that the is returned 200


#Scenario 5:
#Se quiere ejecutar petición Get por cada Id de imagen contenido en el archivo de insumo para consulta. Consultar uno a uno los 10 id's
@GetAllImagesId
Scenario Outline: Make request to method Get for consult image by all ids from file
  Given I make the connection to the api
  When Execute the method GET with the Id <id> from file
  Then See that the is returned 200
  Examples:
    |id  |
    |0   |
    |1   |
    |2   |
    |3   |
    |4   |
    |5   |
    |6   |
    |7   |
    |8   |
    |9   |


#Scenario 6:
#Permite probar la carga de una imágen que contenga un gato
@UploadImage   #Metodo Post
Scenario: Make request to method Post for upload image
  Given I make the connection to the api
  When Execute the method POST with the resource api "upload"
#  Then See that the is returned 201


#Scenario 7:
#Se valida si la imagen cargada en el escenario 6 se encuentra cargada
@GetImageUpload
Scenario: Make request to method Get for consult image upload
  Given I make the connection to the api
  When Execute the method GET with upload Id
  Then See that the is returned 200

#Scenario 8:
#Se obtiene análisis de la imágen cargada
@GetAnalysisImageUpload
Scenario: Make request to method Get for consult image upload
  Given I make the connection to the api
  When Execute the method GET with with the resource api "analysis"
  Then See that the is returned 200


#Scenario 9:
#Se elimina la imagen previamente cargada en el Scenario 6
@DeleteImage   #Metodo Delete
Scenario: Make request to method Delete for delete image by id
  Given I make the connection to the api
  When Execute the method DELETE with the random Id
  Then See that the is returned 204


#Scenario 10:
#Se valida que la imagen eliminada ya no exista
@GetTryDeleteImage
Scenario: Make request to method Get for consult image upload
  Given I make the connection to the api
  When Execute the method GET with upload Id
  Then See that the is returned 400


#Scenario 11:
#Permite probar que no se permita la carga de una imagen que no tenga un gato presente
@UploadNoCatImage   #Metodo Post
Scenario: Make request to method Post for upload no cat image
  Given I make the connection to the api
  When Execute the method POST with the resource api "upload"
  Then See that the is returned 400


#Scenario 12:
#Carga de varias imagenes. Cargar 5 en total, 2 con id mío y 3 con id cualquiera
@UploadImages   #Metodo Post
Scenario Outline: Make request to method Post for upload various cat images
  Given I make the connection to the api
  When Execute the method POST with the resource api "upload"
  And with the <image>
  Then See that the is returned 201
  Examples:
    |image  |
    |img1   |
    |img2   |
    |img3   |
    |img4   |
    |img5   |


#Scenario 13:
#Consultar las imagenes cargadas en escenario 12 según filtros o parámetros
#Filtrar por Sub_id(2) y limit (3)
@GetImagesUpload
Scenario: Make request to method Get for consult all images upload with Scenario 12
  Given I make the connection to the api
  When Execute the method GET with the resource api "images"
  Then See that the is returned 200

