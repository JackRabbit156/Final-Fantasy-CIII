package charakter.model.klassen;

import charakter.model.SpielerCharakter;
import trainer.faehigkeiten.FaehigkeitFabrik;

import java.util.ArrayList;
import java.util.Arrays;

public class MDD extends Klasse {

    public static final ArrayList<String> NUTZBARE_AUSRUESTUNG = new ArrayList<>(Arrays.asList("Magierstab", "MittlereRuestung"));
    private static final String geschichte = "Nach Abschluss seiner Ausbildung verließ #NAME# die Schule und zog durch die Länder, um sein Wissen zu erweitern und neue Zaubersprüche zu erlernen. Dabei stieß er auf eine uralte Prophezeiung, die von einem bösen Zauberer und einer drohenden Dunkelheit sprach.\n" +
            "Entschlossen, dem Bösen entgegenzutreten, schloss sich #NAME# einer Gruppe tapferer Abenteurer an, die ebenfalls gegen das Unheil kämpften. Mit seinen mächtigen Zaubern und magischen Fähigkeiten unterstützte #NAME# seine Gefährten im Kampf gegen Horden von Untoten, gefährliche Drachen und andere finstere Kreaturen.\n" +
            "Im epischen finalen Kampf nutzte #NAME# seine gesamte Macht, um den bösen Zauberer zu besiegen und das Land vor der drohenden Dunkelheit zu retten. Sein mutiges Handeln und seine Fähigkeit, die Kräfte der Magie zum Wohl aller einzusetzen, machten ihn zu einem wahren Helden.\n" +
            "Die Geschichten über #NAME# verbreiteten sich wie ein Lauffeuer und sein Name wurde in den Annalen der Magie verewigt. Sein Erbe lebt weiter und inspiriert junge Zauberer dazu, ihre eigenen Kräfte zum Schutz der Welt einzusetzen.\n" ;

    private static final String darstellung = "charaktere/freund/mdd.png";

    /**
     * Setzt die Klasse bei Soeldnern und Feinden
     *
     * @author Lang
     * @since 30.11.2023
     */
    public MDD() {
        this.setBezeichnung("Magischer DD");
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
    public MDD(SpielerCharakter spielerCharakter) {
        this.setBezeichnung("Magischer DD");
        this.setNutzbareAusruestung(NUTZBARE_AUSRUESTUNG);
        spielerCharakter.setMaxGesundheitsPunkte(10);
        spielerCharakter.setGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte());
        spielerCharakter.setMaxManaPunkte(20);
        spielerCharakter.setManaPunkte(spielerCharakter.getMaxManaPunkte());
        spielerCharakter.setPhysischeAttacke(3);
        spielerCharakter.setMagischeAttacke(6);
        spielerCharakter.setGenauigkeit(5);
        spielerCharakter.setVerteidigung(2);
        spielerCharakter.setMagischeVerteidigung(4);
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

    public String getDarstellung() {
        return darstellung;
    }
}
