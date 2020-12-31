**ID**

The ID class is used to classify types of creatures.  There can be either humans or zombies, so this class is an enumeration which declares those two types.

**Direction**

The Direction class is similar to the ID class in that it is also an enum.  It creates four different directions that humans and zombies can move in (North, South, East, West).

**Human**

The Human class gives traits to humans in the simulation.  Since the humans have a 10% chance of randomly changing direction, they have a static final int "Percent" which is 10.  This percentage will be used throughout the code to determine if they turn or not.  Humans have their own set of x and y coordinates, ID(human), color(white), chance of changing direction(10%), and current direction.

**Zombie**

The Zombie class gives traits to zombies in the simulation.  Zombies have a 20% chance of randomly changing direction, so they are given a static final int of 20.  Like humans, zombies have a percent of changing direction (20%), a current direction, an ID(zombie), and a color(red).

**Creature**

The Creature class is an abstract class which contains qualities that both humans and zombies share.  Both types of creatures have a percentage that they will change direction, a current direction, an ID, and a color.  This class contains getters that return the x coordinate, y coordinate, ID, and color.  The method chooseNewDirection associates integers 0 - 3 with one of four directions.  Since the direction generated for a human or zombie is supposed to be random, this method uses the nextInt function of the helper class to generate random numbers 0 - 3.  If the number generated is a 0, the direction is North.  If the number is a 1, the direction is West.  If the number is 2, the direction is East.  If the number is 3, the direction is South.  

The turnAround method gives zombies and humans the ability to turn around 180 degrees.  This is useful for when a human sees a zombie approaching, or vice versa.  If the current direction is one way, it makes the direction the one that faces opposite.  For example, if a human is facing North and they need to turn around, they will then face South. 
Similar instructions are given for the turnRight and turnLeft methods.  Based off of the current direction, the new direction will be a 90 degree turn away.  For example, if a human is facing North, a turnRight method would make them now face East.

The move method gives humans and zombies the ability to move around the city.  This method takes in as parameters, width, height, walls, and changeDirection.  First, the integer whichWayToTurn is given wither the value of 0 or 1 from the helper class because if a creature is moving forward, they can only turn two directions.  To decide whether or not the creature turns, if boolean changeDirection is true, and a number less than the changePercent number is generated, then the creature can change directions.  This will work for both humans and zombies, just for humans, the random int has to be less than 10, and for zombies, the random int has to be less than 20.  Now, to actually implement the changing of directions, we acually have to change the position of the creature on the screen.  I create integers nextX and nextY that represent the next potition on the screen after changing directions.  For example, there is a for loop that goes from 0 to 4 (not including 4), representing the 4 directions.  If the direction is North, to actually move North, the y coordinate is decreased by 1 (this actually moves the dot up by one on the screen).  If the direction is West, the x coordinate is decreased by 1, and so on for the other directions.  To prevent the creature from moving out of bounds, if the next x coordinate is greater or equal to 0 and less than the width of the grid and the next y coordinate is greater than or equal to 0 and less than the height of the grid, then it is a valid position.  Then it checks for walls in the next coordinate, and if there are no valls there, then the new positon can be taken by the creature.  Now that the creature has the go ahead to move, it takes the number 0 or 1 from earlier and lets it make the turn left or right,

The method iCanSee returns true if the specified creature is within 10 squares of the other type of creature.  I use a switch to do this.  The parameter to the switch is the current direction that the creature faces.  Whichever direction it matches, it will perform what is in the if statement.  For example, if the creature, lets say a zombie, is facing North, if the x coordinate of a human is the same as it and the y coordinate of the human is within 10 squares of it, then it returns true, and it can see it.  It is similar for the other directions.

The nextTo method also takes in the other creature as an argument.  If the x coordinate of the other creature is equal to the x coordinate of the creature and the y coordinate is within one away from the creature, it is next to it.  The same happens if the y coordinates are equal and the x coordiantes are within one.  This accounts for being tangent on the x axis and being tangent on the y axis.

**City**

The City class does several things.  It creates the actual environment of the simulation, including the unique wall pattern.  It also populates the city with humans and zombies.  The setWorld method is used to create a completely new, unique world with walls and creatures.

Since there can be either humans or zombies in the city, I decided to have both of them fall under the category of a creature.  I have created a list of creatures, where a creature can be either a human or a zombie.  The populate method takes numPeople as a parameter and uses that to create that amound of humans in the simulation.  The helper class is used to generate random coordinates for the humans to appear in.  The add method of the list feature allows humans to be added at these randomly generated coordinates.  This method also adds a zombie to the city to begin with.

The update method is what does the bulk of the work in the City class.  If the resetTriggered is true (spacebar pressed), a new world is set.  If not, it makes updates to the existing world, including movement, adding zombies, turning humans into zombies, and so on.  If the mouse is clicked and triggeredZombie is not null, a zombie is added to the screen.  This method moves through the list of creatures, and if any of them are humans, it makes a new human and lets them move.  It does the same for zombies.  It if humans are nexi to zombies, it gets rid of the human and adds a zombie in it's place.  If move is true (humans and zombies can move), humans check to see if they can see a zombie, and if they can, they will move two spaces, and then continue their normal moving pattern.  If a zombie can see a human while moving, it moves towards the human and doesn't change direction.

**KeyInput**

The KeyInput class checks to see if the spacebar is pressed, and if it is, a new world is set up.  The boolean value, spaceKeyPressed can be true or false.  The method keyPressed takes in an event e from the keyboard and if the key is the space bar pressed, the world is reset.  When the pacebar is released, the kay is no longer marked as pressed.  This class extends KeyAdapter class.  For my unique feature, I added an else if statement so that if the enter key is pressed, the game exits.  This involves a similar process to the space bar input.  There are specific codes for the different keys on the keyboard, so I just used the code for the enter key instead here.

**MouseInput**

The MouseInput class checks if the mouse is clicked on the screen.  If it is, a new zombies is placed at the coordinates of where the mouse was clicked.  It takes the current world and dot size and sets that up in the constructor.  It then takes in an event e, which is the mouse click.  Once the mouse is released, then the new zombie can be added.

**Helper**

I made an update to the Helper class.  I added a boolean method inRange that takes in a number, a minimum number, and a maximum number and returns true if the number is in the range of the min and the max.  This is used in the iCanSee method to tell if the humans are within 10 squares of the zombies.

**ZombieSim**

I have made updates to the ZombieSim class to add a KeyListener and a MouseListener.


