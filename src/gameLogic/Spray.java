package gameLogic;

public abstract class Spray { // ready

	protected int charge; 
	protected int radian; 
	
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
