package gameLogic;

public abstract class Spray {

	private int charge;
	private int radian;

	public abstract void mechanism(Field field);
	
	public final int getCharge() {
		return charge;
	}
	
	public final int getRadian() {
		return radian;
	}
}
