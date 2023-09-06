public class Room {

    private String name;

    private  Integer guestPerRoom;

    private Double rate;

    private Boolean available ;

    public Room(String name, Integer guestPerRoom, Double rate, Boolean available){
        this.name = name;
        this.guestPerRoom = guestPerRoom;
        this.rate = rate;
        this.available = available;

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

    public String getRoom() {
        String availability = available ? "Disponível" : "Indisponível";
        return name + " - " + guestPerRoom + " pessoas - R$ " + rate + "/noite - " + availability;
    }

     public boolean setRoom(boolean available){
       return this.available = available;
      }


}
