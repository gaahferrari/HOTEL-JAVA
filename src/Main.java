import DAO.DAO;
import entity.*;

import java.sql.SQLException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
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

                    switch (option)
                    {
                        case 1:
                            System.out.println("Escolha o hotel e o quarto qual deseja fazer sua reserva");
                            Reservation.registerNewReservation(currentUser).saveDB();
                            break;
                        case 2:
                            if (currentUser != null) {
                                Wallet currentUserPayment = DAO.getPayment(currentUser);
                                Address currentUserAddress = DAO.getAddress(currentUser);
                                System.out.println("DADOS PESSOAIS");
                                System.out.println("Nome completo: " + currentUser.getName() + " " + currentUser.getLastName());
                                System.out.println("Idade: " + currentUser.getAge());
                                System.out.println("Endereço de email: " + currentUser.getEmailAddress());
                                System.out.println("Endereço: " + currentUserAddress.getStreet() + " " + currentUserAddress.getNumber());
                                System.out.println("CEP: " + currentUserAddress.getZipCode());
                                System.out.println("Bairro: " + currentUserAddress.getNeighborhood());
                                System.out.println("Cidade: " + currentUserAddress.getCity());
                                System.out.println("Estado: " + currentUserAddress.getState());
                                System.out.println("País: " + currentUserAddress.getCountry());
                                System.out.println("DADOS DE PAGAMENTO:");
                                System.out.println("Número do cartão: " + currentUserPayment.getCard());
                                System.out.println("Data de expiração: " + currentUserPayment.getExpirationDate());

                            }
                            break;

                        case 3:
                            if (currentUser != null) {
                                List<Reservation> reservations = DAO.getAllReservations(currentUser);
                                System.out.println("Reservas:");
                                if (reservations != null && !reservations.isEmpty()) {
                                    for (Reservation reservation : reservations) {
                                        System.out.println("Reserva número " + reservation.getId() + " de " + reservation.getId_user().getName() + " " + reservation.getId_user().getLastName());
                                        System.out.println("Hotel: " + reservation.getId_hotel().getName() + " ☆ " + reservation.getId_hotel().getRating());
                                        System.out.println("Quarto: " + reservation.getId_room().getName() + " (" + reservation.getId_room().getGuestPerRoom() + " pessoas)");
                                        System.out.println("Dias de hospedagem: de " + reservation.getStartDay() + " a " + reservation.getEndDay() + " (" + reservation.getNumDays() + " diárias)");
                                        System.out.println("Total da reserva: R$ " + reservation.getTotal());
                                    }
                                } else {
                                    System.out.println("Você ainda não possui nenhuma reserva!");
                                }
                            }

                            break;
                        case 4:
                            System.out.println("Qual reserva deseja cancelar?");
                            if (currentUser != null) {
                                List<Reservation> reservations = DAO.getAllReservations(currentUser);
                                System.out.println("Reservas:");
                                if (reservations != null && !reservations.isEmpty()) {
                                    for (Reservation reservation : reservations) {
                                        System.out.println("Reserva número " + reservation.getId() + " de " + reservation.getId_user().getName() + " " + reservation.getId_user().getLastName());
                                        System.out.println("Hotel: " + reservation.getId_hotel().getName() + " ☆ " + reservation.getId_hotel().getRating());
                                        System.out.println("Quarto: " + reservation.getId_room().getName() + " (" + reservation.getId_room().getGuestPerRoom() + " pessoas)");
                                        System.out.println("Dias de hospedagem: de " + reservation.getStartDay() + " a " + reservation.getEndDay() + " (" + reservation.getNumDays() + " diárias)");
                                        System.out.println("Total da reserva: R$ " + reservation.getTotal());

                                    }
                                    System.out.print("Digite o número da reserva que deseja cancelar: ");
                                    int reservationId = scanner.nextInt();
                                    DAO.cancelReservation(reservationId);
                                    System.out.println("Reserva cancelada com sucesso!");
                                } else {
                                    System.out.println("Você ainda não possui nenhuma reserva, neste caso você não pode cancelar!");
                                }
                            }
                            break;
                        case 5:
                            System.out.println("Nosso catálogo de quartos:");
                            List<Room> rooms = DAO.getAllRooms();
                            for (int i = 0; i < rooms.size(); i++) {
                                Room room = rooms.get(i);
                                String availability = room.getavailable() ? "disponível" : "indisponível";
                                System.out.println((i + 1) + ". Hotel: " + room.getId_hotel().getName() + " ☆ " + room.getId_hotel().getRating() + ", " + room.getName() + " (" + room.getGuestPerRoom() + " pessoas, R$ " + room.getRate() + " por diária, " + availability + ")");
                            }
                            break;
                        case 6:
                            if (currentUser.getIsAdmin()) {
                                System.out.println("Digite as informações do hotel que deseja adicionar:");
                                Hotel.registerNewHotel().saveDB();
                            } else {
                                System.out.println("Você não tem permissão para adicionar um novo hotel.");
                            }
                            break;
                        case 7:
                            if (currentUser.getIsAdmin()) {
                                System.out.println("Digite as informações do quarto que deseja adicionar");
                                List<Hotel> hotels = DAO.getAllHotels();
                                Room.registerNewRoom(hotels).saveDB();
                            } else {
                                System.out.println("Você não tem permissão para adicionar um novo hotel.");
                            }
                            break;
                        case 8:
                            System.out.println("Obrigado por utilizar nossos serviços!");
                            running = false;
                            break;
                        default:
                            System.out.println("Opção inválida. Por favor, digite uma opção válida.");
                            break;

                    }

                    break;
                case 3:
                    currentUser = Menu.updatePasswordClient();

                    if (currentUser != null) {

                        System.out.println("Nome: " + currentUser.getName());
                        System.out.println("Sobrenome: " + currentUser.getLastName());
                        System.out.println("Email: " + currentUser.getEmailAddress());
                        System.out.println("Digite sua nova senha: ");
                        String password = scanner.nextLine();

                        DAO.updatePassword(currentUser, password);
                        System.out.println("Senha atualizada com sucesso.");
                    } else {
                        System.out.println("Usuário ou senha incorretos.");
                    }

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
