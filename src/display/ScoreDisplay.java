package display;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

public class ScoreDisplay {
	
	private long score = 0;
	
	ScoreDisplay(long score) {
		this.score = score;
	}
	
	public void display(Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D)g;
		
		// paint string
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Serif", Font.PLAIN, 60);
		g2.setFont(font);

		g2.drawString("Pontsz�m: " + score, x, y);

		g2.drawString("K�sz�t�k:", x+100, y+100);
		Line2D lin = new Line2D.Float(x+100, y+105, x+312, y+105);
		g2.draw(lin);
		font = new Font("Calibri", Font.PLAIN, 45);
		g2.setFont(font);
		g2.drawString(" - Mark� L�szl�", x+100, y+150);
		g2.drawString(" - Kov�cs Bal�zs", x+100, y+200);
		g2.drawString(" - Wagner �kos", x+100, y+250);
		g2.drawString(" - B�tor Andr�s", x+100, y+300);
		g2.drawString(" - T�th Viktor", x+100, y+350);
	}
	
}
