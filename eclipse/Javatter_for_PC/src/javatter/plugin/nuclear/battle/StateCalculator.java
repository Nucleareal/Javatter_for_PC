package javatter.plugin.nuclear.battle;

import java.util.Random;

public class StateCalculator
{
	public static int getState(Random rand, int power)
	{
		return (int)(Math.pow(rand.nextInt(power), 2D)/100D);
	}
}
