package hu.dual.invoice.service;

import hu.dual.invoice.model.Currencies;
import hu.mnb.webservices.GetExchangeRatesRequestBody;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MNBCaller {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public BigDecimal getActualExchangeRate(Currencies currency) {
        GetExchangeRatesRequestBody request = new GetExchangeRatesRequestBody();
        request.setCurrencyNames(currency.name());
        request.setStartDate(dateFormat.format(new Date()));
        request.setEndDate(dateFormat.format(new Date()));
        return null;
    }

}
