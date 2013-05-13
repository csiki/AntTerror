package display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class VoidDisplay {
	public void display(Graphics g, int x, int y) {
		try {
			Image img = ImageIO.read(new File("img/stack.png"));
			Graphics2D g2 = (Graphics2D)g;
			g2.drawImage(img, x, y, img.getWidth(null), img.getHeight(null), 0, 0, img.getWidth(null), img.getHeight(null), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}