package SchiffeVersenken;

/**
 * Vorbereitung, Spiel, Spielende
 * 
 * 
 * Regelwerk:
 * 
 * 1. Schiffe d�rfen nicht aneinandersto�en 2. Schiffe sind immer gerade
 * (vertikal bzw horizontal), nicht �ber Eck, mit Ausbuchtungen oder diagonal 3.
 * Schiffe: 
 * ein BATTLESHIP(5 lang), 
 * zwei CRUISER(4 lang),
 * drei DESTROYER(3 lang),
 * vier SUBMARINE(2 lang)
 * 
 * 4. Es wird immer abwechselnd geschossen, Bei Treffer darf erneut geschossen
 * werden.
 * 
 * 
 * Ablauf:
 * 
 * => Spielaufbau, jeder setzt seine Schiffe (vgl. Punkt 3) => Abwechselndes
 * Schie�en bis die Flotte eines Spielers vernichtet ist.
 * 
 *
 * Feld 10x10 == 1-10(x), A-J(y)
 * 
 */

public interface SchiffeVersenken {

	/**
	 * Setzt ein Schiff an die daf�r ausgew�hlte Stelle
	 * 
	 * @param x      X Koordinate vom Bug
	 * @param y      Y Koordinate vom Bug, in Form der BuchstabenKoordinate
	 * @param ship   gew�hltes Schiff - Name
	 * @param length L�nge des gew�hlten Schiffes
	 * @param dir    Direction: Ausrichtung vom Schiff (m�gliche: horizontal,
	 *               vertikal)
	 * @true = vertical
	 * @false = horiontal
	 * @throws invalideLaengenEingabeException falsche Schiffsl�nge
	 * @throws InvalideEingabeException        falls die Eingabe der Koordinaten
	 *                                         au�erhalb des Spiels liegt.
	 * @throws SchiffSetFeldBelegtException    auf dem Feld befindet sich bereits
	 *                                         ein Schiff
	 * @throws @throws
	 * @deprecated
	 */
	oneShip setShip(int x, int y, Ship ship, int length, boolean dir)
			throws InvalideEingabeException, invalideLaengenEingabeException, SchiffSetFeldBelegtException, 
			zuVieleSchiffeException, InvalideSchiffSetPositionExecption;

	/**
	 * Ein Ziel setzt sich aus der Buchstaben(x)Achse und der Zahlen(y)Achse
	 * zusammen = "Umrechnung" von Buchstaben in ints.
	 * 
	 * Schie�t auf eine bestimmte Stelle die ein Spieler gew�hlt hat.
	 * 
	 * @param x Zahl, auf die geschossen wird
	 * @param y Zahl auf die geschosssen wird
	 */
	Shot shot(int x, int y)throws InvalideEingabeException;
	
}
