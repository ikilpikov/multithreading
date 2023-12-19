package client;

import bank.FrontalSystem;
import ticket.Ticket;

public class Client implements Runnable {
    private Ticket ticket;
    private FrontalSystem frontalSystem;

    public Client(Ticket ticket, FrontalSystem frontalSystem) {
        this.ticket = ticket;
        this.frontalSystem = frontalSystem;
    }

    @Override
    public void run() {
        frontalSystem.addTicket(ticket);
        System.out.println("Клиент отправил заявку "  + ticket + " в банк.");
    }
}
