package gameLogic;


public class Map {

	private int width;
	private int height;
	private Field[][] fields = null;
	
	Map(int width, int height, Field[][] fields) {
		this.width = width;
		this.height = height;
		this.fields = fields;
	}

	public Field getField(int x, int y) {
		
		if (x >= this.width || y >= this.height || x < 0 || y < 0) {
			return null;
		} else {
			return fields[y][x];
		}
	}

	public void decreaseAntOdor() {
		
		for (int r=0; r<height; ++r)
			for (int c=0; c<width; ++ c)
				fields[r][c].decreaseAntOdor();
		
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

}
