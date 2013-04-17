package gameLogic;

public class Food extends Item {

	Food(Field field) {
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
		System.out.println("\t\ts - deregister()");
		this.field.deregister();
		
		ant.pickup(this);
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
