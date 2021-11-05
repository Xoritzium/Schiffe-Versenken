package SchiffeVersenken;



public class SchiffeVersenkenImpl implements SchiffeVersenken {
// braucht man das Enum ship ?
	public oneShip setShip(int x, int y, Ship ship, int length, boolean dir) throws InvalideEingabeException, InvalideLaengenEingabeException {
		
		// Validierung der Eingaben
		validCoord(x);
		validCoord(y);
		validLength(length);

		int[] coords = { x, y };

		// generiert das gesamte Schiff
		oneShip actualship = new oneShip(coords, ship, length, dir);

		return actualship;

	}

	public int[] shot(int x, int y) {
		int[] coords = { x, y };
		return coords;

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
	 * @param l Länge des Schiffs
	 * @throws invalide Länge
	 */
	private void validLength(int l) throws InvalideLaengenEingabeException {
		if (l < 2 || l > 5) {
			throw new InvalideLaengenEingabeException();
		}

	}



}
