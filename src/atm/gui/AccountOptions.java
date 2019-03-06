package atm.gui;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("javadoc")
final class AccountOptions extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static Font labelFont = new Font("Dialog", Font.BOLD, 14);
    private JLabel accountOptionsLabel;
    private JButton backButton, checkingAccountButton, savingAccountButton;


    public AccountOptions() {

        super(null);

        accountOptionsLabel = new JLabel("Account options");
        accountOptionsLabel.setFont(labelFont);
        accountOptionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountOptionsLabel.setVerticalAlignment(SwingConstants.CENTER);
        accountOptionsLabel.setBounds(150, 10, 200, 50);


        checkingAccountButton = new JButton("Create checking account");
        checkingAccountButton.setToolTipText("Checking account.");
        checkingAccountButton.setBounds(110, 145, 300, 50);

        savingAccountButton = new JButton("Create saving account");
        savingAccountButton.setToolTipText("Saving account.");
        savingAccountButton.setBounds(110, 215, 300, 50);

        backButton = new JButton("Back");
        backButton.setToolTipText("Return to the main menu.");
        backButton.setBounds(185, 285, 150, 50);

        this.add(accountOptionsLabel);
        this.add(backButton);
        this.add(checkingAccountButton);
        this.add(savingAccountButton);

    }

    public JLabel getAccountOptionsLabel() {
        return this.accountOptionsLabel;
    }

    public JButton getBackButton() {
        return this.backButton;
    }

    public JButton getCheckingAccountButton() {
        return this.checkingAccountButton;
    }

    public JButton getSavingAccountButton() {
        return this.savingAccountButton;
    }


}
