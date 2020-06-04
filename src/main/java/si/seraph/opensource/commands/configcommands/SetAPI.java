package si.seraph.opensource.commands.configcommands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import si.seraph.opensource.seraphapi.config.ModConfig;
import si.seraph.opensource.seraphapi.methodbases.SeraphCommandBase;
import si.seraph.opensource.seraphapi.utils.Handler;

public class SetAPI extends SeraphCommandBase {

    @Override
    public String getCommandName() {
        return "setapi";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Handler.asExecutor(()-> {
            String API_KEY = args[0];
            ModConfig.getInstance().setApiKey(API_KEY);
            ModConfig.getInstance().save();
        });
    }

}
