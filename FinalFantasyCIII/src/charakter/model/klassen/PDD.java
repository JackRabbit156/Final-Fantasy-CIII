package charakter.model.klassen;

import charakter.model.SpielerCharakter;
import gamehub.trainer.faehigkeiten.FaehigkeitFabrik;

public class PDD extends Klasse{

    public PDD(){
        this.setBezeichnung("Physischer DD");
    }

    public PDD(SpielerCharakter spielerCharakter) {
        this.setBezeichnung("Physischer DD");
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
        spielerCharakter.setGrafischeDarstellung("Dummy PDD-Darstellung");
        spielerCharakter.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(spielerCharakter));
        //TODO implement CharakterDarstellung
    }
}
