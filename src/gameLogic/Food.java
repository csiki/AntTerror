package gameLogic;

import display.FoodDisplay;
import display.MapElementDisplay;

public class Food extends Item { // ready
	
	Food(Field field) {
		super(field);
	}

	@Override
	public void act() {
		this.spreadFoodOdor(this.field, 80);
		
		// check if disappeared
		if (this.field != null && this.field.getItem() == null)
			this.field = null;
	}
	
	public void dropped(Field newField) { // TODO új fvény
		if (this.field != null) {
			this.field.deregister();
		}
		this.field = newField;
		
		if (newField != null) {
			newField.register(this);
			this.spreadFoodOdor(newField, 80);
		}
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
		if (!ant.isThereAnyFood()) { // cannot carry more than 1
			this.field.deregister();
			ant.pickup(this);
		}
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
		return FoodDisplay.getInstance();
	}

	@Override
	public boolean isThereAnyFood() {
		return true;
	}

}
