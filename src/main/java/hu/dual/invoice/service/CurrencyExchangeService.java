package hu.dual.invoice.service;

import hu.dual.invoice.model.Currencies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CurrencyExchangeService {

    public static BigDecimal exchangeRate;

    @Autowired
    MNBCallerService mnbCallerService;

    @Scheduled(cron = "1 0 * * * *")
    //@Scheduled(cron = "* * * * * *")
    public void getActualExchangeRate() throws Exception {
        exchangeRate = mnbCallerService.getActualExchangeRate(Currencies.EUR);
    }

}
