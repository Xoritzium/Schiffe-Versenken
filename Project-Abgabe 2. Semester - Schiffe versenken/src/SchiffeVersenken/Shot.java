package SchiffeVersenken;

public class Shot {
	// Treffer oder nicht AENDERN !
	boolean treffer = false;
	// springt auf true beim letzten Treffer
	boolean won = false;
	// Schuss Koordinaten
	int x;
	int y;
	
	public Shot( int x, int y){
		
		this.x = x;
		this.y = y;
		
	}
	/** berechnet bei jedem Schuss, ob es sich um einen Treffer handelt und gibz
	 * entsprechend True/False zurück
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean calculateHit(int x, int y) {
		return treffer;
	}
	/**returned true, wenn der nächste Spieler dran ist, also nicht getroffen hat
	 * returned false, wenn man getroffen hat und nochmal dran ist
	 * 
	 * @return
	 */
	public boolean getNextPlayer() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	////////////Getter///////////////
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
}
