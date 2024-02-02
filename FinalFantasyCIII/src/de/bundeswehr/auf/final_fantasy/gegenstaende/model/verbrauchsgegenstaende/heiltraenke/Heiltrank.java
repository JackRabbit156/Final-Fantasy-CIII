package de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.heiltraenke;

import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;

public abstract class Heiltrank extends Verbrauchsgegenstand {

	private int heilwert;

	/**
	 * Wendet den Gegenstand auf den Charakter an
	 * 
	 * @param spielerCharakter Zielcharakter
	 *
	 * @since 18.11.2023
	 * @author Lang
	 */
	@Override
	public void gegenstandVerwenden(SpielerCharakter spielerCharakter) {
		if (spielerCharakter.getGesundheitsPunkte() + this.getHeilwert() > spielerCharakter.getMaxGesundheitsPunkte()) {
			spielerCharakter.setGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte());
		}
		else {
			spielerCharakter.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte() + this.getHeilwert());
		}
	}

	public int getHeilwert() {
		return heilwert;
	}

	public void setHeilwert(int heilwert) {
		this.heilwert = heilwert;
	}
}
