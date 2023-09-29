package entity;

import DAO.DAO;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;


public class Hotel {
    private Integer id;
    private String name;
    private Integer rating;



    public void saveDB() {
        try {
            DAO.newHotel(this);
            System.out.println("✔ Hotel cadastrado com sucesso no banco de dados.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("✘ Erro ao cadastrar o hotel no banco de dados.");
        }
    }

    public static Hotel registerNewHotel() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Random random = new Random();
        int id = random.nextInt(1000000);

        System.out.println("✚ Digite o nome do Hotel: ");
        String name = scanner.nextLine();

        System.out.println("✚ Digite a nota de avaliação do Hotel: ");
        Integer rating = scanner.nextInt();

        return new Hotel(id, name, rating);
    }

    public Hotel(Integer id, String name, Integer rating){
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    //Getters
    public Integer getId() {
        return id;
    }
    public String getName(){
        return this.name;
    }

    public Integer getRating(){
        return this.rating;
    }



    //Setters

    public void setId(Integer id) {
        this.id = id;}

    public String setName(String name){
        return this.name = name;
    }

    public Integer setRating(Integer rating){
        return this.rating = rating;
    }




    //Prints





}
