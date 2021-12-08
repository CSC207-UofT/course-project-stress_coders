# Design Document
## SOLID Principles
The Open/Closed Principle is adhered very well in our design. All our classes that should be expanded can be done without modifying the original class. For instance, the Interactable class is very generalized such that many subclasses are able to extend it, with more specifications in them and with no need to change an already existing class such as Interactable. In fact, almost all our entities are descendants of Interactable. As well this allows many other classes to be closed for modification, as the parameters don’t need to be changed every time a different entity is used as they are all of type Interactable, so methods can simply take that as a parameter.

The Liskov Substitution principle is also followed closely in our project. As mentioned above this can be seen in the entities, as interactable was made very generally such as it’s descendants have extra methods and extra members, they do not remove or modify any of the behaviours of Interactable. The same goes with Command, it was implemented very generally, with only one member and an execute() method that all command subclasses need and the parameters work for all the command subclasses as well, which allows each command subclass to have the properties from command plus extra individual properties.

We believe the Interface Segregation principle was also done very well. All of our interfaces have 1-2 methods only. This allows classes to implement exactly what they need and nothing more. For example, an Enemy or Animal only has to implement how they handle getting hit, meanwhile a weapon doesn’t have to handle getting hit, which wouldn’t make sense, but does have to have a hit probability and damage value.

We also believed we have good use of dependency inversion principle because our higher level classes depend on interfaces not on their lower level classes. For instance, the command subclasses check what kind of interface they received instead of checking what entity they received.  From Phase1, we were notified that we violated this rule since our command line depends directly on low level classes, that is, they directly use them.  In all honesty, we did our best to resolve this. Unfortunately too much of our project relies on type-casting and type-checking using these low level classes. We do often use the abstract ‘Interactable’ class, as opposed to the direct low-level classes. which is the parent of all most of our low level entities.

In phase 1, we thought we could improve on the Single Responsibility Principle. All of our entities did follow this principle, for instance Enemy class is only in charge of Enemy reaction to getting hit, handling hit and responding.  As well, in our interface adapters, we have 2 classes BuilderSetup and GameState, one is responsible for generating and building encounters while the other is responsible for loading, requesting, and returning different encounters. However, Encounter class had too many responsibilities. It was responsible for interacting with the interactables such as listing them while also being responsible for creating aspects of an encounter, or using the encounters. As a solution, in phase 2 we decided to extract some methods into a new class, the Interactables Manager class. Now, the InteractablesManager class is responsible for the interactables interactions such as listing the interactables, getting their ID’s, adding adjectives, etc. Meanwhile, the encounter class is now strictly responsible for managing encounters by displaying interactions, choosing main missions, etc.

## Clean Architecture
The program is for the most part consistent with clean architecture, aside from one violation, which we were not able to fix.

The dependency rule is followed in terms of flow from outer to inner, as in no inner layer uses an outer layer. For example, the CommandLine class in frameworks receives commands from a user and produces an output, it uses GameState in interface adapters, which uses Encounter in use cases among other use cases, which uses Character and other interactables in entities. The Encounter loads, requests, displays the interactables, etc. The GameState requests, loads, gets current  Encounter, etc.  Finally, the CommandLine  class uses GameState to load, request encounters, etc. to allow the user to interact with the encounter, getting outputs about the the steps in the encounter and writing input which the CommandLine accepts, then uses game state, which uses encounter, which can use interactables, to react to inputs accordingly.

Unfortunately, there are some clean architecture violations, which we could not figure out how to fix. Some classes in frameworks and interface adapters import and use entities, which is a violation of the adjacent layer aspect of the dependency rule. However, we only use those entities to cast and type check. Because our game has an open world aspect where the user can call on any interactable, we have to be able to type check those interactables before running commands from the player. So, we are not sure how to get around that. As well, despite this jump in layers, changes in the outer layers will not impact the interactables.

From our Phase 1 revision, we were not able to resolve the fact that higher layers use Interactable (and a few others) for type checking and casting, as we feared this would be too major a change for our time frame. However, we did do our best to resolve the issue of handling UI elements (interacting with System console) from lower layers (the entities). Warning: We were not able to successfully complete this. We had two main ideas. Our first was to make mirror UI commands on a higher level that interacted with CommandLine (legally) and handled all UI elements, such as printing and inputting. However, this required us to type-check to navigate logic structures, and print certain things, which would require us to import entities on the UI layer, a clear violation. Below is a snip of this

![](/phase2/MdRes/firstpic.png)

