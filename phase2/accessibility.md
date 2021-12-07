#ACCESSIBILITY DOCUMENT

##Principle 1: Equitable Use
####1a. Provide the same means of use for all users: identical whenever possible; equivalent when not.

In general our program is fairly accessible to all users and always is used in the same way regardless of the user. In that way our program provides identical use regardless of the user. When analyzing whether our program provides the same means to every user, since our program is a text-based program the only points of inaccessibility would stem from a user’s difficulty to give keyboard inputs or a user’s difficulty reading the output. As mentioned later we have means to help mitigate the effects of these points of inaccessibility, specifically in (2c) and (1d) they are outlined, but there are also areas where we could improve these aspects, again in (2c) and (1d) they are mentioned.

####1b. Avoid segregating or stigmatizing any users.

Our program is used the same way for any user. It is purely textual meaning it is not suitable for those that are visually impaired or those who cannot type, but this is not a segregation as this is the literal goal of our project, to be a text-based game. Our program is not biased towards any gender or social group so should not stigmatize anyone. It is set in a medieval time period with neutral game elements.

####1c. Provisions for privacy, security, and safety should be equally available to all users.

Our program does not store sensitive information regardless of the user. As such there are no safety/security measures in our program for any user.

####1d. Make the design appealing to all users.

As is the nature with a text based game, our program outputs a lot of text. We attempted to make the design appealing by providing lots of line separators and indicators to help make our text more readable. Adding to that we also highlighted key portions of our programs output, again to make our output more readable and thereby more appealing for all users.

There are some aspects where this could be improved however. For one we do not have the option to increase the text size outputted by our program. For people with poor eyesight this can be an issue in how appealing/readiable the program is. Furthermore, although we color coded the text, we do not have colourblind specific options, so for users who might be colourblind some of the text colours may not be differentiable leading to a decrease in how appealing the design is for them.

##Principle 2: Flexibility in Use
####2a. Provide choice in methods of use.

This is an area where we could improve on. Currently, the only way to interact with the program is to input commands, and there are only so many commands so there is no guarantee that you have multiple options per encounter. To fix this, we could (1) add more commands and (2) create multiple different ways to input commands. Some ideas are, have a list of options you could select from that would autofill the command instead of requiring the user to type out each command every time they want to call it or perhaps create a GUI that allows the user to select the commands with their mouse instead of having to type everything.

####2b. Accommodate right- or left-handed access and use.

The only input for our program is typing, and as explained later you can type at whatever pace you want to. In that way you can choose to use both hands or one of any preferred hand to interact with the program.

####2c. Facilitate the user's accuracy and precision.

There are some areas where we adhere to this principle and some areas where we could improve. As explained, the only form of input for our program is typing, and that comes mainly in the form of typing commands. In order for the commands to run they must be inputted in a specific format. To help those who may incorrectly input a command, due to some typing imprecision we made sure that inputting an incorrect command would not negatively impact the user. Meaning, the program won't crash and the ingame player and your performance in the game will not be negatively affected by an incorrect command. To help also with accuracy we provide a command that when called will provide the user with the syntax for all commands available to them at a given time that they can reference while typing. Furthermore if a command is incorrectly inputted we try our best to provide meaningful error messages to help the user identify what was incorrect about their input.
The area here where we could improve is to try and have some amount of autocorrect for the user. Currently our commands are quite rigid with how they are needed to be inputted. For instance they are case sensitive. Implementing some amount of autocorrect, to instead of doing direct matching of commands, tried to call the one that best fit, removing case sensitivity, allowing for multiple command input styles, or an option to select from a list instead or the options in 2a would all help to improve how our program accommodates users all of precision levels.

####2d. Provide adaptability to the user's pace.

Our program actually facilitates this quite well. The way our text-based game is structured is in a turn based fashion, as in, you perform an action by inputting a command via some text, some result happens and then you can perform another action. There is no time limit for how long you have for your turn, users have as long as they need to deliberate and perform their desired input so that regardless of how fast a user is at thinking and typing our program can accommodate them. In that way our program accommodates users of any typing ability (and because typing is the only action performed this would essentially amount to accommodating users with all prefoiancy levels).

##Principle 3: Simple and Intuitive Use
####3a. Eliminate unnecessary complexity.

Our program is a complex game, but this is as intended. Building on this, we actually simplified some functionality such as diverging from quests and having a free landscape type thing to a more strictly linear game. Perhaps the fact that one can interact with side interactables regardless of their main focus adds complexity, but we thought it would be fun.

####3b. Be consistent with user expectations and intuition.

