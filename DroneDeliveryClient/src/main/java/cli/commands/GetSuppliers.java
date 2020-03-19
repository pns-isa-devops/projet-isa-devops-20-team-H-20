package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.accounting.Supplier;

import java.util.List;

public class GetSuppliers extends Command<DronePublicAPI> {

    @Override
    public String identifier() {
        return "get-suppliers";
    }

    @Override
    public void execute() throws Exception {
        displayResult(shell.system.getAccountingWebService().find);
    }

    private void displayResult(Supplier supplier){
        System.out.println("Supplier : " + supplier.getName());
    }

    @Override
    public String describe() {
        return identifier() + " (name)";
    }
}
