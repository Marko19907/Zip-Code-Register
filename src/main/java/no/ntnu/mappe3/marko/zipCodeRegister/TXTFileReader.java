package no.ntnu.mappe3.marko.zipCodeRegister;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class TXTFileReader is responsible for reading data from text files.
 *
 * @author Marko
 * @version 11-05-2021
 */
public class TXTFileReader
{
    /**
     * The separator used in the txt file
     */
    private static final String SEPARATOR = "\t";

    /**
     * The name of the file to read the PostalCodes from
     * Must include the file name and the file extension (e.g., 'someFile.txt')
     */
    private static final String DATA_FILE_NAME = "zipCodeData.txt";

    /**
     * The logger
     */
    private final Logger logger = Logger.getLogger(this.getClass().toString());


    /**
     * Constructor for TXTFileReader objects.
     */
    public TXTFileReader()
    {
    }

    /**
     * Returns a List of PostalCodes from a txt file.
     * @return A List of PostalCodes read from a txt file, null if the List can not be read
     */
    public List<PostalCode> loadData()
    {
        List<PostalCode> postalCodeList = null;

        try {
            File fileToRead = new File(
                    Objects.requireNonNull(
                            this.getClass().getClassLoader().getResource(DATA_FILE_NAME)).toURI()
            );
            postalCodeList = this.readPostalCodesList(fileToRead);
        }
        catch (IOException | NullPointerException | URISyntaxException e) {
            this.logger.log(Level.INFO, "Failed to read data from the txt file . . .");
            this.logger.log(Level.INFO, e.getMessage());
        }

        return postalCodeList;
    }

    /**
     * Returns a List of PostalCodes from a given txt file.
     * @param fileToRead The file to read from, not null
     * @return A List of PostalCodes read from the given txt file
     * @throws IllegalArgumentException If the given file is invalid
     * @throws IOException              If an IO error is encountered
     */
    private List<PostalCode> readPostalCodesList(File fileToRead) throws IOException
    {
        List<PostalCode> postalCodesList = new ArrayList<>();

        if (fileToRead != null && fileToRead.canRead()) {
            List<String> lineStringList = Files.readAllLines(Paths.get(fileToRead.getAbsolutePath()), StandardCharsets.UTF_8);
            lineStringList.forEach(row -> {
                String[] data = row.split(SEPARATOR);
                String zipCode = data[0];
                String townName = data[1];
                String municipalityName = data[3];

                postalCodesList.add(new PostalCode(townName, municipalityName, zipCode));
            });
        }
        return postalCodesList;
    }
}