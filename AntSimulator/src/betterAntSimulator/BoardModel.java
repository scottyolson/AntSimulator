package betterAntSimulator;

public class BoardModel 
{
	//holds location of everything on the board
	GroundCell[][] boardLayout = new GroundCell[Parameters.BOARD_SIZE][Parameters.BOARD_SIZE];
	
	Food[] theFood = new Food[Parameters.NUM_OF_FOOD_PILES]; //array of food piles
	Nest nest1; 
	
	//constructor
	public BoardModel()
	{
		for(int i=0; i<Parameters.BOARD_SIZE; i++)
		{
			for( int j=0; j<Parameters.BOARD_SIZE; j++)
			{
				boardLayout[i][j] = new GroundCell();
			}
		}
		
		nest1 = new Nest(); //create nest
		boardLayout[nest1.getX()][nest1.getY()].hasNest = true; //add nest to board
		
		//create the food piles
		for(int i=0; i<Parameters.NUM_OF_FOOD_PILES; i++)
		{
			int tempX = ((int)(Math.random() * (Parameters.BOARD_SIZE - 2)))+1; //some randoms to place food piles.
			int tempY = ((int)(Math.random() * (Parameters.BOARD_SIZE - 2)))+1;
			
			//check to see if location is empty of food and nest
			while(boardLayout[tempX][tempY].hasFood || boardLayout[tempX][tempY].hasNest)
			{
				tempX = ((int)(Math.random() * (Parameters.BOARD_SIZE - 2)))+1;
				tempY = ((int)(Math.random() * (Parameters.BOARD_SIZE - 2)))+1;
			}
			theFood[i] = new Food(tempX, tempY);
			boardLayout[tempX][tempY].hasFood = true;
		}
	}
	
	//moves the ants and then checks where they are
	public void moveAnts()
	{
		//iterate through the ants
		for(int i = 0; i < Parameters.NUM_OF_ANTS; ++i)
		{
			// Check to see if the ant is carrying food. If it is, drop the pheromone
			if(nest1.theAnts[i].isHasFood() && boardLayout[nest1.theAnts[i].getX()][nest1.theAnts[i].getY()].isEmpty)
			{
				boardLayout[nest1.theAnts[i].getX()][nest1.theAnts[i].getY()].setPheromone(Parameters.PHEROMONE_STRENGTH + 1);
			}
			
			// Check if the cell has a pheromone on it. If it does, check the cells around it
			// and move in the direction of the food (weaker direction)
			if(boardLayout[nest1.theAnts[i].getX()][nest1.theAnts[i].getY()].getPheromone() > 0 && !nest1.theAnts[i].isHasFood())
			{
				int antX = nest1.theAnts[i].getX();
				int antY = nest1.theAnts[i].getY();
				int pherStrength = boardLayout[antX][antY].getPheromone();
				
				if(boardLayout[antX][antY+1].getPheromone() < pherStrength && boardLayout[antX][antY+1].getPheromone() > 0)
				{
					nest1.theAnts[i].move(antX, antY+1);
				}
				else if(boardLayout[antX+1][antY+1].getPheromone() < pherStrength && boardLayout[antX+1][antY+1].getPheromone() > 0)
				{
					nest1.theAnts[i].move(antX+1, antY+1);
				}
				else if(boardLayout[antX+1][antY].getPheromone() < pherStrength && boardLayout[antX+1][antY].getPheromone() > 0)
				{
					nest1.theAnts[i].move(antX+1, antY);
				}
				else if(boardLayout[antX+1][antY-1].getPheromone() < pherStrength && boardLayout[antX+1][antY-1].getPheromone() > 0)
				{
					nest1.theAnts[i].move(antX+1, antY-1);
				}
				else if(boardLayout[antX][antY-1].getPheromone() < pherStrength && boardLayout[antX][antY-1].getPheromone() > 0)
				{
					nest1.theAnts[i].move(antX, antY-1);
				}
				else if(boardLayout[antX-1][antY-1].getPheromone() < pherStrength && boardLayout[antX-1][antY-1].getPheromone() > 0)
				{
					nest1.theAnts[i].move(antX-1, antY-1);
				}
				else if(boardLayout[antX-1][antY].getPheromone() < pherStrength && boardLayout[antX-1][antY].getPheromone() > 0)
				{
					nest1.theAnts[i].move(antX-1, antY);
				}
				else if(boardLayout[antX-1][antY+1].getPheromone() < pherStrength && boardLayout[antX-1][antY+1].getPheromone() > 0)
				{
					nest1.theAnts[i].move(antX-1, antY+1);
				}
				else
				{
					// If no more surrounding cells have pheromones and the pheromone trail
					// doesn't lead to a food pile, move the ants to next space
					nest1.theAnts[i].move();
				}
			}
			else
			{			
				//move the ants to next space
				nest1.theAnts[i].move();
			}

			//check if ant is at the nest
			if(nest1.theAnts[i].getX() == nest1.getX() && nest1.theAnts[i].getY() == nest1.getY())
			{
				//check to see if the ant is carrying food
				if(nest1.theAnts[i].isHasFood())
				{
					//drop off food at the nest
					nest1.theAnts[i].setHasFood(false);
					nest1.incrementFood();
				}
				
				//Check if ant is hungry and if there is food at the nest
				if(nest1.theAnts[i].getRemainingLife() < Parameters.ANT_LIFESPAN/2 && nest1.getFoodStored() > 1)
				{
					//restores the ants life remaining to full value
					nest1.theAnts[i].feedAnt();
					nest1.decrementFood();
				}
			}
			//check to see if an ant found food
			else if(boardLayout[nest1.theAnts[i].getX()][nest1.theAnts[i].getY()].hasFood)
			{
				//only pick up food if the ant isn't already carrying food
				if(!nest1.theAnts[i].isHasFood())
				{
					nest1.theAnts[i].setHasFood(true);//set carrying food attribute to true on the ant
					int temp = getFoodPileIndex(nest1.theAnts[i]); //get index number of given food pile
					theFood[temp].decrementPile(); //decrement the food remaining
				}
			}
		}
	}
	
