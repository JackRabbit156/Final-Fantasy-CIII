package charakter.model.klassen;

import charakter.model.SpielerCharakter;

public class HLR extends Klasse {

    public HLR(){
        this.setBezeichnung("Healer");
    }
    public HLR(SpielerCharakter spielerCharakter) {
        this.setBezeichnung("Healer");
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
        //TODO implement Fähigkeiten, Waffe, Rüstung, Accessiore
    }
}
