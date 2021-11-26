package ConsoleInterface;

import java.io.BufferedReader;
import java.util.Random;
import java.util.Scanner;

import SchiffeVersenken.*;
import views.ConsoleView;

public class MainGame {
	// brauche ich die Variablen ?
	private SchiffeVersenken pOne;
	private SchiffeVersenken pTwo;
	ConsoleView cv;

	// final strings schreiben !
	// clear methode in SChiffeVersenken falls ein Idiot das Feld so zubaut, dass
	// man nicht alle Schiff setzen kann/ remove

	private final String INPUT_LINE_EXPLANATION = "Bitte gib die Koordinaten, Schiffsnamen und Ausrichtung,\ngetrennt einem Komma, an: "
			+ "X-Koordinate, Y-Koordinate, Namen, Ausrichtung";
	private final String INPUT_EXPLANATION = "X und Y Koordinaten: Zahl zwischen 1 und 10\n"
			+ "Name: battleship,cruiser,destroyer,submarine\n"
			+ "Ausrichtung: v f�r vertikale Ausrichtung, h f�r horizontale";
	private final String ENTER = "enter: ";
	private final String ALL_SHIPS_SET = "Es sind bereits alle Schiffe gesetzt worden.";
	private final String KEIN_SCHIFFSNAME_ERKANNT = "Der eingegebene Schiffsname wurde nicht erkannt";
	private final String KEINE_AUSRICHTUNG_ERKANNT = "Die eingegebene Ausrichtung des Schiffes ist unbekannt!";
	private final String UENGULTIGE_EINGABE = "die Eingabe ist ung�ltig!";

	public MainGame(SchiffeVersenken pOne, SchiffeVersenken pTwo) {
		this.pOne = pOne;
		this.pOne = pTwo;

	}

	/**
	 * "Startbildschirm" Playerauswahl und erkl�rte Befehle
	 * 
	 * @param p1 SchiffeVersenken f�r den einen Spieler
	 * @param p2 SchiffeVersenken f�r den anderen
	 */
	public void startScreen(SchiffeVersenken p1, SchiffeVersenken p2) {

		Random random = new Random();
		boolean startingPlayer = random.nextBoolean();

		System.out.println("------Willkommen zu Schiffe Versenken------ \n"
				+ "------------------------------------------ \n" + "Jeder Spieler bekommt ein Feld, der Spieler\n"
				+ "1 - Spiel starten\n" + "0 - Spiel verlassen\n" + "bitte eingeben:");

		int gameState = 0;
		// Spiel starten oder schlie�en
		Scanner scanner = new Scanner(System.in);
		while (true) {
			if (scanner.hasNextInt()) {
				gameState = scanner.nextInt();
				if (gameState == 0 || gameState == 1) {
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
				// Spieler 1 beginnt und das Spiel l�uft
				runGame(p1, p2);

			} else {
				System.out.println("es beginnt Spieler 2!");
				// Spieler 2 beginnt, Spiel l�uft
				runGame(p2, p1);
			}
			break;
		case 0:
			System.out.println("Tschau Kakao!");
			System.exit(0);
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
		settingPhase(p1); // set the ships
		settingPhase(p2);
		shotingPhase(p1, p2); // shoting to win
	}

	/**
	 * l�uft, bis beide Spieler ihr Feld mit Schiffen besetzt haben
	 * 
	 * @param player
	 */
	private void settingPhase(SchiffeVersenken player) {
		boolean set = true;

		Scanner readSet = new Scanner(System.in);
		String input;

		System.out.println(INPUT_LINE_EXPLANATION);
		System.out.println(INPUT_EXPLANATION);
		// Schiff setzen
		while (set) {
			/*
			 * setShip: xKoord yKoord Name Ausrichtung xKoord,yKoord == ints [1-10] Name ==
			 * Name [Battleship, Submarine,Cruiser, Destroyer] Ausrichtung == h,v [=
			 * horizontal, vertical]
			 */
			try {
				System.out.println(ENTER);
				 input = readSet.next();
				setInputToSetShip(player, input);
			} catch (Exception zuVieleSchiffeException) {
				System.out.println(ALL_SHIPS_SET);
				break;
				// hier m�ssen mehrere Catchbl�cke hin ! f�r jede geworfene Exception einer !

			}

		}

	}

	/**
	 * l�uft, bis jemand gewonnen hat
	 * 
	 * @param p1
	 * @param p2
	 */
	private void shotingPhase(SchiffeVersenken p1, SchiffeVersenken p2) {

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
		if (temp.length != 4) {
			System.out.println(UENGULTIGE_EINGABE);
			return false;
		}
		int x = Integer.parseInt(temp[0]); // x und y Koordinaten rauslesen
		int y = Integer.parseInt(temp[1]);
		temp[2].toLowerCase(); // SChiffsnamen, und damit die L�nge rauslesen
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

}