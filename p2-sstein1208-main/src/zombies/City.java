package zombies;

import util.Helper;

import java.awt.Color;
import java.util.List;
import java.util.LinkedList;


public class City {

  /** walls is a 2D array with an entry for each space in the city.
   *  If walls[x][y] is true, that means there is a wall in the space.
   *  else the space is free. Humans should never go into spaces that
   *  have a wall.
   */
  private boolean walls[][];
  private int width, height, numBuildings, numPeople;

  private List<Creature> creatures; //list of creatures in city (either human or zombie)

  //input triggered events (defaul = false/null)
  private boolean resetTriggered;
  private Zombie triggeredZombie;

  /**
   * Create a new City and fill it with buildings and people.
   * @param w width of city
   * @param h height of city
   * @param numB number of buildings
   * @param numP number of people
   */
  public City(int w, int h, int numB, int numP) {
    width = w;
    height = h;
    numBuildings = numB;
    numPeople = numP;
    
    setWorld();
    
  }

  private void setWorld() //calling this creates a world for the sim
  {
    walls = new boolean[width][height];
    creatures = new LinkedList<Creature>();
    randomBuildings(numBuildings);
    populate(numPeople);

  }

  public void triggeredReset() //reset sim
  {
    resetTriggered = true;
  }

  //make new zombie appear at a location specified
  public void triggeredNewZombie(int x, int y)
  {
    triggeredZombie = new Zombie(x, y);
  }
  /**
   * Generates numPeople random people distributed throughout the city.
   * People must not be placed inside walls!
   *
   * @param numPeople the number of people to generate
   */
  private void populate(int numPeople)
  {
    // Generate numPeople new humans randomly placed around the city.
    int count = 0;
    while(count < numPeople) //adds numPeople humans
    {
      //generate random numbers for x and y coordinates
      int x = Helper.nextInt(width);
      int y = Helper.nextInt(height);

      if(!walls[x][y])// if no walls at that coordinate
      {
        creatures.add(new Human(x, y));//add a human
        count++;
      }
    }

    count = 0; //reset count to 0
    while(count < 1) //always true
    {
      int x = Helper.nextInt(width);
      int y = Helper.nextInt(height);
      if(!walls[x][y])
      {
        creatures.add(new Zombie(x, y)); //add zombie to city
        count++; //makes sure only one zombie is added to begin with
      }
    }

  }


  /**
   * Generates a random set of numB buildings.
   *
   * @param numB the number of buildings to generate
   */
  private void randomBuildings(int numB) {
    /* Create buildings of a reasonable size for this map */
    int bldgMaxSize = width/6;
    int bldgMinSize = width/50;

    /* Produce a bunch of random rectangles and fill in the walls array */
    for(int i=0; i < numB; i++) {
      int tx, ty, tw, th;
      tx = Helper.nextInt(width);
      ty = Helper.nextInt(height);
      tw = Helper.nextInt(bldgMaxSize) + bldgMinSize;
      th = Helper.nextInt(bldgMaxSize) + bldgMinSize;

      for(int r = ty; r < ty + th; r++) {
        if(r >= height)
          continue;
        for(int c = tx; c < tx + tw; c++) {
          if(c >= width)
            break;
          walls[c][r] = true;
        }
      }
    }
  }

