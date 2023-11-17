package party;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;

import java.util.ArrayList;

public class AusruestungsgegenstandInventar {
    private ArrayList<Waffe> inventarWaffen;
    private ArrayList<Ruestung> inventarRuestung;
    private ArrayList<Accessoire> inventarAccessiore;
    private ArrayList<Ausruestungsgegenstand> gesamteAusruestungsgegenstaende;

//Konstruktor
    public AusruestungsgegenstandInventar() {
        this.inventarWaffen = new ArrayList<>();
        this.inventarRuestung = new ArrayList<>();
        this.inventarAccessiore = new ArrayList<>();
        this.gesamteAusruestungsgegenstaende = new ArrayList<>();
    }

//Methoden
    public ArrayList<Ausruestungsgegenstand> getGesamteAusruestungsgegenstaende(){
        gesamteAusruestungsgegenstaende.clear();
        gesamteAusruestungsgegenstaende.addAll(this.inventarWaffen);
        gesamteAusruestungsgegenstaende.addAll(this.inventarRuestung);
        gesamteAusruestungsgegenstaende.addAll(this.inventarAccessiore);
        return this.gesamteAusruestungsgegenstaende;
    }

    public void ausruestungsgegenstandHinzufuegen(Ausruestungsgegenstand ausruestungsgegenstand){
        if(ausruestungsgegenstand instanceof Ruestung){
            this.inventarRuestung.add((Ruestung) ausruestungsgegenstand);
        } else if (ausruestungsgegenstand instanceof Waffe){
            this.inventarWaffen.add((Waffe) ausruestungsgegenstand);
        } else if(ausruestungsgegenstand instanceof Accessoire){
            this.inventarAccessiore.add((Accessoire) ausruestungsgegenstand);
        }
    }

    public void ausruestungsgegenstandEntfernen(Ausruestungsgegenstand ausruestungsgegenstand){
        if(ausruestungsgegenstand instanceof Ruestung){
            this.inventarRuestung.remove(ausruestungsgegenstand);
        } else if (ausruestungsgegenstand instanceof Waffe){
            this.inventarWaffen.remove(ausruestungsgegenstand);
        } else if(ausruestungsgegenstand instanceof Accessoire){
            this.inventarAccessiore.remove(ausruestungsgegenstand);
        }
    }

//statische Methoden
    public static ArrayList<Ausruestungsgegenstand> getGetrageneAusreustungsgegenstaende(Party party){
        ArrayList<Ausruestungsgegenstand> returnListe = new ArrayList<>(CharakterController.ausruestungAnzeigen(party.getHauptCharakter()));
        for(SpielerCharakter charakter : party.getNebenCharakter()){
            returnListe.addAll(CharakterController.ausruestungAnzeigen(charakter));
        }
        return returnListe;
    }

//Getter / Setter

    public ArrayList<Waffe> getInventarWaffen() {
        return inventarWaffen;
    }

    public void setInventarWaffen(ArrayList<Waffe> inventarWaffen) {
        this.inventarWaffen = inventarWaffen;
    }

    public ArrayList<Ruestung> getInventarRuestung() {
        return inventarRuestung;
    }

    public void setInventarRuestung(ArrayList<Ruestung> inventarRuestung) {
        this.inventarRuestung = inventarRuestung;
    }

    public ArrayList<Accessoire> getInventarAccessiore() {
        return inventarAccessiore;
    }

    public void setInventarAccessiore(ArrayList<Accessoire> inventarAccessiore) {
        this.inventarAccessiore = inventarAccessiore;
    }
}
