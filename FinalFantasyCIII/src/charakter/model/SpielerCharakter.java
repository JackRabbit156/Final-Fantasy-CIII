package charakter.model;

import charakter.controller.CharakterController;
import charakter.model.klassen.HLR;
import charakter.model.klassen.MDD;
import charakter.model.klassen.PDD;
import charakter.model.klassen.TNK;
import gamehub.trainer.faehigkeiten.Faehigkeit;
import gamehub.trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;
import party.AusruestungsgegenstandInventar;

public class SpielerCharakter extends Charakter {

	private String geschichte;
	private int erfahrungsPunkte;
	private int offeneFaehigkeitspunkte;
	private int verteilteFaehigkeitspunkte;
	private int offeneAttributpunkte;

	/**
	 * Erstellt SpielerCharakter
	 *
	 * @param name
	 * @param klasse
	 * @param geschichte
	 *
	 * @author Lang
	 * @since 15.11.2023
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
		this.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(this.getKlasse(), this.getLevel()));
		CharakterController.ausruestungAnlegen(this, this.getWaffe(), new AusruestungsgegenstandInventar());
		this.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(this.getKlasse(), this.getLevel()));
		CharakterController.ausruestungAnlegen(this, this.getRuestung(), new AusruestungsgegenstandInventar());
		this.setAccessoires(new Accessoire[3]);
		this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()), 0);
		CharakterController.ausruestungAnlegen(this, this.getAccessoire(0), new AusruestungsgegenstandInventar());
		this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()), 1);
		CharakterController.ausruestungAnlegen(this, this.getAccessoire(1), new AusruestungsgegenstandInventar());
		this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()), 2);
		CharakterController.ausruestungAnlegen(this, this.getAccessoire(2), new AusruestungsgegenstandInventar());
		this.setGesundheitsPunkte(this.getMaxGesundheitsPunkte());
		this.setManaPunkte(this.getMaxManaPunkte());
	}

	public SpielerCharakter(String name, String klasse, String geschichte, int partyLevel) {
		super();
		this.geschichte = geschichte;
		this.erfahrungsPunkte = partyLevel * 100;
		this.offeneAttributpunkte = 0;
		this.verteilteFaehigkeitspunkte = partyLevel;
		this.offeneFaehigkeitspunkte = 0;
		this.setName(name);
		this.setLevel(partyLevel);
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
		this.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(this.getKlasse().getBezeichnung(), partyLevel));
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


		return sc;
	}
}