This is in frameworks. Then the information get passed on from layer to layer, for instance here is the call in the interface adapter's layer:

![](/phase2/MdRes/secondpic.png)

And then it finally ends up here:

![](/phase2/MdRes/lastpic.png)

Our second idea was to use callbacks, this was more complicated though. We never bothered implementing it because the only feasible solution we thought of required multiple return types. Our idea was to use an Inputtable interface that had entities (and other classes that needed input) to implement a getInput() method. This method would return an InputCallback object (that we would make) that had a wrapper of text we wanted to print before and after input. Then the CLI would receive this object, instead of the return string it was expecting and handle it accordingly. Once it received input at the UI layer, it would send it back down to the command that originally sent the InputCallback object. Then the command, depending on what stage in its inputting progress (indicated in the InputCallback object) can decide what to do next. An example is the vault door asking for a password. It would return a callback object asking for a password. When it is called with this updated object it will respond accordingly. This was more theoretical. We note these two ideas to demonstrate we worked diligently in phase 2 trying to solve this issue, but ultimately couldn’t, and hope our efforts are recognized by the marking team.

We would also like to acknowledge that we had to compromise on some (known) clean architecture violations in the save/load state. These violations contributed to the existing issue that permeated through our project that we discussed with the TA, that being higher levels using low-level entities for menial tasks. We decided adding the save/state functionality and slightly worsening this clean architecture issue was a valid tradeoff.

Finally, we did implement Timer in a manner which follows the Clean Architecture. When Timer needs the System time, it originally made a system call as seen in PR #90 and #94. However, in PR #114 we changed that. When initialized the Timer is given an instance of a use case that implements Timing, a use case interface. When it needs the time, it simply calls on the Timing interface’s returnTime method. The Timing interface is implemented by the Time use case. When initialized, Time is given an instance of an interface adapter that implements SystemTimeable. When returnTime is called it calls on the SystemTimeable interface’s getSystemTime method. The SystemTimeable interface is implemented by TimeSystem which, when getSystemTime is called, it simply returns the time from the system, which it can do since it is an interface adapter. When we say “an instance of an object that uses” we are simply referring to the fact that Timer does not know that it is using Time since it only calls on Timing. Same thing with Time not being aware that it is using TimeSystem. Timer goes through the Timing interface to use Time and then Time goes through the SystemTimeable interface to use TimeSystem, which then returns the data back through the interfaces. This results in the System call being done in the proper outer layer and using inverted dependency to go up the Clean Architecture. Therefore, Timer and the manner by which it gets the time from the system illustrates a good example of Clean Architecture in our codebase.

#### Scenario Walkthrough: Side Interaction - Animal - Shoot:
In Phase 2 our gameplay did not change so we left this for the TA, although they would have already been versed in the scenario walk through and how the game is played.

The Scenario starts with the Main class (framework) that has the main method. The user sees a welcome message which is printed out in the main method. Then start() from CommandLine class (framework) is called.  

The start() method prints out a sentence that asks if the user would like to start a new game or load an existing one. Then it prompts for the user's name. The user then types their name, which the Scanner reads. Then it uses GameState (interface adapter) which uses PlayerManager (use case) to set the name of the player.

The method then:
1. calls another method A which is also in CommandLine, to print out the next line asking what length of game they want to play and giving options.  The user types in ‘short’.  

   1. this method A  creates a BuilderSetup object (interface adapter) using the length selected, and calls on another method  B in BuilderSetup to create encounter types and details  for the encounters.
   
   2. Then this method A loads encounters for the quest using GameState and randomizes the features of the encounters
   
2. The start() method calls a method C from GameState and prints out it’s result

   1. Method C prints out a message asking to pick a quest, and prints out the todo and completed encounter options. The user then types in the encounter choice. For example, “Heroic 1”.
   
   2. If the user typed in an existing encounter, the method C prints that they selected a quest.
   
   3. Method C calls on method D from Encounter (use case)
   
      1. Method D calls on a method E also from Encounter, which prints out mission and side interaction options then prints a message asking to select a mission or interaction
     
      2. Method D than reads the user input (for example “Animal” if it an option for randomized side interaction) , and shifts attributes accordingly
      
      3. When a side interaction is selected, a message stating that a side interaction was selected is printed
      
   4. Then method C prints out good luck
   
3. Type in “help” and the start() method will call on method E, also in CommandLine to handle that

   1. Method E returns method F in GameState
   
      1. Method F will call on method G which is in Encounter, this method will give different help depending on the interaction you picked, in the case of animal, for example,  it will print the current weapon of the player (entity) and an example of how to write the command
      
