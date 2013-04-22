package gameLogic;

public class AntEater extends Item { // ready

	public int hunger; // TODO csak protoban public, egybk private
	private Field nbField = null;
	private int fromWhere; // TODO új attibrútum!
	int[] odds;
	
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
				
				if (this.hunger <= 0)
					oddsSoFar = o.getColony();
				else
					oddsSoFar = o.getAnt();
				odds[i] = oddsSoFar;
			}
		}
	}
	
	private Field getNextBestChoiceNb() {
		
		int maxOdds = 0;
		
		for (int i=0; i<6; ++i) {
			
			if (this.odds[i] > maxOdds) {
				maxOdds = this.odds[i];
				this.fromWhere = i;
			}
		}
		
		if (maxOdds == -1)
			return null;
			
		this.odds[this.fromWhere] = -1;
		
		return this.field.getNeighbour(fromWhere);
	}
	

	@Override
	public void antInteract(Ant ant) {
		ant.eaten();
		this.hunger--;
	}

	@Override
	public void antEaterInteract(AntEater antEater) {
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
	
	public int getFromWhere() { // TODO új fvény!
		return this.fromWhere;
	}
	
	private void reg() { // TODO új fvény!
		this.nbField.register(this);
		this.field = this.nbField;
	}
	
	private void dereg() { // TODO új fvény
		this.field.deregister();
		this.field = null;
	}
	
}
