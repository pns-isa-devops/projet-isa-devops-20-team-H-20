package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.accounting.Invoice;

import java.util.List;

public class FindInvoicesNotPaid extends Command<DronePublicAPI> {

    @Override
    public String identifier() {
        return "get-notpaid-invoices";
    }

    @Override
    public void execute() throws Exception {
        List<Invoice> invoices = shell.system.getAccountingWebService().findAllUnpaidInvoices();
        for(Invoice invoice : invoices){
            System.out.println(invoice);
        }
    }

    @Override
    public String describe() {
        return identifier();
    }
}
