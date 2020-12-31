package zombies;
import java.awt.Color;
import util.Helper;

public abstract class Creature
{
  protected int x; //xpos on screen
  protected int y; //ypos on screen

  protected int changePercent; //can be 0 - 3
  protected Direction direction; //N, W, S, E

  protected ID id; //id of type ID (Human or Zombie)
  protected Color color; // color of type Color

  //getters
  public int getX()
  {
    return x;
  }
  public int getY()
  {
    return y;
  }
  public ID getID()
  {
    return id;
  }
  public Color getColor()
  {
    return color;
  }
  
  public Direction chooseNewDirection() //associates a number with a direction
  {
    int rand = Helper.nextInt(4); //generate number 0 - 3
    if(rand == 0)
    {
      return Direction.North;
    }
    if(rand == 1)
    {
      return Direction.West;
    }
    if(rand == 2)
    {
      return Direction.East;
    }
    else // 3
    {
      return Direction.South;
    }
  }

  public void turnAround() // 180 degree turn around
  {
    if(direction == Direction.North)
    {
      direction = Direction.South;
    }
    if(direction == Direction.West)
    {
      direction = Direction.East;
    }
    if(direction == Direction.East)
    {
      direction = Direction.West;
    }
    if(direction == Direction.South)
    {
      direction = Direction.North;
    }
  }

  public void turnRight() //turn right 90 degrees
  {
    if(direction == Direction.North)
    {
      direction = Direction.East;
    }
    if(direction == Direction.West)
    {
      direction = Direction.North;
    }
    if(direction == Direction.East)
    {
      direction = Direction.South;
    }
    if(direction == Direction.South)
    {
      direction = Direction.West;
    }
  }

  public void turnLeft() //turn left 90 degrees
  {
    if(direction == Direction.North)
    {
      direction = Direction.West;
    }
    if(direction == Direction.West)
    {
      direction = Direction.South;
    }
    if(direction == Direction.East)
    {
      direction = Direction.North;
    }
    if(direction == Direction.South)
    {
      direction = Direction.East;
    }
  }

  public void move(int width, int height, boolean[][] walls, boolean changeDirection) 
  {
    int whichWayToTurn = Helper.nextInt(2);  //can turn 2 ways

    if(changeDirection && Helper.nextInt(100) < changePercent) //10% of the time, it chooses a new direction
    {
      direction = chooseNewDirection();
    }
    for(int i = 0; i < 4; i++) //can turn 4 ways
    {
      //current x and y position on screen
      int nextX = x;
      int nextY = y;
      //these set the next coordinate up for movement
      if(direction == Direction.North)
      {
        nextY--; //move up
      }
      else if(direction == Direction.West)
      {
        nextX--; // move left
      }
      else if(direction == Direction.East)
      {
        nextX++; // move right
      }
      else if(direction == Direction.South)
      {
        nextY++; // move down
      }

      if(nextX >= 0 && nextX < width && nextY >= 0 && nextY < height)//if the creature is within the boundaries of the grid
      {
        //then it can change its coordinates
        if(!walls[nextX][nextY])// no walls
        {
          x = nextX;
          y = nextY;
          break;
        }
      }
      //procedure for either a left or right turn
      if(whichWayToTurn == 0) 
      {
        turnRight(); //  0 is right
      }
      else // if it = 1
      {
        turnLeft(); // 1 is left
      }

    }
    
  }

  public boolean iCanSee(Creature other) //if zombie, other is human, if human, other is zombie
  {
    switch(direction) //parameter is the current direction
    { //direction compared with the cases listed (N, E, S, W)
      // if there is a match, then do the if statement
      case North: 
        if(x == other.getX() && Helper.inRange(other.getY(), y - 10, y - 1)) //if the x coord of one creature is the same as the x coord as the other and the other creature's y coord is within 10 squares away, return true
        return true;
      case West:  
        if(y == other.getY() && Helper.inRange(other.getX(), x - 10, x - 1)) 
        return true;
      case East:  
        if(y == other.getY() && Helper.inRange(other.getX(), x + 1, x + 10)) 
        return true;
      case South: 
        if(x == other.getX() && Helper.inRange(other.getY(), y + 1, y + 10)) 
        return true;
    }
    return false;
  }

  

  public boolean nextTo(Creature other)
  {
    if(x == other.getX() && Helper.inRange(other.getY(), y-1, y+1))
    {
      return true;
    }
    if(y == other.getY() && Helper.inRange(other.getX(), x-1, x+1))
    {
      return true;
    }
    return false;
    
  }

}