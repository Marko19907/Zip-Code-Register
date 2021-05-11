package no.ntnu.mappe3.marko.zipCodeRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class PostalCodeRegister is a simple register that can hold PostalCodes in memory.
 *
 * @author Marko
 * @version 11-05-2021
 */
public class PostalCodeRegister
{
    /**
     * The HashMap containing the PostalCodes
     */
    private final HashMap<String, PostalCode> postalCodes;

    /**
     * Constructor for PostalCodeRegister objects
     */
    public PostalCodeRegister()
    {
        this.postalCodes = new HashMap<>();
    }

    /**
     * Adds a given PostalCode to the register
     * @param postalCode The PostalCode to add, not null
     */
    public void addPostalCode(PostalCode postalCode)
    {
        if (postalCode != null && !this.postalCodes.containsKey(postalCode.getZipCode())) {
            this.postalCodes.put(postalCode.getZipCode(), postalCode);
        }
    }

    /**
     * Adds a given List of PostalCodes to the register
     * @param postalCodes The PostalCode List to add, not null
     */
    public void addPostalCodes(List<PostalCode> postalCodes)
    {
        if (postalCodes != null) {
            postalCodes.forEach(this::addPostalCode);
        }
    }

    /**
     * Returns a List of all postal codes in the register
     * @return A List of all postal codes in the register
     */
    public List<PostalCode> getPostalCodes()
    {
        return new ArrayList<>(this.postalCodes.values());
    }

    /**
     * Returns a List of postal codes that contain the given town name
     * @param searchString The town name to search with, not null or blank
     * @return A List of postal codes that contain the given town name
     * @throws IllegalArgumentException If the given searchString is null or blank
     */
    public List<PostalCode> getPostalCodesByTown(String searchString)
    {
        if (searchString == null || searchString.isBlank()) {
            throw new IllegalArgumentException("The given search String can not be null or blank!");
        }

        return this.postalCodes.values().stream()
                .filter(postalCode ->
                        postalCode.getTownName().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Returns a List of postal codes that contain the given zip String
     * @param searchString The zip String to search with, not null or blank
     * @return A List of postal codes that contain the given zip String
     * @throws IllegalArgumentException If the given searchString is null or blank
     */
    public List<PostalCode> getPostalCodesByZip(String searchString)
    {
        if (searchString == null || searchString.isBlank()) {
            throw new IllegalArgumentException("The given search String can not be null or blank!");
        }

        return this.postalCodes.values().stream()
                .filter(postalCode ->
                        postalCode.getZipCode().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Returns the number of PostalCodes in the register
     * @return The number of PostalCodes in the register as an int
     */
    public int getPostalCodesSize()
    {
        return this.postalCodes.size();
    }
}
