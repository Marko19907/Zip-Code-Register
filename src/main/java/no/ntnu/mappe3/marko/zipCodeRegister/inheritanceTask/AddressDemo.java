package no.ntnu.mappe3.marko.zipCodeRegister.inheritanceTask;

/**
 * Class AddressDemo represents a very simple client that is used to the test the inheritance task
 *
 * @author Marko
 * @version 13-05-2021
 */
public class AddressDemo
{
    /**
     * The main method
     */
    public static void main(String[] args)
    {
        System.out.println("US address: ");
        System.out.println(
                new USAddress("Anyville RD NW", "47", "Anytown", "AZ", "01234")
                        .getAsString()
        );
        System.out.println();


        System.out.println("UK address: ");
        System.out.println(
                new UKAddress("Anyville RD", "47", "Reading", "Berkshire", "RG1 1AT")
                        .getAsString()
        );
        System.out.println();


        System.out.println("Norwegian address: ");
        System.out.println(
                new NorwegianAddress("Korsegata", "34", "Ålesund", "6002")
                        .getAsString()
        );
        System.out.println();
    }
}
