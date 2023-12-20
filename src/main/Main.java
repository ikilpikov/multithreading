package main;

import bank.*;
import client.*;
import ticket.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        FrontalSystem frontalSystem = new FrontalSystem(2);
        ExecutorService executorService = Executors.newFixedThreadPool(7);
        Bank bank = new Bank(1000);

        List<TimeoutService> timeoutServices = new ArrayList<>(List.of(
                new TimeoutService(bank),
                new TimeoutService(bank),
                new TimeoutService(bank)
        ));

        try {
            executorService.invokeAll(timeoutServices);
            System.out.println("Денег в банке: " + bank.getBalance());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<Client> clients = new ArrayList<>(List.of(
                new Client(new Ticket("#1", 500, OperationType.OUTGOING),
                        frontalSystem),
                new Client(new Ticket("#2", 1000, OperationType.INCOMING),
                        frontalSystem),
                new Client(new Ticket("#3", 2000, OperationType.INCOMING),
                        frontalSystem),
                new Client(new Ticket("#4", 1500, OperationType.OUTGOING),
                        frontalSystem),
                new Client(new Ticket("#5", 150, OperationType.INCOMING),
                        frontalSystem)
        ));

        List<Thread> clientThreads = clients
                .stream()
                .map(Thread::new)
                .collect(toList());



        List<TicketHandler> ticketHandlers = new ArrayList<>(List.of(
                new TicketHandler("#1", frontalSystem, bank),
                new TicketHandler("#2", frontalSystem, bank)));

        List<Thread> ticketThreads = ticketHandlers
                .stream()
                .map(Thread::new)
                .collect(toList());


        clientThreads.stream().forEach(x -> executorService.submit(x));
        ticketThreads.stream().forEach(x -> executorService.submit(x));
    }
}