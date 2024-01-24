package de.bundeswehr.auf.final_fantasy.menu.kampf.controller;

import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.TNK;
import de.bundeswehr.auf.final_fantasy.menu.kampf.Kampf;
import de.bundeswehr.auf.final_fantasy.menu.kampf.view.KampfView;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model.Faehigkeit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FeindLogik {

    private static final Random RANDOM_NUMBER_GENERATOR = new Random();

    private Kampf kampf;
    private final KampfController kampfController;

    public FeindLogik(KampfController kampfController) {
        this.kampfController = kampfController;
    }

    /**
     * Abhängig von der Klasse des Gegners wird hier seine Angriffs/Heal- Logik
     * bestimmt und entweder geblockt oder eine Fähigkeit genutzt
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    public void logik() {
        Feind aktuellerCharakter = (Feind) kampf.getAktuellerCharakter();
        double chanceFaehigkeitEinzusetzen;
        switch (aktuellerCharakter.getKlasse().getBezeichnung()) {
            // 35% Wahrscheinlichkeit, dass der Tank angreift (Selbstheilung oder Schaden am
            // Spieler-Team), 65% Chance, dass der Tank blockt
            case Klasse.TNK:
                chanceFaehigkeitEinzusetzen = 0.35;
                break;
            // Alle anderen Klassen haben die gleichen Wahrscheinlichkeiten zu blocken (10%)
            // oder eine Fähigkeit zu benutzen (90%)
            case Klasse.HLR:
            case Klasse.MDD:
            case Klasse.PDD:
                chanceFaehigkeitEinzusetzen = 0.9;
                break;
            default:
                throw new RuntimeException("Ungültige Klasse: " + aktuellerCharakter.getKlasse().getBezeichnung());
        }
        if (RANDOM_NUMBER_GENERATOR.nextDouble() < chanceFaehigkeitEinzusetzen) {
            faehigkeitUndZiele(aktuellerCharakter);
        }
        else {
            kampfController.blocken();
        }
    }

    public void setKampf(Kampf kampf) {
        this.kampf = kampf;
    }

    /**
     * Lässt Gegner Fähigkeit und Ziele wählen, falls er angreift und nicht blockt
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    private void faehigkeitUndZiele(Feind aktuellerCharakter) {
        List<Charakter> zielGruppe;
        String feindKlasse = aktuellerCharakter.getKlasse().getBezeichnung();
        List<Faehigkeit> moeglicheFaehigkeiten = new ArrayList<>();
        Faehigkeit faehigkeit = null;

        // Befüllt Feind-Ziel-ArrayList (Feind-Team)
        List<Feind> moeglicheFeinde = new ArrayList<>(kampf.getFeindeDieNochLeben());
        // Befüllt SpielerCharakter-Ziel-ArrayList (SpielerCharakter-Team)
        List<SpielerCharakter> moeglicheSpielerCharaktere = new ArrayList<>(kampf.getFreundeDieNochLeben());

        // Nur Fähigkeiten sind möglich für die die Manapunkte auch reichen
        for (Faehigkeit eineFaehigkeit : kampfController.getAktiveFaehigkeiten()) {
            if (eineFaehigkeit.getManaKosten() < aktuellerCharakter.getManaPunkte()) {
                moeglicheFaehigkeiten.add(eineFaehigkeit);
            }
        }

        // Gegnerlogik ist klassenabhängig!!!
        switch (feindKlasse) {
            // Healer versuchen immer zuerst ihre Teammitglieder (inklusive sich selbst) zu
            // heilen!
            case Klasse.HLR:
                // Entfernt alle Feinde aus dem eigenen Team als mögliche Ziele die die
                // maximale Gesundheit haben
                moeglicheFeinde.removeIf(feind -> feind.getGesundheitsPunkte() == feind.getMaxGesundheitsPunkte());
                // Entfernt alle Fähigkeiten die nicht aufs eigene Team genutzt werden können
                // und entfernt alle Fähigkeiten die mehr Ziele heilen können als es
                // Teammitglieder gibt die die Heilung benötigen.
                moeglicheFaehigkeiten.removeIf(eineFaehigkeit -> eineFaehigkeit.getZielAnzahl() > moeglicheFeinde.size() || !eineFaehigkeit.isIstFreundlich());
                // Wenn nach den Filtern keine Fähigkeiten mehr übrig sind bedeutet das,
                // dass alle Feinde 100% ihrer Lebenspunkte haben. Erst jetzt will der Healer in
                // den Angriffsmodus gehen.
                if (moeglicheFaehigkeiten.isEmpty()) {
                    // Nur Fähigkeiten sind möglich für die die Manapunkte auch reichen
                    for (Faehigkeit eineFaehigkeit : kampfController.getAktiveFaehigkeiten()) {
                        if (eineFaehigkeit.getManaKosten() < aktuellerCharakter.getManaPunkte()) {
                            moeglicheFaehigkeiten.add(eineFaehigkeit);
                        }
                    }
                    // Alle Fähigkeiten die aufs eigene Team angewendet werden können fliegen raus
                    // Alle Fähigkeiten die auf mehr Charaktere angewendet werden können als es
                    // Ziele gibt fliegen raus
                    moeglicheFaehigkeiten.removeIf(eineFaehigkeit -> eineFaehigkeit.getZielAnzahl() > moeglicheSpielerCharaktere.size() || eineFaehigkeit.isIstFreundlich());
                    // Ziel-Gruppe ändert sich von eigener (Feind) zur SpielerCharakter-Gruppe
                    if (!moeglicheFaehigkeiten.isEmpty()) {
                        faehigkeit = moeglicheFaehigkeiten.get(RANDOM_NUMBER_GENERATOR.nextInt(moeglicheFaehigkeiten.size()));
                        zielGruppe = zieleDamage(faehigkeit, moeglicheSpielerCharaktere);
                    }
                    else {
                        faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
                        zielGruppe = zieleFallback(faehigkeit);
                    }
                }
                // Es gibt Feind-Charaktere (eigenes Team) die geheilt werden können.
                // Das Fähigkeitsset besteht aus den zu Anfang bestimmten Fähigkeiten
                else {
                    // Fähigkeit wird aus dem möglichen Pool zufällig gewählt
                    faehigkeit = moeglicheFaehigkeiten.get(RANDOM_NUMBER_GENERATOR.nextInt(moeglicheFaehigkeiten.size()));
                    zielGruppe = zieleHeal(faehigkeit, moeglicheFeinde);
                }
                break;

            // Tanks heilen sich entweder selbst, oder greifen die SpielerCharaktere-Gruppe
            // an, abhängig von ihren eigenen Lebenspunkten
            case Klasse.TNK:
                zielGruppe = new ArrayList<>();
                // Wenn der Tank weniger als 50% seiner maximalen Lebenspunkte hat, will er sich
                // selbst heilen
                // In allen anderen Fällen will er die SpielerCharaktere-Gruppe angreifen
                boolean willIchMichHeilen = aktuellerCharakter.getGesundheitsPunkte() * 2 < aktuellerCharakter.getMaxGesundheitsPunkte();
                if (willIchMichHeilen) {
                    // Wenn sich der Tank heilen will ist die Zielgruppe der Fähigkeit er selbst
                    zielGruppe.add(aktuellerCharakter);
                    // Alle Fähigkeiten die nicht aufs eigene Team angewendet werden können fliegen raus.
                    // Alle Fähigkeiten die auf mehr als einen Charakter angewendet werden können fliegen raus.
                    for (Faehigkeit eineFaehigkeit : new ArrayList<>(moeglicheFaehigkeiten)) {
                        if (!eineFaehigkeit.isIstFreundlich()) {
                            moeglicheFaehigkeiten.remove(eineFaehigkeit);
                        }
                        else if (eineFaehigkeit.getZielAnzahl() != 1) {
                            moeglicheFaehigkeiten.remove(eineFaehigkeit);
                        }
                    }
                    if (!moeglicheFaehigkeiten.isEmpty()) {
                        // Fähigkeit wird zufällig aus dem möglichen Pool bestimmt und auf sich
                        // selbst angewendet
                        faehigkeit = moeglicheFaehigkeiten.get(RANDOM_NUMBER_GENERATOR.nextInt(moeglicheFaehigkeiten.size()));
                        // zielWahl.add(zielGruppe.indexOf(aktuellerCharakter));
                    }
                    // Tank will sich zwar selber heilen, aber kann aus welchen Gründen auch immer
                    // keine Fähigkeit auf sich wirken. Also muss er wohl in den Angriff wechseln.
                    else {
                        willIchMichHeilen = false;
                    }
                }

                // Der Tank hat 50% oder mehr seiner maximalen Lebenspunkte oder kann keine
                // seiner Selbstheilungen benutzen. Daher will er nun Schaden an den
                // SpielerCharakteren verursachen
                if (!willIchMichHeilen) {
                    // Fähigkeiten die aufs eigene Team angewendet werden fliegen raus
                    moeglicheFaehigkeiten.removeIf(Faehigkeit::isIstFreundlich);

                    // Fähigkeiten die mehr Ziele haben als es noch auswählbare SpielerCharaktere
                    // gibt fliegen raus
                    moeglicheFaehigkeiten.removeIf(eineFaehigkeit -> eineFaehigkeit.getZielAnzahl() > moeglicheSpielerCharaktere.size());

                    // Fähigkeit wird zufällig aus dem möglichen Pool bestimmt
                    if (moeglicheFaehigkeiten.isEmpty()) {
                        moeglicheFaehigkeiten.add(aktuellerCharakter.getFaehigkeiten().get(0));
                    }
                    faehigkeit = moeglicheFaehigkeiten.get(RANDOM_NUMBER_GENERATOR.nextInt(moeglicheFaehigkeiten.size()));
                    zielGruppe = zieleDamage(faehigkeit, moeglicheSpielerCharaktere);
                }
                break;

            // 'Physische DD' und 'Magische DD' haben beide die gleiche offensive Logik,
            // welche der Logik entspricht, die der Tank verfolgt, solange er 50% oder mehr
            // seiner maximalen Lebenspunkte hat.
            case Klasse.PDD:
            case Klasse.MDD:
            default:
                moeglicheFaehigkeiten.removeIf(Faehigkeit::isIstFreundlich);
                moeglicheFaehigkeiten.removeIf(eineFaehigkeit -> eineFaehigkeit.getZielAnzahl() > moeglicheSpielerCharaktere.size());
                faehigkeit = moeglicheFaehigkeiten.get(RANDOM_NUMBER_GENERATOR.nextInt(moeglicheFaehigkeiten.size()));
                zielGruppe = zieleDamage(faehigkeit, moeglicheSpielerCharaktere);
                break;
        }

        if (faehigkeit == null ||
                faehigkeit.getManaKosten() > aktuellerCharakter.getManaPunkte() ||
                faehigkeit.getZielAnzahl() > zielGruppe.size()) {
            faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
            zieleFallback(faehigkeit);
        }
        kampfController.faehigkeitBenutzen(zielGruppe, faehigkeit);
    }

    private boolean isBetterTargetDamage(SpielerCharakter aktuellesZiel, SpielerCharakter moeglichesZiel) {
        // Tank wird bevorzugt angegriffen
        if (moeglichesZiel.getKlasse() instanceof TNK) {
            if (!(aktuellesZiel.getKlasse() instanceof TNK)) {
                return true;
            }
        }
        else if (aktuellesZiel.getKlasse() instanceof TNK) {
            return false;
        }
        // Ansonsten Reihung nach HP
        return moeglichesZiel.getGesundheitsPunkte() < aktuellesZiel.getGesundheitsPunkte();
    }

    private boolean isBetterTargetHeal(Feind aktuellesZiel, Feind moeglichesZiel) {
        return moeglichesZiel.getGesundheitsPunkte() < aktuellesZiel.getGesundheitsPunkte();
    }

    private List<Charakter> zieleDamage(Faehigkeit faehigkeit, List<SpielerCharakter> moeglicheZiele) {
        List<Charakter> zielGruppe = new ArrayList<>();
        int nochZuWaehlendeZiele = faehigkeit.getZielAnzahl();
        // Ziele werden bestimmt, wobei Tanks und dann niedrige Lebenspunkte priorisiert werden.
        while (nochZuWaehlendeZiele > 0) {
            SpielerCharakter aktuellesZiel = moeglicheZiele.get(0);
            for (SpielerCharakter ziel : moeglicheZiele) {
                if (isBetterTargetDamage(aktuellesZiel, ziel)) {
                    aktuellesZiel = ziel;
                }
            }
            moeglicheZiele.remove(aktuellesZiel);
            zielGruppe.add(aktuellesZiel);
            nochZuWaehlendeZiele--;
            if (moeglicheZiele.isEmpty()) {
                nochZuWaehlendeZiele = 0;
            }
        }
        return zielGruppe;
    }

    private List<Charakter> zieleFallback(Faehigkeit faehigkeit) {
        List<Charakter> zielGruppe = new ArrayList<>();
        if (faehigkeit.isIstFreundlich()) {
            zielGruppe.add(kampf.getFeindeDieNochLeben().get(0));
        }
        else {
            zielGruppe.add(kampf.getFreundeDieNochLeben().get(0));
        }
        return zielGruppe;
    }

    private List<Charakter> zieleHeal(Faehigkeit faehigkeit, List<Feind> moeglicheZiele) {
        List<Charakter> zielGruppe = new ArrayList<>();
        int nochZuWaehlendeZiele = faehigkeit.getZielAnzahl();
        // Ziele werden auf Grundlage ihrer Lebenspunkte gewählt.
        // Beim Heilen werden Feinde mit niedriger Gesundheit priorisiert
        while (nochZuWaehlendeZiele > 0) {
            Feind aktuellesZiel = moeglicheZiele.get(0);
            for (Feind ziel : moeglicheZiele) {
                if (isBetterTargetHeal(aktuellesZiel, ziel)) {
                    aktuellesZiel = ziel;
                }
            }
            moeglicheZiele.remove(aktuellesZiel);
            zielGruppe.add(aktuellesZiel);
            nochZuWaehlendeZiele--;
            if (moeglicheZiele.isEmpty()) {
                nochZuWaehlendeZiele = 0;
            }
        }
        return zielGruppe;
    }

}
