package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.*;

import static DAO.DatabaseConnection.getConnection;

public class DAO {

    public static void newUSer(User user) throws SQLException {

        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO USER (id, name, lastName, age, username, email, password, ADMIN) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, user.getId());
                statement.setString(2, user.getName());
                statement.setString(3, user.getLastName());
                statement.setString(4, user.getAge());
                statement.setString(5, user.getLoginUserName());
                statement.setString(6, user.getEmailAddress());
                statement.setString(7, user.getPasswordUser());
                statement.setBoolean(8, user.getIsAdmin());
                statement.executeUpdate();
            }
        }
    }

    public static void newCard(Wallet wallet) throws SQLException {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO PAYMENT(id, Card, expiredDate, installments, id_user) VALUES (?, ?, ?,?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, wallet.getId());
                statement.setString(2, wallet.getCard());
                statement.setString(3, wallet.getExpirationDate());
                statement.setString(4, null);
                statement.setInt(5, wallet.getUser_id().getId());
                statement.executeUpdate();
            }
        }
    }

    public static void newAddress(Address address) throws SQLException {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO ADDRESS (id, street, number, neighborhood, zipCode, city, state, country, id_user) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {


                statement.setInt(1, address.getId());
                statement.setString(2, address.getStreet());
                statement.setInt(3, address.getNumber());
                statement.setString(4, address.getNeighborhood());
                statement.setString(5, address.getZipCode());
                statement.setString(6, address.getCity());
                statement.setString(7, address.getState());
                statement.setString(8, address.getCountry());
                statement.setInt(9, address.getUser_id().getId());
                statement.executeUpdate();
            }
        }
    }

    public static void newHotel(Hotel hotel) throws SQLException {

        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO HOTEL (id, name, rating) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, hotel.getId());
                statement.setString(2, hotel.getName());
                statement.setInt(3, hotel.getRating());
                statement.executeUpdate();
            }
        }
    }

    public static void newRoom(Room room) throws SQLException {

        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO ROOM (id, name, guestPerRoom, rate, available, hotel_id) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, room.getId());
                statement.setString(2, room.getName());
                statement.setInt(3, room.getGuestPerRoom());
                statement.setDouble(4, room.getRate());
                statement.setBoolean(5, room.getavailable());
                statement.setInt(6, room.getId_hotel().getId());
                statement.executeUpdate();
            }
        }
    }

    public static void newReservation(Reservation reservation) throws SQLException {

        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO RESERVATION (id, startDay, endDay, numDays, id_hotel, id_room, id_user, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, reservation.getId());
                statement.setString(2, reservation.getStartDay());
                statement.setString(3, reservation.getEndDay());
                statement.setLong(4, reservation.getNumDays());
                statement.setInt(5, reservation.getId_hotel().getId());
                statement.setInt(6, reservation.getId_room().getId());
                statement.setInt(7, reservation.getId_user().getId());
                statement.setDouble(8, reservation.getTotal());

                statement.executeUpdate();
            }
        }
    }

    public static List<Hotel> getAllHotels() throws SQLException {
        List<Hotel> hotels = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM HOTEL";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Integer rating = resultSet.getInt("rating");

                    hotels.add(new Hotel(id, name, rating));
                }
            }
        }

        return hotels;
    }

    public static List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String sql = "SELECT ROOM.*, HOTEL.name AS hotelName, HOTEL.rating as hotelRating FROM ROOM LEFT JOIN HOTEL ON ROOM.hotel_id = HOTEL.id";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Integer guestPerRoom = resultSet.getInt("guestPerRoom");
                    Double rate = resultSet.getDouble("rate");
                    boolean available = resultSet.getBoolean("available");

                    int hotelId = resultSet.getInt("hotel_id");
                    String hotelName = resultSet.getString("hotelName");
                    Integer hotelRating = resultSet.getInt("hotelRating");
                    Hotel hotel = new Hotel(hotelId, hotelName, hotelRating);

                    rooms.add(new Room(id, name, guestPerRoom, rate, available, hotel));
                }
            }
        }

        return rooms;
    }


    public static List<Reservation> getAllReservations(User currentUser) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String sql = "SELECT RESERVATION.*, ROOM.*, HOTEL.*, USER.* " +
                    "FROM RESERVATION " +
                    "LEFT JOIN ROOM ON RESERVATION.id_room = ROOM.id " +
                    "LEFT JOIN HOTEL ON RESERVATION.id_hotel = HOTEL.id " +
                    "LEFT JOIN USER ON RESERVATION.id_user = USER.id " +
                    "WHERE USER.id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, currentUser.getId());

                try (ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String startDay = resultSet.getString("startDay");
                        String endDay = resultSet.getString("endDay");
                        long numDays = resultSet.getLong("numDays");

                        int roomId = resultSet.getInt("room.id");
                        String roomName = resultSet.getString("room.name");
                        Integer roomGuestPerRoom = resultSet.getInt("room.guestPerRoom");
                        Double roomRate = resultSet.getDouble("room.rate");
                        boolean roomAvailable = resultSet.getBoolean("room.available");
                        Room room = new Room(roomId, roomName, roomGuestPerRoom, roomRate, roomAvailable, null);

                        int hotelId = resultSet.getInt("hotel.id");
                        String hotelName = resultSet.getString("hotel.name");
                        Integer hotelRating = resultSet.getInt("hotel.rating");
                        Hotel hotel = new Hotel(hotelId, hotelName, hotelRating);

                        int userId = resultSet.getInt("user.id");
                        String userName = resultSet.getString("user.name");
                        String userLastName = resultSet.getString("user.lastName");
                        String userAge = resultSet.getString("user.age");
                        String userEmailAddress = resultSet.getString("user.email");
                        String loginUserName = resultSet.getString("user.userName");
                        String passwordUser = resultSet.getString("user.password");
                        Boolean isAdmin = resultSet.getBoolean("user.ADMIN");
                        User user = new User(userId, userName, userLastName, userAge, userEmailAddress, loginUserName, passwordUser, isAdmin);

                        double total = resultSet.getDouble("total");

                        reservations.add(new Reservation(id, startDay, endDay, numDays, hotel, room, user, total));
                    }
                }
            }
        }

        return reservations;
    }


    public static User loginMenuAccess(String username, String password) throws SQLException {
        User user = null;
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM USER WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String lastName = resultSet.getString("lastName");
                        String age = resultSet.getString("age");
                        String emailAddress = resultSet.getString("email");
                        String loginUserName = resultSet.getString("username");
                        String passwordUser = resultSet.getString("password");
                        Boolean isAdmin = resultSet.getBoolean("ADMIN");
                        user = new User(id, name, lastName, age, emailAddress, loginUserName, passwordUser, isAdmin);
                    }
                }
            }
        }

        return user;
    }


    public static void updatePassword(User currentUser, String newPassword) throws SQLException {
        try (Connection connection = getConnection()) {
            String sql = "UPDATE USER SET password = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, newPassword);
                statement.setInt(2, currentUser.getId());
                statement.executeUpdate();
            }
        }
    }

    public static User newPassword(String username) throws SQLException {
        User user = null;
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM USER WHERE username = ? ";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String lastName = resultSet.getString("lastName");
                        String age = resultSet.getString("age");
                        String emailAddress = resultSet.getString("email");
                        String loginUserName = resultSet.getString("username");
                        String passwordUser = resultSet.getString("password");
                        Boolean isAdmin = resultSet.getBoolean("ADMIN");
                        user = new User(id, name, lastName, age, emailAddress, loginUserName, passwordUser, isAdmin);
                    }
                }
            }
        }

        return user;
    }

    public static Wallet getPayment(User currentUser) throws SQLException {
        Wallet wallet = null;
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM PAYMENT WHERE id_user = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, currentUser.getId());

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String Card = resultSet.getString("Card");
                        String expiredDate = resultSet.getString("expiredDate");
                        Integer installments = resultSet.getInt("installments");

                        wallet = new Wallet(id, Card, expiredDate, installments, currentUser);
                    }
                }
            }
        }

        return wallet;
    }

    public static Address getAddress(User currentUser) throws SQLException {
        Address address = null;
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM ADDRESS WHERE id_user = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, currentUser.getId());

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String street = resultSet.getString("street");
                        Integer number = resultSet.getInt("number");
                        String neighborhood = resultSet.getString("neighborhood");
                        String zipCode = resultSet.getString("zipCode");
                        String city = resultSet.getString("city");
                        String state = resultSet.getString("state");
                        String country = resultSet.getString("country");

                        address = new Address(id, street, number, neighborhood, zipCode, city, state, country, currentUser);
                    }
                }
            }
        }
        return address;
    }
    public static void cancelReservation(int reservationId) throws SQLException {
        try (Connection connection = getConnection()) {
        String sql = "DELETE FROM RESERVATION WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservationId);
            statement.executeUpdate();
    }}}

}


