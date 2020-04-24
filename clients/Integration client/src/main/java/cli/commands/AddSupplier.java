package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.accounting.Supplier;

import java.util.List;

public class AddSupplier extends Command<DronePublicAPI> {
    private String name;
    private String contact;


    @Override
    public String identifier() {
        return "add-supplier";
    }

    @Override
    public void load(List<String> args) {
        name = args.get(0);
        contact = args.get(1);
    }

    @Override
    public void execute() throws Exception {
        Supplier res = shell.system.getAccountingWebService().registerSupplier(name, contact);
        if(res != null){
            System.out.println("Supplier added");
        }else{
            System.out.println("Error supplier not added");
        }
    }

    @Override
    public String describe() {
        return identifier() + " (name contact(mail or phone))";
    }
}
