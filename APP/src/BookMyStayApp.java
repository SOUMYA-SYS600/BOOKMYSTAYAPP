import java.util.*;

/**
 * ================================================================
 * CLASS - RoomInventory
 * ================================================================
 */
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}


/**
 * ================================================================
 * CLASS - CancellationService
 * ================================================================
 *
 * Handles booking cancellation and rollback.
 */
class CancellationService {

    /** Stack to store released room IDs (LIFO) */
    private Stack<String> releasedRoomIds;

    /** Maps reservation ID to room type */
    private Map<String, String> reservationRoomTypeMap;

    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    /**
     * Registers confirmed booking
     */
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    /**
     * Cancels booking and restores inventory
     */
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid reservation ID.");
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);

        // Restore inventory
        Map<String, Integer> availability = inventory.getRoomAvailability();
        inventory.updateAvailability(roomType, availability.get(roomType) + 1);

        // Push to rollback stack
        releasedRoomIds.push(reservationId);

        // Remove booking
        reservationRoomTypeMap.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    /**
     * Displays rollback history
     */
    public void showRollbackHistory() {

        System.out.println("\nRollback History (Most Recent First):");

        for (int i = releasedRoomIds.size() - 1; i >= 0; i--) {
            System.out.println("Released Reservation ID: " + releasedRoomIds.get(i));
        }
    }
}


/**
 * ================================================================
 * MAIN CLASS - UseCase10BookingCancellation
 * ================================================================
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking Cancellation");

        // Inventory
        RoomInventory inventory = new RoomInventory();

        // Cancellation service
        CancellationService service = new CancellationService();

        // Register bookings (simulate confirmed bookings)
        service.registerBooking("Single-1", "Single");
        service.registerBooking("Double-1", "Double");

        // Cancel one booking
        service.cancelBooking("Single-1", inventory);

        // Show rollback history
        service.showRollbackHistory();

        // Show updated availability
        System.out.println("\nUpdated Single Room Availability: "
                + inventory.getRoomAvailability().get("Single"));
    }
}