package gameLogic;

public class Ant extends Item {

	public boolean carry = false; //csak a protoban, egyébként private
	public Food carriedItem = null; //csak a protoban, egyébként private
	private Field nbField = null;
	
	Ant(Field field) {
		super(field);
	}
	
	public void eaten() {
		System.out.println("\t\tant - eaten()");
		System.out.println("\t\t\tf - deregister()");
		this.field.deregister();
	}

	public void pickup(Food food) {
		System.out.println("\t\tant - pickup(i)");
		System.out.println("\t\t\tf - deregister()");
		this.field.deregister();
		System.out.println("\t\t\ts - register()");
		this.nbField.register(this);
	}

	public void arrivedHome() {
		System.out.println("\t\tant - arrivedHome()");
		System.out.println("\t\t\tf - deregister()");
		this.field.deregister();
	}

	@Override
	public void act() {
		System.out.println("ant - act()");
		
		this.nbField = this.field.getNeighbour(0);
		Odor o = this.nbField.getOdor();
		ItemManagableByItem i = this.nbField.getItem();
		
		if (i != null)
			i.antInteract(this);
		else {
			System.out.println("\tf - deregister()");
			this.field.deregister();
			System.out.println("\ts - register()");
			this.nbField.register(this);
		}
	}

	@Override
	public void antInteract(Ant ant) {
		System.out.println("\ti - antInteract(ant)");
	}

	@Override
	public void antEaterInteract(AntEater antEater) {
		System.out.println("\tant - antEaterInteract(anteater)");
		System.out.println("\t\ts - deregister()");
		this.field.deregister();
		antEater.eat();
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
