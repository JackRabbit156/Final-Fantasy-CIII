package gegenstand.Ausruestungsgegenstand;

import hilfsklassen.ZufallsZahlenGenerator;

/**
 * Objektklasse für die Accessoire
 * @author OF Kretschmer
 * @since 17.11.23
 */
public class Accessoire extends Ausruestungsgegenstand {

    private int maxGesundheitsPunkte;
    private int maxManaPunkte;
    private int gesundheitsRegeneration;
    private int manaRegeneration;
    private int beweglichkeit;


    /**
     * Konstruktor für Händler
     * @param stufe -
     * @author OF Kretschmer
     * @since 17.11.23
     */
    public Accessoire(int stufe) {
        super();
        normalesAccessoire(stufe);
        this.setIstNichtKaufbar(true);
    }

    /**
     * Konstruktor für Söldner/Gegner & Loot
     * @param stufe -
     * @param istNichtKaufbar -
     * @author OF Kretschmer
     * @since 17.11.23
     */
    public Accessoire(int stufe, boolean istNichtKaufbar) {
        super();
        this.setIstNichtKaufbar(!istNichtKaufbar);
        int einzigartigeChance = ZufallsZahlenGenerator.zufallsZahlIntAb1(100);
        //Der Wert in der If Prüfung regelt die Dropchance für einzigartige Accessoires
        if (einzigartigeChance > 5) {
            normalesAccessoire(stufe);
        } else {
            einzigartigesAccessoire(stufe);
        }

    }

    /**
     * Erstellt ein normales Accessoire
     * @author OF Kretschmer
     * @since 17.11.23
     * @param stufe des Charakters
     */
    private void normalesAccessoire(int stufe) {
        this.setName(namenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(namenArray.length-1)]);
        this.setKaufwert(stufe * 20);
        this.setVerkaufswert(stufe * 16);
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);
        this.setMaxGesundheitsPunkte(stufe * (ZufallsZahlenGenerator.zufallsZahlIntAb0(10)));
        this.setMaxManaPunkte(stufe * (ZufallsZahlenGenerator.zufallsZahlIntAb0(10)));
        this.setGesundheitsRegeneration(stufe * (ZufallsZahlenGenerator.zufallsZahlIntAb0(10)));
        this.setManaRegeneration(stufe * (ZufallsZahlenGenerator.zufallsZahlIntAb0(10)));
        this.setBeweglichkeit(stufe * (ZufallsZahlenGenerator.zufallsZahlIntAb0(10)));
    }

    private void einzigartigesAccessoire(int stufe) {
        int stelle = ZufallsZahlenGenerator.zufallsZahlIntAb0(einzigartigeNamenArray.length-1);
        this.setName(einzigartigeNamenArray[stelle]);

        if (this.getName().equals("Kaputter Ring")) {
            this.setVerkaufswert(stufe * 2);

            this.setMaxGesundheitsPunkte(stufe * (ZufallsZahlenGenerator.zufallsZahlIntAb0(2)));
            this.setMaxManaPunkte(stufe * (ZufallsZahlenGenerator.zufallsZahlIntAb0(2)));
            this.setGesundheitsRegeneration(stufe * (ZufallsZahlenGenerator.zufallsZahlIntAb0(2)));
            this.setManaRegeneration(stufe * (ZufallsZahlenGenerator.zufallsZahlIntAb0(2)));
            this.setBeweglichkeit(stufe * (ZufallsZahlenGenerator.zufallsZahlIntAb0(2)));

        } else {
            einzigartigeNamenArray[stelle] = "Kaputter Ring";
            this.setVerkaufswert(stufe * 16);

            this.setMaxGesundheitsPunkte(stufe * 15);
            this.setMaxManaPunkte(stufe * 15);
            this.setGesundheitsRegeneration(stufe * 15);
            this.setManaRegeneration(stufe * 15);
            this.setBeweglichkeit(stufe * 15);
        }
        this.setKaufwert(stufe * 20);
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);
    }

    private static final String[] namenArray = {"Silberne Kette", "Goldene Kette", "Rubinhalskette", "Goldenes Diadem", "Blechring",
            "Goldener Ring", "Edle Brosche", "Diamantdiadem", "Holzkette", "Smaragdkette" +
            "Diamantring", "Rubinring", "Saphirring", "Silberring", " Smaragdring"};


    private static final String[] einzigartigeNamenArray = {
            "Das Herz des Ozeans - Titanic",
            "Das Glas voll Dreck",
            "Das Diadem von Ravenclaw",
            "Krone von Barenziah",
            "Der eine Ring "};


    public int getMaxGesundheitsPunkte() {
        return maxGesundheitsPunkte;
    }

    public void setMaxGesundheitsPunkte(int maxGesundheitsPunkte) {
        this.maxGesundheitsPunkte = maxGesundheitsPunkte;
    }

    public int getMaxManaPunkte() {
        return maxManaPunkte;
    }

    public void setMaxManaPunkte(int maxManaPunkte) {
        this.maxManaPunkte = maxManaPunkte;
    }

    public int getGesundheitsRegeneration() {
        return gesundheitsRegeneration;
    }

    public void setGesundheitsRegeneration(int gesundheitsRegeneration) {
        this.gesundheitsRegeneration = gesundheitsRegeneration;
    }

    public int getManaRegeneration() {
        return manaRegeneration;
    }

    public void setManaRegeneration(int manaRegeneration) {
        this.manaRegeneration = manaRegeneration;
    }

    public int getBeweglichkeit() {
        return beweglichkeit;
    }

    public void setBeweglichkeit(int beweglichkeit) {
        this.beweglichkeit = beweglichkeit;
    }
}
