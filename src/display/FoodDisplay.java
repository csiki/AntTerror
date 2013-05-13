package display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FoodDisplay extends MapElementDisplay {

	private static final FoodDisplay instance = new FoodDisplay();
	private Image img = null;
	
	private FoodDisplay() {
		try {
			img = ImageIO.read(new File("img/food.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static FoodDisplay getInstance() {
		return instance;
	}
	
	@Override
	public void display(Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(img, x, y, x+MapElementDisplay.getSize(), y+MapElementDisplay.getSize(), 0, 0, 100, 100, null);
	}

}
