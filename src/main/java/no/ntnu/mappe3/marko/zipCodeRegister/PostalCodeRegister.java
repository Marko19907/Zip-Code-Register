package no.ntnu.mappe3.marko.zipCodeRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class PostalCodeRegister is a simple register that can hold PostalCodes in memory.
 *
 * @author Marko
 * @version 10-05-2021
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
     * Returns a List of all postal codes in the register
     * @return A List of all postal codes in the register
     */
    public List<PostalCode> getPostalCodes()
    {
        return new ArrayList<>(this.postalCodes.values());
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
