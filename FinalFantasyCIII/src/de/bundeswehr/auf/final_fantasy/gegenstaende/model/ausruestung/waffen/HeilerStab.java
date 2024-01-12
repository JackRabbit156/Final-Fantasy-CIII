package de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;

public class HeilerStab extends Waffe {

    private static final String[] NAMEN = { "Hohestab des Wächters", "Holunderholzgehstock", "Stab des Hörsaalältesten",
            "Stab des Schamanen", "Richtstab", "Stab des Lichts", "Beichtstab", "Stab der Zwillingswelten", "Ritualstab",
            "Stab des Feldwebels", "Kristalldrachenstab", "Stab der Mondfinsternis", "Zorn des Lichts" };

    /**
     * Konstruktor für Händler
     *
     * @param stufe diese LevelStufe +/- 2
     * @author OF Stetter
     * @since 06.12.23
     */
    public HeilerStab(int stufe) {
        this.setIcon("icons/heilerWaffe.png");
        this.setName(NAMEN[ZufallsZahlenGenerator.zufallsZahlAb0(NAMEN.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setAttacke(0);
        this.setMagischeAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(4));
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(5));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(3));
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
    public HeilerStab(int stufe, boolean istNichtKaufbar) {
        this.setIcon("icons/heilerWaffe.png");
        this.setName(NAMEN[ZufallsZahlenGenerator.zufallsZahlAb0(NAMEN.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(istNichtKaufbar);
        this.setAttacke(0);
        this.setMagischeAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(4));
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(5));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(3));
        this.setLevelAnforderung(stufe);
        this.setIstSoeldnerItem(false);
    }

}
