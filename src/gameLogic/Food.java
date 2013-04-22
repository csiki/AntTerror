package gameLogic;

public class Food extends Item { // ready
	
	Food(Field field) {
		super(field);
		
		this.spreadFoodOdor(this.field, 80); // 80 odor to spread
		// TODO az ilyen konstansokat Game public statikus final változokká !
	}

	@Override
	public void act() {
		// nothing happens here
	}
	
	/**
	 * Spread food odor recursively.
	 * @param field current field to drop odor
	 * @param mode 3: spread in all directions; 2: spread forward and to the first left; 1: spread forward
	 * @param from direction of the food
	 * @param odor amount of food odor to drop
	 */
	private void spreadFoodOdor(Field field, int odor) { // TODO új fvény!
		
		if (odor <= 0)
			return;
		
		if (field != null && field.getOdor().getFood() < odor)
			field.setFoodOdor(odor);
		else
			return;
		
		for (int nb=0; nb<6; ++nb) {
			if (field.getNeighbour(nb) != null)
				spreadFoodOdor(field.getNeighbour(nb), odor-1);
		}
	}

	@Override
	public void antInteract(Ant ant) {
		this.field.deregister();
		
		ant.pickup(this);
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

}
