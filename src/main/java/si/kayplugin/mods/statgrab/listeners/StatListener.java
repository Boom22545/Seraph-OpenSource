package si.kayplugin.mods.statgrab.listeners;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class StatListener {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void finalKills(ClientChatReceivedEvent chat) {
        String msg = chat.message.getFormattedText();
        if (msg.contains("JohnRoe")) {
            System.out.println("LISTENER IS WORKING");
        }
        if(msg.contains("FINAL KILL!")) {
            System.out.println("Final Kill Found");
            String[] finalkillmsg = msg.split(" ");
            if (finalkillmsg[1].equalsIgnoreCase("was")) {
                String name = finalkillmsg[2].replace("'s", "").trim();
                System.out.println(name);
            } else if (msg.contains("by")) {
                String name = msg.replace(". FINAL KILL!", "");
                String[] name_ = name.split(" ");
                String test = name_[name.length() - 1];
                System.out.println(test);
            }
        }
    }
}
