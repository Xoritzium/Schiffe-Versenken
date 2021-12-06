package ConsoleInterface;

import java.util.Random;
import java.util.Scanner;

import SchiffeVersenken.*;
import views.ConsoleView;
import views.Views;

public class MainGame {
	// Spielfeld und Logik des ersten Spielers
	private SchiffeVersenken pOne;
	// Spielfeld und Logik des zweiten Spielers
	private SchiffeVersenken pTwo;
	// eine View, auf der Konsole
	Views cv;
	//

	// Alle n�tigen Strings um das User-Interface verst�ndlich zu benutzen
	private final String CUT = "------------------------------------------";
	private final String INPUT_LINE_EXPLANATION = "Bitte gib die Koordinaten, Schiffsnamen und Ausrichtung,\ngetrennt von einem Komma an: "
			+ "X-Koordinate, Y-Koordinate, Namen, Ausrichtung";
	private final String INPUT_EXPLANATION = "X und Y Koordinaten: Zahl zwischen 1 und 10\n"
			+ "Name: battleship,cruiser,destroyer,submarine\n"
			+ "Ausrichtung: v f�r vertikale Ausrichtung, h f�r horizontale";
	private final String RULES = "1 - Schiffe d�rfen sich nicht ueberkreuzen\n"
			+ "2 - Schiffe sind immer gerade ausgerichtet (vertikal bzw. horizontal)\n"
			+ "3 - es gibt insegesamt 10 Schiffe, in den Klammern ist jeweils die Laenge angegeben:\n"
			+ "----ein Battleship (5)\n" + "----zwei Cruiser (4)\n" + "----drei Destroyer (3)\n"
			+ "----vier Submarines (2)\n"
			+ "4 - Es wird immer abwechselnd geschossen, trifft ein Spieler darf er noch einmal schie�en";
	private final String RULES_NAVIGATION = "1 - Regelwerk schlie�en\n" + "0 - Spiel beenden";
	private final String SHOT_TO = "Schuss auf:  (Eingabe: x,y)";
	private final String ENTER = "enter: ";
	private final String ALL_SHIPS_SET = "Es sind bereits alle Schiffe gesetzt worden.";
	private final String KEIN_SCHIFFSNAME_ERKANNT = "Der eingegebene Schiffsname wurde nicht erkannt";
	private final String KEINE_AUSRICHTUNG_ERKANNT = "Die eingegebene Ausrichtung des Schiffes ist unbekannt!";
	private final String UENGULTIGE_EINGABE = "die Eingabe ist ung�ltig!";
	private final String INVALIDE_EINGABE = "Die X oder Y Koordinate ist ung�ltig, valide Werte liegen zwischen 1 und 10.";
	private final String BELEGTES_FELD = "Das Schiff �berlappt mit einem anderen Schiff, sie d�rfen nicht �bereinanderliegen.";
	private final String SCHIFF_UEBER_BORDER = "Das Schiff ragt �ber die Spielfeldkante hinaus !";
	private final String EXIT = "M�chtest du das Spiel wirklich beenden ?: 0 = exit, alle anderen Zahlen = weiterspielen";

	private final String TREFFER = "Treffer, du kannst erneut schie�en!";
	private final String KEIN_TREFFER = "Kein Treffer, dein Gegner ist dran!";
	private final String STARTSCREEN = "------Willkommen zu Schiffe Versenken------ \n"
			+ "------------------------------------------ \n" + "Jeder Spieler bekommt ein Feld, der Spieler\n"
			+ "1 - Spiel starten\n" + "2 - Regelwerk\n" + "0 - Spiel verlassen\n";
	private final String WON = "Du hast gewonnen, Gratulation";
	private final String GOODBYE = "Spiel beendet!";

// Konstructor, nur noetig um das Spiel zu starten
	public MainGame(SchiffeVersenken pOne, SchiffeVersenken pTwo) {
		this.pOne = pOne;
		this.pTwo = pTwo;

	}

