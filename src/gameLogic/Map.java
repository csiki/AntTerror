package gameLogic;


/******
	
	b * * * * * 				 b * * * * *		b * * * * *
	 a * * * * *	 ==>		<-a * * * * *	=	a * * * * *
	* * c * * *					 * * c * * *		* * c * * *
	 * * * * * *				<-* * * * * *		* * * * * *
	 
	 b(0,0) = base
	 a(0,1)
	 c(2,2)
	 
	 c szomszédai sorban: 	(1,1) 	(2,1) 	(3,2) 	(2,3) 	(1,3) 	(1,2)
	 			relatíve:	(-1,-1) (0,-1)	(1,0)	(0,1)	(1,-1)	(-1,0)
	 
 ******/

public class Map { // ready

	private int width;
	private int height;
	//private Field base; // TODO új attibrútum
	//private List<Field> fields = new ArrayList<Field>();
	private Field[][] fields = null;
	
	Map(int width, int height) {
		this.width = width;
		this.height = height;
		fields = new Field[height][width];
		for (int r=0; r<height; ++r){
			for (int c=0; c<width; ++ c){
				fields[r][c] = new Field();
			}
		}
		for (int r=0; r<height; ++r){
			for (int c=0; c<width; ++ c){
				if(r%2==0){
					fields[r][c].addNeighbour(this.getField(r-1, c-1), 0);
					fields[r][c].addNeighbour(this.getField(r-1, c), 1);
					fields[r][c].addNeighbour(this.getField(r, c+1), 2);
					fields[r][c].addNeighbour(this.getField(r+1, c), 3);
					fields[r][c].addNeighbour(this.getField(r+1, c-1), 4);
					fields[r][c].addNeighbour(this.getField(r, c-1), 5);
				}
				else{
					fields[r][c].addNeighbour(this.getField(r-1, c), 0);
					fields[r][c].addNeighbour(this.getField(r-1, c+1), 1);
					fields[r][c].addNeighbour(this.getField(r, c+1), 2);
					fields[r][c].addNeighbour(this.getField(r+1, c+1), 3);
					fields[r][c].addNeighbour(this.getField(r+1, c), 4);
					fields[r][c].addNeighbour(this.getField(r, c-1), 5);
				}
			}
		}
				
	}
	
	public void addField(Field field) {
		//this.fields.add(field);
	}

	public Field getField(int x, int y) {
		
		if (y >= this.width || x >= this.height || x < 0 || y < 0)
		{
			return null;
		}
		else
		{
			return fields[x][y];
		}
		/*
		if (x >= this.width || y >= this.height || x < 0 || y < 0)
			return null;
		
		Field current = this.base;
		
		// step down
		for (int row=0; row<x; ++row) {
			if (row % 2 == 0)
				current = current.getNeighbour(3);
			else
				current = current.getNeighbour(4);
		}
		
		// step right
		for (int col=0; col<y; ++col)
			current = current.getNeighbour(2);
		
		return current;*/
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
