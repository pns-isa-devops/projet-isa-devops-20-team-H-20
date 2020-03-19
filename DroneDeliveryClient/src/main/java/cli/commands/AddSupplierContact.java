package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class AddSupplierContact extends Command<DronePublicAPI> {
    private String name;
    private String contact;


    @Override
    public String identifier() {
        return "add-supplier-contact";
    }

    @Override
    public void load(List<String> args) {
        name = args.get(0);
        contact = args.get(1);
    }

    @Override
    public void execute() throws Exception {
        boolean res = shell.system.getAccountingWebService().addSupplierContact(name, contact);
        if(res){
            System.out.println("Supplier contact added");
        }else{
            System.out.println("Error supplier contact not added");
        }
    }

    @Override
    public String describe() {
        return identifier() + " (name contact(mail or phone))";
    }
}
