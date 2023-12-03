package trainer;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.ViewController;

/**
 * The type Trainer attribute aendern view.
 */
public class TrainerAttributeAendernView extends VBox {
    private TrainerController trainerController;
    private Label offeneAttributsPunkte;
    private Label lblMaxGesundheit;
    private Label lblMaxGesundheitWert;
    private Button btnMaxGesundheitPlus;
    private Button btnMaxGesundheitMinus;
    private Label lblMaxMana;
    private Label lblMaxManaWert;
    private Button btnMaxManaPlus;
    private Button btnMaxManaMinus;
    private Label lblPhysischeAttacke;
    private Label lblPhysischeAttackeWert;
    private Button btnPhysischeAttackePlus;
    private Button btnPhysischeAttackeMinus;
    private Label lblMagischeAttacke;
    private Label lblMagischeAttackeWert;
    private Button btnMagischeAttackePlus;
    private Button btnMagischeAttackeMinus;
    private Label lblGenauigkeit;
    private Label lblGenauigkeitWert;
    private Button btnGenauigkeitPlus;
    private Button btnGenauigkeitMinus;
    private Label lblVerteidigung;
    private Label lblVerteidigungWert;
    private Button btnVerteidigungPlus;
    private Button btnVerteidigungMinus;
    private Label lblMagischeVerteidigung;
    private Label lblMagischeVerteidigungWert;
    private Button btnMagischeVerteidigungPlus;
    private Button btnMagischeVerteidigungMinus;
    private Label lblResistenz;
    private Label lblResistenzWert;
    private Button btnResistenzPlus;
    private Button btnResistenzMinus;
    private Label lblBeweglichkeit;
    private Label lblBeweglichkeitWert;
    private Button btnBeweglichkeitPlus;
    private Button btnBeweglichkeitMinus;


