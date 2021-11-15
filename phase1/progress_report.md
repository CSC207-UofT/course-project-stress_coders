# Progress Report
##Questions
Throughout this project we found ourselves struggling to functionally implement the control flow of this project, while still upholding CA. Originally, we were going to go from the utmost layer (CLI), take commands through game state, send in the player and encounter the commands, then execute them underneath with the entities. This upheld CA but did not support any interactions and responses between game objects which is a fatal restriction on our project’s expandability. So instead we structured it by taking the command, going through game state, and drawing entities from the encounter and executing it as needed. While this upholds CA, these don’t allow for CA friendly special commands, such as picking things up, or consuming items. We did our best, truly did, but would like help as to how to approach this for phase 2 / any possible fixes. Have any ideas?

##Worked well so far:
Command design pattern worked well, we utilized layering and clean architecture to properly parse, process, and execute commands

Our encounter system turned out to be a great choice in design. We could easily implement linear progression and allow for effective game flow.

Codebase restructuring:
1. Restructuring yielded a better, more adaptable, structure of our project

2. A great example is the interactable class. This made processing commands and execution of them very simple.

## Work Split up:
###Devan
#### Worked on:
1. Restructuring the whole codebase with Conrad

2. All commands

3. Project Manager, managed everyone’s PRs and commits, discussed with everyone to make sure we all were on the right track working with, and not against each other. Collaborative leader with Shehzaad and Conrad.

4. Added interactables such as goblin, food, resources, tree, trader

5. Worked on encounter structuring of commands

6. Special commands (along with Shehzaad)

7. Worked on command line structure

#### Next:
1. Add a bunch of new interactables

   1. Tavern, bartender, mythical creatures that can be ridden
   
2. Re structure command, game state interfacing to be more coherent with every command

   1. Allow for state saving and loading as a result of this
  
3. Add more interactables that can be harvested (rock for metal), and add a builder interactable so one can build weapons

###Shezaad
#### Worked on:
1. Created a bunch of interactables including Door and VaultDoor and their related commands and interfaces

2. Implemented Encounter to allow for progression and generic interactions

3. Implemented GameState to keep track of the game and populate the game with encounters and interactables, also allows encounter progression and allows user to choose encounters

4. Implemented Command Line Special Commands with Devan with many features even specific help per situation user faces!

5. Refactored a lot of code to cater for the progression flow

6. Created BuilderSetup class with Conrad that randomly generates and populates the game with encounters and interactables based off of user input, this is done using the BuilderPattern

7. Prepared the whole live demo and ran the entire game using Encounter, GameState, CommandLine and the BuilderSetup, we have a fully working live game

8. Fixed hundreds of bugs to allow player to game interactions with live responses and feedback

9. Co-led and managed with Conrad and Devan


#### Next:
1. Add smart generation to builder Setup, by this I mean that a survival type mission will be populated with more enemies and so on

2. Allow user to pick a genre for the story and add this configuration to builder setup when populating the game (this requires many more interactables)

3. Add a hub/lobby area with some essentials the user needs for their survival that they get sent to at the start of the game and in between each encounter, this will have a trader, mystery box and position dispenser

4. Make better riddles and passcodes for VaultDoor and RiddleGoblin to make game more interesting

###Riyan
#### Worked on:
1. Github actions

2. Implementing various github actions in our project to make it more organized such as automatically labeling state pull requests, state issues, labeling pull requests based on the directory its in

3. Tried to setup ant build system to use continuous integration (CI) workflow in GitHub Actions to build and test our Java project with Ant, so that at every commit we could tell if the codebase would compile and build, and it would say “build passing” or otherwise “build failing” on github



#### Next:
1. Get CI/CD workflow working with github actions

2. Do more coding on the project directly by adding more interactables (we wanted 100 interactables in the game) 

###Henri
#### Worked on:
1. Code documentation for various classes

2. Code design

3. Implementing a Shootable Weapon archetype class, and variations of this archetype (crossbow)

4. Implementing a RefillabePotion variation to Potion

5. Implementing the Shoot command

6. Added the mana character attribute

7. Implementing alternative throw weapons like the stone and the throw knife

8. Implementing the SuspiciousMushroom consumable item


#### Next:
1. Implementing a Melee weapon type

2. Swing and Parry action

3. Damage modifiers

4. Status effects for characters

###Tomas
#### Worked on:
1. Function and format of how the game would work (what would cause success)

2. Adding commands and interactables and determining necessary commands and interactables

3. Adding java docs

4. Fixing redundancies and bugs

5. Refactor to fix clean architecture breaks

6. Analyzing for bugs and informing the group

7. Accepting pull requests

8. Github conflict resolution discussions

9. Organizing group:what is necessary work, who does what, etc.

10. Majority of CRC Model

11. Fixing Javadoc in all entities

#### Next:
1. Work on fixing remaining clean architecture breaks

2. New interactables and commands

3. Continuing analyzing the program for bugs and errors

4. Continue solving conflicts and accepting merge requests

5. Organize group meetings

6. Determining improvements to code and software design

### Conrad
#### Worked on:
1. Restructured the program after phase 0 with devan, including redesigning the entire system that ran the game. I.e our command system, encounter system etc.

2. Did the documentation for the initial restructuring

3. Added the adjective system into our game that randomizes item names and modifies their properties based on the random names

4. Added all the tests for our program ~ 130 tests from 30 files

5. Worked with shehzad on creating the builder and designing it, and fixing issues to help get the demo to run.

#### Next:
1. Create more interactables and commands We need more options for the game to be interesting

2. Create a system to save encounters

3. Create a system to load encounters from a JSON to determine a preset narrative created by us in addition to our random system

### Michelle 
#### Worked on:
1. Implementing interactables classes 
   1. Weapon subclasses
   
   2. Food class and subclasses
   
   3. Animal class and Meat class
   
2. Adding java docs

3. Fixing redundancies and bugs

4. Refactoring to fix clean architecture breaks 
5. Analyzing for bugs and informing the group

6. Design document

#### Next:
1. Work on fixing remaining clean architecture breaks

2. New interactables and commands

3. Continuing analyzing the program for bugs and errors
