package gameLogic;

public class KillerSpray extends Spray { // ready
	
	public KillerSpray(int charge, int radian) {
		super(charge, radian);
	}

	@Override
	public void mechanism(Field field) {
		this.spreadKiller(field, 3, 0, 5);
		// TODO 5öt Game final static globalba!
	}
	
	private void spreadKiller(Field field, int mode, int to, int power) { // TODO új fvény
		if (power == 0)
			return;
		
		if (field.getItem() != null)
			field.getItem().killerSprayInteract();
		
		if (mode == 3) {
			
			for (int i=0; i<6; ++i)
				if (field.getNeighbour(i) != null)
					spreadKiller(field.getNeighbour(i), mode-1, to, power-1);
			
		} else if (mode == 2) {
			
			if (field.getNeighbour(to) != null) // forward, inherit mode 2
				spreadKiller(field.getNeighbour(to), mode, to, power-1);
			
			to = (to == 0) ? to = 5 : to - 1;
			if (field.getNeighbour(to) != null) // to the left, mode-1
				spreadKiller(field.getNeighbour(to), mode-1, to, power-1);
			
		} else if (mode == 1) {
			
			if (field.getNeighbour(to) != null) // forward, inherit mode 1
				spreadKiller(field.getNeighbour(to), mode, to, power-1);
			
		}
	}
}
