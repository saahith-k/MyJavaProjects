package main;

import services.BookingService;
import models.Room;
import models.User;
import models.RoomBooking;
import exceptions.*;

import java.util.List;
import java.util.Scanner;

public class HotelBookingApp {
    public static void main(String[] args) {
        BookingService service = new BookingService();
        service.addRoom(new Room(101, "Deluxe", 3000, true));
        service.addRoom(new Room(102, "Standard", 2000, true));
        service.addRoom(new Room(103, "Deluxe", 3000, false));

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your contact email: ");
        String email = sc.nextLine();

        User user = new User("U1", name, email);

        System.out.print("Enter room type to search: ");
        String type = sc.nextLine();
        List<Room> availableRooms = service.searchAvailableRooms(type);

        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available for type: " + type);
            return;
        }

        System.out.println("Available Rooms:");
        for (Room room : availableRooms) {
            System.out.println(room);
        }

        System.out.print("Enter Room ID to book: ");
        int roomId = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter From Date (e.g., May 25): ");
        String from = sc.nextLine();
        System.out.print("Enter To Date (e.g., May 27): ");
        String to = sc.nextLine();

        try {
            RoomBooking booking = service.bookRoom(roomId, user, from, to);
            System.out.println(booking);
        } catch (RoomUnavailableException | InvalidRoomSelectionException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
