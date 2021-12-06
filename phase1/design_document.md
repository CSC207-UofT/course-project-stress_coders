# Design Document
## SOLID Principles
The Open/Closed Principle is adhered very well in our design. All our classes that should be expanded can be done without modifying the original class. For instance, the Interactable class is very generalized such that many subclasses are able to extend it, with more specifications in them and with no need to change an already existing class such as Interactable. In fact, almost all our entities are descendants of Interactable. As well this allows many other classes to be closed for modification, as the parameters don’t need to be changed every time a different entity is used as they are all type Interactable, so methods can simply take that as a parameter.

The Liskov Substitution principle is also followed closely in our project. As mentioned above this can be seen in the entities, as interactable was made very generally such as its descendants have extra methods and extra members, they do not remove or modify any of the behaviours of Interactable. The same goes with Command, it was implemented very generally, with only one member and an execute() method that all command subclasses need and the parameters work for all the command subclasses as well, which allows each command subclass to have the properties from command plus extra individual properties.

We believe the Interface Segregation principle was also done very well. All of our interfaces have 1-2 methods only. This allows classes to implement exactly what they need and nothing more. For example, an Enemy or Animal only has to implement how they handle getting hit, meanwhile a weapon doesn’t have to handle getting hit, which wouldn’t make sense, but does have to have a hit probability and damage value.

We also believe we have good use of dependency inversion principle because our higher level classes depend on interfaces not on their lower level classes. For instance, the command subclasses check what kind of interface they received instead of checking what entity they received.

The one principle we believe we could improve is the Single Responsibility Principle. All of our entities do follow this principle, for instance Enemy class is only in charge of Enemy reaction to getting hit, handling hit and responding.  As well, in our interface adapters, we have 2 classes BuilderSetup and GameState, one is responsible for generating and building encounters while the other is responsible for loading, requesting, and returning different encounters. However, for example in Encounter and GameState classes there are getHelp() methods, that while use the same members have a different responsibility,  they job to inform about the current wallet, weapon, etc. of player, while the rest of the class is responsible for creating aspects of an encounter, or using the encounters, respectively. As a solution, perhaps we can make the members protected and create subclasses EncounterHelp and GameStateHelp such that they will have access to the same attributes but now each class has a single responsibility.

## Clean Architecture
The program is for the most part consistent with clean architecture, aside from one violation, which we are not sure how to/ whether it needs to be  fixed.

The dependency rule is followed in terms of flow from outer to inner, as in no inner layer uses an outer layer. For example, the CommandLine class in frameworks receives commands from a user and produces an output, it uses GameState in interface adapters, which uses Encounter in use cases among other use cases, which uses Character and other interactables in entities. The Encounter loads, requests, displays the interactables, etc. The GameState requests, loads, gets current  Encounter, etc.  Finally, the CommandLine  class uses GameState to load, request encounters, etc. to allow the user to interact with the encounter, getting outputs about the the steps in the encounter and writing input which the CommandLine accepts, then uses game state, which uses encounter, which can use interactables, to react to inputs accordingly.

Unfortunately, there are some clean architecture violations, which we are not sure how to fix. Some classes in frameworks and interface adapters import and use entities, which is a violation of the adjacent layer aspect of the dependency rule. However, we only use those entities to cast and type check. Because our game has an open world aspect where the user can call on any interactable, we have to be able to type check those interactables before running commands from the player. So, we are not sure how to get around that. As well, despite this jump in layers, changes in the outer layers will not impact the interactables.
We also use a use case in frameworkers, however we can fix that in the future by simply having methods in interface adapter classes that use the use case and then calling those methods in frameworks.


#### Scenario Walkthrough: Side Interaction - Animal - Shoot:

The Scenario starts with the Main class (framework) that has the main method. The user sees a welcome message which is printed out in the main method. Then start() from CommandLine class (framework) is called.  

The start() method prints out a sentence that asks for user’s name.  The user then types their name, which the Scanner reads. Then it uses GameState (interface adapter) which uses PlayerManager (use case) to set the name of the player.

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
In our project, we implemented the command design pattern. There is the Command class and the classes that extend it such as, Throw and Spin. They hold the receiver objects, the Interactable descendants (which are explained next), and an execute() method which calls on a method from the receiver, for example, handleHit(), spin(), etc. Next, there is the Interactable descendants which are the Receiver classes. They hold the methods that handle the events when execute() is called, such as handeHit(), spin(), userAttempt(), etc. Then, there is the Command Line Class which is the Sender Class. It initiates the request by setting the command that was received and has a method that calls on execute() from Command subclasses. Lastly, there is the Main class, which is the Client class. Main has the main method. It is the controller of the process. It creates a Command Line object, and calls on Command Line’s start() method, which calls on Command Line’s methods that set the commands and call on methods that call on execute().

