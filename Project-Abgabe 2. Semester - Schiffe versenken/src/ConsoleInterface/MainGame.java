package ConsoleInterface;

import java.util.Random;
import java.util.Scanner;

import SchiffeVersenken.*;
import views.ConsoleView;

public class MainGame {

	private SchiffeVersenken pOne;
	private SchiffeVersenken pTwo;
	ConsoleView cv;
//TODO
	// final strings schreiben !
	// String, dass die EingabeBefehle erklärt
	// clear methode in SChiffeVersenken falls ein Idiot das Feld so zubaut, dass
	// man nicht alle Schiff setzen kann/ remove

	private final String CUT = "------------------------------------------";
	private final String INPUT_LINE_EXPLANATION = "Bitte gib die Koordinaten, Schiffsnamen und Ausrichtung,\ngetrennt einem Komma, an: "
			+ "X-Koordinate, Y-Koordinate, Namen, Ausrichtung";
	private final String INPUT_EXPLANATION = "X und Y Koordinaten: Zahl zwischen 1 und 10\n"
			+ "Name: battleship,cruiser,destroyer,submarine\n"
			+ "Ausrichtung: v für vertikale Ausrichtung, h für horizontale";
	private final String RULES = "1 - Schiffe dürfen sich nicht ueberkreuzen\n"
			+ "2 - Schiffe sind immer gerade ausgerichtet (vertikal bzw. horizontal)\n"
			+ "3 - es gibt insegesamt 10 Schiffe, in den Klammern ist jeweils die Laenge angegeben:\n"
			+ "----ein Battleship (5)\n" + "----zwei Cruiser (4)\n" + "----drei Destroyer (3)\n"
			+ "----vier Submarines (2)\n"
			+ "4 - Es wird immer abwechselnd geschossen, trifft ein Spieler darf er noch einmal schießen";
	private final String RULES_NAVIGATION = "1 - Regelwerk schließen\n" + "0 - Spiel beenden";

	private final String ENTER = "enter: ";
	private final String ALL_SHIPS_SET = "Es sind bereits alle Schiffe gesetzt worden.";
	private final String KEIN_SCHIFFSNAME_ERKANNT = "Der eingegebene Schiffsname wurde nicht erkannt";
	private final String KEINE_AUSRICHTUNG_ERKANNT = "Die eingegebene Ausrichtung des Schiffes ist unbekannt!";
	private final String UENGULTIGE_EINGABE = "die Eingabe ist ungültig!";
	private final String INVALIDE_EINGABE = "Die X oder Y Koordinate ist ungültig, valide Werte liegen zwischen 1 und 10.";
	private final String BELEGTES_FELD = "Das Schiff überlappt mit einem anderen Schiff, sie dürfen nicht übereinanderliegen.";
	private final String SCHIFF_UEBER_BORDER = "Das Schiff ragt über die Spielfeldkante hinaus !";
	private final String EXIT = "Möchtest du das Spiel wirklich beenden ?: 0 = exit, alle anderen Zahlen = weiterspielen";

	private final String STARTSCREEN = "------Willkommen zu Schiffe Versenken------ \n"
			+ "------------------------------------------ \n" + "Jeder Spieler bekommt ein Feld, der Spieler\n"
			+ "1 - Spiel starten\n" + "2 - Regelwerk\n" + "0 - Spiel verlassen\n";
	private final String WON = "Du hast gewonnen, Gratulation";
	private final String GOODBYE = "Spiel beendet!";

	public MainGame(SchiffeVersenken pOne, SchiffeVersenken pTwo) {
		this.pOne = pOne;
		this.pOne = pTwo;

	}

	/**
	 * "Startbildschirm" Playerauswahl und erklärte Befehle
	 * 
	 * @param p1 SchiffeVersenken für den einen Spieler
	 * @param p2 SchiffeVersenken für den anderen
	 */
	public void startScreen(SchiffeVersenken p1, SchiffeVersenken p2) {

		Random random = new Random();
		boolean startingPlayer = random.nextBoolean();

		System.out.println(STARTSCREEN);
		System.out.println(ENTER);

		int gameState = 0;
		// Spiel starten oder schließen
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
			if (startingPlayer) {
				System.out.println("es beginnt Spieler 1!");
				// Spieler 1 beginnt und das Spiel läuft
				runGame(p1, p2);
				startScreen(p1, p2);

			} else {
				System.out.println("es beginnt Spieler 2!");
				// Spieler 2 beginnt, Spiel läuft
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
	 * wird im startscreen entschieden laufendes Spiel, Überkreuzung, der
	 * Spielfelder läuft bis zum Gewinn der einen Partei
	 * 
	 * @param p1 Spieler 1, er beginnt
	 * @param p2 Spieler 2, beginnt nie
	 */
	private void runGame(SchiffeVersenken p1, SchiffeVersenken p2) {
		cv = new ConsoleView(); // initialize the view
		settingPhase(p1); // set the ships
		settingPhase(p2);
		shotingPhase(p1, p2); // shoting to win
	}

	/**
	 * läuft, bis beide Spieler ihr Feld mit Schiffen besetzt haben
	 * 
	 * @param player
	 */
	private void settingPhase(SchiffeVersenken player) {
		boolean set = true;

		String input;

		System.out.println(INPUT_LINE_EXPLANATION);
		System.out.println(INPUT_EXPLANATION);
		System.out.println(CUT);
		// Schiff setzen
		while (set) {
			/*
			 * setShip: xKoord yKoord Name Ausrichtung xKoord,yKoord == ints [1-10] Name ==
			 * Name [Battleship, Submarine,Cruiser, Destroyer] Ausrichtung == h,v [=
			 * horizontal, vertical]
			 */
			try {
				System.out.println(ENTER);
				input = readInput();
				setInputToSetShip(player, input);
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
	 * läuft, bis jemand gewonnen hat
	 * 
	 * @param p1
	 * @param p2
	 */
	private void shotingPhase(SchiffeVersenken p1, SchiffeVersenken p2) {

		boolean win = false;
		while (win == false) {
			// exit Bedingung
			if (p1.getShot().getWon() || p2.getShot().getWon()) {
				win = true;
			}

			System.out.println(WON);
		}  

	}

	/**
	 * 
	 * @param player
	 * @param in
	 * @return
	 * @throws InvalideEingabeException
	 * @throws invalideLaengenEingabeException
	 * @throws SchiffSetFeldBelegtException
	 * @throws zuVieleSchiffeException
	 * @throws InvalideSchiffSetPositionExecption
	 */
	private boolean setInputToSetShip(SchiffeVersenken player, String in)
			throws InvalideEingabeException, invalideLaengenEingabeException, SchiffSetFeldBelegtException,
			zuVieleSchiffeException, InvalideSchiffSetPositionExecption {
		// in bspw: 5 5 Submarine v
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
		temp[2].toLowerCase(); // SChiffsnamen, und damit die Länge rauslesen
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
		cv.updateField(player.getField().getWholeField());
		return true;
	}

	private boolean setInputToSHot(String input) {

	}

	/*
	 * Beendet das Programm vorzeitig, falls nötig
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

	private String readInput() {
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}

	/**
	 * gibt das Regelwerk sowie die Möglichkeit zurück ins Hauptmenue zu kehren aus-
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
			System.out.println(STARTSCREEN);
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
