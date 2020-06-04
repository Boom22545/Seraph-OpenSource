package si.seraph.opensource.seraphapi.methodbases;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import si.seraph.opensource.seraphapi.utils.References;
import si.seraph.opensource.seraphapi.utils.chat.ChatColour;

public abstract class SeraphCommandBase extends CommandBase {

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}

	public void sendMessage(String message) {
		try {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColour.translateAlternateColourCodes(message)));
		} catch (Exception ignored) {

		}
	}

	public void sendMessageWithBorder(String message) {
		try {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColour.translateAlternateColourCodes(References.PREFIX + message + References.SUFFIX)));
		} catch (Exception ignored) {

		}
	}

	public String getFormattedString(String i18n) {
		return I18n.format("si.seraph.commands." + getCommandName() + "." + i18n);
	}

	protected String getFormattedString(String i18n, Object args) {
		return I18n.format("si.seraph.commands." + getCommandName() + "." + i18n, args);
	}

}
