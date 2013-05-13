package gameLogic;

import display.MapElementDisplay;

public interface ItemManagableByItem { // ready
	public void antInteract(Ant ant);
	public void antEaterInteract(AntEater antEater);
	public void killerSprayInteract();
	public void stoneInteract(Stone stone);
	public MapElementDisplay getDisplay();
}
