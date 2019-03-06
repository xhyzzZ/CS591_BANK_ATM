# Design Choice
Basically, I divide account object and GUI class separately. I have three packages and a main function in atm package. The way I design the class in this way is that it's easier for us to change or make some updates without destroying original structure, like when you want to change UI, you can make changes in GUI, you want to change account properties, you can change it in account package.  
The main entrance to the program is **Main.java**. I stored the history transaction log in History.txt file, it is like cache. Even you exit the ATM, the data will maintain in the file and you can look it locally, so it is very convenient. Also, you can clear the data in the ATM using manager account. That's the best way I can imagine to view and clear history transaction.  
There are two things you need to pay attention:  
One is that you should change the path for your own **History.txt** in BankAccount.java.  
Second is that when you create a new account and want to login, you need the account number which are six digits **generated in command line**.
- atm.account
  - atm.account.CurrencyImpl
    - DOLLAR
    - FRANC
    - RMB
  - [Account](#Class-atm.account.Account(Abstract))
  - BankAccount
  - CheckingsAccount
  - SavingsAccount
  - ManagerAccount
  - Currency
  - Pin
- atm.gui
  - AccountMenu
  - AccountOptions
  - CreateCheckingAccount
  - CreateSavingAccount
  - Deposit
  - GUI
  - Loan
  - Login
  - MainMenu
  - ManagerMenu
  - Transfer
  - Withdraw
- atm.util
  - Constants
- main.java

## Class atm.account.Account(Abstract)
This class provides the framework and methods for creating and utilizing different types of accounts.

### Variables
- ACCOUNT_MAP
```
/**
* Provides means of storage and retrieval of accounts based on their account number.
*/

private static final HashMap<Integer, Account> ACCOUNT_MAP = new HashMap<>();

```
- DATE_TIME
```
/**
* Provides the date and time formatted for use in recording account history.
*/

static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

```
- ACCOUNT_NUMBER
```
/**
* Unique 6 digit number for identifying an account.
*/

final int ACCOUNT_NUMBER;
```
- accountName
```
/**
* The account holder's name.
*/

String accountName;
```
- accountPin
```
/**
* The account's 4 digit PIN.
*/

Pin accountPin;
```
### Constructor

```
/**
* Constructor called by subclasses that creates an account with the user's name, 4 digit PIN, and account history. The account history for all of accounts
* starts with an entry with the date and time the account was created. When the account is created, it is added to the account database.
*
* @param accountName
*       The account holder's name.
* @param accountPin
*       The account's 4 digit PIN.
*/

Account(String accountName, Pin accountPin) {
    ACCOUNT_NUMBER = GENERATE_ACCOUNT_NUMBER();
    this.accountName = accountName;
    this.accountPin = accountPin;
    ACCOUNT_MAP.put(ACCOUNT_NUMBER, this);
}
```
### Methods
- GENERATE_ACCOUNT_NUMBER

```
/**
* Generates a random 6 digit account number that is currently not in use by any accounts in the account database.
*
* @return Unique 6 digit account number.
*/

private static final int GENERATE_ACCOUNT_NUMBER()
```
- GET_ACCOUNT
```
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
 
public static final Account GET_ACCOUNT(int accountNumber, String accountPin) throws IllegalArgumentException, NullPointerException
```
- ACCOUNT_EXISTS
```
/**
 * Checks the account database to see if an account with the given account number has been created.
 *
 * @param accountNumber
 *        The account number to check for.
 * @return True, if an account with the given account number has been found in the account map.
 * @throws NullPointerException
 *         Thrown if an account with the given account number cannot be found or does not exist.
 */

public static final boolean ACCOUNT_EXISTS(int accountNumber) throws NullPointerException

```
- GET_ACCOUNT_MAP()
```
/**
 * @return A reference to the account map.
 */
     
public static final HashMap<Integer, Account> GET_ACCOUNT_MAP()
```
## Class atm.account.BankAccount extends Account
### Variables
- Path(The Path which stores transaction history, change it in your computer)
```
private static String Path = "/Users/kobale/IdeaProjects/Bank ATM/src/History.txt";
```
- accountBalance
```
/**
 * Stores the user's account balance saved to two significant decimal places.
 */
 
 BigDecimal accountBalance;
```
### Constructor
```
/**
 * Opens a new bank account with the user's name, PIN, and starting balance. Upon calling the constructor in Account, this account is added to the account database.
 * If any of the parameters are not valid, the account is closed and removed from the account map.
 *
 * @param accountName
 *        The account holder's name.
 * @param accountPin
 *        The user's 4 digit PIN.
 * @param accountBalance
 *        The account's starting balance.
 * @throws IllegalArgumentException
 *         Thrown if a negative amount is entered for the account's balance.
 */
 
public BankAccount(String accountName, Pin accountPin, BigDecimal accountBalance) throws IllegalArgumentException

```
### Methods
- IS_POSITIVE_AMOUNT
```
/**
 * Performs a check to see if the user has entered a positive amount for any currency values.
 *
 * @param amount
 *        The amount to check if positive.
 * @return True, if the amount is greater than zero.
 * @throws IllegalArgumentException
 *         Thrown if the amount is less than zero.
 */
 
public static final boolean IS_POSITIVE_AMOUNT(double amount) throws IllegalArgumentException
```
- hasSufficientBalance
```
/**
 * Performs a check to see if the user has a sufficient balance to complete a transaction.
 *
 * @param amount
 *        The amount to see if the user has in their account balance.
 * @return True, if the user has enough money in their balance to complete a transaction.
 * @throws IllegalArgumentException
 *         Thrown if the amount the user needs is greater than their balance.
 */
 
public final boolean hasSufficientBalance(double amount) throws IllegalArgumentException
```
- transfer
```
/**
 * User chooses another account to send money to. Money is withdrawn from their account and deposited in the account they specify. This method adds interest if
 * the accounts in use are savings accounts.
 *
 * @param receivingAccount
 *        The account to receive the money.
 * @param transferAmount
 *        The amount of money to transfer.
 * @throws IllegalArgumentException
 *         Thrown if the user enters a negative amount of money to transfer, or they have an insufficient balance.
 * @throws NullPointerException
 *         Thrown if the account the user wants to transfer money to does not exist.
 */
 
public final void transfer(int receivingAccount, double transferAmount) throws IllegalArgumentException, NullPointerException
```
- deposit
```
/**
 * Deposits a specified amount of money into the user's account.
 *
 * @param depositAmount
 *        The amount to deposit.
 * @throws IllegalArgumentException
 *         Thrown if the deposit amount is negative.
 */
 
public void deposit(double depositAmount) throws IllegalArgumentException
```
- loan
```
/**
 * Loans a specified amount of money into the user's account.
 *
 * @param loanAmount
 *        The amount to deposit.
 * @throws IllegalArgumentException
 *         Thrown if the deposit amount is negative.
 */
 
public void loan(double loanAmount) throws IllegalArgumentException
```
- withdraw
```
/**
 * Withdrawals a specified amount of money from the user's account.
 *
 * @param withdrawalAmount
 *        The amount to withdrawal.
 * @throws IllegalArgumentException
 *         Thrown if the withdrawal amount is negative or the user has an insufficient balance.
 */
 
public void withdraw(double withdrawalAmount) throws IllegalArgumentException
```
- createFile
```
/**
 * Create the transaction history file.
 */
 
public static boolean createFile(String path)
```
- writeTxtFile
```
/**
 * Write information to transaction file
 */
 
public static boolean writeTxtFile(String content, String path, boolean append)
```
- OpenFile
```
/**
 * Open the transaction history file.
 */
 
public static void OpenFile()
```
- ClearFile
```
/**
 * Clear the transaction history file.
 */
public static void ClearFile()
```
## Class atm.account.SavingsAccount extends BankAccount
### Variables
- PERCENTAGE
```
/**
 * Formats numerical values to percentage values. Uses the pattern: 34.241 to 34.241%
 */
 
private static final NumberFormat PERCENTAGE
```
- interestRate
```
/**
 * The interest rate that determines the amount of interest to be added on to any transactions.
 */
 
private double interestRate;
```
### Constructor
```
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
 
public SavingsAccount(String accountName, Pin accountPin, BigDecimal accountBalance, double interestRate) throws IllegalArgumentException
```
### Methods
- TO_PERCENTAGE_FORMAT
```
/**
 * Formats numerical percentage values to a formatted string. Example: 45.34 to 45.34%, 12.375 to 12.375%.
 *
 * @param percentage
 *        The numerical percentage value to format.
 * @return A string with a formatted percentage value.
 */
 
public static String TO_PERCENTAGE_FORMAT(double percentage)
```
- getInterest
```
/**
 * Returns the interest added on during a transaction.
 *
 * @param amount
 *        The amount of the transaction.
 * @return The amount of interest calculated for the transaction.
 */
 
public double getInterest(double amount)
```
- withdraw
```
/**
 * Withdraws a specified amount to this savings account with interest added on.
 *
 * @param withdrawAmount
 *        The amount to withdraw to this account.
 * @throws IllegalArgumentException
 *         Thrown if the withdraw amount is negative.
 */
 
@Override
public void withdraw(double withdrawAmount) throws IllegalArgumentException
```
- deposit
```
/**
 * Deposits a specified amount to this savings account with interest added on.
 *
 * @param depositAmount
 *        The amount to deposit to this account.
 * @throws IllegalArgumentException
 *         Thrown if the deposit amount is negative.
 */
 
@Override
public void deposit(double depositAmount) throws IllegalArgumentException
```
## Class atm.account.CheckingsAccount extends BankAccount
### Vairables
- PERCENTAGE
```
/**
 * Formats numerical values to percentage values. Uses the pattern: 34.241 to 34.241%
 */
 
private static final NumberFormat PERCENTAGE
```
- interestRate
```
/**
 * The interest rate that determines the amount of interest to be added on to any transactions.
 */
 
private double interestRate
```
### Constructor
```
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
 
public CheckingsAccount(String accountName, Pin accountPin, BigDecimal accountBalance, double interestRate) throws IllegalArgumentException
```
### Methods
- TO_PERCENTAGE_FORMAT
```
/**
 * Formats numerical percentage values to a formatted string. Example: 45.34 to 45.34%, 12.375 to 12.375%.
 *
 * @param percentage
 *        The numerical percentage value to format.
 * @return A string with a formatted percentage value.
 */
 
public static String TO_PERCENTAGE_FORMAT
```
## Class atm.account.ManagerAccount extends Account
- Just useless class. Need to be implemented in the future.
## Class atm.Pin 
### Variables
- pin
```
/**
 * The 4 digit numerical PIN.
 */
 
private String pin
```
### Constructor
```
/**
 * Constructs a Pin object.
 *
 * @param pin
 *        A 4 digit numerical PIN.
 * @param confirmPin
 *        The same 4 digit numerical PIN entered again for confirmation.
 * @throws IllegalArgumentException
 *         Thrown if the PIN entered is not valid, or if the PINs do not match.
 */
 
public Pin(String pin, String confirmPin) throws IllegalArgumentException
```
### Methods
- IS_VALID_PIN
```
/**
 * Checks to see if the given PIN is 4 numerical digits.
 *
 * @param pin
 *        The PIN to be validated.
 * @return True, if the PIN is valid.
 * @throws IllegalArgumentException
 *         Thrown if the PIN is invalid.
 */
 
public static boolean IS_VALID_PIN(String pin) throws IllegalArgumentException
```
- PINs_MATCH
```
/**
 * Determines whether or not the user has entered the correct PIN by asking for the PIN twice to confirm.
 *
 * @param pin
 *        The PIN to be confirmed.
 * @param confirmPin
 *        The same PIN entered again.
 * @return True, if both PINs are the same.
 * @throws IllegalArgumentException
 *         Thrown if the PINs are not the same.
 */
 
public static boolean PINs_MATCH
```
- IS_CORRECT_PIN
```
/**
 * Performs a check to see if the user has entered the correct PIN to login to their account.
 *
 * @param accountNumber
 *        The account trying to be accessed.
 * @param accountPin
 *        The PIN the user enters.
 * @return True, if the user enters the correct PIN for their account.
 * @throws IllegalArgumentException
 *         Thrown if the incorrect PIN is entered.
 * @throws NullPointerException
 *         Thrown if the account could not be found.
 */
 
public static boolean IS_CORRECT_PIN(int accountNumber, String accountPin) throws IllegalArgumentException, NullPointerException
```
## Class atm.account.Currency(Abstract)
- nothing but just a abstract class.
## Class atm.account.CurrencyImpl.DOLLAR extends Currency
### Variables
- US_DOLLARS
```
/**
 * transfer to form of dollar
 */
static final NumberFormat US_DOLLARS = NumberFormat.getCurrencyInstance(Locale.US);
```
### Methods
- TO_US_CURRENCY_FORMAT
```
/**
 * transfer BigDecimal to us dollar format
 */
public static final String TO_US_CURRENCY_FORMAT(BigDecimal amount)
```
## Class atm.account.CurrencyImpl.RMB extends Currency(not implemented in code right now)
### Variables
- CN_RMB
```
/**
 * transfer to form of RMB
 */
static final NumberFormat CN_RMB 
```
### Methods
- TO_CN_CURRENCY_FORMAT
```
/**
 * transfer BigDecimal to cn RMB format
 */
public static final String TO_CN_CURRENCY_FORMAT(BigDecimal amount)
```
## Class atm.account.CurrencyImpl.FRANC extends Currency(not implemented in code right now)
### Variables
- FR_RRANC
```
/**
 * transfer to form of franc
 */
static final NumberFormat US_DOLLARS
```
### Methods
- TO_FRANC_CURRENCY_FORMAT
```
/**
 * transfer BigDecimal to france franc format
 */
public static final String TO_FRANC_CURRENCY_FORMAT(BigDecimal amount)
```
# GUI Design

