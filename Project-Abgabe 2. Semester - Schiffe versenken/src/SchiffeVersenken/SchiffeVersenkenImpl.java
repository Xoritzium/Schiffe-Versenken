package SchiffeVersenken;

public class SchiffeVersenkenImpl implements SchiffeVersenken {

	private final int NUM_OF_SUBMARINES = 4;
	private final int NUM_OF_DESTROYERS = 3;
	private final int NUM_OF_CRUISERS = 2;
	private final int NUM_OF_BATTLESHIPS = 1;
	/**
	 *
	 */
	// private final int TOTAL_NUM_OF_Ships = NUM_OF_BATTLESHIPS + NUM_OF_CRUISERS +
	// NUM_OF_DESTROYERS + NUM_OF_SUBMARINES; // insg:

	// zum Testen alternative maximale Schiffsmenge
	private final int TOTAL_NUM_OF_Ships = 1; // 10
	private int countSubmarines;
	private int countDestroyers;
	private int countCruisers;
	private int countBattleship;
	private int countNumOfShips;

	/*
	 * Das Spielfeld. Es gibt insgesamt Zwei Objekte Schiffe Versenken, diese werden
	 * entsprechend angesteuert! Nur für die gesetzten Schiffe
	 * 
	 */
	private Field theField;
	// Ueberpruefungsfeld, dort werden die Schüsse vermerkt || also nur bei Shot
	// Aufruf
	private Field ShotField;
// der aktuelle Schuss
	private Shot shot;

	/**
	 * allgemeiner Constructor
	 */
	public SchiffeVersenkenImpl() {
		theField = new Field();
		ShotField = new Field();

	}

	public oneShip setShip(int x, int y, Ship ship, int length, boolean dir)
			throws InvalideEingabeException, invalideLaengenEingabeException, SchiffSetFeldBelegtException,
			zuVieleSchiffeException, InvalideSchiffSetPositionExecption {

		// Validierung der Eingaben
		validCoord(x);
		validCoord(y);
		validLength(ship, length);
		// alternativeValidLength(ship, length);
		// checkt aus dem Spielfeld herausragende Schiffe
		validBorderPositions(x, y, length, dir);
		enoughShips(ship);
		// alternativeEnoughShips(ship);
		checkTakenField(x, y, length, dir);

		// generiert das gesamte Schiff
		oneShip actualship = new oneShip(x, y, ship, length, dir);
		// welches Feld wann ?

		// fügt das Schiff dem Spielfeld hinzu
		theField.updateFieldOnSet(actualship);
		// Debug Kram System.out.println("Setting a Ship");
		System.out.println("SChiffe gesetzt: " + countNumOfShips);
		return actualship;

	}

	/**
	 * generiert einen Schuss
	 * 
	 * @throws InvalideEingabeException
	 */
	public Shot shot(int x, int y) throws InvalideEingabeException {
		shot = new Shot(x, y);

		validCoord(x);
		validCoord(y);
		shot.calculateHit(x, y, theField.getSingleField(x, y));
		//das beschossene Feld bekommt ein Upate
		theField.updateFieldOnHit(shot);
		shot.setWin(theField);
		ShotField.shoted(x, y); // updated das Schussfeld, damit der entsprechende Spieler weiß, auf welches
								// Koords er schon geschossen hat.
		// debug Kram System.out.println("shot auf: x: " + x + "y: " + y);

		return shot;

	}

///////////////////// Hilfsmethoden////////////////////
	/**
	 * Überprüft, ob die Eingabe innerhalb der gegebenen Parameter liegt
	 * 
	 * @param c Eingabe der Koordinate
	 * @throws Exception falls die Eingabe außerhalb der Parameter liegt
	 */
	private void validCoord(int c) throws InvalideEingabeException {
		if (c < 1 || c > 10) {
			throw new InvalideEingabeException();
		}
	}

	/**
	 * Überprüft die Länge des Schiffes ein Schiff kann zwischen 2 und 5 Lang sein
	 * 
	 * ======Warum, diese nicht geht und die mit den vier if-Statements, entzieht
	 * sich meiner Kenntnis^^ selbes gilt für "enoughShips" -> liegts am Switch Case
	 * ?
	 * 
	 * @param length Länge des Schiffs
	 * @Throw throws invalide Länge als Exception
	 */
	private void validLength(Ship ship, int length) throws invalideLaengenEingabeException {
		switch (length) {
		case 2:
			if (ship != Ship.SUBMARINE) {
				throw new invalideLaengenEingabeException();
			}
			break;
		case 3:
			if (ship != Ship.DESTROYER) {
				throw new invalideLaengenEingabeException();
			}
			break;
		case 4:
			if (ship != Ship.CRUISER) {
				throw new invalideLaengenEingabeException();
			}
			break;
		case 5:
			if (ship != Ship.BATTLESHIP) {
				throw new invalideLaengenEingabeException();
			}
			break;
		default:
			throw new invalideLaengenEingabeException();

		}
	}

