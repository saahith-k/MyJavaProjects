package models;

public class RoomBooking {
    private Room room;
    private User user;
    private String fromDate;
    private String toDate;

    public RoomBooking(Room room, User user, String fromDate, String toDate) {
        this.room = room;
        this.user = user;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Room getRoom() { return room; }
    public User getUser() { return user; }

    public String getFromDate() { return fromDate; }
    public String getToDate() { return toDate; }

    public double calculateTotalPrice() {
        return room.getPrice() * 2; // Assuming fixed 2-day stay for simplicity
    }

    @Override
    public String toString() {
        return "Booking Confirmed!\n" +
               user + "\n" +
               "Room ID: " + room.getRoomId() + "\n" +
               "Stay Dates: " + fromDate + " - " + toDate + "\n" +
               "Total Price: ₹" + calculateTotalPrice() + "\n" +
               "Confirmation email sent to " + user.getContact();
    }
}
