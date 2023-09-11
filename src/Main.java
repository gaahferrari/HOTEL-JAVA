import entity.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PaymentsImpl payments = new PaymentsImpl();

        Hotel.hotel1.setRooms(List.of(Hotel.room1, Hotel.room2, Hotel.room3));
        Hotel.hotel2.setRooms(List.of(Hotel.room12, Hotel.room22, Hotel.room32));
        Hotel.hotel3.setRooms(List.of(Hotel.room13, Hotel.room23, Hotel.room33));

        boolean running = true;

        while (running) {
            System.out.println("------- BEM-VINDO AO SERVIÇO DE RESERVAS DE HOTÉIS ------");
            Menu.loginMenu();
            int loginOption = scanner.nextInt();
            scanner.nextLine();

            switch (loginOption) {
                case 1:

                    if (User.users.size() > 0) {
                        System.out.println("Você já está logado, se deseja cadastrar outro usuário selecione a opção SAIR");
                    } else {
                        System.out.println("Cadastre-se agora");
                        System.out.println("Digite seu e-mail: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
                        String emailAddress = scanner.next();

                        System.out.println("Digite seu usuário: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
                        String loginUserName = scanner.next();

                        System.out.println("Digite sua senha: (Usar o símbolo '_' se precisar dar espaço entre caracteres) ");
                        String passwordUser = scanner.next();

                        System.out.println("Digite seu nome: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
                        String name = scanner.next();

                        System.out.println("Digite seu sobrenome: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
                        String lastName = scanner.next();

                        System.out.println("Digite sua data de nascimento: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
                        String age = scanner.next();

                        System.out.println("Digite seu cartão: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
                        String card = scanner.next();

                        System.out.println("Digite o nome da sua rua: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
                        String street = scanner.next();

                        System.out.println("Digite o número da sua casa: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
                        String number = scanner.next();

                        System.out.println("Digite o nome do seu bairro: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
                        String neighborhood = scanner.next();

                        System.out.println("Digite seu CEP: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
                        String zipCode = scanner.next();

                        System.out.println("Digite o nome da sua cidade: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
                        String city = scanner.next();

                        System.out.println("Digite o nome do seu estado: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
                        String state = scanner.next();

                        System.out.println("Digite o nome do seu país: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
                        String country = scanner.next();

                        Wallet wallet = new Wallet(card);
                        Address address = new Address(street, number, neighborhood, zipCode, city, state, country);
                        Client client = new Client(name, lastName, age, wallet, address);

                        User.users.add(new User(emailAddress, loginUserName, passwordUser, client));
                        System.out.println("Usuário cadastrado com sucesso! Você será direcionado ao menu inicial.");
                    }
                    break;
                case 2:
                    if (User.users.size() == 0) {
                        System.out.println("Você ainda não está cadastrado ao sistema!");
                    } else {
                        Menu.getMenu();
                        int option = scanner.nextInt();
                        scanner.nextLine();

                        switch (option) {
                            case 1:
                                Menu.hotelOptionMenu();

                                Integer hotelOption = scanner.nextInt();

                                if (hotelOption == 1) {
                                    System.out.println(Hotel.hotel1.getHotel());
                                    Boolean controler = true;
                                    while (controler) {
                                        System.out.println("Digite o número do quarto que você deseja reservar:");

                                        List<Room> rooms = Hotel.hotel1.getRooms();

                                        Integer roomOption = scanner.nextInt();
                                        Room selectedRoom = null;

                                        for (int i = 0; i < rooms.size(); i++) {
                                            Room room = rooms.get(i);

                                            if (roomOption == i + 1) {
                                                if (!room.getavailable()) {
                                                    System.out.println("Este quarto já está reservado. Por favor, escolha outro quarto.");
                                                } else {
                                                    System.out.println(room.getRoom());
                                                    selectedRoom = room;
                                                    room.setRoom(false);
                                                }
                                            } else {
                                                System.out.println("Opção Inválida. Por favor, digite uma opção válida.");
                                            }

                                            if (selectedRoom != null) {
                                                System.out.println("Digite data de check-in (no formato yyyy-MM-dd):");
                                                String startDay = scanner.next();
                                                LocalDate startDayDate = LocalDate.parse(startDay);

                                                System.out.println("Digite data de check-out (no formato yyyy-MM-dd):");
                                                String endDay = scanner.next();
                                                LocalDate endDayDate = LocalDate.parse(endDay);

                                                System.out.println("Selecione o tipo de pagamento: ");
                                                System.out.println("1 - Débito");
                                                System.out.println("2 - Crédito");
                                                int paymentType = scanner.nextInt();
                                                Double amount = selectedRoom.getRate();
                                                Wallet wallet;
                                                int installments = 1;
                                                if (paymentType == 1) {
                                                     wallet = new Wallet("debit");
                                                    payments.payDebit(amount, wallet);
                                                    System.out.println("Processando pagamento ...");
                                                    try {
                                                        Thread.sleep(2000);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                    System.out.println("PAGAMENTO APROVADO!");
                                                } else if (paymentType == 2) {
                                                     wallet = new Wallet("credit");
                                                    System.out.println("Quantas parcelas deseja dividir o pagamento? (Até 12X sem juros)");
                                                    installments = scanner.nextInt();
                                                    payments.payCredit(amount, installments, wallet);
                                                    System.out.println("Processando pagamento ...");
                                                    try {
                                                        Thread.sleep(2000);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                    System.out.println("PAGAMENTO APROVADO!");
                                                } else {
                                                    System.out.println("Opção inválida");
                                                    break;
                                                }

                                                long numDays = ChronoUnit.DAYS.between(startDayDate, endDayDate);


                                                Reservation reservation = new Reservation(User.getUserFullName(), startDay, endDay, Hotel.hotel1, selectedRoom, numDays, wallet, installments);
                                                Hotel.reservations.add(reservation);

                                                controler = false;
                                                System.out.println("Reserva feita com sucesso! Você será direcionado ao menu incial.");
                                                break;
                                            } else {
                                                System.out.println("Quarto inválido. Tente novamente.");
                                            }
                                        }
                                    }
                                } else if (hotelOption == 2) {
                                    System.out.println(Hotel.hotel2.getHotel());
                                    Boolean controler = true;
                                    while (controler) {
                                        System.out.println("Digite o número do quarto que você deseja reservar:");

                                        List<Room> rooms = Hotel.hotel2.getRooms();

                                        Integer roomOption = scanner.nextInt();
                                        Room selectedRoom = null;

                                        for (int i = 0; i < rooms.size(); i++) {
                                            Room room = rooms.get(i);

                                            if (roomOption == i + 1) {
                                                if (!room.getavailable()) {
                                                    System.out.println("Este quarto já está reservado. Por favor, escolha outro quarto.");
                                                } else {
                                                    System.out.println(room.getRoom());
                                                    selectedRoom = room;
                                                    room.setRoom(false);
                                                }
                                            } else {
                                                System.out.println("Opção Inválida. Por favor, digite uma opção válida.");
                                            }

                                            if (selectedRoom != null) {

                                                System.out.println("Digite data de check-in (no formato yyyy-MM-dd):");
                                                String startDay = scanner.next();
                                                LocalDate startDayDate = LocalDate.parse(startDay);

                                                System.out.println("Digite data de check-out (no formato yyyy-MM-dd):");
                                                String endDay = scanner.next();
                                                LocalDate endDayDate = LocalDate.parse(endDay);

                                                System.out.println("Selecione o tipo de pagamento: ");
                                                System.out.println("1 - Débito");
                                                System.out.println("2 - Crédito");
                                                int paymentType = scanner.nextInt();

                                                Double amount = selectedRoom.getRate();
                                                Wallet wallet;
                                                int installments = 1;
                                                if (paymentType == 1) {
                                                    wallet = new Wallet("debit");
                                                    payments.payDebit(amount, wallet);
                                                    System.out.println("PAGAMENTO APROVADO!");
                                                } else if (paymentType == 2) {
                                                    wallet = new Wallet("credit");
                                                    System.out.println("Quantas parcelas deseja dividir o pagamento? (Até 12X sem juros)");
                                                    installments = scanner.nextInt();
                                                    payments.payCredit(amount, installments, wallet);
                                                    System.out.println("PAGAMENTO APROVADO!");
                                                } else {
                                                    System.out.println("Opção inválida");
                                                    break;
                                                }

                                                long numDays = ChronoUnit.DAYS.between(startDayDate, endDayDate);

                                                Hotel.reservations.add(new Reservation(User.getUserFullName(), startDay, endDay, Hotel.hotel2, selectedRoom, numDays, wallet, installments));
                                                controler = false;
                                                System.out.println("Reserva feita com sucesso! Você será direcionado ao menu incial.");
                                                break;
                                            } else {
                                                System.out.println("Quarto inválido. Tente novamente.");
                                            }
                                        }
                                    }
                                } else if (hotelOption == 3) {
                                    System.out.println(Hotel.hotel3.getHotel());
                                    Boolean controler = true;
                                    while (controler) {
                                        System.out.println("Digite o número do quarto que você deseja reservar:");

                                        List<Room> rooms = Hotel.hotel3.getRooms();

                                        Integer roomOption = scanner.nextInt();
                                        Room selectedRoom = null;

                                        for (int i = 0; i < rooms.size(); i++) {
                                            Room room = rooms.get(i);

                                            if (roomOption == i + 1) {
                                                if (!room.getavailable()) {
                                                    System.out.println("Este quarto já está reservado. Por favor, escolha outro quarto.");
                                                } else {
                                                    System.out.println(room.getRoom());
                                                    selectedRoom = room;
                                                    room.setRoom(false);
                                                }
                                            } else {
                                                System.out.println("Opção Inválida. Por favor, digite uma opção válida.");
                                            }

                                            if (selectedRoom != null) {

                                                System.out.println("Digite data de check-in (no formato yyyy-MM-dd):");
                                                String startDay = scanner.next();
                                                LocalDate startDayDate = LocalDate.parse(startDay);

                                                System.out.println("Digite data de check-out (no formato yyyy-MM-dd):");
                                                String endDay = scanner.next();
                                                LocalDate endDayDate = LocalDate.parse(endDay);

                                                System.out.println("Selecione o tipo de pagamento: ");
                                                System.out.println("1 - Débito");
                                                System.out.println("2 - Crédito");
                                                int paymentType = scanner.nextInt();

                                                Double amount = selectedRoom.getRate();
                                                Wallet wallet;
                                                int installments = 1;
                                                if (paymentType == 1) {
                                                    wallet = new Wallet("debit");
                                                    payments.payDebit(amount, wallet);
                                                    System.out.println("PAGAMENTO APROVADO!");
                                                } else if (paymentType == 2) {
                                                    wallet = new Wallet("credit");
                                                    System.out.println("Quantas parcelas deseja dividir o pagamento? (Até 12X sem juros)");
                                                    installments = scanner.nextInt();
                                                    payments.payCredit(amount, installments, wallet);
                                                    System.out.println("PAGAMENTO APROVADO!");
                                                } else {
                                                    System.out.println("Opção inválida");
                                                    break;
                                                }
                                                long numDays = ChronoUnit.DAYS.between(startDayDate, endDayDate);

                                                Hotel.reservations.add(new Reservation(User.getUserFullName(), startDay, endDay, Hotel.hotel3, selectedRoom, numDays, wallet, installments));
                                                controler = false;
                                                System.out.println("Reserva feita com sucesso! Você será direcionado ao menu incial.");
                                                break;
                                            } else {
                                                System.out.println("Quarto inválido. Tente novamente.");
                                            }
                                        }
                                    }

                                } else {
                                    System.out.println("Opção Inválida");
                                }
                                break;
                            case 2:
                                System.out.println("DADOS PESSOAS");
                                User.userInfo();
                                break;
                            case 3:
                                if (Hotel.reservations.size() == 0) {
                                    System.out.println("Você ainda não fez nenhuma reserva :(");
                                    break;
                                } else {
                                    Hotel.listReservation();
                                    break;
                                }

                            case 4:
                                System.out.println("Obrigado por utilizar nossos serviços!");
                                running = false;
                                break;
                            default:
                                System.out.println("Opção inválida. Por favor, digite uma opção válida.");
                                break;

                        }
                    }
                    break;
                case 3:
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