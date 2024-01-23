package de.bundeswehr.auf.final_fantasy.charakter.model;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model.Faehigkeit;

import java.util.List;

/**
 * Es werden nur die 11 Grund-Attribute verwaltet, damit die ursprünglichen Werte beim Skillen oder durch Buffen, etc.
 * nicht verloren gehen:
 * <ul>
 *     <li>Beweglichkeit</li>
 *     <li>Genauigkeit</li>
 *     <li>GesundheitsRegeneration</li>
 *     <li>MagischeAttacke</li>
 *     <li>MagischeVerteidigung</li>
 *     <li>ManaRegeneration</li>
 *     <li>MaxGesundheitsPunkte</li>
 *     <li>MaxManaPunkte</li>
 *     <li>PhysischeAttacke</li>
 *     <li>Resistenz</li>
 *     <li>Verteidigung</li>
 * </ul>
 * GesundheitsPunkte werden auf MaxGesundheitsPunkte gesetzt.<br>
 * ManaPunkte werden auf MaxManaPunkte gesetzt.<br><br>
 * Eine Anpassung der Werte des Attribut-Charakters passt die entsprechenden Werte des Charakters an.
 * Werden MaxGesundheitsPunkte oder MaxManaPunkte angepasst, werden analog GesundheitsPunkte und ManaPunkte angepasst.
 * Ein ungewolltes Heilen oder illegales Erhöhen von Mana ist dabei ausgeschlossen.
 *
 * @author OFR Rieger
 * @since 19.01.24
 */
public class AttributCharakter extends Charakter {

    public static class NoValidAttributeException extends RuntimeException {

        public NoValidAttributeException(String attribut) {
            super(String.format("'%s' ist kein Attribut des Attribut-Charakters", attribut));
        }

    }

    private final Charakter charakter;

    /**
     * Beweglichkeit,
     * Genauigkeit,
     * GesundheitsRegeneration,
     * MagischeAttacke,
     * MagischeVerteidigung,
     * ManaRegeneration,
     * MaxGesundheitsPunkte,
     * MaxManaPunkte,
     * PhysischeAttacke,
     * Resistenz,
     * Verteidigung
     * werden vom Charakter übernommen.
     * <br>
     * GesundheitsPunkte werden auf MaxGesundheitsPunkte gesetzt und können nicht verändert werden.
     * ManaPunkte werden auf MaxManaPunkte gesetzt und können nicht verändert werden.
     *
     * @param charakter Die Attributwerte dieses Charakters werden übernommen
     */
    public AttributCharakter(Charakter charakter) {
        this.charakter = charakter;
        super.setBeweglichkeit(charakter.getBeweglichkeit());
        super.setGenauigkeit(charakter.getGenauigkeit());
        super.setGesundheitsPunkte(charakter.getMaxGesundheitsPunkte());
        super.setGesundheitsRegeneration(charakter.getGesundheitsRegeneration());
        super.setMagischeAttacke(charakter.getMagischeAttacke());
        super.setMagischeVerteidigung(charakter.getMagischeVerteidigung());
        super.setManaPunkte(charakter.getMaxManaPunkte());
        super.setManaRegeneration(charakter.getManaRegeneration());
        super.setMaxGesundheitsPunkte(charakter.getMaxGesundheitsPunkte());
        super.setMaxManaPunkte(charakter.getMaxManaPunkte());
        super.setPhysischeAttacke(charakter.getPhysischeAttacke());
        super.setResistenz(charakter.getResistenz());
        super.setVerteidigung(charakter.getVerteidigung());
    }

    @Override
    public Accessoire getAccessoire(int i) {
        throw new NoValidAttributeException("Accessoires");
    }

    @Override
    public Accessoire[] getAccessoires() {
        throw new NoValidAttributeException("Accessoires");
    }

    @Override
    public AttributCharakter getAttribute() {
        throw new NoValidAttributeException("Attribute");
    }

    @Override
    public List<Faehigkeit> getFaehigkeiten() {
        throw new NoValidAttributeException("Faehigkeiten");
    }

    @Override
    public String getGrafischeDarstellung() {
        throw new NoValidAttributeException("GrafischeDarstellung");
    }

    @Override
    public Klasse getKlasse() {
        throw new NoValidAttributeException("Klasse");
    }

    @Override
    public int getLevel() {
        throw new NoValidAttributeException("Level");
    }

    @Override
    public String getName() {
        throw new NoValidAttributeException("Name");
    }

    @Override
    public Ruestung getRuestung() {
        throw new NoValidAttributeException("Ruestung");
    }

    @Override
    public Waffe getWaffe() {
        throw new NoValidAttributeException("Waffe");
    }

    @Override
    public void setAccessoire(Accessoire accessoire, int i) {
        throw new NoValidAttributeException("Accessoires");
    }

    @Override
    public void setAccessoires(Accessoire[] accessoires) {
        throw new NoValidAttributeException("Accessoires");
    }

    @Override
    public void setBeweglichkeit(int beweglichkeit) {
        charakter.setBeweglichkeit(charakter.getBeweglichkeit() + beweglichkeit - getBeweglichkeit());
        super.setBeweglichkeit(beweglichkeit);
    }

    @Override
    public void setFaehigkeiten(List<Faehigkeit> faehigkeiten) {
        throw new NoValidAttributeException("Faehigkeiten");
    }

    @Override
    public void setGenauigkeit(int genauigkeit) {
        charakter.setGenauigkeit(charakter.getGenauigkeit() + genauigkeit - getGenauigkeit());
        super.setGenauigkeit(genauigkeit);
    }

