package gameLogic;

public abstract class Item implements ItemManagableByItem { // ready

	protected Field field;
	
	Item(Field field) {
		this.field = field;
	}
	
	public Field getField() { // TODO új metódus !!!
		return this.field;
	}

	public abstract void act();
	
	public abstract void antInteract(Ant ant);

	public abstract void antEaterInteract(AntEater antEater);

	public abstract void killerSprayInteract();
	
	public abstract void stoneInteract(Stone stone);
	
	public abstract boolean isThereAnyFood();
}
