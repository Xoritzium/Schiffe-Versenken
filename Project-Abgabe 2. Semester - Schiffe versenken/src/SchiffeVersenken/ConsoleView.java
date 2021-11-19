package SchiffeVersenken;

public class ConsoleView {

	public ConsoleView() {
		System.out.println("---- Initalizing the empty Field ----");
		updateField(new boolean[10][10]);
			}

	public void updateField(boolean[][] field) {
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

}
