package gegenstand;

import java.util.Map;

import charakter.model.SpielerCharakter;
import gegenstand.material.Eisenerz;
import gegenstand.material.Golderz;
import gegenstand.material.Material;
import gegenstand.material.Mithril;
import gegenstand.material.Popel;
import gegenstand.material.Schleim;
import gegenstand.material.Silbererz;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import gegenstand.verbrauchsgegenstand.heiltraenke.GrosserHeiltrank;
import gegenstand.verbrauchsgegenstand.heiltraenke.KleinerHeiltrank;
import gegenstand.verbrauchsgegenstand.heiltraenke.MittlererHeiltrank;
import gegenstand.verbrauchsgegenstand.manatraenke.GrosserManatrank;
import gegenstand.verbrauchsgegenstand.manatraenke.KleinerManatrank;
import gegenstand.verbrauchsgegenstand.manatraenke.MittlererManatrank;
import javafx.beans.property.IntegerProperty;

public class GegenstandController {

	private static final Verbrauchsgegenstand[] VERBRAUCHSGEGENSTAENDE = { new KleinerHeiltrank(),
			new MittlererHeiltrank(), new GrosserHeiltrank(), new KleinerManatrank(), new MittlererManatrank(),
			new GrosserManatrank() };

	/**
	 * Gibt alle Verbrauchsgegenstaende als Array zurueck
	 * 
	 * @return Verbrauchsgegenstand[]
	 *
	 * @since 18.11.2023
	 * @author Lang
	 */
	public static Verbrauchsgegenstand[] rueckgabeAllerVerbrauchsgegenstaende() {
		return VERBRAUCHSGEGENSTAENDE;
	}

	/**
	 * Gibt einen spezifischen Verbrauchsgegenstand zurueck
	 * 
	 * @param name
	 * @return Verbrauchsgegenstand
	 *
	 * @since 18.11.2023
	 * @author Lang
	 */
	public static Verbrauchsgegenstand rueckgabeSpezifischerVerbrauchsgegenstand(String name) {
		Verbrauchsgegenstand rueckgabe = null;
		for (Verbrauchsgegenstand verbrauchsgegenstand : VERBRAUCHSGEGENSTAENDE) {
			if (verbrauchsgegenstand.getName().equals(name)) {
				rueckgabe = verbrauchsgegenstand;
			}
		}
		return rueckgabe;
	}

	/**
	 * Verwendet einen Verbrauchsgegenstand reduziert die Anzahl der verfügbaren
	 * Verbrauchsgegenstaende um 1 Sollte die Anzahl 0 erreichen wird der
	 * Verbrauchsgegenstand aus der HashMap geloescht
	 * 
	 * @param verbrauchsgegenstaende
	 * @param verwendeterVerbGgst
	 * @param spielerCharakter
	 * @return HashMap<Verbrauchsgegenstand, Integer>
	 *
	 * @since 18.11.2023
	 * @author Lang
	 */
	public static Map<Verbrauchsgegenstand, IntegerProperty> verwendeVerbrauchsgegenstand(
			Map<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstaende, Verbrauchsgegenstand verwendeterVerbGgst,
			SpielerCharakter spielerCharakter) {
		IntegerProperty updatedValue = null;
		if (verbrauchsgegenstaende.get(verwendeterVerbGgst).getValue() > 0) {
			verwendeterVerbGgst.gegenstandVerwenden(spielerCharakter);
//			updatedValue.set(verbrauchsgegenstaende.get(verwendeterVerbGgst).getValue() - 1);
//			verbrauchsgegenstaende.put(verwendeterVerbGgst, updatedValue);
		}
		if (verbrauchsgegenstaende.get(verwendeterVerbGgst).getValue() == 0) {
			verbrauchsgegenstaende.remove(verwendeterVerbGgst);
		}
		return verbrauchsgegenstaende;
	}

	private static final Material[] MATERIALIEN = { new Eisenerz(), new Golderz(), new Silbererz(), new Mithril(),
			new Schleim(), new Popel() };

	public static Material[] rueckgabeAllerMaterialien() {
		return MATERIALIEN;
	}

	public static Material rueckgabeSpezifischerMaterialien(String name) {
		Material rueckgabe = null;
		for (Material material : MATERIALIEN) {
			if (material.getName().equals(name)) {
				rueckgabe = material;
			}
		}
		return rueckgabe;
	}

	public static Map<Material, Integer> materialVerwenden(Map<Material, Integer> map, Material material, int anzahl) {
		map.put(material, map.get(material) - anzahl);
		return map;
	}
}
