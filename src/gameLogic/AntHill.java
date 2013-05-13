package gameLogic;

import display.HillDisplay;
import display.MapElementDisplay;

public class AntHill extends Item { // ready

	private Field spawnField;
	private int timeBetweenRelease;
	private int countdownToRelease;
	private Spawner spawner;
	
	AntHill(Field field, Spawner spawner) {
		super(field);
		int i = 0;
		while (this.field.getNeighbour(i) == null) ++i;
		
		this.spawnField = this.field.getNeighbour(i);
		this.timeBetweenRelease = 10;
		this.countdownToRelease = 0;
		this.spawner = spawner;
		
		this.spreadAntHillOdor(this.field, 100); // 100 odor to spread
	}
	
	AntHill(Field field, int timeBetweenRelease, Field spawnField, Spawner spawner) {
		super(field);
		this.spawnField = spawnField;
		this.timeBetweenRelease = timeBetweenRelease;
		this.countdownToRelease = 0;
		this.spawner = spawner;
		
		this.spreadAntHillOdor(this.field, 100); // 100 odor to spread
	}
	
	@Override
	public void act() {
		
		// spawn
		if (this.countdownToRelease <= 0) {
			Ant a = new Ant(this.spawnField);
			this.spawnField.register(a);
			this.spawner.spawnItem(a);
			
			this.countdownToRelease = timeBetweenRelease;
		}
		
		this.countdownToRelease--;
	}
	
	/**
	 * Spread anthill odor recursively.
	 * @param field current field to drop odor
	 * @param mode 3: spread in all directions; 2: spread forward and to the first left; 1: spread forward
	 * @param from direction of the food
	 * @param odor amount of food odor to drop
	 */
	private void spreadAntHillOdor(Field field, int odor) { // TODO új fvény!
		
		if (odor <= 0)
			return;
		
		if (field != null && field.getOdor().getHill() < odor)
			field.setHillOdor(odor);
		else
			return;
		
		for (int nb=0; nb<6; ++nb) {
			if (field.getNeighbour(nb) != null)
				spreadAntHillOdor(field.getNeighbour(nb), odor-1);
		}
	}
	
	@Override
	public void antInteract(Ant ant) {
		ant.arrivedHome();
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
		return HillDisplay.getInstance();
	}

	@Override
	public boolean isThereAnyFood() {
		return false;
	}

}
