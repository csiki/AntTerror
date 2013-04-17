package gameLogic;

public class AntEater extends Item {

	public int hunger; //csak a protoban, egyébként private
	private Field nbField = null;
	
	AntEater(Field field) {
		super(field);
	}
	
	public void eat() {
		System.out.println("\t\tanteater - eat()");
		System.out.println("\t\t\tf - deregister()");
		this.field.deregister();
		System.out.println("\t\t\ts - register()");
		this.nbField.register(this);
	}

	public void arrivedHome() {
		System.out.println("\t\tanteater - arrivedHome()");
		System.out.println("\t\t\tf - deregister()");
		this.field.deregister();
	}
	public void push(){
		
	}

	@Override
	public void act() {
		System.out.println("hs - act()");
		
		this.nbField = this.field.getNeighbour(0);
		Odor o = this.nbField.getOdor();
		ItemManagableByItem i = this.nbField.getItem();
		
		if (i != null)
			i.antEaterInteract(this);
		else {
			System.out.println("\tf - deregister()");
			this.field.deregister();
			System.out.println("\ts - register()");
			this.nbField.register(this);
		}
	}

	@Override
	public void antInteract(Ant ant) {
		System.out.println("\tanteater - antInteract(ant)");
		ant.eaten();
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
