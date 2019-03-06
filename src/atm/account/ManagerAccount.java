package atm.account;

/**
 * This class provides special functionality and privileges that allow direct access and control of other accounts. An admin account can change values
 * stored in the accounts and view account information.
 *
 */
public final class ManagerAccount extends Account {

    /**
     * Creates a new admin account with the user's name and PIN. Upon calling the constructor in Account, this account is added to the account database.
     *
     * @param accountName
     *        The account holder's name.
     * @param accountPin
     *        The user's 4 digit PIN.
     */
    public ManagerAccount(String accountName, Pin accountPin) {
        super(accountName, accountPin);
    }

    /**
     * Compares the content of two Admin Accounts for equality.
     *
     * @param adminAccount
     *        The admin account to test equality with.
     * @return True, if the account's are equal, false if otherwise.
     */
    @Override
    public boolean equals(Object adminAccount) {
        if (this.toString().equalsIgnoreCase(((ManagerAccount)adminAccount).toString())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return A string representation of the content of this object.
     */
    @Override
    public String toString() {
        return "Account Number: " + ACCOUNT_NUMBER + "\nAccount Name: " + accountName + "\nAccount Pin: " + accountPin;
    }

}
