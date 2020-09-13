import jcurses.system.Toolkit;

@SuppressWarnings("unchecked")
public class Main
{
	static
	{
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				Toolkit.shutdown();
				System.err.println("JVM was forcibly terminated");
			}
		});

		//set up the system property jcurses uses to write debug logs
		System.setProperty("jcurses.protocol.filename", "jcurses.log");
	}

	static long seed;
	static long refreshRate;
	static float chanceToStartAlive;

	public static void main(String[] args) throws Exception
	{
		if(args.length != 3)
			throw new Exception("Malformed arguments given\nCorrect usage is 'Main.jar seed refreshRate chanceToStartAlive'\nWhere refreshRate is a long and chanceToStartAlive is a float");

		seed = Long.parseLong(args[0]);
		refreshRate = Long.parseLong(args[1]);
		chanceToStartAlive = Float.parseFloat(args[2]);

		Toolkit.init();

		Util.setSeed(seed);
		CellularAutomaton gol = new CellularAutomaton(chanceToStartAlive, Toolkit.getScreenWidth(),Toolkit.getScreenHeight());

		while(true)
		{
			gol.paint();
			gol.update();
			Thread.sleep(refreshRate);
		}
	}
}
