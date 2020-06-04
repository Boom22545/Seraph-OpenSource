package si.seraph.opensource.commands.statcommands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import si.seraph.opensource.seraphapi.games.bedwars.Bedwars;
import si.seraph.opensource.seraphapi.utils.Handler;
import si.seraph.opensource.seraphapi.utils.chat.ChatUtils;

public class BedwarStats extends CommandBase {


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
        Handler.asExecutor(()-> {
            Bedwars bw = new Bedwars(args[0]);
            ChatUtils.sendMessage(bw.getFormattedStats());
        });
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