4. Now that the weapon and command is known, the user will write in the command, for instance, if the weapon is Nut Slinger, and the command is shoot, the user will type in “shoot: shoot_obj=Nut Slinger, target=Animal”

5. start() call a method H, also in CommandLine based on this input.

   1. This method will parse the command, than return a method I from GameState
   
      1. Method I will call on Method J in Encounter
      
         1. Method J is used to progress through an interactable and encounter. It will call on a method K in the Command class the user typed in such as “shoot”
         
            1. Method K will ensure the command the user typed is correct and then it will calculate whether the command hit or missed based on a randomized value and print it out to the user

## Design Patterns
As pointed out by our TA in Phase 1 review, we didn’t completely use the Command Design Pattern. We have separated commands to act more as a wrapper for the use cases below them that modify the entities, as recommended. Now we can clarify the better usage of the command design pattern in our project. We have successfully implemented the necessary classes in the Command Design Pattern:
1. Invoker: GameState is our invoker. It invokes commands through the Encounter which is a use case. This class invokes commands.
2. Command: The Command class is our abstract command class.
3. ConcreteCommand: The concrete command classes are each sub-command in the interface-adapters layer. These commands call on the corresponding use cases that modify entities.
4. Client: CommandLine is our client. You can see that it triggers the Invoker to call commands, and saves the commands into the invoker using CommandConstants. It then receives the output of commands and prints it out, as the Client should. This client orchestrates the usage of commands.
5. Receiver: Our receivers are the action classes in use cases. Originally entities interacted with Commands directly, but our TA recommended that these be changed to act as more of a wrapper class, and delegate the use case to an attribute class. So we changed it to exactly this.

## Use of GitHub Features
We used github features such as issues whenever we had to communicate various issues arising in the development process, such as to discuss proposals of potential features and enhancements, letting other group members know when there are problems with the codebase and that we need to fix it, and when there are certain things missing in the codebase. To make using the issues feature easier, we used labels such as “enhancement”, for potential features in the future, and  “help wanted” for whenever help was wanted in solving a bug or implementing a feature. We also used a github action to label stale issues which had no activity on them.

We used the github Actions to create several actions workflows, such as an automatic pull request labeler, and stale issues and pull request labeler. Every time a pull request is made, it automatically labels the pull request on github with a “src” label to show that the changes were made to the source directory of the project. The state issues pull request labeler checks to see if there has been little or no activity on those issues or pull requests. The labeler makes it so that there is a check mark or a cross beside each pull request depending on if the labeler successfully worked. We tried to set up continuous integration to build and test with the ant build system, but the setup process was too difficult.

Prior to phase 2 changes, we had not fully utilized the benefits of making and reviewing pull requests. So in phase 2, we utilized reviews and issues more. You can see that we have issues that were opened then closed with pull requests (after being approved), and you can see that we formally reviewed each other's work, pointing out issues and collaboratively criticising them to be improved, and then merged. This yielded a more fluid GitHub workflow environment for our group, and resulted in less bugs arising.

## Code Style and Documentation
Our code has no warnings when you open it in IntelliJ. Furthermore we tried to adhere to best practices when coding our program. For instance, instance variables have the most limited access modifiers possible and type headings are as general as possible.  For example when an instance variable intends to use an arraylist we specify its type heading as the general List interface. Methods are kept simple and only perform one tasks and magic numbers are kept to a minimum.

Our program is also documented extensively, methods and classes both have headers. Method headers describe what the method does and describe all possible outputs, for

instance in the following comment

![](/phase1/MdRes/MD%20res/pic1.png)

One thing to note is that we used a mix of regular comments and javadocs. For comments describing methods with obvious return values and parameters we used regular comments to simply define the functionality, for more complex methods that interact with more of our code we used javadoc to aid in the understanding using the @param, @return and the @see decorators.  For instance:

![](/phase1/MdRes/MD%20res/pic2.png)

And this document that describes a key and complex instance variable.

![](/phase1/MdRes/MD%20res/pic3.png)

We tried not to over document parts of our code that were simple and under document the complex part. As evident from the above picture, even though the comment is only documenting one instance variable since it is very complex and plays a large part in our command system we added an extensive comment describing it. For other more self explanatory properties like an Id we simply add a line specifying its role.

Note many classes have empty constructors, this was done because it is necessary for Gson. Gson creates a blank instance of a class and fills in the instance variables using reflection, so hence needs an empty constructor for everything you are deserializing.

