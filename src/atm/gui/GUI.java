package atm.gui;

import atm.account.*;
import atm.account.CurrencyImpl.DOLLAR;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

@SuppressWarnings("javadoc")
public final class GUI extends JFrame implements ActionListener {

    /**
     *
     */
    private static String Path = "/Users/kobale/IdeaProjects/Bank ATM/src/History.txt";
    private static String OpenAccountMoney = "$5";

    private static final long serialVersionUID = 1L;
    private MainMenu mainMenuPanel;
    private Login loginPanel;
    private AccountOptions accountOptionsPanel;
    private CreateSavingAccount createSavingAccountPanel;
    private CreateCheckingAccount createCheckingAccountPanel;
    private AccountMenu accountMenuPanel;
    private Loan loanPanel;
    private Deposit depositPanel;
    private Withdraw withdrawPanel;
    private Transfer transferPanel;
    private BankAccount currentAccount;
    private ManagerMenu managerMenuPanel;
    private BankAccount bankAccount;

    private int accountNumber;
    private String accountPin;



    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        this.setVisible(true);
        this.setTitle("ATM");
        this.setSize(500, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new CardLayout());
        this.add(mainMenuPanel = new MainMenu());
        this.add(loginPanel = new Login());
        this.add(createSavingAccountPanel = new CreateSavingAccount());
        this.add(createCheckingAccountPanel = new CreateCheckingAccount());
        this.add(accountMenuPanel = new AccountMenu());
        this.add(depositPanel = new Deposit());
        this.add(loanPanel = new Loan());
        this.add(withdrawPanel = new Withdraw());
        this.add(transferPanel = new Transfer());
        this.add(accountOptionsPanel = new AccountOptions());
        this.add(managerMenuPanel = new ManagerMenu());
        mainMenuPanel.getLoginButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Login Pressed");
                mainMenuPanel.setVisible(false);
                loginPanel.setVisible(true);
            }
        });
        mainMenuPanel.getCreateAccountButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Create Account Pressed");
                mainMenuPanel.setVisible(false);
                accountOptionsPanel.setVisible(true);
            }
        });
        mainMenuPanel.getManagerButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Manager Pressed");
                mainMenuPanel.setVisible(false);
                managerMenuPanel.setVisible(true);
            }
        });
        mainMenuPanel.getExitButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Exit Pressed");
                System.exit(0);
            }
        });
        accountOptionsPanel.getCheckingAccountButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Click checking account");
                accountOptionsPanel.setVisible(false);
                createCheckingAccountPanel.setVisible(true);
            }
        });
        accountOptionsPanel.getSavingAccountButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Click saving account");
                accountOptionsPanel.setVisible(false);
                createSavingAccountPanel.setVisible(true);
            }
        });
        accountOptionsPanel.getBackButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Back Pressed");
                accountOptionsPanel.setVisible(false);
                mainMenuPanel.setVisible(true);
            }
        });
        loginPanel.getBackButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Back Pressed");
                loginPanel.setVisible(false);
                mainMenuPanel.setVisible(true);
            }
        });
        loginPanel.getLoginButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Login Pressed");
                try {
                    BankAccount.createFile(Path);
                    System.out.println(Account.GET_ACCOUNT(loginPanel.getAccountNumber(), loginPanel.getAccountPin()).toString());
                    accountNumber = loginPanel.getAccountNumber();
                    accountPin = loginPanel.getAccountPin();
                    if(Account
                    .GET_ACCOUNT_MAP().get(accountNumber) instanceof SavingsAccount) {
                        currentAccount = (SavingsAccount)Account.GET_ACCOUNT(accountNumber, accountPin);
                        BankAccount.writeTxtFile("Saving account " + accountNumber + " is opened, open fee is " + OpenAccountMoney + ".\n", Path, true);
                    } else {
                        currentAccount = (CheckingsAccount)Account.GET_ACCOUNT(accountNumber, accountPin);
                        BankAccount.writeTxtFile("Checking account " + accountNumber + " is opened, open fee is " + OpenAccountMoney + ".\n", Path, true);
                    }
                    loginPanel.setVisible(false);
                    accountMenuPanel.setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        createSavingAccountPanel.getCreateAccountButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Create Saving Account Pressed");
                System.out.println("Input: " + createSavingAccountPanel.getAccountName() + createSavingAccountPanel.getAccountPin() + createSavingAccountPanel.getConfirmPin());
                try {
                    BankAccount x = new SavingsAccount(createSavingAccountPanel.getAccountName(), new Pin(createSavingAccountPanel.getAccountPin(), createSavingAccountPanel.getConfirmPin()),
                            new BigDecimal(0), 0.03);
                    System.out.println(x.toString());
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });
        createSavingAccountPanel.getBackButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Back Pressed");
                createSavingAccountPanel.setVisible(false);
                accountOptionsPanel.setVisible(true);
            }
        });
        createCheckingAccountPanel.getCreateAccountButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Create Checking Account Pressed");
                System.out.println("Input: " + createCheckingAccountPanel.getAccountName() + createCheckingAccountPanel.getAccountPin() + createCheckingAccountPanel.getConfirmPin());
                try {
                    BankAccount x = new CheckingsAccount(createCheckingAccountPanel.getAccountName(), new Pin(createCheckingAccountPanel.getAccountPin(), createCheckingAccountPanel.getConfirmPin()),
                            new BigDecimal(0),  0.00);
                    System.out.println(x.toString());
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });
        createCheckingAccountPanel.getBackButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Back Pressed");
                createCheckingAccountPanel.setVisible(false);
                accountOptionsPanel.setVisible(true);
            }
        });
        accountMenuPanel.getMakeDepositButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Depoist Pressed");
                accountMenuPanel.setVisible(false);
                depositPanel.setVisible(true);
            }
        });
        accountMenuPanel.getMakeLoanButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Loan Pressed");
                accountMenuPanel.setVisible(false);
                loanPanel.setVisible(true);
            }
        });
        accountMenuPanel.getMakeWithdrawalButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Withdraw Pressed");
                accountMenuPanel.setVisible(false);
                withdrawPanel.setVisible(true);
            }
        });
        accountMenuPanel.getMakeTransferButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Transfer Pressed");
                accountMenuPanel.setVisible(false);
                transferPanel.setVisible(true);
            }
        });
        accountMenuPanel.getCheckBalanceButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Check Balance Pressed");
                try {
                    JOptionPane.showMessageDialog(null, DOLLAR.TO_US_CURRENCY_FORMAT(((BankAccount)Account.GET_ACCOUNT(accountNumber, accountPin)).getAccountBalance()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        accountMenuPanel.getLogoutButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Logout Pressed.");
                accountMenuPanel.setVisible(false);
                mainMenuPanel.setVisible(true);
            }
        });
        depositPanel.getDepositButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Deposit Pressed");
                try {
                    String account = String.valueOf(loginPanel.getAccountNumber());
                    BankAccount.createFile(Path);
                    currentAccount.deposit(depositPanel.getDepositAmount());
                    if(Account.GET_ACCOUNT_MAP().get(Integer.parseInt(account)) instanceof SavingsAccount) {
                        BankAccount.writeTxtFile("Saving account " + account + " deposited " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(depositPanel.getDepositAmount())) + ".\n", Path, true);
                    } else {
                        BankAccount.writeTxtFile("Checking account " + account + " deposited " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(depositPanel.getDepositAmount())) + ".\n", Path, true);
                    }
                    JOptionPane.showMessageDialog(null, "Deposited" + " " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(depositPanel.getDepositAmount())) + " to your account.", "",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        depositPanel.getBackButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Back Pressed");
                depositPanel.setVisible(false);
                accountMenuPanel.setVisible(true);
            }
        });
        withdrawPanel.getWithdrawButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Withdraw Pressed");
                try {
                    String account = String.valueOf(loginPanel.getAccountNumber());
                    currentAccount.withdraw(withdrawPanel.getWithdrawAmount());
                    if(Account.GET_ACCOUNT_MAP().get(Integer.parseInt(account)) instanceof SavingsAccount) {
                        BankAccount.writeTxtFile("Saving account " + account + " withdrawn " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(withdrawPanel.getWithdrawAmount()))
                                + " deduct interest fee " +  DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(withdrawPanel.getWithdrawAmount() * SavingsAccount.getWithdrawInterestRate(0.05))) + ".\n", Path, true);
                    } else {
                        BankAccount.writeTxtFile("Checking account " + account + " withdrawn " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(withdrawPanel.getWithdrawAmount())) + ".\n", Path, true);
                    }
                    JOptionPane.showMessageDialog(null, "Withdrawn" + " " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(withdrawPanel.getWithdrawAmount())) + " to your account.", "",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        withdrawPanel.getBackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                System.out.println("Back Pressed");
                withdrawPanel.setVisible(false);
                accountMenuPanel.setVisible(true);
            }
        });
        loanPanel.getLoanButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Loan Pressed");
                try {
                    String account = String.valueOf(loginPanel.getAccountNumber());
                    BankAccount.createFile(Path);
                    currentAccount.loan(loanPanel.getLoanAmount());
                    if(Account.GET_ACCOUNT_MAP().get(Integer.parseInt(account)) instanceof SavingsAccount) {
                        BankAccount.writeTxtFile("Saving account " + account + " loaned " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(loanPanel.getLoanAmount())) +
                                " Interest Fee: " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(loanPanel.getLoanAmount() * SavingsAccount.getLoanInterestRate(0.05))) + ".\n", Path, true);
                    } else {
                        BankAccount.writeTxtFile("Checking account " + account + " loaned " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(loanPanel.getLoanAmount())) +
                                " Interest Fee: " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(loanPanel.getLoanAmount() * CheckingsAccount.getLoanInterestRate(0.05))) + ".\n", Path, true);
                    }
                    JOptionPane.showMessageDialog(null, "Loaned" + " " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(loanPanel.getLoanAmount())) + " to your account.", "",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loanPanel.getBackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                System.out.println("Back Pressed");
                loanPanel.setVisible(false);
                accountMenuPanel.setVisible(true);
            }
        });
        transferPanel.getTransferButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                String accounts = null;
                System.out.println("Transfer Pressed");
                String account = String.valueOf(loginPanel.getAccountNumber());
                int transferAccount = Integer.parseInt(transferPanel.getTransferAccount());
                if(Account.GET_ACCOUNT_MAP().get(Integer.parseInt(account)) instanceof SavingsAccount) {
                    accounts = "Saving account ";
                } else {
                    accounts = "Checking account ";
                }
                double transferAmount = transferPanel.getTransferAmount();
                try {
                    if(Account.ACCOUNT_EXISTS(transferAccount)) {
                        currentAccount.transfer(transferAccount, transferAmount);
                        if(Account.GET_ACCOUNT_MAP().get(transferAccount) instanceof SavingsAccount) {
                            BankAccount.writeTxtFile(accounts + account + " transferred " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(transferAmount)) + " to " + "saving account " + String.valueOf(transferAccount) + " Transaction Fee: " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(transferAmount * 0.02)) + ".\n", Path, true);
                        } else {
                            BankAccount.writeTxtFile(accounts + account + " transferred " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(transferAmount)) + " to " + "checking account " + String.valueOf(transferAccount) + " Transaction Fee: " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(transferAmount * 0.02)) + ".\n", Path, true);
                        }
                        JOptionPane.showMessageDialog(null, "Transferred" + " " + DOLLAR.TO_US_CURRENCY_FORMAT(new BigDecimal(transferAmount)) + " to"+ transferPanel.getTransferAccount(), "",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        transferPanel.getBackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                System.out.println("Back Pressed");
                transferPanel.setVisible(false);
                accountMenuPanel.setVisible(true);
            }
        });
        managerMenuPanel.getShowTransactionHistoryButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Transaction history Pressed");
                BankAccount.OpenFile();
            }
        });
        managerMenuPanel.getClearTransactionHistoryButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Clear transaction history Pressed");
                BankAccount.ClearFile();
            }
        });
        managerMenuPanel.getBackButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                System.out.println("Back Pressed");
                managerMenuPanel.setVisible(false);
                mainMenuPanel.setVisible(true);
            }
        });

    }

    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == depositPanel.getBackButton()) {

        }
    }

}