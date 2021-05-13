package no.ntnu.mappe3.marko.zipCodeRegister.inheritanceTask;

/**
 * Class UKAddress represents an address in the United Kingdom
 * It stores the extra parameters and returns the
 * address as a String in the correct format
 *
 * @author Marko
 * @version 12-05-2021
 */
public class UKAddress extends Address implements Printable
{
    /**
     * The name of the county
     */
    private final String county;

    /**
     * Constructor for a UKAddress
     * @param streetName  The name of the street, not null or blank
     * @param houseNumber The house number as a String, not null or blank
     * @param town        The town name, not null or blank
     * @param county      The name of the county, not null or blank
     * @param zipCode     The zip code as a String, not null or blank
     * @throws IllegalArgumentException If one of the parameters is null or blank
     */
    public UKAddress(String streetName, String houseNumber, String town, String county, String zipCode)
    {
        super(streetName, houseNumber, town, zipCode);

        if (county == null) {
            throw new IllegalArgumentException("county can not be null");
        }
        if (county.isBlank()) {
            throw new IllegalArgumentException("county can not be blank");
        }

        this.county = county;
    }

    /**
     * Returns the county name
     * @return The county name
     */
    public String getCounty()
    {
        return this.county;
    }

    @Override
    public String getAsString()
    {
        return super.getHouseNumber() + " " + super.getStreetName() + LINE_SEPARATOR
                + super.getTown() + LINE_SEPARATOR
                + this.getCounty() + LINE_SEPARATOR
                + super.getZipCode();
    }
}
