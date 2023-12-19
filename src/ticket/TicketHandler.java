package ticket;
import bank.Bank;
import bank.FrontalSystem;

public class TicketHandler implements Runnable {
    private final String name;
    private final FrontalSystem frontalSystem;
    private final Bank bank;

    public TicketHandler(String name, FrontalSystem frontalSystem, Bank bank) {
        this.name = name;
        this.frontalSystem = frontalSystem;
        this.bank = bank;
    }

    @Override
    public void run() {
        while (true) {
            Ticket ticket = frontalSystem.getTicket();
            System.out.println("Обработчик " + name + " получил заявку " + ticket);

            switch (ticket.getOperationType()) {
                case INCOMING -> bank.decreaseBalance(ticket);
                case OUTGOING -> bank.increaseBalance(ticket);
                default -> throw new IllegalArgumentException("Такой операции не бывает");
            }
        }
    }
}
