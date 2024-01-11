package de.bundeswehr.auf.final_fantasy.menu.taverne;

import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.*;
import de.bundeswehr.auf.final_fantasy.menu.gamehub.GameHubController;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import de.bundeswehr.auf.final_fantasy.party.model.Party;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.statistik.StatistikController;
import de.bundeswehr.auf.final_fantasy.menu.overlay.AnsichtsTyp;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.menu.taverne.view.AusruhenView;
import de.bundeswehr.auf.final_fantasy.menu.taverne.view.SoeldnerEntlassenView;
import de.bundeswehr.auf.final_fantasy.menu.taverne.view.TaverneView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TaverneController {

    private static final String[] NAMEN = {"Finn", "Ivy", "Zane", "Luna", "Blaze", "Nova", "Kai", "Ember", "Aria", "Orion", "Orio", "Jade", "Axel", "Zaza", "Griffin", "Serena", "Titan", "Scarlett", "Asher", "Lyra", "Jasper", "Celeste", "Silas", "Elara", "Kian", "Phonix", "Dax", "Sable", "Ryder", "Hawk", "Dawn", "Hans", "Greta", "Klaus", "Ingrid", "Friedrich", "Heidi", "Otto", "Liesl", "Dieter", "Anneliese", "Wolfgang", "Ilse", "Ludwig", "Gerda", "Gunther", "Helga", "Heinrich", "Ursula", "Ernst", "Hilde"};

    private PartyController partyController;
    private StatistikController statistikController;
    private int letzteGeneration;
    private GameHubController gameHubController;
    private SpielerCharakter[] soeldner;
    private ViewController viewController;
    private List<Button> taverneButtons;
    private List<Button> taverneEntlassenButtons;
    private TaverneView taverneView;
    private SoeldnerEntlassenView taverneEntlassenView;
    private List<SpielerCharakter> nebenCharaktere;
    BooleanProperty anheuernNichtVerfuegbar = new SimpleBooleanProperty(false);

    /**
     * Konstruktor für den TaverneController.
     * Der TaverneController ist verantwortlich für die Interaktionen innerhalb der Taverne,
     * einschließlich des Anheuerns und Entlassens von Söldnern, sowie dem Ausruhen der Party.
     *
     * @author Dennis, Markus (vor allem die Bindings!! ;) ), Nick
     * @since 06.12.2023
     * @param partyController       Der PartyController für die Party-Interaktionen (Partylevel, Gold, Nebencharaktere)
     * @param statistikController   Der StatistikController, zum abrufen der durchgeführten Kämpfe (Söldnergeneration)
     * @param gameHubController     Der GameHubController für die Navigation.
     * @param viewController        Der ViewController zum Anmelden der Views und nachhintensetzen der Ansichten.
     */
    public TaverneController(PartyController partyController, StatistikController statistikController,
                             GameHubController gameHubController, ViewController viewController) {
        this.partyController = partyController;
        this.statistikController = statistikController;
        this.letzteGeneration = -4;
        this.gameHubController = gameHubController;
        this.viewController = viewController;
        AtomicInteger aufrufe = new AtomicInteger(0);
        BooleanProperty soeldnerVorhanden = new SimpleBooleanProperty((anzahlSoeldnerInParty().getValue() >= 0));

        Button entlassenView = new Button("Einen Söldner entlassen");
        entlassenView.setOnAction(event -> zuEntlassendeMitgliederAnzeigen());
        entlassenView.disableProperty().bind(Bindings.equal(soeldnerVorhanden, new SimpleBooleanProperty(false)));

        Button anheuern = new Button("Anheuern für " + (int) Math.floor(partyController.getPartyLevel()) + " Gold");
        anheuern.setOnAction(event -> {
            teammitgliedEinstellen(taverneView.getSoeldnerIndex());
            new TaverneView(this);
            aufrufe.getAndIncrement();
            soeldnerVorhanden.setValue(true);
            anheuernNichtVerfuegbar.setValue(istKeinSoeldnerVorhanden().getValue());
            if (nebenCharaktere.size() == 3){
                anheuernNichtVerfuegbar.set(true);
            }
        });
//        Binding<Boolean> disableAnheuern = Bindings.createBooleanBinding(() ->
//            !((int) Math.floor(partyController.getPartyLevel() <= partyController.getParty().goldProperty().get() &&
//                    !anheuernVerfuegbar.get()))
//        , partyController.getParty().goldProperty(), anheuernVerfuegbar);
        anheuern.disableProperty().bind(Bindings.when(Bindings.and(Bindings.greaterThan((int) Math.floor(partyController.getPartyLevel()), partyController.getParty().goldProperty()).not()
                , Bindings.equal(anheuernNichtVerfuegbar, new SimpleBooleanProperty(true)).not())).then(false).otherwise(true));

        Button ausruhen = new Button("Ausruhen für " + (int) Math.floor(partyController.getPartyLevel()) + " Gold");
        ausruhen.disableProperty().bind(Bindings.greaterThan((int) Math.floor(partyController.getPartyLevel()), partyController.getParty().goldProperty()));
        ausruhen.setOnAction(event -> ausruhen());

        Button zurueck = new Button("Zurück zum Gamehub");
        zurueck.setOnAction(event -> {
            if (aufrufe.get() > 0) {
                for (int i = 0; i <= aufrufe.get(); i++) {
                    viewController.aktuelleNachHinten();
                }
            } else {
                viewController.aktuelleNachHinten();
            }
            aufrufe.set(0);
        });
        this.taverneButtons = new ArrayList<>(Arrays.asList(anheuern, entlassenView, ausruhen, zurueck));

        // ab hier Entlassen Buttons
        AtomicInteger entlassenAufrufe = new AtomicInteger(0);
        Button entlassen = new Button("Entlassen!");
        entlassen.setOnAction(event -> {
            if (taverneEntlassenView.getSoeldnerIndex() == 2 && nebenCharaktere.size() == 1){
                teammitgliedEntlassen(nebenCharaktere.get(taverneEntlassenView.getSoeldnerIndex()-2));
            } else if (taverneEntlassenView.getSoeldnerIndex() == 1 && nebenCharaktere.size() == 1){
                teammitgliedEntlassen(nebenCharaktere.get(taverneEntlassenView.getSoeldnerIndex()-1));
            } else {
                teammitgliedEntlassen(nebenCharaktere.get(taverneEntlassenView.getSoeldnerIndex()));
            }
            entlassenAufrufe.getAndIncrement();
            soeldnerVorhanden.setValue(anzahlSoeldnerInParty().getValue() >= 0);
        });
        entlassen.disableProperty().bind(Bindings.equal(soeldnerVorhanden, new SimpleBooleanProperty(false)));
        Button zurueckAusEntlassen = new Button("Zurück");
        zurueckAusEntlassen.setOnAction(e -> {
            if (entlassenAufrufe.get() > 0) {
                for (int i = 0; i <= entlassenAufrufe.get(); i++) {
                    viewController.aktuelleNachHinten();
                }
            } else {
                viewController.aktuelleNachHinten();
            }
            anheuernNichtVerfuegbar.setValue(istKeinSoeldnerVorhanden().getValue());
            entlassenAufrufe.set(0);
        });
        taverneEntlassenButtons = new ArrayList<>(Arrays.asList(entlassen, zurueckAusEntlassen));
    }

    /**
     * Dient zum Anzeigen der Taverne.
     * Bei jedem Aufruf des Anzeigens werden neue Soeldner generiert, sofern min. drei
     * Kaempfe durchgefuehrt worden sind.
     * @author OF Ridder / OF Schroeder
     * @since 06.12.2023
     */
    public void taverneAnzeigen() {
        Party party = partyController.getParty();
        anheuernNichtVerfuegbar.setValue(istKeinSoeldnerVorhanden().getValue());
        if (statistikController.getStatistik().getDurchgefuehrteKaempfe() - letzteGeneration >= 3) {
            generiereSoeldner();
        }
        nebenCharaktere = new ArrayList<>();
        for (int i = 0; i < party.getNebenCharakter().length; i++) {
            if (party.getNebenCharakter()[i] != null) {
                nebenCharaktere.add(party.getNebenCharakter()[i]);
            }
        }
        this.taverneButtons.get(0).setText("Anheuern für " + (int) Math.floor(partyController.getPartyLevel()) + " Gold");
        taverneView = new TaverneView(this);
        viewController.anmelden(taverneView, taverneButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    /**
     * Überprüft, ob keine Söldner in der Party vorhanden sind.
     * @author Markus
     * @since 05.12.2023
     * @return Ein BooleanProperty, das wahr ist, wenn keine Söldner vorhanden sind, sonst falsch.
     */
    public BooleanProperty istKeinSoeldnerVorhanden() {
        BooleanProperty ergebnis = new SimpleBooleanProperty(true);
        if (soeldner != null) {
            for (int i = 0; i < soeldner.length; i++) {
                if (soeldner[i] != null) {
                    ergebnis.setValue(false);
                    break;
                }
            }
        }
        return ergebnis;
    }

    private void generiereSoeldner() {
        soeldner = new SpielerCharakter[3];
        for (int i = 0; i < 3; i++) {
            soeldner[i] = generiereEinenZufaelligenSoeldner((int) partyController.getPartyLevel());
        }
        letzteGeneration = statistikController.getStatistik().getDurchgefuehrteKaempfe();

        // Setzt den Anheuern-Button wieder auf verfügbar
        int counter = 0;
        for (int i = 0; i < partyController.getParty().getNebenCharakter().length; i++) {
            if (partyController.getParty().getNebenCarakter(i) != null) {
                counter++;
            }
        }
        if (counter < 2) {
            anheuernNichtVerfuegbar.setValue(istKeinSoeldnerVorhanden().getValue());
        }

    }

    /**
     * Generiert einen zufälligen Söldner
     * @author Oli
     * @since 05.12.2023
     * @param level Das Level
     * @return Ein neuer SpielerCharakter als zufälliger Söldner.
     */
    public static SpielerCharakter generiereEinenZufaelligenSoeldner(int level) {
        String zufaelligerName = NAMEN[ZufallsZahlenGenerator.zufallsZahlIntAb0(NAMEN.length - 1)];
        String zufaelligeKlasse = Klasse.KLASSEN_NAMEN[ZufallsZahlenGenerator.zufallsZahlIntAb0(Klasse.KLASSEN_NAMEN.length - 1)];
        String geschichte;
        switch (zufaelligeKlasse) {
            case Klasse.HLR:
                geschichte = HLR.getGeschichte().replace("#NAME#", zufaelligerName);
                break;
            case Klasse.MDD:
                geschichte = MDD.getGeschichte().replace("#NAME#", zufaelligerName);
                break;
            case Klasse.PDD:
                geschichte = PDD.getGeschichte().replace("#NAME#", zufaelligerName);
                break;
            case Klasse.TNK:
                geschichte = TNK.getGeschichte().replace("#NAME#", zufaelligerName);
                break;
            default:
                geschichte = "Hier könnte Ihre Werbung stehen!";
                break;
        }
        return new SpielerCharakter(zufaelligerName, zufaelligeKlasse, geschichte, level, true);
    }

    private void ausruhen() {
        AusruhenView ausruhenView = new AusruhenView(viewController);
        viewController.anmelden(ausruhenView, null, AnsichtsTyp.OHNE_OVERLAY);
        Party party = partyController.getParty();
        if (partyController.getPartyGold() >= (int) Math.floor(partyController.getPartyLevel())) {
            partyController.goldAbziehen((int) Math.floor(partyController.getPartyLevel()));
            List<SpielerCharakter> nebenCharaktere = new ArrayList<>();
            for (int i = 0; i < party.getNebenCharakter().length; i++) {
                if (party.getNebenCharakter()[i] != null) {
                    nebenCharaktere.add(party.getNebenCharakter()[i]);
                }
            }
            party.getHauptCharakter().setGesundheitsPunkte(party.getHauptCharakter().getMaxGesundheitsPunkte());
            party.getHauptCharakter().setManaPunkte(party.getHauptCharakter().getMaxManaPunkte());
            for (SpielerCharakter spielerCharakter : nebenCharaktere) {
                spielerCharakter.setGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte());
                spielerCharakter.setManaPunkte(spielerCharakter.getMaxManaPunkte());
            }
        }
    }

    private void zuEntlassendeMitgliederAnzeigen() {
        taverneEntlassenView = new SoeldnerEntlassenView(this, partyController);
        viewController.anmelden(taverneEntlassenView, taverneEntlassenButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    private void teammitgliedEinstellen(int index) {
        if (partyController.getPartyGold() >= (int) Math.floor(partyController.getPartyLevel())) {
            partyController.goldAbziehen((int) Math.floor(partyController.getPartyLevel()));
//            partyController.goldAbziehen(200); // nur testweise eingefügt, um das Disabling der Buttons zu testen
            partyController.teammitgliedHinzufuegen(soeldner[index]);
//            System.out.println(Farbauswahl.GREEN_BACKGROUND + soeldner[index].getName() + " angeheuert!" + Farbauswahl.RESET);
            soeldner[index] = null;
        } else {
            System.out.println("Deine Armut kotzt mich an!");
        }
        taverneAnzeigen();
    }

    private void teammitgliedEntlassen(SpielerCharakter soeldner) {
        partyController.teammitgliedEntfernen(soeldner);
        taverneEntlassenView.setSoeldnerIndex(taverneEntlassenView.getSoeldnerIndex());
//        System.out.println(Farbauswahl.RED_BACKGROUND + soeldner.getName() + " entlassen!" + Farbauswahl.RESET);
        // Setzt den Anheuern-Button wieder auf verfügbar
        int counter = 0;
        for (int i = 0; i < partyController.getParty().getNebenCharakter().length; i++) {
            if (partyController.getParty().getNebenCarakter(i) != null) {
                counter++;
            }
        }
        if (counter < 2) {
            anheuernNichtVerfuegbar.setValue(istKeinSoeldnerVorhanden().getValue());
        }
        zuEntlassendeMitgliederAnzeigen();
    }

    public SpielerCharakter[] getSoeldner() {
        return soeldner;
    }

    public List<SpielerCharakter> getNebenCharaktere() {
        return nebenCharaktere;
    }

    /**
     * Gibt die Anzahl der Söldner in der Party zurück.
     * @author Markus
     * @since 05.12.2023
     * @return Ein IntegerProperty, das die Anzahl der Söldner in der Party repräsentiert.
     */
    public IntegerProperty anzahlSoeldnerInParty() {
        int soeldnerIndex = -1;
        for (int i = 0; i < partyController.getParty().getNebenCharakter().length; i++) {
            if (partyController.getParty().getNebenCharakter()[i] != null) {
                soeldnerIndex++;
            }
        }
        return new SimpleIntegerProperty(soeldnerIndex);
    }
}
