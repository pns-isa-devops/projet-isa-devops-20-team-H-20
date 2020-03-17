package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

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
        shell.system.getAccountingWebService().register(name, contact);
    }

    @Override
    public String describe() {
        return identifier() + " (name contact(mail or phone))";
    }
}
