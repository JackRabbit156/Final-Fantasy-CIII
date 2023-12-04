package taverne;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.*;
import charakter.model.klassen.soeldner.Kaempfer;
import charakter.model.klassen.soeldner.Magier;
import charakter.model.klassen.soeldner.Supporter;
import gamehub.GameHubController;
import hilfsklassen.Farbauswahl;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import hilfsklassen.ZufallsZahlenGenerator;
import party.Party;
import party.PartyController;
import statistik.StatistikController;
import view.AnsichtsTyp;
import view.ViewController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class TaverneController {

    private static final String[] NAMEN = {"Finn", "Ivy", "Zane", "Luna", "Blaze", "Nova", "Kai", "Ember", "Aria", "Orion", "Orio", "Jade", "Axel", "Zaza", "Griffin", "Serena", "Titan", "Scarlett", "Asher", "Lyra", "Jasper", "Celeste", "Silas", "Elara", "Kian", "Phonix", "Dax", "Sable", "Ryder", "Hawk", "Dawn", "Hans", "Greta", "Klaus", "Ingrid", "Friedrich", "Heidi", "Otto", "Liesl", "Dieter", "Anneliese", "Wolfgang", "Ilse", "Ludwig", "Gerda", "Gunther", "Helga", "Heinrich", "Ursula", "Ernst", "Hilde"};

	private Taverne taverne;
	private PartyController partyController;
	private StatistikController statistikController;
	private int letzteGeneration;
	private GameHubController gameHubController;
    private PartyController partyController;
    private StatistikController statistikController;
    private int letzteGeneration;
    private GameHubController gameHubController;
    private SpielerCharakter[] soeldner;
    private ViewController viewController;
    private ArrayList<Button> taverneButtons;
    private ArrayList<Button> taverneEntlassenButtons;
    private TaverneView taverneView;
    private TaverneEntlassenView taverneEntlassenView;
    private ArrayList<SpielerCharakter> nebenCharaktere;

    public TaverneController(PartyController partyController, StatistikController statistikController,
                             GameHubController gameHubController, ViewController viewController) {
        this.partyController = partyController;
        this.statistikController = statistikController;
        this.letzteGeneration = -4;
        this.gameHubController = gameHubController;
        this.viewController = viewController;
        BooleanProperty soeldnerVorhanden = new SimpleBooleanProperty((anzahlSoeldnerInParty().getValue() >= 0));
        BooleanProperty anheuernVerfuegbar = new SimpleBooleanProperty(false);
        AtomicInteger aufrufe = new AtomicInteger(0);
        Button entlassenView = new Button("Söldner entlassen");
        entlassenView.setOnAction(event -> zuEntlassendeMitgliederAnzeigen());
        entlassenView.disableProperty().bind(Bindings.equal(soeldnerVorhanden, new SimpleBooleanProperty(false)));
        Button anheuern = new Button("Anheuern für " + (int) Math.floor(partyController.getPartyLevel()) + " Gold");
        anheuern.setOnAction(event -> {
            teammitgliedEinstellen(taverneView.getSoeldnerIndex());
            new TaverneView(this);
            aufrufe.getAndIncrement();
            soeldnerVorhanden.setValue(true);
            anheuernVerfuegbar.setValue(istKeinSoeldnerVorhanden().getValue());

        });
        anheuern.disableProperty().bind(Bindings.when(Bindings.and(Bindings.greaterThan((int) Math.floor(partyController.getPartyLevel()), partyController.getParty().goldProperty()).not()
                , Bindings.equal(anheuernVerfuegbar, new SimpleBooleanProperty(true)).not())).then(false).otherwise(true));
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
        AtomicInteger entlassenAufrufe = new AtomicInteger(0);
        Button entlassen = new Button("Entlassen!");
        entlassen.setOnAction(event -> {
            teammitgliedEntlassen(nebenCharaktere.get(taverneEntlassenView.getSoeldnerIndex()));
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
            entlassenAufrufe.set(0);
        });
        taverneEntlassenButtons = new ArrayList<>(Arrays.asList(entlassen, zurueckAusEntlassen));
    }

    /**
     * Dient zum Anzeigen der Taverne, welche die Moeglichkeit bietet, sich
     * auszuruhen, um Gesundheit und Mana der Party gegen Gold wiederherzustellen,
     * sowie die Moeglichkeiten zum Einstellen und Entlassen von Soeldnern. Bei
     * jedem Aufruf des Anzeigens werden neue Soeldner generiert, sofern min. drei
     * Kaempfe durchgefuehrt worden sind.
     *
     * @author OF Ridder / OF Schroeder
     * @since 21.11.2023
     */
    public void taverneAnzeigen() {
        Party party = partyController.getParty();
        if (statistikController.getStatistik().getDurchgefuehrteKaempfe() - letzteGeneration >= 3) {
            generiereSoeldner();
        }
        nebenCharaktere = new ArrayList<>();
        for (int i = 0; i < party.getNebenCharakter().length; i++) {
            if (party.getNebenCharakter()[i] != null) {
                nebenCharaktere.add(party.getNebenCharakter()[i]);
            }
        }
        taverneView = new TaverneView(this);
        viewController.anmelden(taverneView, taverneButtons, AnsichtsTyp.MIT_OVERLAY);
    }

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
        soeldner[0] = new Magier("Voldemort", "Magischer DD", "Klassenbester aus Hogwarts!", (int) Math.floor(partyController.getPartyLevel()));
        soeldner[1] = new Kaempfer("Kloppi", "Physischer DD", "Hauptschuleeeee - aufs Maul?", (int) Math.floor(partyController.getPartyLevel()));
        soeldner[2] = new Supporter("DerSupporter", "Tank", "Alles fuers Team!", (int) Math.floor(partyController.getPartyLevel()));

        /*
        Nach jeweils X Kaempfen (ein Kampf zaehlt, egal ob er gewonnen oder verloren wurde) werden die rekrutierbaren Soeldner in voller Anzahl neu generiert. (Bereits in die Party rekrutierte Soeldner bleiben bestehen).
         */

        for (int i = 0; i < 3; i++) {
            soeldner[i] = generiereEinenZufaelligenSoeldner((int)partyController.getPartyLevel());
        }
        letzteGeneration = statistikController.getStatistik().getDurchgefuehrteKaempfe();
    }

    public static SpielerCharakter generiereEinenZufaelligenSoeldner(int level){
	    String zufaelligerName = NAMEN[ZufallsZahlenGenerator.zufallsZahlIntAb0(NAMEN.length-1)];
	    String zufaelligeKlasse = Klasse.KLASSEN_NAMEN[ZufallsZahlenGenerator.zufallsZahlIntAb0(Klasse.KLASSEN_NAMEN.length-1)];
		SpielerCharakter returnCharakter = new SpielerCharakter(zufaelligerName, zufaelligeKlasse, "Geschichte",level, true);
		returnCharakter.setGeschichte(returnCharakter.getKlasse().getGeschichte());
        return returnCharakter;
    }

    private void ausruhen() {
        AusruhenView ausruhenView = new AusruhenView(viewController);
        viewController.anmelden(ausruhenView, null, AnsichtsTyp.OHNE_OVERLAY);
        Party party = partyController.getParty();
        if (partyController.getPartyGold() >= (int) Math.floor(partyController.getPartyLevel())) {
            partyController.goldAbziehen((int) Math.floor(partyController.getPartyLevel()));
            ArrayList<SpielerCharakter> nebenCharaktere = new ArrayList<>();
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
        } else {
            System.out.println(Farbauswahl.RED_BACKGROUND + "Nicht genug Gold!" + Farbauswahl.RESET);
        }
    }

    private void zuEntlassendeMitgliederAnzeigen() {
        taverneEntlassenView = new TaverneEntlassenView(this, partyController);
        viewController.anmelden(taverneEntlassenView, taverneEntlassenButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    private void teammitgliedEinstellen(int index) {
        if (partyController.getPartyGold() >= (int) Math.floor(partyController.getPartyLevel())) {
            partyController.goldAbziehen((int) Math.floor(partyController.getPartyLevel()));
//            partyController.goldAbziehen(200); // nur testweise eingefügt, um das Disabling der Buttons zu testen
            partyController.teammitgliedHinzufuegen(soeldner[index]);
            System.out.println(Farbauswahl.GREEN_BACKGROUND + soeldner[index].getName() + " angeheuert!" + Farbauswahl.RESET); // TODO am Ende rausnehmen, ist nur zum Debug noch drin
            soeldner[index] = null;
        } else {
            System.out.println("Deine Armut kotzt mich an!");
        }
        taverneAnzeigen();
    }

    private void teammitgliedEntlassen(SpielerCharakter soeldner) {
        partyController.teammitgliedEntfernen(soeldner);
        taverneEntlassenView.setSoeldnerIndex(taverneEntlassenView.getSoeldnerIndex());
        System.out.println(Farbauswahl.RED_BACKGROUND + soeldner.getName() + " entlassen!" + Farbauswahl.RESET); // TODO am Ende rausnehmen, ist nur zum Debug noch drin
        zuEntlassendeMitgliederAnzeigen();
    }

    public SpielerCharakter[] getSoeldner() {
        return soeldner;
    }

    public ArrayList<SpielerCharakter> getNebenCharaktere() {
        return nebenCharaktere;
    }

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
