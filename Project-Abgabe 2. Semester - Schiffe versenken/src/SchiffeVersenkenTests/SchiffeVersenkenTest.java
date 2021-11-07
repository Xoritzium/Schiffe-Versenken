package SchiffeVersenkenTests;

import SchiffeVersenken.InvalideEingabeException;
import SchiffeVersenken.InvalideLaengenEingabeException;
import SchiffeVersenken.InvalideRichtungException;
import SchiffeVersenken.SchiffeVersenken;
import SchiffeVersenken.SchiffeVersenkenImpl;
import SchiffeVersenken.Ship;
import SchiffeVersenken.Shot;
import SchiffeVersenken.oneShip;
import junit.framework.Assert;

import static org.junit.Assert.*;

import org.junit.Test;

/*
 * 
 * 	------------Test setShip----------
 * ==valide Koordinaten
 * ==invalide koordinateneingaben  <1 und >10
 * ==Shiff ragt aus dem Spielfeld
 * ==Anzahl gesetzter Schiffe (nicht mehr Schiffe des Regelwerks vorgeben)
 * ==falsche Schiffslängen ( Länge muss mit Namen übereinstimmen)
 * ==Schiffe dürfen nicht aneinander stoßen, sich also nicht überlappen, Kante and Kante ist ok!
 * == nochmal dran sein oder nicht
 * 
 * -------- Tests shot--------
 * ==valide Koordinaten
 * == Treffer oder nicht = Hilfsmethode, die angibt ob ein Treffer vorlieg, Schiffslage des Beschossenen mit shot Koordinaten vergleichen
 * ==Gewonnen = ansage welcher Spieler gewonnen hat, sobald ein Spieler den letzten Treffer gelandet hat.
 * ==
 * 
 * ------------allgemein--------
 * ==Spieler festlegen
 * == testspiellauf, kleinere Anzahl Schiffe
 */
public class SchiffeVersenkenTest {
//////////////// valide Koordinateneingaben//////////kannn man theoretisch zusammenfasse, sodass ein Test mehrere Asserts hat
	// Fragen: ist z37 klug ?, ein classobjekt von Schiffe versenken machen? anstatt

	/**
	 * 
	 * @return ein Objekt von SchiffeVersenken
	 */
	private SchiffeVersenken schiffeVersenkenImpl() {
		SchiffeVersenken sv = new SchiffeVersenkenImpl();
		return sv;
	}
	
	

	@Test
	public void testsetShip001()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		oneShip ship = sv.setShip(1, 1, Ship.CRUISER, 4, false);
		int[] tester = { ship.getCoordX(), ship.getCoordY() };
		int[] pruefer = { 1, 1 };
		assertArrayEquals(pruefer, tester);

	}

	@Test
	public void testsetShip002()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		oneShip ship = sv.setShip(1, 3, Ship.CRUISER, 4, false);
		int[] tester = { ship.getCoordX(), ship.getCoordY() };
		int[] pruefer = { 1, 3 };
		assertArrayEquals(pruefer, tester);

	}

	@Test
	public void testsetShip003()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		oneShip ship = sv.setShip(5, 6, Ship.CRUISER, 4, false);
		int[] tester = { ship.getCoordX(), ship.getCoordY() };
		int[] pruefer = { 5, 6 };
		assertArrayEquals(pruefer, tester);

	}

	@Test
	public void testsetShip004()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		oneShip ship = sv.setShip(10, 10, Ship.CRUISER, 4, false);
		int[] tester = { ship.getCoordX(), ship.getCoordY() };
		int[] pruefer = { 10, 10 };
		assertArrayEquals(pruefer, tester);

	}

	@Test
	public void testsetShip005()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		oneShip ship = sv.setShip(5, 10, Ship.CRUISER, 4, false);
		int[] tester = { ship.getCoordX(), ship.getCoordY() };
		int[] pruefer = { 5, 10 };
		assertArrayEquals(pruefer, tester);

	}

	///////////// invalide Eingaben der koordinaten//////////////////

	@Test(expected = InvalideEingabeException.class)
	public void testsetShip006()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(11, 8, Ship.CRUISER, 4, false);

	}

	@Test(expected = InvalideEingabeException.class)
	public void testsetShip007()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(2, 12, Ship.CRUISER, 4, false);

	}

////////////////////// falscheSchiffslängen: /////////////////////
	@Test(expected = InvalideLaengenEingabeException.class)
	public void testsetShip008()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = schiffeVersenkenImpl());
		sv.setShip(5, 10, Ship.CRUISER, 6, false);

	}

	@Test(expected = InvalideLaengenEingabeException.class)
	public void testsetShip009()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(5, 10, Ship.CRUISER, 0, false);

	}

