package charakter.model;

import gamehub.trainer.faehigkeiten.Faehigkeit;

public class Charakter {
	private int beweglichkeit;

	public int getBeweglichkeit() {
		return this.beweglichkeit;
	}

	public Faehigkeit[] getFaehigkeiten() {
		Faehigkeit[] meineFaehigkeiten = new Faehigkeit[5];
		return meineFaehigkeiten;
	}
}
