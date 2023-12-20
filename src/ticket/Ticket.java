package ticket;

public class Ticket {
    private final String name;
    private final long sum;
    private final OperationType operationType;

    public Ticket(String name, long sum, OperationType operationType) {
        this.name = name;
        this.sum = sum;
        this.operationType = operationType;
    }

    public String getName() {
        return name;
    }

    public long getSum() {
        return sum;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    @Override
    public String toString() {
        return "ticket.Ticket{" +
                "name='" + name + '\'' +
                ", sum=" + sum +
                ", operationType=" + operationType +
                '}';
    }
}
