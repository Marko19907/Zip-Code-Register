package no.ntnu.mappe3.marko.zipCodeRegister;

import java.util.List;

/**
 * PostalCodeRegister represents an interface for adding, reading and searching operations
 *
 * @author Marko
 * @version 13-05-2021
 */
public interface PostalCodeRegister
{
    /**
     * Adds a given PostalCode to the register
     * @param postalCode The PostalCode to add, not null
     */
    void addPostalCode(PostalCode postalCode);

    /**
     * Adds a given List of PostalCodes to the register
     * @param postalCodes The PostalCode List to add, not null
     */
    void addPostalCodes(List<PostalCode> postalCodes);

    /**
     * Returns a List of all postal codes in the register
     * @return A List of all postal codes in the register
     */
    List<PostalCode> getPostalCodes();

    /**
     * Returns a List of postal codes that contain the given town name
     * @param searchString The town name to search with, not null or blank
     * @return A List of postal codes that contain the given town name
     * @throws IllegalArgumentException If the given searchString is null or blank
     */
    List<PostalCode> getPostalCodesByTown(String searchString);

    /**
     * Returns a List of postal codes that contain the given zip String
     * @param searchString The zip String to search with, not null or blank
     * @return A List of postal codes that contain the given zip String
     * @throws IllegalArgumentException If the given searchString is null or blank
     */
    List<PostalCode> getPostalCodesByZip(String searchString);

    /**
     * Returns the number of PostalCodes in the register
     * @return The number of PostalCodes in the register as an int
     */
    int getPostalCodesSize();
}