////// ragt nach rechts aus dem Spielfeld Direction = @false //////////
	@Test(excpected = InvalideSchiffSetPositionExecption)
	public void testsetShip010()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(10, 1, Ship.SUBMARINE, 2, false);
	}

	@Test(excpected = InvalideSchiffSetPositionExecption)
	public void testsetShip011()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(10, 10, Ship.SUBMARINE, 2, false);

	}

	@Test(excpected = InvalideSchiffSetPositionExecption)
	public void testsetShip014()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = schiffeVersenkenImpl()();
		sv.setShip(10, 5, Ship.SUBMARINE, 2, false);

	}

/////////ragt nach unten aus dem Spielfeld Direction = @true ///////		
	@Test(excpected = InvalideSchiffSetPositionExecption)
	public void testsetShip012()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(1, 10, Ship.SUBMARINE, 2, true);

	}

	@Test(excpected = InvalideSchiffSetPositionExecption)
	public void testsetShip013()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(10, 10, Ship.SUBMARINE, 2, true);

	}

	@Test(excpected = InvalideSchiffSetPositionExecption)
	public void testsetShip015()
			throws InvalideEingabeException, InvalideLaengenEingabeException, InvalideRichtungException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(5, 10, Ship.SUBMARINE, 2, false);

	}
//////////////////Anzahl der Schiffe übersschritten///////////////

	@Test(excpected = zuVieleSchiffeException)
	public void testsetShip016() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(1, 1, Ship.SUBMARINE, 2, true);
		sv.setShip(2, 2, Ship.SUBMARINE, 2, true);
		sv.setShip(3, 3, Ship.SUBMARINE, 2, true);
		sv.setShip(4, 5, Ship.SUBMARINE, 2, true);
		sv.setShip(5, 6, Ship.SUBMARINE, 2, true); // zuviel

	}

	@Test(excpected = zuVieleSchiffeException)
	public void testsetShip017() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(1, 1, Ship.DESTROYER, 3, true);
		sv.setShip(2, 2, Ship.DESTROYER, 3, true);
		sv.setShip(3, 3, Ship.DESTROYER, 3, true);
		sv.setShip(4, 5, Ship.DESTROYER, 3, true); // zuviel

	}

	@Test(excpected = zuVieleSchiffeException)
	public void testsetShip018() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(1, 1, Ship.BATTLESHIP, 5, true);
		sv.setShip(2, 2, Ship.BATTLESHIP, 5, true); // zuviel

	}

	//////////////// falsche Schiffslängen//////////////
	@Test(excpected = falscheSChiffslaengeException)
	public void testsetShip019() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(1, 1, Ship.BATTLESHIP, 2, true);

	}

	@Test(excpected = falscheSChiffslaengeException)
	public void testsetShip020() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(1, 1, Ship.CRUISER, 5, true);

	}

	@Test(excpected = falscheSChiffslaengeException)
	public void testsetShip021() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(1, 1, Ship.SUBMARINE, 3, true);

	}

	///////////////// Schiffe überlappen sich/////////////
	
	@Test(excpected = SchiffSetFeldBelegtException)
	public void testsetShip022() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(1, 1, Ship.SUBMARINE, 2, true);
		sv.setShip(1, 2, Ship.CRUISER, 3, false);

	}

	@Test(excpected = SchiffSetFeldBelegtException)
	public void testsetShip023() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(4, 4, Ship.BATTLESHIP, 5, true);
		sv.setShip(4, 4, Ship.DESTROYER, 3, false);

	}

	@Test(excpected = SchiffSetFeldBelegtException)
	public void testsetShip024() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken sv = schiffeVersenkenImpl();
		sv.setShip(5, 3, Ship.SUBMARINE, 2, true);
		sv.setShip(6, 2, Ship.CRUISER, 4, false);

		///////////////////////////////////////////
		/////////// Tests für "shot"///////////////
		///////////////////////////////////////////
	}

	@Test
	public void testShot001() {
		SchiffeVersenken shooter = schiffeVersenkenImpl();
		// Schuss aus x = 1, y = 1
		int[] tester = { shooter.shot(1, 1).getX() , shooter.shot(1, 1).getY() };
		int[] pruefer = { 1, 1 };
		assertArrayEquals(pruefer, tester);
	}

	@Test
	public void testShot002() {
		SchiffeVersenken shooter = schiffeVersenkenImpl();
		// Schuss aus x = 10, y = 10
		int x = shooter.shot(1, 1).getX();
		int[] tester = { shooter.shot(10, 10).getX(), shooter.shot(10, 10).getY() };
		int[] pruefer = { 10, 10 };
		assertArrayEquals(pruefer, tester);
	}

	@Test
	public void testShot003() {
		SchiffeVersenken shooter = schiffeVersenkenImpl();
		// Schuss auf x = 3, y = 6
		int[] tester = { shooter.shot(3, 6).getX(), shooter.shot(3, 6).getY() };
		int[] pruefer = { 3, 6 };
		assertArrayEquals(pruefer, tester);
	}

	@Test(expected = InvalideEingabeException.class)
	public void testShot004() {
		SchiffeVersenken shooter = schiffeVersenkenImpl();
		shooter.shot(3, 11);

	}

	@Test(expected = InvalideEingabeException.class)
	public void testShot005() {
		SchiffeVersenken shooter = schiffeVersenkenImpl();
		shooter.shot(0, 6);

	}

