@services2
  Feature: Make request to service TheCatAPI

#Scenario 6:
#Permite probar la carga de una imágen que contenga un gato
@UploadImage2   #Metodo Post
Scenario: Make request to method Post for upload image
  Given I open html form data passing a cat image
  When Execute the method POST with the resource api "upload"
#  Then See that the is returned 201


#Scenario 6 modificado:
#Permite probar la carga de una imágen que contenga un gato
#@UploadImage2   #Metodo Post
#Scenario: Make request to method Post for upload image
#  Given I open html form data passing a cat image
#  When Execute the method POST with the resource api "upload"
#  Then See that the is returned 201
