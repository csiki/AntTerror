package gameLogic;

import java.util.ArrayList;
import java.util.List;

public class Field {

	private List<Field> neighbours;
	private Odor odor;
	protected Item item = null; //miért lett ez protected ? ha protected minek a getItem ?
	
	Field() {
		this.odor = new Odor();
		this.neighbours = new ArrayList<Field>();
	}

	public void decreaseAntOdor() {
	}

	public void register(Item item) {
		this.item = item;
	}

	public void deregister() {
		this.item = null;
	}

	public ItemManagableByItem getItem() {
		System.out.println("\ts - getItem()");
		return this.item;
	}

	public Odor getOdor() {
		System.out.println("\ts - getOdor()");
		return this.odor;
	}
	
	public void addNeighbour(Field nb) {
		this.neighbours.add(nb);
	}

	public Field getNeighbour(int index) {
		System.out.println("\tf - getNeighbour(int)");
		return this.neighbours.get(index);
	}

	public void dropOdor(Odor smell) {
	}

}
