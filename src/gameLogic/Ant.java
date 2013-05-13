package gameLogic;

import java.util.Random;
import display.AntCarryDisplay;
import display.AntDisplay;
import display.MapElementDisplay;

public class Ant extends Item { // ready

	private boolean carry = false; 
	private Food carriedItem = null;
	private Field nbField = null;
	private int[] odds;
	
	Ant(Field field) {
		super(field);
		this.field.setAntOdor(4);
	}
	
	public void eaten() {
		this.dropFood();
		this.dereg();
	}

	public void pickup(Food food) {
		this.dereg();
		this.reg();
		this.carriedItem = food;
		this.carry = true;
	}

	public void arrivedHome() {
		if (this.carry) {
			this.carriedItem.dropped(null);
			this.dereg();
		}
	}

	@Override
	public void act() {
		
		this.chooseByOdor();
		this.nbField = null;
		while (this.field != this.nbField && this.field != null) {

			this.nbField = getNextBestChoiceNb();
			if (this.nbField == null)
				break;
			
			ItemManagableByItem i = this.nbField.getItem();
				
			if (i != null)
				i.antInteract(this);
			else {
				this.dereg();
				this.reg();
			}
		}
	}
	
	private void chooseByOdor() { // TODO �j fv�ny
		
		Odor o = null;
		int oddsSoFar;
		Field f = null;
		
		odds = new int[6];
		for (int i=0; i<6; ++i)
			odds[i] = -1;
		
		for (int i=0; i<6; ++i) {
			oddsSoFar = 0;
			
			if (this.field != null)
				f = this.field.getNeighbour(i);
			
			if (f != null) {
				o = f.getOdor();
				
				if (this.carry)
					oddsSoFar = o.getHill();
				else
					oddsSoFar = o.getAnt() + 6*o.getFood();
				odds[i] = oddsSoFar;
			}
		}
	}
	
	private Field getNextBestChoiceNb() {
		int chosenIndex = -1;
		
		if (this.carry) {
			chosenIndex = this.maxIndex();
		} else {
			int randomOdds[] = new int[6];
			int sum = 0;
			
			// fill randomOdds
			randomOdds[0] = odds[0];
			for (int i=1; i<6; ++i) {
				if (odds[i] == -1)
					randomOdds[i] = -1;
				else {
					randomOdds[i] = sum + odds[i] * 50; // TODO
					sum += odds[i] * 50;
				}
			}
			
			// generate random number
			Random generator = new Random();
			int randNum = 0;
			if (sum != 0)
				randNum = generator.nextInt(sum);
			
			// get chosenIndex
			for (int i=0; i<6; ++i) {
				if (randNum <= randomOdds[i]) {
					chosenIndex = i;
					break;
				}
			}
		}
		
		if (chosenIndex == -1)
			return null;
		
		this.odds[chosenIndex] = -1;
		
		return this.field.getNeighbour(chosenIndex);
	}
	
	private int maxIndex() {
		int maxVal = -1;
		int index = -1;
		
		for (int i=0; i<6; ++i) {
			if (this.odds[i] > maxVal) {
				maxVal = this.odds[i];
				index = i;
			}
		}
		
		return index;
	}
	
	private void dropFood() { // TODO �j met�dus
		if (this.carry) { // drop food
			for (int i=0; i<6; ++i) {
				if (this.field.getNeighbour(i) != null) {
					if (this.field.getNeighbour(i).getItem() == null) {
						this.field.getNeighbour(i).register(this.carriedItem);
						this.carriedItem.dropped(this.field.getNeighbour(i));
					}
				}
			}
		}
	}
	
	private void reg() { // TODO �j fv�ny!
		this.nbField.register(this);
		this.field = this.nbField;
		this.field.setAntOdor(4);
	}
	
	private void dereg() { // TODO �j fv�ny
		this.field.deregister();
		this.field = null;
	}

	@Override
	public void antInteract(Ant ant) {
		// nothing happens here
	}

	@Override
	public void antEaterInteract(AntEater antEater) {
		this.dropFood();
		this.dereg();
		antEater.eat();
	}

	@Override
	public void killerSprayInteract() {
		this.dropFood();
		this.dereg();
	}

	@Override
	public void stoneInteract(Stone stone) {
		// nothing happens here
	}

	@Override
	public MapElementDisplay getDisplay() {
		if (this.carry)
			return AntCarryDisplay.getInstance();
		return AntDisplay.getInstance();
	}

	@Override
	public boolean isThereAnyFood() {
		return this.carry; // if got food than true, if not, false
	}
}
