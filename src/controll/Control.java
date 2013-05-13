package controll;

import gameLogic.Game;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import java.awt.BorderLayout;
import java.awt.Point;

import display.Display;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.Action;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Control {

	private JFrame frame;
	
	private Display display;
	private Game game;
	private int chosenSprayIndex = 0;
	
	JMenuItem menuStartGame;
	JMenuItem menuPauseGame;
	JCheckBoxMenuItem checkMenuKillerSpray;
	JCheckBoxMenuItem checkMenuOdorneutralizer;
	
	private FileChooserFrame fcf = new FileChooserFrame();
	private final Action newGameAction = new NewGameStartAction();
	private final Action pauseGameAction = new PauseGameAction();
	private final Action killerSprayAction = new KillerSprayAction();
	private final Action odorNeutSprayAction = new OdorNeutSprayAction();

	/**
	 * Create the application.
	 */
	public Control(Game game, Display display) {
		this.game = game;
		this.display = display;
		this.game.setControl(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//frame.setResizable(false);
		frame.addKeyListener(new KeyAdapter() { // P pressed for pause
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getExtendedKeyCode() == KeyEvent.VK_P)
					pauseGameAction.actionPerformed(null);
			}
		});
		frame.setBounds(100, 100, 800, 600);
		frame.setTitle("AntTerror - stack_overlord");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menuGame = new JMenu("J\u00E1t\u00E9k");
		menuBar.add(menuGame);
		
		menuStartGame = new JMenuItem("J\u00E1t\u00E9k ind\u00EDt\u00E1sa");
		menuStartGame.setAction(newGameAction);
		menuGame.add(menuStartGame);
		
		menuPauseGame = new JMenuItem("Sz\u00FCneteltet\u00E9s (P)");
		menuPauseGame.setAction(pauseGameAction);
		menuPauseGame.setEnabled(false);
		menuGame.add(menuPauseGame);
		
		JMenu menuSpray = new JMenu("Spray");
		menuBar.add(menuSpray);
		
		checkMenuKillerSpray = new JCheckBoxMenuItem("Killerspray");
		checkMenuKillerSpray.setAction(killerSprayAction);
		checkMenuKillerSpray.setEnabled(false);
		menuSpray.add(checkMenuKillerSpray);
		
		checkMenuOdorneutralizer = new JCheckBoxMenuItem("OdorNeutralizerSpray");
		checkMenuOdorneutralizer.setAction(odorNeutSprayAction);
		checkMenuOdorneutralizer.setEnabled(false);
		menuSpray.add(checkMenuOdorneutralizer);
		
		this.display.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Point fieldIndex = calculateClickedField(arg0.getPoint());
				if (fieldIndex != null)
					game.sprayUsed(chosenSprayIndex, fieldIndex.x, fieldIndex.y); // spray used
			}
		});
		this.display.addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentResized(ComponentEvent e) {
		        display.repaint();
		    }
		});
		
		frame.getContentPane().add(this.display, BorderLayout.CENTER);
		
		this.frame.setVisible(true);
	}
	
	public void gameEnded() { // called by Game
		menuStartGame.setEnabled(true);
		menuPauseGame.setEnabled(false);
		checkMenuKillerSpray.setEnabled(false);
		checkMenuOdorneutralizer.setEnabled(false);
	}
	
	private Point calculateClickedField(Point p) {
		return this.display.calculateClickedField(p);
	}
	
	private class NewGameStartAction extends AbstractAction {
		private static final long serialVersionUID = -4420803407826511088L;
		
		public NewGameStartAction() {
			putValue(NAME, "Játék indítása");
			putValue(SHORT_DESCRIPTION, "");
		}
		
		public void actionPerformed(ActionEvent e) {
			int returnVal = fcf.fc.showOpenDialog(null);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fcf.fc.getSelectedFile();
				if (selectedFile.canRead()) {
					game.loadMap(selectedFile);
					Thread gameThread = new Thread(game); // game called here
					gameThread.start();
					
					// activate menu elements
					menuStartGame.setEnabled(false);
					menuPauseGame.setEnabled(true);
					checkMenuKillerSpray.setEnabled(true);
					checkMenuOdorneutralizer.setEnabled(true);
					checkMenuKillerSpray.setSelected(true);
					checkMenuOdorneutralizer.setSelected(false);
					
					fcf.setSavedPath(selectedFile); // whatever
				}
			}
		}
	}
	
	private class PauseGameAction extends AbstractAction {
		private static final long serialVersionUID = 4600588803440707244L;

		public PauseGameAction() {
			putValue(NAME, "Játék megszakítása");
			putValue(SHORT_DESCRIPTION, "");
		}
		
		public void actionPerformed(ActionEvent e) {
			if (game.switchPaused())
				putValue(NAME, "Játék folytatása");
			else
				putValue(NAME, "Játék megszakítása");
		}
	}
	
	private class KillerSprayAction extends AbstractAction {
		private static final long serialVersionUID = -7630949991616188176L;

		public KillerSprayAction() {
			putValue(NAME, "Killer spray");
			putValue(SHORT_DESCRIPTION, "Killer spray választása");
		}
		
		public void actionPerformed(ActionEvent e) {
			if (checkMenuKillerSpray.isSelected()) {
				chosenSprayIndex = 0;
				checkMenuOdorneutralizer.setSelected(false);
			} else {
				chosenSprayIndex = 1;
				checkMenuOdorneutralizer.setSelected(true);
			}
		}
	}
	private class OdorNeutSprayAction extends AbstractAction {
		private static final long serialVersionUID = -185896066128093438L;
		
		public OdorNeutSprayAction() {
			putValue(NAME, "OdorNeutralizer spray");
			putValue(SHORT_DESCRIPTION, "OdorNeutralizer spray választása");
		}
		
		public void actionPerformed(ActionEvent e) {
			if (checkMenuOdorneutralizer.isSelected()) {
				chosenSprayIndex = 1;
				checkMenuKillerSpray.setSelected(false);
			} else {
				chosenSprayIndex = 0;
				checkMenuKillerSpray.setSelected(true);
			}
		}
	}
}
