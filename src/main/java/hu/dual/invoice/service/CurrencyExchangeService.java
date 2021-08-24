package hu.dual.invoice.service;

import hu.dual.invoice.model.Currencies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@Component
public class CurrencyExchangeService {

    public static BigDecimal exchangeRate;

    @Autowired
    MNBCaller mnbCaller;

    @Scheduled
    public void getActualExchangeRate() {
        exchangeRate = BigDecimal.ZERO;
        exchangeRate.add(mnbCaller.getActualExchangeRate(Currencies.EUR));
    }

}
