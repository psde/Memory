/**
 * Eine Spielkarte mit einem bestimmten Wert,
 * die entweder aufgedeckt oder zugedeckt sein kann.
 */
public class Karte {
	private int karte;
	private boolean aufgedeckt;
		
	/**
	 * Erzeugt eine neue Karte, wobei diese immer zugedeckt ist.
	 * @param karte Wert der Karte
	 */
	public Karte(int karte)
	{
		this.karte = karte;
		this.aufgedeckt = false;
	}
	
	/**
	 * Gibt zurück, ob Karte aufgedeckt ist.
	 * @return true, wenn Karte aufgedeckt ist, ansonsten false
	 */
	public boolean istAufgedeckt()
	{
		return this.aufgedeckt;
	}
	/**
	 * Gibt Kartenwert zurück.
	 * @return Kartenwert
	 */
	public int getKarte()
	{
		return this.karte;
	}
	/**
	 * Deckt eine Karte zu oder auf.
	 * @param aufgedeckt  
	 */
	public void setAufgedeckt(boolean aufgedeckt)
	{
		this.aufgedeckt = aufgedeckt; 
	}
	
	/*public void toogleAufgedeckt()
	{
		this.aufgedeckt = !this.aufgedeckt; 
	}*/
}
