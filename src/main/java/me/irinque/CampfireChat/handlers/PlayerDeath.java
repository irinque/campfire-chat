package me.irinque.CampfireChat.handlers;

import me.irinque.CampfireChat.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class PlayerDeath implements Listener
{
    static Main plugin = Main.getInstance();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (Objects.equals(plugin.getConfig().getString("custom-death.toggle"), "true"))
        {
            Player player = event.getEntity();
            String message_template = plugin.getConfig().getString("custom-death.message");
            String message_death = event.getDeathMessage().toString();
            event.setDeathMessage(message_template.replace("{death_message}", message_death).replace("{death_sign}", "[\uD83D\uDC80]").replace("{player}", player.getDisplayName()));
        }
    }
}
