package com.mycompany.elevatorsimulator;

import entities.Accounts;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import repositories.AccountsRepository;

/**
 *
 * @author Vlad Adriana
 */
public class ElevatorSimulator {

    public static void main(String[] args) {
        try {
            AccountsRepository accounts = new AccountsRepository();
            Accounts account = new Accounts();
            account.setId(1);
            account.setName("overseer");
            account.setPassword("overseer");
            account.setType("overseer");
            accounts.insert(account);
        } catch (SQLException ex) {
            Logger.getLogger(ElevatorSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
