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
// Test 2

/*immer mindestens 5 oberer und unterer Rand testen (wenn nötig)
 * 
 * 	------------Test setShip----------
 * ==valide Koordinaten
 * ==invalide koordinateneingaben  <1 und >10
 * ==falsch Schiffslängen ( Länge muss mit Namen übereinstimmen)
 * ==Shiff ragt aus dem Spielfeld
 * ==Anzahl gesetzter Schiffe (nicht mehr Schiffe des Regelwerks vorgeben)
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
//////////////// valide Koordinateneingaben
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

	@Test(excpected = zuVieleSchiffeException)
	public void testsetShip016() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		sv.setShip(2, 3, Ship.SUBMARINE, 2, false);

	}

}
