package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

public class Bye extends Command<DronePublicAPI> {

    @Override
    public String identifier() { return "bye"; }

    @Override
    public void execute() { }

    @Override
    public String describe() {
        return "Exit Cookie on Demand";
    }

    @Override
    public boolean shouldContinue() { return false; }
}
