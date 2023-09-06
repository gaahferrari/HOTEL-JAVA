import entity.User;

public class Reservation {

    private String userName;
    private String startDay;
    private String endDay;
    private Hotel hotel;
    static Room room;
    private Long numDays;
    private String cardType;
    private int installments;



    public Reservation(String userName, String startDay, String endDay, Hotel hotel, Room room, long numDays, String cardType, int installments) {
        this.userName = userName;
        this.startDay = startDay;
        this.endDay = endDay;
        this.hotel = hotel;
        this.room = room;
        this.numDays = numDays;
        this.cardType = cardType;
        this.installments = installments;
    }

    public String totalRate() {
        double rate = room.getRate() * numDays;
        return String.format("%.2f", rate);
    }


    public String getReservation() {
        String paymentInfo;
        if (cardType.equals("debit")) {
            paymentInfo = "Pagamento à vista com cartão de débito";
        } else if (cardType.equals("credit")) {
            paymentInfo = "Pagamento parcelado em " + installments + "x com cartão de crédito";
        } else {
            paymentInfo = "Tipo de cartão inválido";
        }

        return "Reserva de " + User.getUserFullName() + " no Hotel " + hotel.getName() + "\n Quarto " + room.getName() + " - "  + room.getGuestPerRoom() + " pessoas por quarto. "
                + "\n Check-In: " + startDay + " Check-Out: " + endDay + "\n Tempo de estádia: " + numDays + " dias(s)" + "\n Preço diária: R$" + room.getRate() + "\n Total: R$" + totalRate()
                + "\n Método de pagamento: " + paymentInfo;
    }
}




