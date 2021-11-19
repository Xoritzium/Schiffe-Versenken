package SchiffeVersenken;

public class oneShip {
	

	// x und y Koordinaten
	private int coordX;
	private int coordY;

	// Schiffsname
	private Ship ship;
	// Schiffslänge
	private int length;
	// Schiffsausrichtung
	private boolean direction;

	public oneShip(int x, int y, Ship s, int length, boolean dir) {
		coordX = x;
		coordY = y;
		ship = s;
		this.length = length;
		direction = dir;
	}
	//////////////// Getters

	public int getCoordX() {
		return coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public Ship getShip() {
		return ship;
	}

	public int getLength() {
		return length;
	}

	public boolean getDirection() {
		return direction;
	}

}
