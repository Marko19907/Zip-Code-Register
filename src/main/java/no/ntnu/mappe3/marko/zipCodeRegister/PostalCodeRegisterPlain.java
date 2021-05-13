package no.ntnu.mappe3.marko.zipCodeRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class PostalCodeRegisterPlain is a simple register that can hold PostalCodes in memory.
 *
 * @author Marko
 * @version 13-05-2021
 */
public class PostalCodeRegisterPlain implements PostalCodeRegister
{
    /**
     * The HashMap containing the PostalCodes
     */
    private final HashMap<String, PostalCode> postalCodes;

    /**
     * Constructor for PostalCodeRegisterPlain objects
     */
    public PostalCodeRegisterPlain()
    {
        this.postalCodes = new HashMap<>();
    }

    @Override
    public void addPostalCode(PostalCode postalCode)
    {
        if (postalCode != null && !this.postalCodes.containsKey(postalCode.getZipCode())) {
            this.postalCodes.put(postalCode.getZipCode(), postalCode);
        }
    }

    @Override
    public void addPostalCodes(List<PostalCode> postalCodes)
    {
        if (postalCodes != null) {
            postalCodes.forEach(this::addPostalCode);
        }
    }

    @Override
    public List<PostalCode> getPostalCodes()
    {
        return new ArrayList<>(this.postalCodes.values());
    }

    @Override
    public List<PostalCode> getPostalCodesByTown(String searchString)
    {
        if (searchString == null || searchString.isBlank()) {
            throw new IllegalArgumentException("The given search String can not be null or blank!");
        }

        return this.postalCodes.values().stream()
                .filter(postalCode ->
                        postalCode.getTownName().toLowerCase().trim().contains(searchString.toLowerCase().trim()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostalCode> getPostalCodesByZip(String searchString)
    {
        if (searchString == null || searchString.isBlank()) {
            throw new IllegalArgumentException("The given search String can not be null or blank!");
        }

        return this.postalCodes.values().stream()
                .filter(postalCode ->
                        postalCode.getZipCode().toLowerCase().trim().contains(searchString.toLowerCase().trim()))
                .collect(Collectors.toList());
    }

    @Override
    public int getPostalCodesSize()
    {
        return this.postalCodes.size();
    }
}