    @Override
    public void setGesundheitsPunkte(int gesundheitsPunkte) {
        throw new NoValidAttributeException("GesundheitsPunkte");
    }

    @Override
    public void setGesundheitsRegeneration(int gesundheitsRegeneration) {
        charakter.setGesundheitsRegeneration(charakter.getGesundheitsRegeneration() + gesundheitsRegeneration - getGesundheitsRegeneration());
        super.setGesundheitsRegeneration(gesundheitsRegeneration);
    }

    @Override
    public void setGrafischeDarstellung(String grafischeDarstellung) {
        throw new NoValidAttributeException("GrafischeDarstellung");
    }

    @Override
    public void setKlasse(Klasse klasse) {
        throw new NoValidAttributeException("Klasse");
    }

    @Override
    public void setLevel(int level) {
        throw new NoValidAttributeException("Level");
    }

    @Override
    public void setMagischeAttacke(int magischeAttacke) {
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() + magischeAttacke - getMagischeAttacke());
        super.setMagischeAttacke(magischeAttacke);
    }

    @Override
    public void setMagischeVerteidigung(int magischeVerteidigung) {
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + magischeVerteidigung - getMagischeVerteidigung());
        super.setMagischeVerteidigung(magischeVerteidigung);
    }

    @Override
    public void setManaPunkte(int manaPunkte) {
        throw new NoValidAttributeException("ManaPunkte");
    }

    @Override
    public void setManaRegeneration(int manaRegeneration) {
        charakter.setManaRegeneration(charakter.getManaRegeneration() + manaRegeneration - getManaRegeneration());
        super.setManaRegeneration(manaRegeneration);
    }

    @Override
    public void setMaxGesundheitsPunkte(int maxGesundheitsPunkte) {
        int delta = maxGesundheitsPunkte - getMaxGesundheitsPunkte();
        // Gesundheitspunkte werden durch Skillen nur dann angepasst, wenn der Charakter voll geheilt ist oder die
        // Anpassung negativ ist, bis zu einem Minimum von 1.
        if (charakter.getGesundheitsPunkte() == charakter.getMaxGesundheitsPunkte()) {
            charakter.setGesundheitsPunkte(charakter.getGesundheitsPunkte() + delta);
        }
        else if (charakter.getGesundheitsPunkte() + delta < 1) {
            charakter.setGesundheitsPunkte(1);
        }
        else if (delta < 0) {
            charakter.setGesundheitsPunkte(charakter.getGesundheitsPunkte() + delta);
        }
        charakter.setMaxGesundheitsPunkte(charakter.getMaxGesundheitsPunkte() + delta);
        super.setMaxGesundheitsPunkte(maxGesundheitsPunkte);
        super.setGesundheitsPunkte(maxGesundheitsPunkte);
    }

    @Override
    public void setMaxManaPunkte(int maxManaPunkte) {
        int delta = maxManaPunkte - getMaxManaPunkte();
        // Manapunkte werden durch Skillen nur dann angepasst, wenn das Mana des Charakters oder die
        // Anpassung negativ ist, bis zu einem Minimum von 1.
        if (charakter.getManaPunkte() == charakter.getMaxManaPunkte()) {
            charakter.setManaPunkte(charakter.getManaPunkte() + delta);
        }
        else if (charakter.getManaPunkte() + delta < 1) {
            charakter.setManaPunkte(1);
        }
        else if (delta < 0) {
            charakter.setManaPunkte(charakter.getManaPunkte() + delta);
        }
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + delta);
        super.setMaxManaPunkte(maxManaPunkte);
        super.setManaPunkte(maxManaPunkte);
    }

    @Override
    public void setName(String name) {
        throw new NoValidAttributeException("Name");
    }

    @Override
    public void setPhysischeAttacke(int physischeAttacke) {
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + physischeAttacke - getPhysischeAttacke());
        super.setPhysischeAttacke(physischeAttacke);
    }

    @Override
    public void setResistenz(int resistenz) {
        charakter.setResistenz(charakter.getResistenz() + resistenz - getResistenz());
        super.setResistenz(resistenz);
    }

    @Override
    public void setRuestung(Ruestung ruestung) {
        throw new NoValidAttributeException("Ruestung");
    }

    @Override
    public void setVerteidigung(int verteidigung) {
        charakter.setVerteidigung(charakter.getVerteidigung() + verteidigung - getVerteidigung());
        super.setVerteidigung(verteidigung);
    }

    @Override
    public void setWaffe(Waffe waffe) {
        throw new NoValidAttributeException("Waffe");
    }

    @Override
    protected void setAttribute(AttributCharakter attribute) {
        throw new NoValidAttributeException("Attribute");
    }

    @Override
    public String toString() {
        return charakter.getName() +
                " {" +
                "maxGP=" + getMaxGesundheitsPunkte() +
                ", GP=" + getMaxGesundheitsPunkte() +
                ", maxMP=" + getMaxManaPunkte() +
                ", MP=" + getMaxManaPunkte() +
                ", A=" + getPhysischeAttacke() +
                ", MA=" + getPhysischeAttacke() +
                ", G=" + getGenauigkeit() +
                ", V=" + getVerteidigung() +
                ", MV=" + getMagischeVerteidigung() +
                ", Res=" + getResistenz() +
                ", B=" + getBeweglichkeit() +
                ", GR=" + getGesundheitsRegeneration() +
                ", MR=" + getManaRegeneration() +
                '}';
    }

}