	//get the index number of a food pile that the given ant is on.
	public int getFoodPileIndex(Ant currentAnt)
	{
		for(int i=0; i<Parameters.NUM_OF_FOOD_PILES; i++)
		{
			if(theFood[i].getX() == currentAnt.getX() && theFood[i].getY() == currentAnt.getY())
				return i;
		}
		
		System.out.println("Reached the unreachable");
		return -1;  //this line will never happen but eclipse made me add another return state
	}
	
	//gets the new status of all the parts of the simulator and returns them in an integer array
	public GroundCell[][] updateMap()
	{
		moveAnts(); //move all the ants
		
		//check to see if there is enough food and space to spawn more ants
		while(nest1.getFoodStored() >= Parameters.FOOD_TO_SPAWN_NEW_ANT && nest1.checkForDeadAnts())
		{
			nest1.createNewAnt(); //will spawn a new ant in a dead ant's place
		}
		
		this.resetBoard();
		
		boardLayout[nest1.getX()][nest1.getY()].hasNest = true; //place the nest
		
		for(int i=0; i<Parameters.NUM_OF_FOOD_PILES; ++i) //draw the food piles
		{
			if(theFood[i].isHasFoodLeft()) //check if food pile still has food in it
			{
				boardLayout[theFood[i].getX()][theFood[i].getY()].hasFood = true;
			}
		}
		
		for(int i=0; i<Parameters.NUM_OF_ANTS; ++i) //draw the ants
		{
			if(nest1.theAnts[i].isAlive()) // check to see if ant is alive
			{
				if(nest1.theAnts[i].isHasFood()) //check if the ant is carrying food
				{
					//display ant carrying food
					boardLayout[nest1.theAnts[i].getX()][nest1.theAnts[i].getY()].hasAntWithFood = true;
				}
				else
				{
					boardLayout[nest1.theAnts[i].getX()][nest1.theAnts[i].getY()].hasAnt = true; //display an ant
				}
			}
		}
		return boardLayout;
	}

	//
	public void resetBoard()
	{
		for(int i=0; i<Parameters.BOARD_SIZE; i++)
		{
			for( int j=0; j<Parameters.BOARD_SIZE; j++)
			{
				boardLayout[i][j].isEmpty = true;
				boardLayout[i][j].hasFood = false;
				boardLayout[i][j].hasAnt = false;
				boardLayout[i][j].hasAntWithFood = false;
				
				int tempPheromone = boardLayout[i][j].getPheromone();
				
				if(tempPheromone > 0)
				{
					boardLayout[i][j].setPheromone(tempPheromone - 1);
				}
			}
		}
	}
}
