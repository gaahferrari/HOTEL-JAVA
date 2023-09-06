import java.util.List;
import java.util.Scanner;

public class Menu {

    static public void loginMenu(){
        System.out.println("De que forma deseja acessar nosso sistema?");
        System.out.println("1 - Desejo me cadastrar");
        System.out.println("2 - Sou cliente e já possuo um cadastro");
        System.out.println("3 - Sair!");
    }

    static public void getMenu() {
        System.out.println("Digite o numero do serviço que deseja: ");
        System.out.println("1 - Quero me hospedar");
        System.out.println("2 - Meu cadastro");
        System.out.println("3 - Minhas reservas");
        System.out.println("4 - Sair!");
    }

    static public void hotelOptionMenu() {
        System.out.println("Digite o numero do hotel que você deseja se hospedar:");
        System.out.println("1 - Copacabana Palace");
        System.out.println("2 - Hilton");
        System.out.println("3 - IBIS");
    }
}