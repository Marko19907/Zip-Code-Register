package no.ntnu.mappe3.marko.zipCodeRegister.inheritanceTask;

/**
 * Class NorwegianAddress represents an address in Norway
 * It stores the required parameters and returns the
 * address as a String in the correct format
 *
 * @author Marko
 * @version 12-05-2021
 */
public class NorwegianAddress extends Address implements Printable
{
    /**
     * Constructor for a NorwegianAddress
     * @param streetName  The name of the street, not null or blank
     * @param houseNumber The house number as a String, not null or blank
     * @param town        The town name, not null or blank
     * @param zipCode     The zip code as a String, not null or blank
     * @throws IllegalArgumentException If one of the parameters is null or blank
     */
    public NorwegianAddress(String streetName, String houseNumber, String town, String zipCode)
    {
        super(streetName, houseNumber, town, zipCode);
    }

    @Override
    public String getAsString()
    {
        return super.getStreetName() + " " + super.getHouseNumber() + LINE_SEPARATOR
                + super.getZipCode() + " " + super.getTown();
    }
}
