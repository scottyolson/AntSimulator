package betterAntSimulator;

public class GroundCell 
{
	public boolean isEmpty;
	public boolean hasFood;
	public boolean hasNest;
	public boolean hasAnt;
	public boolean hasAntWithFood;
	private int pheromone;
//	private GroundImages groundImg;

	public GroundCell() 
	{
		isEmpty = true;
		hasFood = false;
		hasNest = false;
		hasAnt = false;
		hasAntWithFood = false;
		setPheromone(0);
//		setGroundImg(GroundImages.dirt1);
	}

	public int getPheromone() {
		return pheromone;
	}

	public void setPheromone(int pheromone) {
		this.pheromone = pheromone;
	}

//	public GroundImages getGroundImg() {
//		return groundImg;
//	}
//
//	public void setGroundImg(GroundImages gi) {
//		this.groundImg = gi;
//	}
}