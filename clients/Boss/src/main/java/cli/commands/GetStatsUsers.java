package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class GetStatsUsers extends Command<DronePublicAPI> {

    @Override
    public String identifier() {
        return "get-stats-user";
    }

    @Override
    public void load(List<String> args) {

    }

    @Override
    public void execute() throws Exception {

    }

    @Override
    public String describe() {
        return identifier();
    }
}
