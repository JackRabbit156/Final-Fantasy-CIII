package charakter.model;

import charakter.model.klassen.HLR;
import charakter.model.klassen.MDD;
import charakter.model.klassen.PDD;
import charakter.model.klassen.TNK;
import gamehub.trainer.faehigkeiten.Faehigkeit;

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
		this.erfahrungsPunkte = 0;
		this.offeneAttributpunkte = 0;
		this.verteilteFaehigkeitspunkte = 0;
		this.offeneFaehigkeitspunkte = 0;
		this.setName(name);
		this.setLevel(1);
		if (klasse.equals("Healer")) {
			this.setKlasse(new HLR());
			this.setMaxGesundheitsPunkte(10);
			this.setGesundheitsPunkte(this.getMaxGesundheitsPunkte());
			this.setMaxManaPunkte(20);
			this.setManaPunkte(getMaxManaPunkte());
			this.setPhysischeAttacke(3);
			this.setMagischeAttacke(0);
			this.setGenauigkeit(5);
			this.setVerteidigung(2);
			this.setMagischeVerteidigung(4);
			this.setResistenz(5);
			this.setBeweglichkeit(5);
			this.setGesundheitsRegeneration(2);
			this.setManaRegeneration(4);
			// TODO implement Fähigkeiten, Waffe, Rüstung, Accessiore
		}
		else if (klasse.equals("Magischer DD")) {
			this.setKlasse(new MDD());
			this.setMaxGesundheitsPunkte(10);
			this.setGesundheitsPunkte(this.getMaxGesundheitsPunkte());
			this.setMaxManaPunkte(20);
			this.setManaPunkte(getMaxManaPunkte());
			this.setPhysischeAttacke(3);
			this.setMagischeAttacke(6);
			this.setGenauigkeit(5);
			this.setVerteidigung(2);
			this.setMagischeVerteidigung(4);
			this.setResistenz(5);
			this.setBeweglichkeit(5);
			this.setGesundheitsRegeneration(2);
			this.setManaRegeneration(4);
			// TODO implement Fähigkeiten, Waffe, Rüstung, Accessiore
		}
		else if (klasse.equals("Physischer DD")) {
			this.setKlasse(new PDD());
			this.setMaxGesundheitsPunkte(20);
			this.setGesundheitsPunkte(this.getMaxGesundheitsPunkte());
			this.setMaxManaPunkte(10);
			this.setManaPunkte(getMaxManaPunkte());
			this.setPhysischeAttacke(6);
			this.setMagischeAttacke(0);
			this.setGenauigkeit(5);
			this.setVerteidigung(4);
			this.setMagischeVerteidigung(2);
			this.setResistenz(5);
			this.setBeweglichkeit(5);
			this.setGesundheitsRegeneration(2);
			this.setManaRegeneration(4);
			// TODO implement Fähigkeiten, Waffe, Rüstung, Accessiore
		}
		else if (klasse.equals("Tank")) {
			this.setKlasse(new TNK());
			this.setMaxGesundheitsPunkte(30);
			this.setGesundheitsPunkte(this.getMaxGesundheitsPunkte());
			this.setMaxManaPunkte(10);
			this.setManaPunkte(getMaxManaPunkte());
			this.setPhysischeAttacke(4);
			this.setMagischeAttacke(0);
			this.setGenauigkeit(5);
			this.setVerteidigung(6);
			this.setMagischeVerteidigung(4);
			this.setResistenz(5);
			this.setBeweglichkeit(2);
			this.setGesundheitsRegeneration(4);
			this.setManaRegeneration(2);
			// TODO implement Fähigkeiten, Waffe, Rüstung, Accessiore
		}
	}

	public String getGeschichte() {
		return geschichte;
	}

	public void setGeschichte(String geschichte) {
		this.geschichte = geschichte;
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

	@Override
	public SpielerCharakter clone() {
		SpielerCharakter sc = new SpielerCharakter(this.getName(), this.getKlasse().getBezeichnung(),
				this.getGeschichte());
		sc.setErfahrungsPunkte(this.erfahrungsPunkte);
		sc.setOffeneFaehigkeitspunkte(this.offeneFaehigkeitspunkte);
		sc.setVerteilteFaehigkeitspunkte(this.verteilteFaehigkeitspunkte);
		sc.setOffeneAttributpunkte(this.offeneAttributpunkte);
		sc.setKlasse(this.getKlasse());
		sc.setMaxGesundheitsPunkte(this.getMaxGesundheitsPunkte());
		sc.setGesundheitsPunkte(this.getGesundheitsPunkte());
		sc.setMaxManaPunkte(this.getMaxManaPunkte());
		sc.setManaPunkte(this.getManaPunkte());
		sc.setPhysischeAttacke(this.getPhysischeAttacke());
		sc.setMagischeAttacke(this.getMagischeAttacke());
		sc.setGenauigkeit(this.getGenauigkeit());
		sc.setVerteidigung(this.getVerteidigung());
		sc.setMagischeVerteidigung(this.getMagischeVerteidigung());
		sc.setResistenz(this.getResistenz());
		sc.setBeweglichkeit(this.getBeweglichkeit());
		sc.setGesundheitsRegeneration(this.getGesundheitsRegeneration());
		sc.setManaRegeneration(this.getManaRegeneration());
		// TODO clone Waffen, Accessoires, Ruestung sobald implementiert
		return sc;
	}
}
