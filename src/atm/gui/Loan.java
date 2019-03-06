package atm.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

@SuppressWarnings("javadoc")
final class Loan extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static Font labelFont = new Font("Dialog", Font.BOLD, 14);
    private JLabel loanLabel, loanAmountLabel;
    private JTextField loanAmountField;
    private JButton backButton, loanButton;
    private double loanAmount;

    public Loan() {
        super(null);

        loanLabel = new JLabel("Loan");
        loanLabel.setFont(labelFont);
        loanLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loanLabel.setVerticalAlignment(SwingConstants.CENTER);
        loanLabel.setBounds(150, 10, 200, 50);

        loanAmountLabel = new JLabel("Loan Amount:");
        loanAmountLabel.setFont(labelFont);
        loanAmountLabel.setVerticalAlignment(SwingConstants.CENTER);
        loanAmountLabel.setBounds(75, 137, 200, 50);

        loanAmountField = new JTextField();
        loanAmountField.setFont(labelFont);
        loanAmountField.setToolTipText("Enter the amount you want to loan to your account.");
        loanAmountField.setBounds(225, 138, 200, 50);

        backButton = new JButton("Back");
        backButton.setToolTipText("Return to the account menu.");
        backButton.setBounds(75, 285, 150, 50);

        loanButton = new JButton("Loan");
        loanButton.setToolTipText("Loan the desired amount into your account.");
        loanButton.setBounds(275, 285, 150, 50);

        this.add(loanLabel);
        this.add(loanAmountLabel);
        this.add(loanAmountField);
        this.add(backButton);
        this.add(loanButton);

    }

    public JLabel getLoanLabel() {
        return this.loanLabel;
    }

    public JLabel getLoanAmountLabel() {
        return this.loanAmountLabel;
    }

    public JTextField getLoanAmountField() {
        return this.loanAmountField;
    }

    public JButton getBackButton() {
        return this.backButton;
    }

    public JButton getLoanButton() {
        return this.loanButton;
    }

    public double getLoanAmount() {
        try {
            this.loanAmount = Double.parseDouble(getLoanAmountField().getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
        return this.loanAmount;
    }

}
