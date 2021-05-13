package no.ntnu.mappe3.marko.zipCodeRegister;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;

/**
 * Class PostalCodeRegisterGUI represents the main window of the application.
 *
 * @author Marko
 * @version 13-05-2021
 */
public class PostalCodeRegisterGUI extends Application
{
    /**
     * The main controller
     */
    private final Controller controller;

    /**
     * The TableView that displays PostalCodes
     */
    private final TableView<PostalCode> postalCodeTableView;


    /**
     * PostalCodeRegisterGUI constructor
     */
    public PostalCodeRegisterGUI()
    {
        this.controller = new Controller();
        this.postalCodeTableView = new TableView<>();
    }

    /**
     * The main method
     * @param args The command-line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        BorderPane root = new BorderPane();
        root.setCenter(this.setupCenter());
        root.setBottom(this.setupBottomLabel());

        primaryStage.setTitle("Postal Code Register");
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(600);

        Scene scene = new Scene(root, 500, 600, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();

        root.requestFocus();
    }

    /**
     * Returns the center VBox
     * @return The center VBox
     */
    private VBox setupCenter()
    {
        VBox centerBox = new VBox();
        centerBox.getChildren().addAll(this.setupTopToolBox(), this.setupCenterTable());
        VBox.setVgrow(centerBox.getChildren().get(1), Priority.ALWAYS);
        return centerBox;
    }

    /**
     * Returns up the table that contains the PostalCodes
     * @return An already set-up table that contains the PostalCodes
     */
    private TableView<PostalCode> setupCenterTable()
    {
        this.postalCodeTableView.setPlaceholder(new Label("No zip codes to display"));
        this.postalCodeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<PostalCode, String> zipColumn = new TableColumn<>("Zip");
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        zipColumn.setStyle("-fx-alignment: CENTER;");
        zipColumn.setMinWidth(50);
        zipColumn.setMaxWidth(50);

        TableColumn<PostalCode, String> townColumn = new TableColumn<>("City / Town");
        townColumn.setCellValueFactory(new PropertyValueFactory<>("townName"));

        TableColumn<PostalCode, String> municipalityColumn = new TableColumn<>("Municipality");
        municipalityColumn.setCellValueFactory(new PropertyValueFactory<>("municipalityName"));

        this.postalCodeTableView.getColumns().addAll(Arrays.asList(zipColumn, townColumn, municipalityColumn));
        this.postalCodeTableView.setItems(this.controller.getPostalCodeObservableList());
        //Set a default sort column
        this.postalCodeTableView.getSortOrder().add(zipColumn);

        return this.postalCodeTableView;
    }

    /**
     * Sets up the top ToolBar
     * @return The top ToolBar
     */
    private ToolBar setupTopToolBox()
    {
        ToolBar toolBar = new ToolBar();
        toolBar.setPadding(new Insets(5, 5, 5, 5));

        final String byZip = "By zip";
        final String byTown = "By city / town";
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(byZip, byTown);
        choiceBox.setValue(byZip);

        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (oldValue!= null && newValue != null) {
                switch (choiceBox.getValue()) {
                    case byZip:
                        // Check if the input is not a digit (0-9)
                        if (!newValue.matches("\\d*")) {
                            searchField.setText(oldValue);
                        }
                        else {
                            this.controller.doSearchByZip(newValue);
                        }
                        break;
                    case byTown:
                        this.controller.doSearchByTown(newValue);
                        break;
                }
                this.refreshTable();
            }
        });

        // Clear the text in the search TextField when the search parameter changes
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null && newValue != null && !oldValue.equals(newValue)) {
                searchField.clear();
                this.refreshTable();
            }
        });

        toolBar.getItems().addAll(choiceBox, searchField);
        return toolBar;
    }

    /**
     * Sets up the bottom HBox
     * @return The bottom HBox
     */
    private HBox setupBottomLabel()
    {
        HBox bottomBox = new HBox();
        int padding = 2;
        bottomBox.setPadding(new Insets(padding, padding, padding, padding));
        bottomBox.setStyle("-fx-background-color: #d5d5d5");
        Label descriptionLabel = new Label("Entities shown: ");

        Label sizeLabel = new Label();
        sizeLabel.textProperty().bind(Bindings.size(this.controller.getPostalCodeObservableList()).asString());

        bottomBox.getChildren().addAll(descriptionLabel, sizeLabel);
        return bottomBox;
    }

    /**
     * Refreshes the table and forces a sort of the data.
     */
    private void refreshTable()
    {
        this.postalCodeTableView.refresh();
        this.postalCodeTableView.sort();
    }
}
