package gameLogic;

public class Barrier extends Item {

	Barrier(Field field) {
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
