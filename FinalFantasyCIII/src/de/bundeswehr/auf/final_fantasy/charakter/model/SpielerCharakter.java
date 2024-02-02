package de.bundeswehr.auf.final_fantasy.charakter.model;

import de.bundeswehr.auf.final_fantasy.charakter.controller.CharakterController;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.HLR;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.MDD;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.PDD;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.TNK;
import de.bundeswehr.auf.final_fantasy.gegenstaende.controller.AusruestungsGegenstandFactory;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;
import de.bundeswehr.auf.final_fantasy.party.model.AusruestungsGegenstandInventar;

public class SpielerCharakter extends Charakter {

    private int erfahrungsPunkte;
    private String geschichte;
    private int offeneAttributpunkte;
    private int offeneFaehigkeitspunkte;
    private boolean soeldner;
    private int verteilteFaehigkeitspunkte;

    /**
     * Kopiert die Werte eines Charakters.
     *
     * @author OFR Rieger
     * @since 19.01.24
     */
    protected SpielerCharakter(SpielerCharakter sc) {
        setAttribute(new AttributCharakter(sc));
        // Charakter
        sc.setAccessoires(getAccessoires());
        sc.setBeweglichkeit(getBeweglichkeit());
        sc.setFaehigkeiten(getFaehigkeiten());
        sc.setGenauigkeit(getGenauigkeit());
        sc.setGesundheitsPunkte(getGesundheitsPunkte());
        sc.setGesundheitsRegeneration(getGesundheitsRegeneration());
        sc.setKlasse(getKlasse());
        sc.setLevel(getLevel());
        sc.setMagischeAttacke(getMagischeAttacke());
        sc.setMagischeVerteidigung(getMagischeVerteidigung());
        sc.setManaPunkte(getManaPunkte());
        sc.setManaRegeneration(getManaRegeneration());
        sc.setMaxGesundheitsPunkte(getMaxGesundheitsPunkte());
        sc.setMaxManaPunkte(getMaxManaPunkte());
        sc.setName(getName());
        sc.setPhysischeAttacke(getPhysischeAttacke());
        sc.setResistenz(getResistenz());
        sc.setRuestung(getRuestung());
        sc.setVerteidigung(getVerteidigung());
        sc.setWaffe(getWaffe());
        // SpielerCharakter
        sc.setErfahrungsPunkte(erfahrungsPunkte);
        sc.setGeschichte(geschichte);
        sc.setOffeneFaehigkeitspunkte(offeneFaehigkeitspunkte);
        sc.setOffeneAttributpunkte(offeneAttributpunkte);
        sc.setVerteilteFaehigkeitspunkte(verteilteFaehigkeitspunkte);
        sc.setSoeldner(soeldner);
    }

