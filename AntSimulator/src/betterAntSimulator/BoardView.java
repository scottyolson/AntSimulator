package betterAntSimulator;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

//
public class BoardView extends JPanel
{
	GroundCell[][] theCells = new GroundCell[Parameters.BOARD_SIZE][Parameters.BOARD_SIZE]; //a grid of cells
	private Timer timer;
	
	//the various images to be displayed
	private ImageIcon[] dirtImage = new ImageIcon[28];
	private ImageIcon antImage;
	private ImageIcon foodImage;
	private ImageIcon nestImage;
	private ImageIcon antWithFoodImage;
	private ImageIcon pheromoneImage;
	
	//the controller that will get info from the model
	private SimulatorController theController;
	
	public BoardView() 
	{
		for(int i=0; i<Parameters.BOARD_SIZE; i++) //create the initial view
		{
			for(int j=0; j<Parameters.BOARD_SIZE; j++)
			{
				theCells[i][j] = new GroundCell();
			}
		}
		
		setLayout(null);
		timer = new Timer(Parameters.TIMER_DELAY, new TimerEventHandler());
		
		Image img;
		Image newImg;
		
		for(int i = 0; i < 28; ++i)
		{
			dirtImage[i] = new ImageIcon("dirt" + (i+1) + ".png");
			img = dirtImage[i].getImage();
			newImg = img.getScaledInstance(Parameters.CELL_SIZE, Parameters.CELL_SIZE, java.awt.Image.SCALE_SMOOTH);
			dirtImage[i] = new ImageIcon( newImg );
		}
		
		antImage = new ImageIcon("ant.png");
		img = antImage.getImage();
		newImg = img.getScaledInstance(Parameters.CELL_SIZE, Parameters.CELL_SIZE, java.awt.Image.SCALE_SMOOTH);
		antImage = new ImageIcon( newImg );
		
		foodImage = new ImageIcon("food.png");
		img = foodImage.getImage();
		newImg = img.getScaledInstance(Parameters.CELL_SIZE, Parameters.CELL_SIZE, java.awt.Image.SCALE_SMOOTH);
		foodImage = new ImageIcon( newImg );
		
		nestImage = new ImageIcon("nest.png");
		img = nestImage.getImage();
		newImg = img.getScaledInstance(Parameters.CELL_SIZE, Parameters.CELL_SIZE, java.awt.Image.SCALE_SMOOTH);
		nestImage = new ImageIcon( newImg );
		
		antWithFoodImage = new ImageIcon("antWithFood.png");
		img = antWithFoodImage.getImage();
		newImg = img.getScaledInstance(Parameters.CELL_SIZE, Parameters.CELL_SIZE, java.awt.Image.SCALE_SMOOTH);
		antWithFoodImage = new ImageIcon( newImg );
		
		pheromoneImage = new ImageIcon("cloud.png");
		img = pheromoneImage.getImage();
		newImg = img.getScaledInstance(Parameters.CELL_SIZE, Parameters.CELL_SIZE, java.awt.Image.SCALE_SMOOTH);
		pheromoneImage = new ImageIcon(newImg);
		
		theController = new SimulatorController();
		
		timer.start();
	}
	
	public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(int i=0; i<Parameters.BOARD_SIZE; i++) //iterate through the grid, painting each cells appropriate images
		{
			for(int j=0; j<Parameters.BOARD_SIZE; j++)
			{
				dirtImage[(i + j) % 28].paintIcon(this, g, i*Parameters.CELL_SIZE, j*Parameters.CELL_SIZE);
				if(theCells[i][j].getPheromone() > 0)
					pheromoneImage.paintIcon(this, g, i*Parameters.CELL_SIZE, j*Parameters.CELL_SIZE);
				if(theCells[i][j].hasNest)
					nestImage.paintIcon(this, g, i*Parameters.CELL_SIZE, j*Parameters.CELL_SIZE);
				if(theCells[i][j].hasFood)
					foodImage.paintIcon(this, g, i*Parameters.CELL_SIZE, j*Parameters.CELL_SIZE);
				if(theCells[i][j].hasAnt)
					antImage.paintIcon(this, g, i*Parameters.CELL_SIZE, j*Parameters.CELL_SIZE);	
				if(theCells[i][j].hasAntWithFood)
					antWithFoodImage.paintIcon(this, g, i*Parameters.CELL_SIZE, j*Parameters.CELL_SIZE);
			}
		}
    }
	
	private class TimerEventHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	theCells = theController.updateBoard(); //get the updated info from the controller
        	
            repaint();
        }
    }
}

