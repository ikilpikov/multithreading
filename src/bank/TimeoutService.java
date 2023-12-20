package bank;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;

public class TimeoutService implements Callable<Void> {
    private static final int MAX_TIME = 10000;
    private static final int MIN_TIME = 5000;
    private final Bank bank;

    public TimeoutService(Bank bank) {
        this.bank = bank;
    }

    @Override
    public Void call() throws Exception {
        try {
            Thread.sleep(new Random().nextInt(MIN_TIME, MAX_TIME));
            bank.increaseBalance(new Random().nextInt(200, 500));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
