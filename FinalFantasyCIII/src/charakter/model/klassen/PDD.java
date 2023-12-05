package charakter.model.klassen;

import charakter.model.SpielerCharakter;
import trainer.faehigkeiten.FaehigkeitFabrik;

import java.util.ArrayList;
import java.util.Arrays;

public class PDD extends Klasse {

    public static final ArrayList<String> NUTZBARE_AUSRUESTUNG = new ArrayList<>(Arrays.asList("Einhandwaffe", "Schildwaffe", "Zweihandwaffe", "SchwereRuestung"));
    private static final String geschichte = "#NAME# wuchs in einer Familie von Kriegern auf, in der er den Kampf von klein auf lernte. Als er älter wurde, begann er seine Fähigkeiten als Kämpfer zu perfektionieren und reiste durch das Land, um seine Fähigkeiten im Kampf zu testen und zu verbessern.\n" +
            "Schließlich fand er eine Gruppe von Abenteurern, die seine Leidenschaft für den Kampf teilten. Zusammen bereisten sie die Welt, machten Jagd auf Monster und kämpften gegen mächtige Feinde. #NAME# zeichnete sich dabei immer wieder durch seine körperliche Stärke und seine geschickte Handhabung diverser Waffen aus.\n" +
            "Mit der Zeit wurde #NAME# zum Anführer der Gruppe und kämpfte unermüdlich für das Wohl seiner Freunde. Seine Entschlossenheit und seine Fähigkeit, seine Kameraden in schwierigen Situationen zu schützen, machten ihn zu einem unverzichtbaren Mitglied des Teams.\n" +
            "Als die Gruppe schließlich einen mächtigen Feind bekämpfte, opferte #NAME# sein eigenes Leben, um seine Freunde vor Schaden zu bewahren. Sein Mut und sein Opfergeist machten ihn zu einem Helden, der in Erinnerung bleiben wird.\n" +
            "Die Legende von #NAME# lebt weiter und inspiriert andere Kämpfer, seinem Beispiel zu folgen und ihrem eigenen Mut zu vertrauen, um die Welt zu einem besseren Ort zu machen.\n";

    private static final String darstellung = "charaktere/freund/pdd.png";

    /**
     * Setzt die Klasse bei Soeldnern und Feinden
     *
     * @author Lang
     * @since 30.11.2023
     */
    public PDD() {
        this.setBezeichnung("Physischer DD");
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
        this.setBezeichnung("Physischer DD");
        this.setNutzbareAusruestung(NUTZBARE_AUSRUESTUNG);
        spielerCharakter.setMaxGesundheitsPunkte(20);
        spielerCharakter.setGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte());
        spielerCharakter.setMaxManaPunkte(10);
        spielerCharakter.setManaPunkte(spielerCharakter.getMaxManaPunkte());
        spielerCharakter.setPhysischeAttacke(6);
        spielerCharakter.setMagischeAttacke(0);
        spielerCharakter.setGenauigkeit(5);
        spielerCharakter.setVerteidigung(4);
        spielerCharakter.setMagischeVerteidigung(2);
        spielerCharakter.setResistenz(5);
        spielerCharakter.setBeweglichkeit(5);
        spielerCharakter.setGesundheitsRegeneration(2);
        spielerCharakter.setManaRegeneration(4);
        spielerCharakter.setGrafischeDarstellung(darstellung);
        spielerCharakter.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(this.getBezeichnung(), spielerCharakter.getLevel()));
        spielerCharakter.setGeschichte(geschichte.replaceAll("#NAME#", spielerCharakter.getName()));
    }

    public static String getGeschichte() {
        return geschichte;
    }

    @Override
    public String getDarstellung() {
        return darstellung;
    }
}
