package hu.dual.invoice.service;

import hu.dual.invoice.model.Currencies;
import hu.dual.invoice.util.XMLUtil;
import hu.mnb.webservices.*;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MNBCallerService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public BigDecimal getActualExchangeRate(Currencies currency) throws MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage {

        MNBArfolyamServiceSoapImpl service = new MNBArfolyamServiceSoapImpl();
        MNBArfolyamServiceSoap client = service.getCustomBindingMNBArfolyamServiceSoap();

        GetExchangeRatesRequestBody request = new GetExchangeRatesRequestBody();
        request.setCurrencyNames(currency.name());
        request.setStartDate(dateFormat.format(new Date()));
        request.setEndDate(dateFormat.format(new Date()));

        GetExchangeRatesResponseBody response = client.getExchangeRates(request);
        Document doc = XMLUtil.stringToDocument(response.getGetExchangeRatesResult());

        Node rateNode = doc.getFirstChild().getFirstChild();
        String rateString = rateNode.getTextContent();
        return BigDecimal.valueOf(Double.parseDouble(rateString.replace(",", ".")));

    }

}
