package betterAntSimulator;

import javax.swing.JFrame;

public class main 
{
	public static void main(String[] args) 
	{
		JFrame antFrame = new JFrame("Ant Simulator");
		
		antFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		antFrame.setSize(Parameters.BOARD_SIZE * Parameters.CELL_SIZE, Parameters.BOARD_SIZE * Parameters.CELL_SIZE + 28);
		antFrame.getContentPane().add(new BoardView());
		
		antFrame.setVisible(true);
	}	
}