import DAO.DAO;
import entity.*;

import java.sql.SQLException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        PaymentsImpl payments = new PaymentsImpl();
        User currentUser = null;

        boolean running = true;

        while (running) {
            System.out.println("╔══════════════════════════════════════════╗\n" + "║BEM-VINDO AO SERVIÇO DE RESERVAS DE HOTÉIS║\n" + "╚══════════════════════════════════════════╝");
            Menu.loginMenu();
            int loginOption = scanner.nextInt();
            scanner.nextLine();

            switch (loginOption) {
                case 1:
                    System.out.println("Cadastre-se agora");
                    User user = User.registerNewUser();
                    user.saveDB();

                    Wallet wallet = Wallet.registerNewCard(user);
                    wallet.saveDB();

                    Address address = Address.registerNewAddress(user);
                    address.saveDB();

                    System.out.println("Usuário cadastrado com sucesso! Você será direcionado ao menu inicial.");
                    break;
                case 2:
                    currentUser = Menu.loginMenuAccessClient();
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1:
                            System.out.println("Escolha o hotel e o quarto qual deseja fazer sua reserva");
                            Reservation.registerNewReservation(currentUser).saveDB();
                            break;
                        case 2:
                            if (currentUser != null) {
                                System.out.println("DADOS PESSOAIS");
                                System.out.println("Nome completo: " + currentUser.getName() + " " + currentUser.getLastName());
                                System.out.println("Idade: " + currentUser.getAge());
                                System.out.println("Endereço de email: " + currentUser.getEmailAddress());

                                //  System.out.println("Número do cartão: " + );
                                // System.out.println("Data de expiração: " + paymentInfo.getExpirationDate());

                            }
                            break;

                        case 3:
                            List<Reservation> reservations = DAO.getAllReservations();
                            System.out.println("Reservas:");
                            for (Reservation reservation : reservations) {
                                System.out.printf("Reserva número %d de %s %s%n", reservation.getId(), reservation.getId_user().getName(), reservation.getId_user().getLastName());
                                System.out.printf("Hotel: %s ☆ %d%n", reservation.getId_hotel().getName(), reservation.getId_hotel().getRating());
                                System.out.printf("Quarto: %s (%d pessoas)%n", reservation.getId_room().getName(), reservation.getId_room().getGuestPerRoom());
                                System.out.printf("Dias de hospedagem: de %s a %s (%d diárias)%n", reservation.getStartDay(), reservation.getEndDay(), reservation.getNumDays());
                                System.out.printf("Total da reserva: R$ %.2f%n", reservation.getTotal());
                                System.out.println();
                            }
                            break;
                        case 4:
                            System.out.println("Nosso catálogo de quartos:");
                            List<Room> rooms = DAO.getAllRooms();
                            for (int i = 0; i < rooms.size(); i++) {
                                Room room = rooms.get(i);
                                String availability = room.getavailable() ? "disponível" : "indisponível";
                                System.out.printf("%d. Hotel: %s ☆ %d, %s (%d pessoas, R$ %.2f por diária, %s)%n", i + 1, room.getId_hotel().getName(), room.getId_hotel().getRating(), room.getName(), room.getGuestPerRoom(), room.getRate(), availability);
                            }
                            break;
                        case 5:
                            if (currentUser.getIsAdmin()) {
                                System.out.println("Digite as informações do hotel que deseja adicionar:");
                                Hotel.registerNewHotel().saveDB();
                            } else {
                                System.out.println("Você não tem permissão para adicionar um novo hotel.");
                            }
                            break;
                        case 6:
                            if (currentUser.getIsAdmin()) {
                            System.out.println("Digite as informações do quarto que deseja adicionar");
                            List<Hotel> hotels = DAO.getAllHotels();
                            Room.registerNewRoom(hotels).saveDB();
                            } else {
                                System.out.println("Você não tem permissão para adicionar um novo hotel.");
                            }
                            break;
                        case 7:
                            System.out.println("Obrigado por utilizar nossos serviços!");
                            running = false;
                            break;
                        default:
                            System.out.println("Opção inválida. Por favor, digite uma opção válida.");
                            break;

                    }

                    break;
                case 3:
                    //currentUser
                    break;
                case 4:
                    System.out.println("Obrigado por utilizar nossos serviços!");
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, digite uma opção válida.");
                    break;

            }
        }
    }
}
