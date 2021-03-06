package gameLogic;

import display.ColonyDisplay;
import display.MapElementDisplay;

public class AntEaterColony extends Item { // ready

	private Field spawnField;
	private int timeBetweenRelease;
	private int countdownToRelease;
	private Spawner spawner;
	
	AntEaterColony(Field field, Spawner spawner) {
		super(field);
		int i = 0;
		while (this.field.getNeighbour(i) == null) ++i;
		
		this.spawnField = this.field.getNeighbour(i);
		this.timeBetweenRelease = 60;
		this.countdownToRelease = 0;
		this.spawner = spawner;
		
		this.spreadColonyOdor(this.field, 100); // 100 odor to spread
	}
	
	AntEaterColony(Field field, int timeBetweenRelease, Field spawnField, Spawner spawner) {
		super(field);
		this.spawnField = spawnField;
		this.timeBetweenRelease = timeBetweenRelease;
		this.countdownToRelease = 0;
		this.spawner = spawner;
		
		this.spreadColonyOdor(this.field, 100); // 100 odor to spread
	}
	
	@Override
	public void act() {

		// spawn
		if (this.countdownToRelease <= 0) {
			AntEater ae = new AntEater(this.spawnField);
			this.spawnField.register(ae);
			this.spawner.spawnItem(ae);
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
	private void spreadColonyOdor(Field field, int odor) { // TODO �j fv�ny!
		
		if (odor <= 0)
			return;
		
		if (field != null && field.getOdor().getColony() < odor)
			field.setColonyOdor(odor);
		else
			return;
		
		for (int nb=0; nb<6; ++nb) {
			if (field.getNeighbour(nb) != null)
				spreadColonyOdor(field.getNeighbour(nb), odor-1);
		}
	}
	
	@Override
	public void antInteract(Ant ant) {
		// nothing happens here
	}
	
	@Override
	public void antEaterInteract(AntEater antEater) {
		antEater.arrivedHome();
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
		return ColonyDisplay.getInstance();
	}

	@Override
	public boolean isThereAnyFood() {
		return false;
	}

}
