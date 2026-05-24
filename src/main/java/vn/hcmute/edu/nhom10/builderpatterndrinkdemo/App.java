package vn.hcmute.edu.nhom10.builderpatterndrinkdemo;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class App extends Application {
    private static final String IMAGE_ROOT = "/vn/hcmute/edu/nhom10/builderpatterndrinkdemo/images/";
    private static final double PREVIEW_WIDTH = 220;
    private static final double PREVIEW_HEIGHT = 280;

    // Vietnamese string constants using Unicode escapes
    private static final String TRA_SUA = "Trà sữa";
    private static final String CA_PHE = "Cà phê";
    private static final String TRAN_CHAU_DEN = "Trân châu đen";
    private static final String THACH_PHO_MAI = "Thạch phô mai";
    private static final String KHONG_CO = "Không có";

    // --- Form controls ---
    private ComboBox<String> drinkTypeComboBox;
    private ToggleGroup sizeGroup;
    private Slider sugarSlider;
    private Slider iceSlider;
    private Label sugarValueLabel;
    private Label iceValueLabel;
    private CheckBox pearlCheckBox;
    private CheckBox cheeseJellyCheckBox;
    private CheckBox puddingCheckBox;
    private TextField noteTextField;

    // --- Display areas ---
    private StackPane previewPane;
    private Label summaryName;
    private Label summarySize;
    private Label summarySugar;
    private Label summaryIce;
    private FlowPane summaryToppings;
    private Label summaryNote;

    @Override
    public void start(Stage stage) {
        HBox header = createHeader();
        VBox leftPanel = createLeftPanel();
        VBox centerPanel = createCenterPanel();
        VBox rightPanel = createRightPanel();

        ScrollPane leftScroll = new ScrollPane(leftPanel);
        leftScroll.setFitToWidth(true);
        leftScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        leftScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        leftScroll.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        HBox mainContent = new HBox(16, leftScroll, centerPanel, rightPanel);
        mainContent.setPadding(new Insets(16));
        HBox.setHgrow(rightPanel, Priority.ALWAYS);

        VBox root = new VBox(0, header, mainContent);
        VBox.setVgrow(mainContent, Priority.ALWAYS);
        root.setStyle("-fx-background-color: #1A1A2E;");

        registerPreviewListeners();
        refreshAll();

        Scene scene = new Scene(root, 1150, 720);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        stage.setTitle("Builder Pattern - Ứng Dụng Đặt Đồ Uống");
        stage.setScene(scene);
        stage.setMinWidth(950);
        stage.setMinHeight(600);
        stage.show();
    }

    // ==================== HEADER ====================

    private HBox createHeader() {
        Label title = new Label("☕  BUILDER PATTERN");
        title.getStyleClass().add("header-title");

        Label subtitle = new Label("ỨNG DỤNG ĐẶT ĐỒ UỐNG");
        subtitle.getStyleClass().add("header-subtitle");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox header = new HBox(12, title, spacer, subtitle);
        header.setAlignment(Pos.CENTER_LEFT);
        header.getStyleClass().add("header-bar");
        return header;
    }

    // ==================== LEFT PANEL (Form) ====================

    private VBox createLeftPanel() {
        VBox panel = new VBox(12);
        panel.setPrefWidth(270);
        panel.setMinWidth(250);

        Label panelTitle = new Label("TÙY CHỈNH ĐỒ UỐNG");
        panelTitle.getStyleClass().add("panel-title");

        panel.getChildren().addAll(
                panelTitle,
                createDrinkTypeCard(),
                createSizeCard(),
                createSugarCard(),
                createIceCard(),
                createToppingCard(),
                createNoteCard());
        return panel;
    }

    private VBox createDrinkTypeCard() {
        drinkTypeComboBox = new ComboBox<>();
        drinkTypeComboBox.getItems().addAll(TRA_SUA, CA_PHE);
        drinkTypeComboBox.setValue(TRA_SUA);
        drinkTypeComboBox.setMaxWidth(Double.MAX_VALUE);
        return createSectionCard("LOẠI ĐỒ UỐNG", drinkTypeComboBox);
    }

    private VBox createSizeCard() {
        sizeGroup = new ToggleGroup();

        ToggleButton sBtn = new ToggleButton("S");
        ToggleButton mBtn = new ToggleButton("M");
        ToggleButton lBtn = new ToggleButton("L");

        sBtn.setToggleGroup(sizeGroup);
        mBtn.setToggleGroup(sizeGroup);
        lBtn.setToggleGroup(sizeGroup);
        mBtn.setSelected(true);

        sBtn.getStyleClass().addAll("size-btn", "size-btn-first");
        mBtn.getStyleClass().add("size-btn");
        lBtn.getStyleClass().addAll("size-btn", "size-btn-last");

        sBtn.setUserData("S");
        mBtn.setUserData("M");
        lBtn.setUserData("L");

        // Prevent deselection
        sizeGroup.selectedToggleProperty().addListener((obs, old, newVal) -> {
            if (newVal == null)
                old.setSelected(true);
        });

        HBox sizeBox = new HBox(0, sBtn, mBtn, lBtn);
        sizeBox.setAlignment(Pos.CENTER);
        return createSectionCard("KÍCH THƯỚC", sizeBox);
    }

    private VBox createSugarCard() {
        sugarSlider = createSlider();
        sugarSlider.getStyleClass().add("sugar-slider");

        sugarValueLabel = new Label("50%");
        sugarValueLabel.getStyleClass().addAll("slider-value", "sugar-value");

        HBox row = new HBox(10, sugarSlider, sugarValueLabel);
        row.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(sugarSlider, Priority.ALWAYS);
        return createSectionCard("MỨC ĐƯỜNG", row);
    }

    private VBox createIceCard() {
        iceSlider = createSlider();
        iceSlider.getStyleClass().add("ice-slider");

        iceValueLabel = new Label("50%");
        iceValueLabel.getStyleClass().addAll("slider-value", "ice-value");

        HBox row = new HBox(10, iceSlider, iceValueLabel);
        row.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(iceSlider, Priority.ALWAYS);
        return createSectionCard("MỨC ĐÁ", row);
    }

    private VBox createToppingCard() {
        pearlCheckBox = new CheckBox(TRAN_CHAU_DEN);
        cheeseJellyCheckBox = new CheckBox(THACH_PHO_MAI);
        puddingCheckBox = new CheckBox("Pudding");

        VBox toppings = new VBox(8, pearlCheckBox, cheeseJellyCheckBox, puddingCheckBox);
        return createSectionCard("TOPPING", toppings);
    }

    private VBox createNoteCard() {
        noteTextField = new TextField();
        noteTextField.setPromptText("Nhập ghi chú...");
        noteTextField.setMaxWidth(Double.MAX_VALUE);
        return createSectionCard("GHI CHÚ", noteTextField);
    }

    // ==================== CENTER PANEL (Preview) ====================

    private VBox createCenterPanel() {
        Label panelTitle = new Label("XEM TRƯỚC");
        panelTitle.getStyleClass().add("panel-title");

        previewPane = new StackPane();
        previewPane.setPrefSize(300, 360);
        previewPane.setMinSize(300, 360);
        previewPane.getStyleClass().add("preview-pane");

        VBox panel = new VBox(12, panelTitle, previewPane);
        panel.setAlignment(Pos.TOP_CENTER);
        panel.setPrefWidth(320);
        return panel;
    }

    // ==================== RIGHT PANEL (Summary + Code) ====================

    private VBox createRightPanel() {
        Label panelTitle = new Label("KẾT QUẢ");
        panelTitle.getStyleClass().add("panel-title");

        VBox summaryCard = createSummaryCard();
        HBox buttons = createButtons();

        VBox panel = new VBox(12, panelTitle, summaryCard, buttons);
        panel.setMinWidth(300);
        return panel;
    }

    private VBox createSummaryCard() {
        Label title = new Label("THÔNG TIN ĐỒ UỐNG");
        title.getStyleClass().add("summary-card-title");

        summaryName = new Label("-");
        summaryName.getStyleClass().add("summary-value");
        summarySize = new Label("-");
        summarySize.getStyleClass().add("summary-value");
        summarySugar = new Label("-");
        summarySugar.getStyleClass().add("summary-value");
        summarySugar.setStyle("-fx-text-fill: #F5A623;");
        summaryIce = new Label("-");
        summaryIce.getStyleClass().add("summary-value");
        summaryIce.setStyle("-fx-text-fill: #4FC3F7;");
        summaryNote = new Label("-");
        summaryNote.getStyleClass().add("summary-value");
        summaryNote.setStyle("-fx-font-style: italic;");
        summaryNote.setWrapText(true);

        summaryToppings = new FlowPane(6, 6);
        summaryToppings.setPrefWrapLength(200);

        VBox card = new VBox(6,
                title,
                createSummaryRow("Tên đồ uống", summaryName),
                createDivider(),
                createSummaryRow("Kích thước", summarySize),
                createDivider(),
                createSummaryRow("Mức đường", summarySugar),
                createDivider(),
                createSummaryRow("Mức đá", summaryIce),
                createDivider(),
                createSummaryRowVert("Topping", summaryToppings),
                createDivider(),
                createSummaryRowVert("Ghi chú", summaryNote));
        card.getStyleClass().add("summary-card");
        return card;
    }

    private HBox createSummaryRow(String key, Label value) {
        Label keyLabel = new Label(key);
        keyLabel.getStyleClass().add("summary-key");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox row = new HBox(8, keyLabel, spacer, value);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }

    private VBox createSummaryRowVert(String key, Node value) {
        Label keyLabel = new Label(key);
        keyLabel.getStyleClass().add("summary-key");
        return new VBox(4, keyLabel, value);
    }

    private Region createDivider() {
        Region divider = new Region();
        divider.getStyleClass().add("summary-divider");
        divider.setMaxHeight(1);
        divider.setPrefHeight(1);
        return divider;
    }

    private HBox createButtons() {
        Button buildBtn = new Button("Tạo đồ uống");
        buildBtn.getStyleClass().add("build-btn");
        buildBtn.setMaxWidth(Double.MAX_VALUE);
        buildBtn.setOnAction(e -> buildDrink());
        HBox.setHgrow(buildBtn, Priority.ALWAYS);

        Button defaultBtn = new Button("Tạo mẫu mặc định");
        defaultBtn.getStyleClass().add("default-btn");
        defaultBtn.setMaxWidth(Double.MAX_VALUE);
        defaultBtn.setOnAction(e -> buildDefaultDrink());
        HBox.setHgrow(defaultBtn, Priority.ALWAYS);

        return new HBox(10, buildBtn, defaultBtn);
    }

    // ==================== HELPERS ====================

    private VBox createSectionCard(String title, Node content) {
        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("section-title");
        VBox card = new VBox(8, titleLabel, content);
        card.getStyleClass().add("card");
        return card;
    }

    private Slider createSlider() {
        Slider slider = new Slider(0, 100, 50);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);
        slider.setBlockIncrement(25);
        slider.setSnapToTicks(true);
        return slider;
    }

    // ==================== LISTENERS ====================

    private void registerPreviewListeners() {
        drinkTypeComboBox.setOnAction(e -> refreshAllAnimated());

        sizeGroup.selectedToggleProperty().addListener((obs, old, newVal) -> {
            if (newVal != null) {
                refreshAll();
                playScaleAnimation(previewPane);
            }
        });

        sugarSlider.valueProperty().addListener((obs, old, newVal) -> {
            sugarValueLabel.setText(String.format("%.0f%%", newVal.doubleValue()));
            refreshAll();
        });

        iceSlider.valueProperty().addListener((obs, old, newVal) -> {
            iceValueLabel.setText(String.format("%.0f%%", newVal.doubleValue()));
            refreshAll();
        });

        pearlCheckBox.setOnAction(e -> refreshAll());
        cheeseJellyCheckBox.setOnAction(e -> refreshAll());
        puddingCheckBox.setOnAction(e -> refreshAll());

        noteTextField.textProperty().addListener((obs, old, newVal) -> refreshAll());
    }

    // ==================== REFRESH ====================

    private void refreshAll() {
        Drink drink = createDrinkFromForm();
        updateDrinkPreview(drink);
        updateSummary(drink);
    }

    private void refreshAllAnimated() {
        Drink drink = createDrinkFromForm();
        playFadeAnimation(previewPane, () -> updateDrinkPreview(drink));
        updateSummary(drink);
    }

    private void updateSummary(Drink drink) {
        summaryName.setText(drink.getName());
        summarySize.setText(drink.getSize());
        summarySugar.setText(drink.getSugar() + "%");
        summaryIce.setText(drink.getIce() + "%");

        summaryToppings.getChildren().clear();
        List<String> toppings = drink.getToppings();
        if (toppings.isEmpty()) {
            Label none = new Label(KHONG_CO);
            none.getStyleClass().add("summary-value");
            none.setStyle("-fx-font-style: italic; -fx-text-fill: #5a6f8a;");
            summaryToppings.getChildren().add(none);
        } else {
            for (String t : toppings) {
                Label tag = new Label(t);
                tag.getStyleClass().add("topping-tag");
                summaryToppings.getChildren().add(tag);
            }
        }

        String note = drink.getNote();
        summaryNote.setText(note == null || note.isBlank() ? KHONG_CO : note);
        if (note == null || note.isBlank()) {
            summaryNote.setStyle("-fx-font-style: italic; -fx-text-fill: #5a6f8a;");
        } else {
            summaryNote.setStyle("-fx-font-style: italic; -fx-text-fill: #EAEAEA;");
        }
    }

    // ==================== BUSINESS LOGIC ====================

    private String getSelectedSize() {
        Toggle selected = sizeGroup.getSelectedToggle();
        return selected != null ? (String) selected.getUserData() : "M";
    }

    private Drink createDrinkFromForm() {
        DrinkBuilder builder;
        String drinkType = drinkTypeComboBox.getValue();

        if (TRA_SUA.equals(drinkType)) {
            builder = new MilkTeaBuilder();
        } else {
            builder = new CoffeeBuilder();
        }

        builder.setSize(getSelectedSize())
                .setSugar((int) sugarSlider.getValue())
                .setIce((int) iceSlider.getValue())
                .setNote(noteTextField.getText());

        if (pearlCheckBox.isSelected())
            builder.addTopping(TRAN_CHAU_DEN);
        if (cheeseJellyCheckBox.isSelected())
            builder.addTopping(THACH_PHO_MAI);
        if (puddingCheckBox.isSelected())
            builder.addTopping("Pudding");

        return builder.build();
    }

    private void buildDrink() {
        Drink drink = createDrinkFromForm();
        updateDrinkPreview(drink);
        updateSummary(drink);
        playScaleAnimation(previewPane);
    }

    private void buildDefaultDrink() {
        DrinkDirector director = new DrinkDirector();
        Drink drink;
        String drinkType = drinkTypeComboBox.getValue();

        if (TRA_SUA.equals(drinkType)) {
            drink = director.makeDefaultMilkTea();
        } else {
            drink = director.makeDefaultCoffee();
        }

        setFormFromDrink(drink);

        updateDrinkPreview(drink);
        updateSummary(drink);
        playScaleAnimation(previewPane);
    }

    private void setFormFromDrink(Drink drink) {
        for (Toggle t : sizeGroup.getToggles()) {
            if (drink.getSize().equals(t.getUserData())) {
                t.setSelected(true);
                break;
            }
        }

        sugarSlider.setValue(drink.getSugar());
        iceSlider.setValue(drink.getIce());
        sugarValueLabel.setText(drink.getSugar() + "%");
        iceValueLabel.setText(drink.getIce() + "%");

        List<String> toppings = drink.getToppings();
        pearlCheckBox.setSelected(toppings.contains(TRAN_CHAU_DEN));
        cheeseJellyCheckBox.setSelected(toppings.contains(THACH_PHO_MAI));
        puddingCheckBox.setSelected(toppings.contains("Pudding"));

        String note = drink.getNote();
        noteTextField.setText(note != null ? note : "");
    }

    // ==================== PREVIEW RENDERING ====================

    private void updateDrinkPreview(Drink drink) {
        previewPane.getChildren().clear();

        double scale = getSizeScale(drink.getSize());
        double width = PREVIEW_WIDTH * scale;
        double height = PREVIEW_HEIGHT * scale;
        List<String> missingLayers = new ArrayList<>();

        addImageLayer(getBaseImagePath(drink), width, height, missingLayers);
        addImageLayer("sugar/sugar-" + getNearestLevel(drink.getSugar()) + ".png", width, height, missingLayers);

        for (String topping : drink.getToppings()) {
            String path = getToppingImagePath(topping);
            if (path != null)
                addImageLayer(path, width, height, missingLayers);
        }

        addImageLayer("ice/ice-" + getNearestLevel(drink.getIce()) + ".png", width, height, missingLayers);

        if (!missingLayers.isEmpty()) {
            Label lbl = new Label("Thiếu ảnh:\n" + String.join("\n", missingLayers));
            lbl.setStyle(
                    "-fx-font-size: 10px; -fx-text-fill: #E94560; -fx-background-color: rgba(0,0,0,0.7); -fx-padding: 6; -fx-background-radius: 6;");
            lbl.setWrapText(true);
            lbl.setMaxWidth(240);
            previewPane.getChildren().add(lbl);
        }
    }

    private void addImageLayer(String imagePath, double width, double height, List<String> missingLayers) {
        URL imageResource = App.class.getResource(IMAGE_ROOT + imagePath);
        if (imageResource == null) {
            missingLayers.add(imagePath);
            return;
        }
        Image image = new Image(imageResource.toExternalForm(), width, height, true, true);
        ImageView iv = new ImageView(image);
        iv.setFitWidth(width);
        iv.setFitHeight(height);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        previewPane.getChildren().add(iv);
    }

    private String getBaseImagePath(Drink drink) {
        return TRA_SUA.equals(drink.getName()) ? "base/milk-tea.png" : "base/coffee.png";
    }

    private String getToppingImagePath(String topping) {
        return switch (topping) {
            case "Trân châu đen" -> "toppings/black-pearl.png";
            case "Thạch phô mai" -> "toppings/cheese-jelly.png";
            case "Pudding" -> "toppings/pudding.png";
            default -> null;
        };
    }

    private int getNearestLevel(int value) {
        int clamped = Math.clamp(value, 0, 100);
        return Math.round(clamped / 25.0f) * 25;
    }

    private double getSizeScale(String size) {
        return switch (size) {
            case "S" -> 0.85;
            case "L" -> 1.15;
            default -> 1.0;
        };
    }

    // ==================== ANIMATIONS ====================

    private void playFadeAnimation(Node node, Runnable onFinish) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(150), node);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.3);
        fadeOut.setOnFinished(e -> {
            if (onFinish != null)
                onFinish.run();
            FadeTransition fadeIn = new FadeTransition(Duration.millis(250), node);
            fadeIn.setFromValue(0.3);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        fadeOut.play();
    }

    private void playScaleAnimation(Node node) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), node);
        st.setFromX(0.95);
        st.setFromY(0.95);
        st.setToX(1.0);
        st.setToY(1.0);
        st.play();
    }
}
