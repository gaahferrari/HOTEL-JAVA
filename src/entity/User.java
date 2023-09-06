package entity;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String emailAddress;
    private String loginUserName;
    private String passwordUser;
    private static Client client;


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

    public User(String emailAddress, String loginUserName, String passwordUser, Client client) {
        this.emailAddress = emailAddress;
        this.loginUserName = loginUserName;
        this.passwordUser = passwordUser;
        this.client = client;

    }

    public static String getUserFullName(){
        return client.getName() + " " + client.getLastName();
    }

    public String getUser() {
        return "Nome Completo: " + client.getName() + " " + client.getLastName() + "\n Email: " + emailAddress + "\n Username: " + loginUserName
                + "\n Senha cadastrada: " + passwordUser + "\n Data de nascimento: " + client.getAge() + "\n Dados do cartão:  " + client.getCardInfo() +  "\n Endereço completo: " + client.getAddressInfo();
    }

    public static List<User> users = new ArrayList<>();

    public static void userInfo() {
        for (User user : users) {
            System.out.println(user.getUser());
        }
    }
}