We are consistent with user expectations. As stated in 3a, our commands all take the same form, and thus the user can expect to have to write the same format with every single action or command they use. Furthermore, we have a howTo portion where the user can ask for help and receive the format and parameters for every action they could take at the current moment.

####3c. Accommodate a wide range of literacy and language skills.

We could improve upon this by adding the ability to use different font sizes so that a user with any range of literacy or language skills can use our program. As mentioned in an issue we have, we could use serialization and language yml files to translate all our strings.

####3d. Arrange information consistent with its importance.

Currently, our program tells you what to do based on importance. Whether you are in a main mission, or side interaction, when you enter this event the game prompts you with the command to do and how to use it. Then additional commands can be used if needed, but the important ones are shown first. Likewise, your progress in the game is then prompted after completion, before allowing you to choose a new activity.

####3e. Provide effective prompting and feedback during and after task completion.

Throughout an interaction we prompt the user for their next move and we give feedback based on their input consistently. If there is an error in an input, we ask the user to try again and if they need help they can ask for it and receive information on commands, what they do, and how to execute them.

##Principle 4: Perceptible Information
####4a. Use different modes (pictorial, verbal, tactile) for redundant presentation of essential information.

Given the scope of our game was to be a text-based game, we only communicate information using text. However, we do have colours in the text to make it more visually appealing.

####4b. Provide adequate contrast between essential information and its surroundings.

We use colour highlighting to highlight important information, and separate different things logically, such as response from the game versus user input, and completion of quests.

####4c. Maximize "legibility" of essential information.

We use headers and capital letters to make essential information easily viewable. We also keep information concise so as to not confuse the user.

####4d. Differentiate elements in ways that can be described (i.e., make it easy to give instructions or directions).

Our program is very structured, in the sense that everything operates in a linear manner, step by step, where all prior steps must be completed to progress. Therefore, it is very easy to describe the elements of the game. An example is: “Choose to start a new game, or load an existing one. Then select  your user name, game difficulty, and game duration. Then select a mission”.

####4e. Provide compatibility with a variety of techniques or devices used by people with sensory limitations.

Unfortunately, given that we intentionally made a text-based game you can only play it on a computer or mobile device (it is not deployed for this though) and must be able to see the text and type in order to use the game.

##Principle 5: Tolerance for Error
####5a. Arrange elements to minimize hazards and errors: most used elements, most accessible; hazardous elements eliminated, isolated, or shielded.

There are no real hazardous elements. We heavily type-check for errors and only allow progression when the precise string needed is typed, depending on the action. In this sense, errors are very much shielded since progression is strictly linear.

####5b. Provide warnings of hazards and errors.

We don’t have any warnings or hazards for errors as the user is free to do as they please, but only certain commands will progress them through the game. The rest will do nothing (except for exiting).

####5c. Provide fail safe features.

This is not very applicable. There is nothing the user could do that would raise the risk of error, which having a fail safe would prevent.

####5d. Discourage unconscious action in tasks that require vigilance.

The user may accidentally hit the wrong keys but this is okay. Any failed command can be repeated. The only breach in this design principle perhaps is in the very beginning where we declare that incorrect input will default to a specific one, in which case “unconscious action” could result in the wrong decision. But thankfully this happens early and only happens there so one can simply restart the game with no consequence.


##Principle 6: Low Physical Effort
####6a. Allow user to maintain a neutral body position.

The user can remain in a neutral body position. Playing our game requires the same bodily arrangement as typing a document or email.

####6b. Use reasonable operating forces.

Our program is interfaced solely through typing with the user, so as such it requires low operating forces, and is very low effort.

####6c. Minimize repetitive actions.

We basically have no repetitive actions. Again, all actions are just inputting commands which are subject to the task you are trying to accomplish, and all of them are just 1 command followed by prompts. However, we do know that the hint command requires you to type it multiple times to receive each hint, but that is the purpose of a “hint”.

####6d. Minimize sustained physical effort.

There is next to know physical effort. One would sustain the same physical effort in this game as typing an essay for school. There is no time pressure, you can rest your hand and wrists at any time, and come back to the game whenever you feel able enough to play.

##Principle 7: Size and Space for Approach and Use
Our game plays out fairly linearly, and so all important elements are directly in front of the user. All components can be reached since everything takes place within the IntelliJ terminal, however sitting or standing does not matter as this is software not hardware. Accommodations for variations in hand and grip size as well as providing adequate space for the use of assistive devices or personal assistance, though, is a hardware design issue. Since we are designing software, it is not something which we must account for. Thus, the only way we could improve upon this is by using a GUI and centering all the information as well as creating buttons for actions. This would create a clear line of sight for all important elements as well as make all components even more comfortable to reach.

