import jcurses.system.CharColor;
import jcurses.system.Toolkit;

public class CellularAutomaton
{
	public static final CharColor alive = new CharColor(CharColor.GREEN, CharColor.BLACK);
	public static final CharColor dead = new CharColor(CharColor.BLACK, CharColor.WHITE);

	public int numOverpop = 4;	//if the number of neighbors exceeds this, die
	public int numUnderpop = 2;	//if this exceeds the number of neighbors, die 
	public int numRepop = 2;	//if the number of neighbors is exactly this, revive

	//the actual board of cells
	private boolean[][] data;

	//creates a board with the given dimensions and gives each cell the given percent chance to start out alive
	public CellularAutomaton(float chanceToStartAlive, int boardWidth, int boardHeight)
	{
		data = new boolean[boardHeight][boardWidth];
		populate(chanceToStartAlive);
	}

	//returns the value of the given cell, and false if it is out of bounds
	public boolean getCell(int x, int y)
	{
		if(y < getHeight()&& y >= 0 && x < getWidth() && x >= 0)
			return data[y][x];
		else
			return false;
	}

	public int getWidth()
	{
		return data[0].length;
	}

	public int getHeight()
	{
		return data.length;
	}

	//gives each cell in the board the given chance to start alive
	public void populate(float chanceToStartAlive)
	{
		for(int y = 0; y < getHeight(); y++)
			for(int x = 0; x < getWidth(); x++)
				data[y][x] = Util.randomFloat(0,1) < chanceToStartAlive;
	}

	//renders the board
	public void paint()
	{
		Toolkit.startPainting();

		for(int y = 0; y < getHeight(); y++)
			for(int x = 0; x < getWidth(); x++)
				if(data[y][x])
					Toolkit.printString("O",x,y,alive);
				else
					Toolkit.printString("X",x,y,dead);

		Toolkit.endPainting();
	}

	//returns the number of cells surrounding the given cell that are alive
	public int getNeighborCount(int x, int y)
	{
		int numNeighbors = 0;

		if(getCell(x-1,y+1)) numNeighbors++;//bottom-left
		if(getCell(x+0,y+1)) numNeighbors++;//bottom-center
		if(getCell(x+1,y+1)) numNeighbors++;//bottom-right
		if(getCell(x-1,y-1)) numNeighbors++;//top-left
		if(getCell(x+0,y-1)) numNeighbors++;//top-center
		if(getCell(x+1,y-1)) numNeighbors++;//top-right
		if(getCell(x-1,y+0)) numNeighbors++;//center-left
		if(getCell(x+1,y+0)) numNeighbors++;//center-right

		return numNeighbors;
	}

	//progress the simulation
	public void update()
	{
		//declare a new board to prevent interference from cells that have already been assigned
		boolean[][] newData = new boolean[getHeight()][getWidth()];

		for(int y = 0; y < getHeight(); y++)
			for(int x = 0; x < getWidth(); x++)
			{
				int numNeighbors = getNeighborCount(x,y);

				//if the cell has too few or too many neighbors, kill it
				if(numNeighbors < numUnderpop || numNeighbors > numOverpop)
					newData[y][x] = false;
				//if the cell has the right number of neighbors, revive it
				else if(numNeighbors == numRepop)
					newData[y][x] = true;
			}

		//assign the new data to the old data
		data = newData;
	}
}
