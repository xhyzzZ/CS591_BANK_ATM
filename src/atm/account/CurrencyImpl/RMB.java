package atm.account.CurrencyImpl;

import atm.account.Currency;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class RMB extends Currency {
    static final NumberFormat CN_RMB = NumberFormat.getCurrencyInstance(Locale.CHINA);

    public static final String TO_CN_CURRENCY_FORMAT(BigDecimal amount) {
        return CN_RMB.format(amount);
    }
}