## Use of GitHub Features
We used github features such as issues whenever we had to communicate various issues arising in the development process, such as to discuss proposals of potential features and enhancements, letting other group members know when there are problems with the codebase and that we need to fix it, and when there are certain things missing in the codebase. To make using the issues feature easier, we used labels such as “enhancement”, for potential features in the future, and  “help wanted” for whenever help was wanted in solving a bug or implementing a feature. We also used a github action to label stale issues which had no activity on them.

We used the github Actions to create several actions workflows, such as an automatic pull request labeler, and stale issues and pull request labeler. Every time a pull request is made, it automatically labels the pull request on github with a “src” label to show that the changes were made to the source directory of the project. The state issues pull request labeler checks to see if there has been little or no activity on those issues or pull requests. The labeler makes it so that there is a check mark or a cross beside each pull request depending on if the labeler successfully worked. We tried to set up continuous integration to build and test with the ant build system, but the setup process was too difficult.

We used pull requests extensively by forcing each group member to make their own branch with the specific part of the codebase they were working on, then make a pull request with that branch, then we merge the pull request into main after making sure that nothing will break afterwards, and to review the pull request code so that fewer bugs are introduced.  

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

## Testing
We have unit tests for many methods in our program, over 130 spanning 35 test files and all of them pass. Our code coverage is: 90% class coverage, 59% line coverage. One thing we’d like to note is that our tests are extensive for each method and each test is not testing the bare minimum to get our code coverage number up. The tests we have for our program test all possible returns and test behaviour too, for example in our chopTest:
![](/phase1/MdRes/MD%20res/pic4.png)
![](/phase1/MdRes/MD%20res/pic5.png)

Notice that our test checks all possible return statements and for return statements that  provide variable output depending on the input object we create a sample object and trace the correct output through its respective class to test the method.

We tried to test every method we could although some we couldn’t find a way to, which we indicated in comments throughout the tests. In specific whenever a scanner was involved asking for further user input when a method we were unable to simulate user input within our testing. In such cases we just manually run our program extensively to try those specific returns. Note that this is the reason why the line coverage ins't higher since a lot of the denser classes require user input from a scanner and can't be tested, as mentioned earlier though we tested extensivly by running the program 

## Refactoring
One of the main examples, which is very encompassing,  of refactoring our code can be seen in pull request #3 - restructured code. This pull request was a full redesign of our entire project from the ground up after phase 0  as we realized that our phase 0 code, 1) violated principles of clean architecture and 2) was not scalable due to the way that core systems were designed, i.e the encounter system, and the command system. This pull request had us rethink the entire system and plan out how exactly we would code our entire project, some additions are as follows:

We redid our command system from phase0 from what it was before to 1) take in a variable number of arguments and 2) follow the command design pattern. Previously we also had to have commands take in a fixed type of variable, after our redesign they could take varying types and define behaviour based on their type.

Objects in the game now all extend Interactable, with important shared functionality, most notably a properties map. Previously there was no way to pool or store game objects in an encounter

Objects (now called interactables) now have a properties map that store variables needed for commands. This is necessary because the properties needed depend on the qualities an object has.  Since an object can have multiple qualities these qualities cannot be classes but must be interfaces and interfaces cannot enforce instance variables. Previously we just hard coded the instance variables depending on the object's qualities. Obviously this is tedious and provides no way to access the objects variables later. Thus we created the properties map as a way to access the variables and also created a system of interfaces to enforce the addition of relevant variables into this property map.

To ensure that these variables used standardized names we added an enum to contain all possible property names.

Another example of refactoring can be seen in PR 24. Here devan redid one of the commands. Previously it had been implemented as a special command - which is a command whose unique properties do not allow it to be implemented with our normal command system. This is reserved for commands that are not related to altering the game objects like a quit command. Naturally we want the number of special commands to be a minimum.

Previously we had consumed implemented as a special command but here we redid it.

Other refactoring can be seen in PR 41, which has numerous changes listed in the commit, most notably however optimizing the encounter class to remove redundant features.


## Code Organization
We organized our codebase in terms of its logical layers in its design. We have our entities which are mainly game objects that exist in Encounters. We also put our interfaces here that verify certain qualities about game objects, like if they can be thrown or consumed. Similarly, we have our use cases which are the commands that use these objects. In our frameworks folder we have the classes that interact with our user like Main and CommandLine, along with BuilderSetup as this is a framework that builds encounters. Lastly, we have interface adapters, which is the layer that interfaces between the surface and deep infrastructure of our code. This is why we have GameState here as this virtually stores the game, and processes requests from the CLI and modifies the game in GameState. This was our packaging structure. We did our best to model it from the ‘By Layer’ package design in lecture. We were originally going to use the ‘By Feature’ packaging structure, but realized since many objects in our game are intertwined, there aren't any easy “feature” categories to package our code into. Rather there are not many features of the game that are disjoint enough to make a meaningful packaging structure.

## Functionality
In the specifications file

## Design Decisions Summary
1. Make all objects that can be interacted with, Interactable

2. Have commands take in the string arguments as parameters rather than the player and encounter

3. Separate special commands from conventional ones since they operate differently

4. Create an automatic encounter generation system.

