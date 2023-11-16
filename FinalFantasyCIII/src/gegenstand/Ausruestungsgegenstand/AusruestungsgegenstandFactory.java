package gegenstand.Ausruestungsgegenstand;

import charakter.model.klassen.HLR;
import charakter.model.klassen.Klasse;
import charakter.model.klassen.MDD;
import charakter.model.klassen.PDD;
import gamehub.haendler.Haendler;

//TODO: java doc schreiben!
public class AusruestungsgegenstandFactory {
    private static final String[] moeglicheKlassen = {"HLR", "MDD", "PDD", "TNK"};

    public static Ruestung erstelleRuestungFuer(Object objekt , int derzeitigesLevel){
        Ruestung returnRuestung;
        if(objekt instanceof Klasse){
            if(objekt instanceof HLR){
                returnRuestung = new Ruestung("HLR", derzeitigesLevel, true);
            } else if (objekt instanceof MDD){
                returnRuestung = new Ruestung("MDD", derzeitigesLevel, true);
            } else if(objekt instanceof PDD){
                returnRuestung = new Ruestung("PDD", derzeitigesLevel, true);
            } else {
                returnRuestung = new Ruestung("TNK", derzeitigesLevel, true);
            }
        } else if(objekt instanceof Haendler){
            returnRuestung = new Ruestung(moeglicheKlassen[(int) Math.random()*moeglicheKlassen.length], derzeitigesLevel);
        } else{
            System.err.println("Keine Ruestung für diese Klasse erstellbar! - null wird zurückgegeben");
            returnRuestung = null;
        }
        return returnRuestung;
    }

    public static Waffe erstelleWaffeFuer(Object objekt , int derzeitigesLevel){
        Waffe returnWaffe;
        if(objekt instanceof Klasse){
            if(objekt instanceof HLR){
                returnWaffe = new Waffe("HLR", derzeitigesLevel, true);
            } else if (objekt instanceof MDD){
                returnWaffe = new Waffe("MDD", derzeitigesLevel, true);
            } else if(objekt instanceof PDD){
                returnWaffe = new Waffe("PDD", derzeitigesLevel, true);
            } else {
                returnWaffe = new Waffe("TNK", derzeitigesLevel, true);
            }
        } else if(objekt instanceof Haendler){
            returnWaffe = new Waffe(moeglicheKlassen[(int) Math.random()*moeglicheKlassen.length], derzeitigesLevel);
        } else{
            System.err.println("Keine Waffe für diese Klasse erstellbar! - null wird zurückgegeben");
            returnWaffe = null;
        }
        return returnWaffe;
    }

    public static Accessoire erstelleAccessoireFuer(Object objekt , int derzeitigesLevel){
        Accessoire returnAccessoire;
        if(objekt instanceof Klasse){
            if(objekt instanceof HLR){
                returnAccessoire = new Accessoire("HLR", derzeitigesLevel, true);
            } else if (objekt instanceof MDD){
                returnAccessoire = new Accessoire("MDD", derzeitigesLevel, true);
            } else if(objekt instanceof PDD){
                returnAccessoire = new Accessoire("PDD", derzeitigesLevel, true);
            } else {
                returnAccessoire = new Accessoire("TNK", derzeitigesLevel, true);
            }
        } else if(objekt instanceof Haendler){
            returnAccessoire = new Accessoire(moeglicheKlassen[(int) Math.random()*moeglicheKlassen.length], derzeitigesLevel);
        } else{
            System.err.println("Keine Accessoire für diese Klasse erstellbar! - null wird zurückgegeben");
            returnAccessoire = null;
        }
        return returnAccessoire;
    }
}
