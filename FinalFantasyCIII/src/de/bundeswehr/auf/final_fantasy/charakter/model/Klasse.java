package de.bundeswehr.auf.final_fantasy.charakter.model;

import java.util.List;

public abstract class Klasse {

    public static final String HLR = "Healer";
    public static final String MDD = "Magischer DD";
    public static final String PDD = "Physischer DD";
    public static final String TNK = "Tank";
    public static final String[] KLASSEN_NAMEN = { HLR, MDD, PDD, TNK };

    private String bezeichnung;
    private List<String> nutzbareAusruestung;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public List<String> getNutzbareAusruestung() {
        return nutzbareAusruestung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setNutzbareAusruestung(List<String> nutzbareAusruestung) {
        this.nutzbareAusruestung = nutzbareAusruestung;
    }

    public abstract String getDarstellung();

    /**
     * 0 = maxGesundheitsPunkte, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
     * 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegeneration,
     * 10 = manaRegeneration
     * @return
     */
    public abstract int[] getDefaultAttribute();

}
