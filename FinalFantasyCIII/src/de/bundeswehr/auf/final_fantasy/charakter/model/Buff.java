package de.bundeswehr.auf.final_fantasy.charakter.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Buff {

    protected int beweglichkeit;
    protected final Charakter charakter;
    protected final boolean debuff;
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

    public Buff(Charakter charakter, boolean debuff) {
        this.charakter = charakter;
        this.debuff = debuff;
    }

    public abstract Buff apply();

    public Charakter getCharakter() {
        return charakter;
    }

    public ImageView getIcon(int height) {
        String icon;
        if (debuff) {
            icon = "/icons/minus.png";
        }
        else {
            icon = "/icons/plus.png";
        }
        return new ImageView(new Image(icon, 0, height, true, true));
    }

    public String getTooltip() {
        StringBuilder sb = new StringBuilder();
        if (debuff) {
            sb.append("DEBUFF\n");
        }
        else {
            sb.append("Buff\n");
        }
        append(sb, beweglichkeit, "Beweglichkeit");
        append(sb, genauigkeit, "Genauigkeit");
        append(sb, gesundheitsPunkte, "Gesundheitspunkte");
        append(sb, gesundheitsRegeneration, "Gesundheitsregeneration");
        append(sb, magischeAttacke, "Magische Attacke");
        append(sb, magischeVerteidigung, "Magische Verteidigung");
        append(sb, manaPunkte, "Manapunkte");
        append(sb, manaRegeneration, "Manaregeneration");
        append(sb, maxGesundheitsPunkte, "Maximale Gesundheitspunkte");
        append(sb, maxManaPunkte, "Maximale Manapunkte");
        append(sb, physischeAttacke, "Physische Attacke");
        append(sb, resistenz, "Resistenz");
        append(sb, verteidigung, "Verteidigung");
        return sb.toString();
    }

    public boolean isDebuff() {
        return debuff;
    }

    public abstract Buff remove();

    private void append(StringBuilder sb, int attribut, String bezeichnung) {
        if (attribut != 0) {
            sb.append(bezeichnung);
            sb.append(": ");
            if (attribut > 0) {
                sb.append("+");
            }
            sb.append(attribut);
            sb.append("\n");
        }
    }

}
