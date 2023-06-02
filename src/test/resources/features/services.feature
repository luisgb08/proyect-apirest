@services
  Feature: Make request to service TheCatAPI

@Get10Images
Scenario: Make request to method Get for consult 10 random images
  Given I make the connection to the api
  When Execute the method GET with the resource api "search?limit=10"
  Then See that the is returned 200

@GetImageId
Scenario: Make request to method Get for consult image by id
  Given I make the connection to the api
  When Execute the method GET with the random Id
  Then See that the is returned 200

@DeleteEmployee   #Metodo Delete
Scenario: Make request to method Delete for delete image by id
  Given I make the connection to the api
  When Execute the method DELETE with the random Id
  Then See that the is returned 200