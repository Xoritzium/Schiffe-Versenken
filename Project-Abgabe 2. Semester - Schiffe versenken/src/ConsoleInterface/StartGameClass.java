package ConsoleInterface;

import SchiffeVersenken.*;


public class StartGameClass {

	public static void main(String[] args) {

		SchiffeVersenken s1 = new SchiffeVersenkenImpl();
		SchiffeVersenken s2 = new SchiffeVersenkenImpl();
		MainGame ps = new MainGame(s1, s2);
		ps.startScreen(s1, s2);
		
	}
}
