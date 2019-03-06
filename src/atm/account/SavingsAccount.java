package atm.account;

import atm.account.CurrencyImpl.DOLLAR;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * This class contains additional functionality to have a bank account that applies interest to every transaction.
 *
 */
public final class SavingsAccount extends BankAccount {

    /**
     * Formats numerical values to percentage values. Uses the pattern: 34.241 to 34.241%
     */
    private static final NumberFormat PERCENTAGE = NumberFormat.getPercentInstance();

    /**
     * The interest rate that determines the amount of interest to be added on to any transactions.
     */
    private double interestRate;

    /**
     * Constructs a new savings account. If all of the parameters are valid, the savings account is added to the account database. If any parameters are not valid, the
     * account is removed from the account map and destroyed.
     *
     * @param accountName
     *        The account holder's name.
     * @param accountPin
     *        The account's PIN.
     * @param accountBalance
     *        The account's starting balance.
     * @param interestRate
     *        The account's interest rate to be applied to any transactions.
     * @throws IllegalArgumentException
     *         Thrown if the account balance or interest rate is negative.
     */
    public SavingsAccount(String accountName, Pin accountPin, BigDecimal accountBalance, double interestRate) throws IllegalArgumentException {
        super(accountName, accountPin, accountBalance);
        try {
            if (IS_POSITIVE_AMOUNT(interestRate)) {
                this.interestRate = interestRate;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please enter a positive percentage for your interest rate.");
        }
    }

    /**
     * Formats numerical percentage values to a formatted string. Example: 45.34 to 45.34%, 12.375 to 12.375%.
     *
     * @param percentage
     *        The numerical percentage value to format.
     * @return A string with a formatted percentage value.
     */
    public static String TO_PERCENTAGE_FORMAT(double percentage) {
        PERCENTAGE.setMinimumFractionDigits(2);
        PERCENTAGE.setMaximumFractionDigits(3);
        return PERCENTAGE.format(percentage / 100);
    }

    /**
     * Returns the interest added on during a transaction.
     *
     * @param amount
     *        The amount of the transaction.
     * @return The amount of interest calculated for the transaction.
     */
    public double getInterest(double amount) {
        return (amount * (interestRate));
    }

    /**
     * Withdraws a specified amount to this savings account with interest added on.
     *
     * @param withdrawAmount
     *        The amount to withdraw to this account.
     * @throws IllegalArgumentException
     *         Thrown if the withdraw amount is negative.
     */
    @Override
    public void withdraw(double withdrawAmount) throws IllegalArgumentException {
        super.withdraw(withdrawAmount + getInterest(withdrawAmount));
    }

    /**
     * Deposits a specified amount to this savings account with interest added on.
     *
     * @param depositAmount
     *        The amount to deposit to this account.
     * @throws IllegalArgumentException
     *         Thrown if the deposit amount is negative.
     */
    @Override
    public void deposit(double depositAmount) throws IllegalArgumentException {
        if(accountBalance.compareTo(new BigDecimal(5000)) >= 0 ) {
            super.deposit(depositAmount + getInterest(depositAmount));
        } else {
            super.deposit(depositAmount);
        }
    }

    /**
     * Compares the contents of two savings accounts for equality.
     *
     * @param savingsAccount
     *        The savings account to compare to.
     * @return True, if the two savings accounts are equal, false if otherwise.
     */
    @Override
    public boolean equals(Object savingsAccount) {
        if (this.toString().equalsIgnoreCase(((SavingsAccount)savingsAccount).toString())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return String containing all of the account's information.
     */
    @Override
    public String toString() {
        return "Account Number: " + ACCOUNT_NUMBER + " \nAccount Name: " + accountName + " \nAccount Pin: " + accountPin + " \nAccount Balance: "
                + DOLLAR.TO_US_CURRENCY_FORMAT(accountBalance) + " \nInterest Rate: " + TO_PERCENTAGE_FORMAT(interestRate);
    }

    /**
     * @return The account's interest rate.
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * @return The account's interest rate.
     */
    public static double getLoanInterestRate(double loanInterestRate) {
        return loanInterestRate ;
    }

    /**
     * @return The account's interest rate.
     */
    public static double getWithdrawInterestRate(double withdrawInterestRate) {
        return withdrawInterestRate ;
    }

}
