package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.List;

import gameLogic.Map;
import gameLogic.Spray;

public class MapDisplay {
	
	private MapElementDisplay[][] elements;
	private Map map;
	private List<Spray> sprays;
	private Dimension displaySize;
	private Dimension mapSize = new Dimension(0,0);
	
	MapDisplay(Dimension displaySize, Map map, List<Spray> sprays) {
		this.map = map;
		this.sprays = sprays;
		this.displaySize = displaySize;
		
		elements = new MapElementDisplay[map.getHeight()][map.getWidth()];
		for (int y=0; y<map.getHeight(); ++y)
			for (int x=0; x<map.getWidth(); ++x)
				elements[y][x] = null;
	}
	
	public void display(Graphics g, int x, int y) {
		this.mapSize.width = this.displaySize.width - 2*x; // x - right and left margin
		this.mapSize.height = this.displaySize.height - 2*y; // y - top and bottom margin
		
		// calc map element size
		int blockSize = this.mapSize.width/map.getWidth() > this.mapSize.height/map.getHeight() ? this.mapSize.height/map.getHeight() : this.mapSize.width/map.getWidth(); // min {mapWidth/map.getWidth(), mapHeight/map.getHeight()}
		MapElementDisplay.setSize(blockSize);
		
		// draw map
		int origiX = x;
		for (int r=0; r<map.getHeight(); ++r) {
			x = origiX;
			
			if (r % 2 == 1) // minden páratlan sor egy lökéssel beljebb
				x += blockSize / 2;
			
			for (int c=0; c<map.getWidth(); ++c) {
				if (map.getField(c, r).getItem() != null)
					map.getField(c, r).getItem().getDisplay().display(g, x, y);
				else
					EmptyDisplay.getInstance().display(g, x, y);
				x += blockSize;
			}
			
			y += blockSize;
		}
		
		// draw sprays and charges
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Serif", Font.PLAIN, 40);
		g2.setFont(font);
		
		Integer charge = this.sprays.get(0).getCharge();
		g2.setColor(Color.red);
		g2.drawString(charge.toString(), this.displaySize.width - 150, this.displaySize.height - 50);
		
		charge = this.sprays.get(1).getCharge();
		g2.setColor(Color.green);
		g2.drawString(charge.toString(), this.displaySize.width - 70, this.displaySize.height - 50);
	}
	
	public Point calculateClickedField(Point p) {
		if (p.x < 0 || p.y < 0 || p.x > this.mapSize.width || p.y > this.mapSize.height)
			return null;
		
		int y = p.y / MapElementDisplay.getSize();
		if (y % 2 == 1) // páratlan soroknál beljebb tolodik a click
			p.x -= MapElementDisplay.getSize() / 2;
		int x = p.x / MapElementDisplay.getSize();
		
		return new Point(x,y);
	}
}

