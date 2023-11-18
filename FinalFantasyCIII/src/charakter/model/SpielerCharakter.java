package charakter.model;

import charakter.model.klassen.HLR;
import charakter.model.klassen.MDD;
import charakter.model.klassen.PDD;
import charakter.model.klassen.TNK;
import gamehub.trainer.faehigkeiten.Faehigkeit;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class SpielerCharakter extends Charakter{


    private String geschichte;
    private int erfahrungsPunkte;
    private int offeneFaehigkeitspunkte;
    private int verteilteFaehigkeitspunkte;
    private int offeneAttributpunkte;


    /**
     * Erstellt SpielerCharakter
     *
     * @param name
     * @param klasse
     * @param geschichte
     *
     * @author Lang
     * @since 15.11.2023
     */
    public SpielerCharakter(String name, String klasse, String geschichte) {
        super();
        this.geschichte = geschichte;
        this.erfahrungsPunkte = 0;
        this.offeneAttributpunkte = 0;
        this.verteilteFaehigkeitspunkte = 0;
        this.offeneFaehigkeitspunkte = 0;
        this.setName(name);
        this.setLevel(1);
        if (klasse.equals("Healer")){
            this.setKlasse(new HLR());
        } else if (klasse.equals("Magischer DD")){
            this.setKlasse(new MDD(this));
        } else if (klasse.equals("Physischer DD")){
            this.setKlasse(new PDD(this));
        } else if (klasse.equals("Tank")){
            this.setKlasse(new TNK(this));
        }
        this.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(this.getKlasse() ,this.getLevel()));
        this.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(this.getKlasse(), this.getLevel()));
        this.setAccessoires(new Accessoire[3]);
        this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()), 0);
        this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()), 1);
        this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()), 2);
        //TODO implement Fähigkeiten
    }

    public String getGeschichte() {
        return geschichte;
    }

    public void setGeschichte(String geschichte) {
        this.geschichte = geschichte;
    }

    public int getErfahrungsPunkte() {
        return erfahrungsPunkte;
    }

    public void setErfahrungsPunkte(int erfahrungsPunkte) {
        this.erfahrungsPunkte = erfahrungsPunkte;
    }

    public int getOffeneFaehigkeitspunkte() {
        return offeneFaehigkeitspunkte;
    }

    public void setOffeneFaehigkeitspunkte(int offeneFaehigkeitspunkte) {
        this.offeneFaehigkeitspunkte = offeneFaehigkeitspunkte;
    }

    public int getVerteilteFaehigkeitspunkte() {
        return verteilteFaehigkeitspunkte;
    }

    public void setVerteilteFaehigkeitspunkte(int verteilteFaehigkeitspunkte) {
        this.verteilteFaehigkeitspunkte = verteilteFaehigkeitspunkte;
    }

    public int getOffeneAttributpunkte() {
        return offeneAttributpunkte;
    }

    public void setOffeneAttributpunkte(int offeneAttributpunkte) {
        this.offeneAttributpunkte = offeneAttributpunkte;
    }

    public void addFaehigkeit(Faehigkeit faehigkeit){
        this.getFaehigkeiten().add(faehigkeit);
    }
}
