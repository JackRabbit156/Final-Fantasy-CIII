package de.bundeswehr.auf.final_fantasy.charakter.model;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.Faehigkeit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Charakter {

    private static final Random rnd = new Random();

    private Accessoire[] accessoires = new Accessoire[3];
    private int beweglichkeit;
    private List<Faehigkeit> faehigkeiten;
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

    /**
     * Gibt Accessoire am angegeben Index zurueck
     * Index groesse 0 -&gt; 2
     *
     * @param i int
     * @return Accessoire
     * @author Lang
     * @since 20.11.2023
     */
    public Accessoire getAccessoire(int i) {
        return this.accessoires[i];
    }

    public Accessoire[] getAccessoires() {
        return accessoires;
    }

    public int getBeweglichkeit() {
        return beweglichkeit;
    }

    public List<Faehigkeit> getFaehigkeiten() {
        return faehigkeiten;
    }

    public int getGenauigkeit() {
        return genauigkeit;
    }

    public int getGesundheitsPunkte() {
        return gesundheitsPunkte;
    }

    public int getGesundheitsRegeneration() {
        return gesundheitsRegeneration;
    }

    public String getGrafischeDarstellung() {
        return grafischeDarstellung;
    }

    public Klasse getKlasse() {
        return klasse;
    }

    public int getLevel() {
        return level;
    }

    public int getMagischeAttacke() {
        return magischeAttacke;
    }

    public int getMagischeVerteidigung() {
        return magischeVerteidigung;
    }

    public int getManaPunkte() {
        return manaPunkte;
    }

    public int getManaRegeneration() {
        return manaRegeneration;
    }

    public int getMaxGesundheitsPunkte() {
        return maxGesundheitsPunkte;
    }

    public int getMaxManaPunkte() {
        return maxManaPunkte;
    }

    public String getName() {
        return name;
    }

    public int getPhysischeAttacke() {
        return physischeAttacke;
    }

    public int getResistenz() {
        return resistenz;
    }

    public Ruestung getRuestung() {
        return ruestung;
    }

    public int getVerteidigung() {
        return verteidigung;
    }

    public Waffe getWaffe() {
        return waffe;
    }

    /**
     * Setzt ein Accessoire auf den uebergebenen Index
     * Index groesse 0 -&gt; 2
     *
     * @param accessoire accessoire
     * @param i          int
     * @author Lang
     * @since 18.11.2023
     */
    public void setAccessoire(Accessoire accessoire, int i) {
        this.accessoires[i] = accessoire;
    }

    public void setAccessoires(Accessoire[] accessoires) {
        this.accessoires = accessoires;
    }

    public void setBeweglichkeit(int beweglichkeit) {
        this.beweglichkeit = beweglichkeit;
    }

    public void setFaehigkeiten(List<Faehigkeit> faehigkeiten) {
        this.faehigkeiten = faehigkeiten;
    }

    public void setGenauigkeit(int genauigkeit) {
        this.genauigkeit = genauigkeit;
    }

    public void setGesundheitsPunkte(int gesundheitsPunkte) {
        this.gesundheitsPunkte = gesundheitsPunkte;
    }

    public void setGesundheitsRegeneration(int gesundheitsRegeneration) {
        this.gesundheitsRegeneration = gesundheitsRegeneration;
    }

    public void setGrafischeDarstellung(String grafischeDarstellung) {
        this.grafischeDarstellung = grafischeDarstellung;
    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMagischeAttacke(int magischeAttacke) {
        this.magischeAttacke = magischeAttacke;
    }

    public void setMagischeVerteidigung(int magischeVerteidigung) {
        this.magischeVerteidigung = magischeVerteidigung;
    }

    public void setManaPunkte(int manaPunkte) {
        this.manaPunkte = manaPunkte;
    }

    public void setManaRegeneration(int manaRegeneration) {
        this.manaRegeneration = manaRegeneration;
    }

    public void setMaxGesundheitsPunkte(int maxGesundheitsPunkte) {
        this.maxGesundheitsPunkte = maxGesundheitsPunkte;
    }

    public void setMaxManaPunkte(int maxManaPunkte) {
        this.maxManaPunkte = maxManaPunkte;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhysischeAttacke(int physischeAttacke) {
        this.physischeAttacke = physischeAttacke;
    }

    public void setResistenz(int resistenz) {
        this.resistenz = resistenz;
    }

    public void setRuestung(Ruestung ruestung) {
        this.ruestung = ruestung;
    }

    public void setVerteidigung(int verteidigung) {
        this.verteidigung = verteidigung;
    }

    public void setWaffe(Waffe waffe) {
        this.waffe = waffe;
    }

    protected int generateRandomValue() {
        int value = (int) Math.round(rnd.nextInt(100) * (level / 10.0));
        return value > 0 ? value : 1;
    }

}