//////// Treffer, irgendwas das True/false ausgibt
	@Test
	public void testShot006() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken shooter = schiffeVersenkenImpl(); 
		shooter.setShip(3, 3, Ship.DESTROYER, 3, true);
		Assert.assertTrue(shooter.shot(3, 5).getTreffer());

	}

	@Test
	public void testShot007() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken shooter = schiffeVersenkenImpl(); 
		shooter.setShip(3, 3, Ship.DESTROYER, 3, true);
		Assert.assertTrue(shooter.shot(3, 3).getTreffer());

	}

	@Test
	public void testShot008() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken shooter = schiffeVersenkenImpl();
		shooter.setShip(10, 3, Ship.DESTROYER, 3, true);
		Assert.assertFalse(shooter.shot(1, 4).getTreffer());

	}

	@Test
	public void testShot009() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken shooter = schiffeVersenkenImpl();
		shooter.setShip(3, 3, Ship.DESTROYER, 3, true);
		shooter.shot(4, 5);
		Assert.assertFalse(shooter.shot(10, 6).getTreffer());

	}

	@Test
	public void testShot010() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken shooter = schiffeVersenkenImpl();
		shooter.setShip(3, 3, Ship.DESTROYER, 3, true);
		shooter.shot(3, 4);
		shooter.shot(3, 5);
		shooter.shot(3, 3);
		Assert.assertTrue(shooter.shot(3, 3).getWon());
	}

	@Test
	public void testShot011() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken shooter = schiffeVersenkenImpl();
		shooter.setShip(3, 3, Ship.DESTROYER, 3, true);
		Assert.assertFalse(shooter.shot(3, 3).getWon());

	}
	// Trffer == nochmal Schießen
	// Kein Treffer == anderer Spieler ist drana

	@Test
	public void testShot12() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken shooter = schiffeVersenkenImpl();
		shooter.setShip(4, 8, Ship.SUBMARINE, 2, false);
		Assert.assertTrue(shooter.shot(2, 2).getNextPlayer());
		Assert.assertFalse(shooter.shot(5, 8).getNextPlayer());

	}

	//////////////// allgemeine Tests ///////////////////
	@Test
	public void testGenerale01() {
		SchiffeVersenken tg = new SchiffeVersenkenImpl("Spanien", "Atzteken");
		Assert.assertNotNull(tg);
	}

	@Test(expected = nameTakenException)
	public void testGenerale02() {
		SchiffeVersenken tg = new SchiffeVersenkenImpl("Spanien", "Spanien");
	}

	@Test
	public void runTest01() throws InvalideEingabeException, InvalideLaengenEingabeException {
		SchiffeVersenken tg = schiffeVersenkenImpl();
		tg.setShip(2, 2, Ship.SUBMARINE, 2, true);
		tg.setShip(3, 2, Ship.SUBMARINE, 2, false);
		tg.setShip(8, 6, Ship.CRUISER, 4, true);
		Assert.assertTrue(tg.shot(2, 2).getTreffer()); // Treffer
		Assert.assertTrue(tg.shot(2, 3).getTreffer()); // Treffer
		Assert.assertFalse(tg.shot(2, 1).getTreffer()); // kein Treffer
		Assert.assertTrue(tg.shot(3, 2).getTreffer()); // Treffer
		Assert.assertTrue(tg.shot(3, 4).getTreffer()); // Treffer
		Assert.assertTrue(tg.shot(8, 6).getTreffer()); // Treffer
		Assert.assertTrue(tg.shot(8, 7).getTreffer()); // Treffer
		Assert.assertTrue(tg.shot(8, 8).getTreffer()); // Treffer
		Assert.assertFalse(tg.shot(8, 8).getWon()); // Treffer aber noch nicht gewonnen
		Assert.assertTrue(tg.shot(8, 9).getTreffer()); // Treffer
		Assert.assertTrue(tg.shot(8, 9).getWon()); // Treffer und gewonnen
	}

}
