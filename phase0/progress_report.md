Progress Report
Date: 10-14-2021

Summary:
Specification:  
Our program is a text-based game that is controlled through a commands line interface, which processes unique custom commands through our own interpreter. The spectacle of this is that our interpreter validates commands based on our organized game structure so new commands and game objects can be added with ease and our interpreter will incorporate it into the game smoothly (nothing needs to be hardcoded or changed, simply just added as expansion). The user starts the project and is shown a commands line. They then input valid commands that pertain to the context of the game (throwing a weapon if you have a weapon and somewhere to throw it). Their commands is then processed and the game state is changed (an enemy defeated perhaps) and these results are printed back to the user in the CLI so they can make their next decision. Through this cyclical interfacing, the user can complete quests, and do independent activities (not quests). Moreover, if they choose to develop their own game entities, as long as it follows our simple structure, they can play the game with their game objects inside, and if they choose to do so, play a completely new game entirely by making their own quests, game objects, etc.

CRC model:
Our CRC model is based on an encounter based system. Each event in the game, such as picking up a weapon, or fighting an enemy is represented in an usecases.Encounter. These Encounters are connected, and in certain paths form Quests. Game Characters can be Players, Enemies, or NPCs. Throughout each encounter, a player will find different things such as Items or Weapons on the ground. Interacting with these is how the game is played and completed. The controls for this textual input through a CLI and these commands are defined by our usecases.Command classes, that represents an Action. These commands are then processed by our GameCompiler which handles the encounters and connects the player’s input to the game state.

A Scenario walk-through:
A simple scenario can be summarized as follows. The user has an axe (assumption). They type in the “throw axe” commands into the CLI. The compiler then uses interfaceadapters.commands.ThrowCommand.java to throw the axe. The result is then returned back to the GameCompiler and printed back to the user. This is all done within the instantiated encounter in the game compiler.

Skeletal program:
Note: Our skeletal program is currently a simplified (more specific) codebase for our overall project. Not everything is fully abstract and hierarchical as it will be when we start developing. We decided as a team it was best to develop the simplified version as a prototype for phase 0, then undergo some restructuring to begin the real project itself. Had we done this from the beginning given our other commitments and the complexity of this project, we feared we wouldn’t have enough to show for phase 0 submission. So we will use a cleaner control flow system that expands on what we have but in a more general, effective manner. With that, we can present our skeletal program with the following components:

We have a commands interfaceadapters.commands.ThrowCommand that throws a throwable object. We have the ThrowableObject interface that ensures an item is throwable. We have an usecases.Encounter that stores this event. We have a player that exists in the encounter where the axe lies. Lastly, we have the CLI up and running which allows the user to execute their commands, then see the results. We also have preliminary error handling to validate the commands and ensure it can execute. This is the program that comprises our Phase0 demo.

Question for TAs:
How can we easily identify different clean architecture layers when the classes and objects in our project don’t seem to be easily confined to one layer?
How can we improve our control flow and avoid circular code?

Successes:
We successfully collaborated and divided work to complete our demo. We also were able to finish all requirements as a group with even work distribution. We also were able to almost always meet as a team and discuss things openly and resolve conflicts maturely, something we troubled with before. Lastly, we believe we successfully produced a working prototype of what we hope our project will become.


Work Breakdown:

Completed
Devan Srinivasan:
Interfaces
Spec document
Progress Report document
Structuring of the CRC model (Code Design)
Scenario walk-through with Michelle
Minor code restructuring in usecases.Encounter, interfaceadapters.commands.ThrowCommand, entities.characters.Player, entities.characters.Character, entities.weapons.Weapon, entities.weapons.Axe
Next Step: adding more interfaceadapters for weapons and other game objects
Riyan Ahmed:
entities.weapons.Weapon and entities.weapons.Axe classes and their documentation
Implementing the ThrowableObject interface in the entities.weapons.Axe class
Reworking the throwObj method in entities.weapons.Axe and how Weapons are thrown
entities.characters.Enemy Class
Minor bug fixes in ThrowableObject Interface, entities.characters.Character
Next Step: adding more weapons

Tomas Harrington:
Action and its subclasses CRC cards
Action.java coded as an abstract class
interfaceadapters.commands.ThrowCommand.java coded and then fixed after testing
ThrowTest.java coded
Organization of code
Wrote TODO’s in code for necessary code expansion post-demo
Worked on figuring out how the demo would work with group members
Next Step: adding more commands and tests

Michal Chernyi:
entities.characters.Character, entities.characters.Player, and entities.Item classes and documentation
CRC cards
Scenario walk through with Devan
Code clean up
Next Step: strengthening player and developing enemy interactions

Henri Langlois :
usecases.Encounter class implementation
Code documentation for various classes
Code design
Implementing a Shootable Weapon Archetype
Implementing variations for generic shootable weapons like the crossbow
Implementing a RefillablePotion variation to Potion
Implementing the Shoot usecase
Added mana to character
Various fixes on preexisting code
Implementing SuspiciousMushroom
Implementing the Stone

Conrad Stanek:
Code design and restructuring
CRC Cards
Specification Document
Major code fixes and reorganization across the project (will be added after phase 0 submission)
Next Step: creating a map that visualizes quest and encounter location and progress

Shehzaad Daureeawoo:
Game compiler and its connectivity
usecases.Command line
Demo code (events and interactions)
usecases.Command constants
restructuring
Next Step: reimplementing the compiler and commands line under our new model

Aside from the individual “next steps” all group members have, please note the following. Proceeding our presentation, our whole group intends to restructure the whole hierarchical control flow of our classes and layers in the project. We found that we omitted too many details in the design phase (these flaws are usually unnoticed) despite our extensive planning. What we learned was that since we are still early in development, we will meticulously design the whole control flow structure of our program. This includes what classes are listed in which layer, and how they all logically use each other to keep clean architecture. This may also involve minor redesigns in the crc model.  Every group member will be majorly involved in this, and all of us will first get started on this before moving forward with the individual “next steps” elements.
