package zombies;
import java.awt.Color;

public class Human extends Creature
{
  public static final int PERCENT = 10; //10 percent chance of changing direction

  public Human(int x, int y)
  {
    this.x = x; //x that belongs to human
    this.y = y; //y that belongs to human

    changePercent = PERCENT; //the percent that it will change directions
    direction = chooseNewDirection(); //choose the direction to move

    id = ID.Human; //it is a human, not a zombie
    color = Color.WHITE; //color of a human is white
  }
}