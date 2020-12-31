package zombies;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Clicking the mouse on the map should add a zombie at that location.

public class MouseInput extends MouseAdapter 
{
   private boolean mouseClicked; //true or false

   private City world;
   private int dotSize;

   public MouseInput(City world, int dotSize)   
   {
      this.world = world;
      this.dotSize = dotSize;
   }

   public void mousePressed(MouseEvent e) 
   {
      if(!mouseClicked)
	    mouseClicked = true;
   }

   public void mouseReleased(MouseEvent e) 
   {
      if(mouseClicked) //if the mouse was clicked
      {
        world.triggeredNewZombie(e.getX() / dotSize, e.getY() / dotSize); //add new triggered zombie to the world
        mouseClicked = false;
      }
   }
}
