package gameLogic;

import java.util.Vector;

/***********
	 0 1   
	5 I 2
	 4 3
 ***********/

public class Field { // ready

	private Vector<Field> neighbours = new Vector<Field>(6);
	private Odor odor = new Odor();
	public Item item = null; // TODO csak protoban public, egybk private
	
	Field() {
		for (int i=0; i<6; ++i)
			neighbours.add(null);
	}

	public void decreaseAntOdor() {
		int newOdor = this.odor.getAnt() == 0 ? 0 : this.odor.getAnt() - 1;
		this.odor.setAnt(newOdor);
	}

	public void register(Item item) {
		this.item = item;
	}

	public void deregister() {
		this.item = null;
	}

	public ItemManagableByItem getItem() {
		return this.item;
	}

	public Odor getOdor() {
		return this.odor;
	}
	
	public void addNeighbour(Field nb, int place) {
		this.neighbours.add(place, nb);
	}

	public Field getNeighbour(int index) {
		return this.neighbours.get(index);
	}

	public void dropOdor(Odor smell) {
		int ant, food, hill, colony;
		
		ant = this.odor.getAnt() + smell.getAnt();
		food = this.odor.getFood() + smell.getFood();
		hill = this.odor.getHill() + smell.getHill();
		colony = this.odor.getColony() + smell.getColony();
		
		this.odor = new Odor(ant, food, hill, colony);
	}
	
	
	public void setFoodOdor(int odor) {
		this.odor.setFood(odor);
	}
	
	public void setColonyOdor(int odor) {
		this.odor.setColony(odor);
	}
	
	public void setHillOdor(int odor) {
		this.odor.setHill(odor);
	}
	
	public void setAntOdor(int odor) {
		this.odor.setAnt(odor);
	}
}
