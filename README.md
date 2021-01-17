# Online Recipes

## High Level Design
The recipes-parent is the parent project that groups the following components
that makes up the system :  
- core : Contains reusable definitions of the data model 
- server : Is responsible for hosting the database, and categorization 
           of the recipes.
- recipebook : provides api methods to upload and search for recipes

### server
The server component and provides a database that will host all the recipes that are uploaded. 
Because it uses a embedded version of H2 it exposes the database via a TCP port providing a way for the
api service to access the data layer. 


A scheduled task runs that will find any uncategorized documents and then in turn categorize the recipe according 
to the details that was included in the recipe. e.g. (chef name, type of recipe, ingredients)

### recipebook 
This recipebook component exposes the api that can be used to upload and search for recipes. A recipe that has been 
uploaded is initially not available through the search api, but will be once the scheduled task has completed with the 
categorization  


## Instructions
### Build
To package the system run the following command from the ..\recipes root directory : 
   
     mvn clean install

This will compile and produce the various components required to run the system 

     core-0.0.1-SNAPSHOT.jar
     server-0.0.1-SNAPSHOT.jar
     recipebook-0.0.1-SNAPSHOT.jar
     
Both the server and recipebook components have a dependency on the core component

### Run

- Start the server component. Ensure that port 8080 and 9090 is available for use.

      java -jar server-0.0.1-SNAPSHOT.jar

- Start the recipebook component. Ensure that port 8081 is available

      java -jar recipebook-0.0.1-SNAPSHOT.jar
      
 
### Usage

#### Data Layer
In order to view the database enter the following url in your browser

     http://localhost:8080/h2-ui

As password use the obvious one password. 

#### API Documentation 
A description of the api is provided through a swagger user interface. 

     http://localhost:8081/swagger-ui.html


## Sample data


### Upload Recipe 
#### Example 1 - Salmon with a creamy sauce
The json structure below represents a recipe and can be used to create an entry in 
the database.
#### JSON
    {
      "title": "Creamy Pasta with Salmon",
      "type": "Dinner",
      "chefName": "Ricardo",
      "portions": 4,
      "timeToPrepare": "15 mins",
      "timeToCook": "20 min",
      "ingredients": [
        {
          "name": "gemeli or other short pasta",
          "quantity": "375g"
        },
    	{
          "name": "shallot finely chopped",
          "quantity": "1"
        },
    	{
          "name": "butter",
          "quantity": "1 tbsp"
        },
    	{
          "name": "all purpose flour",
          "quantity": "2 tbsp"
        },
    	{
          "name": "white wine",
          "quantity": "125 ml"
        },
    	{
          "name": "cooking cream",
          "quantity": "250 ml"
        },
    	{
          "name": "spinache coarsely chopped",
          "quantity": "1 cup"
        },
    	{
          "name": "Parmesan cheese",
          "quantity": "1/2 cup"
        }, 
    	{
          "name": "Fresh herbs",
          "quantity": "1/2 cup"
        },
    	{
          "name": "steamed salmon",
          "quantity": "1"
        }
      ],  
    "instructions": [ 
    "In a large pot of salted boiling water, cook the pasta until al dente. Keep ½ cup (125 ml) of the cooking water. Drain the pasta and lightly oil. Set aside.",
    "In the same pot, soften the shallot and garlic in the butter. Sprinkle with the flour and cook for 1 minute, stirring constantly. Add the white wine and bring to a boil, stirring with a whisk. Add the cream and bring to boil.",
    "Stir in the spinach, cheese and herbs. Return the pasta to the pot and toss to coat with the sauce. Reheat and add the reserved cooking water or more cream, as needed. Adjust the seasoning. Garnish with pieces of steamed salmon confit and arugula, if desired."
      ]
    }

     
#### Curl 
     curl -X POST "http://localhost:8081/api/recipebook/upload/" -H "accept: */*" -H "Content-Type: application/json" -d "{\"title\":\"Creamy Pasta with Salmon\",\"type\":\"Dinner\",\"chefName\":\"Ricardo\",\"portions\":4,\"timeToPrepare\":\"15 mins\",\"timeToCook\":\"20 min\",\"ingredients\":[{\"name\":\"gemeli or other short pasta\",\"quantity\":\"375g\"},{\"name\":\"shallot finely chopped\",\"quantity\":\"1\"},{\"name\":\"butter\",\"quantity\":\"1 tbsp\"},{\"name\":\"all purpose flour\",\"quantity\":\"2 tbsp\"},{\"name\":\"white wine\",\"quantity\":\"125 ml\"},{\"name\":\"cooking cream\",\"quantity\":\"250 ml\"},{\"name\":\"spinache coarsely chopped\",\"quantity\":\"1 cup\"},{\"name\":\"Parmesan cheese\",\"quantity\":\"1/2 cup\"},{\"name\":\"Fresh herbs\",\"quantity\":\"1/2 cup\"},{\"name\":\"steamed salmon\",\"quantity\":\"1\"}],\"instructions\":[\"In a large pot of salted boiling water, cook the pasta until al dente. Keep ½ cup (125 ml) of the cooking water. Drain the pasta and lightly oil. Set aside.\",\"In the same pot, soften the shallot and garlic in the butter. Sprinkle with the flour and cook for 1 minute, stirring constantly. Add the white wine and bring to a boil, stirring with a whisk. Add the cream and bring to boil.\",\"Stir in the spinach, cheese and herbs. Return the pasta to the pot and toss to coat with the sauce. Reheat and add the reserved cooking water or more cream, as needed. Adjust the seasoning. Garnish with pieces of steamed salmon confit and arugula, if desired.\"]}"
     
### Search Recipe

### Search by type
The type of recipe indicates if it is breakfast, lunch etc. 

### Search by ingredient
To search by ingredient you could supply part of the ingredient e.g. %wine%


## Action Items 
These items needs to be addressed in future releases
- Exception handling needs to be tightened up
- Include the option to upload an image 
- Extensions for creating a shopping list from a recipe 









