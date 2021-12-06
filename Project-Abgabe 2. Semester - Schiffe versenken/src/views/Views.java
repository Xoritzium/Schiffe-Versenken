package views;

public interface Views {
	/**
	 * Die Views kann beliebig viele Views implementieren. momentan ist die einzige
	 * View eine grafische ConsolView, die auf der Konsole ausgegeben wird.
	 * Mögliche weitere View wäre via JFrame.
	 */

	/**
	 * Nach jedem gesetzten Schiff wird die "grafische" Ansicht aktualisiert und
	 * ausgegeben
	 * 
	 * @param field ein 2d-boolean Array, dass via true oder false angibt, ob auf
	 *              dem jeweiligen Feld ein Schiff plaziert ist/wurde oder nicht
	 * 
	 */
	public void updateFieldOnSetShip(boolean[][] field);

	/**
	 * Mit jedem Schuss wird sein eigenes Spielfeld aktualisiert und es wird
	 * ausgegeben, wie viel Schiff noch übrig ist, sowie wohin man bereits
	 * geschossen hat
	 * 
	 * @param aktivePlayer gibt an, welcher Spieler actuell dran ist.
	 * @param field        das eigene Spielfeld, seine eigenen SChiffe werden
	 *                     angezeigt.
	 * @param checkField   übergebenes Kontrollfeld, dass einem anzeigt, auf welche
	 *                     Felder man bereits geschossen hat
	 */
	public void updateFieldOnShot(boolean aktivePlayer, boolean[][] field, boolean[][] checkField);
}
