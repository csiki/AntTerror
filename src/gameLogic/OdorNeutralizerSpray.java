package gameLogic;


public class OdorNeutralizerSpray extends Spray { // ready

	public OdorNeutralizerSpray(int charge, int radian) {
		super(charge, radian);
	}

	@Override
	public void mechanism(Field field) {
		this.charge--;
		this.spreadNeutralizer(field, 3, 0, this.radian);
	}
	
	private void spreadNeutralizer(Field field, int mode, int to, int power) { // TODO új fvény
		if (power == 0)
			return;
		
		int negativeAntOdor = field.getOdor().getAnt() * (-1);
		field.dropOdor(new Odor(negativeAntOdor, 0, 0, 0)); // drop negative ant odor
		
		if (mode == 3) {
			
			for (int i=0; i<6; ++i)
				if (field.getNeighbour(i) != null)
					spreadNeutralizer(field.getNeighbour(i), mode-1, to, power-1);
			
		} else if (mode == 2) {
			
			if (field.getNeighbour(to) != null) // forward, inherit mode 2
				spreadNeutralizer(field.getNeighbour(to), mode, to, power-1);
			
			to = (to == 0) ? to = 5 : to - 1;
			if (field.getNeighbour(to) != null) // to the left, mode-1
				spreadNeutralizer(field.getNeighbour(to), mode-1, to, power-1);
			
		} else if (mode == 1) {
			
			if (field.getNeighbour(to) != null) // forward, inherit mode 1
				spreadNeutralizer(field.getNeighbour(to), mode, to, power-1);
			
		}
	}

}
