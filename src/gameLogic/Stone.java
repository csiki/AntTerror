package gameLogic;

public class Stone extends Item {
	
	private Field nbField = null;
	private int fromWhere; // TODO új attibrútum! SEEMMI NEM ÁLLÍTJA BE !!!!!
	
	Stone(Field field) {
		super(field);
	}

	@Override
	public void act() {
		// nothing happens here
	}

	@Override
	public void antInteract(Ant ant) {
		// nothing happens here
	}
	
	/*******
	 0 1   
	5 I 2
	 4 3
	
	if comesFrom=5 --> futherTo=2 ...
	 *******/
	
	@Override
	public void antEaterInteract(AntEater antEater) {
		int comesFrom = antEater.getFromWhere();
		int furtherTo = (comesFrom + 3) % 6;
		this.nbField = this.field.getNeighbour(furtherTo);
		ItemManagableByItem item = this.nbField.getItem();
		
		if (item != null)
			item.stoneInteract(this);
		else {
			this.field.deregister();
			this.nbField.register(this);
		}
	}

	@Override
	public void killerSprayInteract() {
		// nothing happens here
	}

	@Override
	public void stoneInteract(Stone stone) {
		int comesFrom = stone.getFromWhere();
		int furtherTo = (comesFrom + 3) % 6;
		this.nbField = this.field.getNeighbour(furtherTo);
		
		if (this.nbField.getItem() == null) {
			this.dereg();
			this.reg();
			stone.push();
		}
	}
	
	public void push(){
		this.dereg();
		this.reg();
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
