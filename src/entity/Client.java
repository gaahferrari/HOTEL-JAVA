package entity;
public class Client {
    private String name;
    private String lastName;
    private String age;
    private Wallet wallet;
    private Address address;

    public Client(String name, String lastName, String age, Wallet wallet, Address address) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.wallet = wallet;
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

   public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Address getAddress() {
       return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAddressInfo() {
        return "Endereço: " +  address.getStreet() + " n° " + address.getNumber() + "\n Bairro: " + address.getNeighborhood()
                + "\n CEP: " + address.getZipCode() + "\n Cidade: " + address.getCity() + "\n Estado: " + address.getState() +  "\n País: " + address.getCountry();
    }

    public String getCardInfo(){
        return wallet.getCard();
    }

}
