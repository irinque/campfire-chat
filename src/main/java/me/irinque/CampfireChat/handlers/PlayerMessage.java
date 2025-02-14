package me.irinque.CampfireChat.handlers;

import me.irinque.CampfireChat.Main;
import me.irinque.CampfireChat.parsers.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

public class PlayerMessage implements Listener
{
    static Main plugin = Main.getInstance();

    @EventHandler
    public void onPlayerMessage(AsyncPlayerChatEvent event)
    {
        Player player_sender = event.getPlayer();
        String message_player = event.getMessage();

        if (Character.toString(message_player.charAt(0)).equals("!") & Objects.equals(plugin.getConfig().getString("global-chat.toggle"),"true"))
        {
            String world_prefix = "●";
            String message_template = plugin.getConfig().getString("global-chat.message");

            if (player_sender.getWorld().toString().contains("_nether")){world_prefix = "§c●";}
            if (player_sender.getWorld().toString().contains("_the_end")){world_prefix = "§d●";}
            if (!player_sender.getWorld().toString().contains("_nether") & !player_sender.getWorld().toString().contains("_the_end")){world_prefix = "§a●";}

            event.setFormat(message_template.replace("{world_prefix}", world_prefix).replace("{player}", player_sender.getDisplayName()).replace("{message}", message_player.substring(1)));
        }
        if (!Character.toString(message_player.charAt(0)).equals("!") & Objects.equals(plugin.getConfig().getString("local-chat.toggle"),"true"))
        {
            int count_listeners = 0;
            int radius_trigger = (int) plugin.getConfig().get("local-chat.radius");
            String message_template = plugin.getConfig().getString("global-chat.message");

            event.setCancelled(true);

            for (Player player_receiver : player_sender.getWorld().getPlayers())
            {
                if (player_receiver.getLocation().distance(player_sender.getLocation()) <= radius_trigger & !player_receiver.getUniqueId().equals(player_sender.getUniqueId()))
                {
                    count_listeners++;
                    player_receiver.sendMessage(message_template.replace("{player}", player_sender.getDisplayName()).replace("{message}", message_player));
                }
            }
            if (count_listeners == 0) {player_sender.sendMessage(Message.getMsg("NoRecipients"));}
            if (count_listeners > 0) {player_sender.sendMessage(message_template.replace("{player}", player_sender.getDisplayName()).replace("{message}", message_player));}
        }
    }
}
