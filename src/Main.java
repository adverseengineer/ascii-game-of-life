

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import jcurses.system.CharColor;
import jcurses.system.InputChar;
import jcurses.system.Toolkit;

import jcurses.util.Protocol;
import jcurses.util.Rectangle;

@SuppressWarnings("unchecked")
public class Main
{
	public static void main(String[] args) throws InterruptedException
	{
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				Toolkit.shutdown();
				System.out.println("JVM was forcibly terminated");		
			}
		});

		//set up our debugging stream
		System.setProperty("jcurses.protocol.filename", "jcurses.log");

		Toolkit.init();

		CellularAutomaton gol = new CellularAutomaton(0.5f, Toolkit.getScreenWidth(),Toolkit.getScreenHeight());

		while(true)
		{
			gol.paint();
			gol.update();
			Thread.sleep(Integer.parseInt(args[0]));
		}
	}
}
