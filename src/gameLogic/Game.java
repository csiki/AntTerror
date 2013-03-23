package gameLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Game {

	private long round;
	private boolean paused;
	private int speed;
	protected List<Spray> sprays;
	protected Map map;
	protected MapCreator mapCreator;
	protected List<Item> items;

	public void startGame() {
		// TODO
	}
	
	public void skeletonTest() {
		int eset;
		
		System.out.println("Skeleton test - stack_overlord");
		
		while (true) {
			eset = 0;
			System.out.print("\nTesztesetek 1-tõl 16-ig: ");
			
			try {
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				eset = Integer.parseInt(bf.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			switch (eset) {
				case 1:
					hangyaHangya();
					break;
				case 2:
					hangyaHangyaszSun();
					break;
				case 3:
					hangyaHangyaszKolonia();
					break;
				case 4:
					hangyaHangyaBoy();
					break;
				case 5:
					hangyaHangyaleso();
					break;
				case 6:
					hangyaAkadaly();
					break;
				case 7:
					hangyaEtel();
					break;
				case 8:
					hangyaUresMezo();
					break;
				case 9:
					hsunHangya();
					break;
				case 10:
					hsunHsun();
					break;
				case 11:
					hsunKolonia();
					break;
				case 12:
					hsunHangyaboy();
					break;
				case 13:
					hsunHangyaleso();
					break;
				case 14:
					hsunAkadaj();
					break;
				case 15:
					hsunEtel();
					break;
				case 16:
					hsunUres();
					break;
				default:
					System.out.println("Invalid teszteset!");
			}
		}
	}
	
	private void hangyaHangya() {
		Field f1 = new Field();
		Field f2 = new Field();
		Ant ant = new Ant(f1);
		Ant ant2 = new Ant(f2);
		
		f1.register(ant);
		f2.register(ant2);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		ant.act();
	}
	
	private void hangyaHangyaszSun() {
		Field f1 = new Field();
		Field f2 = new Field();
		Ant ant = new Ant(f1);
		AntEater ae = new AntEater(f2);
		
		f1.register(ant);
		f2.register(ae);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		ant.act();
	}
	
	private void hangyaHangyaszKolonia() {
		Field f1 = new Field();
		Field f2 = new Field();
		Ant ant = new Ant(f1);
		AntEaterColony ac = new AntEaterColony(f2);
		
		f1.register(ant);
		f2.register(ac);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		ant.act();
	}
	
	private void hangyaHangyaBoy() {
		Field f1 = new Field();
		Field f2 = new Field();
		Ant ant = new Ant(f1);
		AntHill ah = new AntHill(f2);
		
		f1.register(ant);
		f2.register(ah);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		ant.act();
	}
	
	private void hangyaHangyaleso() {
		Field f1 = new Field();
		Field f2 = new Field();
		Ant ant = new Ant(f1);
		AntLion al = new AntLion(f2);
		
		f1.register(ant);
		f2.register(al);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		ant.act();
	}
	
	private void hangyaAkadaly() {
		Field f1 = new Field();
		Field f2 = new Field();
		Ant ant = new Ant(f1);
		Barrier b = new Barrier(f2);
		
		f1.register(ant);
		f2.register(b);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		ant.act();
	}
	
	private void hangyaEtel() {
		Field f1 = new Field();
		Field f2 = new Field();
		Ant ant = new Ant(f1);
		Food food = new Food(f2);
		
		f1.register(ant);
		f2.register(food);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		ant.act();
	}
	
	private void hangyaUresMezo() {
		Field f1 = new Field();
		Field f2 = new Field();
		Ant ant = new Ant(f1);
		
		f1.register(ant);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		ant.act();
	}
	
	private void hsunHangya() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		Ant a = new Ant(f2);
		
		f1.register(antEater);
		f2.register(a);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		antEater.act();
	}
	
	private void hsunHsun() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		AntEater ae = new AntEater(f2);
		
		f1.register(antEater);
		f2.register(ae);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		antEater.act();
	}
	
	private void hsunKolonia() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		AntEaterColony ac = new AntEaterColony(f2);
		
		f1.register(antEater);
		f2.register(ac);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		antEater.act();
	}
	
	private void hsunHangyaboy() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		AntHill ah = new AntHill(f2);
		
		f1.register(antEater);
		f2.register(ah);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		antEater.act();
	}
	
	private void hsunHangyaleso() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		AntLion al = new AntLion(f2);
		
		f1.register(antEater);
		f2.register(al);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		antEater.act();
	}
	
	private void hsunAkadaj() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		Barrier b = new Barrier(f2);
		
		f1.register(antEater);
		f2.register(b);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		antEater.act();
	}
	
	private void hsunEtel() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		Food food = new Food(f2);
		
		f1.register(antEater);
		f2.register(food);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		antEater.act();
	}
	
	private void hsunUres() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		
		f1.register(antEater);
		
		f1.addNeighbour(f2);
		f2.addNeighbour(f1);
		
		antEater.act();
	}
}
