package charakter.model;

import charakter.controller.CharakterController;
import charakter.model.klassen.HLR;
import charakter.model.klassen.MDD;
import charakter.model.klassen.PDD;
import charakter.model.klassen.TNK;
import gegenstaende.ausruestung.Accessoire;
import gegenstaende.ausruestung.AusruestungsGegenstandFactory;
import party.AusruestungsGegenstandInventar;
import trainer.faehigkeiten.Faehigkeit;
import trainer.faehigkeiten.FaehigkeitFactory;

import java.util.Random;

public class SpielerCharakter extends Charakter {
	private String geschichte;
	private int erfahrungsPunkte;
	private int offeneFaehigkeitspunkte;
	private int verteilteFaehigkeitspunkte;
	private int offeneAttributpunkte;
	private boolean isSoeldner = false;

	/**
	 * Erstellt SpielerCharakter
	 *
	 * @param name       Name des Charakters - String
	 * @param klasse     Klasse des Charakters - String
	 * @param geschichte Geschichte des Charakters - String
	 *
	 * @author Lang
	 * @since 30.11.2023
	 */
	public SpielerCharakter(String name, String klasse, String geschichte) {
		super();
		this.geschichte = geschichte;
		this.erfahrungsPunkte = 100;
		this.offeneAttributpunkte = 0;
		this.verteilteFaehigkeitspunkte = 0;
		this.offeneFaehigkeitspunkte = 0;
		this.setName(name);
		this.setLevel(1);
		if (klasse.equals("Healer")) {
			this.setKlasse(new HLR(this));
		}
		else if (klasse.equals("Magischer DD")) {
			this.setKlasse(new MDD(this));
		}
		else if (klasse.equals("Physischer DD")) {
			this.setKlasse(new PDD(this));
		}
		else if (klasse.equals("Tank")) {
			this.setKlasse(new TNK(this));
		}
		else {
			System.out.println("Keine Klasse gesetzt!" + klasse);
		}
		this.setWaffe(AusruestungsGegenstandFactory.erstelleWaffeFuer(this.getKlasse(), this.getLevel()));
		CharakterController.ausruestungAnlegen(this, this.getWaffe(), new AusruestungsGegenstandInventar());
		this.setRuestung(AusruestungsGegenstandFactory.erstelleRuestungFuer(this.getKlasse(), this.getLevel()));
		CharakterController.ausruestungAnlegen(this, this.getRuestung(), new AusruestungsGegenstandInventar());
		this.setAccessoires(new Accessoire[3]);
		CharakterController.ausruestungAnlegen(this,
				AusruestungsGegenstandFactory.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()),
				new AusruestungsGegenstandInventar());
		CharakterController.ausruestungAnlegen(this,
				AusruestungsGegenstandFactory.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()),
				new AusruestungsGegenstandInventar());
		CharakterController.ausruestungAnlegen(this,
				AusruestungsGegenstandFactory.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()),
				new AusruestungsGegenstandInventar());
		this.setGesundheitsPunkte(this.getMaxGesundheitsPunkte());
		this.setManaPunkte(this.getMaxManaPunkte());
	}

	/**
	 * Constructor fuer die Soeldnererstellung
	 *
	 * @param name       Name des Charakters - String
	 * @param klasse     Klasse des Charakters - String
	 * @param geschichte Geschichte des Charakters - String
	 * @param partyLevel Level des Charakters - int
	 * @param isSoeldner Ob ein Charakter Soeldner ist - boolean
	 *
	 * @since 30.11.2023
	 * @author Lang
	 */
	public SpielerCharakter(String name, String klasse, String geschichte, int partyLevel, boolean isSoeldner) {
		super();
		Random rnd = new Random();
		this.geschichte = geschichte;
		this.erfahrungsPunkte = partyLevel * 100;
		this.offeneAttributpunkte = 0;
		this.verteilteFaehigkeitspunkte = partyLevel;
		this.offeneFaehigkeitspunkte = 0;
		this.setName(name);
		this.setLevel(partyLevel);
		this.isSoeldner = isSoeldner;
		if (klasse.equals("Healer")) {
			this.setKlasse(new HLR());
		}
		else if (klasse.equals("Magischer DD")) {
			this.setKlasse(new MDD());
		}
		else if (klasse.equals("Physischer DD")) {
			this.setKlasse(new PDD());
		}
		else if (klasse.equals("Tank")) {
			this.setKlasse(new TNK());
		}
		else {
			System.out.println("Keine Klasse gesetzt!" + klasse);
		}
		super.setMaxGesundheitsPunkte(((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) : 1);
		super.setGesundheitsPunkte(super.getMaxGesundheitsPunkte());
		super.setMaxManaPunkte(((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) : 1);
		super.setManaPunkte(getMaxManaPunkte());
		super.setPhysischeAttacke(((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) : 1);
		super.setMagischeAttacke(((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) : 1);
		super.setGenauigkeit(((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) : 1);
		super.setVerteidigung(((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) : 1);
		super.setMagischeVerteidigung(((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) : 1);
		super.setResistenz(((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) : 1);
		super.setBeweglichkeit(((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) : 1);
		super.setGesundheitsRegeneration(((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) : 1);
		super.setManaRegeneration(((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLevel / 10.0))) : 1);
		super.setLevel(partyLevel);
		this.setFaehigkeiten(FaehigkeitFactory.erstelleFaehigkeitFuer(this.getKlasse().getBezeichnung(), partyLevel));
		this.setWaffe(AusruestungsGegenstandFactory.erstelleWaffeFuer(this, this.getLevel()));
		CharakterController.ausruestungAnlegen(this, this.getWaffe(), new AusruestungsGegenstandInventar());
		this.setRuestung(AusruestungsGegenstandFactory.erstelleRuestungFuer(this, this.getLevel()));
		CharakterController.ausruestungAnlegen(this, this.getRuestung(), new AusruestungsGegenstandInventar());
		this.setAccessoires(new Accessoire[3]);
		CharakterController.ausruestungAnlegen(this,
				AusruestungsGegenstandFactory.erstelleAccessoireFuer(this, this.getLevel()),
				new AusruestungsGegenstandInventar());
		CharakterController.ausruestungAnlegen(this,
				AusruestungsGegenstandFactory.erstelleAccessoireFuer(this, this.getLevel()),
				new AusruestungsGegenstandInventar());
		CharakterController.ausruestungAnlegen(this,
				AusruestungsGegenstandFactory.erstelleAccessoireFuer(this, this.getLevel()),
				new AusruestungsGegenstandInventar());
		this.setGesundheitsPunkte(this.getMaxGesundheitsPunkte());
		this.setManaPunkte(this.getMaxManaPunkte());
		switch (klasse){
			case "Healer":
				this.setGrafischeDarstellung("charaktere/freund/heiler.png");
				break;
			case "Magischer DD":
				this.setGrafischeDarstellung("charaktere/freund/mdd.png");
				break;
			case "Physischer DD":
				this.setGrafischeDarstellung("charaktere/freund/pdd.png");
				break;
			case "Tank":
				this.setGrafischeDarstellung("charaktere/freund/tank.png");
				break;
			default:
				this.setGrafischeDarstellung("charaktere/freund/pdd.png");

				break;
		}
	}

	public int getErfahrungsPunkte() {
		return erfahrungsPunkte;
	}

	public void setErfahrungsPunkte(int erfahrungsPunkte) {
		this.erfahrungsPunkte = erfahrungsPunkte;
	}

	public int getOffeneFaehigkeitspunkte() {
		return offeneFaehigkeitspunkte;
	}

	public void setOffeneFaehigkeitspunkte(int offeneFaehigkeitspunkte) {
		this.offeneFaehigkeitspunkte = offeneFaehigkeitspunkte;
	}

	public int getVerteilteFaehigkeitspunkte() {
		return verteilteFaehigkeitspunkte;
	}

	public void setVerteilteFaehigkeitspunkte(int verteilteFaehigkeitspunkte) {
		this.verteilteFaehigkeitspunkte = verteilteFaehigkeitspunkte;
	}

	public int getOffeneAttributpunkte() {
		return offeneAttributpunkte;
	}

	public void setOffeneAttributpunkte(int offeneAttributpunkte) {
		this.offeneAttributpunkte = offeneAttributpunkte;
	}

	public void addFaehigkeit(Faehigkeit faehigkeit) {
		this.getFaehigkeiten().add(faehigkeit);
	}

	public void setGeschichte(String geschichte) {
		this.geschichte = geschichte;
	}

	public String getGeschichte() {
		return this.geschichte;
	}

	public boolean isSoeldner() {

		return isSoeldner;
	}

	public void setSoeldner(boolean soeldner) {
		isSoeldner = soeldner;

	}

	@Override
	public SpielerCharakter clone() {
		SpielerCharakter sc = new SpielerCharakter(this.getName(), this.getKlasse().getBezeichnung(),
				this.getGeschichte());
		sc.setAccessoires(this.getAccessoires());
		sc.setBeweglichkeit(this.getBeweglichkeit());
		sc.setErfahrungsPunkte(this.erfahrungsPunkte);
		sc.setFaehigkeiten(this.getFaehigkeiten());
		sc.setGenauigkeit(this.getGenauigkeit());
		sc.setGeschichte(this.geschichte);
		sc.setGesundheitsPunkte(this.getGesundheitsPunkte());
		sc.setGesundheitsRegeneration(this.getGesundheitsRegeneration());
		sc.setGeschichte(this.geschichte);
		sc.setKlasse(this.getKlasse());
		sc.setLevel(this.getLevel());
		sc.setMagischeAttacke(this.getMagischeAttacke());
		sc.setMagischeVerteidigung(this.getMagischeVerteidigung());
		sc.setManaPunkte(this.getManaPunkte());
		sc.setManaRegeneration(this.getManaRegeneration());
		sc.setMaxGesundheitsPunkte(this.getMaxGesundheitsPunkte());
		sc.setMaxManaPunkte(this.getMaxManaPunkte());
		sc.setName(this.getName());
		sc.setOffeneFaehigkeitspunkte(this.offeneFaehigkeitspunkte);
		sc.setOffeneAttributpunkte(this.offeneAttributpunkte);
		sc.setPhysischeAttacke(this.getPhysischeAttacke());
		sc.setResistenz(this.getResistenz());
		sc.setRuestung(this.getRuestung());
		sc.setVerteidigung(this.getVerteidigung());
		sc.setVerteilteFaehigkeitspunkte(this.verteilteFaehigkeitspunkte);
		sc.setWaffe(this.getWaffe());
		sc.setSoeldner(this.isSoeldner);
		return sc;
	}
}
