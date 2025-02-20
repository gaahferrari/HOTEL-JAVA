package entity;

import DAO.DAO;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Address {
    private Integer id;
    private User user_id;
    private String street;
    private Integer number;
    private String neighborhood;
    private String zipCode;
    private String city;
    private String state;
    private String country;


    public Address(Integer id, String street, Integer number, String neighborhood, String zipCode, String city, String state, String country, User user_id) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.country = country;
        this.user_id = user_id;
    }

    public void saveDB() {
        try {
            DAO.newAddress(this);
            System.out.println("Endereço salvo com sucesso no banco de dados.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar o endereço no banco de dados.");
        }
    }

    public static Address registerNewAddress(User user) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int id = random.nextInt(1000000);

        System.out.println("Digite o nome da sua rua: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
        String street = scanner.nextLine();

        System.out.println("Digite o número da sua casa: (Usar o símbolo '_' se precisar dar espaço entre caracteres)");
        Integer number = scanner.nextInt();

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

        return new Address(id, street, number, neighborhood, zipCode, city, state, country, user);
    }

    public String getStreet() {
        return street;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}