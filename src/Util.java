

import java.util.Random;

public final class Util
{
	private static Random rng = new Random(System.currentTimeMillis());	//this class's internal rng

	//+setSeed(seed:long)
	public static void setSeed(long seed)
	{
		rng.setSeed(seed);
	}//end setSeed(long)

	//+randomFloat(min:float,max:float)
	public static float randomFloat(float min, float max)
	{
		if(min > max)
		{
			float temp = max;
			max = min;
			min = temp;
		}
		return rng.nextFloat() * (max - min) + min;
	}//end randomFloat(float,float)

	//+randomInt(min:int,max:int):int
	public static int randomInt(int min, int max)
	{
		if(min > max)
		{
			int temp = max;
			max = min;
			min = temp;
		}
		//TODO: find a way to remove the Math.max here
		return rng.nextInt(Math.max(1,max - min)) + min; 
	}//end randomInt(int,int)

	//+randomBool():boolean
	public static boolean randomBool()
	{
		return rng.nextBoolean();
	}//end randomBool()
}
