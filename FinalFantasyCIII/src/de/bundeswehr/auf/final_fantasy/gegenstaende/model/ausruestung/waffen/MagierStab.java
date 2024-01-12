package de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen;

import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;

public class MagierStab extends Waffe {

    private static final String[] NAMEN = { "Nussknacker", "Sauron's Stab", "Onyxstab", "Höllenzauberstab", "Zahnstocher",
            "Schimmersteinstab", "Meteoritenzauberstab", "Schlangenstab", "Stab des Erzmagiers", "Stab des Zeitalters",
            "Drachenzornstab", "Merlin's Stab", "Elderstab" };
    /**
     * Konstruktor für Händler
     *
     * @param stufe diese LevelStufe +/- 2
     * @author OF Stetter
     * @since 06.12.23
     */
    public MagierStab(int stufe) {
        this.setIcon("icons/magierWaffe.png");
        this.setName(NAMEN[ZufallsZahlenGenerator.zufallsZahlIntAb0(NAMEN.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setAttacke(0);
        this.setMagischeAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1Inklusive(4));
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4));
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
    public MagierStab(int stufe, boolean istNichtKaufbar) {
        this.setIcon("icons/magierWaffe.png");
        this.setName(NAMEN[ZufallsZahlenGenerator.zufallsZahlIntAb0(NAMEN.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(istNichtKaufbar);
        this.setAttacke(0);
        this.setMagischeAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1Inklusive(4));
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4));
        this.setLevelAnforderung(stufe);
        this.setIstSoeldnerItem(false);
    }

}
