package gameLogic;

public class Food extends Item { // ready
	
	private boolean firstRound = true; // TODO új attibrútum
	
	Food(Field field) {
		super(field);
	}

	@Override
	public void act() {
		if (this.firstRound) {
			this.firstRound = false;
			this.spreadFoodOdor(this.field, 3, 0, 100); // 100 odor to spread
			// TODO az ilyen konstansokat Game public statikus final változokká !
		}
	}
	
	/**
	 * Spread food odor recursively.
	 * @param field current field to drop odor
	 * @param mode 3: spread in all directions; 2: spread forward and to the first left; 1: spread forward
	 * @param from direction of the food
	 * @param odor amount of food odor to drop
	 */
	private void spreadFoodOdor(Field field, int mode, int to, int odor) { // TODO új fvény!
		
		if (odor <= 0)
			return;
		
		field.dropOdor(new Odor(0, odor, 0, 0));
		
		if (mode == 3) {
			
			for (int i=0; i<6; ++i)
				if (field.getNeighbour(i) != null)
					spreadFoodOdor(field.getNeighbour(i), mode-1, i, odor-1);
			
		} else if (mode == 2) {
			
			if (field.getNeighbour(to) != null) // forward, inherit mode 2
				spreadFoodOdor(field.getNeighbour(to), mode, to, odor-1);
			
			to = (to == 0) ? to = 5 : to - 1;
			if (field.getNeighbour(to) != null) // to the left, mode-1
				spreadFoodOdor(field.getNeighbour(to), mode-1, to, odor-1);
			
		} else if (mode == 1) {
			
			if (field.getNeighbour(to) != null) // forward, inherit mode 1
				spreadFoodOdor(field.getNeighbour(to), mode, to, odor-1);
			
		}
	}

	@Override
	public void antInteract(Ant ant) {
		System.out.println("\ti - antInteract(ant)");
		System.out.println("\t\ts - deregister()");
		this.field.deregister();
		
		ant.pickup(this);
	}

	@Override
	public void antEaterInteract(AntEater antEater) {
		System.out.println("\ti - antEaterInteract(hs)");
		
	}

	@Override
	public void killerSprayInteract() {
		// TODO Auto-generated method stub
	}

	@Override
	public void stoneInteract(Stone stone) {
		// TODO Auto-generated method stub
		
	}

}
