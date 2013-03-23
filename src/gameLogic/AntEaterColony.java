package gameLogic;

public class AntEaterColony extends Item {

	private Field spawnField;
	private int timeBetweenRelease;
	private int countdownToRelease;
	
	AntEaterColony(Field field) {
		super(field);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void antInteract(Ant ant) {
		System.out.println("\ti - antInteract(ant)");
		
	}
	@Override
	public void antEaterInteract(AntEater antEater) {
		System.out.println("\tanteatercol - antEaterInteract(anteater)");
		antEater.arrivedHome();
	}
	@Override
	public void killerSprayInteract() {
		// TODO Auto-generated method stub
		
	}

}
