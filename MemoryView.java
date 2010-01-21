/**
 * Zuständig für das Anzeigen des Spiels in einem 2D-Koordinatensystem.
 */
public class MemoryView {
	private MemoryModel model;
	private int hoehe, breite, kartenBreite;
	
	/**
	 * Initialisiert und berechnet die optimalen Dimensionen des Spielfeldes.
	 * 
	 * @param model MemoryModel des Spiels
	 */
	public MemoryView(MemoryModel model)
	{
		this.model = model;
		Karte[] karten = model.getKarten();
		
		int[] faktoren = this.faktorzerlegung(karten.length);
		if(faktoren[0] < faktoren[1])
		{
			this.hoehe = faktoren[0];
			this.breite = faktoren[1];
		}
		else
		{
			this.hoehe = faktoren[1];
			this.breite = faktoren[0];
		}
		
		int maximum = 0;
		for(int i = 0; i<karten.length; i++)
		{
			if(karten[i].getKarte() > maximum) maximum = karten[i].getKarte();
		}
		this.kartenBreite = log10(maximum);
	}
	/**
	 * Zeigt einen String an.
	 * 
	 * @param text Anzuzeigender Text, es wird kein newline oder carriage-return angehängt.
	 */
	public void zeigeTextAn(String text)
	{
		System.out.print(text);
	}
	
	/**
	 * Zeigt Spielfeld, Punktzahl und aktueller Spieler an.
	 */
	public void zeigeSpiel()
	{
		int spielerAmZug = this.model.getSpielerAmZug();
		System.out.println("Spieler am Zug: " + (spielerAmZug == 0 ? "A" : "B"));
		
		int[] spielerPunkte = this.model.getSpielerPunkte();
		System.out.println("Punkte Spieler A: " + spielerPunkte[0]);
		System.out.println("Punkte Spieler B: " + spielerPunkte[1]);
		
		this.zeigeKarten();
	}
	
	/**
	 * Zeigt Spielfeld an. 
	 */
	public void zeigeKarten()
	{
		this.zeigeKarten(-1, -1);
	}
	
	/**
	 * Zeigt Spielfeld an, wobei zwei Karten einmalig (temporär) aufgedeckt werden.
	 * 
	 * @param ersteKarte 1D-Koordinate der ersten Karte
	 * @param zweiteKarte 1D-Koordinate der zweiten Karte
	 */
	public void zeigeKarten(int ersteKarte, int zweiteKarte)
	{
		Karte[] karten = this.model.getKarten();
		
		for(int y = 0; y < this.hoehe; y++)
		{
			for(int x = 0; x < this.breite; x++)
			{
				Karte aktuelleKarte = karten[y * this.breite + x];
				
				if(this.uebersetzeKoordinaten(x, y) == ersteKarte || 
				   this.uebersetzeKoordinaten(x, y) == zweiteKarte )
				{
					System.out.printf("%03d", aktuelleKarte.getKarte());
					//System.out.print(aktuelleKarte.getKarte()+ " ");
				}
				else
				{
					if(!aktuelleKarte.istAufgedeckt())
					{
						System.out.printf("%0" + this.kartenBreite + "d ", aktuelleKarte.getKarte());
					}
					else
					{
						String nichtAufgedeckt = "";
						for(int i=0; i<this.kartenBreite; i++)
						{
							nichtAufgedeckt += "X";
						}
						System.out.print(nichtAufgedeckt + " ");
					}
					//System.out.print((aktuelleKarte.istAufgedeckt() ? aktuelleKarte.getKarte() : "X") + " ");
				}
			}
			System.out.print("\n");
		}		
	}
	
	/**
	 * 
	 * @param koordinaten 2D-Array aus Integern nach folgendem Format: {x, y}
	 * @return 1D-Koordinate 
	 */
	public int uebersetzeKoordinaten(int[] koordinaten)
	{
		return this.uebersetzeKoordinaten(koordinaten[0], koordinaten[1]);
	}
	
	/**
	 * Übersetzt 2D-Koordinaten in eine 1D-Koordinate
	 * @param x 
	 * @param y
	 * @return 1D-Koordinate 
	 */
	public int uebersetzeKoordinaten(int x, int y)
	{
		return y * this.breite + x;
	}	
	
	private int[] faktorzerlegung(int zahl)
	{
		int[] faktoren = new int[2];
		for (int i=2; i < 0.5 + Math.sqrt(zahl); i++) {
			double tmp=zahl/(double)i;
			if (tmp == (int)tmp) {
				faktoren[0] = i;
				faktoren[1] = (int)tmp;
			}
		}
        return faktoren;
	}
}
