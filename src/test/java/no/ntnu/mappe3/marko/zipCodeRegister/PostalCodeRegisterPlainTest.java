package no.ntnu.mappe3.marko.zipCodeRegister;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class PostalCodeRegisterPlainTest
{
    /**
     * A valid PostalCode to use for testing
     */
    private static final PostalCode POSTAL_CODE_1 =
            new PostalCode("town", "municipality1", "1234");

    /**
     * A valid PostalCode to use for testing
     */
    private static final PostalCode POSTAL_CODE_2 =
            new PostalCode("city", "municipality2", "4321");

    @Test
    @DisplayName("Positive, test adding a valid postal code")
    public void testAddingPostalCode()
    {
        // Arrange
        PostalCodeRegister register = new PostalCodeRegisterPlain();

        // Act
        register.addPostalCode(POSTAL_CODE_1);
        register.addPostalCode(POSTAL_CODE_2);

        // Assert
        assertEquals(2, register.getPostalCodesSize());
        assertTrue(register.getPostalCodes().stream().anyMatch(postalCode -> postalCode.equals(POSTAL_CODE_1)));
        assertTrue(register.getPostalCodes().stream().anyMatch(postalCode -> postalCode.equals(POSTAL_CODE_2)));
    }

    @Test
    @DisplayName("Negative, test adding a null postal code to the register")
    public void testAddingNull()
    {
        // Arrange
        PostalCodeRegister register = new PostalCodeRegisterPlain();

        // Act
        register.addPostalCode(null);

        // Assert
        assertEquals(0, register.getPostalCodesSize());
        assertTrue(register.getPostalCodes().isEmpty());
    }

    @Test
    @DisplayName("Negative, test adding duplicate postal codes")
    public void testAddingDuplicatePostalCodes()
    {
        // Arrange
        PostalCodeRegister register = new PostalCodeRegisterPlain();

        // Act
        register.addPostalCode(POSTAL_CODE_1);
        register.addPostalCode(POSTAL_CODE_1);

        // Assert
        assertEquals(1, register.getPostalCodesSize());
        assertTrue(register.getPostalCodes().stream().anyMatch(postalCode -> postalCode.equals(POSTAL_CODE_1)));
    }

    @Test
    @DisplayName("Positive, test adding a List of valid postal codes")
    public void testAddingPostalCodesList()
    {
        // Arrange
        PostalCodeRegister register = new PostalCodeRegisterPlain();
        List<PostalCode> postalCodeList = new ArrayList<>();
        postalCodeList.add(POSTAL_CODE_1);
        postalCodeList.add(POSTAL_CODE_2);

        // Act
        register.addPostalCodes(postalCodeList);

        // Assert
        assertEquals(2, register.getPostalCodesSize());
        assertTrue(register.getPostalCodes().stream().anyMatch(postalCode -> postalCode.equals(POSTAL_CODE_1)));
        assertTrue(register.getPostalCodes().stream().anyMatch(postalCode -> postalCode.equals(POSTAL_CODE_2)));
    }

    @Test
    @DisplayName("Negative, test adding a List with duplicate postal codes")
    public void testAddingPostalCodesListWithDuplicates()
    {
        // Arrange
        PostalCodeRegister register = new PostalCodeRegisterPlain();
        List<PostalCode> postalCodeList = new ArrayList<>();
        postalCodeList.add(POSTAL_CODE_1);
        postalCodeList.add(POSTAL_CODE_1);

        // Act
        register.addPostalCodes(postalCodeList);

        // Assert
        assertEquals(1, register.getPostalCodesSize());
        assertTrue(register.getPostalCodes().stream().anyMatch(postalCode -> postalCode.equals(POSTAL_CODE_1)));
    }

    @Test
    @DisplayName("Negative, test adding a List with null postal codes")
    public void testAddingPostalCodesListWithNull()
    {
        // Arrange
        PostalCodeRegister register = new PostalCodeRegisterPlain();
        List<PostalCode> postalCodeList = new ArrayList<>();
        postalCodeList.add(null);

        // Act
        register.addPostalCodes(postalCodeList);

        // Assert
        assertEquals(0, register.getPostalCodesSize());
    }

    @Test
    @DisplayName("Negative, test adding a null List")
    public void testAddingPostalCodesNullList()
    {
        // Arrange
        PostalCodeRegister register = new PostalCodeRegisterPlain();

        // Act
        register.addPostalCodes(null);

        // Assert
        assertEquals(0, register.getPostalCodesSize());
    }

    @Test
    @DisplayName("Positive, test searching by town name")
    public void testSearchingByTown()
    {
        // Arrange
        PostalCodeRegister register = new PostalCodeRegisterPlain();
        register.addPostalCode(POSTAL_CODE_1);
        register.addPostalCode(POSTAL_CODE_2);

        // Act
        List<PostalCode> postalCodesFound = register.getPostalCodesByTown("Town");

        // Assert
        assertEquals(1, postalCodesFound.size());
        assertTrue(postalCodesFound.stream().anyMatch(postalCode -> postalCode.equals(POSTAL_CODE_1)));
    }

    @Test
    @DisplayName("Negative, test searching by town name with a null argument")
    public void testSearchingByTownWithNull()
    {
        // Arrange
        PostalCodeRegister register = new PostalCodeRegisterPlain();
        boolean exceptionThrown = false;

        try {
            // Act
            register.getPostalCodesByTown(null);
            fail("An IllegalArgumentException should have been thrown");
        }
        catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        // Assert
        assertTrue(exceptionThrown);
    }

    @Test
    @DisplayName("Negative, test searching by town name with a blank String")
    public void testSearchingByTownWithBlankString()
    {
        // Arrange
        PostalCodeRegister register = new PostalCodeRegisterPlain();
        boolean exceptionThrown = false;

        try {
            // Act
            register.getPostalCodesByTown("");
            fail("An IllegalArgumentException should have been thrown");
        }
        catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        // Assert
        assertTrue(exceptionThrown);
    }

    @Test
    @DisplayName("Positive, test searching by zip")
    public void testSearchingByZip()
    {
        // Arrange
        PostalCodeRegister register = new PostalCodeRegisterPlain();
        register.addPostalCode(POSTAL_CODE_1);
        register.addPostalCode(POSTAL_CODE_2);

        // Act
        List<PostalCode> postalCodesFound = register.getPostalCodesByZip("4321");

        // Assert
        assertEquals(1, postalCodesFound.size());
        assertTrue(postalCodesFound.stream().anyMatch(postalCode -> postalCode.equals(POSTAL_CODE_2)));
    }

    @Test
    @DisplayName("Negative, test searching by zip code with a null argument")
    public void testSearchingByZipWithNull()
    {
        // Arrange
        PostalCodeRegister register = new PostalCodeRegisterPlain();
        boolean exceptionThrown = false;

        try {
            // Act
            register.getPostalCodesByZip(null);
            fail("An IllegalArgumentException should have been thrown");
        }
        catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        // Assert
        assertTrue(exceptionThrown);
    }

    @Test
    @DisplayName("Negative, test searching by zip code with a blank String")
    public void testSearchingByZipWithBlankString()
    {
        // Arrange
        PostalCodeRegister register = new PostalCodeRegisterPlain();
        boolean exceptionThrown = false;

        try {
            // Act
            register.getPostalCodesByZip("");
            fail("An IllegalArgumentException should have been thrown");
        }
        catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        // Assert
        assertTrue(exceptionThrown);
    }
}