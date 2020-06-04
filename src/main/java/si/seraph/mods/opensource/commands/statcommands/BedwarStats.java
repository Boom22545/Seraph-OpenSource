package si.seraph.mods.opensource.commands.statcommands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import si.seraph.mods.opensource.seraphapi.games.bedwars.Bedwars;
import si.seraph.mods.opensource.seraphapi.methodbases.SeraphCommandBase;
import si.seraph.mods.opensource.seraphapi.utils.Handler;
import si.seraph.mods.opensource.seraphapi.utils.chat.ChatUtils;

public class BedwarStats extends SeraphCommandBase {

    @Override
    public String getCommandName() {
        return "bws";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return getFormattedString("usage");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1) {
            Handler.asExecutor(()-> {
                Bedwars bw = new Bedwars(args[0]);
                ChatUtils.sendMessage(bw.getFormattedStats());
            });
        } else {
            ChatUtils.sendMessage(getFormattedString("usage"));
        }
    }

}