	/**
	 * "Startbildschirm" Playerauswahl und erkl�rte Befehle
	 * 
	 * @param p1 SchiffeVersenken f�r den einen Spieler
	 * @param p2 SchiffeVersenken f�r den Anderen
	 */
	public void startScreen(SchiffeVersenken p1, SchiffeVersenken p2) {

		System.out.println(STARTSCREEN);
		System.out.println(ENTER);

		int gameState = 0;
		// Spiel starten oder schlie�en
		Scanner scanner = new Scanner(System.in);
		while (true) {
			if (scanner.hasNextInt()) {
				gameState = scanner.nextInt();
				if (gameState == 0 || gameState == 1 || gameState == 2) {
					break;
				} else {
					System.out.println("Bitte einen validen Wert eingeben ! [0-1]");
				}
			}
		}

		switch (gameState) {
		case 1:
			Random random = new Random();
			boolean startingPlayer = random.nextBoolean();

			if (startingPlayer) {
				System.out.println("es beginnt Spieler 1!"); // debug, sp�ter Verteilung, welcher Rechner beginnt
				// Spieler 1 beginnt und das Spiel l�uft
				runGame(p1, p2);
				startScreen(p1, p2);

			} else {
				System.out.println("es beginnt Spieler 2!");
				// Spieler 2 beginnt, Spiel l�uft
				runGame(p2, p1);
				startScreen(p1, p2);
			}
			break;
		case 0:
			System.out.println(GOODBYE);
			System.exit(0);
			break;
		case 2:
			rules();
			break;
		}

	}

	/**
	 * p1 ist immer der beginnende Spieler, welcher Spieler in p1 reingegeben wird,
	 * wird im startscreen entschieden laufendes Spiel, �berkreuzung, der
	 * Spielfelder l�uft bis zum Gewinn der einen Partei
	 * 
	 * @param p1 Spieler 1, er beginnt
	 * @param p2 Spieler 2, beginnt nie
	 */
	private void runGame(SchiffeVersenken p1, SchiffeVersenken p2) {
		cv = new ConsoleView(); // initialize the view
		settingPhase(p1); // set the ships starting player
		settingPhase(p2); // set ships for second player
		shotingPhase(p1, p2); // shoting to win
	}

	/**
	 * l�uft, bis beide Spieler ihr Feld mit Schiffen besetzt haben
	 * 
	 * @param player Spielfeld des aktuellen Spielers, der an der Reihe ist, sein
	 *               Feld vorzubereiten
	 */
	private void settingPhase(SchiffeVersenken player) {
		int count = 0;

		String input;

		System.out.println(INPUT_LINE_EXPLANATION);
		System.out.println(INPUT_EXPLANATION);
		System.out.println(CUT);
		// Schiff setzen
		while (count < player.getShipsCount()) {
			/*
			 * setShip: xKoord yKoord Name Ausrichtung xKoord,yKoord == ints [1-10] Name ==
			 * Name [Battleship, Submarine,Cruiser, Destroyer] Ausrichtung == h,v [=
			 * horizontal, vertical]
			 */
			try {
				System.out.println(ENTER);
				input = readInput();
				// setInputToSetShip(player, input);
				if (setInputToSetShip(player, input))
					count++;
			} catch (zuVieleSchiffeException e) {
				System.out.println(ALL_SHIPS_SET);
				System.out.println(CUT);
				break;
			} catch (InvalideEingabeException e) {
				System.out.println(INVALIDE_EINGABE);
				System.out.println(CUT);
			} catch (invalideLaengenEingabeException e) {
				System.out.println(KEIN_SCHIFFSNAME_ERKANNT);
				System.out.println(CUT);

			} catch (SchiffSetFeldBelegtException e) {
				System.out.println(BELEGTES_FELD);
				System.out.println(CUT);

			} catch (InvalideSchiffSetPositionExecption e) {
				System.out.println(SCHIFF_UEBER_BORDER);
				System.out.println(CUT);

			}

		}

	}

