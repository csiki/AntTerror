package gameLogic;

public interface ItemManagableByItem {
	public void antInteract(Ant ant);
	public void antEaterInteract(AntEater antEater);
	public void killerSprayInteract();
	public void stoneInteract(Stone stone); // TODO változás: +1 argumentum
}
