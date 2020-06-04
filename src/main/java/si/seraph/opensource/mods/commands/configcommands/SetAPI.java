package si.seraph.opensource.mods.commands.configcommands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import si.seraph.opensource.seraphapi.config.ModConfig;
import si.seraph.opensource.seraphapi.methodbases.SeraphCommandBase;
import si.seraph.opensource.seraphapi.utils.Handler;
import si.seraph.opensource.seraphapi.utils.chat.ChatColour;
import si.seraph.opensource.seraphapi.utils.chat.ChatUtils;

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
        if (args.length == 1) {
            Handler.asExecutor(()-> {
                String API_KEY = args[0];
                ModConfig.getInstance().setApiKey(API_KEY);
                ModConfig.getInstance().save();
            });
        } else {
            ChatUtils.sendMessage(ChatColour.RED + "Command Usage: /setapi <api_key>");
        }
    }

}
