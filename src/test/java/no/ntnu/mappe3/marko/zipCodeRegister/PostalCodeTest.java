package no.ntnu.mappe3.marko.zipCodeRegister;

import no.ntnu.mappe3.marko.zipCodeRegister.model.PostalCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class PostalCodeTest
{
    @Test
    @DisplayName("Positive, test the constructor with valid values")
    public void testConstructor()
    {
        // Arrange
        String townName = "testTown";
        String zipCode = "1234";
        String municipalityName = "testMunicipality";

        // Act
        PostalCode postalCode = new PostalCode(townName, municipalityName, zipCode);

        // Assert
        assertNotNull(postalCode);
        assertEquals(zipCode, postalCode.getZipCode());
        assertEquals(municipalityName, postalCode.getMunicipalityName());
    }

    @Test
    @DisplayName("Negative, test the constructor with null values")
    public void testConstructorWithNull()
    {
        // Arrange
        boolean exceptionThrown = false;

        try {
            // Act
            PostalCode postalCode = new PostalCode(null, null, null);
            fail("An IllegalArgumentException should have been thrown");
        }
        catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        // Assert
        assertTrue(exceptionThrown);
    }

    @Test
    @DisplayName("Negative, test the constructor with blank Strings")
    public void testConstructorWithBlankStrings()
    {
        // Arrange
        String townName = "";
        String zipCode = "";
        String municipalityName = "";
        boolean exceptionThrown = false;

        try {
            // Act
            PostalCode postalCode = new PostalCode(townName, municipalityName, zipCode);
            fail("An IllegalArgumentException should have been thrown");
        }
        catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        // Assert
        assertTrue(exceptionThrown);
    }
}