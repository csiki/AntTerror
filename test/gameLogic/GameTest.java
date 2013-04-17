package gameLogic;
import static org.junit.Assert.*;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GameTest {
	Game game = null;
	String commands = "";
	ByteArrayOutputStream reasult = new ByteArrayOutputStream();
	String expected = "";
	String nl = System.getProperty("line.separator");

	@Before 
	public void initialize() {
		game = new Game();
		System.setOut(new PrintStream(reasult));
	}
	@After 
	public void after() {
		System.setOut(System.out);
		System.setIn(System.in);
	}
	@Test
	public void initTest() {
		//Teszt-eset neve	Inicializálás 
		//Rövid leírás	Az alkalmazás létrehoz egy pályát, amin minden fajta objektum szerepel.
		//Teszt célja	Leellenõrizni, hogy az alkalmazás megfelelõen hozza-e létre a pályát.

		commands = "newTest init"+nl
						+ "createMap 6 6"+nl
						+ "addAnt 0 0 0"+nl
						+ "addAnt 0 2 1"+nl
						+ "addFood 5 5"+nl
						+ "addAntHill 1 0 0"+nl
						+ "addAntEater 3 2 3"+nl
						+ "addStone 3 3"+nl
						+ "addBarrier 5 1"+nl
						+ "addAntLion 1 4"+nl
						+ "addAntEaterColony 5 2 0"+nl
						+ "showMap item"+nl
						+ "exit"+nl;
			/*newTest init
			createMap 6 6
			addAnt 0 0 0
			addAnt 0 2 1
			addFood 5 5
			addAntHill 1 0 0
			addAntEater 3 2 3
			addStone 3 3
			addBarrier 5 1
			addAntLion 1 4
			addAntEaterColony 5 2 0
			showMap item
			exit*/
			System.setIn(new ByteArrayInputStream(commands.getBytes()));
			game.protoTest();
			expected = "A 0 B 0 0 0"+nl
					 + " H 0 0 0 L 0"+nl
					 + "0 0 0 0 0 0"+nl
					 + " 0 0 E S 0 0"+nl
					 + "0 0 0 0 0 0"+nl
					 + " 0 Z C 0 0 F"+nl
					 + "----------------"+nl;
			/*A 0 B 0 0 0
			 H 0 0 0 L 0
			0 0 0 0 0 0
			 0 0 E S 0 0
			0 0 0 0 0 0
			 0 Z C 0 0 F
			----------------*/
			assertEquals(expected, reasult.toString());
	}
	@Test
	public void hangyaMozgasaTest() {
		//Teszt-eset neve	Hangya mozgása (hangyászsün ugyanez az elv)
		//Rövid leírás	A pályán elhelyezünk egy szagminta sorozatot és egy hangyát. A hangyának végig kell mennie a szagmintán.
		//Teszt célja	Leellenõrizni, hogy a hangya megfelelõen követi a szagot.

		commands = "newTest hangyaMozgasa"+nl
						+ "createMap 6 6"+nl
						+ "addAnt 1 1 0"+nl
						+ "addFood 5 2"+nl
						+ "setAntOdor 2 2 4"+nl
						+ "showMap item"+nl
						+ "showMap antodor"+nl
						+ "actAllItem"+nl
						+ "setAntOdor 3 2 4"+nl
						+ "showMap item"+nl
						+ "showMap antodor"+nl
						+ "actAllItem"+nl
						+ "setAntOdor 4 2 3"+nl
						+ "setAntOdor 4 3 4"+nl
						+ "showMap item"+nl
						+ "showMap antodor"+nl
						+ "actAllItem"+nl
						+ "showMap item"+nl
						+ "showMap antodor"+nl
						+ "exit"+nl;
			/*newTest hangyaMozgasa
			createMap 6 6
			addAnt 1 1 0
			addFood 5 2
			setAntOdor 2 2 4
			showMap item
			showMap antodor
			actAllItem
			setAntOdor 3 2 4
			showMap item
			showMap antodor
			actAllItem
			setAntOdor 4 2 3
			setAntOdor 4 3 4
			showMap item
			showMap antodor
			actAllItem
			showMap item
			showMap antodor
			exit*/
			System.setIn(new ByteArrayInputStream(commands.getBytes()));
			game.protoTest();
			expected = 
					"0 0 0 0 0 0" + nl
				+	" 0 A 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 F 0 0 0" + nl
				+	"----------------" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 4 0 0 0 0" + nl
				+	"0 0 4 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 A 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 F 0 0 0" + nl
				+	"----------------" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 4 0 0 0 0" + nl
				+	"0 0 4 0 0 0" + nl
				+	" 0 0 4 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 A 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 F 0 0 0" + nl
				+	"----------------" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 3 0 0 0 0" + nl
				+	"0 0 4 0 0 0" + nl
				+	" 0 0 4 0 0 0" + nl
				+	"0 0 3 4 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 A 0 0" + nl
				+	" 0 0 F 0 0 0" + nl
				+	"----------------" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 2 0 0 0 0" + nl
				+	"0 0 3 0 0 0" + nl
				+	" 0 0 4 0 0 0" + nl
				+	"0 0 2 4 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl;
			/*0 0 0 0 0 0
			 0 A 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 F 0 0 0
			----------------
			0 0 0 0 0 0
			 0 4 0 0 0 0
			0 0 4 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 A 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 F 0 0 0
			----------------
			0 0 0 0 0 0
			 0 4 0 0 0 0
			0 0 4 0 0 0
			 0 0 4 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 A 0 0 0
			0 0 0 0 0 0
			 0 0 F 0 0 0
			----------------
			0 0 0 0 0 0
			 0 3 0 0 0 0
			0 0 4 0 0 0
			 0 0 4 0 0 0
			0 0 3 4 0 0
			 0 0 0 0 0 0
			----------------
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 A 0 0
			 0 0 F 0 0 0
			----------------
			0 0 0 0 0 0
			 0 2 0 0 0 0
			0 0 3 0 0 0
			 0 0 4 0 0 0
			0 0 2 4 0 0
			 0 0 0 0 0 0
			----------------*/
			assertEquals(expected, reasult.toString());
	}
	@Test
	public void hangyaEtelTest() {
		//Teszt-eset neve	Hangya találkozása étellel
		//Rövid leírás	A pályán elhelyezünk egy ételt és egy hangyát. A hangyának fel kell tudnia venni az ételt, és utána tudnia kell cipelni azt.
		//Teszt célja	Leellenõrizni, hogy a hangya megfelelõen reagál-e étellel való találkozásra.

		commands = "newTest hangyaEtel"+nl
						+ "createMap 6 6"+nl
						+ "addAnt 1 0 0"+nl
						+ "addFood 1 1"+nl
						+ "showMap item"+nl
						+ "actAllItem"+nl
						+ "showMap item"+nl
						+ "exit"+nl;
			/*newTest hangyaEtel
			createMap 6 6
			addAnt 1 0 0
			addFood 1 1
			showMap item
			actAllItem
			showMap item
			exit*/
			System.setIn(new ByteArrayInputStream(commands.getBytes()));
			game.protoTest();
			expected =
					"0 0 0 0 0 0" + nl
				+	" A F 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 B 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl;
			/*0 0 0 0 0 0
			 A F 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------
			0 0 0 0 0 0
			 0 B 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------*/
			assertEquals(expected, reasult.toString());
	}
	@Test
	public void hangyaBlokkTest() {
		//Teszt-eset neve	Hangya találkozása õt blokkoló dologgal
		//Rövid leírás	A pályán elhelyezünk egy hangyát és egy kavicsot. A hangyának ki kell tudni kerülni a kavicsot.
		//Teszt célja	Leellenõrizni, hogy a hangya ki tud kerülni egy akadályt.

		commands = "newTest hangyaBlokk"+nl
						+ "createMap 6 6"+nl
						+ "addAnt 0 0 0"+nl
						+ "addFood 0 3"+nl
						+ "showMap item"+nl
						+ "actAllItem"+nl
						+ "showMap item"+nl
						+ "exit"+nl;
			/*newTest hangyaBlokk
			createMap 6 6
			addAnt 0 0 0
			addStone 0 1
			addFood 0 3
			showMap item
			actAllItem
			actAllItem
			showMap item
			exit*/
			System.setIn(new ByteArrayInputStream(commands.getBytes()));
			game.protoTest();
			expected =
					"A S 0 F 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl
				+	"0 S 0 F 0 0" + nl
				+	" 0 A 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl;
			/*A S 0 F 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------
			0 S 0 F 0 0
			 0 A 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------*/
			assertEquals(expected, reasult.toString());
	}
	@Test
	public void hangyaHangyalesoTest() {
		//Teszt-eset neve	Hangya belesétál egy hangyalesõbe
		//Rövid leírás	A pályán elhelyezünk egy hangyát és egy hangyalesõt. Amikor a hangya belesétál a hangyalesõbe, annak el kell pusztítania a hangyát.
		//Teszt célja	Leellenõrizni, hogy a hangyalesõ el tud pusztítani egy hangyát.


		commands = "newTest hangyaHangyaleso"+nl
						+ "createMap 6 6"+nl
						+ "addAnt 0 0 0"+nl
						+ "addAntLion 0 1"+nl
						+ "addFood 0 3"+nl
						+ "showMap item"+nl
						+ "actAllItem"+nl
						+ "showMap item"+nl
						+ "exit"+nl;
			/*newTest hangyaHangyaleso
			createMap 6 6
			addAnt 0 0 0
			addAntLion 0 1
			addFood 0 3
			showMap item
			actAllItem
			showMap item
			exit*/
			System.setIn(new ByteArrayInputStream(commands.getBytes()));
			game.protoTest();
			expected =
					"A L 0 F 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl
				+	"0 L 0 F 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl;
			/*A L 0 F 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------
			0 L 0 F 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------*/
			assertEquals(expected, reasult.toString());
	}
	@Test
	public void hangyaHangyabolyTest() {
		//Teszt-eset neve	Hangya visszatalálása a bolyba (hangyászsün ugyanez az elv)
		//Rövid leírás	A pályán elhelyezünk egy hangyát és egy hangyabolyt. A hangyának, a hangyaboly szagát követve vissza kell találni a hangyabolyba.
		//Teszt célja	Leellenõrizni, hogy a hangya vissza tud találni a hangyabolyba.


		commands = "newTest hangyaHangyaboly"+nl
						+ "createMap 6 6"+nl
						+ "addAnt 0 0 1"+nl
						+ "addAntHill 0 3 1"+nl
						+ "showMap item"+nl
						+ "actAllItem"+nl
						+ "actAllItem"+nl
						+ "showMap item"+nl
						+ "actAllItem"+nl
						+ "showMap item"+nl
						+ "exit"+nl;
			/*newTest hangyaHangyaboly
			createMap 6 6
			addAnt 0 0 1
			addAntHill 0 3 1
			showMap item
			actAllItem
			actAllItem
			showMap item
			actAllItem
			showMap item
			exit*/
			System.setIn(new ByteArrayInputStream(commands.getBytes()));
			game.protoTest();
			expected =
					"B 0 0 H 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl
				+	"0 0 B H 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl
				+   "0 0 0 H 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl;
			/*B 0 0 H 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------
			0 0 B H 0 0
			 0 A 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------
			0 0 0 H 0 0
			 0 A 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------*/
			assertEquals(expected, reasult.toString());
	}
	@Test
	public void hangyaszsunHangyaTest() {
		//Teszt-eset neve	Hangyászsün találkozása hangyával
		//Rövid leírás	A pályán elhelyezünk egy hangyát és egy hangyászsünt. A hangyászsünnek meg kell tudnia ennie a hangyát. A hangyának el kell pusztulnia.
		//Teszt célja	Leellenõrizni, hogy a hangyászsün meg tudja-e enni a hangyát.
		
		commands = "newTest hangyaszsunHangya"+nl
						+ "createMap 6 6"+nl
						+ "addAnt 0 0 0"+nl
						+ "addAntEater 0 1 3"+nl
						+ "addFood 0 3"+nl
						+ "showMap item"+nl
						+ "actAllItem"+nl
						+ "showMap item"+nl
						+ "exit"+nl;
			/*newTest hangyaszsunHangya
			createMap 6 6
			addAnt 0 0 0
			addAntEater 0 1 3
			addFood 0 3
			showMap item
			actItem 0 1
			showMap item
			exit*/
			System.setIn(new ByteArrayInputStream(commands.getBytes()));
			game.protoTest();
			expected =
					"A E 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl
				+	"E 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl;
			/*A E 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------
			E 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------*/
			assertEquals(expected, reasult.toString());
	}
	@Test
	public void hangyaszsunKavicsTest() {
		//Teszt-eset neve	Hangyászsün találkozása kaviccsal
		//Rövid leírás	A pályán elhelyezünk két kavicsot és egy hangyászsünt. A sünnek el kell tudnia tolni mindkét kavicsot, amennyiben a kavicsok mögött nem áll semmi.
		//Teszt célja	Leellenõrizni, hogy a hangyászsün el tud tolni egy kavicsot.

		commands = "newTest hangyaszsunKavics"+nl
						+ "createMap 6 6"+nl
						+ "addAntEater 0 0 0"+nl
						+ "addStone 0 1"+nl
						+ "addStone 0 2"+nl
						+ "addAntEaterColony 0 4 1"+nl
						+ "showMap item"+nl
						+ "actAllItem"+nl
						+ "showMap item"+nl
						+ "exit"+nl;
			/*newTest hangyaszsunKavics
			createMap 6 6
			addAntEater 0 0 0
			addStone 0 1
			addStone 0 2
			addAntEaterColony 0 4 1
			showMap item
			actAllItem
			showMap item
			exit*/
			System.setIn(new ByteArrayInputStream(commands.getBytes()));
			game.protoTest();
			expected =
					"E S S 0 C 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl
				+	"0 E S S C 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl;
			/*E S S 0 C 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------
			0 E S S C 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------*/
			assertEquals(expected, reasult.toString());
	}
	@Test
	public void hangyaszsunBlokkTest() {
		//Teszt-eset neve	Hangyászsün találkozása õt blokkoló dologgal
		//Rövid leírás	A pályán elhelyezünk egy hangyászsünt és egy õt blokkoló dolgot (pl. hangyalesõ). A sünnek tudnia kell kikerülni ezt a dolgot.
		//Teszt célja	Leellenõrizni, hogy a hangyászsün ki tud kerülni egy akadályt.

		commands = "newTesthangyaszsunBlokk"+nl
						+ "createMap 6 6"+nl
						+ "addAntEater 0 0 0"+nl
						+ "addAntLion 0 1"+nl
						+ "addAntEaterColony 0 4 1"+nl
						+ "showMap item"+nl
						+ "actAllItem"+nl
						+ "showMap item"+nl
						+ "exit"+nl;
			/*newTest hangyaszsunBlokk
			createMap 6 6
			addAntEater 0 0 0
			addAntLion 0 1
			addAntEaterColony 0 4 1
			showMap item
			actAllItem
			actAllItem
			showMap item
			exit*/
			System.setIn(new ByteArrayInputStream(commands.getBytes()));
			game.protoTest();
			expected =
					"E L 0 0 C 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl
				+	"0 L 0 0 C 0" + nl
				+	" 0 E 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl;
			/*E L 0 0 C 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------
			0 L 0 0 C 0
			 0 E 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------*/
			assertEquals(expected, reasult.toString());
	}
	@Test
	public void hangyaIrtoSprayTest() {
		//Teszt-eset neve	Hangyairtó spray tesztelése
		//Rövid leírás	A pályán elhelyezünk pár hangyát elszórtan és használjuk a hangyairtó spray-t. A spray-nek egy adott körön belül el kell tudnia pusztítani a hangyákat. Valamint látni kell a spray fogyását is.
		//Teszt célja	Hangyairtó spray megfelelõ mûködésének ellenõrzése.

		commands = "newTest hangyaIrtoSpray"+nl
						+ "createMap 6 6"+nl
						+ "createKillerSpray 2 1"+nl
						+ "addAnt 0 0 0"+nl
						+ "addAnt 0 1 0"+nl
						+ "addAnt 0 2 0"+nl
						+ "addAnt 0 3 0"+nl
						+ "addAnt 1 3 0"+nl
						+ "addAnt 1 1 0"+nl
						+ "addAnt 2 2 0"+nl
						+ "addAnt 3 2 0"+nl				
						+ "showMap item"+nl
						+ "useKillerSpray 1 1"+nl
						+ "showMap item"+nl
						+ "exit"+nl;
			/*newTest hangyaIrtoSpray
			createMap 6 6
			createKillerSpray 2 1
			addAnt 0 0 0
			addAnt 0 1 0
			addAnt 0 2 0
			addAnt 0 3 0
			addAnt 1 3 0
			addAnt 1 1 0
			addAnt 2 2 0
			addAnt 3 2 0
			showMap item
			useKillerSpray 1 1
			showMap item
			exit*/
			System.setIn(new ByteArrayInputStream(commands.getBytes()));
			game.protoTest();
			expected =
					"A A A A 0 0" + nl
				+	" 0 A 0 A 0 0" + nl
				+	"0 0 A 0 0 0" + nl
				+	" 0 0 A 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl
				+	"KillerSpray charge 1" + nl
				+	"----------------" + nl
				+	"A 0 0 A 0 0" + nl
				+	" 0 0 0 A 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 A 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl;
			/*A A A A 0 0
			 0 A 0 A 0 0
			0 0 A 0 0 0
			 0 0 A 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------
			KillerSpray charge 1
			----------------
			A 0 0 A 0 0
			 0 0 0 A 0 0
			0 0 0 0 0 0
			 0 0 A 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------*/
			assertEquals(expected, reasult.toString());
	}
	@Test
	public void hangyaSzagSprayTest() {
		//Teszt-eset neve	Hangyaszag-semlegesítõ spray tesztelése
		//Rövid leírás	A pályán elhelyezett szagokat egy meghatározott körön belül semlegesítenie kell a spray-nek. Valamint látni kell a spray fogyását is.
		//Teszt célja	Hangyaszag-semlegesítõ spray megfelelõ mûködésének ellenõrzése.


		commands = "newTest hangyaSzagSpray"+nl
						+ "createMap 6 6"+nl
						+ "createOdorSpray 2 1"+nl
						+ "setAntOdor 0 0 4"+nl
						+ "setAntOdor 0 1 4"+nl
						+ "setAntOdor 0 2 4"+nl
						+ "setAntOdor 0 3 4"+nl
						+ "setAntOdor 1 3 4"+nl
						+ "setAntOdor 1 1 4"+nl
						+ "setAntOdor 2 2 4"+nl
						+ "setAntOdor 3 2 4"+nl				
						+ "showMap antodor"+nl
						+ "useOdorSpray 1 1"+nl
						+ "showMap antodor"+nl
						+ "exit"+nl;
			/*newTest hangyaSzagSpray
			createMap 6 6
			createOdorSpray 2 1
			setAntOdor 0 0 4
			setAntOdor 0 1 4
			setAntOdor 0 2 4
			setAntOdor 0 3 4
			setAntOdor 1 3 4
			setAntOdor 1 1 4
			setAntOdor 2 2 4
			setAntOdor 3 2 4
			showMap antodor
			useOdorSpray 1 1
			showMap antodor
			exit*/
			System.setIn(new ByteArrayInputStream(commands.getBytes()));
			game.protoTest();
			expected =
					"4 4 4 4 0 0" + nl
				+	" 0 4 0 4 0 0" + nl
				+	"0 0 4 0 0 0" + nl
				+	" 0 0 4 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl
				+	"OdorSpray charge 1" + nl
				+	"----------------" + nl
				+	"4 0 0 4 0 0" + nl
				+	" 0 0 0 4 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 4 0 0 0" + nl
				+	"0 0 0 0 0 0" + nl
				+	" 0 0 0 0 0 0" + nl
				+	"----------------" + nl;
			/*A A A A 0 0
			 0 A 0 A 0 0
			0 0 A 0 0 0
			 0 0 A 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------
			OdorSpray charge 1
			----------------
			4 0 0 4 0 0
			 0 0 0 4 0 0
			0 0 0 0 0 0
			 0 0 4 0 0 0
			0 0 0 0 0 0
			 0 0 0 0 0 0
			----------------*/
			assertEquals(expected, reasult.toString());
	}

}
