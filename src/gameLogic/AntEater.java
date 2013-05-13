package gameLogic;

import java.util.Random;

import display.AntEaterDisplay;
import display.MapElementDisplay;

public class AntEater extends Item { // ready

	public int hunger;
	private Field nbField = null;
	private int fromWhere;
	int[] odds;
	
	AntEater(Field field) {
		super(field);
		this.hunger = 5;
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
		
		this.chooseByOdor();
		this.nbField = null;
		while (this.field != this.nbField && this.field != null) {

			this.nbField = getNextBestChoiceNb();
			if (this.nbField == null)
				break;
			
			ItemManagableByItem i = this.nbField.getItem();
				
			if (i != null)
				i.antEaterInteract(this);
			else {
				this.dereg();
				this.reg();
			}
		}
	}
	
	private void chooseByOdor() {
		
		Odor o = null;
		int oddsSoFar;
		Field f;
		
		/// calculate odds
		odds = new int[6];
		for (int i=0; i<6; ++i)
			odds[i] = -1;
		
		for (int i=0; i<6; ++i) {
			oddsSoFar = 0;
			
			f = this.field.getNeighbour(i);
			
			if (f != null) {
				o = f.getOdor();
				
				if (this.hunger <= 0)
					oddsSoFar = o.getColony();
				else
					oddsSoFar = o.getAnt();
				odds[i] = oddsSoFar;
			}
		}
	}
	
	private Field getNextBestChoiceNb() {
		int chosenIndex = -1;
		
		if (this.hunger <= 0) { // straight to the colony
			chosenIndex = this.maxIndex();
		}
		else if (this.sumOfOdds() == 0) { // if no odor than pure random
			int numOfPossibleDirs = 0;
			
			for (int i=0; i<6; ++i)
				if (this.odds[i] != -1)
					numOfPossibleDirs++;
			
			if (numOfPossibleDirs == 0)
				return null;
			
			Random generator = new Random();
			int randNum = generator.nextInt(numOfPossibleDirs);
			
			int nth = 0;
			for (int i=0; i<6; ++i) {
				if (this.odds[i] != -1) {
					if (nth == randNum) {
						chosenIndex = i;
						break;
					}
					nth++;
				}
			}
		} else { // odor + random
			int randomOdds[] = new int[6];
			int sum = 0;
			
			// fill randomOdds
			randomOdds[0] = odds[0];
			for (int i=1; i<6; ++i) {
				if (odds[i] == -1)
					randomOdds[i] = -1;
				else {
					randomOdds[i] = sum + odds[i] * 20; // TODO
					sum += odds[i] * 20;
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
	
	private int sumOfOdds() {
		int sum = 0;
		for (int i=0; i<6; ++i)
			sum += this.odds[i];
		
		return sum;
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

	@Override
	public void antInteract(Ant ant) {
		ant.eaten();
		this.hunger--;
	}

	@Override
	public void antEaterInteract(AntEater antEater) {
		/*this.nbField = getNextBestChoiceNb();
		
		if (this.nbField != null) {
			ItemManagableByItem i = this.nbField.getItem();
				
			if (i != null)
				i.antEaterInteract(this);
			else {
				this.dereg();
				this.reg();
			}
		}*/
		// nothing happens here
	}

	@Override
	public void killerSprayInteract() {
		// nothing happens here
	}

	@Override
	public void stoneInteract(Stone stone) {
		this.nbField = getNextBestChoiceNb();
		
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
	
	public int getFromWhere() {
		return this.fromWhere;
	}
	
	private void reg() {
		this.nbField.register(this);
		this.field = this.nbField;
	}
	
	private void dereg() {
		this.field.deregister();
		this.field = null;
	}

	@Override
	public MapElementDisplay getDisplay() {
		return AntEaterDisplay.getInstance();
	}

	@Override
	public boolean isThereAnyFood() {
		return false;
	}
	
}
