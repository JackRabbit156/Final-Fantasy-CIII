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

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * ausruestungsgegenstandHinzufuegen ermoeglicht das Hinzufuegen von Ausruestungsgegenstaenden ins Inventar
     * @param ausruestungsgegenstand : Welcher Ausruestungsgegenstand soll hinzugefuegt werden?
     */
    public void ausruestungsgegenstandHinzufuegen(Ausruestungsgegenstand ausruestungsgegenstand){
        if(ausruestungsgegenstand instanceof Ruestung){
            this.inventarRuestung.add((Ruestung) ausruestungsgegenstand);
        } else if (ausruestungsgegenstand instanceof Waffe){
            this.inventarWaffen.add((Waffe) ausruestungsgegenstand);
        } else if(ausruestungsgegenstand instanceof Accessoire){
            this.inventarAccessiore.add((Accessoire) ausruestungsgegenstand);
        }
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * ausruestungsgegenstandEntfernen ermoeglicht das entfernen von Ausruestungsgegenstaenden ins Inventar
     * @param ausruestungsgegenstand : Welcher Ausruestungsgegenstand soll entfernt werden?
     */
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

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * getGetrageneAusreustungsgegenstaende den Zugriff auf alle getragenen Ausreustungsgegenstaende
     * @param party : fuer welche Party sollen alle getragenen Ausruestungsgegenstaende ausgegeben werden?
     * @return gibt die List aller getragenen Ausruestungsgegenstaende zurück
     * @see Ausruestungsgegenstand
     */
    public static ArrayList<Ausruestungsgegenstand> getGetrageneAusruestungsgegenstaende(Party party){
        ArrayList<Ausruestungsgegenstand> returnListe = new ArrayList<>(CharakterController.ausruestungAnzeigen(party.getHauptCharakter()));
        for(SpielerCharakter charakter : party.getNebenCharakter()){
            if(charakter != null){
            returnListe.addAll(CharakterController.ausruestungAnzeigen(charakter));
            }
        }
        return returnListe;
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * getGetrageneWaffen den Zugriff auf alle getragenen Waffen einer Party
     * @param party : fuer welche Party sollen alle getragenen Waffen ausgegeben werden?
     * @return gibt die List aller getragenen Waffen zurück
     * @see Waffe
     */
    public static ArrayList<Waffe> getGetrageneWaffen(Party party){
        ArrayList<Waffe> waffenListe = new ArrayList<>();
        for(Ausruestungsgegenstand ausruestungsgegenstand : getGetrageneAusruestungsgegenstaende(party)){
            if(ausruestungsgegenstand instanceof Waffe){
                waffenListe.add((Waffe)ausruestungsgegenstand);
            }
        }
        return waffenListe;
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * getGetrageneRuestung den Zugriff auf alle getragenen Ruestung einer Party
     * @param party : fuer welche Party sollen alle getragenen Ruestung ausgegeben werden?
     * @return gibt die List aller getragenen Ruestung zurück
     * @see Ruestung
     */
    public static ArrayList<Ruestung> getGetrageneRuestung(Party party){
        ArrayList<Ruestung> reustungListe = new ArrayList<>();
        for(Ausruestungsgegenstand ausruestungsgegenstand : getGetrageneAusruestungsgegenstaende(party)){
            if(ausruestungsgegenstand instanceof Ruestung){
                reustungListe.add((Ruestung) ausruestungsgegenstand);
            }
        }
        return reustungListe;
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * getGetrageneAccessiores den Zugriff auf alle getragenen Accessiore einer Party
     * @param party : fuer welche Party sollen alle getragenen Accessiore ausgegeben werden?
     * @return gibt die List aller getragenen Accessiore zurück
     * @see Accessoire
     */
    public static ArrayList<Accessoire> getGetrageneAccessiores(Party party){
        ArrayList<Accessoire> accessioreListe = new ArrayList<>();
        for(Ausruestungsgegenstand ausruestungsgegenstand : getGetrageneAusruestungsgegenstaende(party)){
            if(ausruestungsgegenstand instanceof Accessoire){
                accessioreListe.add((Accessoire) ausruestungsgegenstand);
            }
        }
        return accessioreListe;
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
