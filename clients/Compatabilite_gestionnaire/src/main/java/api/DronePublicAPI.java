package api;

import stubs.accounting.AccountingWebService;
import stubs.accounting.AccountingWebServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class DronePublicAPI {
    private AccountingWebService accountingWebService;

    public AccountingWebService getAccountingWebService() {
        return accountingWebService;
    }

    private void initAccounting(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/AccountingWebServiceImpl.wsdl");
        AccountingWebServiceImplService factory = new AccountingWebServiceImplService(wsdlLocation);
        this.accountingWebService = factory.getAccountingWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/AccountingWS";
        ((BindingProvider) accountingWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    public DronePublicAPI(String host, String port) {
        initAccounting(host, port);
    }


}
