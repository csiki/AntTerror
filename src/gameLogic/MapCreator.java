package gameLogic;

import java.util.*;

public class MapCreator {

	public Map createMap(int width, int height, int difficulty) {
		
		// whatever the difficulty is
		Field[][] fields = new Field[height][width];
		
		for (int r=0; r<height; ++r)
			for (int c=0; c<width; ++ c)
				fields[r][c] = new Field();
		
		Map map = new Map(width, height, fields[0][0]);
		
		// connect fields
		for (int r=0; r<height; ++r) {
			for (int c=0; c<width; ++ c) {
				
			}		
		}
		
		return map;
	}

}
