package no.ntnu.mappe3.marko.zipCodeRegister;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class Controller represents the main controller of the application.
 * It is responsible for handling the events from the GUI.
 *
 * @author Marko
 * @version 10-05-2021
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
        this.postalCodeRegister = new PostalCodeRegister();
        this.postalCodeObservableList = FXCollections.observableArrayList(this.postalCodeRegister.getPostalCodes());

        this.fillWithDemoEntities();
    }

    /**
     * Fills the register with demo data
     */
    private void fillWithDemoEntities()
    {
        this.postalCodeRegister.addPostalCode(new PostalCode("TestName1", "1234"));
        this.postalCodeRegister.addPostalCode(new PostalCode("TestName2", "4321"));
        this.postalCodeRegister.addPostalCode(new PostalCode("TestName3", "6789"));

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
}
