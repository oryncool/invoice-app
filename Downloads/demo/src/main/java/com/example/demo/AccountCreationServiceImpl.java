package com.example.demo;

public class AccountCreationServiceImpl implements AccountCreationService{
    private AccountDAO accountDAO;

    public AccountCreationServiceImpl() {
    }

    public AccountCreationServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void create(AccountType accountType, long BankID, String clientID, long AccountID) {
        String accountNumber = String.format("%03d%06d", 1, AccountID);
        if (accountType.getAccountType().equals("CHECKING")) {
            Account account = new CheckingAccount(accountType, accountNumber, clientID, 0.0, true);
            accountDAO.createNewAccount(account);
            System.out.println("Bank account created");
        } else if (accountType.getAccountType().equals("SAVING")) {
            Account account = new SavingAccount(accountType, accountNumber, clientID, 0.0, true);
            accountDAO.createNewAccount(account);
            System.out.println("Bank account created");
        } else if (accountType.getAccountType().equals("FIXED")) {
            Account account = new FixedAccount(accountType, accountNumber, clientID, 0.0, false);
            accountDAO.createNewAccount(account);
            System.out.println("Bank account created");
        }
        else System.out.println("Incorrect account type! Account is not created");

    }
}
