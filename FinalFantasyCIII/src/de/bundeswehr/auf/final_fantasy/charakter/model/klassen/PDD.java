package de.bundeswehr.auf.final_fantasy.charakter.model.klassen;

import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PDD extends Klasse {

    public static final List<String> NUTZBARE_AUSRUESTUNG = new ArrayList<>(Arrays.asList("EinhandWaffe", "Bogen", "ZweihandWaffe", "SchwereRuestung"));

    private static final String DARSTELLUNG = "charakter/freund/pdd.png";
    // 0 = maxGesundheitsPunkte, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegeneration,
    // 10 = manaRegeneration
    private static final int[] DEFAULT_ATTRIBUTE = { 20, 10, 6, 0, 5, 4, 2, 5, 5, 2, 4 };
    private static final String GESCHICHTE = "#NAME# wuchs in einer Familie von Kriegern auf, in der er den Kampf von klein auf lernte. Als er älter wurde, begann er seine Fähigkeiten als Kämpfer zu perfektionieren und reiste durch das Land, um seine Fähigkeiten im Kampf zu testen und zu verbessern.\n" +
            "Schließlich fand er eine Gruppe von Abenteurern, die seine Leidenschaft für den Kampf teilten. Zusammen bereisten sie die Welt, machten Jagd auf Monster und kämpften gegen mächtige Feinde. #NAME# zeichnete sich dabei immer wieder durch seine körperliche Stärke und seine geschickte Handhabung diverser Waffen aus.\n" +
            "Mit der Zeit wurde #NAME# zum Anführer der Gruppe und kämpfte unermüdlich für das Wohl seiner Freunde. Seine Entschlossenheit und seine Fähigkeit, seine Kameraden in schwierigen Situationen zu schützen, machten ihn zu einem unverzichtbaren Mitglied des Teams.\n" +
            "Als die Gruppe schließlich einen mächtigen Feind bekämpfte, opferte #NAME# sein eigenes Leben, um seine Freunde vor Schaden zu bewahren. Sein Mut und sein Opfergeist machten ihn zu einem Helden, der in Erinnerung bleiben wird.\n" +
            "Die Legende von #NAME# lebt weiter und inspiriert andere Kämpfer, seinem Beispiel zu folgen und ihrem eigenen Mut zu vertrauen, um die Welt zu einem besseren Ort zu machen.\n";

    /**
     * Setzt die Klasse bei Soeldnern und Feinden
     *
     * @author Lang
     * @since 30.11.2023
     */
    public PDD() {
        this.setBezeichnung(Klasse.PDD);
        this.setNutzbareAusruestung(NUTZBARE_AUSRUESTUNG);
    }

    /**
     * Setzt die Klasse und setzt Attributspunkte auf Standardwerte für die Klasse.
     * Grundsätzlich für die Erstellung und den Klassenwechsel des Hauptcharakters gedacht.
     * Vergebene Attributspunkte werden hier nicht beruecksichtigt
     *
     * @param spielerCharakter Der SpielerCharakter
     * @author Lang
     * @since 30.11.2023
     */
    public PDD(SpielerCharakter spielerCharakter) {
        this.setBezeichnung(Klasse.PDD);
        this.setNutzbareAusruestung(NUTZBARE_AUSRUESTUNG);
        spielerCharakter.setMaxGesundheitsPunkte(DEFAULT_ATTRIBUTE[0]);
        spielerCharakter.setGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte());
        spielerCharakter.setMaxManaPunkte(DEFAULT_ATTRIBUTE[1]);
        spielerCharakter.setManaPunkte(spielerCharakter.getMaxManaPunkte());
        spielerCharakter.setPhysischeAttacke(DEFAULT_ATTRIBUTE[2]);
        spielerCharakter.setMagischeAttacke(DEFAULT_ATTRIBUTE[3]);
        spielerCharakter.setGenauigkeit(DEFAULT_ATTRIBUTE[4]);
        spielerCharakter.setVerteidigung(DEFAULT_ATTRIBUTE[5]);
        spielerCharakter.setMagischeVerteidigung(DEFAULT_ATTRIBUTE[6]);
        spielerCharakter.setResistenz(DEFAULT_ATTRIBUTE[7]);
        spielerCharakter.setBeweglichkeit(DEFAULT_ATTRIBUTE[8]);
        spielerCharakter.setGesundheitsRegeneration(DEFAULT_ATTRIBUTE[9]);
        spielerCharakter.setManaRegeneration(DEFAULT_ATTRIBUTE[10]);
        spielerCharakter.setGrafischeDarstellung(DARSTELLUNG);
        spielerCharakter.setFaehigkeiten(FaehigkeitFactory.erstelleFaehigkeitFuer(this.getBezeichnung(), spielerCharakter.getLevel()));
        spielerCharakter.setGeschichte(GESCHICHTE.replaceAll("#NAME#", spielerCharakter.getName()));
    }

    public static String getGeschichte() {
        return GESCHICHTE;
    }

    @Override
    public String getDarstellung() {
        return DARSTELLUNG;
    }

}
