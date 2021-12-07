# Progress Report
### Devan
#### Worked on:
Since Phase 1 my main work was on higher level changes in respect to the Command Design Pattern, SOLID, Clean Architecture, and overall code functionality. This was done in close collaboration with Michelle, Conrad, and Shehzaad. I worked on satisfying the Command Design Pattern to fulfill its full implementation. I also worked on fixing system UI calls, which was not able to be completed. Lastly, I worked on fixing layer violations to support more clean architecture. In between all of this, I added documentation, wrote the design document with Michelle, formally reviewed many PRs, fixed countless bugs, errors, and poorly coded classes, and upgraded lots of code for better functionality. Like Phase 1, I also coordinated work amongst the whole group. I also re-packaged the codebase with Michelle. Lastly, Conrad and I fixed all of the Accessibility document to be more applicable to our project. As per my  personalPRs, these two were big ones from me in Phase 1 and Phase 2. However, arguably my biggest contribution design wise and functionality wise came in the restructuring PR that was coded by Conrad, where I was on call the whole time and did it with him. [See Conrad's progress report for more] Please see his section of this report for that. That being said, here are two that I did myself.

https://github.com/CSC207-UofT/course-project-stress_coders/pull/87

This PR was major in Phase 2. It included repackaging most of the codebase to resolve issues pointed out by the TA. It also was a main contribution to implementing the Command Design Pattern properly and improving Clean Architecture violations, also pointed out by the TA.

https://github.com/CSC207-UofT/course-project-stress_coders/pull/15

This PR was neat from Phase 1. It was a part of adding diverse interactables and game interactions. Previously, we had just enemies and weapons, so I decided to be creative and added resource harvesting.


### Shehzaad
#### Worked on:
1. Game Logic, allowing missions to progress and creating a runnable game.
2. Created the Encounter to GameState classes which contribute to how commands are executed, help is provided, mission completion and allowing the game to have a story.
3. Created the BuilderSetup which allows a game to be auto-generated based on user preferences.
4. Added a variety of interactables and added NPC Interactions that affect player stats to most interactables.
5. Ran a variety of live tests and debugged throughout, making sure the game ran as desired or was fixed when any issues were found.

Major PRs:

PR #19: https://github.com/CSC207-UofT/course-project-stress_coders/pull/19

PR #22: https://github.com/CSC207-UofT/course-project-stress_coders/pull/22

PR #43: https://github.com/CSC207-UofT/course-project-stress_coders/pull/43

For Phase 2:

PR #103: https://github.com/CSC207-UofT/course-project-stress_coders/pull/103

Given that most of my heavy work was done in Phase 1, phase 2 just required me making sure that the game was still runnable and adding the new features to the game. I worked Phase 2 as admin but again had completed most of the tasks by Phase 1.


### Riyan
#### Worked on:
One main role I had was to create new interactable side missions or games for the player to play, and these are entities and since they are interactable they implement from the interactable class in the entities package, and depending on their functionality they extend different interfaces. We called these “interactables” when we communicated with each other. I was responsible for creating these types of mini-game interactables.

Major PR’s

https://github.com/CSC207-UofT/course-project-stress_coders/pull/91

https://github.com/CSC207-UofT/course-project-stress_coders/pull/85

While making new interactables, such as Joust and HorseRace in PR#85, I realized that the entities folder had too many classes and it was becoming hard to manage, so I had the idea for repackaging entities by having separate packages for the type of entity, and to separate entities by categories such as food, minigames, characters, and weapons. This meant that there were less entities in the root of the entities folder. This way it is more maintainable and easier to understand. Then Devan and Michelle later expanded on the ideas with other entities and packages.

So to start I made a package for minigame interactables and created minigame interactables such as Catapult, Fishing, HorseRace, HorseShoe, PebbleSkip, Teetotum which are all games to be played. These can be seen in PR#91. Originally we had a clean architecture violation, so we needed to separate use cases and commands, so we interface adapters for our different commands for the interactables so I added LaunchCommand and DiceRoll command in PR#91.When creating interactables you also need to create use cases, as well as interfaces that they use and I created duelable, launchable, rateable, diceRollable, in PR#85.

### Henri
#### Worked on:
My main goal during P2 was to expand the range of interactables available to the player and to the rest of the group to use as they saw fit. This included new enemy types for the player to fight, like an ork that deals more damage once his health goes past a certain threshold, or a phoenix that resurrects once killed. They can all be found here:

https://github.com/CSC207-UofT/course-project-stress_coders/tree/main/src/main/entities/characters

I also added the ability to use swingable weapons, adding corresponding weapons as well as the Swing command and usecase. I also added some additional weapons in other categories, as well as food items that could have negative effects on the player. I also lightly modified the food parent class so that it could detect if the effect was positive or negative and correctly convey the information to the player. For all of these, I also added tests. This can all be found in this PR#97

https://github.com/CSC207-UofT/course-project-stress_coders/pull/97

### Tomas
#### Worked on:
My main goal in Phase 2 was creating new interactables and helping solve some of the Clean Architecture. In PR #90 and #94 I created a Maze interactable that uses a timer. Essentially, a maze is a regex which the user must figure out. The regex is a combination of 4 characters: ‘r’ for right, ‘l’ for left, ‘u’ for up, and ‘d’ for down. Thus a regex for a maze could look like “lurulldluuu” and the user would have to take input moves one by one to determine where they were going. They do so by inputting “left”, “right”, “up”, or “down”. If they input anything else it automatically submits a down, since it is the least used character. Then the program checks if the move worked by adding their input as a character to the current and using the solution path as a regex.

The entire time the maze uses the Timer entity to check if the amount of time given to the user has run out. This is where I added code to properly demonstrate the Clean Architecture. As discussed in the Clean Architecture portion of our Design Document, we couldn’t completely solve our Clean Architecture violations when it came to System calls for inputs, however since timer was not an entity within the game put rather a code entity that helped keep track of and time an interactable, I was able to create it in an isolated environment and, therefore, use the Clean Architecture. When Timer needs to get the time from the system to update, it uses the Timing interface and calls on the returnTime method. The Timing interface is implemented by the Time usecase. This time usecase then uses the SystemTimeable interface to call the getSystemTime method. The SystemTimeable interface is implemented by TimeSystem, an interface adapter which then is allowed to directly get the time from the System, and return it to the Time use case which returns it to the Timer. As you can see, we go up the Clean Architecture using interfaces and data is brought back down through those same interfaces using returns. Thus, we do not break the clean architecture. This can be seen in PR #114

Finally, I reviewed pull requests, made comments on other people’s pull requests, communicated with my group consistently on what I was doing and also coded and ran tests on the code. I tried organizing meetings and coordinating our presentation as well as helped to make sure that everybody else was doing well. I discussed with Devan solutions to the clean architecture and other issues as well. Finally, I did the majority of the Accessibility document.

### Conrad
#### Worked on:
1. Wrote the save and load state feature. By serializing/deserializing the gamestate and writing it to a file.

2. (This is the encounter serializer, gamestate serializer and gamestate saver)

3. This includes changes to CL and gamestate as well as many minor changes in almost every class in order to provide the option to save.

NOTE the runtimetypeadapterfactory file was not written by any of us (everything else was however), it's an extension to the Gson library that's not present in the core Library and from what I read the only way to add it to your project was to just add the file.

Major PRs:

PR #100: https://github.com/CSC207-UofT/course-project-stress_coders/pull/100

PR #92: https://github.com/CSC207-UofT/course-project-stress_coders/pull/92

PR #3: https://github.com/CSC207-UofT/course-project-stress_coders/pull/3


For Phase 2:

PR #100- Finished save serialization feature. This PR contains the finished save feature, built upon what I added from a more minor PR #92 which was the skeleton code for the save feature. PR #100 added 3 major classes, (4 if you count the runtimetypeadapterfactory) and a bunch of smaller changes made to other classes to make serialization possible. It was also one of the more complex PR since figuring out how to serialize was quite difficult, (required to download some external library and then find an unofficial addon for that library [runtimetypeadapterfactory]).

Phase 1:

The most major PR from phase 1 was PR#3. NOTE: this PR is from my github account, but it was written by both Devan and I. This PR was a complete restructuring of our phase 0 code from the ground up and we redesigned all of the systems that our program needs to make them usable as previously our phase 0 code was unworkable. It was designed by the both of us and we wrote it together so note that this is both of our contributions.


### Michelle 
#### Worked on:
1. Fixing the command design pattern with devan
2. Fixing solid principles such as single responsibility principle
3. Attempting to create separation of UI and lower levels, which unfortunately we were not able to do
4. Code clean up
5. Writing design doc with devan
6. Repackaging

Major PRs: fixes to command design pattern:

https://github.com/CSC207-UofT/course-project-stress_coders/pull/88

https://github.com/CSC207-UofT/course-project-stress_coders/pull/107

One of the main pieces of feedback from phase 1 was that we did not implement the command design pattern correctly. Devan and I thought this issue through thoroughly, deciding what is the best approach to solve this, which classes need to be changed/ added, what needs to be moved, which class will be the receiver, invoker, etc. and ultimately came to the decision and implementation which we believed was our best option.
