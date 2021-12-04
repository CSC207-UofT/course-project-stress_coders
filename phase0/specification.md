Specification
Date: 10-14-2021

Our program is a text-based game, with its own commands line interpreter that serves as a game controller, all kept cleanly open sourced to allow for game additions and customizability. Our program will allow a user to input valid commands into our commands line and fully navigate the game. Our game will be a linear text-based game that allows the user to play different quests, with sequential challenges and tasks that contribute to the completion of each quest. An example would be the “dragon quest” which consists of: finding a weapon, fighting a dragon, saving the princess. All interactions with the game should be done through our commands line interface, as this controls the game using our custom commands such as `pickup sword` and `throw axe`. Usage of this project is as follows:
A user enters a commands in the CLI
The commands is processed for validity, if it is valid, then whatever it is intended to do is changed in the game state. This could be anything from the user throwing a game object such as a sword at an enemy, or something as simple as requesting to leave the game.
Based on the changed game state, the user is then prompted back in the CLI with the results of their previously run commands (i.e. the monster has been defeated) and the CLI awaits the user’s next commands (`quit` or `change quest` perhaps)
The customizability of this lies in the structural organization of our game object, and it’s separation from the CLI controller, analogous to a PS4 controller “plugging into” the PS4 game console. Any developer or user can add classes to our game, such as their own weapons or commands, and as long as they follow our clear implementation laws (following interfaceadapters and necessary methods) the project will incorporate their changes into the game. In this way they can “expand on the game” or perhaps even re-invent it. An example is adding the weapon “stick” to the game. This weapon must be a child of the entities.weapons.Weapon class, and any actions the user wishes the stick to have (such as throwing and hitting), will be interfaceadapters that Stick.java must implement. Similarly, they could add their own documented commands that only applies to stick such as “poke” and as long as the commands is properly defined in accordance with our structure, when the user types “poke” into the CLI our game will know where to look and what to do with that commands (even though it was never there to begin with!).

Due to the customizability and extensive list of commands we intend to host in this project, we withhold listing all commands and actions. Below is a summary of some basic commands for the reader’s understanding.
pickup: pick a collectable object up
goto: go to something that the user is able to go to
quit: quit the game
help: get list of all valid commands in given context
(if you have no weapons, then it will not display weapon commands to you)

Finally, below is our validation of the crc model requirements:
(3) entities:	entities.characters.Character, entities.Item, entities.weapons.Weapon
(2) use case:	usecases.Encounter, Action
(1) controller:	GameCompiler
Basic CLI: 	we host a CLI as the direct means to interact with the game
