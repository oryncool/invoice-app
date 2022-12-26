package com.example.demo;

import java.util.Scanner;

public class MyCLI implements CLIUI{
    private Scanner scanner;

    public MyCLI() {
        this.scanner = new Scanner(System.in);
    }

    public MyCLI(Scanner scanner) {

    }

    public double requestClientAmount() {
        return scanner.nextDouble();
    }

    public String requestClientAccountNumber() {
        return scanner.next();
    }

    public AccountType requestAccountType() {
        return new AccountType(scanner.next());
    }


}
