package sv.dev.api.utils;

import java.time.Year;
import java.util.Random;

public class IdGenerator {
	
	 // Generate a string of random numbers with a specific length
    public static int generateRandomNumbers(int length) {
        Random random = new Random();
        int result = 0;

        for (int i = 0; i < length; i++) {
            result = result * 10 + random.nextInt(10); // Append random digits (0-9)
        }

        return result;
    }

    // Generate a unique ID for the incident
    public static int generateIncidentID() {
        int year = Year.now().getValue(); // Get the current year
        int uniqueID = year * 1000 + generateRandomNumbers(3); // Combine year with random numbers
        return uniqueID;
    }

}
