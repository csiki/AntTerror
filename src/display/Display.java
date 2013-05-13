package display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import javax.swing.JPanel;
import gameLogic.Map;
import gameLogic.Spray;

public class Display extends JPanel implements DisplayRefreshable { // ready
	
	private static final long serialVersionUID = -4171952854064152174L;
	
	private int state = 0;
	private MapDisplay mapD;
	private VoidDisplay voidD = new VoidDisplay();
	private ScoreDisplay scoreD;
	
	@Override
	public void paintComponent(Graphics g) {
		// clear all
		Graphics2D g2=(Graphics2D) g;
		g2.clearRect(0, 0, this.getSize().width, this.getSize().height);
		
		if (state == 1 && this.mapD != null)
			this.mapD.display(g, 20, 20);
		else if (state == 2)
			this.scoreD.display(g, 150, 100);
		else
			this.voidD.display(g, 300, 200);
	}
	
	public void displayComponent(int state) {
		this.state = state;
		this.repaint();
	}
	
	public Point calculateClickedField(Point p) {
		p.x = p.x - 20; // from start point (0,0)
		p.y = p.y - 20;
		
		if (this.mapD != null)
			return this.mapD.calculateClickedField(p);
		
		return null;
	}
	
	public void refreshMapElements() {
		this.displayComponent(1);
	}
	
	public void provideMapData(Map m, List<Spray> sprays) {
		this.mapD = new MapDisplay(this.getSize(), m, sprays);
		this.displayComponent(1);
	}
	
	public void gameEnded(long round) {
		this.scoreD = new ScoreDisplay(round);
		this.displayComponent(2);
	}
}
