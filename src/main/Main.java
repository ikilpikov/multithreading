package main;

import bank.*;
import client.*;
import ticket.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        FrontalSystem frontalSystem = new FrontalSystem();

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

        Bank bank = new Bank(1000);

        List<TicketHandler> ticketHandlers = new ArrayList<>(List.of(
                new TicketHandler("#1", frontalSystem, bank),
                new TicketHandler("#2", frontalSystem, bank)));

        List<Thread> ticketThreads = ticketHandlers
                .stream()
                .map(Thread::new)
                .collect(toList());

        clientThreads.stream().forEach(Thread::start);
        ticketThreads.stream().forEach(Thread::start);
    }
}