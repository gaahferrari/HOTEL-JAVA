package entity;

public class PaymentsImpl implements Payment  {

    @Override
    public void payDebit(double amount, Wallet wallet) {
        if (wallet.getCard().equals("debit")) {
        } else {
            System.out.println("Cartão inválido para pagamento à vista");
        }
    }

    @Override
    public void payCredit(double amount, int installments, Wallet wallet) {
        if (wallet.getCard().equals("credit")) {
            if (installments > 1 && installments <= 12) {
                for (int i = 1; i <= installments; i++) {
                }
            } else {
                System.out.println("Número de parcelas inválido");
            }
        } else {
            System.out.println("Cartão inválido para pagamento parcelado");
        }
    }

}
