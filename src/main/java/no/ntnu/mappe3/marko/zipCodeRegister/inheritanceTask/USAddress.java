package no.ntnu.mappe3.marko.zipCodeRegister.inheritanceTask;

/**
 * Class USAddress represents an address in the United States
 * It stores the extra parameters and returns the
 * address as a String in the correct format
 *
 * @author Marko
 * @version 12-05-2021
 */
public class USAddress extends Address implements Printable
{
    /**
     * The name of the state
     */
    private final String state;

    /**
     * Constructor for a USAddress
     * @param streetName  The name of the street, not null or blank
     * @param houseNumber The house number as a String, not null or blank
     * @param town        The town name, not null or blank
     * @param state       The state name, it's two-letter postal abbreviation will suffice, not null or blank
     * @param zipCode     The zip code as a String, not null or blank
     * @throws IllegalArgumentException If one of the parameters is null or blank
     */
    public USAddress(String streetName, String houseNumber, String town, String state, String zipCode)
    {
        super(streetName, houseNumber, town, zipCode);

        if (state == null) {
            throw new IllegalArgumentException("state can not be null");
        }
        if (state.isBlank()) {
            throw new IllegalArgumentException("state can not be blank");
        }

        this.state = state;
    }

    /**
     * Returns the state
     * @return The state
     */
    public String getState()
    {
        return this.state;
    }

    @Override
    public String getAsString()
    {
        return super.getHouseNumber() + " " + super.getStreetName() + LINE_SEPARATOR
                + super.getTown() + " " + this.getState() + " " + super.getZipCode();
    }
}
