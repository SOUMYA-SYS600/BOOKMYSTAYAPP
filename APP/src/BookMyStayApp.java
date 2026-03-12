import java.util.HashMap;
import java.util.Map;

/**
 * ================================================================
 * CLASS - RoomInventory
 * ================================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * This class acts as the single source of truth
 * for room availability in the hotel.
 *
 * @version 3.1
 */
class RoomInventory {

    /**
     * Stores available room count for each room type.
     * Key -> Room type name
     * Value -> Available room count
     */
    private Map<String, Integer> roomAvailability;

    /**
     * Constructor initializes inventory with default values.
     */
    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    /**
     * Initializes room availability data.
     */
    private void initializeInventory() {
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    /**
     * Returns the availability map.
     */
    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    /**
     * Updates availability for a room type.
     */
    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}


/**
 * ================================================================
 * MAIN CLASS - UseCase3InventorySetup
 * ================================================================
 *
 * Demonstrates centralized room inventory management.
 *
 * @version 3.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Hotel Room Inventory Status\n");

        // Room objects (from UC2)
        SingleRoom single = new SingleRoom();
        DoubleRoom dbl = new DoubleRoom();
        SuiteRoom suite = new SuiteRoom();

        // Create centralized inventory
        RoomInventory inventory = new RoomInventory();

        Map<String, Integer> availability = inventory.getRoomAvailability();

        // Single Room
        System.out.println("Single Room:");
        single.displayRoomDetails();
        System.out.println("Available Rooms: " + availability.get("Single"));
        System.out.println();

        // Double Room
        System.out.println("Double Room:");
        dbl.displayRoomDetails();
        System.out.println("Available Rooms: " + availability.get("Double"));
        System.out.println();

        // Suite Room
        System.out.println("Suite Room:");
        suite.displayRoomDetails();
        System.out.println("Available Rooms: " + availability.get("Suite"));
    }
}