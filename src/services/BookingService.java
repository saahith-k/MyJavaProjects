package services;

import java.util.List;
import java.util.ArrayList;
import models.Room;
import models.User;
import models.RoomBooking;
import exceptions.*;

public class BookingService {
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> searchAvailableRooms(String type) {
        List<Room> result = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getType().equalsIgnoreCase(type) && room.isAvailable()) {
                result.add(room);
            }
        }
        return result;
    }

    public Room getRoomById(int roomId) throws InvalidRoomSelectionException {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        throw new InvalidRoomSelectionException("Room ID " + roomId + " does not exist.");
    }

    public RoomBooking bookRoom(int roomId, User user, String fromDate, String toDate)
            throws RoomUnavailableException, InvalidRoomSelectionException {
        Room room = getRoomById(roomId);
        if (!room.isAvailable()) {
            throw new RoomUnavailableException("Room ID " + roomId + " is not available.");
        }
        room.setAvailable(false);
        return new RoomBooking(room, user, fromDate, toDate);
    }
}
