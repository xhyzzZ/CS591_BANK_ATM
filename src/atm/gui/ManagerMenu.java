package atm.gui;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("javadoc")
final class ManagerMenu extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static Font labelFont = new Font("Dialog", Font.BOLD, 14);
    private JLabel historyLabel;
    private JButton backButton, showTransactionHistoryButton, clearTransactionHistoryButton;


    public ManagerMenu() {

        super(null);

        historyLabel = new JLabel("Transaction Histoty");
        historyLabel.setFont(labelFont);
        historyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        historyLabel.setVerticalAlignment(SwingConstants.CENTER);
        historyLabel.setBounds(150, 10, 200, 50);


        showTransactionHistoryButton = new JButton("Show Transaction History");
        showTransactionHistoryButton.setToolTipText("Show.");
        showTransactionHistoryButton.setBounds(110, 145, 300, 50);

        clearTransactionHistoryButton = new JButton("Clear Transaction History");
        clearTransactionHistoryButton.setToolTipText("Clear.");
        clearTransactionHistoryButton.setBounds(110, 215, 300, 50);

        backButton = new JButton("Back");
        backButton.setToolTipText("Return to the main menu.");
        backButton.setBounds(185, 285, 150, 50);

        this.add(historyLabel);
        this.add(backButton);
        this.add(showTransactionHistoryButton);
        this.add(clearTransactionHistoryButton);

    }

    public JLabel getHistoryLabel() {
        return this.historyLabel;
    }

    public JButton getBackButton() {
        return this.backButton;
    }

    public JButton getShowTransactionHistoryButton() {
        return this.showTransactionHistoryButton;
    }

    public JButton getClearTransactionHistoryButton() {
        return this.clearTransactionHistoryButton;
    }


}