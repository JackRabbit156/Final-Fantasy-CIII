package charakter.model.klassen.spezialisierungen;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.PDD;
import trainer.faehigkeiten.FaehigkeitFabrik;

public class Berserker extends PDD implements Spezialisierung{

    // 0 = maxGesundheitsPunkt, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegenartion,
    // 10 = manaRegeneration
    private Integer[] attribute = {0,0,3,0,-1,-2,0,0,1,0,0};
    private final String GESCHICHTE = "Ein tapferer Berserker namens #NAME# durchstreifte die Länder auf der Suche nach Abenteuern und Herausforderungen. Seine unglaubliche Stärke und sein unbezähmbarer Mut machten ihn zu einer Legende. Aufgewachsen in den Bergen, lernte er früh die harte Kunst des Überlebens und die Fertigkeit im Kampf. Im Laufe der Jahre verfeinerte #NAME# seine Fähigkeiten und befreite zahllose Dörfer von tyrannischen Bedrohungen. Doch während er auf seinem blutigen Pfad wandelte, entdeckte er auch sein Mitgefühl für die Schwachen und Bedürftigen. #NAME# wurde zu einem Beschützer der Wehrlosen, einer Lichtgestalt in dunklen Zeiten. Jeder, der sein Schwert kreuzte, wurde von der Wut eines berserkenden Unwetters getroffen, doch diejenigen, denen er half, erlebten seine gnädige Geduld und Freundlichkeit. #NAME# wird für immer als ein Hüter der Gerechtigkeit und ein Symbol der Hoffnung in Erinnerung bleiben.";

    public Berserker(SpielerCharakter charakter){
        charakter.setBeweglichkeit(charakter.getBeweglichkeit() + attribute[8]);
        charakter.setGenauigkeit(charakter.getGenauigkeit() + attribute[4]);
        charakter.setVerteidigung(charakter.getVerteidigung() + attribute[5]);
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + attribute[2]);
        charakter.setKlasse(this);
        FaehigkeitFabrik.spezialisierungsFaehigkeitHinzufuegen(charakter);
        charakter.setGeschichte(GESCHICHTE.replaceAll("#NAME#", charakter.getName()));
    }

    public Integer[] getAttribute() {
        return attribute;
    }

}
