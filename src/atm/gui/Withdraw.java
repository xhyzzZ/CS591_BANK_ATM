package atm.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

@SuppressWarnings("javadoc")
final class Withdraw extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static Font labelFont = new Font("Dialog", Font.BOLD, 14);
    private JLabel withdrawLabel, withdrawAmountLabel;
    private JTextField withdrawAmountField;
    private JButton backButton, withdrawButton;
    private double withdrawAmount;

    public Withdraw() {
        super(null);

        withdrawLabel = new JLabel("Withdraw");
        withdrawLabel.setFont(labelFont);
        withdrawLabel.setHorizontalAlignment(SwingConstants.CENTER);
        withdrawLabel.setVerticalAlignment(SwingConstants.CENTER);
        withdrawLabel.setBounds(150, 10, 200, 50);

        withdrawAmountLabel = new JLabel("Withdraw Amount:");
        withdrawAmountLabel.setFont(labelFont);
        withdrawAmountLabel.setVerticalAlignment(SwingConstants.CENTER);
        withdrawAmountLabel.setBounds(75, 137, 200, 50);

        withdrawAmountField = new JTextField();
        withdrawAmountField.setFont(labelFont);
        withdrawAmountField.setToolTipText("Enter the amount you want to withdraw to your account.");
        withdrawAmountField.setBounds(225, 138, 200, 50);

        backButton = new JButton("Back");
        backButton.setToolTipText("Return to the account menu.");
        backButton.setBounds(75, 285, 150, 50);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setToolTipText("Withdraw the desired amount into your account.");
        withdrawButton.setBounds(275, 285, 150, 50);

        this.add(withdrawLabel);
        this.add(withdrawAmountLabel);
        this.add(withdrawAmountField);
        this.add(backButton);
        this.add(withdrawButton);

    }

    public JLabel getWithdrawLabel() {
        return this.withdrawLabel;
    }

    public JLabel getWithdrawAmountLabel() {
        return this.withdrawAmountLabel;
    }

    public JTextField getWithdrawAmountField() {
        return this.withdrawAmountField;
    }

    public JButton getBackButton() {
        return this.backButton;
    }

    public JButton getWithdrawButton() {
        return this.withdrawButton;
    }

    public double getWithdrawAmount() {
        try {
            this.withdrawAmount = Double.parseDouble(withdrawAmountField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
        return this.withdrawAmount;
    }

}
