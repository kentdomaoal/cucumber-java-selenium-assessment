package util;

import io.cucumber.messages.ndjson.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    static ObjectMapper mapper = new ObjectMapper();

    public static List<String> getExampleList(){
        String fileLocation = "testdata/exampleLinks.json";
        ArrayList<String> exampleList = null;
        try {
            // Load the file out of the resources root directory
            ClassLoader classLoader = JsonReader.class.getClassLoader();
            try (InputStream inputStream = classLoader.getResourceAsStream(fileLocation)) {

                if (inputStream == null) {
                    throw new RuntimeException("Error: " + fileLocation + " not found in resources folder.");
                }

                // Reads JSON file and directly maps it to string ArrayList
                exampleList = mapper.readValue(inputStream,
                        mapper.getTypeFactory().constructCollectionType(ArrayList.class, String.class)
                );

            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read json file.");
        }
        return exampleList;
    }
}