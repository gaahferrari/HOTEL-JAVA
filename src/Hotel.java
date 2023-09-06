import java.util.ArrayList;
import java.util.List;


public class Hotel {
    private String name;

    private Integer rating;

    private List<Room> rooms;

    public Hotel(String name, Integer rating){
        this.name = name;
        this.rating = rating;
    }


    //Getters
    public String getName(){
        return this.name;
    }

    public Integer getRating(){
        return this.rating;
    }

    public List<Room> getRooms(){
        return this.rooms;
    }


    //Setters
    public String setName(String name){
        return this.name = name;
    }

    public Integer setRating(Integer rating){
        return this.rating = rating;
    }

    public List<Room> setRooms(List<Room> rooms){
        return this.rooms = rooms;
    }


    //Prints
    public String getHotel()
    {
        StringBuilder hotel = new StringBuilder("--------- Hotel " + this.name + "-----------\n" + "Rating: " + this.rating +
                "\nRooms: \n");

        for(Room room: this.rooms){
            String availability = room.getavailable() ? "Disponível" : "Indisponível";
            hotel.append(" - "+ room.getName() + " - "+ availability + "\n");
        }

        return hotel.toString();
    }

    static Hotel hotel1 = new Hotel("Copacabana Palace", 8);
    static Room room1 = new Room("1 - Suite Master", 2, 3000.00, true);
    static Room room2 = new Room("2 - Suite Premium", 2, 2500.00, true);
    static Room room3 = new Room("3 - Suite Family", 5, 4000.00, true);
    static Hotel hotel2 = new Hotel("Hilton", 3);
    static Room room12 = new Room("1 - Suite Master", 2, 700.00, true);
    static Room room22 = new Room("2 - Suite Premium", 2, 500.00, true);
    static Room room32 = new Room("3 - Suite Family", 5, 900.00, true);
    static Hotel hotel3 = new Hotel("IBIS", 5);
    static Room room13 = new Room("1 - Suite Master", 2, 300.00, true);
    static Room room23 = new Room("2 - Suite Premium", 2, 200.00, true);
    static Room room33 = new Room("3 - Suite Family", 5, 400.00, true);
    static List<Reservation> reservations = new ArrayList<>();

    public static void listReservation() {
        System.out.println("Lista de reservas: ");
        for (Reservation reservation : reservations) {
            System.out.println(reservation.getReservation());
        }
    }









}
