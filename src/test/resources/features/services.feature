@services
  Feature: Make request to service TheCatAPI

#Scenario 1:
#Se consultan 10 imágenes random según API
@Get10Images
Scenario: Make request to method Get for consult 10 random images
  Given I make the connection to the api
  When Execute the method GET with the resource api "search"
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
@GetAllImagesIdScenario1
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
@GetRandomImageIFile
Scenario: Make request to method Get for consult image by id from file
  Given I make the connection to the api
  When Execute the method GET with the random Id from file
  Then See that the is returned 200


#Scenario 5:
#Se quiere ejecutar petición Get por cada Id de imagen contenido en el archivo de insumo para consulta. Consultar uno a uno los 10 id's
@GetAllImagesIFile
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


##Scenario 6:
##Permite probar la carga de una imágen que contenga un gato
@UploadImage   #Metodo Post
Scenario: Make request to method Post for upload image
  Given I open html form data passing a image
  When Execute the method POST with the resource api "upload"
  Then See that the status code is 201

  #See that the return code is 201

###Scenario 7:
###Se valida si la imagen cargada en el escenario 6 se encuentra cargada
@GetImageUpload
Scenario: Make request to method Get for consult upload image
  Given I make the connection to the api
  When Execute the method GET with upload Id
  Then See that the is returned 200


###Scenario 8:
###Se obtiene análisis de la imágen cargada
@GetAnalysisImageUpload
Scenario: Make request to method Get for image upload analysis
  Given I make the connection to the api
  When Execute the method GET with with the resource api "analysis"
  Then See that the is returned 200


##Scenario 9:
##Se elimina la imagen previamente cargada en el Scenario 6
@DeleteImage   #Metodo Delete
Scenario: Make request to method Delete for delete image by id
  Given I make the connection to the api
  When Execute the method DELETE with the random Id
  Then See that the is returned 204


##Scenario 10:
##Se valida que la imagen eliminada ya no exista
@GetTryDeleteImage
Scenario: Make request to method Get for consult delete image
  Given I make the connection to the api
  When Execute the method GET with upload Id
  Then See that the is returned 400
#
#
##Scenario 11:
##Permite probar que no se permita la carga de una imagen que no tenga un gato presente
@UploadNoCatImage   #Metodo Post
Scenario: Make request to method Post for upload no cat image
  Given I open html form data passing a image
  When Execute the method POST with the resource api "upload" for no cat image
  Then See that the status code is 400


##Scenario 12:
##Consultar las imagenes cargadas
##Filtrar por Sub_id(2) y limit (2, 5)
@GetImagesUpload
Scenario Outline: Make request to method Get for consult all images upload previously
  Given I make the connection to the api
  When Execute the method GET with params subid <subId> for limit <limit>
  Then See that the is returned 200
  Examples:
    |subId       |limit     |
    |"1245lf"    |2         |
    |"gabino22"  |5         |
