package atm.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;


@SuppressWarnings("javadoc")
final class AccountMenu extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static Font labelFont = new Font("Dialog", Font.BOLD, 14);
    private JLabel accountMenuLabel;
    private JButton makeDepositButton, makeWithdrawalButton, makeLoanButton, makeTransferButton, checkBalanceButton, logoutButton;

    public AccountMenu() {
        super(null);

        accountMenuLabel = new JLabel("Account Menu");
        accountMenuLabel.setFont(labelFont);
        accountMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountMenuLabel.setVerticalAlignment(SwingConstants.CENTER);
        accountMenuLabel.setBounds(150, 10, 200, 50);

        makeDepositButton = new JButton("Deposit");
        makeDepositButton.setToolTipText("Make a deposit to your account.");
        makeDepositButton.setBounds(150, 70, 200, 50);

        makeWithdrawalButton = new JButton("Withdraw");
        makeWithdrawalButton.setToolTipText("Make a withdrawal from your account.");
        makeWithdrawalButton.setBounds(150, 130, 200, 50);

        makeLoanButton = new JButton("Loan");
        makeLoanButton.setToolTipText("Make a loan from your account.");
        makeLoanButton.setBounds(150, 190, 200, 50);

        makeTransferButton = new JButton("Transfer");
        makeTransferButton.setToolTipText("Make a transfer from your account to another account.");
        makeTransferButton.setBounds(150, 250, 200, 50);

        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setToolTipText("Check your account balance.");
        checkBalanceButton.setBounds(150, 310, 200, 50);


        logoutButton = new JButton("Logout");
        logoutButton.setToolTipText("Logout from your account.");
        logoutButton.setBounds(150, 418, 200, 50);

        this.add(accountMenuLabel);
        this.add(makeDepositButton);
        this.add(makeWithdrawalButton);
        this.add(makeTransferButton);
        this.add(checkBalanceButton);
        this.add(logoutButton);
        this.add(makeLoanButton);
    }

    public JLabel getAccountMenuLabel() {
        return this.accountMenuLabel;
    }

    public JButton getMakeDepositButton() {
        return this.makeDepositButton;
    }

    public JButton getMakeWithdrawalButton() {
        return this.makeWithdrawalButton;
    }

    public JButton getMakeLoanButton() {
        return this.makeLoanButton;
    }

    public JButton getMakeTransferButton() {
        return this.makeTransferButton;
    }

    public JButton getCheckBalanceButton() {
        return this.checkBalanceButton;
    }

    public JButton getLogoutButton() {
        return this.logoutButton;
    }

}
