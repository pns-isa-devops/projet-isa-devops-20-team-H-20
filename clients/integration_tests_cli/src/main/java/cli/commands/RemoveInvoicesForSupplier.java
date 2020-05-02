package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class RemoveInvoicesForSupplier extends Command<DronePublicAPI> {
    private String name;

    @Override
    public String identifier() {
        return "remove-invoices-for-supplier";
    }

    @Override
    public void load(List<String> args) {
        name = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        boolean res = shell.system.getAccountingWebService().deleteInvoicesForSupplier(name);
        if(res){
            System.out.println("Invoices deleted for supplier");
        }else{
            System.out.println("Error invoices not deleted for supplier");
        }
    }

    @Override
    public String describe() {
        return identifier() + " (name)";
    }
}
