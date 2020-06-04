package dooger.mods.statgrab.listeners;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.Arrays;
import java.util.List;

public class SpamPartyCommand extends CommandBase {

    boolean isEnabled = false;

    @Override
    public String getCommandName() {
        return "partyspam";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) { return "partyspam";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (isEnabled) {
            isEnabled = false;
        } else {
            isEnabled = true;
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(final ICommandSender sender) { return true; }

}
