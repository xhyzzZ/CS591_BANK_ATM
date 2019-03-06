package atm.account.CurrencyImpl;


import atm.account.Currency;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class DOLLAR extends Currency {
    static final NumberFormat US_DOLLARS = NumberFormat.getCurrencyInstance(Locale.US);

    public static final String TO_US_CURRENCY_FORMAT(BigDecimal amount) {
        return US_DOLLARS.format(amount);
    }
}
