package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.accounting.Supplier;

import java.util.List;

public class GetSupplier extends Command<DronePublicAPI> {
    private String name;

    @Override
    public String identifier() {
        return "get-supplier";
    }

    @Override
    public void load(List<String> args) {
        name = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        displayResult(shell.system.getAccountingWebService().findSupplierByName(name));
    }

    private void displayResult(Supplier supplier){
        System.out.println("Supplier : " + supplier.getName());
        for(String contact : supplier.getContacts()){
            System.out.println("\tContact : " + contact);
        }
    }

    @Override
    public String describe() {
        return identifier() + " (name)";
    }
}
