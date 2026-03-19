import java.io.*;
import java.util.*;

/**
 * ================================================================
 * CLASS - RoomInventory
 * ================================================================
 */
class RoomInventory {

    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single", 5);
        availability.put("Double", 3);
        availability.put("Suite", 2);
    }

    public Map<String, Integer> getAvailability() {
        return availability;
    }

    public void setAvailability(String type, int count) {
        availability.put(type, count);
    }
}


/**
 * ================================================================
 * CLASS - FilePersistenceService
 * ================================================================
 */
class FilePersistenceService {

    /**
     * Save inventory to file
     */
    public void saveInventory(RoomInventory inventory, String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (Map.Entry<String, Integer> entry : inventory.getAvailability().entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }

            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    /**
     * Load inventory from file
     */
    public boolean loadInventory(RoomInventory inventory, String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("=");

                if (parts.length == 2) {
                    String type = parts[0];
                    int count = Integer.parseInt(parts[1]);
                    inventory.setAvailability(type, count);
                }
            }

            return true;

        } catch (IOException e) {
            return false;
        }
    }
}


/**
 * ================================================================
 * MAIN CLASS - UseCase12DataPersistenceRecovery
 * ================================================================
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("System Recovery");

        String filePath = "inventory.txt";

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistence = new FilePersistenceService();

        // Try loading previous state
        boolean loaded = persistence.loadInventory(inventory, filePath);

        if (!loaded) {
            System.out.println("No valid inventory data found. Starting fresh.");
        } else {
            System.out.println("Inventory restored from file.");
        }

        // Display inventory
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.getAvailability().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Save inventory
        persistence.saveInventory(inventory, filePath);
    }
}