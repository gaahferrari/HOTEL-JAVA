package entity;

import DAO.DAO;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

        static public void loginMenu(){
            System.out.println("De que forma deseja acessar nosso sistema?");
            System.out.println("1 - Desejo me cadastrar");
            System.out.println("2 - Sou cliente e já possuo um cadastro");
            System.out.println("3 - Esqueci minha senha!");
            System.out.println("4 - Sair!");
        }

        static public User loginMenuAccessClient() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("❰❰❰❰ \uD835\uDC0B\uD835\uDC0E\uD835\uDC06\uD835\uDC08\uD835\uDC0D ❱❱❱❱\n");
            System.out.print("Digite seu username:\n");
            String username = scanner.nextLine();
            System.out.print("Digite sua senha:\n");
            String password = scanner.nextLine();
            User user = null;
            try {
                user = DAO.loginMenuAccess(username, password);
                if (user != null) {
                    System.out.println("Olá, " + user.getName() + " " + user.getLastName() + "!");
                    getMenu();
                } else {
                    System.out.println("OOPS! Parece que esse usuário e senha não existem :(");

                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro ao acessar o banco de dados.");
            }
            return user;
        }

        static public void getMenu() {
            System.out.println("Digite o numero do serviço que deseja: ");
            System.out.println("1 - Quero me hospedar");
            System.out.println("2 - Meu cadastro");
            System.out.println("3 - Minhas reservas");
            System.out.println("4 - Sou administrador e desejo cadastrar um hotel");
            System.out.println("5 - Sou administrador e desejo cadastrar um quarto");
            System.out.println("6 - Visualizar lista de quartos e hotéis do catálogo");
            System.out.println("7 - Sair!");
        }


    }

