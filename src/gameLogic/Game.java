package gameLogic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Game {

	private long round;
	volatile private boolean paused;
	volatile private int speed;
	protected List<Spray> sprays; //!! minek ennek lista k�t f�le van ? ezzel csak a sz�v�s lesz, k�zben megoldottam de akkor is bazd meg
	protected Map map;
	protected MapCreator mapCreator;
	protected List<Item> items;
	
	public Game(){
		round = 0;
		paused = false;
		speed = 0;
		mapCreator = new MapCreator();
		map = null;
		sprays = new ArrayList<Spray>(2);
		items = null;
	}

	public void startGame() {
		
		this.map = this.mapCreator.createMap(100, 100, 0);
		sprays.add(new KillerSpray(10, 5)); // TODO ezeket a konstansokat globalba !
		sprays.add(new OdorNeutralizerSpray(10, 5)); // TODO ezeket a konstansokat globalba !
		
		while (!this.isGameEnded()) {
			
			this.actItems();
			map.decreaseAntOdor();
			
			this.round++;
			
			this.waitTillNextRound();
		}
		
	}
	
	private boolean isGameEnded() { // TODO �j fv�ny!
		
		// TODO kital�lni
		
		return false;
	}
	
	private void actItems(){
		for (Item i : items)
			i.act();
	}
	
	private void waitTillNextRound() {
		
		// pause
		while (this.paused);
		
		// roundtime
		try {
			Thread.sleep(1000 / speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void protoTest() {
		//A feladatki�r�s szerint a protot�pus c�lja, hogy bemutassa,
		//a program elk�sz�lt, helyesen m�k�dik �s minden feladat�nak elv�gz�s�re k�pes.
		//Ez a v�ltozat egy elk�sz�lt program csak a grafikus interf�sz hi�nyzik bel�le,
		//tervez�si szempontb�l k�sznek tekinthet�, valamennyi met�dusa m�r a v�gleges algoritmusokat hajtja v�gre. 
		//A protot�pus vez�rl�se konzolon kereszt�l fog t�rt�nni, a tesztel�shez el�re meghat�rozott teszteseteket k�sz�t�nk.	
		//A protot�pus a szabv�nyos be- �s kimeneten kereszt�l kommunik�l a felhaszn�l�val.
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		List<Item> actitems = new ArrayList<Item>();
		File file = null;
		BufferedWriter out = null;
		String nl = System.getProperty("line.separator");
		boolean exit = false; 
		try {
			while (!exit) {
				String[] comm = bf.readLine().split(" ");
				if (comm[0].equals("newTest")) {
					//newTest <tesztmegnevez�se>
					//Le�r�s: �j tesztet ind�t. Kimenetet ki�rja <tesztmegnevez�se>.txt-be.
					//Opci�k: teszt megnevez�se
					
					map = null;
					sprays = new ArrayList<Spray>(2);
					items = null;
					actitems = new ArrayList<Item>();
					
					//f�jlba �r�s nem vizsg�lja hogy l�tezik-e m�r ilyen nev�
					if(out==null){
						file = new File(System.getProperty("user.dir")+"\\prototest\\"+comm[1]+".txt");
						file.createNewFile();
						out = new BufferedWriter(new FileWriter(file));
					}
					else{
						out.close();
						file = new File(System.getProperty("user.dir")+"\\prototest\\"+comm[1]+".txt");
						file.createNewFile();
						out = new BufferedWriter(new FileWriter(file));
					}
				}
				else if (comm[0].equals("createMap")) {
					//createMap <width> <height>
					//Le�r�s: Csin�l egy <width> x<height> m�ret� p�ly�t
					//Opci�k: oszlopok sz�ma, sorok sz�ma

					map = mapCreator.createMap(Integer.parseInt(comm[1]), Integer.parseInt(comm[2]), 0);
				}
				else if (comm[0].equals("createKillerSpray")) {
					//createKillerSpray <charge> <radian>
					//Le�r�s: Hangya�l� spray l�trehoz�sa,
					//meglehet adni a t�lt�tts�get �s a hat�t�vols�got
					//Opci�k: t�lt�tts�g, hat�t�vols�g

					sprays.add(0, new KillerSpray(Integer.parseInt(comm[1]), Integer.parseInt(comm[2])));
				}
				else if (comm[0].equals("createOdorSpray")) {
					//createOdorSpray <charge> <radian>
					//Le�r�s: Hangyaszag semleges�t� spray l�trehoz�sa,
					//meglehet adni a t�lt�tts�get �s a hat�t�vols�got
					//Opci�k: t�lt�tts�g, hat�t�vols�g

					sprays.add(1,new OdorNeutralizerSpray(Integer.parseInt(comm[1]), Integer.parseInt(comm[2])));
				}
				else if (comm[0].equals("addAnt")) {
					//addAnt <row> <column> <carry>
					//Le�r�s: Csin�l egy hangy�t az adott mez�re,
					//megadhat� hogy cipel-e �telt
					//Opci�k: sorsz�m, oszlopsz�m, 0-1
					//hangya carriedItem carry adattagja public lett a protoban
					
					Ant ant = new Ant(map.getField(Integer.parseInt(comm[1]), Integer.parseInt(comm[2]) ));
					if(comm[3]=="1"){
						Field field = new Field();
						Food food = new Food(field);
						field.deregister();
						ant.carriedItem = food;
						ant.carry = true;
					};
					items.add(ant);
					actitems.add(ant);
				}
				else if (comm[0].equals("addAntEater")) {
					//addAntEater <row> <column> <hunger>
					//Le�r�s: Csin�l egy hangy�szs�nt az adott mez�re,
					//megadhat� hogy mennyire �hes
					//Opci�k: sorsz�m, oszlopsz�m, 0-1-2-3
					//hangy�sz hunger adattagja public lett a protoban
					
					AntEater anteater = new AntEater(map.getField(Integer.parseInt(comm[1]), Integer.parseInt(comm[2]) ));
					anteater.hunger= Integer.parseInt(comm[3]);
					items.add(anteater);
					actitems.add(anteater);
				}
				else if (comm[0].equals("addFood")) {
					//addFood <row> <column>
					//Le�r�s: Csin�l egy �telt az adott mez�re
					//Opci�k: sorsz�m, oszlopsz�m
					
					Food food = new Food(map.getField(Integer.parseInt(comm[1]), Integer.parseInt(comm[2]) ));
					items.add(food);
					actitems.add(food);
				}
				else if (comm[0].equals("addStone")) {
					//addStone <row> <column>
					//Le�r�s: Csin�l egy k�vet az adott mez�re
					//Opci�k: sorsz�m, oszlopsz�m
					
					Stone stone = new Stone(map.getField(Integer.parseInt(comm[1]), Integer.parseInt(comm[2]) ));
					items.add(stone);
					actitems.add(stone);
				}
				else if (comm[0].equals("addBarrier")) {
					//addBarrier <row> <column>
					//Le�r�s: Csin�l egy akad�lyt az adott mez�re
					//Opci�k: sorsz�m, oszlopsz�m
					
					Barrier barrier = new Barrier(map.getField(Integer.parseInt(comm[1]), Integer.parseInt(comm[2]) ));
					items.add(barrier);
					actitems.add(barrier);
				}
				else if (comm[0].equals("addAntLion")) {
					//addAntLion <row> <column>  
					//Le�r�s: Csin�l egy hangyales�t az adott mez�re
					//Opci�k: sorsz�m, oszlopsz�m
					
					AntLion antlion = new AntLion(map.getField(Integer.parseInt(comm[1]), Integer.parseInt(comm[2]) ));
					items.add(antlion);
					actitems.add(antlion);
				}
				else if (comm[0].equals("addAntHill")) {
					//addAntHill <row> <column> <passive>
					//Le�r�s: Csin�l egy hangyabolyt az adott mez�re,
					//megadhat� hogy ne dobjon ki �j hangy�t, teh�t ne l�pjen semelyik k�rben
					//Opci�k: sorsz�m, oszlopsz�m, 0-1
					
					AntHill anthill = new AntHill(map.getField(Integer.parseInt(comm[1]), Integer.parseInt(comm[2]) ));
					items.add(anthill);
					if(comm[3]=="0"){
						actitems.add(anthill);
					}
				}
				else if (comm[0].equals("addAntEaterColony")) {
					//addAntEaterColony <row> <column> <passive>
					//Le�r�s: Csin�l egy hangy�szs�n kol�ni�t az adott mez�re,
					//megadhat� hogy ne dobjon ki �j hangy�szt, teh�t ne l�pjen semelyik k�rben
					//Opci�k: sorsz�m, oszlopsz�m, 0-1
					
					AntEaterColony colony = new AntEaterColony(map.getField(Integer.parseInt(comm[1]), Integer.parseInt(comm[2]) ));
					items.add(colony);
					if(comm[3]=="0"){
						actitems.add(colony);
					}
				}
				else if (comm[0].equals("setAntOdor")) {
					//setAntOdor <row> <column> <antodor>
					//Le�r�s: Be�ll�tja az adott mez�n a hangyaszagot*
					//Opci�k: sorsz�m, oszlopsz�m, 0-1-2-3-4
					
					//*antodor public legyen a protoban

					map.getField(Integer.parseInt(comm[1]), Integer.parseInt(comm[2])).getOdor().ant = Integer.parseInt(comm[3]);
				}
				else if (comm[0].equals("actAllItem")) {
					//actAllItem 
					//Le�r�s: L�pteti az �sszes elemet a map-on, valamilyen sorrend szerint
					//Opci�k: -
					
					for (Iterator<Item> iterator = actitems.iterator(); iterator
							.hasNext();) {
						Item item = (Item) iterator.next();
						item.act();
					}
				}
				else if (comm[0].equals("actItem")) {
					//actItem <row> <column>
					//Le�r�s: L�pteti az adott elemet a map-on
					//Opci�k: sorsz�m, oszlopsz�m

					map.getField(Integer.parseInt(comm[1]), Integer.parseInt(comm[2])).item.act(); 
					//!!h�t ezt nem tudom mire v�lni field-ben mi�rt protected az item,
					//!!ha m�gis private akkor itt castolni kell, nem sz�p de itt elmegy
				}
				else if (comm[0].equals("showMap")) {
					//showMap <map>
					//Le�r�s: Ki�rja a map tartalm�t, lehet v�lasztani az Item-ek 
					//�s n�gy fajta Odor k�z�tt
					//Opci�k: item-antodor-colonyodor-foododor-hillodor

					//!!ezt nem �rom meg mert ehhez kell a createMap
					//!! itt �rdemes lenne leellen�rizni hogy a megfelel� mez�k egym�s szomsz�dai-e ha nem akkor valami �zenet
					//!! egy�bk�nt meg a map az nem nagy cucc
					;
				}
				else if (comm[0].equals("useKillerSpray")) {
					//useKillerSpray <row> <column>
					//Le�r�s: Hangya�l� spray haszn�lata az adott mez� k�z�ppontj�b�l
					//Opci�k: sorsz�m, oszlopsz�m
					
					if(sprays.get(0).getCharge()==0){
						System.out.println("KillerSpray is empty");
						if(out!=null){
							out.write("KillerSpray is empty"+nl);
						}
					}
					else{
						sprays.get(0).mechanism(map.getField(Integer.parseInt(comm[1]), Integer.parseInt(comm[2])));
						System.out.println("KillerSpray charge "+sprays.get(0).getCharge());
						if(out!=null){
							out.write("KillerSpray charge "+sprays.get(0).getCharge()+nl);
						}
					}
				}
				else if (comm[0].equals("useOdorSpray")) {
					//useOdorSpray <row> <column>
					//Le�r�s: Hangyaszag semleges�t� spray haszn�lata az adott mez� k�z�ppontj�b�l
					//Opci�k: sorsz�m, oszlopsz�

					if(sprays.get(1).getCharge()==0){
						System.out.println("OdorSpray is empty");
						if(out!=null){
							out.write("OdorSpray is empty"+nl);
						}
					}
					else{
						sprays.get(1).mechanism(map.getField(Integer.parseInt(comm[1]), Integer.parseInt(comm[2])));
						System.out.println("odorSpray charge "+sprays.get(1).getCharge());
						if(out!=null){
							out.write("OdorSpray charge "+sprays.get(1).getCharge()+nl);
						}
					}
				}
				else if (comm[0].equals("exit")) {
					//exit
					//Le�r�s: kil�p�s a programb�l
					//Opci�k: -
					
					if(out!=null){
						out.close();
					}
					exit = true;
				}
				else {
					System.out.println("Hib�s parancs");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void skeletonTest() {
		int eset;
		
		System.out.println("Skeleton test - stack_overlord");
		
		while (true) {
			eset = 0;
			System.out.print("\nTesztesetek 1-t�l 16-ig: ");
			
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
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		ant.act();
	}
	
	private void hangyaHangyaszSun() {
		Field f1 = new Field();
		Field f2 = new Field();
		Ant ant = new Ant(f1);
		AntEater ae = new AntEater(f2);
		
		f1.register(ant);
		f2.register(ae);
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		ant.act();
	}
	
	private void hangyaHangyaszKolonia() {
		Field f1 = new Field();
		Field f2 = new Field();
		Ant ant = new Ant(f1);
		AntEaterColony ac = new AntEaterColony(f2);
		
		f1.register(ant);
		f2.register(ac);
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		ant.act();
	}
	
	private void hangyaHangyaBoy() {
		Field f1 = new Field();
		Field f2 = new Field();
		Ant ant = new Ant(f1);
		AntHill ah = new AntHill(f2);
		
		f1.register(ant);
		f2.register(ah);
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		ant.act();
	}
	
	private void hangyaHangyaleso() {
		Field f1 = new Field();
		Field f2 = new Field();
		Ant ant = new Ant(f1);
		AntLion al = new AntLion(f2);
		
		f1.register(ant);
		f2.register(al);
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		ant.act();
	}
	
	private void hangyaAkadaly() {
		Field f1 = new Field();
		Field f2 = new Field();
		Ant ant = new Ant(f1);
		Barrier b = new Barrier(f2);
		
		f1.register(ant);
		f2.register(b);
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		ant.act();
	}
	
	private void hangyaEtel() {
		Field f1 = new Field();
		Field f2 = new Field();
		Ant ant = new Ant(f1);
		Food food = new Food(f2);
		
		f1.register(ant);
		f2.register(food);
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		ant.act();
	}
	
	private void hangyaUresMezo() {
		Field f1 = new Field();
		Field f2 = new Field();
		Ant ant = new Ant(f1);
		
		f1.register(ant);
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		ant.act();
	}
	
	private void hsunHangya() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		Ant a = new Ant(f2);
		
		f1.register(antEater);
		f2.register(a);
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		antEater.act();
	}
	
	private void hsunHsun() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		AntEater ae = new AntEater(f2);
		
		f1.register(antEater);
		f2.register(ae);
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		antEater.act();
	}
	
	private void hsunKolonia() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		AntEaterColony ac = new AntEaterColony(f2);
		
		f1.register(antEater);
		f2.register(ac);
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		antEater.act();
	}
	
	private void hsunHangyaboy() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		AntHill ah = new AntHill(f2);
		
		f1.register(antEater);
		f2.register(ah);
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		antEater.act();
	}
	
	private void hsunHangyaleso() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		AntLion al = new AntLion(f2);
		
		f1.register(antEater);
		f2.register(al);
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		antEater.act();
	}
	
	private void hsunAkadaj() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		Barrier b = new Barrier(f2);
		
		f1.register(antEater);
		f2.register(b);
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		antEater.act();
	}
	
	private void hsunEtel() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		Food food = new Food(f2);
		
		f1.register(antEater);
		f2.register(food);
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		antEater.act();
	}
	
	private void hsunUres() {
		Field f1 = new Field();
		Field f2 = new Field();
		AntEater antEater = new AntEater(f1);
		
		f1.register(antEater);
		
		f1.addNeighbour(f2,3);
		f2.addNeighbour(f1,0);
		
		antEater.act();
	}
}
