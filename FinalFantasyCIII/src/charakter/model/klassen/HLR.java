package charakter.model.klassen;

import charakter.model.SpielerCharakter;
import trainer.faehigkeiten.FaehigkeitFabrik;

import java.util.ArrayList;
import java.util.Arrays;

public class HLR extends Klasse {

    public static final ArrayList<String> NUTZBARE_AUSRUESTUNG = new ArrayList<>(Arrays.asList("Heilerwaffe", "LeichteRuestung"));
    private static final String geschichte = "Schon früh zeigte sich #NAME#s Fähigkeit, andere zu heilen, zu trösten und zu unterstützen. Er beschloss, seine Gabe zu nutzen, um anderen zu helfen und zog aus, um Abenteurer in ihren gefährlichen Kämpfen zu unterstützen.\n" +
            "Seine magischen Fähigkeiten und Kräuterkenntnisse machten ihn zu einem wertvollen Mitglied jeder Gruppe von Abenteurern. Immer bereit, seine Fähigkeiten einzusetzen, um Verletzungen zu heilen oder Krankheiten zu lindern, half er seinen Gefährten, unzählige Kämpfe zu überstehen.\n" +
            "Mit der Zeit wurde #NAME# zu einem erfahrenen Heiler und gänzlichem Lebensretter. Seine Fähigkeiten waren so gefragt, dass er schließlich sogar eine eigene Praxis eröffnete, in der er Verletzte und Kranke behandelte.\n" +
            "Seine unermüdliche Arbeit und sein Einsatz für das Wohl anderer machten ihn zu einem beliebten und respektierten Heiler. Sein Ruf eilte ihm voraus und viele kamen von weit her, um von ihm geheilt zu werden.\n";

    /**
     * Setzt die Klasse bei Soeldnern und Feinden
     *
     * @since 30.11.2023
     * @author Lang
     */
    public HLR(){
        this.setBezeichnung("Healer");
        this.setNutzbareAusruestung(NUTZBARE_AUSRUESTUNG);
    }

    /**
     * Setzt die Klasse und setzt Attributspunkte auf Standardwerte für die Klasse.
     * Grundsätzlich für die Erstellung und den Klassenwechsel des Hauptcharakters gedacht.
     * Vergebene Attributspunkte werden hier nicht beruecksichtigt
     *
     * @param spielerCharakter Der SpielerCharakter
     *
     * @since 30.11.2023
     * @author Lang
     */
    public HLR(SpielerCharakter spielerCharakter) {
        this.setBezeichnung("Healer");
        this.setNutzbareAusruestung(NUTZBARE_AUSRUESTUNG);
        spielerCharakter.setMaxGesundheitsPunkte(10);
        spielerCharakter.setGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte());
        spielerCharakter.setMaxManaPunkte(20);
        spielerCharakter.setManaPunkte(spielerCharakter.getMaxManaPunkte());
        spielerCharakter.setPhysischeAttacke(3);
        spielerCharakter.setMagischeAttacke(0);
        spielerCharakter.setGenauigkeit(5);
        spielerCharakter.setVerteidigung(2);
        spielerCharakter.setMagischeVerteidigung(4);
        spielerCharakter.setResistenz(5);
        spielerCharakter.setBeweglichkeit(5);
        spielerCharakter.setGesundheitsRegeneration(2);
        spielerCharakter.setManaRegeneration(4);
        spielerCharakter.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(this.getBezeichnung(), spielerCharakter.getLevel()));
        spielerCharakter.setGrafischeDarstellung("charaktere/freund/heiler.png");
        spielerCharakter.setGeschichte(geschichte.replaceAll("#NAME#", spielerCharakter.getName()));
    }

    public static String getGeschichte() {
        return geschichte;
    }
}
