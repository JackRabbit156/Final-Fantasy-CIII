package de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen;

import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;

public class Bogen extends Waffe{

    private static final String[] NAMEN = { "Lebendiger Langbogen", "Phönixbogen des Sonnenzorns", "Quantenbogen",
            "Rae'shalare", "Voodoojagdbogen", "Dämonenknochenbogen", "Armor's Bogen", "Golemherzbogen", "Belthronding",
            "Schattenbogen", "Witwenmacher", "Vulkanglasbogen", "Ellen Bogen" };

    /**
     * Konstruktor für Händler
     *
     * @param stufe diese LevelStufe +/- 2
     * @author OF Stetter
     * @since 06.12.23
     */
    public Bogen(int stufe) {
        this.setIcon("icons/bogenWaffe.png");
        this.setName(NAMEN[ZufallsZahlenGenerator.zufallsZahlIntAb0(NAMEN.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1Inklusive(4));
        this.setMagischeAttacke(0);
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(2));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(6));
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
    public Bogen(int stufe, boolean istNichtKaufbar) {
        this.setIcon("icons/bogenWaffe.png");
        this.setName(NAMEN[ZufallsZahlenGenerator.zufallsZahlIntAb0(NAMEN.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(istNichtKaufbar);
        this.setAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1Inklusive(4));
        this.setMagischeAttacke(0);
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(2));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(6));
        this.setLevelAnforderung(stufe);
        this.setIstSoeldnerItem(false);
    }

}
