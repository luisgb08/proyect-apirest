@services
  Feature: Make request to service TheCatAPI

# Scenario 1:
# Se consultan 10 imágenes random según API
@Get10Images
Scenario: Make request to method Get for consult 10 random images
  Given I make the connection to the api
  When Execute the method GET with the resource api "images/search"
  Then See that the code returned is 200

# Scenario 2:
# Se consulta una imagen dado su Id, el Id es consultado aleatoriamente
# de una lista generada con el response del escernario 1
@GetImageIdScenario1
Scenario: Make request to method Get for consult image by id
  Given I make the connection to the api
  When Execute the method GET with random Id from Scenario1 with the resource api "images"
  Then See that the code returned is 200

# Scenario 3:
# Se quiere ejecutar petición Get por cada Id de la respuesta del primer escenario.
# Consultar uno a uno los 10 id's
@GetAllImagesIdScenario1
Scenario Outline: Make request to method Get for consult image by all ids from Scenario 1
  Given I make the connection to the api
  When Execute the method GET with the Id <id> from scenario1 with the resource api "images"
  Then See that the code returned is 200
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


# Scenario 4:
@GetRandomImageIdFile
Scenario: Make request to method Get for consult image by id from file
  Given I make the connection to the api
  When Execute the method GET with the random Id from file with the resource api "images"
  Then See that the code returned is 200


# Scenario 5:
# Se quiere ejecutar petición Get por cada Id de imagen contenido en el archivo de insumo para consulta.
# Consultar uno a uno los 10 id's
@GetAllImagesIdFile
Scenario Outline: Make request to method Get for consult image by all ids from file
  Given I make the connection to the api
  When Execute the method GET with the Id <id> from file with the resource api "images"
  Then See that the code returned is 200
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


# Scenario 6:
# Permite probar la carga de una imágen que contenga un gato
@UploadImage   #Metodo Post
Scenario: Make request to method Post for upload image
  Given I open html form data passing a image
  When Execute the method POST with the resource api "images/upload"
  Then See that the status code is 201


# Scenario 7:
# Se valida si la imagen cargada en el escenario 6 se encuentra cargada
@GetImageUpload
Scenario: Make request to method Get for consult upload image
  Given I make the connection to the api
  When Execute the method GET with upload Id with the resource api "images"
  Then See that the code returned is 200


# Scenario 8:
# Se obtiene análisis de la imágen cargada
@GetAnalysisImageUpload
Scenario: Make request to method Get for image upload analysis
  Given I make the connection to the api
  When Execute the method GET for analysys with the resource api "images"
  Then See that the code returned is 200


# --------------------- FAVOURITES
# Scenario 9:
# Creación de favorito a imagen cargada en Scenario6
@CreateFavImage   #Metodo Post
Scenario: Make request to method Post for create favourite
  Given I make the connection to the api
  When Execute the method POST to create fav with the resource api "favourites"
  Then See that the code returned is 200
  And Check if the cat favourite was create successfuly


# Scenario 10:
# Permite consultar favorito creado en escenario 9
@GetFavImageId
Scenario: Make request to method Get for consult fav cat image by id
  Given I make the connection to the api
  When Execute the method GET with fav Id with the resource api "favourites"
  Then See that the code returned is 200


# Scenario 11:
# Permite consultar favorito creado en escenario 9 dado el Sub_id
@GetFavImageSubId
Scenario: Make request to method Get for consult fav cat image by sub_id
  Given I make the connection to the api
  When Execute the method GET by Sub_id with the resource api "favourites?sub_id=gabino88"
  Then See that the code returned is 200


# Scenario 12:
# Se elimina favorito creado en escenario 9 dado su id
@DeleteFavImage   #Metodo Delete
Scenario: Make request to method Delete for delete fav cat image by id
  Given I make the connection to the api
  When Execute the method DELETE with fav Id with the resource api "favourites"
  Then See that the code returned is 200
  And Check if the cat favourite was deleted successfuly

# --------------------- FAVOURITES


# Scenario 13:
# Se elimina la imagen previamente cargada en el Scenario 6
@DeleteImage   #Metodo Delete
Scenario: Make request to method Delete for delete image by id
  Given I make the connection to the api
  When Execute the method DELETE with the random Id with the resource api "images"
  Then See that the code returned is 204


# Scenario 14: Unhappypath
# Se intenta eliminar nuevamente la imagen cargada que ya fue eliminada en el Scenario 9
@TryDeleteImgAgain   #Metodo Delete
Scenario: Make request to method Delete for try delete image already deleted
  Given I make the connection to the api
  When Execute the method DELETE with the random Id with the resource api "images"
  Then See that the code returned is 400
  And Check if the received message is correct


# Scenario 15: Unhappypath
# Se intenta consultar la imagen previamente eliminada
@GetTryDeleteImage
Scenario: Make request to method Get for consult delete image
  Given I make the connection to the api
  When Execute the method GET with upload Id with the resource api "images"
  Then See that the code returned is 400


# Scenario 16: Unhappypath
# Permite probar que no se permita la carga de una imagen que no tenga un gato presente
@UploadNoCatImage   #Metodo Post
Scenario: Make request to method Post for upload no cat image
  Given I open html form data passing a image
  When Execute the method POST with the resource api "images/upload" for no cat image
  Then See that the status code is 400
  And Check if the message in the body response is correct


# Scenario 17:
# Consultar las imagenes cargadas
# Filtrar por Sub_id(2) y limit (2, 5)
@GetImagesUpload
Scenario Outline: Make request to method Get for consult all images upload previously
  Given I make the connection to the api
  When Execute the method GET with params subid <subId> for limit <limit> with the resource api "images"
  Then See that the code returned is 200
  Examples:
    |subId       |limit     |
    |"1245lf"    |2         |
    |"gabino22"  |5         |