    /**
     * Instantiates a new Trainer attribute aendern view.
     *
     * @param trainerController the trainer controller
     */
    public TrainerAttributeAendernView(TrainerController trainerController) {


        this.trainerController = trainerController;
        Label titel = new Label("Attribute verändern");
        titel.setAlignment(Pos.CENTER);
        offeneAttributsPunkte = new Label();
        offeneAttributsPunkte.setAlignment(Pos.CENTER);
        //Erstellung der zentralen Buttons für Plus und Minus
        //Plus Button
        Image imgPlus = new Image("icons\\plus.png");
        Image imgMinus = new Image("icons\\minus.png");
        ImageView imgBtnPlus = new ImageView(imgPlus);
        ImageView imgBtnMinus = new ImageView(imgMinus);
        //Container MaxGesundheit
        HBox containerMaxGesundheitspunkte = new HBox();
        HBox cointainerMaxGesundheitspunkteplusminus = new HBox();
        lblMaxGesundheit = new Label();
        lblMaxGesundheit.setAlignment(Pos.CENTER);
        lblMaxGesundheitWert = new Label();
        lblMaxGesundheitWert.setAlignment(Pos.CENTER);
        btnMaxGesundheitPlus = new Button();
        btnMaxGesundheitPlus.setGraphic(imgBtnPlus);
        btnMaxGesundheitPlus.setOnAction(event -> trainerController.attributAendern("maxGesundheit", true));
        btnMaxGesundheitMinus = new Button();
        btnMaxGesundheitMinus.setGraphic(imgBtnMinus);
        btnMaxGesundheitMinus.setOnAction(event -> trainerController.attributAendern("maxGesundheit", false));
        cointainerMaxGesundheitspunkteplusminus.getChildren().addAll(btnMaxGesundheitMinus,btnMaxGesundheitPlus);
        containerMaxGesundheitspunkte.setSpacing(50.0);
        containerMaxGesundheitspunkte.getChildren().addAll(lblMaxGesundheit, lblMaxGesundheitWert,cointainerMaxGesundheitspunkteplusminus);
        //Container Max ManaPunkte
        HBox containerMaxManaPunkte = new HBox();
        lblMaxMana = new Label();
        lblMaxManaWert = new Label();
        btnMaxManaPlus = new Button("+");
        btnMaxManaPlus.setOnAction(event -> trainerController.attributAendern("maxMana", true));
        btnMaxManaMinus = new Button("-");
        btnMaxManaMinus.setOnAction(event -> trainerController.attributAendern("maxMana", false));
        containerMaxManaPunkte.getChildren().addAll(lblMaxMana, lblMaxManaWert, btnMaxManaPlus, btnMaxManaMinus);
        //Container Physische Attacke
        HBox containerPhysischeAttacke = new HBox();
        lblPhysischeAttacke = new Label();
        lblPhysischeAttackeWert = new Label();
        btnPhysischeAttackePlus = new Button("+");
        btnPhysischeAttackePlus.setOnAction(event -> trainerController.attributAendern("physischeAttacke", true));
        btnPhysischeAttackeMinus = new Button("-");
        btnPhysischeAttackeMinus.setOnAction(event -> trainerController.attributAendern("physischeAttacke", false));
        containerPhysischeAttacke.getChildren().addAll(lblPhysischeAttacke, lblPhysischeAttackeWert, btnPhysischeAttackePlus, btnPhysischeAttackeMinus);
        //Container Magische Attacke
        HBox containerMagischeAttacke = new HBox();
        lblMagischeAttacke = new Label();
        lblMagischeAttackeWert = new Label();
        btnMagischeAttackePlus = new Button("+");
        btnMagischeAttackePlus.setOnAction(event -> trainerController.attributAendern("MagischeAttacke", true));
        btnMagischeAttackeMinus = new Button("-");
        btnMagischeAttackeMinus.setOnAction(event -> trainerController.attributAendern("MagischeAttacke", false));
        containerMagischeAttacke.getChildren().addAll(lblMagischeAttacke, lblMagischeAttackeWert, btnMagischeAttackePlus, btnMagischeAttackeMinus);
        //Container Genauigkeit
        HBox containerGenauigkeit = new HBox();
        lblGenauigkeit = new Label();
        lblGenauigkeitWert = new Label();
        btnGenauigkeitPlus = new Button("+");
        btnGenauigkeitPlus.setOnAction(event -> trainerController.attributAendern("genauigkeit", true));
        btnGenauigkeitMinus = new Button("-");
        btnGenauigkeitMinus.setOnAction(event -> trainerController.attributAendern("genauigkeit", false));
        containerGenauigkeit.getChildren().addAll(lblGenauigkeit, lblGenauigkeitWert, btnGenauigkeitPlus, btnGenauigkeitMinus);
        //Container Verteidigung
        HBox containerVerteidigung = new HBox();
        lblVerteidigung = new Label();
        lblVerteidigungWert = new Label();
        btnVerteidigungPlus = new Button("+");
        btnVerteidigungPlus.setOnAction(event -> trainerController.attributAendern("verteidigung", true));
        btnVerteidigungMinus = new Button("-");
        btnVerteidigungMinus.setOnAction(event -> trainerController.attributAendern("verteidigung", false));
        containerVerteidigung.getChildren().addAll(lblVerteidigung, lblVerteidigungWert, btnVerteidigungPlus, btnVerteidigungMinus);
        //Container Magische Verteidigung
        HBox containerMagischeVerteidigung = new HBox();
        lblMagischeVerteidigung = new Label();
        lblMagischeVerteidigungWert = new Label();
        btnMagischeVerteidigungPlus = new Button("+");
        btnMagischeVerteidigungPlus.setOnAction(event -> trainerController.attributAendern("magischeVerteidigung", true));
        btnMagischeVerteidigungMinus = new Button("-");
        btnMagischeVerteidigungMinus.setOnAction(event -> trainerController.attributAendern("magischeVerteidigung", false));
        containerMagischeVerteidigung.getChildren().addAll(lblMagischeVerteidigung, lblMagischeVerteidigungWert, btnMagischeVerteidigungPlus, btnMagischeVerteidigungMinus);
        //Container Resistenz
        HBox containerResistenz = new HBox();
        lblResistenz = new Label();
        lblResistenzWert = new Label();
        btnResistenzPlus = new Button("+");
        btnResistenzPlus.setOnAction(event -> trainerController.attributAendern("resistenz", true));
        btnResistenzMinus = new Button("-");
        btnResistenzMinus.setOnAction(event -> trainerController.attributAendern("resistenz", false));
        containerResistenz.getChildren().addAll(lblResistenz, lblResistenzWert, btnResistenzPlus, btnResistenzMinus);
        //Container Beweglichkeit
        HBox containerBeweglichkeit = new HBox();
        lblBeweglichkeit = new Label();
        lblBeweglichkeitWert = new Label();
        btnBeweglichkeitPlus = new Button("+");
        btnBeweglichkeitPlus.setOnAction(event -> trainerController.attributAendern("beweglichkeit", true));
        btnBeweglichkeitMinus = new Button("-");
        btnBeweglichkeitMinus.setOnAction(event -> trainerController.attributAendern("beweglichkeit", false));
        containerBeweglichkeit.getChildren().addAll(lblBeweglichkeit, lblBeweglichkeitWert, btnBeweglichkeitPlus, btnBeweglichkeitMinus);

        //styleKlassenZuweisen
        titel.getStyleClass().add("trainerAttributeTitel");
        //Container
        containerMaxGesundheitspunkte.getStyleClass().add("trainerAttributeContainer");
        containerMaxManaPunkte.getStyleClass().add("trainerAttributeContainer");
        containerPhysischeAttacke.getStyleClass().add("trainerAttributeContainer");
        containerMagischeAttacke.getStyleClass().add("trainerAttributeContainer");
        containerGenauigkeit.getStyleClass().add("trainerAttributeContainer");
        containerVerteidigung.getStyleClass().add("trainerAttributeContainer");
        containerMagischeVerteidigung.getStyleClass().add("trainerAttributeContainer");
        containerResistenz.getStyleClass().add("trainerAttributeContainer");
        containerBeweglichkeit.getStyleClass().add("trainerAttributeContainer");
        //Buttons
        btnMaxGesundheitPlus.getStyleClass().add("trainerAttributeButton");
        btnMaxManaPlus.getStyleClass().add("trainerAttributeButton");
        btnPhysischeAttackePlus.getStyleClass().add("trainerAttributeButton");
        btnMagischeAttackePlus.getStyleClass().add("trainerAttributeButton");
        btnMagischeAttackePlus.getStyleClass().add("trainerAttributeButton");
        btnGenauigkeitPlus.getStyleClass().add("trainerAttributeButton");
        btnVerteidigungPlus.getStyleClass().add("trainerAttributeButton");
        btnMagischeVerteidigungPlus.getStyleClass().add("trainerAttributeButton");
        btnResistenzPlus.getStyleClass().add("trainerAttributeButton");
        btnBeweglichkeitPlus.getStyleClass().add("trainerAttributeButton");
        btnMaxGesundheitMinus.getStyleClass().add("trainerAttributeButton");
        btnMaxManaMinus.getStyleClass().add("trainerAttributeButton");
        btnPhysischeAttackeMinus.getStyleClass().add("trainerAttributeButton");
        btnMagischeAttackeMinus.getStyleClass().add("trainerAttributeButton");
        btnGenauigkeitMinus.getStyleClass().add("trainerAttributeButton");
        btnVerteidigungMinus.getStyleClass().add("trainerAttributeButton");
        btnMagischeVerteidigungMinus.getStyleClass().add("trainerAttributeButton");
        btnResistenzMinus.getStyleClass().add("trainerAttributeButton");
        btnBeweglichkeitMinus.getStyleClass().add("trainerAttributeButton");
        //Label
        offeneAttributsPunkte.getStyleClass().add("trainerAttributeLabel");
        lblMaxGesundheit.getStyleClass().add("trainerAttributeLabel");
        lblMaxGesundheitWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblMaxMana.getStyleClass().add("trainerAttributeLabel");
        lblMaxManaWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblPhysischeAttacke.getStyleClass().add("trainerAttributeLabel");
        lblPhysischeAttackeWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblMagischeAttacke.getStyleClass().add("trainerAttributeLabel");
        lblMagischeAttackeWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblGenauigkeit.getStyleClass().add("trainerAttributeLabel");
        lblGenauigkeitWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblVerteidigung.getStyleClass().add("trainerAttributeLabel");
        lblVerteidigungWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblMagischeVerteidigung.getStyleClass().add("trainerAttributeLabel");
        lblMagischeVerteidigungWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblResistenz.getStyleClass().add("trainerAttributeLabel");
        lblResistenzWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblBeweglichkeit.getStyleClass().add("trainerAttributeLabel");
        lblBeweglichkeitWert.getStyleClass().add("trainerAttributeLabelWerte");
        //Die Komponenten der VBox hinzufügen
        this.getChildren().addAll(titel, offeneAttributsPunkte, containerMaxGesundheitspunkte, containerMaxManaPunkte, containerPhysischeAttacke, containerMagischeAttacke, containerGenauigkeit, containerVerteidigung, containerMagischeVerteidigung, containerResistenz, containerBeweglichkeit);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5.0);
        this.setMaxWidth(1536.0);
        this.setBackground(TrainerController.setzeTrainerHintergrund());
    }

    /**
     * Anzeige vorbereiten.
     * Wegen der Aktualisierung im menü
     */
    public void anzeigeVorbereiten() {
        int offeneAttributpunkte = trainerController.getAktuellerCharakter().getOffeneAttributpunkte();
        // Überschrift / Titel / OffeneAttributsPunkte
        offeneAttributsPunkte.setText(" Name :" + trainerController.getAktuellerCharakter().getName() + "\nOffene Attributspunkte : " + offeneAttributpunkte);

        // Es folgen die einzelnen Label
        lblMaxGesundheit.setText("Max. Gesundheitspunkte");
        lblMaxGesundheitWert.setText(String.valueOf(trainerController.getAktuellerCharakter().getMaxGesundheitsPunkte()));

        lblMaxMana.setText("Max. Manapunkte");
        lblMaxManaWert.setText(String.valueOf(trainerController.getAktuellerCharakter().getMaxManaPunkte()));

        lblPhysischeAttacke.setText("Physische Attacke");
        lblPhysischeAttackeWert.setText(String.valueOf(trainerController.getAktuellerCharakter().getPhysischeAttacke()));

        lblMagischeAttacke.setText("Magische Attacke");
        lblMagischeAttackeWert.setText(String.valueOf(trainerController.getAktuellerCharakter().getMagischeAttacke()));

        lblGenauigkeit.setText("Genauigkeit");
        lblGenauigkeitWert.setText(String.valueOf(trainerController.getAktuellerCharakter().getGenauigkeit()));

        lblVerteidigung.setText("Verteidigung");
        lblVerteidigungWert.setText(String.valueOf(trainerController.getAktuellerCharakter().getVerteidigung()));

        lblMagischeVerteidigung.setText("Magische Verteidigung");
        lblMagischeVerteidigungWert.setText(String.valueOf(trainerController.getAktuellerCharakter().getPhysischeAttacke()));

        lblResistenz.setText("Resistenz");
        lblResistenzWert.setText(String.valueOf(trainerController.getAktuellerCharakter().getPhysischeAttacke()));

        lblBeweglichkeit.setText("Beweglichkeit");
        lblBeweglichkeitWert.setText(String.valueOf(trainerController.getAktuellerCharakter().getPhysischeAttacke()));
        // Plus Knöpfe
        btnMaxGesundheitPlus.setDisable(offeneAttributpunkte <= 0);
        btnMaxManaPlus.setDisable(offeneAttributpunkte <= 0);
        btnPhysischeAttackePlus.setDisable(offeneAttributpunkte <= 0);
        btnMagischeAttackePlus.setDisable(offeneAttributpunkte <= 0);
        btnMagischeAttackePlus.setDisable(offeneAttributpunkte <= 0);
        btnGenauigkeitPlus.setDisable(offeneAttributpunkte <= 0);
        btnVerteidigungPlus.setDisable(offeneAttributpunkte <= 0);
        btnMagischeVerteidigungPlus.setDisable(offeneAttributpunkte <= 0);
        btnResistenzPlus.setDisable(offeneAttributpunkte <= 0);
        btnBeweglichkeitPlus.setDisable(offeneAttributpunkte <= 0);
        //Minus Knöpfe
        btnMaxGesundheitMinus.setDisable(trainerController.getAktuellerCharakter().getMaxGesundheitsPunkte() <= 1);
        btnMaxManaMinus.setDisable(trainerController.getAktuellerCharakter().getMaxManaPunkte() <= 0);
        btnPhysischeAttackeMinus.setDisable(trainerController.getAktuellerCharakter().getPhysischeAttacke() <= 0);
        btnMagischeAttackeMinus.setDisable(trainerController.getAktuellerCharakter().getMagischeAttacke() <= 0);
        btnGenauigkeitMinus.setDisable(trainerController.getAktuellerCharakter().getGenauigkeit() <= 0);
        btnVerteidigungMinus.setDisable(trainerController.getAktuellerCharakter().getVerteidigung() <= 0);
        btnMagischeVerteidigungMinus.setDisable(trainerController.getAktuellerCharakter().getMagischeVerteidigung() <= 0);
        btnResistenzMinus.setDisable(trainerController.getAktuellerCharakter().getResistenz() <= 0);
        btnBeweglichkeitMinus.setDisable(trainerController.getAktuellerCharakter().getBeweglichkeit() <= 0);
    }

}
