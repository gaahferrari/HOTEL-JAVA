package entity;

public interface Payment {

    void payDebit(double amount, Wallet wallet);

    void payCredit(double amount, int installments, Wallet wallet);
}
