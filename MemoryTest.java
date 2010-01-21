import junit.framework.TestCase;

/**
 * Testet die MemoryModel-Klasse.
 * 
 * @author Mathias Garbe
 */
public class MemoryTest extends TestCase {
	
	/**
	 * �berpr�ft auf korrekte Anlegung des Spielfeldes
	 */
	public void testKonstruktor()
	{
		int anzahlKarten = 10;
		MemoryModel model = new MemoryModel(anzahlKarten);
		Karte[] karten = model.getKarten();
		
		assertTrue(karten.length == anzahlKarten);
		
		for(int i = 0; i < anzahlKarten; i+=2)
		{
			assertTrue(karten[i].getKarte() == karten[i+1].getKarte());
		}
	}
	/**
	 * �berpr�ft auf korrektes Mischen.
	 */
	public void testMischen()
	{
		int anzahlKarten = 10;
		MemoryModel model = new MemoryModel(anzahlKarten);
		Karte[] karten = model.getKarten();
		
		model.mischeKarten();
		
		int toleranz = 2; //TODO: Zufall besser testen? (Wie?) 
		
		for(int i = 0; i < anzahlKarten; i+=2)
		{
			if(karten[i].getKarte() == karten[i+1].getKarte()) toleranz--;
		}
		assertTrue(toleranz >= 0);
	}
	
	/**
	 * �berpr�ft auf korrekten R�ckgabewert bei dem Ziehen einer Karte.
	 */
	public void testZiehen()
	{
		int anzahlKarten = 10;
		MemoryModel model = new MemoryModel(anzahlKarten);
		
		assertTrue(model.kartenAufdecken(0, 1) == 1);
		assertTrue(model.kartenAufdecken(2, 4) == 0);
		
		assertTrue(model.kartenAufdecken(0, 1) == -1);
		assertTrue(model.kartenAufdecken(5, 1) == -1);
	}
	
	/**
	 * �berpr�ft auf korrekten Abschluss-Zustand der MemoryModel-Klasse.
	 */
	public void testIstAbgeschlossen()
	{
		int anzahlKarten = 2;
		MemoryModel model = new MemoryModel(anzahlKarten);
		//Karte[] karten = model.getKarten();
		
		
		assertFalse(model.istAbgeschlossen());
		model.kartenAufdecken(0, 1);
		assertTrue(model.istAbgeschlossen());
	}
	
	/**
	 * �berpr�ft die Getter der MemoryModel-Klasse auf korrektheit.
	 */
	public void testGetter()
	{
		int anzahlKarten = 2;
		MemoryModel model = new MemoryModel(anzahlKarten);

		assertTrue(model.getSpielerAmZug() == 0);
		assertTrue(model.getSpielerPunkte()[0] == 0);
		assertTrue(model.getSpielerPunkte()[1] == 0);
	}
}
