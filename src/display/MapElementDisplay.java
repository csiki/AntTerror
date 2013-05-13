package display;

import java.awt.Graphics;

public abstract class MapElementDisplay {
	private static int size = 30; // in px
	public abstract void display(Graphics g, int x, int y);
	
	public static void setSize(int set) {
		size = set;
	}
	
	public static int getSize() {
		return size;
	}
}
