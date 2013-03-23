package gameLogic;

public abstract class Item implements ItemManagableByItem {

	protected Field field;
	
	Item(Field field) {
		this.field = field;
	}

	public abstract void act();
	
	public abstract void antInteract(Ant ant);

	public abstract void antEaterInteract(AntEater antEater);

	public abstract void killerSprayInteract();
	
}
