package SchiffeVersenken;

public class Shot {

	boolean treffer;
	// springt auf true beim letzten Treffer
	boolean won = false;
	// Schuss Koordinaten
	int x;
	int y;

	public Shot(int x, int y) {

		this.x = x;
		this.y = y;
	}

	/**
	 * berechnet bei jedem Schuss, ob es sich um einen Treffer handelt und gibz
	 * entsprechend True/False zurück
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean calculateHit(int x, int y, boolean infield) {
		if (infield == true) {
			return treffer = true;
		}
		return treffer = false;
	}
	/**
	 *  gibt solange false zurück, solange noch Schiffsreste auf dem Feld liegen

	 * @param field
	 * @return
	 */
	
	/*
	private boolean setWin(Field field) {
		for (int i = 0; i < field.getFieldLength(); i++) {
			for (int j = 0; j < field.getFieldLength(); j++) {
				if (field.getSingleField(i, j) == true) {
					return won =false;
				}

			}

		}
		return won = true;
	}
*/
	
	
	/**
	 * returned true, wenn der nächste Spieler dran ist, also nicht getroffen hat
	 * returned false, wenn man getroffen hat und nochmal dran ist
	 * 
	 * @return
	 */
	public boolean getNextPlayer() {
		// TODO Auto-generated method stub
		return false;
	}

	//////////// Getter///////////////
	public boolean getTreffer() {
		return treffer;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getWon() {
		return won;
	}
////Setter
	public void setWon(boolean won) {
		this.won = won;
	}
	
}
