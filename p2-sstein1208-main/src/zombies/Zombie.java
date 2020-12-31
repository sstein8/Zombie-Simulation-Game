package zombies;
import java.awt.Color;

public class Zombie extends Creature 
{
   public static final int PERCENT = 20; //20 percent chance of changing direction

   public Zombie(int x, int y) {
      this.x = x;
      this.y = y;

      changePercent = PERCENT;
      direction = chooseNewDirection();

      id = ID.Zombie;
      color = Color.RED;
   }
}
