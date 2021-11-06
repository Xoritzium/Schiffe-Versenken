package SchiffeVersenken;

/**
 * Vorbereitung, Spiel, Spielende
 * 
 * 
 * Regelwerk:
 * 
 * 1. Schiffe dürfen nicht aneinanderstoßen 2. Schiffe sind immer gerade
 * (vertikal bzw horizontal), nicht über Eck, mit Ausbuchtungen oder diagonal 3.
 * Schiffe: ein BATTLESHIP(5), zwei CRUISER(4), drei DESTROYER(3), vier SUBMARINE(2)
 * 
 * 4. Es wird immer abwechselnd geschossen, Bei Treffer darf erneut geschossen
 * werden.
 * 
 * 
 * Ablauf:
 * 
 * => Spielaufbau, jeder setzt seine Schiffe (vgl. Punkt 3) => Abwechselndes
 * Schießen bis die Flotte eines Spielers vernichtet ist.
 * 
 *
 * Feld 10x10 == 1-10, A-J
 * 
 * //////////////////////// Vorlesung 1,11,21
 * //////////////////////////////////// Tests: Reihenfolge, Spielseite, Gewinner
 * abfragen testen, siehe tic tac toe schön nach junit richten !
 */

public interface SchiffeVersenken {

	/**
	 * Setzt ein Schiff an die dafür ausgewählte Stelle
	 * 
	 * @param x      X Koordinate vom Bug
	 * @param y      Y Koordinate vom Bug, in Form der BuchstabenKoordinate
	 * @param ship   gewähltes Schiff - Name
	 * @param length Länge des gewählten Schiffes
	 * @param dir    Direction: Ausrichtung vom Schiff (mögliche: horizontal,
	 *               vertikal)
	 *               @true = vertical
	 *               @false = horiontal
	 * @throws InvalideLaengenEingabeException falsche Schiffslänge
	 * @throws InvalideEingabeException        falls die Eingabe der Koordinaten
	 *                                         außerhalb des Spiels liegt.
	 */
	oneShip setShip(int x, int y, Ship ship, int length, boolean dir)
			throws InvalideEingabeException, InvalideLaengenEingabeException;

	/**
	 * Ein Ziel setzt sich aus der Buchstaben(x)Achse und der Zahlen(y)Achse
	 * zusammen = "Umrechnung" von Buchstaben in ints.
	 * 
	 * Schießt auf eine bestimmte Stelle die ein Spieler gewählt hat.
	 * 
	 * @param x Zahl, auf die geschossen wird
	 * @param y Zahl auf den geschosssen wird
	 */
	int[] shot(int x, int y);

}
