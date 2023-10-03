package entity;

import DAO.DAO;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Reservation {
    private Integer id;
    private String startDay;
    private String endDay;
    private Long numDays;
    private Hotel id_hotel;
    private Room id_room;
    private User id_user;
    private Double total;


    public void saveDB() {
        try {
            DAO.newReservation(this);
            System.out.println("✔ Reserva realizada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("✘ Erro ao realizar a reserva");
        }
    }


    public static Reservation registerNewReservation(User user) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int id = random.nextInt(1000000);

        List<Room> rooms = DAO.getAllRooms();

        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            String availability = room.getavailable() ? "disponível" : "indisponível";
            System.out.printf("%d. Hotel: %s ☆ %d, %s (%d pessoas, R$ %.2f por diária, %s)%n", i + 1, room.getId_hotel().getName(), room.getId_hotel().getRating(), room.getName(), room.getGuestPerRoom(), room.getRate(), availability);
        }

        System.out.print("Selecione o quarto desejado: ");
        int roomIndex = scanner.nextInt() - 1;
        Room selectedRoom = rooms.get(roomIndex);
        
        System.out.print("Digite a data de check-in (dd/mm/aaaa): ");
        String checkInDateStr = scanner.next();
        LocalDate checkInDate = LocalDate.parse(checkInDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Digite a data de check-out (dd/mm/aaaa): ");
        String checkOutDateStr = scanner.next();
        LocalDate checkOutDate = LocalDate.parse(checkOutDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        long numDays = ChronoUnit.DAYS.between(checkInDate, checkOutDate);

        return new Reservation(id, checkInDateStr, checkOutDateStr, numDays, selectedRoom.getId_hotel(), selectedRoom, user, selectedRoom.getRate() * numDays);

    }



    public Reservation(Integer id, String startDay, String endDay, Long numDays, Hotel id_hotel, Room id_room, User id_user, Double total) {
        this.id = id;
        this.startDay = startDay;
        this.endDay = endDay;
        this.numDays = numDays;
        this.id_hotel = id_hotel;
        this.id_room = id_room;
        this.id_user = id_user;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public Long getNumDays() {
        return numDays;
    }

    public void setNumDays(Long numDays) {
        this.numDays = numDays;
    }

    public Hotel getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(Hotel id_hotel) {
        this.id_hotel = id_hotel;
    }

    public Room getId_room() {
        return id_room;
    }

    public void setId_room(Room id_room) {
        this.id_room = id_room;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getReservation() {
     /*   String paymentInfo;
        if (wallet.equals("debit")) {
            paymentInfo = "Pagamento à vista com cartão de débito";
        } else if (wallet.equals("credit")) {
            paymentInfo = "Pagamento parcelado em " + installments + "x com cartão de crédito";
        } else {
            paymentInfo = "Tipo de cartão inválido";
        }*/

        return "Reserva de " + User.getName() + " " + User.getLastName() + " no Hotel " + id_hotel.getName() + "\n Quarto " + id_room.getName() + " - " + id_room.getGuestPerRoom() + " pessoas por quarto. "
                + "\n Check-In: " + startDay + " Check-Out: " + endDay + "\n Tempo de estádia: " + numDays + " dias(s)" + "\n Preço diária: R$" + total;

    }}



