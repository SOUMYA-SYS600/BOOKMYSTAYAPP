/**
 * ================================================================
 * ABSTRACT CLASS - Room
 * ================================================================
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Description:
 * This abstract class represents a generic hotel room.
 * It models attributes that are intrinsic to a room type
 * and remain constant regardless of availability.
 *
 * @version 2.1
 */

abstract class Room {

    /** Number of beds available in the room. */
    protected int numberOfBeds;

    /** Total size of the room in square feet. */
    protected int squareFeet;

    /** Price charged per night for this room type. */
    protected double pricePerNight;

    /**
     * Constructor used by child classes to initialize room attributes.
     */
    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    /** Displays room details */
    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}

/**
 * ================================================================
 * CLASS - SingleRoom
 * ================================================================
 */
class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

/**
 * ================================================================
 * CLASS - DoubleRoom
 * ================================================================
 */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

/**
 * ================================================================
 * CLASS - SuiteRoom
 * ================================================================
 */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}

/**
 * ================================================================
 * MAIN CLASS - UseCase2RoomInitialization
 * ================================================================
 *
 * Demonstrates room initialization and static availability.
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Hotel Room Initialization\n");

        // Create room objects
        SingleRoom single = new SingleRoom();
        DoubleRoom dbl = new DoubleRoom();
        SuiteRoom suite = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Single Room
        System.out.println("Single Room:");
        single.displayRoomDetails();
        System.out.println("Available: " + singleAvailable);
        System.out.println();

        // Double Room
        System.out.println("Double Room:");
        dbl.displayRoomDetails();
        System.out.println("Available: " + doubleAvailable);
        System.out.println();

        // Suite Room
        System.out.println("Suite Room:");
        suite.displayRoomDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}