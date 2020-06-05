package si.seraph.opensource.mods.commands.statcommands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.bedwars.Bedwars;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.classic.arenabrawl.ArenaBrawl;
import si.seraph.opensource.seraphapi.methodbases.SeraphCommandBase;
import si.seraph.opensource.seraphapi.utils.Handler;

public class ArenaBrawlStats extends SeraphCommandBase {
    @Override
    public String getCommandName() {
        return "abs";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return getFormattedString("usage");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1) {
            Handler.asExecutor(()-> {
                ArenaBrawl ab = new ArenaBrawl(args[0]);
                sendMessageWithBorder(ab.getFormattedStats());
            });
        } else {
            sendMessage(getFormattedString("usage"));
        }
    }

}