package de.bundeswehr.auf.final_fantasy.charakter.model.klassen;

import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MDD extends Klasse {

    public static final List<String> NUTZBARE_AUSRUESTUNG = new ArrayList<>(Arrays.asList("MagierStab", "EinhandWaffe", "Bogen", "MittlereRuestung"));

    private static final String DARSTELLUNG = "charakter/freund/mdd.png";
    /**
     * 0 = maxGesundheitsPunkte, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
     * 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegeneration,
     * 10 = manaRegeneration
     */
    private static final int[] DEFAULT_ATTRIBUTE = { 10, 20, 3, 6, 5, 2, 4, 5, 5, 2, 4 };
    private static final String GESCHICHTE = "Nach Abschluss seiner Ausbildung verließ #NAME# die Schule und zog durch die Länder, um sein Wissen zu erweitern und neue Zaubersprüche zu erlernen. Dabei stieß er auf eine uralte Prophezeiung, die von einem bösen Zauberer und einer drohenden Dunkelheit sprach.\n" +
            "Entschlossen, dem Bösen entgegenzutreten, schloss sich #NAME# einer Gruppe tapferer Abenteurer an, die ebenfalls gegen das Unheil kämpften. Mit seinen mächtigen Zaubern und magischen Fähigkeiten unterstützte #NAME# seine Gefährten im Kampf gegen Horden von Untoten, gefährliche Drachen und andere finstere Kreaturen.\n" +
            "Im epischen finalen Kampf nutzte #NAME# seine gesamte Macht, um den bösen Zauberer zu besiegen und das Land vor der drohenden Dunkelheit zu retten. Sein mutiges Handeln und seine Fähigkeit, die Kräfte der Magie zum Wohl aller einzusetzen, machten ihn zu einem wahren Helden.\n" +
            "Die Geschichten über #NAME# verbreiteten sich wie ein Lauffeuer und sein Name wurde in den Annalen der Magie verewigt. Sein Erbe lebt weiter und inspiriert junge Zauberer dazu, ihre eigenen Kräfte zum Schutz der Welt einzusetzen.\n";

    /**
     * Setzt die Klasse bei Soeldnern und Feinden
     *
     * @author Lang
     * @since 30.11.2023
     */
    public MDD() {
        this.setBezeichnung(Klasse.MDD);
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
        setBezeichnung(Klasse.MDD);
        setNutzbareAusruestung(NUTZBARE_AUSRUESTUNG);
        spielerCharakter.getAttribute().setMaxGesundheitsPunkte(DEFAULT_ATTRIBUTE[0]);
        spielerCharakter.getAttribute().setMaxManaPunkte(DEFAULT_ATTRIBUTE[1]);
        spielerCharakter.getAttribute().setPhysischeAttacke(DEFAULT_ATTRIBUTE[2]);
        spielerCharakter.getAttribute().setMagischeAttacke(DEFAULT_ATTRIBUTE[3]);
        spielerCharakter.getAttribute().setGenauigkeit(DEFAULT_ATTRIBUTE[4]);
        spielerCharakter.getAttribute().setVerteidigung(DEFAULT_ATTRIBUTE[5]);
        spielerCharakter.getAttribute().setMagischeVerteidigung(DEFAULT_ATTRIBUTE[6]);
        spielerCharakter.getAttribute().setResistenz(DEFAULT_ATTRIBUTE[7]);
        spielerCharakter.getAttribute().setBeweglichkeit(DEFAULT_ATTRIBUTE[8]);
        spielerCharakter.getAttribute().setGesundheitsRegeneration(DEFAULT_ATTRIBUTE[9]);
        spielerCharakter.getAttribute().setManaRegeneration(DEFAULT_ATTRIBUTE[10]);
        spielerCharakter.setGrafischeDarstellung(DARSTELLUNG);
        spielerCharakter.setFaehigkeiten(FaehigkeitFactory.erstelleFaehigkeitFuer(getBezeichnung(), spielerCharakter.getLevel()));
        spielerCharakter.setGeschichte(GESCHICHTE.replaceAll("#NAME#", spielerCharakter.getName()));
    }

    public static String getGeschichte() {
        return GESCHICHTE;
    }

    @Override
    public String getDarstellung() {
        return DARSTELLUNG;
    }

    @Override
    public int[] getDefaultAttribute() {
        return DEFAULT_ATTRIBUTE;
    }

}
