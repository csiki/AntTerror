package gameLogic;

import java.io.File;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import display.DisplayRefreshable;

public class Game implements Runnable, Spawner {

	private long round;
	volatile private boolean paused;
	volatile private int speed;
	private List<Spray> sprays;
	private Map map;
	private MapCreator mapCreator;
	private List<Item> items;
	private DisplayRefreshable display;
	
	public Game(DisplayRefreshable display) {
		this.display = display;
		round = 0;
		paused = false;
		speed = 15;
		mapCreator = new MapCreator();
		map = null;
		sprays = new ArrayList<Spray>(2);
		items = new CopyOnWriteArrayList<Item>();
	}
	
	@Override
	public void run() {
		this.display.provideMapData(this.map, this.sprays);
		
		while (!this.isGameEnded()) {
			
			this.actItems();
			this.removeDeadItems();
			map.decreaseAntOdor();

			this.round++;
			
			this.display.refreshMapElements();
			this.waitTillNextRound();
		}
		
		this.display.gameEnded(this.round);
	}
	
	public void loadMap(File mapSource) {
		this.map = this.mapCreator.createMap(mapSource, this.items, this);
		sprays.add(new KillerSpray(10, 5));
		sprays.add(new OdorNeutralizerSpray(10, 5));
	}
	
	public boolean switchPaused() {
		this.paused = !this.paused;
		return this.paused;
	}
	
	public void sprayUsed(int sprayIndex, int x, int y) {
		if (sprayIndex < this.sprays.size())
			this.sprays.get(sprayIndex).mechanism(this.map.getField(x, y));
	}
	
	private void removeDeadItems() {
		Iterator<Item> it = this.items.iterator();
		List<Item> toRemove = new ArrayList<Item>();
		Item next;
		
		while(it.hasNext()) {
			next = it.next();
			if (next.getField() == null)
				toRemove.add(next);
		}
		
		this.items.removeAll(toRemove);
	}
	
	private boolean isGameEnded() {
		
		for (Item i : this.items)
			if (i.isThereAnyFood())
				return false;
		
		return true;
	}

	private void actItems() {
		Iterator<Item> it = this.items.iterator();
		while(it.hasNext())
			it.next().act();
	}

	private void waitTillNextRound() {
		
		// roundtime
		try {
			Thread.sleep(1000 / speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// pause
		while (this.paused) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void spawnItem(Item item) {
		this.items.add(item);
	}
}
