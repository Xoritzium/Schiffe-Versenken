package views;

public class ConsoleView implements Views {

	/**
	 * initialisiert die Konsolenview
	 */
	public ConsoleView() {
		System.out.println("---- Initalizing the empty Field ----");
		updateFieldOnSetShip(new boolean[10][10]);
	}

	public void updateFieldOnSetShip(boolean[][] field) {
		System.out.println("-------------------------------- \n");
		System.out.println("1 2 3 4 5 6 7 8 9 10");
		System.out.println("--------------------");
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field.length; y++) {
				if (field[y][x] == true) {
					System.out.print("x ");
				} else {
					System.out.print(". ");
				}
			}

			System.out.print("|" + (x + 1) + " \n");
		}
		System.out.println("\n");
	}

	public void updateFieldOnShot(boolean aktivePlayer, boolean[][] field, boolean[][] checkField) {

		if (aktivePlayer) {
			System.out.println("-----------------Player 1-----------------------");
		} else {
			System.out.println("-----------------Player 2-----------------------");
		}
		System.out.println("  Your Set Ships	   ControlField of your shots");
		System.out.println("--------------------------------------------------------");
		System.out.println("1 2 3 4 5 6 7 8 9 10		1 2 3 4 5 6 7 8 9 10");
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field.length; y++) {
				if (field[y][x] == true) {
					System.out.print("x ");

				} else {
					System.out.print(". ");
				}
			}

			System.out.print("|" + (x + 1));
			System.out.print("		");
			for (int i = 0; i < checkField.length; i++) {
				if (checkField[i][x] == true) {
					System.out.print("o ");
				} else {
					System.out.print(". ");
				}
			}
			System.out.print("|" + (x + 1));
			System.out.print("\n");
		}
	}

}
