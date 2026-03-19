import java.util.*;

/**
 * ================================================================
 * CLASS - Reservation
 * ================================================================
 */
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}


/**
 * ================================================================
 * CLASS - BookingRequestQueue (FIFO)
 * ================================================================
 */
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}


/**
 * ================================================================
 * CLASS - RoomInventory
 * ================================================================
 */
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 2);
        roomAvailability.put("Double", 1);
        roomAvailability.put("Suite", 1);
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
 * CLASS - RoomAllocationService
 * ================================================================
 *
 * Handles confirmation and room assignment.
 */
class RoomAllocationService {

    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    /**
     * Allocates room and updates inventory
     */
    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get(roomType) > 0) {

            String roomId = generateRoomId(roomType);

            // Mark allocated
            allocatedRoomIds.add(roomId);

            assignedRoomsByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

            // Update inventory
            inventory.updateAvailability(roomType, availability.get(roomType) - 1);

            // Print confirmation
            System.out.println("Booking confirmed for Guest: "
                    + reservation.getGuestName()
                    + ", Room ID: "
                    + roomId);

        } else {
            System.out.println("No rooms available for Guest: "
                    + reservation.getGuestName()
                    + " (" + roomType + ")");
        }
    }

    /**
     * Generates unique room ID
     */
    private String generateRoomId(String roomType) {

        assignedRoomsByType.putIfAbsent(roomType, new HashSet<>());
        int count = assignedRoomsByType.get(roomType).size() + 1;

        return roomType + "-" + count;
    }
}


/**
 * ================================================================
 * MAIN CLASS - UseCase6RoomAllocation
 * ================================================================
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        // Create queue
        BookingRequestQueue queue = new BookingRequestQueue();

        // Add booking requests
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Inventory
        RoomInventory inventory = new RoomInventory();

        // Allocation service
        RoomAllocationService service = new RoomAllocationService();

        // Process queue FIFO
        while (queue.hasPendingRequests()) {
            Reservation request = queue.getNextRequest();
            service.allocateRoom(request, inventory);
        }
    }
}