	/**
	 * l�uft, bis jemand gewonnen hat, �bernimmt beide Spielfelder, da der
	 * schie�ende Spieler jeweils auf das Spielfeld der anderen schie�t.
	 * 
	 * @param p1 der Spieler, der anf�ngt, wer das ist, wird hier nicht entschieden
	 * @param p2 der darauf folgende Spieler
	 */
	private void shotingPhase(SchiffeVersenken p1, SchiffeVersenken p2) {
		boolean player = true;
		boolean win = false;
		Shot tempShot; // noetig zur Ausgabe von nextPlayer und getWon

		while (win == false) {
			// exit Bedingung
			try {
				if (player) { // player = true, player 1 ist dran
					System.out.println(SHOT_TO);
					System.out.println(ENTER);
					String input = readInput();
					tempShot = setInputToShot(player, input, p1, p2);
					// wenn spieler 1 schie�t, muss der Schuss auf das Feld von Spieler2
					if (tempShot.getTreffer()) {
						player = true;
						System.out.println(TREFFER);
						System.out.println(CUT);
					} else {
						System.out.println(KEIN_TREFFER);
						System.out.println(CUT);
						player = false;
					}
				} else { // player = false, Spieler 2 ist dran
					System.out.println(SHOT_TO);
					System.out.println(ENTER);
					String input = readInput();
					tempShot = setInputToShot(player, input, p2, p1);

					// wenn Spieler 2 schie�t muss der Schuss auf das Feld von p1 aufgerufen werden.
					if (tempShot.getTreffer()) {
						player = false;
						System.out.println(TREFFER);
						System.out.println(CUT);
					} else {
						System.out.println(KEIN_TREFFER);
						System.out.println(CUT);
						player = true;
					}
				}
				// exit Bedingung und Sieger
				if (tempShot.getWon()) {
					win = true;
					break;
				}
			} catch (InvalideEingabeException e) {
				System.out.println(INVALIDE_EINGABE);
				System.out.println(CUT);
			}

		}
		System.out.println(WON);

	}

	/**
	 * �bernimmt den Input und setzt das Schiff an gew�nschte Stelle
	 * 
	 * @param player Dies ist das Feld,auf dem der aktuelle Spieler sein Schiff
	 *               setzt
	 * @param in     Input von Koordinaten, Namen und Ausrichtung des Schiffes
	 * @return true wenn es keine Fehler in der Eingabe gab und false wenn Fehler
	 *         auftreten, der returnwert ist f�r das weitere Spiel jedoch irrelevant
	 * @throws InvalideEingabeException           Falsche Eingabe, beendet aktuelle
	 *                                            Eingabe
	 * @throws invalideLaengenEingabeException    Falsche Eingabe, kann hier jedoch
	 *                                            nicht auftreten
	 * @throws SchiffSetFeldBelegtException       Es wurde versucht ein Schiff auf
	 *                                            ein bereits belegtes Feld zu
	 *                                            platzieren
	 * @throws zuVieleSchiffeException            Es wurden bereits alle Schiffe
	 *                                            gesetzt
	 * @throws InvalideSchiffSetPositionExecption Das Schiff kann dort nicht
	 *                                            platziert werden weil es zum
	 *                                            Beispiel aus dem Spielfeld ragt
	 *                                            
	 *                                            Alle Exceptions werden
	 *                                            aufgefangen, ein entsprechender
	 *                                            hinweisender String ausgegeben und
	 *                                            es wird in die n�chste Iteration
	 *                                            gesprungen um erneut einen
	 *                                            SChiffssetzversuch zu starten
	 */
	private boolean setInputToSetShip(SchiffeVersenken player, String in)
			throws InvalideEingabeException, invalideLaengenEingabeException, SchiffSetFeldBelegtException,
			zuVieleSchiffeException, InvalideSchiffSetPositionExecption {
		// in bspw: 5,5,submarine,v
		String[] temp = in.split(",");

		if (in.equals("exit")) {
			exitProgram();
			return false;
		} else if (temp.length != 4) {
			System.out.println(UENGULTIGE_EINGABE);
			return false;
		}
		int x; // x und y Koordinaten rauslesen
		int y;
		try {
			x = Integer.parseInt(temp[0]);
			y = Integer.parseInt(temp[1]);
		} catch (NumberFormatException e) {
			System.out.println(INVALIDE_EINGABE);
			return false;
		}
		temp[2].toLowerCase(); // Schiffsnamen, und damit die L�nge rauslesen
		Ship ship = null;
		int length = 0;
		switch (temp[2]) {
		case "submarine":
			ship = ship.SUBMARINE;
			length = 2;
			break;
		case "destroyer":
			ship = ship.DESTROYER;
			length = 3;
			break;
		case "cruiser":
			ship = ship.CRUISER;
			length = 4;
			break;
		case "battleship":
			ship = ship.BATTLESHIP;
			length = 5;
			break;
		default:
			System.out.println(KEIN_SCHIFFSNAME_ERKANNT);
			return false;
		}
		boolean direction = false; // Ausrichtung, horizontal oder vertikal rauslesen
		switch (temp[3]) {
		case "v":
			direction = true;
			break;
		case "h":
			direction = false;
			break;
		default:
			System.out.println(KEINE_AUSRICHTUNG_ERKANNT);
			return false;
		}
		// Schiff mit eingelesenen Werten aufs Feld setzen.
		player.setShip(x, y, ship, length, direction);
		// update the Field
		cv.updateFieldOnSetShip(player.getField().getWholeField());
		return true;
	}

