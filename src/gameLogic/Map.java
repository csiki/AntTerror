package gameLogic;

import java.util.*;

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

public class Map {

	private int width;
	private int height;
	private Field base; // TODO új attibrútum
	private List<Field> fields = new ArrayList<Field>();
	
	Map(int width, int height, Field base) {
		this.width = width;
		this.height = height;
		this.base = base;
	}
	
	public void addField(Field field) {
		this.fields.add(field);
	}

	public Field getField(int x, int y) {

		if (x >= this.width || y >= this.height)
			return null;
		
		// TODO döntsétek el, hogy történjen a teszt és hogyan érjünk el egy fieldet koordináta alapján !
		
		return null;
	}

	public void decreaseAntOdor() {
		
		for (Field f : this.fields)
			f.decreaseAntOdor();
		
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

}
