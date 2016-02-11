package betterAntSimulator;

public class SimulatorController 
{
	BoardModel theBoard;
	
	public SimulatorController() 
	{
		theBoard = new BoardModel(); // The initial creation of the board
	}
	
	//this is the function that the view calls to update the board
	public GroundCell[][] updateBoard()
	{
		return theBoard.updateMap(); //requests and update from the model of all the ant positions
	}
}