package no.ntnu.mappe3.marko.zipCodeRegister;

import javafx.application.Application;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;

/**
 * Class PostalCodeRegisterGUI represents the main window of the application.
 *
 * @author Marko
 * @version 10-05-2021
 */
public class PostalCodeRegisterGUI extends Application
{
    /**
     * The main controller
     */
    private final Controller controller;

    /**
     * PostalCodeRegisterGUI constructor
     */
    public PostalCodeRegisterGUI()
    {
        this.controller = new Controller();
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
        TableView<PostalCode> postalCodeTableView = new TableView<>();
        postalCodeTableView.setPlaceholder(new Label("No zip codes to display"));
        postalCodeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<PostalCode, String> zipColumn = new TableColumn<>("Zip");
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));

        TableColumn<PostalCode, String> townColumn = new TableColumn<>("City / Town");
        townColumn.setCellValueFactory(new PropertyValueFactory<>("townName"));

        TableColumn<PostalCode, String> municipalityColumn = new TableColumn<>("Municipality");
        municipalityColumn.setCellValueFactory(new PropertyValueFactory<>("municipalityName"));

        postalCodeTableView.getColumns().addAll(Arrays.asList(zipColumn, townColumn, municipalityColumn));
        postalCodeTableView.setItems(this.controller.getPostalCodeObservableList());
        //Set a default sort column
        postalCodeTableView.getSortOrder().add(zipColumn);

        return postalCodeTableView;
    }

    /**
     * Sets up the top ToolBar
     * @return The top ToolBar
     */
    private ToolBar setupTopToolBox()
    {
        ToolBar toolBar = new ToolBar();
        toolBar.setPadding(new Insets(5, 5, 5, 5));

        String[] choices = {
                "By zip",
                "By city / town"
        };
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(choices);
        choiceBox.setValue(choices[0]);

        TextField textField = new TextField();
        textField.setPromptText("Search");
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (choiceBox.getValue()) {
                case "By zip":
                    if (newValue != null) {
                        this.controller.doSearchByZip(newValue);
                    }
                    break;
                case "By city / town":
                    if (newValue != null) {
                        this.controller.doSearchByTown(newValue);
                    }
                    break;
            }
        });

        toolBar.getItems().addAll(choiceBox, textField);
        return toolBar;
    }
}