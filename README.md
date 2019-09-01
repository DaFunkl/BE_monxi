# BE_monxi

Hello, this is a simple Spring Boot App. It's used to test some Rest Calls against an API (this API).

Main Function is located in com.monx.BE_monxi.App

all configuration goes to com.monx.BE_monxi.configuration

Rest Controller should be placed in com.monx.BE_monxi.controller
eg: com.monx.BE_monxi.controller.Default_Controller
Controller Naming Convention: <ClassName>_Controller.java
All controller shall route their endpoints starting with /api/ 
This way every Rest calls has to go to <domain>/api/<rest-service>

Swagger is set up. It only shows all controller in the controller path.
There is an admin controller path: com.monx.BE_monxi.admin
The Configuration File can be found in com.monx.BE_monxi.configuration.SwaggerConfig

This server implements a TicTacToe game. I'm thinking about adding a few more games eventually.
Game directory can be found in com.monx.BE_monxi.game
Each new game should be placed in this directory, in it's own directory,
For example TicTacToe path is com.monx.BE_monxi.game.tictactoe

Path com.monx.BE_monxi.models is used to implement POJOs.
In com.monx.BE_monxi.models.basic path POJOs can be added, which are used globaly
otherwise create a new folder for each POJO area
like all POJOs for TicTacToe are located in com.monx.BE_monxi.models.ticatactoe

Currently only 20 games of TicTacToe are allowed to be open at the same time. 
Each Game ends after being at least 2 minutes idle (with current configuration, max Time should be around 2 min and ~10 sec).
A Thread is used to look every 10 seconds if any game was idle for more than 2 minutes and closes it if so.  
