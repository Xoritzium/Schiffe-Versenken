package SchiffeVersenken;

public class SchiffeVersenkenImpl implements SchiffeVersenken {
// braucht man das Enum ship ?

	/**
	 * die Beiden Spielernamen, Wichtig f�r die �berkreuzgeschichte
	 * 
	 * @param p1 == Player One
	 * @param p2 == Player Two
	 */
	public SchiffeVersenkenImpl(String p1, String p2) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * allgemeiner Constructor
	 */
	public SchiffeVersenkenImpl() {
		// TODO Auto-generated constructor stub
	}

	public oneShip setShip(int x, int y, Ship ship, int length, boolean dir)
			throws InvalideEingabeException, invalideLaengenEingabeException,  SchiffSetFeldBelegtException,
			zuVieleSchiffeException, InvalideSchiffSetPositionExecption {

		// Validierung der Eingaben
		validCoord(x);
		validCoord(y);
		validLength(length, ship);

		int[] coords = { x, y };

		// generiert das gesamte Schiff
		oneShip actualship = new oneShip(coords, ship, length, dir);

		return actualship;

	}

	/**
	 * generiert einen Schuss
	 */
	public Shot shot(int x, int y) {
		return new Shot(x, y);

	}

///////////////////// Hilfsmethoden////////////////////

	//////////////// Validierungsmethoden /////////

	/**
	 * �berpr�ft, ob die Eingabe innerhalb der gegebenen Parameter liegt
	 * 
	 * @param c Eingabe der Koordinate
	 * @throws Exception falls die Eingabe au�erhalb der Parameter liegt
	 */
	private void validCoord(int c) throws InvalideEingabeException {
		if (c < 1 || c > 10) {
			throw new InvalideEingabeException();
		}
	}

	/**
	 * �berpr�ft die L�nge des Schiffes ein Schiff kann zwischen 2 und 5 Lang sein
	 * 
	 * @param l L�nge des Schiffs
	 * @Throw throws invalide L�nge
	 */
	private void validLength(int l, Ship ship) throws invalideLaengenEingabeException {
		switch (l) {
		case 2:
			if (ship != Ship.SUBMARINE)
				throw new invalideLaengenEingabeException();
		case 3:
			if (ship != Ship.DESTROYER)
				throw new invalideLaengenEingabeException();
		case 4:
			if (ship != Ship.CRUISER)
				throw new invalideLaengenEingabeException();
		case 5:
			if (ship != Ship.BATTLESHIP)
				throw new invalideLaengenEingabeException();

		}

	}

}
