package gameLogic;

import display.BarrierDisplay;
import display.MapElementDisplay;

public class Barrier extends Item { // ready

	Barrier(Field field) {
		super(field);
	}

	@Override
	public void act() {
		// nothing happens here
	}

	@Override
	public void antInteract(Ant ant) {
		// nothing happens here
	}

	@Override
	public void antEaterInteract(AntEater antEater) {
		// nothing happens here
	}

	@Override
	public void killerSprayInteract() {
		// nothing happens here
	}

	@Override
	public void stoneInteract(Stone stone) {
		// nothing happens here
	}

	@Override
	public MapElementDisplay getDisplay() {
		return BarrierDisplay.getInstance();
	}

	@Override
	public boolean isThereAnyFood() {
		return false;
	}

}