## Testing
We have unit tests for many methods in our program, over 130 spanning 35 test files and all of them pass. Our code coverage is: 90% class coverage, 59% line coverage. One thing we’d like to note is that our tests are extensive for each method and each test is not testing the bare minimum to get our code coverage number up. The tests we have for our program test all possible returns and test behaviour too, for example in our chopTest:
![](/phase1/MdRes/MD%20res/pic4.png)
![](/phase1/MdRes/MD%20res/pic5.png)

Notice that our test checks all possible return statements and for return statements that  provide variable output depending on the input object we create a sample object and trace the correct output through its respective class to test the method.

We tried to test every method we could although some we couldn’t find a way to, which we indicated in comments throughout the tests. In specific whenever a scanner was involved asking for further user input when a method we were unable to simulate user input within our testing. In such cases we just manually run our program extensively to try those specific returns. Note that this is the reason why the line coverage ins't higher since a lot of the denser classes require user input from a scanner and can't be tested, as mentioned earlier though we tested extensivly by running the program 

Since we did a good job of this in Phase 1, we tried to maintain his coverage by having each new added interactable have corresponding tests added. Unfortunately, not all group members adhered to this and hence our coverage was reduced. See individual PRs regarding interactables to notice this.

## Refactoring
One of the main examples, which is very encompassing,  of refactoring our code can be seen in pull request #3 - restructured code. This pull request was a full redesign of our entire project from the ground up after phase 0  as we realized that our phase 0 code, 1) violated principles of clean architecture and 2) was not scalable due to the way that core systems were designed, i.e the encounter system, and the command system. This pull request had us rethink the entire system and plan out how exactly we would code our entire project, some additions are as follows:

We redid our command system from phase0 from what it was before to 1) take in a variable number of arguments and 2) follow the command design pattern. Previously we also had to have commands take in a fixed type of variable, after our redesign they could take varying types and define behaviour based on their type.

Objects in the game now all extend Interactable, with important shared functionality, most notably a properties map. Previously there was no way to pool or store game objects in an encounter

Objects (now called interactables) now have a properties map that store variables needed for commands. This is necessary because the properties needed depend on the qualities an object has.  Since an object can have multiple qualities these qualities cannot be classes but must be interfaces and interfaces cannot enforce instance variables. Previously we just hard coded the instance variables depending on the object's qualities. Obviously this is tedious and provides no way to access the object's variables later. Thus we created the properties map as a way to access the variables and also created a system of interfaces to enforce the addition of relevant variables into this property map.

To ensure that these variables used standardized names we added an enum to contain all possible property names.

Another example of refactoring can be seen in PR 24. Here devan redid one of the commands. Previously it had been implemented as a special command - which is a command whose unique properties do not allow it to be implemented with our normal command system. This is reserved for commands that are not related to altering the game objects like a quit command. Naturally we want the number of special commands to be a minimum.

Previously we had consume implemented as a special command but here we redid it.

Other refactoring can be seen in PR 41, which has numerous changes listed in the commit, most notably however optimizing the encounter class to remove redundant features.

Some new refactoring we did in this phase is mainly represented in our upgrades of the Command Design Pattern usage in our code. An example is extracting the entity modification from commands and delegating them in use cases to support a cleaner architecture and allow the commands to act more as a wrapper. We also repacked our whole code base to a more logical structure.

We also used method extraction to move some methods to new classes to follow the single responsibility principle, as explained under the SOLID principles section.

## Code Organization
In Phase 2 we improved on our code base’s organization. This can be seen in the new package structure. We still adhered to the ‘By-Layer’ packaging strategy, but added subdirectories for easy readability, and more logical categorization. An example is how we organized our test suite to segregate entity tests and command tests. Likewise, we packaged our entities separately based on their general purpose, such as food, characters, or weapons.

In coordination with this, we updated our package layers to match those of clean architectures, reflective of the clean architecture fixes we implemented to resolve the issues pointed out by our TA. We hope it is more readable.

## Functionality
In the specifications file

## Design Decisions Summary
1. Make all objects that can be interacted with, Interactable

2. Have commands take in the string arguments as parameters rather than the player and encounter

3. Separate special commands from conventional ones since they operate differently

4. Create an automatic encounter generation system.

The major design decisions of this phase are:
1. Saving and loading state through JSON and Gson library
2. Implementing and satisfying the Command Design Pattern properly
3. Improving on SOLID by fixing the Single Responsibility Rule
4. Improving on some Clean Architecture by removing some cross layer violations.

