package no.ntnu.mappe3.marko.zipCodeRegister;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
     * Returns the center HBox
     * @return The center HBox
     */
    private HBox setupCenter()
    {
        HBox centerBox = new HBox();
        centerBox.getChildren().add(this.setupCenterTable());
        HBox.setHgrow(centerBox.getChildren().get(0), Priority.ALWAYS);
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

        TableColumn<PostalCode, String> municipalityColumn = new TableColumn<>("Municipality");
        municipalityColumn.setCellValueFactory(new PropertyValueFactory<>("municipalityName"));

        postalCodeTableView.getColumns().addAll(Arrays.asList(zipColumn, municipalityColumn));
        postalCodeTableView.setItems(this.controller.getPostalCodeObservableList());
        //Set a default sort column
        postalCodeTableView.getSortOrder().add(zipColumn);

        return postalCodeTableView;
    }
}
