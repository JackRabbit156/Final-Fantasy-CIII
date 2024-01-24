package de.bundeswehr.auf.final_fantasy.menu.kampf.controller;

import de.bundeswehr.auf.final_fantasy.Game;
import de.bundeswehr.auf.final_fantasy.charakter.controller.CharakterController;
import de.bundeswehr.auf.final_fantasy.charakter.controller.FeindController;
import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.buffs.*;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen.*;
import de.bundeswehr.auf.final_fantasy.gegenstaende.controller.GegenstandController;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Attribute;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;
import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.HauptmenuController;
import de.bundeswehr.auf.final_fantasy.menu.kampf.Kampf;
import de.bundeswehr.auf.final_fantasy.menu.kampf.view.KampfView;
import de.bundeswehr.auf.final_fantasy.menu.overlay.AnsichtsTyp;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.menu.speicherstand.SpeicherstandController;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model.Faehigkeit;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model.SpezialFaehigkeiten;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.party.model.Party;
import de.bundeswehr.auf.final_fantasy.statistik.GameOverView;
import de.bundeswehr.auf.final_fantasy.statistik.StatistikController;
import javafx.beans.property.IntegerProperty;

import java.util.*;

public class KampfController {

    private static final Random RANDOM_NUMBER_GENERATOR = new Random();

    private final Map<Charakter, List<Buff>> aktiveBuffs = new HashMap<>();
    private final List<Charakter> aktuelleZugreihenfolge = new ArrayList<>();
    private final FeindController feindController;
    private final FeindLogik feindLogik;
    private Feind[] feinde;
    private final Game gameController;
    private final List<Feind> gegnerAnordnung = new ArrayList<>();
    private final HauptmenuController hauptmenuController;
    private Kampf kampf;
    private KampfView kampfView;
    private final List<String> kampfWerteLog = new ArrayList<>();
    private final Set<Charakter> kannRegenerieren = new HashSet<>();
    private int nextKampfLogIndex = -1;
    private final Party party;
    private final List<SpielerCharakter> partyAnordnung = new ArrayList<>();
    private final PartyController partyController;
    private final SpeicherstandController speicherstandController;
    private final StatistikController statistikController;
    private final ViewController viewController;

    public KampfController(FeindController feindController, PartyController partyController,
                           StatistikController statistikController, Game gameController,
                           HauptmenuController hauptmenuController, SpeicherstandController speicherstandController,
                           ViewController viewController) {
        this.feindController = feindController;
        feindLogik = new FeindLogik(this);
        this.partyController = partyController;
        this.statistikController = statistikController;
        this.gameController = gameController;
        this.hauptmenuController = hauptmenuController;
        party = partyController.getParty();
        this.speicherstandController = speicherstandController;
        this.viewController = viewController;
    }

