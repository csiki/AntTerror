package gameLogic;

public class AntHill extends Item {

	private Field spawnField;
	private int timeBetweenRelease;
	private int countdownToRelease;
	
	AntHill(Field field) {
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
		ant.arrivedHome();
	}
	@Override
	public void antEaterInteract(AntEater antEater) {
		System.out.println("\ti - antEaterInteract(hs)");
		
	}
	@Override
	public void killerSprayInteract() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stoneInteract() {
		// TODO Auto-generated method stub
		
	}

}
