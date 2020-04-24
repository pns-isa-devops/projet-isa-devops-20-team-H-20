package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.accounting.Supplier;

import java.util.List;

public class GenerateInvoiceSupplier extends Command<DronePublicAPI> {
    private String name;

    @Override
    public String identifier() {
        return "generate-invoice-supplier";
    }

    @Override
    public void load(List<String> args) {
        name = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        shell.system.getAccountingWebService().generateInvoiceFor(name);
    }

    @Override
    public String describe() {
        return identifier() + " (name)";
    }
}
