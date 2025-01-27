package wiprotrainingday2;

import java.util.ArrayList;
import java.util.List;

// Base class for a Room
class Room {
    protected int roomNumber;
    protected String roomType;
    protected double price;
    protected boolean isAvailable;

    public Room(int roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = true;
    }

    public boolean checkAvailability() {
        return isAvailable;
    }

    public String book() {
        if (isAvailable) {
            isAvailable = false;
            return "Room " + roomNumber + " has been booked successfully.";
        } else {
            return "Room " + roomNumber + " is not available.";
        }
    }

    public String checkOut() {
        isAvailable = true;
        return "Room " + roomNumber + " is now available.";
    }
}

// StandardRoom class inherits Room class
class StandardRoom extends Room {
    public StandardRoom(int roomNumber) {
        super(roomNumber, "Standard", 100.0);  // Price set to 100 by default
    }
}

// DeluxeRoom class inherits Room class
class DeluxeRoom extends Room {
    public DeluxeRoom(int roomNumber) {
        super(roomNumber, "Deluxe", 150.0);  // Price set to 150 by default
    }
}

// Booking class handles room reservations and customer bookings
class Booking {
    private Customer customer;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private String bookingStatus;

    public Booking(Customer customer, Room room, String checkInDate, String checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingStatus = "Confirmed";
    }

    public String getBookingDetails() {
        return "Booking Details:\nCustomer: " + customer.getName() + "\nRoom Number: " + room.roomNumber + 
               "\nType: " + room.roomType + "\nCheck-in: " + checkInDate + "\nCheck-out: " + checkOutDate + 
               "\nStatus: " + bookingStatus;
    }

    public String cancelBooking() {
        room.checkOut();
        bookingStatus = "Cancelled";
        return "Booking for Room " + room.roomNumber + " has been canceled.";
    }
}

// Customer class to handle customer information and their bookings
class Customer {
    private String name;
    private int customerId;
    private List<Booking> bookings;

    public Customer(String name, int customerId) {
        this.name = name;
        this.customerId = customerId;
        this.bookings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String makeBooking(Room room, String checkInDate, String checkOutDate) {
        if (room.checkAvailability()) {
            room.book();
            Booking newBooking = new Booking(this, room, checkInDate, checkOutDate);
            bookings.add(newBooking);
            return newBooking.getBookingDetails();
        } else {
            return "Room " + room.roomNumber + " is not available for booking.";
        }
    }

    public String checkOutRoom(Room room) {
        for (Booking booking : bookings) {
            if (booking != null && booking.getBookingDetails().contains(String.valueOf(room.roomNumber))) {
                room.checkOut();
                return "Customer " + name + " has checked out of Room " + room.roomNumber + ".";
            }
        }
        return "Room " + room.roomNumber + " is not booked by " + name + ".";
    }
}

// Main class to run the booking system
public class HotelRoomBookingSystem {
    public static void main(String[] args) {
        // Create room objects
        Room room1 = new StandardRoom(101);
        Room room2 = new DeluxeRoom(102);
        Room room3 = new StandardRoom(103);
        Room room4 = new DeluxeRoom(104);

        // Create customer objects
        Customer customer1 = new Customer("John Doe", 1);

        // Booking a room
        System.out.println(customer1.makeBooking(room1, "2025-02-01", "2025-02-10"));

        // Listing the booked room details
        System.out.println(customer1.makeBooking(room2, "2025-02-05", "2025-02-15"));

        // Customer checks out from room1
        System.out.println(customer1.checkOutRoom(room1));

        // Cancelling the booking for room2
        Booking booking = new Booking(customer1, room2, "2025-02-05", "2025-02-15");
        System.out.println(booking.cancelBooking());
    }
}
