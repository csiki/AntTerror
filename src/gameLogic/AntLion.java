package gameLogic;

import display.AntLionDisplay;
import display.MapElementDisplay;


public class AntLion extends Item { // ready

	AntLion(Field field) {
		super(field);
	}

	@Override
	public void act() {
		// nothing happens here
	}

	@Override
	public void antInteract(Ant ant) {
		ant.eaten();
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
		return AntLionDisplay.getInstance();
	}

	@Override
	public boolean isThereAnyFood() {
		return false;
	}
}
