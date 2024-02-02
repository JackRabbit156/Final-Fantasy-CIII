package de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung;

import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;

import java.util.Objects;

/**
 * Objektklasse für die Accessoire
 *
 * @author OF Kretschmer
 * @since 17.11.23
 */
public class Accessoire extends AusruestungsGegenstand {

    private static final String BEZEICHNUNG_ZERSTOERTES_ARTEFAKT = "Kaputter Ring";
    private static final String[] EINZIGARTIGE_NAMEN = { "Das Herz des Ozeans", "Das Glas voll Dreck",
            "Das Diadem von Ravenclaw", "Krone von Barenziah", "Der eine Ring" };
    private static final String[] NAMEN = { "Silberne Kette", "Goldene Kette", "Rubinhalskette", "Goldenes Diadem",
            "Blechring", "Goldener Ring", "Edle Brosche", "Diamantdiadem", "Holzkette", "Smaragdkette",
            "Diamantring", "Rubinring", "Saphirring", "Silberring", "Smaragdring" };

    private int beweglichkeit;
    private int gesundheitsRegeneration;
    private int manaRegeneration;
    private int maxGesundheitsPunkte;
    private int maxManaPunkte;

    /**
     * Konstruktor für Händler. Erstellt immer ein normales Accessoire.
     *
     * @param stufe diese LevelStufe +/- 2
     * @author OF Kretschmer
     * @since 05.12.23
     */
    public Accessoire(int stufe) {
        this.setIstNichtKaufbar(false);
        this.setLevelAnforderung(ZufallsZahlenGenerator.gegenstandsstufeFuerHaendler(stufe));
        this.setIstSoeldnerItem(false);
        normalesAccessoire();
    }

    /**
     * Konstruktor für Söldner/Gegner und Loot. Erstellt mit einer 5%-Chance ein einzigartiges Accessoire, ansonsten
     * wird ein normales erstellt.
     *
     * @param stufe           wird genau auf dieser Levelstufe erstellt
     * @param istNichtKaufbar zusätzlicher Parameter um Konstruktor zu unterscheiden
     * @author OF Kretschmer
     * @since 05.12.23
     */
    public Accessoire(int stufe, boolean istNichtKaufbar) {
        this.setIstNichtKaufbar(istNichtKaufbar);
        this.setLevelAnforderung(stufe);
        this.setIstSoeldnerItem(false);
        // 95% normal, 5% einzigartig
        if (ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(100) > 5) {
            normalesAccessoire();
        }
        else {
            einzigartigesAccessoire();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accessoire that = (Accessoire) o;
        return getName() == that.getName() &&
                getKaufwert() == that.getKaufwert() &&
                getVerkaufswert() == that.getVerkaufswert() &&
                beweglichkeit == that.beweglichkeit &&
                gesundheitsRegeneration == that.gesundheitsRegeneration &&
                manaRegeneration == that.manaRegeneration &&
                maxGesundheitsPunkte == that.maxGesundheitsPunkte &&
                maxManaPunkte == that.maxManaPunkte;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getKaufwert(), getVerkaufswert(),
                beweglichkeit, gesundheitsRegeneration, manaRegeneration, maxGesundheitsPunkte, maxManaPunkte);
    }

    public int getBeweglichkeit() {
        return beweglichkeit;
    }

    public int getGesundheitsRegeneration() {
        return gesundheitsRegeneration;
    }

    public int getManaRegeneration() {
        return manaRegeneration;
    }

    public int getMaxGesundheitsPunkte() {
        return maxGesundheitsPunkte;
    }

    public int getMaxManaPunkte() {
        return maxManaPunkte;
    }

    public void setBeweglichkeit(int beweglichkeit) {
        this.beweglichkeit = beweglichkeit;
    }

    public void setGesundheitsRegeneration(int gesundheitsRegeneration) {
        this.gesundheitsRegeneration = gesundheitsRegeneration;
    }

    public void setManaRegeneration(int manaRegeneration) {
        this.manaRegeneration = manaRegeneration;
    }

    public void setMaxGesundheitsPunkte(int maxGesundheitsPunkte) {
        this.maxGesundheitsPunkte = maxGesundheitsPunkte;
    }

    public void setMaxManaPunkte(int maxManaPunkte) {
        this.maxManaPunkte = maxManaPunkte;
    }

    @Override
    public String toString() {
        return getName() +
                ", lvl=" + getLevelAnforderung() +
                " {" +
                "B=" + beweglichkeit +
                ", GR=" + gesundheitsRegeneration +
                ", MR=" + manaRegeneration +
                ", maxGP=" + maxGesundheitsPunkte +
                ", maxMP=" + maxManaPunkte +
                "} " +
                "kau=" + getKaufwert() +
                ", ver=" + getVerkaufswert();
    }

    /**
     * Erstellt ein einzigartiges Accessoire
     * Entweder mit höheren Bonis oder als Eastergg einen kaputten Ring mit niedrigeren Werten.
     *
     * @author OF Kretschmer
     * @since 30.11.23
     */
    private void einzigartigesAccessoire() {
        int stelle = ZufallsZahlenGenerator.zufallsZahlAb0(EINZIGARTIGE_NAMEN.length);
        this.setName(EINZIGARTIGE_NAMEN[stelle]);
        if (this.getName().equals(BEZEICHNUNG_ZERSTOERTES_ARTEFAKT)) {
            this.setIcon("icons/AccKaputt.png");
            this.setVerkaufswert(this.getLevelAnforderung() * 2);
            this.setMaxGesundheitsPunkte(this.getLevelAnforderung() * (ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(2)));
            this.setMaxManaPunkte(this.getLevelAnforderung() * (ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(2)));
            this.setGesundheitsRegeneration(this.getLevelAnforderung() * (ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(2)));
            this.setManaRegeneration(this.getLevelAnforderung() * (ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(2)));
            this.setBeweglichkeit(this.getLevelAnforderung() * (ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(2)));

        }
        else {
            EINZIGARTIGE_NAMEN[stelle] = BEZEICHNUNG_ZERSTOERTES_ARTEFAKT;
            this.setIcon("icons/AccEinzigartig.png");
            this.setVerkaufswert(this.getLevelAnforderung() * 25);
            this.setMaxGesundheitsPunkte(this.getLevelAnforderung() * 25);
            this.setMaxManaPunkte(this.getLevelAnforderung() * 25);
            this.setGesundheitsRegeneration(this.getLevelAnforderung() * 25);
            this.setManaRegeneration(this.getLevelAnforderung() * 25);
            this.setBeweglichkeit(this.getLevelAnforderung() * 25);
        }
        this.setKaufwert(this.getLevelAnforderung() * 25);
    }

    /**
     * Erstellt ein normales Accessoire
     *
     * @author OF Kretschmer
     * @since 30.11.23
     */
    private void normalesAccessoire() {
        this.setIcon("icons/AccNormal.png");
        this.setName(NAMEN[ZufallsZahlenGenerator.zufallsZahlAb0(NAMEN.length)]);
        this.setKaufwert(this.getLevelAnforderung() * 20);
        this.setVerkaufswert(this.getLevelAnforderung() * 16);
        this.setMaxGesundheitsPunkte(this.getLevelAnforderung() * (ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(10)));
        this.setMaxManaPunkte(this.getLevelAnforderung() * (ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(10)));
        this.setGesundheitsRegeneration(this.getLevelAnforderung() * (ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(10)));
        this.setManaRegeneration(this.getLevelAnforderung() * (ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(10)));
        this.setBeweglichkeit(this.getLevelAnforderung() * (ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(10)));
    }

}
