package no.ntnu.mappe3.marko.zipCodeRegister.inheritanceTask;

/**
 * The Printable interface allows classes that implement it
 * to print out their own address in their specified format
 *
 * @author Marko
 * @version 12-05-2021
 */
public interface Printable
{
    /**
     * The line separator
     */
    String LINE_SEPARATOR = "\n";

    /**
     * Returns the address in the format of the current country as a multiline String
     * @return The address in the format of the current country
     */
    String getAsString();
}
