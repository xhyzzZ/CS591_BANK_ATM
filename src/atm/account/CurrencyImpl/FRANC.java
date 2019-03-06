package atm.account.CurrencyImpl;

import atm.account.Currency;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class FRANC extends Currency {
    static final NumberFormat FR_RRANC = NumberFormat.getCurrencyInstance(Locale.FRANCE);

    public static final String TO_FRANC_CURRENCY_FORMAT(BigDecimal amount) {
        return FR_RRANC.format(amount);
    }
}
