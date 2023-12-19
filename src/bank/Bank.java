package bank;

import ticket.Ticket;

public class Bank {
    private volatile double balance;

    public double getBalance() {
        return balance;
    }

    public Bank(double balance) {
        this.balance = balance;
    }

    public synchronized void increaseBalance(Ticket ticket) {
        balance += ticket.getSum();
        showSucessfulResult(ticket);
    }

    public synchronized void decreaseBalance(Ticket ticket) {
        double sum = ticket.getSum();
        if (balance >= sum) {
            balance -= sum;
            showSucessfulResult(ticket);
        }

    }

    private void showSucessfulResult(Ticket ticket) {
        System.out.println("Заявка "
                + ticket
                + " обработана. Денег на балансе банка: "
                + balance);
    }
}