	/**
	 * alternative
	 * 
	 * @param ship
	 * @param length
	 * @throws invalideLaengenEingabeException
	 */
	private void alternativeValidLength(Ship ship, int length) throws invalideLaengenEingabeException {
		if (ship == Ship.BATTLESHIP && length != 5 || ship == Ship.CRUISER && length != 4
				|| ship == Ship.DESTROYER && length != 3 || ship == Ship.SUBMARINE && length != 2) {
			throw new invalideLaengenEingabeException();
		}
	}

	/**
	 * überprüft, ob das Schiff am Rand irgendwo rausragt
	 * 
	 * @param x         X Koordinate
	 * @param y         Y Koordinate
	 * @param length    des aktuellen Schiffs
	 * @param direction Richtung des SChiffes, true = vertical, false = horizontal
	 * @throws InvalideSchiffSetPositionExecption
	 */
	private void validBorderPositions(int x, int y, int length, boolean direction)
			throws InvalideSchiffSetPositionExecption {
		// ragt nach rechts raus
		if (x + (length - 1) > 10 && direction == false) {
			throw new InvalideSchiffSetPositionExecption();
			// ragt nach unten raus
		} else if (y + (length - 1) >10 && direction == true) {
			throw new InvalideSchiffSetPositionExecption();
		}

	}

	/**
	 * checkt bei jedem gesetzten SChiff, ob nicht schon zuviele Schiffe gesetzt
	 * wurden
	 * 
	 * @param ship das aktuelle Schiff
	 * @throws zuVieleSchiffeException falls ein schiff zuviel gesetzt wird
	 */
	private void enoughShips(Ship ship) throws zuVieleSchiffeException {
		switch (ship) {
		case SUBMARINE:
			countSubmarines++;
			countNumOfShips++;
			if (countSubmarines > NUM_OF_SUBMARINES || countNumOfShips >= TOTAL_NUM_OF_Ships) {
				throw new zuVieleSchiffeException();
			}
			break;
		case DESTROYER:
			countDestroyers++;
			countNumOfShips++;
			if (countDestroyers > NUM_OF_DESTROYERS || countNumOfShips >= TOTAL_NUM_OF_Ships) {
				throw new zuVieleSchiffeException();
			}
			break;
		case CRUISER:
			countCruisers++;
			countNumOfShips++;
			if (countCruisers > NUM_OF_CRUISERS || countNumOfShips >= TOTAL_NUM_OF_Ships) {
				throw new zuVieleSchiffeException();
			}
			break;
		case BATTLESHIP:
			countBattleship++;
			countNumOfShips++;
			if (countBattleship > NUM_OF_BATTLESHIPS || countNumOfShips >= TOTAL_NUM_OF_Ships) {
				throw new zuVieleSchiffeException();
			}
			break;

		}
	}

	/**
	 * 
	 * @param ship
	 * @throws zuVieleSchiffeException
	 */
	private void alternativeEnoughShips(Ship ship) throws zuVieleSchiffeException {
		if (ship == Ship.BATTLESHIP) {
			countBattleship++;
			if (countBattleship > NUM_OF_BATTLESHIPS) {
				throw new zuVieleSchiffeException();
			}
		} else if (ship == Ship.CRUISER) {
			countCruisers++;
			if (countCruisers > NUM_OF_CRUISERS) {
				throw new zuVieleSchiffeException();
			}
		} else if (ship == Ship.DESTROYER) {
			countDestroyers++;
			if (countDestroyers > NUM_OF_DESTROYERS) {
				throw new zuVieleSchiffeException();
			}
		} else if (ship == Ship.SUBMARINE) {
			countSubmarines++;
			if (countSubmarines > NUM_OF_SUBMARINES) {
				throw new zuVieleSchiffeException();
			}
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param shiplength
	 * @param direction
	 * @throws SchiffSetFeldBelegtException
	 */

	private void checkTakenField(int x, int y, int shiplength, boolean direction) throws SchiffSetFeldBelegtException {
		if (direction == false) {
			for (int i = x; i < x + shiplength; i++) {
				if (theField.getSingleField(i, y) == true) {
					throw new SchiffSetFeldBelegtException();
				}
			}
		} else {
			for (int i = y; i < y + shiplength; i++) {
				if (theField.getSingleField(x, i) == true) {
					throw new SchiffSetFeldBelegtException();
				}
			}
		}
	}

////////////////////// Getter //////////////////////
	public Field getField() {
		return theField;
	}

	public int getCountOfShips() {
		return countNumOfShips;
	}

	public Field getTheField() {
		return theField;
	}
	public Field getShotField() {
		return ShotField;
	}

	@Override
	public Shot getShot() {
		return shot;
	}

	@Override
	public int getShipsCount() {
		
		return TOTAL_NUM_OF_Ships;
	}

}
