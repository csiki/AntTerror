package gameLogic;

public class AntEaterColony extends Item { // ready

	private Field spawnField;
	private int timeBetweenRelease;
	private int countdownToRelease;
	private boolean firstRound = true; // TODO �j attibr�tum
	
	AntEaterColony(Field field) {
		super(field);
		int i = 0;
		while (this.field.getNeighbour(i) == null) ++i;
		
		this.spawnField = this.field.getNeighbour(i);
		this.timeBetweenRelease = 10;
		this.countdownToRelease = 0;
	}
	
	AntEaterColony(Field field, int timeBetweenRelease, Field spawnField) {
		super(field);
		this.spawnField = spawnField;
		this.timeBetweenRelease = timeBetweenRelease;
		this.countdownToRelease = 0;
	}
	
	@Override
	public void act() {

		// spawn
		if (this.countdownToRelease <= 0) {
			this.spawnField.register(new AntEater(this.spawnField));
			this.countdownToRelease = timeBetweenRelease;
		}
		
		// spread odor
		if (this.firstRound) {
			this.firstRound = false;
			this.spreadColonyOdor(this.field, 3, 0, 100); // 100 odor to spread
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
	private void spreadColonyOdor(Field field, int mode, int to, int odor) { // TODO �j fv�ny!
		
		if (odor <= 0)
			return;
		
		field.dropOdor(new Odor(0, 0, 0, odor));
		
		if (mode == 3) {
			
			for (int i=0; i<6; ++i)
				if (field.getNeighbour(i) != null)
					spreadColonyOdor(field.getNeighbour(i), mode-1, i, odor-1);
			
		} else if (mode == 2) {
			
			if (field.getNeighbour(to) != null) // forward, inherit mode 2
				spreadColonyOdor(field.getNeighbour(to), mode, to, odor-1);
			
			to = (to == 0) ? to = 5 : to - 1;
			if (field.getNeighbour(to) != null) // to the left, mode-1
				spreadColonyOdor(field.getNeighbour(to), mode-1, to, odor-1);
			
		} else if (mode == 1) {
			
			if (field.getNeighbour(to) != null) // forward, inherit mode 1
				spreadColonyOdor(field.getNeighbour(to), mode, to, odor-1);
			
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

}
