package me.irinque.CampfireChat.handlers;

import me.irinque.CampfireChat.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import java.util.Objects;

public class PlayerJoin implements Listener
{
    static Main plugin = Main.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        if (Objects.equals(plugin.getConfig().getString("custom-join.toggle"), "true"))
        {
            String message_join = plugin.getConfig().getString("custom-join.message");
            event.setJoinMessage(message_join.replace("{player}", player.getDisplayName()));
        }

    }
}
