package de.bundeswehr.auf.final_fantasy.menu.kampf;

import de.bundeswehr.auf.final_fantasy.Game;
import de.bundeswehr.auf.final_fantasy.charakter.controller.CharakterController;
import de.bundeswehr.auf.final_fantasy.charakter.controller.FeindController;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.TNK;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen.*;
import de.bundeswehr.auf.final_fantasy.gegenstaende.controller.GegenstandController;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Attribute;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;
import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.HauptmenuController;
import de.bundeswehr.auf.final_fantasy.menu.overlay.AnsichtsTyp;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.menu.speicherstand.SpeicherstandController;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model.Faehigkeit;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model.SpezialFaehigkeiten;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.party.model.Party;
import de.bundeswehr.auf.final_fantasy.statistik.GameOverView;
import de.bundeswehr.auf.final_fantasy.statistik.StatistikController;

import java.util.*;

public class KampfController {

    final List<Charakter> aktuelleZugreihenfolge = new ArrayList<>();
    Charakter aktuellerCharakter;
    final List<Charakter> blockendeCharaktere = new ArrayList<>();
    final List<Feind> gegnerAnordnung = new ArrayList<>();
    final boolean[] istKampfVorbei = { false };
    final List<String> kampfWerteLog = new ArrayList<>();
    final Party party;
    final List<SpielerCharakter> partyAnordnung = new ArrayList<>();

    private final FeindController feindController;
    private Feind[] feinde;
    private final List<Feind> feindeDieGestorbenSind = new ArrayList<>();
    private final List<Feind> feindeDieNochActionHaben = new ArrayList<>();
    private final List<Feind> feindeDieNochLeben = new ArrayList<>();
    private final List<SpielerCharakter> freundeDieGestorbenSind = new ArrayList<>();
    private final List<SpielerCharakter> freundeDieNochActionHaben = new ArrayList<>();
    private final List<SpielerCharakter> freundeDieNochLeben = new ArrayList<>();
    private final Game gameController;
    private Faehigkeit gegnerFaehigkeit;
    private final SpielerCharakter hauptCharakterVorKampfbeginn;
    private final HauptmenuController hauptmenuController;
    private KampfView kampfView;
    private final List<SpielerCharakter> nebenCharaktereVorKampfbeginn = new ArrayList<>();
    private final PartyController partyController;
    private final Random random = new Random();
    private final List<Charakter> selbstBuffCharaktere = new ArrayList<>();
    private final SpeicherstandController speicherstandController;
    private final StatistikController statistikController;
    private final ViewController viewController;
    private final List<Charakter> zielGruppe = new ArrayList<>();

    public KampfController(FeindController feindController, PartyController partyController,
                           StatistikController statistikController, Game gameController,
                           HauptmenuController hauptmenuController, SpeicherstandController speicherstandController,
                           ViewController viewController) {
        this.feindController = feindController;
        this.partyController = partyController;
        this.statistikController = statistikController;
        this.gameController = gameController;
        this.hauptmenuController = hauptmenuController;

        hauptCharakterVorKampfbeginn = partyController.getParty().getHauptCharakter().clone();
        for (SpielerCharakter nebenCharakter : partyController.getParty().getNebenCharakter()) {
            if (nebenCharakter != null) {
                nebenCharaktereVorKampfbeginn.add(nebenCharakter.clone());
            }
        }
        this.party = partyController.getParty();
        this.speicherstandController = speicherstandController;
        this.viewController = viewController;
    }

    public static List<Faehigkeit> getAktiveFaehigkeiten(Charakter charakter) {
        List<Faehigkeit> moeglicheFaehigkeiten = new ArrayList<>();
        for (Faehigkeit faehigkeit : charakter.getFaehigkeiten()) {
            if (faehigkeit.getLevel() > 0) {
                moeglicheFaehigkeiten.add(faehigkeit);
            }
        }
        return moeglicheFaehigkeiten;
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
        istKampfVorbei[0] = feindeCounter == 0 || partyCounter == 0;
    }

