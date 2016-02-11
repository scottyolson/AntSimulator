package betterAntSimulator;

public class Nest 
{
	private int x; //location of nest on map
	private int y;
	private int foodStored; //amount of food stored in the nest
	Ant[] theAnts = new Ant[Parameters.NUM_OF_ANTS]; //the array of this nests ants
	
	//default constructor places nest in the middle of the board.
	public Nest()
	{
		this(Parameters.BOARD_SIZE/2, Parameters.BOARD_SIZE/2);
	}
	
	//coordinates can be put in for the starting location of the nest
	public Nest(int startX, int startY)
	{
		x = startX;
		y = startY;
		for(int i=0; i<Parameters.NUM_OF_ANTS; i++)
		{
			theAnts[i] = new Ant(x, y); //ants are created, starting position is on nest.
		}
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void incrementFood()
	{
		foodStored++;
		System.out.println("Food Supply: " + foodStored); //print update
	}
	
	public void decrementFood()
	{
		foodStored--;
		System.out.println("Food Supply: " + foodStored); //print update
	}
	
	public int getFoodStored() {
		return foodStored;
	}

	public void createNewAnt()
	{
		for(int i=0; i<Parameters.NUM_OF_ANTS; i++) 
		{
			if(!theAnts[i].isAlive()) //check if ant[i] is dead
			{
				theAnts[i] = new Ant(x, y);
				foodStored-= Parameters.FOOD_TO_SPAWN_NEW_ANT;
				System.out.println("Food Supply: " + foodStored + ", New Ant Created"); //print update
				return;
			}
		}
	}
	
	public boolean checkForDeadAnts()
	{
		for(int i=0; i<Parameters.NUM_OF_ANTS; i++)
		{
			if(!theAnts[i].isAlive()) //check if ant[i] is dead
			{
				return true;
			}
		}
		return false;
	}
}
