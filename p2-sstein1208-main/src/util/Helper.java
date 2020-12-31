package util;

import util.DotPanel;
import java.util.Random; 

public class Helper {

	private static Random rand = null;


	/**
	 * Use the singleton design pattern to store
	 * the random number generator. You'll learn
	 * more about this later.
	 * @return
	 */
	private static Random getRand() {
		if(rand == null) {
			rand = new Random();
		}
		return rand;
	}
	/**
	 * Set the seed used by the random number generator.
	 * @param seed
	 */
	public static void setSeed(long seed) {
		rand = new Random(seed);
	}
	/**
	 * Get a random integer r, such that 0<= r < max.
	 */
	public static int nextInt(int max) {
		return getRand().nextInt(max);
	}
	/**
	 * Return a random double between 0 (inclusive) and 1 (exclusive).
	 */
	public static double nextDouble() {
		return getRand().nextDouble();
	}

  public static boolean inRange(int num, int min, int max) 
  {
    if(min > max) 
    {
      int temp = min;
      min = max;
      max = temp;
    }
    return num >= min && num <= max;
  }

}
