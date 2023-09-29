package entity;

import DAO.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Room {
    private Integer id;
    private String name;

    private  Integer guestPerRoom;

    private Double rate;

    private Boolean available ;

    private Hotel id_hotel;


    public Room(Integer id, String name, Integer guestPerRoom, Double rate, Boolean available, Hotel id_hotel){
        this.id = id;
        this.name = name;
        this.guestPerRoom = guestPerRoom;
        this.rate = rate;
        this.available = available;
        this.id_hotel = id_hotel;

    }

    public void saveDB() {
        try {
            DAO.newRoom(this);
            System.out.println("✔ Quarto cadastrado com sucesso no banco de dados.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("✘ Erro ao cadastrar o quarto no banco de dados.");
        }
    }

    public static Room registerNewRoom(List<Hotel> hotel) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean available = true;
        Random random = new Random();
        int id = random.nextInt(1000000);

        System.out.println("Selecione o hotel qual você deseja adicionar o quarto:");
        DAO.getAllHotels();
        for (int i = 0; i < hotel.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, hotel.get(i).getName());
        }

        int hotelIndex = scanner.nextInt() - 1;
        Hotel selectedHotel = hotel.get(hotelIndex);

        scanner.nextLine();

        System.out.println("✚ Digite o nome do quarto: ");
        String name = scanner.nextLine();

        System.out.println("✚ Digite quantas pessoas o quarto suporta: ");
        Integer guestPerRoom = scanner.nextInt();

        System.out.println("✚ Digite valor da diária do quarto: ");
        Double rate = scanner.nextDouble();


        return new Room(id, name, guestPerRoom, rate, available, selectedHotel);
    }

    public String getName(){
        return this.name;
    }

    public Integer getGuestPerRoom(){
        return this.guestPerRoom;
    }

    public Double getRate(){
        return this.rate;
    }

    public Boolean getavailable(){
        return this.available;
    }

    public Hotel getId_hotel() {
        return id_hotel;
    }
    public void setId_hotel(Hotel id_hotel) {
        this.id_hotel = id_hotel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


     public boolean setRoom(boolean available){
       return this.available = available;
      }


}
