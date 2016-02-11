package betterAntSimulator;

public class Ant 
{
	private int x;
	private int y;
	private boolean hasFood;
	private int nestX;
	private int nestY;
	private int remainingLife = Parameters.ANT_LIFESPAN;
	private boolean isAlive = true;

	public Ant() //Constructor with no parameters generate random location
	{
		this((int)(Math.random() * Parameters.BOARD_SIZE), (int)(Math.random() * Parameters.BOARD_SIZE));
	}
	
	public Ant(int startX, int startY) //coordinates can be created for the ants spawn location
	{
		hasFood = false;
		x = startX;
		y = startY;
		nestX = startX;
		nestY = startY;
	}
	
	public void move(int x, int y)
	{
		remainingLife--; //decrement life every move
		if(remainingLife < 1) //if life reaches zero ant dies.
		{
			isAlive = false;
			return;
		}
		
		this.setX(x);
		this.setY(y);
	}
	
	public void move()
	{
		remainingLife--; //decrement life every move
//		if(remainingLife == 0)
//			System.out.println("             An Ant Dies");
		if(remainingLife < 1) //if life reaches zero ant dies.
		{
			isAlive = false;
			return;
		}
		
		//Ants move with complete randomness when not carrying food and randomness==0
		if(hasFood && Parameters.PULL_TOWARDS_NEST_WITH_FOOD)
		{
			int tempX = nestX - x;
			int tempY = nestY - y;
			if(tempX > 0)
				this.setX(x + 1);
			else if (tempX < 0)
				this.setX(x - 1);
			if(tempY > 0)
				this.setY(y + 1);
			else if (tempY < 0)
				this.setY(y - 1);
		}
		else
		{
			int rand = (int)(Math.random() * 4); //generate a random number to pick a direction for the ant to move
			
			if (rand == 0) //right 
			{
				this.setX(x + 1);
			}
			else if (rand == 1) //left 
			{  
				this.setX(x - 1);
			}
			else if (rand == 2) //down 
			{
				this.setY(y + 1);
			}
			else if (rand == 3) //up
			{
				this.setY(y - 1);
			}
		}
	}
	
	public int getX() 
	{
		return x;
	}
	
	public int getY() 
	{
		return y;
	}
	
	public boolean isHasFood() {
		return hasFood;
	}
	
	public void setHasFood(boolean hasFood) {
		this.hasFood = hasFood;
	}
	
	public void setX(int x) 
	{
		//Check to make sure the new location is within the boundaries of the simulation
		if(x >= Parameters.BOARD_SIZE)
			x--;
		if(x < 0)
			x++;
		this.x = x;
	}
	
	public void setY(int y) 
	{
		if(y == Parameters.BOARD_SIZE)
			y--;
		if(y < 0)
			y++;
		this.y = y;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public int getRemainingLife() {
		return remainingLife;
	}
	
	public void setRemainingLife(int remainingLife) {
		this.remainingLife = remainingLife;
	}
	
	public void feedAnt()
	{
		this.remainingLife = Parameters.ANT_LIFESPAN;
	}
}
