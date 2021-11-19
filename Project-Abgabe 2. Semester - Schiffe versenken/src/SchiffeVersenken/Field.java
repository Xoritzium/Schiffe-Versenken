package SchiffeVersenken;

public class Field {
	/*
	 * x*y true: da ist ein Schiff false: freier Platz,
	 */
	private boolean[][] field = new boolean[10][10];

	public Field() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				field[0][0] = false;
			}
		}
	}

	/**
	 * jedes Mal, wenn ein neues Schiff gesetzt wird, wird das Feld aktualisiert
	 * 
	 */
	public void updateFieldOnSet(oneShip ship) {
		int trueX = ship.getCoordX() - 1;
		int trueY = ship.getCoordY() - 1;
		if (ship.getDirection() == false) {
			for (int i = trueX; i < trueX + ship.getLength(); i++) {
				field[i][trueY] = true;
			}
		} else {
			for (int i = trueY; i < trueY + ship.getLength(); i++) {
				field[trueX][i] = true;
			}

		}

	}

	/**
	 * aktuallisiert die Schüsse, gerade bei Treffer
	 * 
	 * @param x
	 * @param y
	 */
	public void updateFieldOnHit(Shot shot) {
		int trueX = shot.getX() - 1;
		int trueY = shot.getY() - 1;
		int iterations = field.length * field.length;
		field[trueX][trueY] = false;
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field.length; y++) {
				if (field[x][y] == true) {
					break;
				}
				iterations--;
			}
		}
		if (iterations != 0) {
			shot.setWon(true);
		}

	}
///////// specialized getter/////////////

	// get a single field of the Field
	public boolean getSingleField(int x, int y) {
		int trueX = x - 1;
		int trueY = y - 1;
		return field[trueX][trueY];
	}

	public int getFieldLength() {
		return field.length;
	}
	public boolean[][] getWholeField(){
		return field;
	}
}
