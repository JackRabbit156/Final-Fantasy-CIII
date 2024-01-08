package charakter.model;

import charakter.model.klassen.*;
import trainer.faehigkeiten.Faehigkeit;
import gegenstaende.ausruestung.Accessoire;
import gegenstaende.ausruestung.ruestungen.Ruestung;
import gegenstaende.ausruestung.waffen.Waffe;

import java.util.ArrayList;

public abstract class Charakter {

    private Accessoire[] accessoires;
    private int beweglichkeit;
    private ArrayList<Faehigkeit> faehigkeiten;
    private int genauigkeit;
    private int gesundheitsPunkte;
    private int gesundheitsRegeneration;
    private String grafischeDarstellung;
    private Klasse klasse;
    private int level;
    private int magischeAttacke;
    private int magischeVerteidigung;
    private int manaPunkte;
    private int manaRegeneration;
    private int maxGesundheitsPunkte;
    private int maxManaPunkte;
    private String name;
    private int physischeAttacke;
    private int resistenz;
    private Ruestung ruestung;
    private int verteidigung;
    private Waffe waffe;



    public Klasse getKlasse() {
        return klasse;
    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrafischeDarstellung() {
        return grafischeDarstellung;
    }

    public void setGrafischeDarstellung(String grafischeDarstellung) {
        this.grafischeDarstellung = grafischeDarstellung;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxGesundheitsPunkte() {
        return maxGesundheitsPunkte;
    }

    public void setMaxGesundheitsPunkte(int maxGesundheitsPunkte) {
        this.maxGesundheitsPunkte = maxGesundheitsPunkte;
    }

    public int getGesundheitsPunkte() {
        return gesundheitsPunkte;
    }

    public void setGesundheitsPunkte(int gesundheitsPunkte) {
        this.gesundheitsPunkte = gesundheitsPunkte;
    }

    public int getMaxManaPunkte() {
        return maxManaPunkte;
    }

    public void setMaxManaPunkte(int maxManaPunkte) {
        this.maxManaPunkte = maxManaPunkte;
    }

    public int getManaPunkte() {
        return manaPunkte;
    }

    public void setManaPunkte(int manaPunkte) {
        this.manaPunkte = manaPunkte;
    }

    public int getPhysischeAttacke() {
        return physischeAttacke;
    }

    public void setPhysischeAttacke(int physischeAttacke) {
        this.physischeAttacke = physischeAttacke;
    }

    public int getMagischeAttacke() {
        return magischeAttacke;
    }

    public void setMagischeAttacke(int magischeAttacke) {
        this.magischeAttacke = magischeAttacke;
    }

    public int getGenauigkeit() {
        return genauigkeit;
    }

    public void setGenauigkeit(int genauigkeit) {
        this.genauigkeit = genauigkeit;
    }

    public int getVerteidigung() {
        return verteidigung;
    }

    public void setVerteidigung(int verteidigung) {
        this.verteidigung = verteidigung;
    }

    public int getMagischeVerteidigung() {
        return magischeVerteidigung;
    }

    public void setMagischeVerteidigung(int magischeVerteidigung) {
        this.magischeVerteidigung = magischeVerteidigung;
    }

    public int getResistenz() {
        return resistenz;
    }

    public void setResistenz(int resistenz) {
        this.resistenz = resistenz;
    }

    public int getBeweglichkeit() {
        return beweglichkeit;
    }

    public void setBeweglichkeit(int beweglichkeit) {
        this.beweglichkeit = beweglichkeit;
    }

    public int getGesundheitsRegeneration() {
        return gesundheitsRegeneration;
    }

    public void setGesundheitsRegeneration(int gesundheitsRegeneration) {
        this.gesundheitsRegeneration = gesundheitsRegeneration;
    }

    public int getManaRegeneration() {
        return manaRegeneration;
    }

    public void setManaRegeneration(int manaRegeneration) {
        this.manaRegeneration = manaRegeneration;
    }

    public ArrayList<Faehigkeit> getFaehigkeiten() {
        return faehigkeiten;
    }

    public void setFaehigkeiten(ArrayList<Faehigkeit> faehigkeiten) {
        this.faehigkeiten = faehigkeiten;
    }

    public Waffe getWaffe() {
        return waffe;
    }

    public void setWaffe(Waffe waffe) {
        this.waffe = waffe;
    }

    public Ruestung getRuestung() {
        return ruestung;
    }

    public void setRuestung(Ruestung ruestung) {
        this.ruestung = ruestung;
    }

    public Accessoire[] getAccessoires() {
        return accessoires;
    }

    public void setAccessoires(Accessoire[] accessoires) {
        this.accessoires = accessoires;
    }

    /**
     * Setzt ein Accessoire auf den uebergebenen Index
     * Index groesse 0 -&gt; 2
     * @param accessoire accessoire
     * @param i int
     *
     * @since 18.11.2023
     * @author Lang
     */
    public void setAccessoire(Accessoire accessoire, int i) {this.accessoires[i] = accessoire;}

    /**
     * Gibt Accessoire am angegeben Index zurueck
     * Index groesse 0 -&gt; 2
     * @param i int
     * @return Accessoire
     *
     * @since 20.11.2023
     * @author Lang
     */
    public Accessoire getAccessoire(int i){
        return this.accessoires[i];
    }
}
