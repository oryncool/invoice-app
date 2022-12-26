package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class MemoryAccountDAO implements AccountDAO {
    private List<Account> accountList;

    public MemoryAccountDAO() {
        this.accountList = new ArrayList<Account>();
    }

    public MemoryAccountDAO(List<Account> accountList) {
        this.accountList = accountList;
    }

    public List<Account> getClientAccounts(String clientID) {
        return accountList;
    }
    public void createNewAccount(Account account) {
        this.accountList.add(account);
    }
    public void updateAccount(Account account) {

    }
    public List<Account> getClientAccountsByType(String clientID, AccountType accountType) {
        List<Account> clientAccountsByType = new ArrayList<Account>();
        for (Account account: this.accountList) {
            if (account.getAccountType().equals(accountType) && account.getClientID().equals(clientID)) {
                clientAccountsByType.add(account);
            }
        }
        return clientAccountsByType;
    }
    public AccountWithdraw getClientWithdrawAccount(String clientID, String accountID) {
        for (Account account: this.accountList) {
            if (account.getId().equals(accountID) && account.getClientID().equals(clientID)) {
                if (account.getAccountType().getAccountType().equals("SAVING") ||account.getAccountType().getAccountType().equals("CHECKING")) {
                    AccountWithdraw accountWithdraw = (AccountWithdraw) account;
                    return accountWithdraw;
                }
            }
        }
        return null;
    }
    public Account getClientAccount(String clientID, String accountID) {
        for (Account account: this.accountList) {
            if (account.getId().equals(accountID) && account.getClientID().equals(clientID)) {
                return account;
            }
        }
        return null;
    }
}
