package si.seraph.opensource.commands.statcommands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.bedwars.Bedwars;
import si.seraph.opensource.seraphapi.methodbases.SeraphCommandBase;
import si.seraph.opensource.seraphapi.utils.Handler;
import si.seraph.opensource.seraphapi.utils.chat.ChatColour;

public class BedwarStats extends SeraphCommandBase {


    @Override
    public String getCommandName() {
        return "bws";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1) {
            Handler.asExecutor(()-> {
                Bedwars bw = new Bedwars(args[0]);
                sendMessageWithBorder(bw.getFormattedStats());
            });
        } else {
            sendMessage(ChatColour.RED + "Command Usage: /bws <player>");
        }
    }

}
