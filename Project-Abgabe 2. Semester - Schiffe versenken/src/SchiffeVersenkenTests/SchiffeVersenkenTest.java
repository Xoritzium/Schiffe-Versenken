package SchiffeVersenkenTests;

import SchiffeVersenken.InvalideEingabeException;
import SchiffeVersenken.InvalideLaengenEingabeException;
import SchiffeVersenken.InvalideRichtungException;
import SchiffeVersenken.SchiffeVersenken;
import SchiffeVersenken.SchiffeVersenkenImpl;
import SchiffeVersenken.Ship;
import SchiffeVersenken.oneShip;
import junit.framework.Assert;

import static org.junit.Assert.*;

import org.junit.Test;

/*immer mindestens 5 oberer und unterer Rand testen (wenn nötig)
 * 
 * 	------------Test setShip----------
 * ==valide Koordinaten
 * ==invalide koordinateneingaben  <1 und >10
 * ==Shiff ragt aus dem Spielfeld
 * ==Anzahl gesetzter Schiffe (nicht mehr Schiffe des Regelwerks vorgeben)
 * ==falsche Schiffslängen ( Länge muss mit Namen übereinstimmen)
 * ==Schiffe dürfen nicht aneinander stoßen, sich also nicht überlappen, Kante and Kante ist ok!
 * 
 * 
 * -------- Tests shot--------
 * ==valide Koordinaten
 * == Treffer oder nicht = Hilfsmethode, die angibt ob ein Treffer vorlieg, Schiffslage des Beschossenen mit shot Koordinaten vergleichen
 * ==Gewonnen = ansage welcher Spieler gewonnen hat, sobald ein Spieler den letzten Treffer gelandet hat.
 * 
 * ------------allgemein--------
 * ==Spieler festlegen
 * ==Überkreuthandlung der Spielbretter festlegen
 */
public class SchiffeVersenkenTest {
//////////////// valide Koordinateneingaben//////////kannn man theoretisch zusammenfasse, sodass ein Test mehrere Asserts hat
	@Test
	public void testsetShip001()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		oneShip ship = sv.setShip(1, 1, Ship.CRUISER, 4, false);
		int[] tester = { ship.getCoordX(), ship.getCoordY() };
		int[] pruefer = { 1, 1 };
		assertArrayEquals(pruefer, tester);

	}

	@Test
	public void testsetShip002()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		oneShip ship = sv.setShip(1, 3, Ship.CRUISER, 4, false);
		int[] tester = { ship.getCoordX(), ship.getCoordY() };
		int[] pruefer = { 1, 3 };
		assertArrayEquals(pruefer, tester);

	}

	@Test
	public void testsetShip003()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		oneShip ship = sv.setShip(5, 6, Ship.CRUISER, 4, false);
		int[] tester = { ship.getCoordX(), ship.getCoordY() };
		int[] pruefer = { 5, 6 };
		assertArrayEquals(pruefer, tester);

	}

	@Test
	public void testsetShip004()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		oneShip ship = sv.setShip(10, 10, Ship.CRUISER, 4, false);
		int[] tester = { ship.getCoordX(), ship.getCoordY() };
		int[] pruefer = { 10, 10 };
		assertArrayEquals(pruefer, tester);

	}

	@Test
	public void testsetShip005()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		oneShip ship = sv.setShip(5, 10, Ship.CRUISER, 4, false);
		int[] tester = { ship.getCoordX(), ship.getCoordY() };
		int[] pruefer = { 5, 10 };
		assertArrayEquals(pruefer, tester);

	}

	///////////// invalide Eingaben der koordinaten//////////////////

	@Test(expected = InvalideEingabeException.class)
	public void testsetShip006()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(11, 8, Ship.CRUISER, 4, false);

	}

	@Test(expected = InvalideEingabeException.class)
	public void testsetShip007()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(2, 12, Ship.CRUISER, 4, false);

	}

