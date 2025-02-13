package me.irinque.CampfireChat.handlers;

import me.irinque.CampfireChat.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class PlayerQuit implements Listener
{
    static Main plugin = Main.getInstance();

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();

        if (Objects.equals(plugin.getConfig().getString("custom-quit.toggle"), "true"))
        {
            String message_quit = plugin.getConfig().getString("custom-quit.message");
            event.setQuitMessage(message_quit.replace("{player}", player.getDisplayName()));
        }

    }
}
