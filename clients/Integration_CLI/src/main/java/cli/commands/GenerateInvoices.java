package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.accounting.Supplier;

import java.util.List;

public class GenerateInvoices extends Command<DronePublicAPI> {

    @Override
    public String identifier() {
        return "generate-invoices";
    }

    @Override
    public void execute(){
        shell.system.getAccountingWebService().generateInvoicesForAllSuppliers();
        System.out.println("Invoices generated");
    }

    @Override
    public String describe() {
        return identifier();
    }
}