////////////////////// falscheSchiffslängen: /////////////////////
	@Test(expected = InvalideLaengenEingabeException.class)
	public void testsetShip008()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(5, 10, Ship.CRUISER, 6, false);

	}

	@Test(expected = InvalideLaengenEingabeException.class)
	public void testsetShip009()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(5, 10, Ship.CRUISER, 0, false);

	}

////// ragt nach rechts aus dem Spielfeld Direction = @false //////////
	@Test(excpected = InvalideSchiffSetPositionExecption)
	public void testsetShip010()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(10, 1, Ship.SUBMARINE, 2, false);
	}

	@Test(excpected = InvalideSchiffSetPositionExecption)
	public void testsetShip011()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(10, 10, Ship.SUBMARINE, 2, false);

	}

	@Test(excpected = InvalideSchiffSetPositionExecption)
	public void testsetShip014()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(10, 5, Ship.SUBMARINE, 2, false);

	}

/////////ragt nach unten aus dem Spielfeld Direction = @true ///////		
	@Test(excpected = InvalideSchiffSetPositionExecption)
	public void testsetShip012()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(1, 10, Ship.SUBMARINE, 2, true);

	}

	@Test(excpected = InvalideSchiffSetPositionExecption)
	public void testsetShip013()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(10, 10, Ship.SUBMARINE, 2, true);

	}

	@Test(excpected = InvalideSchiffSetPositionExecption)
	public void testsetShip015()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(5, 10, Ship.SUBMARINE, 2, false);

	}
//////////////////Anzahl der Schiffe übersschritten///////////////

	@Test(excpected = zuVieleSchiffeException)
	public void testsetShip016() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(1, 1, Ship.SUBMARINE, 2, true);
		sv.setShip(2, 2, Ship.SUBMARINE, 2, true);
		sv.setShip(3, 3, Ship.SUBMARINE, 2, true);
		sv.setShip(4, 5, Ship.SUBMARINE, 2, true);
		sv.setShip(5, 6, Ship.SUBMARINE, 2, true); // zuviel

	}

	@Test(excpected = zuVieleSchiffeException)
	public void testsetShip017() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(1, 1, Ship.DESTROYER, 3, true);
		sv.setShip(2, 2, Ship.DESTROYER, 3, true);
		sv.setShip(3, 3, Ship.DESTROYER, 3, true);
		sv.setShip(4, 5, Ship.DESTROYER, 3, true); // zuviel

	}

	@Test(excpected = zuVieleSchiffeException)
	public void testsetShip018() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(1, 1, Ship.BATTLESHIP, 5, true);
		sv.setShip(2, 2, Ship.BATTLESHIP, 5, true); // zuviel

	}

	//////////////// falsche Schiffslängen//////////////
	@Test(excpected = falscheSChiffslaengeException)
	public void testsetShip019() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(1, 1, Ship.BATTLESHIP, 2, true);

	}

	@Test(excpected = falscheSChiffslaengeException)
	public void testsetShip020() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(1, 1, Ship.CRUISER, 5, true);

	}

	@Test(excpected = falscheSChiffslaengeException)
	public void testsetShip021() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(1, 1, Ship.SUBMARINE, 3, true);

	}

	///////////////// Schiffe überlappen sich/////////////
	@Test(excpected = SchiffSetFeldBelegtException)
	public void testsetShip022() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(1, 1, Ship.SUBMARINE, 2, true);
		sv.setShip(1, 2, Ship.CRUISER, 3, false);

	}
	@Test(excpected = SchiffSetFeldBelegtException)
	public void testsetShip023() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(4, 4, Ship.BATTLESHIP, 5, true);
		sv.setShip(4, 4, Ship.DESTROYER, 3, false);

	}
	@Test(excpected = SchiffSetFeldBelegtException)
	public void testsetShip024() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(5, 3, Ship.SUBMARINE, 2, true);
		sv.setShip(6, 2, Ship.CRUISER, 4, false);

	}

}
