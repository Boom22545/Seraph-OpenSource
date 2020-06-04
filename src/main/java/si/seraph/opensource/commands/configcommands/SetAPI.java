package si.seraph.opensource.commands.configcommands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import si.seraph.opensource.seraphapi.config.ModConfig;
import si.seraph.opensource.seraphapi.utils.Handler;

public class SetAPI extends CommandBase {

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

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
