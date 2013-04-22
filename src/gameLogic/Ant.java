package gameLogic;

public class Ant extends Item { // ready

	public boolean carry = false; // TODO csak protoban public, egybk private
	public Food carriedItem = null; // TODO csak protoban public, egybk privates
	private Field nbField = null;
	int[] odds;
	
	Ant(Field field) {
		super(field);
		this.field.setAntOdor(4);
	}
	
	public void eaten() {
		this.dereg();
	}

	public void pickup(Food food) {
		this.dereg();
		this.reg();
		this.carriedItem = food;
		this.carry = true;
	}

	public void arrivedHome() {
		if (this.carry)
			this.dereg();
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
	
	private void chooseByOdor() { // TODO új fvény
		
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
				
				if (this.carry)
					oddsSoFar = o.getHill();
				else
					oddsSoFar = o.getAnt() + 5*o.getFood();
				odds[i] = oddsSoFar;
			}
		}
	}
	
	private Field getNextBestChoiceNb() {
		
		int maxOdds = 0;
		int maxIndex = 0;
		
		for (int i=0; i<6; ++i) {
			
			if (this.odds[i] > maxOdds) {
				maxOdds = this.odds[i];
				maxIndex = i;
			}
		}
		
		if (maxOdds == -1)
			return null;
			
		this.odds[maxIndex] = -1;
		
		return this.field.getNeighbour(maxIndex);
	}
	
	private void reg() { // TODO új fvény!
		this.nbField.register(this);
		this.field = this.nbField;
		this.field.setAntOdor(4);
	}
	
	private void dereg() { // TODO új fvény
		this.field.deregister();
		this.field = null;
	}

	@Override
	public void antInteract(Ant ant) {
		// nothing happens here
	}

	@Override
	public void antEaterInteract(AntEater antEater) {
		this.dereg();
		antEater.eat();
	}

	@Override
	public void killerSprayInteract() {
		this.dereg();
	}

	@Override
	public void stoneInteract(Stone stone) {
		// nothing happens here
	}
}
