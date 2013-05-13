package gameLogic;


public class OdorNeutralizerSpray extends Spray { // ready

	public OdorNeutralizerSpray(int charge, int radian) {
		super(charge, radian);
	}

	@Override
	public void mechanism(Field field) {
		if (this.charge > 0) {
			this.spreadNeutralizer(field, 2, 0, this.radian);
			this.charge--;
		}
	}
	
	private void spreadNeutralizer(Field field, int mode, int to, int power) { // TODO új fvény
		if (power < 0 || field == null)
			return;
		
		field.setAntOdor(0);
		
		if (mode == 2) {
			for (int nb=0; nb<6; ++nb) {
				if (field.getNeighbour(nb) != null)
					spreadNeutralizer(field.getNeighbour(nb), mode-1, nb, power-1);
			}
		} else if (mode == 1) {
			// 0
			spreadNeutralizer(field.getNeighbour(to), mode, to, power-1);
			
			// +1
			to = (to + 1) % 6;
			spreadNeutralizer(field.getNeighbour(to), mode, to, power-1);
			
			// -1
			to = to-1 < 0 ? 5 : to-1;
			spreadNeutralizer(field.getNeighbour(to), mode, to, power-1);
		}
	}

}
