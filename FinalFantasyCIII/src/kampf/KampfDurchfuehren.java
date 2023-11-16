package kampf;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import charakter.model.Charakter;
import charakter.model.Feind;
import charakter.model.SpielerCharakter;

public class KampfDurchfuehren {
	Scanner scanner = new Scanner(System.in);
	private int runde;
	private boolean istKampfVorbei;
	private boolean istKampfVerloren;
	private List<SpielerCharakter> freundeDieNochLeben = new ArrayList<>();
	private List<SpielerCharakter> freundeDieNochActionHaben = new ArrayList<>();
	private List<Feind> feindeDieNochLeben = new ArrayList<>();
	private List<Feind> feindeDieNochActionHaben = new ArrayList<>();
	private List<Charakter> zugreihenfolge = new ArrayList<>();
	private Charakter naechsterCharakter;

	public KampfDurchfuehren(ArrayList<Charakter> initialeZugreihenfolge) {
		this.runde = 1;
		this.istKampfVorbei = false;
		this.istKampfVerloren = false;
		this.zugreihenfolge = initialeZugreihenfolge;
		for (int counter = 1, len = initialeZugreihenfolge.size(); counter < len; counter++) {
			if (initialeZugreihenfolge.get(counter) instanceof SpielerCharakter) {
				freundeDieNochLeben.add((SpielerCharakter) initialeZugreihenfolge.get(counter));
			}
			else if (initialeZugreihenfolge.get(counter) instanceof Feind) {
				feindeDieNochLeben.add((Feind) initialeZugreihenfolge.get(counter));
			}
		}
		for (int counter = 1, len = freundeDieNochLeben.size() + 1; counter < len; counter++) {
			freundeDieNochActionHaben.add(freundeDieNochLeben.get(counter));
		}
		for (int counter = 1, len = feindeDieNochLeben.size() + 1; counter < len; counter++) {
			feindeDieNochActionHaben.add(feindeDieNochLeben.get(counter));
		}
		this.naechsterCharakter = initialeZugreihenfolge.get(1);
	}

	public Charakter naechstenCharakterBestimmen(ArrayList<SpielerCharakter> freundeDieNochActionHaben,
			ArrayList<Feind> feindeDieNochActionHaben) {
		List<Charakter> alleCharakterDieNochActionHaben = new ArrayList<>();
		Charakter naechsterCharakter = null;
		for (int counter = 1; counter < 5; counter++) {
			if (freundeDieNochActionHaben.get(counter) != null) {
				alleCharakterDieNochActionHaben.add(freundeDieNochActionHaben.get(counter));
			}
			if (feindeDieNochActionHaben.get(counter) != null) {
				alleCharakterDieNochActionHaben.add(feindeDieNochActionHaben.get(counter));
			}
		}
		for (int counter = 1, len = alleCharakterDieNochActionHaben.size() + 1; counter < len; counter++) {
			if (naechsterCharakter == null) {
				naechsterCharakter = alleCharakterDieNochActionHaben.get(counter);
			}
			else if (alleCharakterDieNochActionHaben.get(counter).getBeweglichkeit() > naechsterCharakter
					.getBeweglichkeit()) {
				naechsterCharakter = alleCharakterDieNochActionHaben.get(counter);
			}
		}
		return naechsterCharakter;
	}

	public void aktionWaehlen(Charakter aktuellerCharakter) {
		int input = 0;
		boolean gueltigeEingabe = true;
		System.out.println("Angreifen  (1)     Blocken (2)");
		System.out.println("Gegenstand (3)     Fliehen (4)");
		do {
			try {
				input = scanner.nextInt();
			} catch (Exception e) {
				gueltigeEingabe = false;
				System.out.println("Eingabe nicht gueltig. Moeglichkeiten: 1 | 2 | 3 | 4");
			}
		} while (!gueltigeEingabe);
		switch (input) {
		case 1:
			angreifen(aktuellerCharakter);
			break;
		case 2:
			blocken(aktuellerCharakter);
			break;
		case 3:
			gegenstand(aktuellerCharakter);
			break;
		case 4:
			fliehen(aktuellerCharakter);
			break;
		}
	}

	public void angreifen(Charakter aktuellerCharakter) {
		int skillWahlAlsInt = 0;
		if (aktuellerCharakter instanceof SpielerCharakter) {
			System.out.println("Fähigkeiten:");
			for (int counter = 0, len = aktuellerCharakter.getFaehigkeiten().length; counter < len; counter++) {
				System.out.println(counter + ". " + aktuellerCharakter.getFaehigkeiten()[counter].getName());
			}
			while (0 > skillWahlAlsInt || skillWahlAlsInt == 0
					|| skillWahlAlsInt > aktuellerCharakter.getFaehigkeiten().length) {
				System.out
						.println("Skill zwischen 1 und " + aktuellerCharakter.getFaehigkeiten().length + " bestimmen:");
				skillWahlAlsInt = scanner.nextInt();
			}
		}
	}

	public void blocken(Charakter aktuellerCharakter) {

	}

	public void gegenstand(Charakter aktuellerCharakter) {

	}

	public void fliehen(Charakter aktuellerCharakter) {

	}

	public int getRunde() {
		return this.runde;
	}

	public boolean isIstKampfVorbei() {
		return this.istKampfVorbei;
	}

	public boolean isIstKampfVerloren() {
		return this.istKampfVerloren;
	}

	public List<SpielerCharakter> getFreundeDieNochLeben() {
		return this.freundeDieNochLeben;
	}

	public List<Feind> getFeindeDieNochLeben() {
		return this.feindeDieNochLeben;
	}

	public List<Charakter> getZugreihenfolge() {
		return this.zugreihenfolge;
	}

	public Charakter getNaechsterCharakter() {
		return this.naechsterCharakter;
	}
}
