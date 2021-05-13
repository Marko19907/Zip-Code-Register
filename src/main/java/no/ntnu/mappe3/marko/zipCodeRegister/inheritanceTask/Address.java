package no.ntnu.mappe3.marko.zipCodeRegister.inheritanceTask;

/**
 * Class Address represents a generic address template for other classes to extend
 * It is responsible for storing only the most important info about a generic address,
 * the street name, house number, town or city and zip code
 *
 * @author Marko
 * @version 12-05-2021
 */
public abstract class Address
{
    /**
     * The street name as a String
     */
    private final String streetName;

    /**
     * The house number as a String
     */
    private final String houseNumber;

    /**
     * The town or city as a String
     */
    private final String town;

    /**
     * The zip code as a String
     */
    private final String zipCode;

    /**
     * Constructs an Address with the least amount of information required
     * @param streetName  The name of the street, not null or blank
     * @param houseNumber The house number as a String, not null or blank
     * @param town        The town name, not null or blank
     * @param zipCode     The zip code as a String, not null or blank
     * @throws IllegalArgumentException If one of the parameters is null or blank
     */
    protected Address(String streetName, String houseNumber, String town, String zipCode)
    {
        if (streetName == null || houseNumber == null || town == null || zipCode == null) {
            throw new IllegalArgumentException("streetName, houseNumber, town or zipCode can not be null");
        }
        if (streetName.isBlank() || houseNumber.isBlank() || town.isBlank() || zipCode.isBlank()) {
            throw new IllegalArgumentException("streetName, houseNumber, town or zipCode can not be blank");
        }

        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.town = town;
        this.zipCode = zipCode;
    }

    /**
     * Returns the street name
     * @return The street name
     */
    public String getStreetName()
    {
        return this.streetName;
    }

    /**
     * Returns the house number as a String
     * @return The house number
     */
    public String getHouseNumber()
    {
        return this.houseNumber;
    }

    /**
     * Returns the town name
     * @return The town name
     */
    public String getTown()
    {
        return this.town;
    }

    /**
     * Returns the zip code as a String
     * @return The zip code as a String
     */
    public String getZipCode()
    {
        return this.zipCode;
    }
}
