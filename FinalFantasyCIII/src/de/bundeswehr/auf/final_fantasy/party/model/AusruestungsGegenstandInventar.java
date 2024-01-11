package de.bundeswehr.auf.final_fantasy.party.model;

import de.bundeswehr.auf.final_fantasy.charakter.controller.CharakterController;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.AusruestungsGegenstand;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;

import java.util.ArrayList;
import java.util.List;

public class AusruestungsGegenstandInventar {

    private List<Waffe> inventarWaffen;
    private List<Ruestung> inventarRuestung;
    private List<Accessoire> inventarAccessiore;
    private List<AusruestungsGegenstand> gesamteAusruestungsgegenstaende;

    //Konstruktor
    public AusruestungsGegenstandInventar() {
        this.inventarWaffen = new ArrayList<>();
        this.inventarRuestung = new ArrayList<>();
        this.inventarAccessiore = new ArrayList<>();
        this.gesamteAusruestungsgegenstaende = new ArrayList<>();
    }

    //Methoden
    public List<AusruestungsGegenstand> getGesamteAusruestungsgegenstaende() {
        gesamteAusruestungsgegenstaende.clear();
        gesamteAusruestungsgegenstaende.addAll(this.inventarWaffen);
        gesamteAusruestungsgegenstaende.addAll(this.inventarRuestung);
        gesamteAusruestungsgegenstaende.addAll(this.inventarAccessiore);
        return this.gesamteAusruestungsgegenstaende;
    }

    /**
     * @param ausruestungsgegenstand : Welcher Ausruestungsgegenstand soll hinzugefuegt werden?
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * ausruestungsgegenstandHinzufuegen ermoeglicht das Hinzufuegen von Ausruestungsgegenstaenden ins Inventar
     */
    public void ausruestungsgegenstandHinzufuegen(AusruestungsGegenstand ausruestungsgegenstand) {
        if (ausruestungsgegenstand instanceof Ruestung) {
            this.inventarRuestung.add((Ruestung) ausruestungsgegenstand);
        }
        else if (ausruestungsgegenstand instanceof Waffe) {
            this.inventarWaffen.add((Waffe) ausruestungsgegenstand);
        }
        else if (ausruestungsgegenstand instanceof Accessoire) {
            this.inventarAccessiore.add((Accessoire) ausruestungsgegenstand);
        }
    }

    /**
     * @param ausruestungsgegenstand : Welcher Ausruestungsgegenstand soll entfernt werden?
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * ausruestungsgegenstandEntfernen ermoeglicht das entfernen von Ausruestungsgegenstaenden ins Inventar
     */
    public void ausruestungsgegenstandEntfernen(AusruestungsGegenstand ausruestungsgegenstand) {
        if (ausruestungsgegenstand instanceof Ruestung) {
            this.inventarRuestung.remove(ausruestungsgegenstand);
        }
        else if (ausruestungsgegenstand instanceof Waffe) {
            this.inventarWaffen.remove(ausruestungsgegenstand);
        }
        else if (ausruestungsgegenstand instanceof Accessoire) {
            this.inventarAccessiore.remove(ausruestungsgegenstand);
        }
    }

//statische Methoden

    /**
     * getGetrageneAusreustungsgegenstaende den Zugriff auf alle getragenen Ausreustungsgegenstaende
     *
     * @param party : fuer welche Party sollen alle getragenen Ausruestungsgegenstaende ausgegeben werden?
     * @return gibt die List aller getragenen Ausruestungsgegenstaende zurueck
     * @author 11777914 OLt Oliver Ebert
     * @see AusruestungsGegenstand
     * @since 20.11.2023
     */
    public static List<AusruestungsGegenstand> getGetrageneAusruestungsgegenstaende(Party party) {
        List<AusruestungsGegenstand> returnListe = new ArrayList<>(CharakterController.ausruestungAnzeigen(party.getHauptCharakter()));
        for (SpielerCharakter charakter : party.getNebenCharakter()) {
            if (charakter != null) {
                returnListe.addAll(CharakterController.ausruestungAnzeigen(charakter));
            }
        }
        return returnListe;
    }

    /**
     * getGetrageneWaffen den Zugriff auf alle getragenen Waffen einer Party
     *
     * @param party : fuer welche Party sollen alle getragenen Waffen ausgegeben werden?
     * @return gibt die List aller getragenen Waffen zurueck
     * @author 11777914 OLt Oliver Ebert
     * @see Waffe
     * @since 20.11.2023
     */
    public static List<Waffe> getGetrageneWaffen(Party party) {
        List<Waffe> waffenListe = new ArrayList<>();
        for (AusruestungsGegenstand ausruestungsgegenstand : getGetrageneAusruestungsgegenstaende(party)) {
            if (ausruestungsgegenstand instanceof Waffe) {
                waffenListe.add((Waffe) ausruestungsgegenstand);
            }
        }
        return waffenListe;
    }

    /**
     * getGetrageneRuestung den Zugriff auf alle getragenen Ruestung einer Party
     *
     * @param party : fuer welche Party sollen alle getragenen Ruestung ausgegeben werden?
     * @return gibt die List aller getragenen Ruestung zurueck
     * @author 11777914 OLt Oliver Ebert
     * @see Ruestung
     * @since 20.11.2023
     */
    public static List<Ruestung> getGetrageneRuestung(Party party) {
        List<Ruestung> reustungListe = new ArrayList<>();
        for (AusruestungsGegenstand ausruestungsgegenstand : getGetrageneAusruestungsgegenstaende(party)) {
            if (ausruestungsgegenstand instanceof Ruestung) {
                reustungListe.add((Ruestung) ausruestungsgegenstand);
            }
        }
        return reustungListe;
    }

    /**
     * getGetrageneAccessiores den Zugriff auf alle getragenen Accessiore einer Party
     *
     * @param party : fuer welche Party sollen alle getragenen Accessiore ausgegeben werden?
     * @return gibt die List aller getragenen Accessiore zurueck
     * @author 11777914 OLt Oliver Ebert
     * @see Accessoire
     * @since 20.11.2023
     */
    public static List<Accessoire> getGetrageneAccessiores(Party party) {
        List<Accessoire> accessioreListe = new ArrayList<>();
        for (AusruestungsGegenstand ausruestungsgegenstand : getGetrageneAusruestungsgegenstaende(party)) {
            if (ausruestungsgegenstand instanceof Accessoire) {
                accessioreListe.add((Accessoire) ausruestungsgegenstand);
            }
        }
        return accessioreListe;
    }

//Getter / Setter

    public List<Waffe> getInventarWaffen() {
        return inventarWaffen;
    }

    public void setInventarWaffen(List<Waffe> inventarWaffen) {
        this.inventarWaffen = inventarWaffen;
    }

    public List<Ruestung> getInventarRuestung() {
        return inventarRuestung;
    }

    public void setInventarRuestung(List<Ruestung> inventarRuestung) {
        this.inventarRuestung = inventarRuestung;
    }

    public List<Accessoire> getInventarAccessiore() {
        return inventarAccessiore;
    }

    public void setInventarAccessiore(List<Accessoire> inventarAccessiore) {
        this.inventarAccessiore = inventarAccessiore;
    }
}