	/**Nimmt den Input in Form eines Strings und verarbeitet ihn in einen Schuss umzuwandeln
	 * 
	 * @param input eingegebener String
	 * @param actingPlayer der Spieler, der an der dran ist.
	 * @return den Schuss der generiert wurde, inklusive der Ver�nderungen im Spielfeld, die daraus resultieren
	 * @throws InvalideEingabeException Bei invalider Eingabe wird der aktuelle Loop beendet und ein entsprechender String ausgegeben
	 */
	private Shot setInputToShot(boolean activePlayer, String input, SchiffeVersenken actingPlayer,
			SchiffeVersenken shotedPlayer) throws InvalideEingabeException {
		// bspw: x,y = 5,2, oder exit
		if (input.equals("exit")) {
			exitProgram();
		}
		String[] temp = input.split(",");
		if (temp.length != 2) {
			return actingPlayer.shot(0, 0); // causes "InvalideEingabeException" which will end this loop of shooting
		}
		int x = 0;
		int y = 0;
		try {
			x = Integer.parseInt(temp[0]);
			y = Integer.parseInt(temp[1]);
		} catch (NumberFormatException e) {
			return actingPlayer.shot(0, 0); // causes "InvalideEingabeException" which will end actual loop of shooting
											// and jumps to the next
		}
		Shot tempShot = shotedPlayer.shot(x, y);
		// print own field + checkField
		cv.updateFieldOnShot(activePlayer, actingPlayer.getField().getWholeField(),
				shotedPlayer.getShotField().getWholeField());
		// Schuss auf den, der nicht dran ist
		return tempShot;

	}

	/*
	 * Beendet das Programm vorzeitig, falls n�tig oder gew�nscht
	 */
	private void exitProgram() {
		Scanner scanner = new Scanner(System.in);
		System.out.println(EXIT);
		int ex = scanner.nextInt();
		switch (ex) {
		case 0:
			System.out.println(GOODBYE);
			System.exit(0);
			break;
		default:
			break;
		}
	}
/**Wann immer ein InputString ben�tigt wird.
 * 
 * @return Input als String
 */
	private String readInput() {
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}

	/**
	 * gibt das Regelwerk sowie die Moeglichkeit zur�ck ins Hauptmenue zu kehren aus.
	 */
	private void rules() {
		System.out.println(RULES);
		System.out.println(CUT);
		System.out.println(RULES_NAVIGATION);
		System.out.println(ENTER);
		int returnthis = 0;
		try {

			returnthis = Integer.parseInt(readInput());
		} catch (NumberFormatException e) {
			System.out.println(CUT);
			System.out.println(UENGULTIGE_EINGABE);
			rules();
		}
		switch (returnthis) {
		case 1:
			startScreen(this.pOne, this.pTwo);
			break;
		case 0:
			exitProgram();
			break;
		default:
			System.out.println(UENGULTIGE_EINGABE);
			rules();
		}
	}
}
