package entity;

import  DAO.DAO;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;


public class User {

    private  Integer id;
    private String emailAddress;
    private String loginUserName;
    private String passwordUser;
    private static String name;
    private static String lastName;
    private static String age;

    private static  Boolean isAdmin;

    private static User currentUser;



    public User(Integer id, String name, String lastName, String age, String emailAddress, String loginUserName, String passwordUser, Boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.emailAddress = emailAddress;
        this.loginUserName = loginUserName;
        this.passwordUser = passwordUser;
        this.isAdmin = isAdmin;
    }

    public void saveDB() {
        try {
            DAO.newUSer(this);
            System.out.println("Usuário salvo com sucesso no banco de dados.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar o usuário no banco de dados.");
        }
    }

    public static User registerNewUser() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean isAdmin = false;
        Random random = new Random();
        int id = random.nextInt(1000000);

        System.out.println("Digite seu nome:");
        String name = scanner.nextLine();

        System.out.println("Digite seu sobrenome:");
        String lastName = scanner.nextLine();

        System.out.println("Digite sua data de nascimento: ");
        String age = scanner.next();

        System.out.println("Digite seu usuário: ");
        String loginUserName = scanner.next();

        System.out.println("Digite seu e-mail: ");
        String emailAddress = scanner.next();

        System.out.println("Digite sua senha: ");
        String passwordUser = scanner.next();
        scanner.nextLine();

        return new User(id, name, lastName, age, emailAddress, loginUserName, passwordUser, isAdmin);
    }

    public Integer getId() {
        return this.id;
    }

    public  void setId(Integer id) {
        this.id = id;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddressAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public static Boolean getIsAdmin() {
        return isAdmin;
    }

    public static void setIsAdmin(Boolean isAdmin) {
        User.isAdmin = isAdmin;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }


}