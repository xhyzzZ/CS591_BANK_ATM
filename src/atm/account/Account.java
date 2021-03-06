package atm.account;

import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class provides the framework and methods for creating and utilizing different types of accounts.
 *
 */
public abstract class Account {

    /**
     * Provides means of storage and retrieval of accounts based on their account number.
     */
    private static final HashMap<Integer, Account> ACCOUNT_MAP = new HashMap<>();

    /**
     * Provides the date and time formatted for use in recording account history.
     */
    static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

    /**
     * Unique 6 digit number for identifying an account.
     */
    final int ACCOUNT_NUMBER;

    /**
     * The account holder's name.
     */
    String accountName;

    /**
     * The account's 4 digit PIN.
     */
    Pin accountPin;

    /**
     * Constructor called by subclasses that creates an account with the user's name, 4 digit PIN, and account history. The account history for all of accounts
     * starts with an entry with the date and time the account was created. When the account is created, it is added to the account database.
     *
     * @param accountName
     *        The account holder's name.
     * @param accountPin
     *        The account's 4 digit PIN.
     */
    Account(String accountName, Pin accountPin) {
        ACCOUNT_NUMBER = GENERATE_ACCOUNT_NUMBER();
        this.accountName = accountName;
        this.accountPin = accountPin;
        ACCOUNT_MAP.put(ACCOUNT_NUMBER, this);
    }

    /**
     * Generates a random 6 digit account number that is currently not in use by any accounts in the account database.
     *
     * @return Unique 6 digit account number.
     */
    private static final int GENERATE_ACCOUNT_NUMBER() {
        int accountNumber;
        do {
            accountNumber = (int)(Math.random() * 1_000_000);
            if ((accountNumber < 100_000) || (accountNumber > 999_999)) {
                continue;
            } else if (ACCOUNT_MAP.containsKey(accountNumber)) {
                continue;
            } else {
                break;
            }
        } while (true);
        return accountNumber;
    }

    /**
     * Retrieves an account from the account database with a specified account number. The method also checks to make sure the correct PIN has been entered.
     *
     * @param accountNumber
     *        The account to be returned.
     * @param accountPin
     *        The account's PIN.
     * @return The account with the given login credentials.
     * @throws IllegalArgumentException
     *         Thrown if an incorrect PIN is entered.
     * @throws NullPointerException
     *         Thrown if an account with the given account number cannot be found or does not exist.
     */
    public static final Account GET_ACCOUNT(int accountNumber, String accountPin) throws IllegalArgumentException, NullPointerException {
        if (ACCOUNT_EXISTS(accountNumber) && Pin.IS_CORRECT_PIN(accountNumber, accountPin)) {
            return ACCOUNT_MAP.get(accountNumber);
        } else {
            return null;
        }
    }

    /**
     * Checks the account database to see if an account with the given account number has been created.
     *
     * @param accountNumber
     *        The account number to check for.
     * @return True, if an account with the given account number has been found in the account map.
     * @throws NullPointerException
     *         Thrown if an account with the given account number cannot be found or does not exist.
     */
    public static final boolean ACCOUNT_EXISTS(int accountNumber) throws NullPointerException {
        if (ACCOUNT_MAP.containsKey(accountNumber)) {
            return true;
        } else {
            throw new NullPointerException("This account does not exist. Please check the account number.");
        }
    }


    /**
     * @return A reference to the account map.
     */
    public static final HashMap<Integer, Account> GET_ACCOUNT_MAP() {
        return ACCOUNT_MAP;
    }

    /**
     * Checks the content of two accounts to see if they are equal.
     *
     * @param account
     *        The account to test equality with.
     * @return True, if the accounts are equal, false otherwise.
     */
    @Override
    public abstract boolean equals(Object account);

    /**
     * @return A string representation of all of the data contained in this account.
     */
    @Override
    public abstract String toString();

    /**
     * @return The account's number.
     */
    public final int getAccountNumber() {
        return ACCOUNT_NUMBER;
    }

    /**
     * @return The account holder's name.
     */
    public final String getAccountName() {
        return accountName;
    }

    /**
     * @return The account's 4 digit PIN.
     */
    public final Pin getAccountPin() {
        return accountPin;
    }

    /**
     * @param accountName
     *        The new account holder's name.
     */
    final void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @param accountPin
     *        The new account PIN.
     */
    final void setAccountPin(Pin accountPin) {
        this.accountPin = accountPin;
    }

}
