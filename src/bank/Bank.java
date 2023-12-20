package bank;

import ticket.Ticket;

import java.util.concurrent.atomic.AtomicLong;

public class Bank {
    private AtomicLong balance;

    public long getBalance() {
        return balance.get();
    }

    public Bank(long balance) {
        this.balance = new AtomicLong(balance);
    }

    public void increaseBalance(long sum) {
        balance.addAndGet(sum);
    }

    public void increaseBalance(Ticket ticket) {
        balance.addAndGet(ticket.getSum());
        showSucessfulResult(ticket);
    }

    public void decreaseBalance(Ticket ticket) {
        long sum = ticket.getSum();
        if (balance.get() >= sum) {
            balance.updateAndGet(x -> x - sum);
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
