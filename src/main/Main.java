package main;

import java.awt.EventQueue;
import display.Display;
import gameLogic.Game;
import controll.Control;

public class Main {

	public static void main(String[] args) {
		
		final Display display = new Display();
		final Game game = new Game(display);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Control control = new Control(game, display);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
