package gameLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class MapCreator {

	public Map createMap(File source, List<Item> addedItems, Spawner spawner) {
		
		Field[][] fields = null;
		int width = 0;
		int height = 0;
		Scanner scanner = null;
		
		// scan width and height of map
		try {
			scanner = new Scanner(source);
			width = scanner.nextInt();
			height = scanner.nextInt();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// allocate fields
		fields = new Field[height][width];
		for (int r=0; r<height; ++r){
			for (int c=0; c<width; ++ c){
				fields[r][c] = new Field();
			}
		}
		
		// link neighbours
		for (int r=0; r<height; ++r) {
			for (int c=0; c<width; ++c) {
				if(r%2==0) {
					fields[r][c].addNeighbour(this.getField(fields, width, height, c-1, r-1), 0);
					fields[r][c].addNeighbour(this.getField(fields, width, height, c, r-1), 1);
					fields[r][c].addNeighbour(this.getField(fields, width, height, c+1, r), 2);
					fields[r][c].addNeighbour(this.getField(fields, width, height, c, r+1), 3);
					fields[r][c].addNeighbour(this.getField(fields, width, height, c-1, r+1), 4);
					fields[r][c].addNeighbour(this.getField(fields, width, height, c-1, r), 5);
				}
				else{
					fields[r][c].addNeighbour(this.getField(fields, width, height, c, r-1), 0);
					fields[r][c].addNeighbour(this.getField(fields, width, height, c+1, r-1), 1);
					fields[r][c].addNeighbour(this.getField(fields, width, height, c+1, r), 2);
					fields[r][c].addNeighbour(this.getField(fields, width, height, c+1, r+1), 3);
					fields[r][c].addNeighbour(this.getField(fields, width, height, c, r+1), 4);
					fields[r][c].addNeighbour(this.getField(fields, width, height, c-1, r), 5);
				}
			}
		}
		
		// fill fields with items
		int temp;
		Item itemToAdd;
		for (int r=0; r<height; ++r){
			for (int c=0; c<width; ++c){
				if (scanner.hasNext()) {
					temp = scanner.nextInt();
					
					itemToAdd = null;
					
					if (temp == 1) // Ant
						itemToAdd = new Ant(fields[r][c]);
					else if (temp == 2) // AntEater
						itemToAdd = new AntEater(fields[r][c]);
					else if (temp == 3) // AntEaterColony
						itemToAdd = new AntEaterColony(fields[r][c], spawner);
					else if (temp == 4) // AntHill
						itemToAdd = new AntHill(fields[r][c], spawner);
					else if (temp == 5) // AntLion
						itemToAdd = new AntLion(fields[r][c]);
					else if (temp == 6) // Barrier
						itemToAdd = new Barrier(fields[r][c]);
					else if (temp == 7) // Food
						itemToAdd = new Food(fields[r][c]);
					else if (temp == 8) // Stone
						itemToAdd = new Stone(fields[r][c]);
					
					if (itemToAdd != null) {
						fields[r][c].register(itemToAdd);
						addedItems.add(itemToAdd);
					}
				}
			}
		}
		
		return new Map(width, height, fields);
	}
	
	private Field getField(Field[][] fields, int width, int height, int x, int y) {
		
		if (x >= width || y >= height || x < 0 || y < 0) {
			return null;
		} else {
			return fields[y][x];
		}
	}
	
}
