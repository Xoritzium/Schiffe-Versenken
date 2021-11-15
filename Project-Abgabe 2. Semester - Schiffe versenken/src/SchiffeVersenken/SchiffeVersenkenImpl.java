package SchiffeVersenken;

public class SchiffeVersenkenImpl implements SchiffeVersenken {

	private final int NUM_OF_SUBMARINES = 4;
	private final int NUM_OF_DESTROYERS = 3;
	private final int NUM_OF_CRUISERS = 2;
	private final int NUM_OF_BATTLESHIPS = 1;
	private int countSubmarines;
	private int countDestroyers;
	private int countCruisers;
	private int countBattleship;

	// SPielfelder SpielerA und SpielerB
	Field fieldA;
	Field fieldB;

	// braucht man das Enum ship ?

	/**
	 * allgemeiner Constructor
	 */
	public SchiffeVersenkenImpl() {
		fieldA = new Field();
		fieldB = new Field();
	}

	public oneShip setShip(int x, int y, Ship ship, int length, boolean dir)
			throws InvalideEingabeException, invalideLaengenEingabeException, SchiffSetFeldBelegtException,
			zuVieleSchiffeException, InvalideSchiffSetPositionExecption {

		// Validierung der Eingaben
		validCoord(x);
		validCoord(y);
		validLength(length, ship);
		validBorderPositions(x, y, length, dir);
		enoughShips(ship);
		checkTakenField(x, y, length, dir);

		int[] coords = { x, y };

		// generiert das gesamte Schiff
		oneShip actualship = new oneShip(coords, ship, length, dir);
		// welches Feld wann ?
		fieldA.updateFieldOnSet(x, y, length, dir);
		
		return actualship;

	}

	/**
	 * generiert einen Schuss
	 * @throws InvalideEingabeException 
	 */
	public Shot shot(int x, int y) throws InvalideEingabeException {
		Shot shot = new Shot(x,y);

		validCoord(x);
		validCoord(y);
		shot.calculateHit(x, y, fieldA.getSingleField(x, y));
		fieldA.updateFieldOnHit(shot);
		return shot;

	}

///////////////////// Hilfsmethoden////////////////////

	//////////////// Validierungsmethoden /////////

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
	 * @param length Länge des Schiffs
	 * @Throw throws invalide Länge als Exception
	 */
	private void validLength(int length, Ship ship) throws invalideLaengenEingabeException {
		switch (ship) {
		case SUBMARINE:
			if (length != 2)
				throw new invalideLaengenEingabeException();
		case DESTROYER:
			if (length != 3)
				throw new invalideLaengenEingabeException();
		case CRUISER:
			if (length != 4)
				throw new invalideLaengenEingabeException();
		case BATTLESHIP:
			if (length != 5)
				throw new invalideLaengenEingabeException();

		}

	}

	/**
	 * überprüft, ob das SChiff am Rand irgendwo rausragt
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
		if (x + (length - 1) >= 10 && direction == false) {
			throw new InvalideSchiffSetPositionExecption();
			// ragt nach unten raus
		} else if (y + (length - 1) >= 10 && direction == true) {
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
			if (countSubmarines > NUM_OF_SUBMARINES) {
				throw new zuVieleSchiffeException();
			}
		case DESTROYER:
			countDestroyers++;
			if (countDestroyers > NUM_OF_DESTROYERS) {
				throw new zuVieleSchiffeException();
			}
		case CRUISER:
			countCruisers++;
			if (countCruisers > NUM_OF_CRUISERS) {
				throw new zuVieleSchiffeException();
			}
		case BATTLESHIP:
			countBattleship++;
			if (countBattleship > NUM_OF_BATTLESHIPS) {
				throw new zuVieleSchiffeException();
			}

		}
	}

	private void checkTakenField(int x, int y, int shiplength, boolean direction) throws SchiffSetFeldBelegtException {
		if (direction == false) {
			for (int i = x; i < shiplength; i++) {
				if (fieldA.getSingleField(i, y) == true) {
					throw new SchiffSetFeldBelegtException();
				}
			}
		} else {
			for (int i = y; i < shiplength; i++) {
				if (fieldA.getSingleField(x, i) == true) {
					throw new SchiffSetFeldBelegtException();
				}
			}
		}
	}
}
