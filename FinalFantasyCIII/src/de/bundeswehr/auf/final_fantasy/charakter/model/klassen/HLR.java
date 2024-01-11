package de.bundeswehr.auf.final_fantasy.charakter.model.klassen;

import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HLR extends Klasse {

    public static final List<String> NUTZBARE_AUSRUESTUNG = new ArrayList<>(Arrays.asList("HeilerStab", "MagierStab", "Bogen", "LeichteRuestung"));

    private static final String DARSTELLUNG = "charakter/freund/heiler.png";
    // 0 = maxGesundheitsPunkte, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegeneration,
    // 10 = manaRegeneration
    private static final int[] DEFAULT_ATTRIBUTE = { 10, 20, 3, 0, 5, 2, 4, 5, 5, 2, 4 };
    private static final String GESCHICHTE = "Schon früh zeigte sich #NAME#s Fähigkeit, andere zu heilen, zu trösten und zu unterstützen. Er beschloss, seine Gabe zu nutzen, um anderen zu helfen und zog aus, um Abenteurer in ihren gefährlichen Kämpfen zu unterstützen.\n" +
            "Seine magischen Fähigkeiten und Kräuterkenntnisse machten ihn zu einem wertvollen Mitglied jeder Gruppe von Abenteurern. Immer bereit, seine Fähigkeiten einzusetzen, um Verletzungen zu heilen oder Krankheiten zu lindern, half er seinen Gefährten, unzählige Kämpfe zu überstehen.\n" +
            "Mit der Zeit wurde #NAME# zu einem erfahrenen Heiler und gänzlichem Lebensretter. Seine Fähigkeiten waren so gefragt, dass er schließlich sogar eine eigene Praxis eröffnete, in der er Verletzte und Kranke behandelte.\n" +
            "Seine unermüdliche Arbeit und sein Einsatz für das Wohl anderer machten ihn zu einem beliebten und respektierten Heiler. Sein Ruf eilte ihm voraus und viele kamen von weit her, um von ihm geheilt zu werden.\n";

    /**
     * Setzt die Klasse bei Soeldnern und Feinden
     *
     * @author Lang
     * @since 30.11.2023
     */
    public HLR() {
        this.setBezeichnung(Klasse.HLR);
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
    public HLR(SpielerCharakter spielerCharakter) {
        this.setBezeichnung(Klasse.HLR);
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
        spielerCharakter.setFaehigkeiten(FaehigkeitFactory.erstelleFaehigkeitFuer(this.getBezeichnung(), spielerCharakter.getLevel()));
        spielerCharakter.setGrafischeDarstellung(DARSTELLUNG);
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
