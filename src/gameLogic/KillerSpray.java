package gameLogic;

public class KillerSpray extends Spray { // ready
	
	public KillerSpray(int charge, int radian) {
		super(charge, radian);
	}

	@Override
	public void mechanism(Field field) {
		if (this.charge > 0) {
			this.spreadKiller(field, 2, 0, this.radian);
			this.charge--;
		}
	}
	
	private void spreadKiller(Field field, int mode, int to, int power) { // TODO új fvény
		if (power < 0 || field == null)
			return;
		
		if (field.getItem() != null)
			field.getItem().killerSprayInteract();
		
		if (mode == 2) {
			for (int nb=0; nb<6; ++nb) {
				if (field.getNeighbour(nb) != null)
					spreadKiller(field.getNeighbour(nb), mode-1, nb, power-1);
			}
		} else if (mode == 1) {
			// 0
			spreadKiller(field.getNeighbour(to), mode, to, power-1);
			
			// +1
			to = (to + 1) % 6;
			spreadKiller(field.getNeighbour(to), mode, to, power-1);
			
			// -1
			to = to-1 < 0 ? 5 : to-1;
			spreadKiller(field.getNeighbour(to), mode, to, power-1);
		}
	}
}
