package de.bundeswehr.auf.final_fantasy.charakter.model;

public abstract class Buff {

    protected int beweglichkeit;
    protected final Charakter charakter;
    protected int genauigkeit;
    protected int gesundheitsPunkte;
    protected int gesundheitsRegeneration;
    protected int magischeAttacke;
    protected int magischeVerteidigung;
    protected int manaPunkte;
    protected int manaRegeneration;
    protected int maxGesundheitsPunkte;
    protected int maxManaPunkte;
    protected int physischeAttacke;
    protected int resistenz;
    protected int verteidigung;

    public Buff(Charakter charakter) {
        this.charakter = charakter;
    }

    public abstract Buff apply();
    
    public abstract Buff remove();
    
}
