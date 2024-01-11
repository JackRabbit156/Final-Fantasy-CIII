package de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen;

import de.bundeswehr.auf.final_fantasy.charakter.model.Spezialisierung;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.PDD;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;

public class Berserker extends PDD implements Spezialisierung {

    private static final String GESCHICHTE = "Ein tapferer Berserker namens #NAME# durchstreifte die Länder auf der Suche nach Abenteuern und Herausforderungen. Seine unglaubliche Stärke und sein unbezähmbarer Mut machten ihn zu einer Legende. Aufgewachsen in den Bergen, lernte er früh die harte Kunst des Überlebens und die Fertigkeit im Kampf. Im Laufe der Jahre verfeinerte #NAME# seine Fähigkeiten und befreite zahllose Dörfer von tyrannischen Bedrohungen. Doch während er auf seinem blutigen Pfad wandelte, entdeckte er auch sein Mitgefühl für die Schwachen und Bedürftigen. #NAME# wurde zu einem Beschützer der Wehrlosen, einer Lichtgestalt in dunklen Zeiten. Jeder, der sein Schwert kreuzte, wurde von der Wut eines berserkenden Unwetters getroffen, doch diejenigen, denen er half, erlebten seine gnädige Geduld und Freundlichkeit. #NAME# wird für immer als ein Hüter der Gerechtigkeit und ein Symbol der Hoffnung in Erinnerung bleiben.";
    // 0 = maxGesundheitsPunkte, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegeneration,
    // 10 = manaRegeneration
    private static final int[] DEFAULT_ATTRIBUTE = { 0, 0, 3, 0, -1, -2, 0, 0, 1, 0, 0 };

    public Berserker(SpielerCharakter charakter) {
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + DEFAULT_ATTRIBUTE[2]);
        charakter.setGenauigkeit(charakter.getGenauigkeit() + DEFAULT_ATTRIBUTE[4]);
        charakter.setVerteidigung(charakter.getVerteidigung() + DEFAULT_ATTRIBUTE[5]);
        charakter.setBeweglichkeit(charakter.getBeweglichkeit() + DEFAULT_ATTRIBUTE[8]);
        charakter.setKlasse(this);
        FaehigkeitFactory.spezialisierungsFaehigkeitHinzufuegen(charakter);
        charakter.setGeschichte(GESCHICHTE.replaceAll("#NAME#", charakter.getName()));
    }

    @Override
    public int[] getDefaultAttribute() {
        return DEFAULT_ATTRIBUTE;
    }

}
