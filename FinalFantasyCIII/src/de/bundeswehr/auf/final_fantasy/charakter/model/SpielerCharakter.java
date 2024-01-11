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
    private boolean isSoeldner;
    private int offeneAttributpunkte;
    private int offeneFaehigkeitspunkte;
    private int verteilteFaehigkeitspunkte;

    /**
     * Erstellt SpielerCharakter
     *
     * @param name       Name des Charakters - String
     * @param klasse     Klasse des Charakters - String
     * @param geschichte Geschichte des Charakters - String
     * @author Lang
     * @since 30.11.2023
     */
    public SpielerCharakter(String name, String klasse, String geschichte) {
        this.geschichte = geschichte;
        this.erfahrungsPunkte = 100;
        this.offeneAttributpunkte = 0;
        this.verteilteFaehigkeitspunkte = 0;
        this.offeneFaehigkeitspunkte = 0;
        this.setName(name);
        this.setLevel(1);
        switch (klasse) {
            case Klasse.HLR:
                this.setKlasse(new HLR(this));
                break;
            case Klasse.MDD:
                this.setKlasse(new MDD(this));
                break;
            case Klasse.PDD:
                this.setKlasse(new PDD(this));
                break;
            case Klasse.TNK:
                this.setKlasse(new TNK(this));
                break;
            default:
                throw new RuntimeException("Spieler-Klasse konnte nicht gesetzt werden: " + klasse);
        }
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleWaffeFuer(this.getKlasse(), this.getLevel()),
                new AusruestungsGegenstandInventar());
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleRuestungFuer(this.getKlasse(), this.getLevel()),
                new AusruestungsGegenstandInventar());
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()),
                new AusruestungsGegenstandInventar());
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()),
                new AusruestungsGegenstandInventar());
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()),
                new AusruestungsGegenstandInventar());
        this.setGesundheitsPunkte(this.getMaxGesundheitsPunkte());
        this.setManaPunkte(this.getMaxManaPunkte());
    }

    /**
     * Constructor fuer die Soeldnererstellung
     *
     * @param name       Name des Charakters - String
     * @param klasse     Klasse des Charakters - String
     * @param geschichte Geschichte des Charakters - String
     * @param partyLevel Level des Charakters - int
     * @param isSoeldner Ob ein Charakter Soeldner ist - boolean
     * @author Lang
     * @since 30.11.2023
     */
    public SpielerCharakter(String name, String klasse, String geschichte, int partyLevel, boolean isSoeldner) {
        this.geschichte = geschichte;
        this.erfahrungsPunkte = partyLevel * 100;
        this.offeneAttributpunkte = 0;
        this.verteilteFaehigkeitspunkte = partyLevel;
        this.offeneFaehigkeitspunkte = 0;
        this.setName(name);
        this.setLevel(partyLevel);
        this.isSoeldner = isSoeldner;
        switch (klasse) {
            case Klasse.HLR:
                this.setKlasse(new HLR());
                break;
            case Klasse.MDD:
                this.setKlasse(new MDD());
                break;
            case Klasse.PDD:
                this.setKlasse(new PDD());
                break;
            case Klasse.TNK:
                this.setKlasse(new TNK());
                break;
            default:
                throw new RuntimeException("Söldner-Klasse konnte nicht gesetzt werden: " + klasse);
        }
        setLevel(partyLevel);
        setMaxGesundheitsPunkte(generateRandomValue());
        setGesundheitsPunkte(getMaxGesundheitsPunkte());
        setMaxManaPunkte(generateRandomValue());
        setManaPunkte(getMaxManaPunkte());
        setPhysischeAttacke(generateRandomValue());
        setMagischeAttacke(generateRandomValue());
        setGenauigkeit(generateRandomValue());
        setVerteidigung(generateRandomValue());
        setMagischeVerteidigung(generateRandomValue());
        setResistenz(generateRandomValue());
        setBeweglichkeit(generateRandomValue());
        setGesundheitsRegeneration(generateRandomValue());
        setManaRegeneration(generateRandomValue());
        this.setFaehigkeiten(FaehigkeitFactory.erstelleFaehigkeitFuer(this.getKlasse().getBezeichnung(), partyLevel));
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleWaffeFuer(this, this.getLevel()),
                new AusruestungsGegenstandInventar());
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleRuestungFuer(this, this.getLevel()),
                new AusruestungsGegenstandInventar());
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleAccessoireFuer(this, this.getLevel()),
                new AusruestungsGegenstandInventar());
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleAccessoireFuer(this, this.getLevel()),
                new AusruestungsGegenstandInventar());
        CharakterController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleAccessoireFuer(this, this.getLevel()),
                new AusruestungsGegenstandInventar());
        this.setGesundheitsPunkte(this.getMaxGesundheitsPunkte());
        this.setManaPunkte(this.getMaxManaPunkte());
        switch (klasse) {
            case Klasse.HLR:
                this.setGrafischeDarstellung("charakter/freund/heiler.png");
                break;
            case Klasse.MDD:
                this.setGrafischeDarstellung("charakter/freund/mdd.png");
                break;
            case Klasse.TNK:
                this.setGrafischeDarstellung("charakter/freund/tank.png");
                break;
            case Klasse.PDD:
            default:
                this.setGrafischeDarstellung("charakter/freund/pdd.png");
        }
    }

    @Override
    public SpielerCharakter clone() {
        SpielerCharakter sc = new SpielerCharakter(this.getName(), this.getKlasse().getBezeichnung(),
                this.getGeschichte());
        sc.setAccessoires(this.getAccessoires());
        sc.setBeweglichkeit(this.getBeweglichkeit());
        sc.setErfahrungsPunkte(this.erfahrungsPunkte);
        sc.setFaehigkeiten(this.getFaehigkeiten());
        sc.setGenauigkeit(this.getGenauigkeit());
        sc.setGeschichte(this.geschichte);
        sc.setGesundheitsPunkte(this.getGesundheitsPunkte());
        sc.setGesundheitsRegeneration(this.getGesundheitsRegeneration());
        sc.setGeschichte(this.geschichte);
        sc.setKlasse(this.getKlasse());
        sc.setLevel(this.getLevel());
        sc.setMagischeAttacke(this.getMagischeAttacke());
        sc.setMagischeVerteidigung(this.getMagischeVerteidigung());
        sc.setManaPunkte(this.getManaPunkte());
        sc.setManaRegeneration(this.getManaRegeneration());
        sc.setMaxGesundheitsPunkte(this.getMaxGesundheitsPunkte());
        sc.setMaxManaPunkte(this.getMaxManaPunkte());
        sc.setName(this.getName());
        sc.setOffeneFaehigkeitspunkte(this.offeneFaehigkeitspunkte);
        sc.setOffeneAttributpunkte(this.offeneAttributpunkte);
        sc.setPhysischeAttacke(this.getPhysischeAttacke());
        sc.setResistenz(this.getResistenz());
        sc.setRuestung(this.getRuestung());
        sc.setVerteidigung(this.getVerteidigung());
        sc.setVerteilteFaehigkeitspunkte(this.verteilteFaehigkeitspunkte);
        sc.setWaffe(this.getWaffe());
        sc.setSoeldner(this.isSoeldner);
        return sc;
    }

    public int getErfahrungsPunkte() {
        return erfahrungsPunkte;
    }

    public String getGeschichte() {
        return this.geschichte;
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

        return isSoeldner;
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
        isSoeldner = soeldner;

    }

    public void setVerteilteFaehigkeitspunkte(int verteilteFaehigkeitspunkte) {
        this.verteilteFaehigkeitspunkte = verteilteFaehigkeitspunkte;
    }

}