    /**
     * Prüft, ob der Kampf vorbei ist
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    public void aktualisiereIstKampfVorbei() {
        int feindeCounter = 0;
        int partyCounter = 0;
        for (Charakter charakter : aktuelleZugreihenfolge) {
            if (charakter instanceof Feind) {
                feindeCounter++;
            }
            else {
                partyCounter++;
            }
        }
        kampf.setKampfVorbei(feindeCounter == 0 || partyCounter == 0);
    }

    /**
     * Feedback vom KampfController, was für Schaden / Heilung, etc. verursacht
     * wurde
     *
     * @return text fürs Kampflog und Infoanzeige nach Aktionsausführung
     * @author OL Schiffer-Schmidl
     * @since 06.12.2023
     */
    public String backendFeedbackKampf() {
        if (nextKampfLogIndex == -1) {
            if (kampfWerteLog.isEmpty()) {
                return "";
            }
            else {
                nextKampfLogIndex = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = nextKampfLogIndex; i < kampfWerteLog.size(); i++) {
            sb.append(kampfWerteLog.get(i));
        }
        sb.append("\n");
        nextKampfLogIndex = kampfWerteLog.size();
        return sb.toString();
    }

    /**
     * Blocken wird durchgeführt bis der Charakter erneut dran ist oder stirbt.
     * Verteidigung und Magische Verteidigung wird um Angriff respektive magischen
     * Angriff erhöht.
     *
     * @author Melvin
     * @since 18.11.2023
     */
    public void blocken() {
        if (Game.debugModus) {
            kampfWerteLog.add(String.format("[DEBUG] Buff von %s: V=%d, MV=%d", kampf.getAktuellerCharakter().getName(),
                    kampf.getAktuellerCharakter().getVerteidigung(), kampf.getAktuellerCharakter().getMagischeVerteidigung()));
        }
        apply(new Block(kampf.getAktuellerCharakter()));
        if (Game.debugModus) {
            kampfWerteLog.add(String.format(" -> V=%d, MV=%d\n", kampf.getAktuellerCharakter().getVerteidigung(),
                    kampf.getAktuellerCharakter().getMagischeVerteidigung()));
        }
        aktualisiereZugreihenfolge();
    }

    public boolean blockt(Charakter charakter) {
        for (Buff buff : aktiveBuffs.get(charakter)) {
            if (buff instanceof Block) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ausgewählte Fähigkeit wird vom aktuellen Charakter auf die gewählten Ziele
     * benutzt.
     *
     * @param ziele      Ziele der Fähigkeit -
     *                   ArrayList<Charakter>
     * @param faehigkeit Benutzte Fähigkeit - Fähigkeit
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    public void faehigkeitBenutzen(List<Charakter> ziele, Faehigkeit faehigkeit) {
        kampfView.setFaehigkeit(faehigkeit);
        kampfView.setZielGruppe(ziele);
        List<Charakter> zielWahl = new ArrayList<>(ziele);
        // Fähigkeit von Freund oder Feind kann ab hier eingesetzt werden und wird
        // entsprechend durchgeführt
        if (kampf.getAktuellerCharakter().getManaPunkte() >= faehigkeit.getManaKosten()) {
            kampf.getAktuellerCharakter().setManaPunkte(kampf.getAktuellerCharakter().getManaPunkte() - faehigkeit.getManaKosten());
        }
        else {
            zielWahl.clear();
            faehigkeit = kampf.getAktuellerCharakter().getFaehigkeiten().get(0);
            if (faehigkeit.isIstFreundlich()) {
                if (kampf.getAktuellerCharakter() instanceof Feind) {
                    zielWahl.add(kampf.getFeindeDieNochLeben().get(0));
                }
                else {
                    zielWahl.add(kampf.getFreundeDieNochLeben().get(0));
                }
            }
            else {
                if (kampf.getAktuellerCharakter() instanceof Feind) {
                    zielWahl.add(kampf.getFreundeDieNochLeben().get(0));
                }
                else {
                    zielWahl.add(kampf.getFeindeDieNochLeben().get(0));
                }
            }
        }

        if (trifft()) {
            int aktuellerCharakterMacht;
            int betroffenerCharakterAbwehr;
            double basisSchadensWert = 100.0;
            // Effekt einzeln auf jedes Ziel angewendet bis alle Ziele abgearbeitet wurden
            while (!zielWahl.isEmpty()) {
                Charakter betroffenerCharakter = zielWahl.get(0);
                String zielAttribut = faehigkeit.getZielAttribut();
                // Zuerst wird geguckt, ob es sich um eine physische oder magische Fähigkeit
                // handelt. Abhängig davon werden physische bzw. magische Angriffs und
                // Verteidigungswerte zur Berechnung verwendet.
                if (faehigkeit.getFaehigkeitsTyp().equals("physisch")) {
                    aktuellerCharakterMacht = kampf.getAktuellerCharakter().getPhysischeAttacke();
                    betroffenerCharakterAbwehr = betroffenerCharakter.getVerteidigung();
                }
                else {
                    aktuellerCharakterMacht = kampf.getAktuellerCharakter().getMagischeAttacke();
                    betroffenerCharakterAbwehr = betroffenerCharakter.getMagischeVerteidigung();
                }
                // Es wird geprüft, ob die genutzte Fähigkeit kritisch trifft (Heal und
                // Schaden!) Jeder Charakter hat eine Grundchance von 30% kritisch zu treffen.
                // Bei einem kritischen Treffer ist die Fähigkeit grundsätzlich 66% stärker.
                // Die Wahrscheinlichkeit kritisch zu treffen wird durch Beweglichkeit erhöht.
                // Zusätzlich kann eine Fähigkeit selbst eine erhöhte Wahrscheinlichkeit
                // haben kritisch zu treffen. Der Grundwert bei Fähigkeiten ist 1.0.
                // Jeder Attributpunkt des Charakters erhöht die Wahrscheinlichkeit um 0.2%.
                // Spätestens wenn man 350 Attributpunkte in Beweglichkeit hat (100%
                // Wahrscheinlichkeit kritisch zu treffen), erhöht jeder weitere
                // Punkt den kritischen Schaden-Multiplikator um 1%. Wie bereits im Abschnitt
                // 'Genauigkeit' erklärt, wird jeder Punkt in Genauigkeit über einem Wert
                // von 20 in die kritische Treffer Berechnung einbezogen.
                // Hierbei wird der Genauigkeitsbonus 1:1 so behandelt wie ein
                // Geschwindigkeit-Attributspunkt. Sollte also die Wahrscheinlichkeit kritisch
                // zu treffen bereits bei 100% liegen, tragen die Bonus-Genauigkeitspunkte
                // ebenfalls zur Erhöhung des kritischen Schaden-Multiplikators bei.
                boolean krit = false;
                double kritMultiplikator = 1.0;
                int genauigkeitsBonus = getGenauigkeitsBonus();
                double kritWahrscheinlichkeit = (0.3 + (faehigkeit.getWahrscheinlichkeit() - 1.0)
                        + 0.002 * (kampf.getAktuellerCharakter().getBeweglichkeit() + genauigkeitsBonus));
                if (RANDOM_NUMBER_GENERATOR.nextDouble() < kritWahrscheinlichkeit) {
                    if (kritWahrscheinlichkeit > 1.0) {
                        kritMultiplikator = 1.66 + ((kritWahrscheinlichkeit - 1) / 2);
                    }
                    else {
                        kritMultiplikator = 1.66;
                    }
                    krit = true;
                }

                int ergebnisWert = (int) Math.floor((faehigkeit.getEffektStaerke() / basisSchadensWert)
                        * aktuellerCharakterMacht * kritMultiplikator);

                // Der Schwierigkeitsgrad beeinflusst den Basiswert VOR Abzug der
                // Verteidigungen.
                // =================================================================//
                // Leicht -> Spielerwert ist um 20% erhöht.
                // -> Gegnerwert ist um 20% verringert.
                // Mittel -> Werte für alle unverändert.
                // Schwer -> Spielerwerte 20% verringert.
                // -> Gegnerwerte 20% erhöht.
                switch (gameController.getSchwierigkeitsgrad()) {
                    case "Leicht":
                        if (kampf.getAktuellerCharakter() instanceof SpielerCharakter) {
                            ergebnisWert *= 1.2;
                        }
                        if (kampf.getAktuellerCharakter() instanceof Feind) {
                            ergebnisWert *= 0.8;
                        }
                        break;
                    case "Schwer":
                        if (kampf.getAktuellerCharakter() instanceof SpielerCharakter) {
                            ergebnisWert *= 0.8;
                        }
                        if (kampf.getAktuellerCharakter() instanceof Feind) {
                            ergebnisWert *= 1.2;
                        }
                }

                // Es wird geguckt welches das ZielAttribut der Fähigkeit ist
                // Heal und Schaden gehen beide auf 'gesundheitsPunkte'
                switch (zielAttribut) {
                    case Attribute.GP:
                        kampfWerteLog.add(gesundheitsPunkte(faehigkeit, betroffenerCharakterAbwehr, betroffenerCharakter, ergebnisWert, krit));
                        break;
                    case Attribute.MAX_GP:
                        kampfWerteLog.add(maxGesundheitsPunkte(faehigkeit, betroffenerCharakter, ergebnisWert));
                        break;
                    case Attribute.MP:
                        kampfWerteLog.add(manaPunkte(faehigkeit, betroffenerCharakter, ergebnisWert));
                        break;
                    case Attribute.MAX_MP:
                        kampfWerteLog.add(maxManaPunkte(faehigkeit, betroffenerCharakter, ergebnisWert));
                        break;
                    case Attribute.A:
                        kampfWerteLog.add(physischeAttacke(faehigkeit, betroffenerCharakter, ergebnisWert));
                        break;
                    case Attribute.MA:
                        kampfWerteLog.add(magischeAttacke(faehigkeit, betroffenerCharakter, ergebnisWert));
                        break;
                    case Attribute.G:
                        kampfWerteLog.add(genauigkeit(faehigkeit, betroffenerCharakter, ergebnisWert));
                        break;
                    case Attribute.V:
                        kampfWerteLog.add(verteidigung(faehigkeit, betroffenerCharakter, ergebnisWert));
                        break;
                    case Attribute.MV:
                        kampfWerteLog.add(magischeVerteidigung(faehigkeit, betroffenerCharakter, ergebnisWert));
                        break;
                    case Attribute.R:
                        kampfWerteLog.add(resistenz(faehigkeit, betroffenerCharakter, ergebnisWert));
                        break;
                    case Attribute.B:
                        kampfWerteLog.add(beweglichkeit(faehigkeit, betroffenerCharakter, ergebnisWert));
                        break;
                    case Attribute.GR:
                        kampfWerteLog.add(gesundheitsRegeneration(faehigkeit, betroffenerCharakter, ergebnisWert));
                        break;
                    case Attribute.MR:
                        kampfWerteLog.add(magischeRegeneration(faehigkeit, betroffenerCharakter, ergebnisWert));
                        break;
                    case Attribute.ABW:
                        kampfWerteLog.add(abwehr(betroffenerCharakter, ergebnisWert));
                        break;
                    case SpezialFaehigkeiten.BERSERKER:
                        kampfWerteLog.add(((Berserker) kampf.getAktuellerCharakter().getKlasse()).spezialFaehigkeit(kampf.getAktuellerCharakter(), betroffenerCharakter, ergebnisWert, betroffenerCharakterAbwehr));
                        break;
                    case SpezialFaehigkeiten.FEUERMAGIER:
                        kampfWerteLog.add(((Feuermagier) kampf.getAktuellerCharakter().getKlasse()).spezialFaehigkeit(kampf.getAktuellerCharakter(), betroffenerCharakter, ergebnisWert, betroffenerCharakterAbwehr));
                        break;
                    case SpezialFaehigkeiten.EISMAGIER:
                        kampfWerteLog.add(((Eismagier) kampf.getAktuellerCharakter().getKlasse()).spezialFaehigkeit(kampf.getAktuellerCharakter(), betroffenerCharakter, aktuelleZugreihenfolge));
                        break;
                    case SpezialFaehigkeiten.PALADIN:
                        kampfWerteLog.add(((Paladin) kampf.getAktuellerCharakter().getKlasse()).spezialFaehigkeit(kampf.getAktuellerCharakter(), betroffenerCharakter));
                        break;
                    case SpezialFaehigkeiten.PRIESTER:
                        kampfWerteLog.add(((Priester) kampf.getAktuellerCharakter().getKlasse()).spezialFaehigkeit(kampf.getAktuellerCharakter(), betroffenerCharakter, kampf.getFreundeDieNochLeben(), kampf.getFeindeDieNochLeben()));
                        break;
                    case SpezialFaehigkeiten.RABAUKE:
                        kampfWerteLog.add(((Rabauke) kampf.getAktuellerCharakter().getKlasse()).spezialFaehigkeit(kampf.getAktuellerCharakter(), betroffenerCharakter));
                        break;
                    case SpezialFaehigkeiten.SANMAUS:
                        kampfWerteLog.add(((Sanmaus) kampf.getAktuellerCharakter().getKlasse()).spezialFaehigkeit(kampf.getAktuellerCharakter(), betroffenerCharakter));
                        break;
                    case SpezialFaehigkeiten.SCHURKE:
                        kampfWerteLog.add(((Schurke) kampf.getAktuellerCharakter().getKlasse()).spezialFaehigkeit(kampf.getAktuellerCharakter(), betroffenerCharakter, kampf.getFreundeDieNochLeben(), kampf.getFeindeDieNochLeben()));
                        break;
                }
                zielWahl.remove(0);
            }
        }
        // Die Fähigkeit hat nicht getroffen. Mana wurde trotzdem abgezogen und der Zug
        // des Charakters ist vorbei
        else {
            kampfWerteLog.add(faehigkeit.getName() + " ist daneben gegangen!\n");
            if (Game.debugModus) {
                kampfWerteLog.add("[DEBUG] Trefferchance liegt bei "
                        + (int) ((0.65 + 0.02 * kampf.getAktuellerCharakter().getGenauigkeit()) * 100) + "%\n");
            }
        }
        aktualisiereZugreihenfolge();
        aktualisiereIstKampfVorbei();
    }

    private int getGenauigkeitsBonus() {
        int genauigkeitsBonus = 0;
        if (kampf.getAktuellerCharakter().getGenauigkeit() > 20) {
            genauigkeitsBonus = kampf.getAktuellerCharakter().getGenauigkeit() - 20;
        }
        return genauigkeitsBonus;
    }

    /**
     * Jeder Charakter hat eine Grundchance von 60% zu treffen. Jeder Punkt in
     * Genauigkeit, bis zum Wert '20', erhöht die Trefferwahrscheinlichkeit um 2%.
     * Wenn das Genauigkeitsattribut den Wert '20' oder höher erreicht hat,
     * beträgt die Wahrscheinlichkeit zu treffen 100%. Jeder Attributpunkt
     * in Genauigkeit über 20 wird für die Berechnung der kritischen
     * Treffer-Wahrscheinlichkeit benutzt, wodurch eine 'Überskillung'
     * keine Verschwendung darstellt.
     */
    private boolean trifft() {
        return RANDOM_NUMBER_GENERATOR.nextDouble() < (0.65 + 0.02 * kampf.getAktuellerCharakter().getGenauigkeit());
    }

    /**
     * Jeder SpielerCharakter hat die Moeglichkeit fliehen() als Action
     * auszuwaehlen. Die Party hat eine Grundchance von 20% zu fliehen. Die
     * Differenz von der Gesamtbeweglichkeit der Party und der Gegenrgruppe
     * entscheidet darüber, ob die Fluchtchance sich erhöht oder nicht. Ist das
     * Fliehen erfolgreich, flieht die gesamte Gruppe und der Kampf ist beendet.
     *
     * @author Melvin
     * @since 18.11.2023
     */
    public void fliehen() {
        int nettoBeweglichkeit = 0;
        double fluchtchance = 0.2;
        for (SpielerCharakter spielerCharakter : kampf.getFreundeDieNochLeben()) {
            nettoBeweglichkeit += spielerCharakter.getBeweglichkeit();
        }
        for (Feind feind : kampf.getFeindeDieNochLeben()) {
            nettoBeweglichkeit -= feind.getBeweglichkeit();
        }

        // 20% + 0.1125% pro Beweglichkeitsvorteil der Gruppe (positive Differenz)
        if (nettoBeweglichkeit > 0) {
            fluchtchance += nettoBeweglichkeit / 800.0;
        }
        if (fluchtchance > 1.0) {
            fluchtchance = 1.0;
        }
        // DEBUG Fliehen 100%
//		fluchtchance = 2;
        if (RANDOM_NUMBER_GENERATOR.nextDouble() < fluchtchance) {
            kampf.setKampfVorbei(true);
        }
        else {
            // Flucht ist fehlgeschlagen, Aktion wird als ausgeführt betrachtet und
            aktualisiereZugreihenfolge();
        }
    }

    /**
     * Hier kann auf das Party-Verbrauchsgegenstandsinventar zugegriffen werden.
     * Methoden sind alles ausgelagert.
     *
     * @param benutzenAuf Zielcharakter für Verbrauchsgegenstand - SpielerCharakter
     * @param item        Verbrauchsgegenstand - Verbrauchsgegenstand
     * @author Melvin
     * @since 18.11.2023
     */

    public void gegenstand(Verbrauchsgegenstand item, SpielerCharakter benutzenAuf) {
        GegenstandController.verwendeVerbrauchsgegenstand(partyController.getParty().getVerbrauchsgegenstaende(),
                item, benutzenAuf);
        aktualisiereZugreihenfolge();
    }

    public void gegnerlogik() {
        feindLogik.logik();
    }

    /**
     * Gibt alle Fähigkeiten des aktuellen Charakters mit einem Level größer 0 zurück.
     */
    public List<Faehigkeit> getAktiveFaehigkeiten() {
        List<Faehigkeit> aktiveFaehigkeiten = new ArrayList<>();
        for (Faehigkeit faehigkeit : kampf.getAktuellerCharakter().getFaehigkeiten()) {
            if (faehigkeit.getLevel() > 0) {
                aktiveFaehigkeiten.add(faehigkeit);
            }
        }
        return aktiveFaehigkeiten;
    }

    public List<Feind> getFeinde() {
        return gegnerAnordnung;
    }

    public Charakter getLast() {
        return aktuelleZugreihenfolge.get(aktuelleZugreihenfolge.size() - 1);
    }

    public Charakter getNext() {
        return aktuelleZugreihenfolge.get(0);
    }

    public List<SpielerCharakter> getParty() {
        return partyAnordnung;
    }

    public Set<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> getVerbrauchsgegenstaende() {
        return party.getVerbrauchsgegenstaende().entrySet();
    }

    /**
     * Kampfende wird ausgewertet - Exp wird verteilt Gold und Ressourcen werden
     * verteilt Statistik wird gepflegt GameOver wird geprüft.
     * Endet in Hub oder GameOver
     *
     * @author Nick
     * @since 04.12.2023
     */
    public void kampfAuswerten() {
        kampfView.updateKampfBildschirm();
        resetKampfDaten();
        entferneBuffsUndDebuffs();
        Party party = partyController.getParty();
        List<SpielerCharakter> ueberlebende = new ArrayList<>();
        List<SpielerCharakter> kaputte = new ArrayList<>();
        if (party.getHauptCharakter().getGesundheitsPunkte() > 0) {
            ueberlebende.add(party.getHauptCharakter());
        }
        else {
            kaputte.add(party.getHauptCharakter());
        }
        SpielerCharakter[] nebenCharakter = party.getNebenCharaktere();
        for (SpielerCharakter charakter : nebenCharakter) {
            if (charakter != null && charakter.getGesundheitsPunkte() > 0) {
                ueberlebende.add(charakter);
            }
            else if (charakter != null) {
                kaputte.add(charakter);
            }
        }
        int ueberlebendeGegner = 0;
        for (Feind feind : feinde) {
            if (feind != null && feind.getGesundheitsPunkte() > 0) {
                ueberlebendeGegner++;
            }
        }
        if (ueberlebende.size() == 0) {
            niederlage(kaputte);
        }
        else if (ueberlebendeGegner > 0) {
            flucht(party);
        }
        else {
            sieg(party, ueberlebende);
        }
        kampfView.showErgebnis();
    }

    /**
     * Startpunkt für kaempfe
     *
     * @author Maass
     * @since 19.11.2023
     */
    public void kampfStarten() {
        if (Game.debugModus) {
            kampfWerteLog.add("[DEBUG] Party:\n");
            kampfWerteLog.add(partyController.getParty().getHauptCharakter() + "\n");
            for (SpielerCharakter nebenCharakter : partyController.getParty().getNebenCharaktere()) {
                if (nebenCharakter != null) {
                    kampfWerteLog.add(nebenCharakter + "\n");
                }
            }
        }
        this.feinde = feindController.gegnerGenerieren(partyController);
        if (Game.debugModus) {
            kampfWerteLog.add("[DEBUG] Feinde:\n");
            for (Feind feind : feinde) {
                kampfWerteLog.add(feind + "\n");
            }
        }
        List<Charakter> zugReihenfolge = new ArrayList<>();
        zugReihenfolge.add(partyController.getParty().getHauptCharakter());
        for (SpielerCharakter spielerCharakter : partyController.getParty().getNebenCharaktere()) {
            if (spielerCharakter != null && spielerCharakter.getGesundheitsPunkte() > 0) {
                zugReihenfolge.add(spielerCharakter);
            }
        }
        zugReihenfolge.addAll(Arrays.asList(feinde));
        zugReihenfolge.sort(Comparator.comparingInt(Charakter::getBeweglichkeit));
        if (Game.debugModus) {
            StringBuilder sb = new StringBuilder();
            ListIterator<Charakter> iter = zugReihenfolge.listIterator(zugReihenfolge.size());
            while (iter.hasPrevious()) {
                Charakter charakter = iter.previous();
                if (sb.length() != 0) {
                    sb.append(" -> ");
                }
                sb.append(charakter.getName());
                sb.append(" (");
                sb.append(charakter.getBeweglichkeit());
                sb.append(")");
            }
            kampfWerteLog.add("[DEBUG] Zugreihenfolge: " + sb + "\n");
        }
        for (Charakter charakter : zugReihenfolge) {
            aktiveBuffs.put(charakter, new ArrayList<>());
        }
        kampfBeginn(zugReihenfolge);
    }

    /**
     * Resettet alle für den Kampf wichtigen Daten nach Kampfabschluss für den
     * nächsten Kampf
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    public void resetKampfDaten() {
        aktuelleZugreihenfolge.clear();
        gegnerAnordnung.clear();
        partyAnordnung.clear();
    }

    public List<Charakter> zugreihenfolge() {
        return aktuelleZugreihenfolge;
    }

    public void zurueckZumHub() {
        viewController.aktuelleNachHinten();
    }

    private String abwehr(Charakter betroffenerCharakter, int ergebnisWert) {
        ergebnisWert -= betroffenerCharakter.getResistenz();
        int pDef = Math.min(Math.max(1, ergebnisWert), betroffenerCharakter.getVerteidigung());
        int mDef = Math.min(Math.max(1, ergebnisWert), betroffenerCharakter.getMagischeVerteidigung());
        apply(new Abwehr(betroffenerCharakter, pDef, mDef));
        return String.format("Abwehr von %s\nwurde verringert." + "\nVerteidigung -%d, Magische Verteidigung -%d\n", betroffenerCharakter.getName(), pDef, mDef);
    }

    /**
     * Aktualisiert die Zugreihenfolge nach einer ausgeführten Aktion und entfernt
     * gegenbenfalls blockende Charaktere
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    private void aktualisiereZugreihenfolge() {
        Charakter charakterDerAktionAusgefuehrtHat = aktuelleZugreihenfolge.get(0);
        aktuelleZugreihenfolge.remove(0);
        aktuelleZugreihenfolge.add(charakterDerAktionAusgefuehrtHat);
        for (Charakter charakter : new ArrayList<>(aktuelleZugreihenfolge)) {
            // Charakter ist gestorben
            if (charakter.getGesundheitsPunkte() < 1) {
                aktuelleZugreihenfolge.remove(charakter);
                stopBlocken(charakter);
                if (charakter instanceof Feind) {
                    Feind feind = (Feind) charakter;
                    kampf.getFeindeDieNochLeben().remove(feind);
                    kampf.getFeindeDieGestorbenSind().add(feind);
                }
                else {
                    SpielerCharakter spielerCharakter = (SpielerCharakter) charakter;
                    kampf.getFreundeDieNochLeben().remove(spielerCharakter);
                    kampf.getFreundeDieGestorbenSind().add(spielerCharakter);
                }
            }
        }
        naechsterSpieler();
    }

    private void apply(Buff buff) {
        aktiveBuffs.get(kampf.getAktuellerCharakter()).add(buff.apply());
    }

    private String beweglichkeit(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            apply(new Beweglichkeit(betroffenerCharakter, ergebnisWert));
            return String.format("Beweglichkeit von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int beweglichkeit = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getBeweglichkeit());
            apply(new Beweglichkeit(betroffenerCharakter, -beweglichkeit));
            return String.format("Beweglichkeit von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), beweglichkeit);
        }
    }

    /**
     * Resettet Statuswerte nach Kampf bevor es zurück ins GameGub geht
     *
     * @author OFR Rieger
     * @since 24.01.24
     */
    private void entferneBuffsUndDebuffs() {
        for (Map.Entry<Charakter, List<Buff>> entry : aktiveBuffs.entrySet()) {
            for (Buff buff : entry.getValue()) {
                buff.remove();
            }
        }
        aktiveBuffs.clear();
    }

    private void flucht(Party party) {
        if (gameController.isHardcore()) {
            // Tote Söldner werden im Hardcore-Modus gelöscht
            SpielerCharakter[] soeldner = party.getNebenCharaktere() != null ? party.getNebenCharaktere()
                    : new SpielerCharakter[0];
            for (int i = 0; i < soeldner.length; i++) {
                if (soeldner[i] != null) {
                    if (soeldner[i].getGesundheitsPunkte() == 0) {
                        kampfView.appendErgebnis(soeldner[i].getName() + " ist tot und hat die Party verlassen.\n");
                        soeldner[i] = null;
                    }
                }
            }
        }
        kampfView.appendErgebnis("Flucht erfolgreich.\nFeigling!");
    }

    private String genauigkeit(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            apply(new Genauigkeit(betroffenerCharakter, ergebnisWert));
            return String.format("Genauigkeit von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int genauigkeit = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getGenauigkeit());
            apply(new Genauigkeit(betroffenerCharakter, -genauigkeit));
            return String.format("Genauigkeit von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), genauigkeit);
        }
    }

    private String gesundheitsPunkte(Faehigkeit faehigkeit, int betroffenerCharakterAbwehr, Charakter betroffenerCharakter, int ergebnisWert, boolean krit) {
        if (faehigkeit.isIstFreundlich()) {
            // TODO soll es temporäre HP geben? Dann Buff
            int healWert;
            // gleiches Team -> Heal -> Verteidigung des Zieles spielt keine Rolle
            if (ergebnisWert + betroffenerCharakter.getGesundheitsPunkte() > betroffenerCharakter.getMaxGesundheitsPunkte()) {
                healWert = betroffenerCharakter.getMaxGesundheitsPunkte() - betroffenerCharakter.getGesundheitsPunkte();
            }
            else {
                healWert = ergebnisWert;
            }
            betroffenerCharakter.setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() + healWert);
            return String.format("%s%s wurde um %d geheilt!\n", krit ? "KRITISCHER TREFFER!\n" : "", betroffenerCharakter.getName(), healWert);
        }
        // feindliches Team -> Schaden -> Verteidigung des Zieles muss beachtet werden
        // Wenn die Verteidigung des Zieles zu gross ist wird kein Schaden verursacht
        else {
            ergebnisWert -= betroffenerCharakterAbwehr;
            int hp = Math.min(Math.max(1, ergebnisWert), betroffenerCharakter.getGesundheitsPunkte());
            betroffenerCharakter.setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() - hp);
            String istGestorbenString = "";
            if (betroffenerCharakter.getGesundheitsPunkte() <= 0) {
                istGestorbenString = (betroffenerCharakter.getName() + " ist gestorben.\n");
            }
            return String.format("%s%s hat %d Schaden erlitten!\n%s", krit ? "KRITISCHER TREFFER!\n" : "", betroffenerCharakter.getName(), hp, istGestorbenString);
        }
    }

    private String gesundheitsRegeneration(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            apply(new GesundheitsRegeneration(betroffenerCharakter, ergebnisWert));
            return String.format("Gesundheitsregeneration von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int gesReg = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getGesundheitsRegeneration());
            apply(new GesundheitsRegeneration(betroffenerCharakter, -gesReg));
            return String.format("Gesundheitsregeneration von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), gesReg);
        }
    }

    /**
     * Nach Initialisierung des Kampfes geht es hier los. Alle noch benötigten
     * ArrayLists und Werte werden befüllt, bevor an den KampfView-Controller
     * übergeben wird
     *
     * @author Melvin
     * @since 18.11.2023
     */
    private void kampfBeginn(List<Charakter> initialeZugreihenfolge) {
        kampf = new Kampf();
        //  kampf wird alles befüllt
        partyAnordnung.add(partyController.getParty().getHauptCharakter());
        for (SpielerCharakter nebencharakter : partyController.getParty().getNebenCharaktere()) {
            if (nebencharakter != null) {
                partyAnordnung.add(nebencharakter);
            }
        }
        if (partyController.getParty().getHauptCharakter().getGesundheitsPunkte() > 0) {
            kampf.getFreundeDieNochLeben().add(partyController.getParty().getHauptCharakter());
        }
        else {
            kampf.getFreundeDieGestorbenSind().add(partyController.getParty().getHauptCharakter());
        }
        for (SpielerCharakter nebenCharakter : partyController.getParty().getNebenCharaktere()) {
            if (nebenCharakter != null) {
                if (nebenCharakter.getGesundheitsPunkte() > 0) {
                    kampf.getFreundeDieNochLeben().add(nebenCharakter);
                }
                else {
                    kampf.getFreundeDieGestorbenSind().add(nebenCharakter);
                }
            }
        }
        for (Charakter value : initialeZugreihenfolge) {
            if (value instanceof Feind) {
                kampf.getFeindeDieNochLeben().add((Feind) value);
                gegnerAnordnung.add((Feind) value);
            }
        }
        kampf.getFreundeDieNochActionHaben().addAll(kampf.getFreundeDieNochLeben());
        kampf.getFeindeDieNochActionHaben().addAll(kampf.getFeindeDieNochLeben());
        aktuelleZugreihenfolge.addAll(initialeZugreihenfolge);
        Collections.reverse(aktuelleZugreihenfolge);

        naechsterSpieler();

        kampfView = new KampfView(kampf, this);
        feindLogik.setKampf(kampf);
        kampfView.updateKampfBildschirm();
        viewController.anmelden(this.kampfView, null, AnsichtsTyp.KEIN_CACHING);
    }

    private boolean kannRegenerieren() {
        return !kannRegenerieren.add(kampf.getAktuellerCharakter());
    }

    private String magischeAttacke(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            apply(new MagischeAttacke(betroffenerCharakter, ergebnisWert));
            return String.format("Magische Attacke von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int magAtk = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getMagischeAttacke());
            apply(new MagischeAttacke(betroffenerCharakter, -magAtk));
            return String.format("Magische Attacke von %s\nwurde um %d verringert\n", betroffenerCharakter.getName(), magAtk);
        }
    }

    private String magischeRegeneration(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            apply(new ManaRegeneration(betroffenerCharakter, ergebnisWert));
            return String.format("Manaregeneration von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int manaReg = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getManaRegeneration());
            apply(new ManaRegeneration(betroffenerCharakter, -manaReg));
            return String.format("Manaregeneration von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), manaReg);
        }
    }

    private String magischeVerteidigung(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            apply(new MagischeVerteidigung(betroffenerCharakter, ergebnisWert));
            return String.format("Magische Verteidigung von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int magVer = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getMagischeVerteidigung());
            apply(new MagischeVerteidigung(betroffenerCharakter, -magVer));
            return String.format("Magische Verteidigung von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), magVer);
        }
    }

    private String manaPunkte(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Heal -> Resistenz des Zieles spielt keine Rolle
        int mp;
        if (faehigkeit.isIstFreundlich()) {
            // TODO soll es temporäre Manapunkte geben? dann Buff
            if (betroffenerCharakter.getManaPunkte() + ergebnisWert > betroffenerCharakter.getMaxManaPunkte()) {
                mp = betroffenerCharakter.getMaxManaPunkte() - betroffenerCharakter.getManaPunkte();
            }
            else {
                mp = ergebnisWert;
            }
            betroffenerCharakter.setManaPunkte(betroffenerCharakter.getManaPunkte() + mp);
            // Wenn der Verbündete durch den Buff mehr MP hätte als durch seine maxMP
            // möglich, werden seine aktuellen MP gleich dem maxMP-Wert gesetzt
            if (betroffenerCharakter.getManaPunkte() > betroffenerCharakter.getMaxManaPunkte()) {
                betroffenerCharakter.setManaPunkte(betroffenerCharakter.getMaxManaPunkte());
            }
            return String.format("Mana von %s\nwurde um %d aufgefüllt\n", betroffenerCharakter.getName(), mp);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn die Resistenz des Zieles zu gross ist wird mindestwert angewendet
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            if (ergebnisWert < 1) {
                ergebnisWert = 1;
            }
            if (betroffenerCharakter.getManaPunkte() - ergebnisWert < 0) {
                mp = betroffenerCharakter.getManaPunkte();
            }
            else {
                mp = ergebnisWert;
            }
            betroffenerCharakter.setManaPunkte(betroffenerCharakter.getManaPunkte() - mp);
            return String.format("Mana von %s\nwurde um %d verringert\n", betroffenerCharakter.getName(), mp);
        }
    }

    private String maxGesundheitsPunkte(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            apply(new MaxGesundheitsPunkte(betroffenerCharakter, ergebnisWert));
            return String.format("Maximale Gesundheit von %s wurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int maxHP = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getMaxGesundheitsPunkte());
            apply(new MaxGesundheitsPunkte(betroffenerCharakter, -maxHP));
            // Wenn der Charakter dadurch stirbt und seine HP unter 0 fallen werden sie auf
            // 0 gesetzt
            String istGestorben = "";
            if (betroffenerCharakter.getGesundheitsPunkte() <= 0) {
                betroffenerCharakter.setGesundheitsPunkte(0);
                istGestorben = betroffenerCharakter.getName() + " ist gestorben.\n";
            }
            return String.format("Maximale Gesundheit von %s\nwurde um %d verringert.\n%s", betroffenerCharakter.getName(), maxHP, istGestorben);
        }
    }

    private String maxManaPunkte(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            apply(new MaxManaPunkte(betroffenerCharakter, ergebnisWert));
            return String.format("Maximales Mana von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int maxMP = Math.min(Math.max(0, ergebnisWert), kampf.getAktuellerCharakter().getMaxManaPunkte());
            apply(new MaxManaPunkte(betroffenerCharakter, -maxMP));
            return String.format("Maximales Mana von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), maxMP);
        }
    }

    private void naechsterSpieler() {
        kampf.setAktuellerCharakter(aktuelleZugreihenfolge.get(0));
        stopBlocken(kampf.getAktuellerCharakter());
        regeneriere(kampf.getAktuellerCharakter());
    }

    private void niederlage(List<SpielerCharakter> kaputte) {
        statistikController.durchgefuehrteKaempfeErhoehen();
        statistikController.verloreneKaempfeErhoehen();
        int kostenWiederbelebung = (int) (Math.floor(partyController.getPartyLevel() * 2.5));
        if (partyController.getPartyGold() >= kostenWiederbelebung && !gameController.isHardcore()) {
            // Genug gold im Nicht-Hardcore zum wiederbeleben
            partyController.goldAbziehen(kostenWiederbelebung);
            for (SpielerCharakter spielerCharakter : kaputte) {
                spielerCharakter.setGesundheitsPunkte(1);
            }
            kampfView.appendErgebnis("Die ohnmächtigen Charaktere wurden für " + kostenWiederbelebung + " Gold wiederbelebt.\n");
            kampfView.addNiederlage();
        }
        else {
            if (gameController.isHardcore()) {
                // Verloren und ist Hardcore
                speicherstandController.entferneSpeicherstandHardcore(partyController);
            }
            hauptmenuController.spielVorhandenProperty().set(false);
            GameOverView gameOverView = new GameOverView(statistikController.getStatistik(),
                    partyController, viewController);
            viewController.anmelden(gameOverView, null, AnsichtsTyp.OHNE_OVERLAY);
        }
    }

    private String physischeAttacke(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            apply(new PhysischeAttacke(betroffenerCharakter, ergebnisWert));
            return String.format("Physische Attacke von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int physAtk = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getPhysischeAttacke());
            apply(new PhysischeAttacke(betroffenerCharakter, -physAtk));
            return String.format("Physische Attacke von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), physAtk);
        }
    }

    private void regeneriere(Charakter charakter) {
        if (kannRegenerieren()) {
            if (Game.debugModus) {
                kampfWerteLog.add(String.format("[DEBUG] Regeneration von %s: HP=%d, MP=%d", kampf.getAktuellerCharakter().getName(), kampf.getAktuellerCharakter().getGesundheitsPunkte(), kampf.getAktuellerCharakter().getManaPunkte()));
            }
            charakter.setGesundheitsPunkte(charakter.getGesundheitsPunkte() + (int) Math.round(charakter.getGesundheitsRegeneration() / 8.0));
            charakter.setManaPunkte(charakter.getManaPunkte() + (int) Math.round(charakter.getManaRegeneration() / 8.0));
            if (charakter.getGesundheitsPunkte() > charakter.getMaxGesundheitsPunkte()) {
                charakter.setGesundheitsPunkte(charakter.getMaxGesundheitsPunkte());
            }
            if (charakter.getManaPunkte() > charakter.getMaxManaPunkte()) {
                charakter.setManaPunkte(charakter.getMaxManaPunkte());
            }
            if (Game.debugModus) {
                kampfWerteLog.add(String.format(" -> HP=%d, MP=%d\n", kampf.getAktuellerCharakter().getGesundheitsPunkte(), kampf.getAktuellerCharakter().getManaPunkte()));
            }
        }
    }

    private String resistenz(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            apply(new Resistenz(betroffenerCharakter, ergebnisWert));
            return String.format("Resistenz von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int resistenz = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getResistenz());
            apply(new Resistenz(betroffenerCharakter, -resistenz));
            return String.format("Resistenz von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), resistenz);
        }
    }

    private void sieg(Party party, List<SpielerCharakter> ueberlebende) {
        int gewonnenesGold = ((int) Math.floor(partyController.getPartyLevel()) * 10);
        partyController.goldHinzufuegen(gewonnenesGold);
        for (SpielerCharakter spielerCharakter : ueberlebende) {
            CharakterController.erfahrungHinzufuegen(spielerCharakter, 10);
            kampfView.appendErgebnis(spielerCharakter.getName() + " hat 10 Erfahrungspunkte erhalten!\n");
        }
        statistikController.goldErhoehen(gewonnenesGold);
        statistikController.durchgefuehrteKaempfeErhoehen();
        statistikController.gewonneneKaempfeErhoehen();
        if (gameController.isHardcore()) {
            // Tote Söldner werden im Hardcore-Modus gelöscht
            SpielerCharakter[] soeldner = party.getNebenCharaktere() != null ? party.getNebenCharaktere()
                    : new SpielerCharakter[0];
            for (int i = 0; i < soeldner.length; i++) {
                if (soeldner[i] != null) {
                    if (soeldner[i].getGesundheitsPunkte() == 0) {
                        kampfView.appendErgebnis(soeldner[i].getName() + " ist tot und hat die Party verlassen.\n");
                        soeldner[i] = null;
                    }
                }
            }
        }
        // 10%-ige Chance Ausrüstung zu looten
        if (ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(10) <= 1) {
            int ausruestungsArt = ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(3);
            int feindIndex = ZufallsZahlenGenerator.zufallsZahlAb0(feinde.length);
            // Waffe
            if (ausruestungsArt == 1) {
                partyController.ausruestungsgegenstandHinzufuegen(feinde[feindIndex].getWaffe());
                kampfView.appendErgebnis(feinde[feindIndex].getWaffe().getName() + " erhalten!\n");
            }
            // Rüstung
            else if (ausruestungsArt == 2) {
                partyController.ausruestungsgegenstandHinzufuegen(feinde[feindIndex].getRuestung());
                kampfView.appendErgebnis(feinde[feindIndex].getRuestung().getName() + " erhalten!\n");
            }
            // Accessoire
            else if (ausruestungsArt == 3) {
                int accessoireIndex = ZufallsZahlenGenerator.zufallsZahlAb0(feinde[feindIndex].getAccessoires().length);
                if (feinde[feindIndex].getAccessoires()[accessoireIndex] != null) {
                    partyController.ausruestungsgegenstandHinzufuegen(feinde[feindIndex].getAccessoires()[accessoireIndex]);
                    kampfView.appendErgebnis(feinde[feindIndex].getAccessoires()[accessoireIndex].getName() + " erhalten!\n");
                }
            }
        }
        Material material = Material.zufaelligeMaterialArt();
        int anzahlMaterial = (int) Math.floor(partyController.getPartyLevel());
        partyController.materialHinzufuegen(material, anzahlMaterial);
        kampfView.appendErgebnis(anzahlMaterial + "x " + material.getClass().getSimpleName() + " erhalten.\n" +
                gewonnenesGold + " Gold erhalten.\n");
        kampfView.addSieg();
    }

    private void stopBlocken(Charakter charakter) {
        if (blockt(charakter)) {
            if (Game.debugModus) {
                kampfWerteLog.add(String.format("[DEBUG] Debuff von %s: V=%d, MV=%d", kampf.getAktuellerCharakter().getName(), kampf.getAktuellerCharakter().getVerteidigung(), kampf.getAktuellerCharakter().getMagischeVerteidigung()));
            }
            for (Buff buff : new ArrayList<>(aktiveBuffs.get(charakter))) {
                if (buff instanceof Block) {
                    aktiveBuffs.get(charakter).remove(buff.remove());
                }
            }
            if (Game.debugModus) {
                kampfWerteLog.add(String.format(" -> V=%d, MV=%d\n", kampf.getAktuellerCharakter().getVerteidigung(), kampf.getAktuellerCharakter().getMagischeVerteidigung()));
            }
        }
    }

    private String verteidigung(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            apply(new Verteidigung(betroffenerCharakter, ergebnisWert));
            return String.format("Verteidigung von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int verteidigung = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getVerteidigung());
            apply(new Verteidigung(betroffenerCharakter, -verteidigung));
            return String.format("Verteidigung von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), verteidigung);
        }
    }

}
