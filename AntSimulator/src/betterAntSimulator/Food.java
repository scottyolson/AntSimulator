package betterAntSimulator;

public class Food 
{
	private int x;
	private int y;
	private int pileSize = Parameters.FOOD_PILE_SIZE; //amount of food contained in this food pile
	private boolean hasFoodLeft;

	public Food(int startX, int startY)
	{
		hasFoodLeft = true;
		x = startX;
		y = startY;
	}

	public int getPileSize() 
	{
		return pileSize;
	}

	public void decrementPile()
	{
		pileSize--;
		if(pileSize < 1)
			this.hasFoodLeft = false;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public boolean isHasFoodLeft() {
		return hasFoodLeft;
	}
	
	public void setHasFoodLeft(boolean hasFoodLeft) {
		this.hasFoodLeft = hasFoodLeft;
	}
}
