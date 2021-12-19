package ConsoleInterface;

import SchiffeVersenken.*;


public class StartGameClass {

	public static void main(String[] args) {
 // stellt die benoetigten Spielerinstanzen zur Verfuegung und startet das Spiel
		SchiffeVersenken s1 = new SchiffeVersenkenImpl();
		SchiffeVersenken s2 = new SchiffeVersenkenImpl();
	
		MainGame ps = new MainGame(s1, s2); // Ein Rechner
		
		ps.startScreen(s1, s2);
	}
}
