package entity;

import DAO.DAO;

import java.sql.SQLException;
import java.util.Scanner;

public class Wallet {
    private User user_id;
    String card;
    String expirationDate;


    public void saveDB() {
        try {
            DAO.newCard(this);
            System.out.println("Cartão salvo com sucesso no banco de dados.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar o cartão no banco de dados.");
        }
    }

    public static Wallet registerNewCard(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite número do seu cartão: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
        String card = scanner.next();

        System.out.println("Digite a data que expira seu cartão: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
        String expirationDate = scanner.next();
        return new Wallet(card, expirationDate, user);
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        card = card;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Wallet(String card, String expirationDate, User user_id) {
        this.card = card;
        this.expirationDate = expirationDate;
        this.user_id = user_id;
    }
}