package me.irinque.CampfireChat.handlers;

import me.irinque.CampfireChat.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerMessage implements Listener
{
    static Main plugin = Main.getInstance();

    @EventHandler
    public void onPlayerMessage(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();
        String message_player = event.getMessage();

        if (Character.toString(message_player.charAt(0)).equals("!"))
        {
            String world_prefix = "●";
            String message_template = plugin.getConfig().getString("global-chat.message");

            if (player.getWorld().toString().contains("_nether")){world_prefix = "§c●";}
            if (player.getWorld().toString().contains("_the_end")){world_prefix = "§d●";}
            if (!player.getWorld().toString().contains("_nether") & !player.getWorld().toString().contains("_the_end")){world_prefix = "§a●";}

            event.setFormat(message_template.replace("{world_prefix}", world_prefix).replace("{player}", player.getDisplayName()).replace("{message}", message_player.substring(1)));
        }
    }
}
