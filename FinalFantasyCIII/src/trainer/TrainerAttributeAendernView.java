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
        HBox cointainerMaxGesundheitspunktePlusMinus = new HBox();

        lblMaxGesundheit = new Label();
        lblMaxGesundheit.setAlignment(Pos.CENTER);
        lblMaxGesundheitWert = new Label();
        lblMaxGesundheitWert.setAlignment(Pos.CENTER);

        btnMaxGesundheitPlus = new Button();
        btnMaxGesundheitPlus.setGraphic(new ImageView(imgPlus));
        btnMaxGesundheitPlus.setOnAction(event -> trainerController.attributAendern("maxGesundheit", true));

        btnMaxGesundheitMinus = new Button();
        btnMaxGesundheitMinus.setGraphic(imgBtnMinus);
        btnMaxGesundheitMinus.setOnAction(event -> trainerController.attributAendern("maxGesundheit", false));

        cointainerMaxGesundheitspunktePlusMinus.getChildren().addAll(btnMaxGesundheitMinus,btnMaxGesundheitPlus);
        containerMaxGesundheitspunkte.setSpacing(50.0);
        containerMaxGesundheitspunkte.getChildren().addAll(lblMaxGesundheit, lblMaxGesundheitWert,cointainerMaxGesundheitspunktePlusMinus);
        //Container Max ManaPunkte
        HBox containerMaxManaPunkte = new HBox();
        HBox containerMaxManaPunktePlusMinus = new HBox();

        lblMaxMana = new Label();
        lblMaxMana.setAlignment(Pos.CENTER);
        lblMaxManaWert = new Label();
        lblMaxManaWert.setAlignment(Pos.CENTER);

        btnMaxManaPlus = new Button();
        btnMaxManaPlus.setGraphic(new ImageView(imgPlus));
        btnMaxManaPlus.setOnAction(event -> trainerController.attributAendern("maxMana", true));

        btnMaxManaMinus = new Button();
        btnMaxManaMinus.setGraphic(new ImageView(imgMinus));
        btnMaxManaMinus.setOnAction(event -> trainerController.attributAendern("maxMana", false));

        containerMaxManaPunktePlusMinus.getChildren().addAll(btnMaxManaMinus,btnMaxManaPlus);
        containerMaxManaPunkte.setSpacing(50);
        containerMaxManaPunkte.getChildren().addAll(lblMaxMana, lblMaxManaWert,containerMaxManaPunktePlusMinus);
        //Container Physische Attacke
        HBox containerPhysischeAttacke = new HBox();
        HBox containerPhysischeAttackePlusMinus = new HBox();

        lblPhysischeAttacke = new Label();
        lblPhysischeAttacke.setAlignment(Pos.CENTER);

        lblPhysischeAttackeWert = new Label();
        lblPhysischeAttackeWert.setAlignment(Pos.CENTER);

        btnPhysischeAttackePlus = new Button();
        btnPhysischeAttackePlus.setGraphic(new ImageView(imgPlus));
        btnPhysischeAttackePlus.setOnAction(event -> trainerController.attributAendern("physischeAttacke", true));

        btnPhysischeAttackeMinus = new Button();
        btnPhysischeAttackeMinus.setGraphic(new ImageView(imgMinus));
        btnPhysischeAttackeMinus.setOnAction(event -> trainerController.attributAendern("physischeAttacke", false));

        containerPhysischeAttackePlusMinus.getChildren().addAll(btnPhysischeAttackeMinus,btnPhysischeAttackePlus);
        containerPhysischeAttacke.setSpacing(50);
        containerPhysischeAttacke.getChildren().addAll(lblPhysischeAttacke, lblPhysischeAttackeWert, containerPhysischeAttackePlusMinus);
        //Container Magische Attacke
        HBox containerMagischeAttacke = new HBox();
        HBox containerMagischeAttackePlusMinus = new HBox();

        lblMagischeAttacke = new Label();
        lblMagischeAttackeWert = new Label();

        btnMagischeAttackePlus = new Button();
        btnMagischeAttackePlus.setGraphic(new ImageView(imgPlus));
        btnMagischeAttackePlus.setOnAction(event -> trainerController.attributAendern("MagischeAttacke", true));

        btnMagischeAttackeMinus = new Button();
        btnMagischeAttackeMinus.setGraphic(new ImageView(imgMinus));
        btnMagischeAttackeMinus.setOnAction(event -> trainerController.attributAendern("MagischeAttacke", false));

        containerMagischeAttackePlusMinus.getChildren().addAll(btnMagischeAttackeMinus,btnMagischeAttackePlus);
        containerMagischeAttacke.setSpacing(50);
        containerMagischeAttacke.getChildren().addAll(lblMagischeAttacke, lblMagischeAttackeWert,containerMagischeAttackePlusMinus);
        //Container Genauigkeit
        HBox containerGenauigkeit = new HBox();
        HBox containerGenauigkeitPlusMinus = new HBox();

        lblGenauigkeit = new Label();
        lblGenauigkeitWert = new Label();

        btnGenauigkeitPlus = new Button();
        btnGenauigkeitPlus.setGraphic(new ImageView(imgPlus));
        btnGenauigkeitPlus.setOnAction(event -> trainerController.attributAendern("genauigkeit", true));

        btnGenauigkeitMinus = new Button();
        btnGenauigkeitMinus.setGraphic(new ImageView(imgMinus));
        btnGenauigkeitMinus.setOnAction(event -> trainerController.attributAendern("genauigkeit", false));

        containerGenauigkeitPlusMinus.getChildren().addAll(btnGenauigkeitMinus,btnGenauigkeitPlus);
        containerGenauigkeit.setSpacing(50);
        containerGenauigkeit.getChildren().addAll(lblGenauigkeit, lblGenauigkeitWert,containerGenauigkeitPlusMinus);

        //Container Verteidigung
        HBox containerVerteidigung = new HBox();
        HBox containerVerteidigungPlusMinus = new HBox();

        lblVerteidigung = new Label();
        lblVerteidigung.setAlignment(Pos.CENTER);

        lblVerteidigungWert = new Label();
        lblVerteidigungWert.setAlignment(Pos.CENTER);

        btnVerteidigungPlus = new Button();
        btnVerteidigungPlus.setGraphic(new ImageView(imgPlus));
        btnVerteidigungPlus.setOnAction(event -> trainerController.attributAendern("verteidigung", true));

        btnVerteidigungMinus = new Button();
        btnVerteidigungMinus.setGraphic(new ImageView(imgMinus));
        btnVerteidigungMinus.setOnAction(event -> trainerController.attributAendern("verteidigung", false));

        containerVerteidigungPlusMinus.getChildren().addAll(btnVerteidigungMinus,btnVerteidigungPlus);
        containerVerteidigung.setSpacing(50);
        containerVerteidigung.getChildren().addAll(lblVerteidigung, lblVerteidigungWert,containerVerteidigungPlusMinus);
        //Container Magische Verteidigung
        HBox containerMagischeVerteidigung = new HBox();
        HBox containerMagischeVerteidigungPlusMinus = new HBox();

        lblMagischeVerteidigung = new Label();
        lblMagischeVerteidigung.setAlignment(Pos.CENTER);

        lblMagischeVerteidigungWert = new Label();
        lblMagischeVerteidigungWert.setAlignment(Pos.CENTER);

        btnMagischeVerteidigungPlus = new Button();
        btnMagischeVerteidigungPlus.setGraphic(new ImageView(imgPlus));
        btnMagischeVerteidigungPlus.setOnAction(event -> trainerController.attributAendern("magischeVerteidigung", true));

        btnMagischeVerteidigungMinus = new Button();
        btnMagischeVerteidigungMinus.setGraphic(new ImageView(imgMinus));
        btnMagischeVerteidigungMinus.setOnAction(event -> trainerController.attributAendern("magischeVerteidigung", false));

        containerMagischeVerteidigungPlusMinus.getChildren().addAll(btnMagischeVerteidigungMinus,btnMagischeVerteidigungPlus);
        containerMagischeVerteidigung.setSpacing(50);
        containerMagischeVerteidigung.getChildren().addAll(lblMagischeVerteidigung, lblMagischeVerteidigungWert,containerMagischeVerteidigungPlusMinus);

        //Container Resistenz
        HBox containerResistenz = new HBox();
        HBox containerResistenzPlusMinus = new HBox();

        lblResistenz = new Label();
        lblResistenz.setAlignment(Pos.CENTER);

        lblResistenzWert = new Label();
        lblResistenzWert.setAlignment(Pos.CENTER);

        btnResistenzPlus = new Button();
        btnResistenzPlus.setGraphic(new ImageView(imgPlus));
        btnResistenzPlus.setOnAction(event -> trainerController.attributAendern("resistenz", true));

        btnResistenzMinus = new Button();
        btnResistenzMinus.setGraphic(new ImageView(imgMinus));
        btnResistenzMinus.setOnAction(event -> trainerController.attributAendern("resistenz", false));

        containerResistenzPlusMinus.getChildren().addAll(btnResistenzMinus,btnResistenzPlus);
        containerResistenz.setSpacing(50);
        containerResistenz.getChildren().addAll(lblResistenz, lblResistenzWert,containerResistenzPlusMinus);

        //Container Beweglichkeit
        HBox containerBeweglichkeit = new HBox();
        HBox containerBeweglichkeitPlusMinus = new HBox();

        lblBeweglichkeit = new Label();
        lblBeweglichkeit.setAlignment(Pos.CENTER);

        lblBeweglichkeitWert = new Label();
        lblBeweglichkeitWert.setAlignment(Pos.CENTER);

        btnBeweglichkeitPlus = new Button();
        btnBeweglichkeitPlus.setGraphic(new ImageView(imgPlus));
        btnBeweglichkeitPlus.setOnAction(event -> trainerController.attributAendern("beweglichkeit", true));

        btnBeweglichkeitMinus = new Button();
        btnBeweglichkeitMinus.setGraphic(new ImageView(imgMinus));
        btnBeweglichkeitMinus.setOnAction(event -> trainerController.attributAendern("beweglichkeit", false));

        containerBeweglichkeitPlusMinus.getChildren().addAll(btnBeweglichkeitMinus,btnBeweglichkeitPlus);
        containerBeweglichkeit.setSpacing(50);
        containerBeweglichkeit.getChildren().addAll(lblBeweglichkeit, lblBeweglichkeitWert,containerBeweglichkeitPlusMinus);

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
        cointainerMaxGesundheitspunktePlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerMaxManaPunktePlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerPhysischeAttackePlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerMagischeAttackePlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerGenauigkeitPlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerVerteidigungPlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerMagischeVerteidigungPlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerResistenzPlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerBeweglichkeitPlusMinus.getStyleClass().add("trainerAttributeContainer");

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
        this.getStyleClass().add("trainerStyle");
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
        lblMagischeVerteidigungWert.setText(String.valueOf(trainerController.getAktuellerCharakter().getMagischeVerteidigung()));

        lblResistenz.setText("Resistenz");
        lblResistenzWert.setText(String.valueOf(trainerController.getAktuellerCharakter().getResistenz()));

        lblBeweglichkeit.setText("Beweglichkeit");
        lblBeweglichkeitWert.setText(String.valueOf(trainerController.getAktuellerCharakter().getBeweglichkeit()));
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
