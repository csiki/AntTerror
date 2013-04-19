package gameLogic;

public class AntEaterColony extends Item { // ready

	private Field spawnField;
	private int timeBetweenRelease;
	private int countdownToRelease;
	
	AntEaterColony(Field field) {
		super(field);
		int i = 0;
		while (this.field.getNeighbour(i) == null) ++i;
		
		this.spawnField = this.field.getNeighbour(i);
		this.timeBetweenRelease = 10;
		this.countdownToRelease = 1;
	}
	
	AntEaterColony(Field field, int timeBetweenRelease, Field spawnField) {
		super(field);
		this.spawnField = spawnField;
		this.timeBetweenRelease = timeBetweenRelease;
		this.countdownToRelease = 1;
	}
	
	@Override
	public void act() {
		this.countdownToRelease--;
		
		if (this.countdownToRelease == 0) {
			this.spawnField.register(new AntEater(this.spawnField));
			this.countdownToRelease = timeBetweenRelease;
		}
	}
	
	@Override
	public void antInteract(Ant ant) {
		// nothing happens here
	}
	
	@Override
	public void antEaterInteract(AntEater antEater) {
		antEater.arrivedHome();
	}
	
	@Override
	public void killerSprayInteract() {
		// nothing happens here
	}

	@Override
	public void stoneInteract(Stone stone) {
		// nothing happens here
	}

}