  /**
   * Updates the state of the city for a time step.
   */

  
  public void update() 
  {
    
    // Move humans, zombies, etc
    if(resetTriggered) //reset world
    {
	    setWorld();
	    resetTriggered = false;
    } 
    else //not resetted
    {
	    if(triggeredZombie != null) //if a zombie is triggered
      {
	      if(!walls[triggeredZombie.getX()][triggeredZombie.getY()]) //if there are no walls at the location desired   
	        creatures.add(triggeredZombie);  //add a triggered zombie
	        triggeredZombie = null;         //reset to null (regular state)
	    }

	    for(int h = 0; h < creatures.size(); h++) //move thru list of creatures
      {
	      Creature creature1 = creatures.get(h);  //gets each creature in the list

	      if(creature1.getID() == ID.Human)   //if a creature is a Human
        {
	        Human human = (Human)creature1; //make a new human
	        boolean move = true;    //give human trait to move
	    
	        for(int z = 0; z < creatures.size(); z++) //move thru list of creatures
          {
		        Creature creature2 = creatures.get(z);  //get each creature in the list again

		        if(creature2.getID() == ID.Zombie)   //if the creature is a zombie
            {
		          Zombie zombie = (Zombie)creature2;  //make a new zombie

		          if(human.nextTo(zombie))    //if a human is next to a zombie
              {
			          Human gone = (Human)creatures.remove(h);  //the human is removed (zombie eats it)
			          creatures.add(h, new Zombie(gone.getX(), gone.getY())); //add a zombie in it's place

			          move = false;
			          break;

		          }
		        }
	        }
	    
	        if(move) 
          {
		        for(int z = 0; z < creatures.size(); z++)   //move thru creatures list
            {
		          Creature creature2 = creatures.get(z);  //get the creatures from the list
		  
		          if(creature2.getID() == ID.Zombie)  //if the creature is a zombie
              {
			          Zombie zombie = (Zombie)creature2;  //make a new zombie
		     
			          if(human.iCanSee(zombie))   //if a human sees a zombie
                {
			            human.turnAround();       //it will turn the opposite direction
			            human.move(width, height, walls, false);  //move 2 spaces, don't change direction
			            human.move(width, height, walls, false);

			            move = false;
			            break;
			          }
		          }
		        }
	        }
	    
	        if(move)
          {
		        human.move(width, height, walls, true); //move normally, change direction
          }
	      }
	    }

	    for(int z = 0; z < creatures.size(); z++)   //move thru creatures list
      {
	      Creature creature1 = creatures.get(z);  //get each creature in the list

	      if(creature1.getID() == ID.Zombie)    //if the creature is a zombie
        {
	        Zombie zombie = (Zombie)creature1;    //make a zombie
	        boolean move = true;                   //let it move
	    
	        for(int h = 0; h < creatures.size(); h++)   //move thru list of creatures
          {
		        Creature creature2 = creatures.get(h);    //get each creature in the list

		        if(creature2.getID() == ID.Human)        //if the creature is a human
            {
		          Human human = (Human)creature2;   //make a human
		  
		          if(zombie.iCanSee(human))    //if the zombie can see the human
              {
			          zombie.move(width, height, walls, false); //move the zombie toward the human, don't change direction
		     
			          move = false;
			          break;
		          }
		        }
	        }
	    
	        if(move)
          {
		        zombie.move(width, height, walls, true); //move and change direction
          }
	      }
	    }  
    }
  }


  /**
   * Draw the buildings and all humans.
   */
  public void draw(){
    /* Clear the screen */
    ZombieSim.dp.clear(Color.black);
    drawWalls(); //first draw walls
    drawCreatures(); //then draw creatures to screen
  }

  /**
   * Draw the buildings.
   * First set the color for drawing, then draw a dot at each space
   * where there is a wall.
   */
  private void drawWalls() {
    ZombieSim.dp.setPenColor(Color.DARK_GRAY);
    for(int r = 0; r < height; r++)
    {
      for(int c = 0; c < width; c++)
      {
        if(walls[c][r])
        {
          ZombieSim.dp.drawDot(c, r);
        }
      }
    }
  }

  public void drawCreatures() //goes thru creatures list, sets color, draws creature
  {
    for(int i = 0; i < creatures.size(); i++)
    {
      Creature creature = creatures.get(i); //get each creature in the list
      ZombieSim.dp.setPenColor(creature.getColor()); //choose color of creature
      ZombieSim.dp.drawDot(creature.getX(), creature.getY()); //draw creature to screen
      
    }
  }

}
