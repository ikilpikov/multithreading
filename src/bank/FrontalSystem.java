package bank;

import ticket.Ticket;

import java.util.ArrayDeque;
import java.util.Queue;

public class FrontalSystem {
    private Queue<Ticket> tickets;

    public FrontalSystem() {
        tickets = new ArrayDeque<Ticket>();
    }

    public void addTicket(Ticket ticket) {
        synchronized (this) {
            while (tickets.size() >= 2) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            tickets.add(ticket);
            notifyAll();
        }
    }

    public Ticket getTicket() {
        synchronized (this) {
            while (tickets.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            Ticket ticket = tickets.poll();
            notifyAll();
            return ticket;
        }
    }
}
