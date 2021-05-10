package no.ntnu.mappe3.marko.zipCodeRegister;

/**
 * The class PostalCode represents a custom data type (entity)
 * that can hold a name of a municipality and it's corresponding zip code
 *
 * @author Marko
 * @version 10-05-2021
 */
public class PostalCode
{
    /**
     * The town name as a String
     */
    private final String townName;

    /**
     * The municipality name as a String
     */
    private final String municipalityName;

    /**
     * The zip code as a String
     */
    private final String zipCode;

    /**
     * Constructor for PostalCode objects
     * @param townName         The name of the city/town, not null or blank
     * @param municipalityName The name of the municipality as a String, not null or blank
     * @param zipCode          The zip code as a String, not null or blank
     * @throws IllegalArgumentException If one of the given Strings is null or blank
     */
    public PostalCode(String townName, String municipalityName, String zipCode)
    {
        if (townName == null || municipalityName == null || zipCode == null) {
            throw new IllegalArgumentException(
                    "townName, municipalityName or zipCode can not be null!");
        }
        if (townName.isBlank() || municipalityName.isBlank() || zipCode.isBlank()) {
            throw new IllegalArgumentException(
                    "townName, municipalityName or zipCode can not be blank!");
        }

        this.townName = townName;
        this.municipalityName = municipalityName;
        this.zipCode = zipCode;
    }

    /**
     * Returns the town/city name as a String
     * @return The town/city name as a String
     */
    public String getTownName()
    {
        return this.townName;
    }

    /**
     * Returns the municipality name as a String
     * @return The municipality name
     */
    public String getMunicipalityName()
    {
        return this.municipalityName;
    }

    /**
     * Returns the zip code as a String
     * @return The zip code
     */
    public String getZipCode()
    {
        return this.zipCode;
    }
}
