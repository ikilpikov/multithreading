package bank;

import ticket.Ticket;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class FrontalSystem {
    /**
     * Требуется коллекция с механизмом доступа FIFO и
     * возможностью блокировки, берем BlockingQueue
     */
    private BlockingQueue<Ticket> tickets;

    public FrontalSystem(int size) {
        tickets = new ArrayBlockingQueue<>(size);
    }

    public void addTicket(Ticket ticket) {
        try {
            tickets.add(ticket);
        } catch (IllegalStateException e) {

        }

    }

    public Ticket getTicket() {
        try {
            return tickets.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
