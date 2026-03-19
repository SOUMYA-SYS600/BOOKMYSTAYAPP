import java.util.*;

/**
 * ================================================================
 * CLASS - AddOnService
 * ================================================================
 *
 * Represents an optional service added to a reservation.
 *
 * @version 7.0
 */

class AddOnService {

    /** Name of the service */
    private String serviceName;

    /** Cost of the service */
    private double cost;

    /**
     * Constructor
     */
    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    /** Return service name */
    public String getServiceName() {
        return serviceName;
    }

    /** Return cost */
    public double getCost() {
        return cost;
    }
}


/**
 * ================================================================
 * CLASS - AddOnServiceManager
 * ================================================================
 *
 * Manages services mapped to reservation IDs.
 *
 * @version 7.0
 */

class AddOnServiceManager {

    /**
     * Key -> Reservation ID
     * Value -> List of services
     */
    private Map<String, List<AddOnService>> servicesByReservation;

    /** Constructor */
    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    /**
     * Adds a service to a reservation
     */
    public void addService(String reservationId, AddOnService service) {

        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    /**
     * Calculates total cost of services
     */
    public double calculateTotalServiceCost(String reservationId) {

        double total = 0.0;

        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getCost();
            }
        }

        return total;
    }
}


/**
 * ================================================================
 * MAIN CLASS - UseCase7AddOnServiceSelection
 * ================================================================
 *
 * Demonstrates attaching add-on services to a booking.
 *
 * @version 7.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection");

        // Example reservation ID (from UC6)
        String reservationId = "Single-1";

        // Create manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Create services
        AddOnService breakfast = new AddOnService("Breakfast", 500.0);
        AddOnService spa = new AddOnService("Spa", 1000.0);

        // Add services
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);

        // Calculate total cost
        double totalCost = manager.calculateTotalServiceCost(reservationId);

        // Display result
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}