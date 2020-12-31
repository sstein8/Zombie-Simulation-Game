package zombies;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Pressing the space bar should cause the zombie simulator to reset (regenerate buildings and humans, and add one zombie).

public class KeyInput extends KeyAdapter 
{
     
   private boolean spaceKeyPressed; //is the space key pressed?
   private boolean enterKeyPressed;
   private City world;

   public KeyInput(City world) 
   {
      this.world = world;
   }

   public void keyPressed(KeyEvent e) 
   {
      int key = e.getKeyCode();

      if(key == KeyEvent.VK_SPACE) 
      {
	    // only set once if space key is being held down
        if(!spaceKeyPressed) 
        {
          world.triggeredReset(); //reset world
          spaceKeyPressed = true;
        }
      }
      else if(key == KeyEvent.VK_ENTER) 
      {
	    // if enter key is pressed
        if(!enterKeyPressed) 
        {
          System.exit(0); //exits game

          enterKeyPressed = true;
        }
      }
   }

   public void keyReleased(KeyEvent e) 
   {
      int key = e.getKeyCode();
      if(key == KeyEvent.VK_SPACE)
      {
	      spaceKeyPressed = false;
      }
      else if(key == KeyEvent.VK_ENTER)
      {
	      enterKeyPressed = false;
      }

   }
   
}
