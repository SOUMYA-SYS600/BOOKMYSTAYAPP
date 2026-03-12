import java.util.Map;

/**
 * ================================================================
 * CLASS - RoomSearchService
 * ================================================================
 *
 * Use Case 4: Room Search & Availability Check
 *
 * This class provides search functionality for guests
 * to view available rooms.
 *
 * It reads room availability from inventory
 * and room details from Room objects.
 *
 * No booking or inventory mutation occurs here.
 *
 * @version 4.0
 */

class RoomSearchService {

    /**
     * Displays available rooms along with their details.
     */
    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        // Check Single Room availability
        if (availability.get("Single") > 0) {
            System.out.println("\nSingle Room:");
            singleRoom.displayRoomDetails();
            System.out.println("Available: " + availability.get("Single"));
        }

        // Check Double Room availability
        if (availability.get("Double") > 0) {
            System.out.println("\nDouble Room:");
            doubleRoom.displayRoomDetails();
            System.out.println("Available: " + availability.get("Double"));
        }

        // Check Suite Room availability
        if (availability.get("Suite") > 0) {
            System.out.println("\nSuite Room:");
            suiteRoom.displayRoomDetails();
            System.out.println("Available: " + availability.get("Suite"));
        }
    }
}


/**
 * ================================================================
 * MAIN CLASS - UseCase4RoomSearch
 * ================================================================
 *
 * Demonstrates how guests can view available rooms
 * without modifying inventory data.
 *
 * @version 4.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Room Search");

        // Create room definitions
        SingleRoom singleRoom = new SingleRoom();
        DoubleRoom doubleRoom = new DoubleRoom();
        SuiteRoom suiteRoom = new SuiteRoom();

        // Create inventory
        RoomInventory inventory = new RoomInventory();

        // Create search service
        RoomSearchService searchService = new RoomSearchService();

        // Perform room search
        searchService.searchAvailableRooms(
                inventory,
                singleRoom,
                doubleRoom,
                suiteRoom
        );
    }
}