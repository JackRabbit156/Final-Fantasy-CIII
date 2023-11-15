package charakter.model;

import charakter.model.klassen.*;
import charakter.model.klassen.spezialisierungen.Spezialisierung;
import gamehub.trainer.faehigkeiten.Faehigkeit;
import gegenstand.Ausruestungsgegenstand.Accesssoire;
import gegenstand.Ausruestungsgegenstand.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffe;

import java.util.ArrayList;

public abstract class Charakter {

    private Klasse klasse;
    private Spezialisierung spezialisierung;
    private String name;
    private String grafischeDarstellung;
    private int level;
    private int maxGesundheitsPunkte;
    private int gesundheitsPunkte;
    private int maxManaPunkte;
    private int manaPunkte;
    private int physischeAttacke;
    private int magischeAttacke;
    private int genauigkeit;
    private int verteidigung;
    private int magischeVerteidigung;
    private int resistenz;
    private int beweglichkeit;
    private int gesundheitsRegeneration;
    private int manaRegeneration;
    private ArrayList<Faehigkeit> faehigkeiten;
    private Waffe waffe;
    private Ruestung ruestung;
    private Accesssoire[] accesssoires;



    public Klasse getKlasse() {
        return klasse;
    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
    }

    public Spezialisierung getSpezialisierung() {
        return spezialisierung;
    }

    public void setSpezialisierung(Spezialisierung spezialisierung) {
        this.spezialisierung = spezialisierung;
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

    public Accesssoire[] getAccesssoires() {
        return accesssoires;
    }

    public void setAccesssoires(Accesssoire[] accesssoires) {
        this.accesssoires = accesssoires;
    }
}
