package si.seraph.opensource.seraphapi.methodbases;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public abstract class SeraphCommandBase extends CommandBase {

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}

	protected String getFormattedString(String i18n) {
		return I18n.format("si.seraph.commands." + getCommandName() + "." + i18n);
	}

	protected String getFormattedString(String i18n, Object args) {
		return I18n.format("si.seraph.commands." + getCommandName() + "." + i18n, args);
	}

}
