package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.accounting.Invoice;

import java.util.List;

public class FindInvoicesSupplier extends Command<DronePublicAPI> {
    private String name;

    @Override
    public String identifier() {
        return "find-invoices-supplier";
    }

    @Override
    public void load(List<String> args) {
        name = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        List<Invoice> invoices = shell.system.getAccountingWebService().findInvoicesForSupplier(name);
        for(Invoice invoice : invoices){
            System.out.println(invoice);
        }
    }

    @Override
    public String describe() {
        return identifier() + " (name)";
    }
}
