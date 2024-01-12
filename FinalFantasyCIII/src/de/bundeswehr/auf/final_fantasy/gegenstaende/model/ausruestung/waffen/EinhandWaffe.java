package de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen;

import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;

public class EinhandWaffe extends Waffe {

    private static final String[] NAMEN = { "Teufelsklinge", "Dreschflegel", "Ebenerzschwert", "Goldschwert",
            "Morgenstern", "Bastardschwert", "Klinge der Reinheit", "Dämonenklinge", "Stich", "Buttermesser des Grauens",
            "Kriegsgleve von Azzinoth", "Mjölnir", "Nadel" };

    /**
     * Konstruktor für Händler
     *
     * @param stufe diese LevelStufe +/- 2
     * @author OF Stetter
     * @since 06.12.23
     */
    public EinhandWaffe(int stufe) {
        this.setIcon("icons/einhandWaffe.png");
        this.setName(NAMEN[ZufallsZahlenGenerator.zufallsZahlIntAb0(NAMEN.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1Inklusive(4));
        this.setMagischeAttacke(0);
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(3));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(5));
        this.setLevelAnforderung(ZufallsZahlenGenerator.gegenstandsstufeFuerHaendler(stufe));
        this.setIstSoeldnerItem(false);
    }

    /**
     * Konstruktor für Söldner/Gegner und Loot
     *
     * @param stufe wird genau auf dieser Levelstufe erstellt
     * @param istNichtKaufbar zusätzlicher Parameter um Konstruktor zu unterscheiden
     * @author OF Stetter
     * @since 06.12.23
     */
    public EinhandWaffe(int stufe, boolean istNichtKaufbar) {
        this.setIcon("icons/einhandWaffe.png");
        this.setName(NAMEN[ZufallsZahlenGenerator.zufallsZahlIntAb0(NAMEN.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(istNichtKaufbar);
        this.setAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1Inklusive(4));
        this.setMagischeAttacke(0);
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(3));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(5));
        this.setLevelAnforderung(stufe);
        this.setIstSoeldnerItem(false);
    }

}
