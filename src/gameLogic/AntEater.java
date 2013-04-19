package gameLogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AntEater extends Item {

	private int hunger;
	private Field nbField = null;
	private int fromWhere; // TODO új attibrútum! SEEMMI NEM ÁLLÍTJA BE !!!!!
	
	AntEater(Field field) {
		super(field);
		this.hunger = 10;
	}
	
	AntEater(Field field, int hunger) {
		super(field);
		this.hunger = hunger;
	}
	
	public void eat() {
		this.dereg();
		this.reg();
		this.hunger--;
	}

	public void arrivedHome() {
		if (this.hunger <= 0)
			this.dereg();
	}
	public void push(){
		this.dereg();
		this.reg();
	}

	@Override
	public void act() {
		
		this.nbField = this.chooseByOdor();
		
		if (this.nbField != null) {
			ItemManagableByItem i = this.nbField.getItem();
			
			if (i != null)
				i.antEaterInteract(this);
			else {
				this.dereg();
				this.reg();
			}
		}
	}
	
	private Field chooseByOdor() { // TODO új fvény
		
		Field chosen = null;
		Odor o = null;
		int oddsSoFar = 0;
		
		/// calculate odds
		Map<Field, Integer> odds = new HashMap<Field, Integer>();
		for (int i=0; i<6; ++i) {
			this.nbField = this.field.getNeighbour(i);
			if (this.nbField != null) {
				o = this.nbField.getOdor();
				if (this.hunger <= 0) // if not hungry, goes to colony
					oddsSoFar += o.getColony();
				else
					oddsSoFar += o.getAnt();
				odds.put(this.nbField, oddsSoFar);
			}
		}
		
		/// rand
		Random gen = new Random();
		int randomNum = gen.nextInt(oddsSoFar);
		
		for (Map.Entry<Field, Integer> entry : odds.entrySet()) {
			if (randomNum <= entry.getValue()) {
				return entry.getKey();
			}
		}
		
		return null; // cannot happen
	}

	@Override
	public void antInteract(Ant ant) {
		ant.eaten();
		this.hunger--;
	}

	@Override
	public void antEaterInteract(AntEater antEater) {
		// nothing happens here
	}

	@Override
	public void killerSprayInteract() {
		// nothing happens here
	}

	@Override
	public void stoneInteract(Stone stone) {
		// nothing happens here
	}
	
	public int getFromWhere() { // TODO új fvény!
		return this.fromWhere;
	}
	
	private void reg() { // TODO új fvény!
		this.nbField.register(this);
		this.field = this.nbField;
		this.nbField = null;
	}
	
	private void dereg() { // TODO új fvény
		this.field.deregister();
		this.field = null;
	}
	
}
