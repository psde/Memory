import java.util.Random;

/**
 * Das Spielfeld welches aus Karten,
 * den aktuellen Spieler und die Spielerpunkte
 * besteht.
 *  
 * @see Karte
 * 
 */
public class MemoryModel {
	private Karte[] karten;
	private int[] spielerPunkte;
	private int spielerAmZug;
	
	/**
	 * Initialisiert das Spielfeld, die Spieler und deren Punkte.
	 * 
	 * @param anzahlKarten Anzahl der Karten. Muss durch zwei teilbar sein.
	 */
	public MemoryModel(int anzahlKarten)
	{
		if(anzahlKarten%2 != 0 || anzahlKarten <= 0) 
		{
			//TODO: Exception?
		}
		
		this.spielerPunkte = new int[2];
		this.spielerAmZug = 0;
		
		this.karten = new Karte[anzahlKarten];
		
		int c = 0;
		for(int i = 0; i < this.karten.length; i+=2)
		{
			this.karten[i] = new Karte(c);
			this.karten[i+1] = new Karte(c);
			c++;
		}
	}

	/**
	 * Mischt das Spielfeld.
	 */
	public void mischeKarten()
	{
		Random rand = new Random();
		
		for(int i = 0; i < this.karten.length; i++)
		{
			int zufall = rand.nextInt(this.karten.length);
			Karte zwischenspeicher = this.karten[i];
			this.karten[i] = this.karten[zufall];
			this.karten[zufall] = zwischenspeicher;
		}
	}
	
	/**
	 * Gibt die Nummer des aktuellen Spielers zurück
	 * @return Aktueller Spieler
	 */
	public int getSpielerAmZug()
	{
		return this.spielerAmZug;
	}

	/**
	 * Gibt die Punktzahl beider Spieler zurück.
	 * 
	 * @return Array aus zwei Integer-Werte, welche die Punkte der Spieler repräsentieren.
	 */
	public int[] getSpielerPunkte()
	{
		return this.spielerPunkte;
	}
	
	/**
	 * Gibt das Spielfeld zurück.
	 * 
	 * @return Array aus Karten, welches das Spielfeld repräsentieren
	 */
	public Karte[] getKarten()
	{
		return this.karten;
	}
	
	/**
	 * Prüft ob das Spiel zuende ist
	 * 
	 * @return True, wenn alle Karten aufgedeckt, ansonsten False
	 */
	public boolean istAbgeschlossen()
	{
		for(int i = 0; i < this.karten.length; i++)
		{
			if(!this.karten[i].istAufgedeckt()) return false;
		}
		return true;
	}

	/**
	 * Versucht, zwei Karten aufzudecken und erhöht bei einem richtigen Zug
	 * die Punkte des aktuellen Spielers.
	 * Bei einem gültigen Zug ist der nächste Spieler an der Reihe
	 * 
	 * @param ersteKarte 1D-Koordinate der ersten Karte
	 * @param zweiteKarte 1D-Koordinate der zweiten Karte
	 * 
	 * @return Integer: -1 bei ungültigen Zug, 0 bei falschen Zug, 1 bei richtigem Zug
	 */
	public int kartenAufdecken(int ersteKarte, int zweiteKarte)
	{
		if(this.karten[ersteKarte].istAufgedeckt()) return -1;
		if(this.karten[zweiteKarte].istAufgedeckt()) return -1;
		
		if(this.karten[ersteKarte].getKarte() == this.karten[zweiteKarte].getKarte())
		{
			this.karten[ersteKarte].setAufgedeckt(true);
			this.karten[zweiteKarte].setAufgedeckt(true);
			
			this.spielerPunkte[this.spielerAmZug]++;
			this.spielerAmZug = (++spielerAmZug)%2;
			return 1;
		}
		else
		{
			this.spielerAmZug = (++spielerAmZug)%2;
			return 0;
		}
	}
}
