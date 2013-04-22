package gameLogic;

public class Odor { // ready
	
	Odor(int ant, int food, int hill, int colony) {
		this.ant = ant;
		this.food = food;
		this.hill = hill;
		this.colony = colony;
	}
	
	Odor() {
		this.ant = 0;
		this.food = 0;
		this.hill = 0;
		this.colony = 0;
	}
	
	public Integer ant = 0; // TODO csak protoban public, egybk private
	private Integer food = 0;
	private Integer hill = 0;
	private Integer colony = 0;

	public int getAnt() {
		return this.ant;
	}

	public int getFood() {
		return this.food;
	}

	public int getHill() {
		return this.hill;
	}

	public int getColony() {
		return this.colony;
	}
	
	public void setFood(int odor) {
		this.food = odor;
	}
	
	public void setColony(int odor) {
		this.colony = odor;
	}
	
	public void setHill(int odor) {
		this.hill = odor;
	}
	
	public void setAnt(int odor) {
		this.ant = odor;
	}

}
