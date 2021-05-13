package no.ntnu.mappe3.marko.zipCodeRegister;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Class Controller represents the main controller of the application.
 * It is responsible for handling the events from the GUI.
 *
 * @author Marko
 * @version 13-05-2021
 */
public class Controller
{
    /**
     * The PostalCodeRegister for adding, storing and reading PostalCodes
     */
    private final PostalCodeRegister postalCodeRegister;

    /**
     * ObservableList of the PostalCodes
     */
    private final ObservableList<PostalCode> postalCodeObservableList;

    /**
     * Instantiates the controller.
     */
    public Controller()
    {
        this.postalCodeRegister = new PostalCodeRegisterPlain();
        this.loadPostalCodeData();
        this.postalCodeObservableList = FXCollections.observableArrayList(this.postalCodeRegister.getPostalCodes());

        //this.fillWithDemoEntities();
    }

    /**
     * Loads the data from the txt file of PostalCodes into the register
     */
    private void loadPostalCodeData()
    {
        TXTFileReader fileReader = new TXTFileReader();
        List<PostalCode> postalCodesList = fileReader.loadData();
        if (postalCodesList != null) {
            this.postalCodeRegister.addPostalCodes(postalCodesList);
        }
    }

    /**
     * Fills the register with demo data
     */
    private void fillWithDemoEntities()
    {
        this.postalCodeRegister
                .addPostalCode(new PostalCode("TestTown1", "TestName1", "1234"));
        this.postalCodeRegister
                .addPostalCode(new PostalCode("TestTown2", "TestName2", "4321"));
        this.postalCodeRegister
                .addPostalCode(new PostalCode("TestTown3", "TestName3", "6789"));

        this.updateObservableList();
    }

    /**
     * Updates the observable list of PostalCodes with fresh values from the register
     */
    private void updateObservableList()
    {
        this.postalCodeObservableList.setAll(this.postalCodeRegister.getPostalCodes());
    }

    /**
     * Returns an ObservableList of PostalCodes to show
     * @return An ObservableList of PostalCodes to show
     */
    public ObservableList<PostalCode> getPostalCodeObservableList()
    {
        return this.postalCodeObservableList;
    }

    /**
     * Displays only the PostalCodes that contain the given String in the table
     * @param searchString The zip String to search with, not null
     */
    public void doSearchByZip(String searchString)
    {
        if (searchString != null) {
            if (searchString.isBlank()) {
                this.updateObservableList();
            }
            else {
                this.postalCodeObservableList
                        .setAll(this.postalCodeRegister.getPostalCodesByZip(searchString));
            }
        }
    }

    /**
     * Displays only the PostalCodes that contain the given String in the table
     * @param searchString The town String to search with, not null
     */
    public void doSearchByTown(String searchString)
    {
        if (searchString != null) {
            if (searchString.isBlank()) {
                this.updateObservableList();
            }
            else {
                this.postalCodeObservableList
                        .setAll(this.postalCodeRegister.getPostalCodesByTown(searchString));
            }
        }
    }
}
