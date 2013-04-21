package gameLogic;

public abstract class Spray { // ready

	protected int charge; // TODO megváltozott protected-re
	protected int radian; // TODO megváltozott protected-re
	
	public Spray(int charge, int radian){
		this.charge = charge;
		this.radian = radian;
	}

	public abstract void mechanism(Field field);
	
	public final int getCharge() {
		return charge;
	}
	
	public final int getRadian() {
		return radian;
	}
}