    /**
     * Erstellt einen Hauptcharakter.
     *
     * @param name       Name des Charakters - String
     * @param klasse     Klasse des Charakters - String
     * @param geschichte Geschichte des Charakters - String
     * @author Lang
     * @since 30.11.2023
     */
    public SpielerCharakter(String name, String klasse, String geschichte) {
        this.geschichte = geschichte;
        erfahrungsPunkte = 100;
        offeneAttributpunkte = 0;
        verteilteFaehigkeitspunkte = 0;
        offeneFaehigkeitspunkte = 0;
        setName(name);
        setLevel(1);
        setAttribute(new AttributCharakter(this));
        switch (klasse) {
            case Klasse.HLR:
                setKlasse(new HLR(this));
                break;
            case Klasse.MDD:
                setKlasse(new MDD(this));
                break;
            case Klasse.PDD:
                setKlasse(new PDD(this));
                break;
            case Klasse.TNK:
                setKlasse(new TNK(this));
                break;
            default:
                throw new RuntimeException("Spieler-Klasse konnte nicht gesetzt werden: " + klasse);
        }
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleWaffeFuer(getKlasse(), getLevel()));
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleRuestungFuer(getKlasse(), getLevel()));
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleAccessoireFuer(getKlasse(), getLevel()));
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleAccessoireFuer(getKlasse(), getLevel()));
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleAccessoireFuer(getKlasse(), getLevel()));
    }

    /**
     * Erstellt einen Söldner, bzw. Nebencharakter.
     *
     * @param name       Name des Charakters
     * @param klasse     Klasse des Charakters
     * @param geschichte Geschichte des Charakters
     * @param partyLevel Level des Charakters
     * @author Lang
     * @since 30.11.2023
     */
    public SpielerCharakter(String name, String klasse, String geschichte, int partyLevel) {
        this.geschichte = geschichte;
        erfahrungsPunkte = partyLevel * 100;
        offeneAttributpunkte = 0;
        verteilteFaehigkeitspunkte = partyLevel;
        offeneFaehigkeitspunkte = 0;
        soeldner = true;
        setName(name);
        setLevel(partyLevel);
        setAttribute(new AttributCharakter(this));
        switch (klasse) {
            case Klasse.HLR:
                setKlasse(new HLR());
                setGrafischeDarstellung("charakter/freund/heiler.png");
                break;
            case Klasse.MDD:
                setKlasse(new MDD());
                setGrafischeDarstellung("charakter/freund/mdd.png");
                break;
            case Klasse.PDD:
                setKlasse(new PDD());
                setGrafischeDarstellung("charakter/freund/pdd.png");
                break;
            case Klasse.TNK:
                setKlasse(new TNK());
                setGrafischeDarstellung("charakter/freund/tank.png");
                break;
            default:
                throw new RuntimeException("Söldner-Klasse konnte nicht gesetzt werden: " + klasse);
        }
        getAttribute().setMaxGesundheitsPunkte(generateRandomValue(getKlasse().getDefaultAttribute()[0]));
        getAttribute().setMaxManaPunkte(generateRandomValue(getKlasse().getDefaultAttribute()[1]));
        getAttribute().setPhysischeAttacke(generateRandomValue(getKlasse().getDefaultAttribute()[2]));
        getAttribute().setMagischeAttacke(generateRandomValue(getKlasse().getDefaultAttribute()[3]));
        getAttribute().setGenauigkeit(generateRandomValue(getKlasse().getDefaultAttribute()[4]));
        getAttribute().setVerteidigung(generateRandomValue(getKlasse().getDefaultAttribute()[5]));
        getAttribute().setMagischeVerteidigung(generateRandomValue(getKlasse().getDefaultAttribute()[6]));
        getAttribute().setResistenz(generateRandomValue(getKlasse().getDefaultAttribute()[7]));
        getAttribute().setBeweglichkeit(generateRandomValue(getKlasse().getDefaultAttribute()[8]));
        getAttribute().setGesundheitsRegeneration(generateRandomValue(getKlasse().getDefaultAttribute()[9]));
        getAttribute().setManaRegeneration(generateRandomValue(getKlasse().getDefaultAttribute()[10]));
        setFaehigkeiten(FaehigkeitFactory.erstelleFaehigkeitFuer(getKlasse().getBezeichnung(), partyLevel));
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleWaffeFuer(this, getLevel()));
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleRuestungFuer(this, getLevel()));
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleAccessoireFuer(this, getLevel()));
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleAccessoireFuer(this, getLevel()));
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleAccessoireFuer(this, getLevel()));
    }

    @Override
    public SpielerCharakter clone() {
        // FIXME sollte nicht benötigt sein!
        return new SpielerCharakter(this);
    }

    public int getErfahrungsPunkte() {
        return erfahrungsPunkte;
    }

    public String getGeschichte() {
        return geschichte;
    }

    public int getOffeneAttributpunkte() {
        return offeneAttributpunkte;
    }

    public int getOffeneFaehigkeitspunkte() {
        return offeneFaehigkeitspunkte;
    }

    public int getVerteilteFaehigkeitspunkte() {
        return verteilteFaehigkeitspunkte;
    }

    public boolean isSoeldner() {
        return soeldner;
    }

    public void setErfahrungsPunkte(int erfahrungsPunkte) {
        this.erfahrungsPunkte = erfahrungsPunkte;
    }

    public void setGeschichte(String geschichte) {
        this.geschichte = geschichte;
    }

    public void setOffeneAttributpunkte(int offeneAttributpunkte) {
        this.offeneAttributpunkte = offeneAttributpunkte;
    }

    public void setOffeneFaehigkeitspunkte(int offeneFaehigkeitspunkte) {
        this.offeneFaehigkeitspunkte = offeneFaehigkeitspunkte;
    }

    public void setSoeldner(boolean soeldner) {
        this.soeldner = soeldner;

    }

    public void setVerteilteFaehigkeitspunkte(int verteilteFaehigkeitspunkte) {
        this.verteilteFaehigkeitspunkte = verteilteFaehigkeitspunkte;
    }

}
