package gameLogic;

public class Stone extends Item { // ready
	
	private Field nbField = null;
	private int furtherTo; // TODO új attibrútum!
	private AntEater pusher;
	
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
		this.pusher = antEater;
		this.furtherTo = antEater.getFromWhere();
		this.nbField = this.field.getNeighbour(this.furtherTo);
		if (this.nbField != null) {
			ItemManagableByItem item = this.nbField.getItem();
			
			if (item != null)
				item.stoneInteract(this);
			else {
				this.dereg();
				this.reg();
				antEater.push();
			}
		}
	}

	@Override
	public void killerSprayInteract() {
		// nothing happens here
	}

	@Override
	public void stoneInteract(Stone stone) {
		this.furtherTo = stone.getFromWhere();
		this.nbField = this.field.getNeighbour(this.furtherTo);
		if(this.nbField!=null){
		
		if (this.nbField.getItem() == null) {
			this.dereg();
			this.reg();
			stone.push();
		}
		}
	}
	
	public void push() {
		this.dereg();
		this.reg();
		this.pusher.push();
	}

	public int getFromWhere() { // TODO új fvény!
		return this.furtherTo;
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
