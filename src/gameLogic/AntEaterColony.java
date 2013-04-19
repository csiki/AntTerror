package gameLogic;

public class AntEaterColony extends Item {

	private Field spawnField;
	private int timeBetweenRelease;
	private int countdownToRelease;
	
	AntEaterColony(Field field, int timeBetweenRelease, Field spawnField) {
		super(field);
		this.spawnField = spawnField;
	}
	
	@Override
	public void act() {
		// TODO Auto-generated method stub
		
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