    /**
     * Aktualisiert die Zugreihenfolge nach einer ausgeführten Aktion und entfernt
     * gegenbenfalls blockende Charaktere
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    public void aktualisiereZugreihenfolge() {
        Charakter charakterDerAktionAusgefuehrtHat = aktuelleZugreihenfolge.get(0);
        aktuelleZugreihenfolge.remove(0);
        aktuelleZugreihenfolge.add(charakterDerAktionAusgefuehrtHat);
        for (Charakter charakter : new ArrayList<>(aktuelleZugreihenfolge)) {
            if (charakter.getGesundheitsPunkte() < 1) {
                aktuelleZugreihenfolge.remove(charakter);
                if (charakter instanceof Feind) {
                    Feind feind = (Feind) charakter;
                    feindeDieNochLeben.remove(feind);
                    feindeDieGestorbenSind.add(feind);
                }
                else {
                    SpielerCharakter spielerCharakter = (SpielerCharakter) charakter;
                    freundeDieNochLeben.remove(spielerCharakter);
                    freundeDieGestorbenSind.add(spielerCharakter);
                }
            }
        }
        aktuellerCharakter = aktuelleZugreihenfolge.get(0);
        blockendeCharaktere.remove(aktuellerCharakter);
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
        aktuellerCharakter
                .setVerteidigung(aktuellerCharakter.getVerteidigung() + aktuellerCharakter.getPhysischeAttacke());
        aktuellerCharakter.setMagischeVerteidigung(
                aktuellerCharakter.getMagischeVerteidigung() + aktuellerCharakter.getMagischeAttacke());
        blockendeCharaktere.add(aktuellerCharakter);
        aktualisiereZugreihenfolge();
    }

    /**
     * Resettet Statuswerte nach Kampf bevor es zurück ins GameGub geht
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    public void buffsUndDebuffsEntferne() {
        // Vor Kampfauswertung muessen alle Statuswerte (ausser aktuelle HP) wieder auf
        // ihren Wert von vor Kampfbeginn gesetzt werden.
        boolean hautpcharakterLebtNoch = false;

        // Hauptcharakter Statuswerte werden zurueckgesetzt (inklusive Leben)
        partyController.getParty().setHauptCharakter(hauptCharakterVorKampfbeginn);

        // Nebencharakter Statuspunkte werden zurueckgesetzt (inklusive Leben)
        SpielerCharakter[] partyUeberschreibung = new SpielerCharakter[3];
        int counter = 0;
        for (SpielerCharakter spielerCharakter :
                nebenCharaktereVorKampfbeginn) {
            partyUeberschreibung[counter] = spielerCharakter;
            counter++;
        }

        // Es gibt SpielerCharaktere die noch Leben
        if (!freundeDieNochLeben.isEmpty()) {
            for (SpielerCharakter spielerCharakter : new ArrayList<>(freundeDieNochLeben)) {
                // Wenn die Geschichte des Charakters zeigt, dass er der Hauptcharakter ist
                // werden die HP des Hauptcharakters auf die von diesem Spielercharakter
                // gesetzt
                if (spielerCharakter.getGeschichte()
                        .equals(partyController.getParty().getHauptCharakter().getGeschichte())) {
                    partyController.getParty().getHauptCharakter()
                            .setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte());
                    partyController.getParty().getHauptCharakter().setManaPunkte(spielerCharakter.getManaPunkte());
                    freundeDieNochLeben.remove(spielerCharakter);
                    // Das bedeutet, dass der hauptcharakter noch lebt
                    hautpcharakterLebtNoch = true;
                }
            }

            // Wenn der Hauptcharakter nicht mehr lebt werden seine HP auf 0 gesetzt
            if (!hautpcharakterLebtNoch) {
                partyController.getParty().getHauptCharakter().setGesundheitsPunkte(0);
                partyController.getParty().getHauptCharakter().setManaPunkte(0);
            }

            counter = 0;
            SpielerCharakter[] nebencharaktere = new SpielerCharakter[3];

            for (SpielerCharakter spielerCharakter : nebenCharaktereVorKampfbeginn) {
                for (SpielerCharakter nebenCharaktereDieUeberlebtHaben : freundeDieNochLeben) {
                    if (spielerCharakter.getName().equals(nebenCharaktereDieUeberlebtHaben.getName())) {
                        if (nebencharaktere[0] == null) {
                            nebencharaktere[0] = spielerCharakter;
                            spielerCharakter
                                    .setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
                            spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
                        }
                        else if (nebencharaktere[1] == null) {
                            nebencharaktere[1] = spielerCharakter;
                            spielerCharakter
                                    .setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
                            spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
                        }
                        else if (nebencharaktere[2] == null) {
                            nebencharaktere[2] = spielerCharakter;
                            spielerCharakter
                                    .setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
                            spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
                        }
                    }
                }
            }
            for (SpielerCharakter spielerCharakter : nebenCharaktereVorKampfbeginn) {
                for (SpielerCharakter nebenCharaktereDieUeberlebtHaben : freundeDieGestorbenSind) {

                    if (nebencharaktere[0] == null) {
                        nebencharaktere[0] = spielerCharakter;
                        spielerCharakter.setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
                        spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
                    }
                    else if (nebencharaktere[1] == null) {
                        nebencharaktere[1] = spielerCharakter;
                        spielerCharakter.setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
                        spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
                    }
                    else if (nebencharaktere[2] == null) {
                        nebencharaktere[2] = spielerCharakter;
                        spielerCharakter.setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
                        spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
                    }
                }
            }

            partyController.getParty().setNebenCharakter(nebencharaktere);

        }

        // Alles Partymitglieder sind tot und der Kampf wurde verloren. Alle am Anfang
        // des Kampfes erstellten Koepien koennen mit einem HP Wert von 0 an die Party
        // zurueckgegeben werden
        else {
            SpielerCharakter hauptCharakterVerloren = hauptCharakterVorKampfbeginn.clone();
            SpielerCharakter[] nebenCharaktereVerloren = new SpielerCharakter[3];
            hauptCharakterVerloren.setGesundheitsPunkte(0);
            counter = 0;
            for (SpielerCharakter nebenCharakter : nebenCharaktereVorKampfbeginn) {
                nebenCharaktereVerloren[counter] = nebenCharakter;
                nebenCharaktereVerloren[counter].setGesundheitsPunkte(0);
                counter++;
            }
            partyController.getParty().setHauptCharakter(hauptCharakterVerloren);
            partyController.getParty().setNebenCharakter(nebenCharaktereVerloren);
        }

        // Aktualisierter Nebencharakter-Array wird der Party uebergeben
        // partyController.getParty().setNebenCharakter(partyUeberschreibung);
        kampfAuswerten();
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
        for (SpielerCharakter spielerCharakter : freundeDieNochLeben) {
            nettoBeweglichkeit += spielerCharakter.getBeweglichkeit();
        }
        for (Feind feind : feindeDieNochLeben) {
            nettoBeweglichkeit -= feind.getBeweglichkeit();
        }

        // 20% + 0.1125% pro Beweglichkeitsvorteil der Gruppe (positive Differenz)
        if (nettoBeweglichkeit > 0) {
            fluchtchance += (nettoBeweglichkeit / 800.0);
        }
        if (fluchtchance > 1.0) {
            fluchtchance = 1.0;
        }
//		fluchtchance = 0;
        if (random.nextDouble() < fluchtchance) {
            istKampfVorbei[0] = true;
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

    /**
     * Abhängig von der Klasse des Gegners wird hier seine Angriffs/Heal- Logik
     * bestimmt und entweder geblockt oder eine Fähigkeit genutzt
     *
     * @param gegner Gegner für den die Logik bestimmt werden soll - Feind
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    public void gegnerlogik(Feind gegner) {
        switch (gegner.getKlasse().getBezeichnung()) {
            case Klasse.TNK:
                // 65% Wahrscheinlichkeit, dass der Tank angreift (Selbstheilung oder Schaden am
                // Spieler-Team)
                if (random.nextDouble() < 0.65) {
                    gegnerLogikFaehigkeitUndZielGruppe();
                    kampfView.setZielGruppe(zielGruppe);
                    kampfView.setFaehigkeit(gegnerFaehigkeit);
                    faehigkeitBenutzen(gegner, zielGruppe, gegnerFaehigkeit);
                }
                // 35% Chance, dass der Tank blockt
                else {
                    blocken();
                }
                break;
            // Alle anderen Klassen haben die gleichen Wahrscheinlichkeiten zu blocken (10%)
            // oder eine Fähigkeit zu benutzen (90%)
            case Klasse.HLR:
            case Klasse.MDD:
            case Klasse.PDD:
                if (random.nextDouble() < 0.9) {
                    gegnerLogikFaehigkeitUndZielGruppe();
                    kampfView.setZielGruppe(zielGruppe);
                    kampfView.setFaehigkeit(gegnerFaehigkeit);
                    faehigkeitBenutzen(gegner, zielGruppe, gegnerFaehigkeit);
                }
                else {
                    blocken();
                }
                break;
            default:
                throw new RuntimeException("Ungültige Klasse: " + gegner.getKlasse().getBezeichnung());
        }
    }

    /**
     * Kampfende wird ausgewertet - Exp wird verteilt Gold und Ressourcen werden
     * verteilt Statistik wird gepflegt GameOver wird geprueft Endet in Hub oder
     * GameOver
     *
     * @author Nick
     * @since 04.12.2023
     */
    public void kampfAuswerten() {
        kampfView.updateKampfBildschirm();
        resetKampfDaten();
        Party party = partyController.getParty();
        List<SpielerCharakter> ueberlebende = new ArrayList<>();
        List<SpielerCharakter> kaputte = new ArrayList<>();
        if (party.getHauptCharakter().getGesundheitsPunkte() > 0) {
            ueberlebende.add(party.getHauptCharakter());
        }
        else {
            kaputte.add(party.getHauptCharakter());
        }
        SpielerCharakter[] nebenCharakter = party.getNebenCharakter();
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
        if (ueberlebende.size() > 0 && ueberlebendeGegner <= 0) {
            // Sieg
            int gewonnenesGold = ((int) Math.floor(partyController.getPartyLevel()) * 10);
            partyController.goldHinzufuegen(gewonnenesGold);
            for (SpielerCharakter spielerCharakter : ueberlebende) {
                CharakterController.erfahrungHinzufuegen(spielerCharakter, 10);
                kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
                        .concat(spielerCharakter.getName() + " hat 10 Erfahrungspunkte erhalten!\n"));
            }
            statistikController.goldErhoehen(gewonnenesGold);
            statistikController.durchgefuehrteKaempfeErhoehen();
            statistikController.gewonneneKaempfeErhoehen();
            if (gameController.isHardcore()) {
                // Tote Söldner werden im Hardcore-Modus gelöscht
                SpielerCharakter[] soeldner = party.getNebenCharakter() != null ? party.getNebenCharakter()
                        : new SpielerCharakter[0];
                for (int i = 0; i < soeldner.length; i++) {
                    if (soeldner[i] != null) {
                        if (soeldner[i].getGesundheitsPunkte() == 0) {
                            kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
                                    .concat(soeldner[i].getName() + " ist tot und hat die Party verlassen.\n"));
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
                    kampfView.kampfErgebnis.setText(
                            kampfView.kampfErgebnis.getText().concat(feinde[feindIndex].getWaffe().getName() + " erhalten!\n"));
                }
                // Rüstung
                else if (ausruestungsArt == 2) {
                    partyController.ausruestungsgegenstandHinzufuegen(feinde[feindIndex].getRuestung());
                    kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
                            .concat(feinde[feindIndex].getRuestung().getName() + " erhalten!\n"));
                }
                // Accessoire
                else if (ausruestungsArt == 3) {
                    int accessoireIndex = ZufallsZahlenGenerator.zufallsZahlAb0(feinde[feindIndex].getAccessoires().length);
                    if (feinde[feindIndex].getAccessoires()[accessoireIndex] != null) {
                        partyController.ausruestungsgegenstandHinzufuegen(feinde[feindIndex].getAccessoires()[accessoireIndex]);
                        kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
                                .concat(feinde[feindIndex].getAccessoires()[accessoireIndex].getName() + " erhalten!\n"));
                    }
                }
            }
            Material material = Material.zufaelligeMaterialArt();
            int anzahlMaterial = (int) Math.floor(partyController.getPartyLevel());
            partyController.materialHinzufuegen(material, anzahlMaterial);
            kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
                    .concat(anzahlMaterial + "x " + material.getClass().getSimpleName() + " erhalten.\n" +
                            gewonnenesGold + " Gold erhalten.\n"));
            kampfView.kampfErgebnisContainer.getChildren().add(0, kampfView.sieg);
        }
        if (ueberlebende.size() == 0) {
            // Niederlage
            statistikController.durchgefuehrteKaempfeErhoehen();
            statistikController.verloreneKaempfeErhoehen();
            int kostenWiederbelebung = (int) (Math.floor(partyController.getPartyLevel() * 2.5));
            if (partyController.getPartyGold() >= kostenWiederbelebung && !gameController.isHardcore()) {
                // Genug gold im Nicht-Hardcore zum wiederbeleben
                partyController.goldAbziehen(kostenWiederbelebung);
                for (SpielerCharakter spielerCharakter : kaputte) {
                    spielerCharakter.setGesundheitsPunkte(1);
                }
                kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
                        .concat("Die ohnmächtigen Charaktere wurden für " + kostenWiederbelebung + " Gold wiederbelebt.\n"));
                kampfView.kampfErgebnisContainer.getChildren().add(0, kampfView.niederlage);
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
        if (ueberlebende.size() > 0 && ueberlebendeGegner > 0) {
            // Flucht
            if (gameController.isHardcore()) {
                // Tote Söldner werden im Hardcore-Modus gelöscht
                SpielerCharakter[] soeldner = party.getNebenCharakter() != null ? party.getNebenCharakter()
                        : new SpielerCharakter[0];
                for (int i = 0; i < soeldner.length; i++) {
                    if (soeldner[i] != null) {
                        if (soeldner[i].getGesundheitsPunkte() == 0) {
                            kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
                                    .concat(soeldner[i].getName() + " ist tot und hat die Party verlassen.\n"));
                            soeldner[i] = null;
                        }
                    }
                }
            }
            kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText().concat("Flucht erfolgreich.\nFeigling!"));
        }
        kampfView.aktionAusgefuehrtInfoAnzeige.toBack();
        kampfView.kampfErgebnisContainer.toFront();
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
            for (SpielerCharakter nebenCharakter : partyController.getParty().getNebenCharakter()) {
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
        for (SpielerCharakter spielerCharakter : partyController.getParty().getNebenCharakter()) {
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

        kampfBeginn(zugReihenfolge);
    }

    // Runde vorbei. Alle noch lebenden SpielerCharaktere und Feinde regenerieren HP
    // und MP
    public void regenerationNachRunde() {
        if (!istKampfVorbei[0]) {
            for (SpielerCharakter freund : freundeDieNochLeben) {
                freund.setGesundheitsPunkte(
                        freund.getGesundheitsPunkte() + (int) Math.round(freund.getGesundheitsRegeneration() / 8.0));
                freund.setManaPunkte(freund.getManaPunkte() + (int) Math.round(freund.getManaRegeneration() / 8.0));
                if (freund.getGesundheitsPunkte() > freund.getMaxGesundheitsPunkte()) {
                    freund.setGesundheitsPunkte(freund.getMaxGesundheitsPunkte());
                }
                if (freund.getManaPunkte() > freund.getMaxManaPunkte()) {
                    freund.setManaPunkte(freund.getMaxManaPunkte());
                }
            }
            for (Feind feind : feindeDieNochLeben) {
                feind.setGesundheitsPunkte(
                        feind.getGesundheitsPunkte() + (int) Math.round(feind.getGesundheitsRegeneration() / 8.0));
                feind.setManaPunkte(feind.getManaPunkte() + (int) Math.round(feind.getManaRegeneration() / 8.0));
                if (feind.getGesundheitsPunkte() > feind.getMaxGesundheitsPunkte()) {
                    feind.setGesundheitsPunkte(feind.getMaxGesundheitsPunkte());
                }
                if (feind.getManaPunkte() > feind.getMaxManaPunkte()) {
                    feind.setManaPunkte(feind.getMaxManaPunkte());
                }
            }
        }
        for (SpielerCharakter spielerCharakter : freundeDieNochLeben) {
            if (spielerCharakter.getGesundheitsPunkte() > 0) {
                freundeDieNochActionHaben.add(spielerCharakter);
            }
            else {
                freundeDieNochActionHaben.remove(spielerCharakter);
            }
        }
        for (Feind feind : feindeDieNochLeben) {
            if (feind.getGesundheitsPunkte() > 0) {
                feindeDieNochActionHaben.add(feind);
            }
            else {
                feindeDieNochActionHaben.remove(feind);
            }
        }
        if (!istKampfVorbei[0]) {
            // TODO ?
        }
        if (feindeDieNochLeben.isEmpty() || freundeDieNochLeben.isEmpty()) {
            istKampfVorbei[0] = true;
            if (feindeDieNochLeben.isEmpty()) {
                freundeDieNochActionHaben.clear();
            }
            else {
                feindeDieNochActionHaben.clear();
            }
        }
    }

    /**
     * Resettet alle für den Kampf wichtigen Daten nach Kampfabschluss für den
     * nächsten Kampf
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    public void resetKampfDaten() {
        for (Charakter charakter : blockendeCharaktere) {
            if (charakter instanceof SpielerCharakter) {
                charakter.setVerteidigung(charakter.getVerteidigung() - charakter.getPhysischeAttacke());
                charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() - charakter.getMagischeAttacke());
            }
        }
        freundeDieGestorbenSind.clear();
        freundeDieNochLeben.clear();
        freundeDieNochActionHaben.clear();
        feindeDieNochLeben.clear();
        feindeDieNochActionHaben.clear();
        feindeDieGestorbenSind.clear();
        aktuelleZugreihenfolge.clear();
        gegnerAnordnung.clear();
        partyAnordnung.clear();
        istKampfVorbei[0] = false;
        blockendeCharaktere.clear();
        selbstBuffCharaktere.clear();
        nebenCharaktereVorKampfbeginn.clear();
        zielGruppe.clear();
    }

    public void zurueckZumHub() {
        viewController.aktuelleNachHinten();
    }

    /**
     * Ausgewähle Fähigkeit wird vom aktuellen Charakter auf die gewählten Ziele
     * benutzt
     *
     * @param charakterDerFaehigkeitBenutzt Charakter der die Fähigkeit benutzt -
     *                                      Charakter
     * @param ziele                         Ziele der Fähigkeit -
     *                                      ArrayList<Charakter>
     * @param faehigkeit                    Benutzte Fähigkeit - Fähigkeit
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    void faehigkeitBenutzen(Charakter charakterDerFaehigkeitBenutzt, List<Charakter> ziele,
                            Faehigkeit faehigkeit) {

        boolean hatCharakterGenugMana = true;
        List<Charakter> zielWahl = new ArrayList<>(ziele);
        kampfWerteLog.clear();

        // Faehigkeit von Freund oder Feind kann ab hier eingesetzt werden und wird
        // entsprechend durchgefuehrt
        try {
            aktuellerCharakter.setManaPunkte(aktuellerCharakter.getManaPunkte() - faehigkeit.getManaKosten());
        } catch (Exception e) {
            zielWahl.clear();
            faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
            if (faehigkeit.isIstFreundlich()) {
                if (aktuellerCharakter instanceof Feind) {
                    zielWahl.add(feindeDieNochLeben.get(0));
                }
                else {
                    zielWahl.add(freundeDieNochLeben.get(0));
                }
            }
            else {
                if (aktuellerCharakter instanceof Feind) {
                    zielWahl.add(freundeDieNochLeben.get(0));
                }
                else {
                    zielWahl.add(feindeDieNochLeben.get(0));
                }
            }
        }

        // Jeder Charakter hat eine Grundchance von 60% zu treffen. Jeder Punkt in
        // Genauigkeit, bis zum Wert '20', erhoeht die Trefferwahrscheinlichkeit um 2%.
        // Wenn das Genauigkeitsattribut den Wert '20' oder hoeher erreicht hat,
        // betraegt die Wahrscheinlichkeit zu treffen 100%. Jeder Attributpunkt
        // in Genauigkeit ueber 20 wird fuer die Berechnung der
        // kritischerTreffer-Wahrscheinlichkeit benutzt, wodurch eine 'Ueberskillung'
        // keine Verschwendung darstellt.
        double treffer = random.nextDouble();
        if (treffer < (0.65 + 0.02 * aktuellerCharakter.getGenauigkeit())) {
            int aktuellerCharakterMacht;
            int betroffenerCharakterAbwehr;
            double basisSchadensWert = 100.0;
            // Effekt einzeln auf jedes Ziel angewendet bis alle Ziele abgearbeitet wurden
            while (!zielWahl.isEmpty()) {
                Charakter betroffenerCharakter = zielWahl.get(0);
                String zielAttribut = faehigkeit.getZielAttribut();
                // Zuerst wird geguckt, ob es sich um eine physische oder magische Faehigkeit
                // handelt Abhaengig davon werden physische bzw. magische Angriffs und
                // Verteidigungswerte zur Berechnung verwendet
                if (faehigkeit.getFaehigkeitsTyp().equals("physisch")) {
                    aktuellerCharakterMacht = aktuellerCharakter.getPhysischeAttacke();
                    betroffenerCharakterAbwehr = betroffenerCharakter.getVerteidigung();
                }
                else {
                    aktuellerCharakterMacht = aktuellerCharakter.getMagischeAttacke();
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
                double kritMultiplikator = 1.0;
                int genauigkeitsBonus = 0;
                int ergebnisWert;
                boolean krit = false;
                if (aktuellerCharakter.getGenauigkeit() > 20) {
                    genauigkeitsBonus = aktuellerCharakter.getGenauigkeit() - 20;
                }
                double kritWahrscheinlichkeit = (0.3 + (faehigkeit.getWahrscheinlichkeit() - 1.0)
                        + 0.002 * (aktuellerCharakter.getBeweglichkeit() + genauigkeitsBonus));
                if (random.nextDouble() < kritWahrscheinlichkeit) {
                    if (kritWahrscheinlichkeit > 1.0) {
                        kritMultiplikator = 1.66 + ((kritWahrscheinlichkeit - 1) / 2);
                    }
                    else {
                        kritMultiplikator = 1.66;
                    }
                    krit = true;
                }

                ergebnisWert = (int) Math.floor((faehigkeit.getEffektStaerke() / basisSchadensWert)
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
                        if (aktuellerCharakter instanceof SpielerCharakter) {
                            ergebnisWert *= 1.2;
                        }
                        if (aktuellerCharakter instanceof Feind) {
                            ergebnisWert *= 0.8;
                        }
                        break;
                    case "Schwer":
                        if (aktuellerCharakter instanceof SpielerCharakter) {
                            ergebnisWert *= 0.8;
                        }
                        if (aktuellerCharakter instanceof Feind) {
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
                    case Attribute.RES:
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
                        kampfWerteLog.add(((Berserker) aktuellerCharakter.getKlasse()).spezialFaehigkeit(aktuellerCharakter, betroffenerCharakter, ergebnisWert, betroffenerCharakterAbwehr));
                        break;
                    case SpezialFaehigkeiten.FEUERMAGIER:
                        kampfWerteLog.add(((Feuermagier) aktuellerCharakter.getKlasse()).spezialFaehigkeit(aktuellerCharakter, betroffenerCharakter, ergebnisWert, betroffenerCharakterAbwehr));
                        break;
                    case SpezialFaehigkeiten.EISMAGIER:
                        kampfWerteLog.add(((Eismagier) aktuellerCharakter.getKlasse()).spezialFaehigkeit(aktuellerCharakter, betroffenerCharakter, aktuelleZugreihenfolge));
                        break;
                    case SpezialFaehigkeiten.PALADIN:
                        kampfWerteLog.add(((Paladin) aktuellerCharakter.getKlasse()).spezialFaehigkeit(aktuellerCharakter, betroffenerCharakter));
                        break;
                    case SpezialFaehigkeiten.PRIESTER:
                        kampfWerteLog.add(((Priester) aktuellerCharakter.getKlasse()).spezialFaehigkeit(aktuellerCharakter, betroffenerCharakter, freundeDieNochLeben, feindeDieNochLeben));
                        break;
                    case SpezialFaehigkeiten.RABAUKE:
                        kampfWerteLog.add(((Rabauke) aktuellerCharakter.getKlasse()).spezialFaehigkeit(aktuellerCharakter, betroffenerCharakter));
                        break;
                    case SpezialFaehigkeiten.SANMAUS:
                        kampfWerteLog.add(((Sanmaus) aktuellerCharakter.getKlasse()).spezialFaehigkeit(aktuellerCharakter, betroffenerCharakter));
                        break;
                    case SpezialFaehigkeiten.SCHURKE:
                        kampfWerteLog.add(((Schurke) aktuellerCharakter.getKlasse()).spezialFaehigkeit(aktuellerCharakter, betroffenerCharakter, freundeDieNochLeben, feindeDieNochLeben));
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
                        + (int) ((0.65 + 0.02 * aktuellerCharakter.getGenauigkeit()) * 100) + "%\n");
            }
        }
        aktualisiereZugreihenfolge();
        aktualisiereIstKampfVorbei();
    }

    private String abwehr(Charakter betroffenerCharakter, int ergebnisWert) {
        ergebnisWert -= betroffenerCharakter.getResistenz();
        int pDef = Math.min(Math.max(1, ergebnisWert), betroffenerCharakter.getVerteidigung());
        int mDef = Math.min(Math.max(1, ergebnisWert), betroffenerCharakter.getMagischeVerteidigung());
        betroffenerCharakter.setVerteidigung(betroffenerCharakter.getVerteidigung() - pDef);
        betroffenerCharakter.setMagischeVerteidigung(betroffenerCharakter.getMagischeVerteidigung() - mDef);
        return String.format("Abwehr von %s\nwurde verringert." + "\n Verteidigung -%d, Magische Verteidigung -%d\n", betroffenerCharakter.getName(), pDef, mDef);
    }

    private String beweglichkeit(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            betroffenerCharakter.setBeweglichkeit(betroffenerCharakter.getBeweglichkeit() + ergebnisWert);
            return String.format("Beweglichkeit von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int beweglichkeit = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getBeweglichkeit());
            betroffenerCharakter.setBeweglichkeit(betroffenerCharakter.getBeweglichkeit() - beweglichkeit);
            return String.format("Beweglichkeit von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), beweglichkeit);
        }
    }

    private boolean damageBetterTarget(SpielerCharakter aktuellesZiel, SpielerCharakter moeglichesZiel) {
        // Tank wird bevorzugt angegriffen
        if (moeglichesZiel.getKlasse() instanceof TNK) {
            if (!(aktuellesZiel.getKlasse() instanceof TNK)) {
                return true;
            }
        }
        return moeglichesZiel.getGesundheitsPunkte() < aktuellesZiel.getGesundheitsPunkte();
    }

    /**
     * Lässt Gegner Fähigkeit und Ziele wählen, falls er angreift und nicht blockt
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    private void gegnerLogikFaehigkeitUndZielGruppe() {
        String feindKlasse = aktuellerCharakter.getKlasse().getBezeichnung();
        List<Faehigkeit> moeglicheFaehigkeiten = new ArrayList<>();
        this.zielGruppe.clear();
        this.kampfWerteLog.clear();
        Faehigkeit faehigkeit = null;

        // Befüllt Feind-Ziel-ArrayList (Feind-Team)
        List<Feind> moeglicheFeinde = new ArrayList<>(feindeDieNochLeben);
        // Befüllt SpielerCharakter-Ziel-ArrayList (SpielerCharakter-Team)
        List<SpielerCharakter> moeglicheSpielerCharaktere = new ArrayList<>(freundeDieNochLeben);

        // Nur Fähigkeiten sind möglich für die die Manapunkte auch reichen
        for (Faehigkeit eineFaehigkeit : getAktiveFaehigkeiten(aktuellerCharakter)) {
            if (eineFaehigkeit.getLevel() > 0 && eineFaehigkeit.getManaKosten() < aktuellerCharakter.getManaPunkte()) {
                moeglicheFaehigkeiten.add(eineFaehigkeit);
            }
        }

        // Gegnerlogik ist klassenabhängig!!!
        switch (feindKlasse) {
            // Healer versuchen immer zuerst ihre Teammitglieder (inklusive sich selbst) zu
            // heilen!
            case Klasse.HLR:
                zielGruppe.clear();
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
                    for (Faehigkeit eineFaehigkeit : getAktiveFaehigkeiten(aktuellerCharakter)) {
                        if (eineFaehigkeit.getLevel() > 0 && eineFaehigkeit.getManaKosten() < aktuellerCharakter.getManaPunkte()) {
                            moeglicheFaehigkeiten.add(eineFaehigkeit);
                        }
                    }
                    // Alle Fähigkeiten die aufs eigene Team angewendet werden können fliegen raus
                    // Alle Fähigkeiten die auf mehr Charaktere angewendet werden können als es
                    // Ziele gibt fliegen raus
                    moeglicheFaehigkeiten.removeIf(eineFaehigkeit -> eineFaehigkeit.getZielAnzahl() > moeglicheSpielerCharaktere.size() || eineFaehigkeit.isIstFreundlich());
                    // Ziel-Gruppe ändert sich von eigener (Feind) zur SpielerCharakter-Gruppe
                    if (!moeglicheFaehigkeiten.isEmpty()) {
                        faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
                        int nochZuWaehlendeZiele = faehigkeit.getZielAnzahl();
                        while (nochZuWaehlendeZiele > 0) {
                            SpielerCharakter aktuellesZiel = moeglicheSpielerCharaktere.get(0);
                            for (SpielerCharakter ziel : moeglicheSpielerCharaktere) {
                                if (damageBetterTarget(aktuellesZiel, ziel)) {
                                    aktuellesZiel = ziel;
                                }
                            }
                            moeglicheSpielerCharaktere.remove(aktuellesZiel);
                            zielGruppe.add(aktuellesZiel);
                            nochZuWaehlendeZiele--;
                            if (moeglicheSpielerCharaktere.isEmpty()) {
                                nochZuWaehlendeZiele = 0;
                            }
                        }
                    }
                    else {
                        faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
                        if (faehigkeit.isIstFreundlich()) {
                            if (aktuellerCharakter instanceof Feind) {
                                zielGruppe.add(feindeDieNochLeben.get(0));
                            }
                            else {
                                zielGruppe.add(freundeDieNochLeben.get(0));
                            }
                        }
                        else {
                            if (aktuellerCharakter instanceof Feind) {
                                zielGruppe.add(freundeDieNochLeben.get(0));
                            }
                            else {
                                zielGruppe.add(feindeDieNochLeben.get(0));
                            }
                        }
                    }
                }
                // Es gibt Feind-Charaktere (eigenes Team) die geheilt werden können.
                // Das Fähigkeitsset besteht aus den zu Anfang bestimmten Fähigkeiten
                else {
                    // Fähigkeit wird aus dem möglichen Pool zufällig gewählt
                    faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
                    int nochZuWaehlendeZiele = faehigkeit.getZielAnzahl();
                    // Ziele werden auf Grundlage ihrer Lebenspunkte gewählt
                    // Beim heilen werden Feinde mit niedriger Gesundheit priorisiert
                    // Beim Schaden verursachen werden SpielerCharaktere mit niedriger Gesundheit
                    // priorisiert
                    while (nochZuWaehlendeZiele > 0) {
                        Feind aktuellesZiel = moeglicheFeinde.get(0);
                        for (Feind ziel : moeglicheFeinde) {
                            if (healBetterTarget(aktuellesZiel, ziel)) {
                                aktuellesZiel = ziel;
                            }
                        }
                        moeglicheFeinde.remove(aktuellesZiel);
                        zielGruppe.add(aktuellesZiel);
                        nochZuWaehlendeZiele--;
                        if (moeglicheFeinde.isEmpty()) {
                            nochZuWaehlendeZiele = 0;
                        }
                    }
                }
                break;

            // Tanks heilen sich entweder selbst, oder greifen die SpielerCharaktere-Gruppe
            // an, abhängig von ihren eigenen Lebenspunkten
            case Klasse.TNK:
                zielGruppe.clear();
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
                        faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
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
                    // Zielgruppe ist die SpielCharaktere-Gruppe
                    zielGruppe.addAll(freundeDieNochLeben);

                    // Fähigkeiten die aufs eigene Team angewendet werden fliegen raus
                    moeglicheFaehigkeiten.removeIf(Faehigkeit::isIstFreundlich);

                    // Fähigkeiten die mehr Ziele haben als es noch auswählbare SpielerCharaktere
                    // gibt fliegen raus
                    moeglicheFaehigkeiten.removeIf(eineFaehigkeit -> eineFaehigkeit.getZielAnzahl() > moeglicheSpielerCharaktere.size());

                    // Fähigkeit wird zufällig aus dem möglichen Pool bestimmt
                    if (moeglicheFaehigkeiten.isEmpty()) {
                        moeglicheFaehigkeiten.add(aktuellerCharakter.getFaehigkeiten().get(0));
                    }
                    faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
                    int nochZuWaehlendeZiele = faehigkeit.getZielAnzahl();
                    zielGruppe.clear();
                    // Ziele werden bestimmt, wobei niedrige Lebenspunkte priorisiert werden
                    while (nochZuWaehlendeZiele > 0) {
                        SpielerCharakter aktuellesZiel = moeglicheSpielerCharaktere.get(0);
                        for (SpielerCharakter ziel : moeglicheSpielerCharaktere) {
                            if (damageBetterTarget(aktuellesZiel, ziel)) {
                                aktuellesZiel = ziel;
                            }
                        }
                        moeglicheSpielerCharaktere.remove(aktuellesZiel);
                        zielGruppe.add(aktuellesZiel);
                        nochZuWaehlendeZiele--;
                        if (moeglicheSpielerCharaktere.isEmpty()) {
                            nochZuWaehlendeZiele = 0;
                        }
                    }
                }
                break;

            // 'Physische DD' und 'Magische DD' haben beide die gleiche offensive Logik,
            // welche der Logik entspricht, die der Tank verfolgt, solange er 50% oder mehr
            // seiner maximalen Lebenspunkte hat.
            case Klasse.PDD:
            case Klasse.MDD:
                for (int counter = 0, len = freundeDieNochLeben.size(); counter < len; counter++) {
                    zielGruppe.add(moeglicheSpielerCharaktere.get(counter));
                }
                moeglicheFaehigkeiten.removeIf(Faehigkeit::isIstFreundlich);
                moeglicheFaehigkeiten.removeIf(eineFaehigkeit -> eineFaehigkeit.getZielAnzahl() > moeglicheSpielerCharaktere.size());
                zielGruppe.clear();
                faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
                int nochZuWaehlendeZiele = faehigkeit.getZielAnzahl();
                while (nochZuWaehlendeZiele > 0) {
                    SpielerCharakter aktuellesZiel = moeglicheSpielerCharaktere.get(0);
                    for (SpielerCharakter ziel : moeglicheSpielerCharaktere) {
                        if (damageBetterTarget(aktuellesZiel, ziel)) {
                            aktuellesZiel = ziel;
                        }
                    }
                    moeglicheSpielerCharaktere.remove(aktuellesZiel);
                    zielGruppe.add(aktuellesZiel);
                    nochZuWaehlendeZiele--;
                    if (moeglicheSpielerCharaktere.isEmpty()) {
                        nochZuWaehlendeZiele = 0;
                    }
                }
                break;
        }

        if (faehigkeit == null ||
                faehigkeit.getManaKosten() > aktuellerCharakter.getManaPunkte() ||
                faehigkeit.getZielAnzahl() > zielGruppe.size()) {
            faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
            if (faehigkeit.isIstFreundlich()) {
                if (aktuellerCharakter instanceof Feind) {
                    zielGruppe.add(feindeDieNochLeben.get(0));
                }
                else {
                    zielGruppe.add(freundeDieNochLeben.get(0));
                }
            }
            else {
                if (aktuellerCharakter instanceof Feind) {
                    zielGruppe.add(freundeDieNochLeben.get(0));
                }
                else {
                    zielGruppe.add(feindeDieNochLeben.get(0));
                }
            }
        }
        gegnerFaehigkeit = faehigkeit;
        kampfView.setFaehigkeit(gegnerFaehigkeit);
    }

    private String genauigkeit(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            betroffenerCharakter.setGenauigkeit(betroffenerCharakter.getGenauigkeit() + ergebnisWert);
            return String.format("Genauigkeit von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int genauigkeit = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getGenauigkeit());
            betroffenerCharakter.setGenauigkeit(betroffenerCharakter.getGenauigkeit() - genauigkeit);
            return String.format("Genauigkeit von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), genauigkeit);
        }
    }

    private String gesundheitsPunkte(Faehigkeit faehigkeit, int betroffenerCharakterAbwehr, Charakter betroffenerCharakter, int ergebnisWert, boolean krit) {
        if (faehigkeit.isIstFreundlich()) {
            int healWert;
            // gleiches Team -> Heal -> Verteidigung des Zieles spielt keine Rolle
            if (ergebnisWert + betroffenerCharakter.getGesundheitsPunkte() > betroffenerCharakter
                    .getMaxGesundheitsPunkte()) {
                healWert = betroffenerCharakter.getMaxGesundheitsPunkte()
                        - betroffenerCharakter.getGesundheitsPunkte();
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
            betroffenerCharakter.setGesundheitsRegeneration(betroffenerCharakter.getGesundheitsRegeneration() + ergebnisWert);
            return String.format("Gesundheitsregeneration von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int gesReg = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getGesundheitsRegeneration());
            betroffenerCharakter.setGesundheitsRegeneration(betroffenerCharakter.getGesundheitsRegeneration() - gesReg);
            return String.format("Gesundheitsregeneration von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), gesReg);
        }
    }

    private boolean healBetterTarget(Feind aktuellesZiel, Feind moeglichesZiel) {
        return moeglichesZiel.getGesundheitsPunkte() < aktuellesZiel.getGesundheitsPunkte();
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
        // freundeDieNochLeben, feindeDieNochLeben, etc. wird alles befüllt
        partyAnordnung.add(partyController.getParty().getHauptCharakter());
        for (SpielerCharakter nebencharakter : partyController.getParty().getNebenCharakter()) {
            if (nebencharakter != null) {
                partyAnordnung.add(nebencharakter);
            }
        }
        if (partyController.getParty().getHauptCharakter().getGesundheitsPunkte() > 0) {
            freundeDieNochLeben.add(partyController.getParty().getHauptCharakter());
        }
        else {
            freundeDieGestorbenSind.add(partyController.getParty().getHauptCharakter());
        }
        for (SpielerCharakter nebenCharakter : partyController.getParty().getNebenCharakter()) {
            if (nebenCharakter != null) {
                if (nebenCharakter.getGesundheitsPunkte() > 0) {
                    freundeDieNochLeben.add(nebenCharakter);
                }
                else {
                    freundeDieGestorbenSind.add(nebenCharakter);
                }
            }
        }
        for (Charakter value : initialeZugreihenfolge) {
            if (value instanceof Feind) {
                feindeDieNochLeben.add((Feind) value);
                gegnerAnordnung.add((Feind) value);
            }
        }
        freundeDieNochActionHaben.addAll(freundeDieNochLeben);
        feindeDieNochActionHaben.addAll(feindeDieNochLeben);
        aktuelleZugreihenfolge.addAll(initialeZugreihenfolge);
        Collections.reverse(aktuelleZugreihenfolge);
        aktuellerCharakter = aktuelleZugreihenfolge.get(0);

        kampfView = new KampfView(this);
        kampfView.updateKampfBildschirm();
        viewController.anmelden(this.kampfView, null, AnsichtsTyp.NICHT_CACHE);
    }

    private String magischeAttacke(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            betroffenerCharakter.setMagischeAttacke(betroffenerCharakter.getMagischeAttacke() + ergebnisWert);
            return String.format("Magische Attacke von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int magAtk = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getMagischeAttacke());
            betroffenerCharakter.setMagischeAttacke(betroffenerCharakter.getMagischeAttacke() - magAtk);
            return String.format("Magische Attacke von %s\nwurde um %d verringert\n", betroffenerCharakter.getName(), magAtk);
        }
    }

    private String magischeRegeneration(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            betroffenerCharakter.setManaRegeneration(betroffenerCharakter.getManaRegeneration() + ergebnisWert);
            return String.format("Manaregeneration von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int manaReg = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getManaRegeneration());
            betroffenerCharakter.setManaRegeneration(betroffenerCharakter.getManaRegeneration() - manaReg);
            return String.format("Manaregeneration von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), manaReg);
        }
    }

    private String magischeVerteidigung(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            betroffenerCharakter.setMagischeVerteidigung(betroffenerCharakter.getMagischeVerteidigung() + ergebnisWert);
            return String.format("Magische Verteidigung von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int magVer = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getMagischeVerteidigung());
            betroffenerCharakter.setMagischeVerteidigung(betroffenerCharakter.getMagischeVerteidigung() - magVer);
            return String.format("Magische Verteidigung von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), magVer);
        }
    }

    private String manaPunkte(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Heal -> Resistenz des Zieles spielt keine Rolle
        int mp;
        if (faehigkeit.isIstFreundlich()) {
            if (betroffenerCharakter.getManaPunkte() + ergebnisWert > betroffenerCharakter
                    .getMaxManaPunkte()) {
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
            betroffenerCharakter.setMaxGesundheitsPunkte(betroffenerCharakter.getMaxGesundheitsPunkte() + ergebnisWert);
            // Die aktuellen Gesundheitspunkte müssen um den gleichen Wert erhöht werden
            // da die Erhöhung der maxHP mit einem Heal einhergeht
            betroffenerCharakter.setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() + ergebnisWert);
            return String.format("Maximale Gesundheit von %s wurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int maxHP = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getMaxGesundheitsPunkte());
            betroffenerCharakter.setMaxGesundheitsPunkte(betroffenerCharakter.getMaxGesundheitsPunkte() - maxHP);
            // Wenn der Charakter aus dem Gegnerteam durch die Reduktion der maxHP nun
            // mehr aktuelle als maxHP hat, müssen die aktuellen HP auf den neuen
            // maxHP-Stand gesetzt werden (impliziert Schaden verursachen)
            if (betroffenerCharakter.getMaxGesundheitsPunkte() < betroffenerCharakter.getGesundheitsPunkte()) {
                betroffenerCharakter.setGesundheitsPunkte(betroffenerCharakter.getMaxGesundheitsPunkte());
            }
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
            betroffenerCharakter.setMaxManaPunkte(betroffenerCharakter.getMaxManaPunkte() + ergebnisWert);
            // Die aktuellen MP müssen um den gleichen Wert erhöht werden
            // da die Erhöhung der maxMP diese mit anhebt
            betroffenerCharakter.setManaPunkte(betroffenerCharakter.getManaPunkte() + ergebnisWert);
            return String.format("Maximales Mana von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int maxMP = Math.min(Math.max(0, ergebnisWert), aktuellerCharakter.getMaxManaPunkte());
            betroffenerCharakter.setMaxManaPunkte(betroffenerCharakter.getMaxManaPunkte() - maxMP);
            // Wenn der Charakter aus dem Gegnerteam durch die Reduktion der maxMP nun
            // mehr aktuelle als maxMP hat, müssen die aktuellen MP auf den neunen
            // maxMP-Stand gesetzt werden (impliziert Reduktion der MP)
            if (betroffenerCharakter.getMaxManaPunkte() < betroffenerCharakter.getManaPunkte()) {
                betroffenerCharakter.setManaPunkte(betroffenerCharakter.getMaxManaPunkte());
            }
            return String.format("Maximales Mana von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), maxMP);
        }
    }

    private String physischeAttacke(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            betroffenerCharakter.setPhysischeAttacke(betroffenerCharakter.getPhysischeAttacke() + ergebnisWert);
            return String.format("Physische Attacke von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int physAtk = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getPhysischeAttacke());
            betroffenerCharakter.setPhysischeAttacke(betroffenerCharakter.getPhysischeAttacke() - physAtk);
            return String.format("Physische Attacke von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), physAtk);
        }
    }

    private String resistenz(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            betroffenerCharakter.setResistenz(betroffenerCharakter.getResistenz() + ergebnisWert);
            return String.format("Resistenz von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int resistenz = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getResistenz());
            betroffenerCharakter.setResistenz(betroffenerCharakter.getResistenz() - resistenz);
            return String.format("Resistenz von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), resistenz);
        }
    }

    private String verteidigung(Faehigkeit faehigkeit, Charakter betroffenerCharakter, int ergebnisWert) {
        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
        if (faehigkeit.isIstFreundlich()) {
            betroffenerCharakter.setVerteidigung(betroffenerCharakter.getVerteidigung() + ergebnisWert);
            return String.format("Verteidigung von %s\nwurde um %d erhöht.\n", betroffenerCharakter.getName(), ergebnisWert);
        }
        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
        else {
            ergebnisWert -= betroffenerCharakter.getResistenz();
            int verteidigung = Math.min(Math.max(0, ergebnisWert), betroffenerCharakter.getVerteidigung());
            betroffenerCharakter.setVerteidigung(betroffenerCharakter.getVerteidigung() - verteidigung);
            return String.format("Verteidigung von %s\nwurde um %d verringert.\n", betroffenerCharakter.getName(), verteidigung);
        }
    }
}
