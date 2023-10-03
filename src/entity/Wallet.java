package entity;

import DAO.DAO;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Wallet {
    Integer id;
    String card;
    String expirationDate;
    Integer installments;
    User user_id;



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
        Integer installments = null;
        Random random = new Random();
        int id = random.nextInt(1000000);

        System.out.println("Digite número do seu cartão: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
        String card = scanner.next();

        System.out.println("Digite a data que expira seu cartão: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
        String expirationDate = scanner.next();
        return new Wallet(id, card, expirationDate, installments, user);
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

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Wallet(Integer id, String card, String expirationDate, Integer installments, User user_id) {
        this.id = id;
        this.card = card;
        this.expirationDate = expirationDate;
        this.installments = installments;
        this.user_id = user_id;
    }
}